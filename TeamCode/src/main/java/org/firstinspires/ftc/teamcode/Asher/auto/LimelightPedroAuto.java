package org.firstinspires.ftc.teamcode.Asher.auto;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.Main.Pedro.PedroConstants;
import static com.pedropathing.api.Paths.line;

/**
 * LimelightPedroAuto
 * 
 * Advanced Autonomous OpMode using Limelight 3A and Pedro Pathing.
 * Features:
 * - Multi-color search (Blue/Red/Yellow).
 * - Continuous tracking for moving balls.
 * - Recovery logic if target is lost.
 * - Active search (spinning) until a target is found.
 */
@Autonomous(name = "Limelight Pedro Advanced Auto")
public class LimelightPedroAuto extends CommandOpMode {

    private Follower follower;
    private Limelight3A limelight;
    private ElapsedTime searchTimer = new ElapsedTime();
    private ElapsedTime lostTargetTimer = new ElapsedTime();

    // --- Configuration ---
    private static final double CAMERA_HEIGHT_INCHES = 10.0;
    private static final double TARGET_HEIGHT_INCHES = 1.5;
    private static final double CAMERA_MOUNT_ANGLE = 20.0;
    
    // Target threshold: distance from target to consider it "reached"
    private static final double REACHED_THRESHOLD_INCHES = 2.5;

    // Search pipelines (Example: 0=Blue, 1=Red, 2=Yellow)
    private final int[] targetPipelines = {0, 1, 2}; 
    private int currentPipelineIndex = 0;

    private enum State {
        SEARCHING,
        TRACKING,
        APPROACHING,
        LOST,
        TARGET_REACHED
    }

    private State currentState = State.SEARCHING;

    @Override
    public void initialize() {
        follower = PedroConstants.create(hardwareMap);
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(targetPipelines[currentPipelineIndex]);
        limelight.start();

        telemetry.addData("Status", "Advanced Init Success");
        telemetry.update();
    }

    @Override
    public void run() {
        if (follower != null) follower.update();

        LLResult result = limelight.getLatestResult();
        boolean targetVisible = (result != null && result.isValid());

        switch (currentState) {
            case SEARCHING:
                // Spin at maximum safe speed to find a ball
                if (follower != null) {
                    follower.manual(0, 0, 0.6); // 0.6 is maximum safe spin speed to maintain tracking visibility
                }
                
                if (targetVisible) {
                    currentState = State.TRACKING;
                } else if (searchTimer.seconds() > 5.0) {
                    // Switch color pipeline if nothing found after 5 seconds
                    currentPipelineIndex = (currentPipelineIndex + 1) % targetPipelines.length;
                    limelight.pipelineSwitch(targetPipelines[currentPipelineIndex]);
                    searchTimer.reset();
                }
                break;

            case TRACKING:
            case APPROACHING:
                if (targetVisible) {
                    lostTargetTimer.reset();
                    double tx = result.getTx();
                    double ty = result.getTy();
                    
                    double angleToTarget = CAMERA_MOUNT_ANGLE + ty;
                    double distance = (CAMERA_HEIGHT_INCHES - TARGET_HEIGHT_INCHES) / Math.tan(Math.toRadians(angleToTarget));

                    // Check if target is close enough to be considered reached
                    if (distance <= REACHED_THRESHOLD_INCHES) {
                        if (follower != null) {
                            follower.stop(); // Stop the robot drivetrain
                        }
                        currentState = State.TARGET_REACHED;
                        break;
                    }

                    // Continuous adjustment for moving balls
                    if (follower != null) {
                        Pose currentPose = follower.pose();
                        double targetHeading = currentPose.heading() + Math.toRadians(tx);
                        
                        // Buffer distance: Stop slightly away from the ball
                        double driveDist = Math.max(0, distance - 2.0);
                        
                        double relX = driveDist * Math.cos(targetHeading);
                        double relY = driveDist * Math.sin(targetHeading);

                        Pose targetPose = new Pose(currentPose.x() + relX, currentPose.y() + relY, targetHeading);
                        
                        // follow() allows re-calling to update the path dynamically
                        follower.follow(line(currentPose, targetPose).constant(targetHeading));
                        currentState = State.APPROACHING;
                    }
                } else {
                    currentState = State.LOST;
                    lostTargetTimer.reset();
                }
                break;

            case LOST:
                // Wait a moment to see if it reappears (latency/flicker protection)
                if (targetVisible) {
                    currentState = State.TRACKING;
                } else if (lostTargetTimer.seconds() > 1.5) {
                    // Genuinely lost, go back to searching
                    currentState = State.SEARCHING;
                    searchTimer.reset();
                }
                break;

            case TARGET_REACHED:
                // -------------------------------------------------------------
                // ADD YOUR CODE HERE ONCE TARGET HAS BEEN REACHED
                // Examples: Activate intake, drop off items, fire servo, etc.
                // -------------------------------------------------------------
                telemetry.addData("Action", "Executing scoring or intake logic...");
                
                // Optional: after finishing the action, go back to searching for next ball
                // currentState = State.SEARCHING;
                // searchTimer.reset();
                break;
        }

        telemetry.addData("State", currentState);
        telemetry.addData("Pipeline", currentPipelineIndex);
        telemetry.addData("Target Visible", targetVisible);
        if (targetVisible) {
            telemetry.addData("Target tx/ty", "%.2f / %.2f", result.getTx(), result.getTy());
        }
        telemetry.update();
    }
}

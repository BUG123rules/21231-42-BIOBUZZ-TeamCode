package org.firstinspires.ftc.teamcode.Asher.auto;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.Main.Pedro.PedroConstants;

/**
 * LimelightAutoCalibrate
 * 
 * Experimental file to use the Limelight 3A's field localization (MegaTag / Botpose)
 * to periodically update or initialize Pedro Pathing's localization coordinates.
 */
@Autonomous(name = "Limelight Auto Calibrate (Experimental)", group = "Asher")
public class LimelightAutoCalibrate extends CommandOpMode {

    private Follower follower;
    private Limelight3A limelight;

    @Override
    public void initialize() {
        follower = PedroConstants.create(hardwareMap);
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        
        // Pipeline configured for AprilTag field map localization
        limelight.pipelineSwitch(7); 
        limelight.start();

        telemetry.addData("Status", "Experimental Calibration Ready.");
        telemetry.update();
    }

    @Override
    public void run() {
        if (follower != null) follower.update();

        LLResult result = limelight.getLatestResult();

        if (result != null && result.isValid()) {
            // Get Botpose from Limelight (field relative coordinate system)
            Pose3D botpose = result.getBotpose();

            if (botpose != null) {
                // Convert inches/mm/meters depending on your Limelight setup
                // FTC Field coordinates use inches for Pedro Pathing
                double fieldX = botpose.getPosition().x; 
                double fieldY = botpose.getPosition().y;
                double fieldHeading = Math.toRadians(botpose.getOrientation().getYaw());

                // Inject the actual absolute pose back into Pedro Pathing to eliminate odometer drift
                if (follower != null) {
                    follower.setPose(new Pose(fieldX, fieldY, fieldHeading));
                    telemetry.addData("Localization Status", "CALIBRATED VIA LIMELIGHT");
                }
                
                telemetry.addData("Limelight Pose", "X: %.2f, Y: %.2f, H: %.2f", fieldX, fieldY, Math.toDegrees(fieldHeading));
            }
        } else {
            telemetry.addData("Localization Status", "Using Odometry Only (No AprilTags in sight)");
        }

        if (follower != null) {
            telemetry.addData("Pedro Pose", follower.pose().toString());
        }
        telemetry.update();
    }
}

package org.firstinspires.ftc.teamcode.Main.Meet0.Drive;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.Main.Meet0.Subsystems.IntakeCmds;
import org.firstinspires.ftc.teamcode.Main.Pedro.PedroConstants;

import com.pedropathing.follower.Follower;

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.ManualDrive;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;


@TeleOp (name = "TeleM0")
public class TeleM0 extends CommandOpMode
{
//    private Follower follower;
    private IntakeCmds intakeCmds;

    boolean started = false;

    @Override
    public void initialize()
    {
//        follower = PedroConstants.create(hardwareMap);
        intakeCmds = new IntakeCmds(hardwareMap);
    }

    @Override
    public void run()
    {
        if (!started)
        {
            started = true;

            intakeCmds.intakeOn();
            intakeCmds.closeAll();
        }

//        follower.update();
//
//        DrivePowers powers = ManualDrive.fieldCentric(
//                -gamepad1.left_stick_y,
//                gamepad1.left_stick_x,
//                gamepad1.right_stick_x,
//                follower.pose().heading()
//        );
//        follower.manual(powers);

        if (gamepad1.aWasPressed()) intakeCmds.intakeOn();
        if (gamepad1.bWasPressed()) intakeCmds.intakeOff();
        if (gamepad1.rightTriggerWasPressed()) intakeCmds.pollenOpen();
        if (gamepad1.rightTriggerWasReleased()) intakeCmds.pollenClose();
        if (gamepad1.leftTriggerWasPressed()) intakeCmds.nectarOpen();
        if (gamepad1.leftTriggerWasReleased()) intakeCmds.nectarClose();

        if (gamepad1.xWasPressed()) intakeCmds.openAll();
        if (gamepad1.xWasReleased()) intakeCmds.closeAll();

        if (gamepad1.leftBumperWasPressed()) intakeCmds.new PrepShootPollen().initialize();
        if (gamepad1.leftBumperWasReleased())
        {
            intakeCmds.intakeOn();
            intakeCmds.closeAll();
        }

        if (gamepad1.rightBumperWasPressed()) intakeCmds.new PrepShootNectar().initialize();
        if (gamepad1.rightBumperWasReleased())
        {
            intakeCmds.intakeOn();
            intakeCmds.closeAll();
        }

        if (gamepad1.bWasPressed()) intakeCmds.new PrepShootAll().initialize();
        if (gamepad1.bWasReleased())
        {
            intakeCmds.intakeOn();
            intakeCmds.closeAll();
        }

        if (gamepad2.yWasPressed())
        {
            schedule(new SequentialCommandGroup(
                    intakeCmds.new PrepShootPollen(),
                    new WaitCommand(1000),
                    new InstantCommand(() -> intakeCmds.intakeOn()),
                    new InstantCommand(() -> intakeCmds.closeAll()),
                    new WaitCommand(1000),
                    intakeCmds.new PrepShootNectar(),
                    new WaitCommand(1000),
                    new InstantCommand(() -> intakeCmds.intakeOn()),
                    new InstantCommand(() -> intakeCmds.closeAll()),
                    new WaitCommand(1000),
                    intakeCmds.new PrepShootAll(),
                    new WaitCommand(1000),
                    new InstantCommand(() -> intakeCmds.intakeOn()),
                    new InstantCommand(() -> intakeCmds.closeAll())
            ));
        }
    }
}

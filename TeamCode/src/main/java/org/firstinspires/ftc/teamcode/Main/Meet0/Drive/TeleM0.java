package org.firstinspires.ftc.teamcode.Main.Meet0.Drive;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.Main.Meet0.Subsystems.IntakeCmds;
import org.firstinspires.ftc.teamcode.Main.Pedro.PedroConstants;

import com.pedropathing.follower.Follower;

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.ManualDrive;



@TeleOp (name = "TeleM1")
public class TeleM0 extends CommandOpMode
{
    private Follower follower;
    private IntakeCmds intakeCmds;

    boolean started = false;

    @Override
    public void initialize()
    {
        follower = PedroConstants.create(hardwareMap);
    }

    @Override
    public void run()
    {
        if (!started)
        {
            started = true;

            intakeCmds.intakeOn();
        }

        follower.update();

        DrivePowers powers = ManualDrive.fieldCentric(
                -gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x,
                follower.pose().heading()
        );
        follower.manual(powers);

        if (gamepad1.aWasPressed()) intakeCmds.intakeOn();
        if (gamepad1.bWasPressed()) intakeCmds.intakeOff();
    }

    @Override
    public void end()
    {

    }

}

package org.firstinspires.ftc.teamcode.Main.Meet0.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeCmds
{
    private final DcMotor intake;

    public IntakeCmds(HardwareMap hwMap)
    {
        intake = hwMap.get(DcMotor.class, "intake");

        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void intakeOn()
    {
        intake.setPower(1);
    }

    public void intakeOff()
    {
        intake.setPower(0);
    }
}

package org.firstinspires.ftc.teamcode.Main.Meet0.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Main.HW_CONSTANTS;

public class IntakeCmds
{
    private final DcMotor intake;
    private final Servo pollenGate;
    private final Servo nectarGate;


    private final double P_OPEN_POS = 0;
    private final double P_CLOSE_POS = 1;

    private final double N_OPEN_POS = 0;
    private final double N_CLOSE_POS = 1;

    public IntakeCmds(HardwareMap hwMap)
    {
        intake = hwMap.get(DcMotor.class, HW_CONSTANTS.INTAKE_MOTOR);
        pollenGate = hwMap.get(Servo.class, HW_CONSTANTS.POLLEN_GATE_SERVO);
        nectarGate = hwMap.get(Servo.class, HW_CONSTANTS.NECTAR_GATE_SERVO);

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

    public void toggleIntake()
    {
        intake.setPower(intake.getPower() > 0.5 ? 1 : 0);
    }

    public void pollenOpen()
    {
        pollenGate.setPosition(P_OPEN_POS);
    }

    public void pollenClose()
    {
        pollenGate.setPosition(P_CLOSE_POS);
    }

    public void nectarOpen()
    {
        nectarGate.setPosition(N_OPEN_POS);
    }

    public void nectarClose()
    {
        nectarGate.setPosition(N_CLOSE_POS);
    }

    public void openAll()
    {
        pollenGate.setPosition(P_OPEN_POS);
        nectarGate.setPosition(N_OPEN_POS);
    }

    public void closeAll()
    {
        pollenGate.setPosition(P_CLOSE_POS);
        nectarGate.setPosition(N_CLOSE_POS);
    }

    public class PrepShootPollen extends CommandBase
    {
        private final ElapsedTime timer = new ElapsedTime();

        @Override
        public void initialize()
        {
            intakeOff();
            nectarClose();
            pollenOpen();

            timer.reset();
        }

        @Override
        public boolean isFinished()
        {
            return timer.seconds() >= 0.1;
        }
    }

    public class PrepShootNectar extends CommandBase
    {
        private final ElapsedTime timer = new ElapsedTime();

        @Override
        public void initialize()
        {
            intakeOff();
            pollenClose();
            nectarOpen();

            timer.reset();
        }

        @Override
        public boolean isFinished()
        {
            return timer.seconds() >= 0.1;
        }
    }

    public class PrepShootAll extends CommandBase
    {
        private final ElapsedTime timer = new ElapsedTime();

        @Override
        public void initialize()
        {
            intakeOff();
            openAll();

            timer.reset();
        }
    }
}

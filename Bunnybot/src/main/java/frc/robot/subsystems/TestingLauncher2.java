package frc.robot.subsystems;

//import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;
import frc.robot.helpers.motor.NewtonMotor;
import frc.robot.helpers.motor.NewtonMotor.IdleMode;
import frc.robot.helpers.motor.talonfx.KrakenX60Motor;


public class TestingLauncher2 {
    // Declaring motors used for launcher
    private NewtonMotor launcher2Motor1; 
    private NewtonMotor launcher2Motor2; 

    public TestingLauncher2() {
        launcher2Motor1 = new KrakenX60Motor(CAN.LAUNCHER_MOTOR_CAN_ID_1, false);
        launcher2Motor2 = new KrakenX60Motor(CAN.LAUNCHER_MOTOR_CAN_ID_2, false);
    }
}

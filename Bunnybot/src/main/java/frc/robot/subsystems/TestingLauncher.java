package frc.robot.subsystems;

//import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;
import frc.robot.helpers.motor.NewtonMotor;
import frc.robot.helpers.motor.NewtonMotor.IdleMode;
import frc.robot.helpers.motor.talonfx.KrakenX60Motor;


public class TestingLauncher {
    // Declaring motors used for launcher
    private NewtonMotor launcherMotor1; 
    private NewtonMotor launcherMotor2; 

    public TestingLauncher() {
        launcherMotor1 = new KrakenX60Motor(CAN.LAUNCHER_MOTOR_CAN_ID_1, false);
        launcherMotor2 = new KrakenX60Motor(CAN.LAUNCHER_MOTOR_CAN_ID_2, false);

    }

    /** 
     * Accepts the desired speed as a percentage and sets the motor to the given speed. 
     * @param percent Desired speed as a percentage.
    */
    public void setLauncherPercentOutput(double percent){
        launcherMotor1.setPercentOutput(percent);
        launcherMotor2.setPercentOutput(percent);
    }

    /**
     * Stops the launcher motor by setting by setting the percent output to 0
     */
    public void stop(){
        setLauncherPercentOutput(0);
    }
}

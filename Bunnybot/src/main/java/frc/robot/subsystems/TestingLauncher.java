package frc.robot.subsystems;

import au.grapplerobotics.LaserCan;
import au.grapplerobotics.interfaces.LaserCanInterface.Measurement;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CAN;
import frc.robot.Constants.INTAKE;
import frc.robot.helpers.motor.NewtonMotor;
import frc.robot.helpers.motor.talonfx.KrakenX60Motor;


public class TestingLauncher extends SubsystemBase {
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
    /**
     * Accepts the desired power of the motor and sets the motor to that power.
     * @param percent Desired percentage of the motor.
     * @return Returns a command to set motor power to given percentage.
     */
    public Command setLauncherCommand(double percent){
        return this.run(()->{setLauncherPercentOutput(percent);
        });
    }

    /**
     * Stops the intake motor by setting the percent output to 0.
     * @return Returns a command to stop the intake motor.
     */
    public Command stopLauncherCommand(){
        return this.runOnce(()->{
            setLauncherPercentOutput(0);
        });
    }

}

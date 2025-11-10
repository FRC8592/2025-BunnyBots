package frc.robot.subsystems;

//import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CAN;
import frc.robot.helpers.motor.NewtonMotor;
import frc.robot.helpers.motor.NewtonMotor.IdleMode;
import frc.robot.helpers.motor.spark.*;



public class TestingLauncher2 extends SubsystemBase {
    // Declaring motors used for launcher
    private NewtonMotor launcher2Motor1; 
    private NewtonMotor launcher2Motor2; 
    public TestingLauncher2() {
        //Top is Motor2
        //Bottom is Motor1
        launcher2Motor1 = new SparkFlexMotor(CAN.LAUNCHER2_MOTOR_CAN_ID_1, true);
        launcher2Motor2 = new SparkFlexMotor(CAN.LAUNCHER2_MOTOR_CAN_ID_2, false);
        SmartDashboard.putNumber("launch_motor1", 0.4);
        SmartDashboard.putNumber("launch_motor2", 0.4);

        //launcherSensor = new LaserCan(CAN.LAUNCHER2_BEAM_BREAK_CAN_ID);

        launcher2Motor1.setIdleMode(IdleMode.kCoast);
        launcher2Motor2.setIdleMode(IdleMode.kCoast);

    }

    /** 
     * Accepts the desired speed as a percentage and sets the motor to the given speed. 
     * @param percent Desired speed as a percentage.
    */
    public void setLauncherPercentOutput(double percent1, double percent2){
        //Debugging to try and see whether the method is running, which it does
        //System.out.println("This method is running");
        launcher2Motor1.setPercentOutput(percent1);
        launcher2Motor2.setPercentOutput(percent2);

    }

    /**
     * Stops the launcher motor by setting by setting the percent output to 0
     */
    public void stop(){
        setLauncherPercentOutput(0,0);
    }
    /**
     * Accepts the desired power of the motor and sets the motor to that power.
     * @param percent Desired percentage of the motor.
     * @return Returns a command to set motor power to given percentage.
     */
    public Command setLauncherCommand(double percent1, double percent2){
        return this.run(()->{setLauncherPercentOutput(percent1, percent2);
        });
    }

    /**
     * Stops the intake motor by setting the percent output to 0.
     * @return Returns a command to stop the intake motor.
     */
    public Command stopLauncherCommand(){
        return this.runOnce(()->{
            setLauncherPercentOutput(0,0);
        });
    }

}

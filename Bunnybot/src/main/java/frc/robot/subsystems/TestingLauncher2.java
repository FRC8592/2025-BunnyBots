package frc.robot.subsystems;

import au.grapplerobotics.LaserCan;
import au.grapplerobotics.interfaces.LaserCanInterface.Measurement;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CAN;
import frc.robot.Constants.INTAKE;
import frc.robot.helpers.motor.NewtonMotor;
import frc.robot.helpers.motor.NewtonMotor.IdleMode;
import frc.robot.helpers.motor.talonfx.KrakenX60Motor;


public class TestingLauncher2 extends SubsystemBase {
    // Declaring motors used for launcher
    private NewtonMotor launcher2Motor1; 
    private NewtonMotor launcher2Motor2; 
    private LaserCan launcherSensor; 

    public TestingLauncher2() {
        launcher2Motor1 = new KrakenX60Motor(CAN.LAUNCHER2_MOTOR_CAN_ID_1, false);
        launcher2Motor2 = new KrakenX60Motor(CAN.LAUNCHER2_MOTOR_CAN_ID_2, false);

        launcherSensor = new LaserCan(CAN.LAUNCHER2_BEAM_BREAK_CAN_ID);

        launcher2Motor1.setIdleMode(IdleMode.kBrake);
        launcher2Motor2.setIdleMode(IdleMode.kBrake);
    }

    /** 
     * Accepts the desired speed as a percentage and sets the motor to the given speed. 
     * @param percent Desired speed as a percentage.
    */
    public void setLauncherPercentOutput(double percent){
        launcher2Motor1.setPercentOutput(percent);
        launcher2Motor2.setPercentOutput(percent);
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
    public void periodic(){
       //Logs for tracking the motor speed 
       //Logger.recordOutput(LAUNCHER.LOG_PATH+"Launcher2|Motor1Speed", launcher2Motor1.getVelocityRPM());
    }

}

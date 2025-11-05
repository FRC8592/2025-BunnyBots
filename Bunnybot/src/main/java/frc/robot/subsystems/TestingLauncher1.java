package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;
import frc.robot.helpers.PIDProfile;
import frc.robot.helpers.Utils;
import frc.robot.helpers.motor.NewtonMotor;
import frc.robot.helpers.motor.NewtonMotor.IdleMode;
import frc.robot.helpers.motor.talonfx.KrakenX60Motor;
public class TestingLauncher1 extends SubsystemBase {
    //declaring motors used for launcher1
    private NewtonMotor launcher1motor1;
    private NewtonMotor launcher1motor2;
    private NewtonMotor launcher1pivotmotor;
    private NewtonMotor launcher1transportmotor;
    private double targetAngleDegrees = 0.0;
    
    public TestingLauncher1()
    {
        PIDProfile positionPid = new PIDProfile();
        // motors for the two 'launching' wheels
        // launcher1motor1 = new KrakenX60Motor(CAN.LAUNCHER1_MOTOR_CAN_ID_1, false);
        // launcher1motor2 = new KrakenX60Motor(CAN.LAUNCHER1_MOTOR_CAN_ID_2, false);

        //motor for transporting ball to the 'launching' wheels
        // launcher1transportmotor = new KrakenX60Motor(CAN.LAUNCHER1_TRANSPORT_MOTOR_CAN_ID_1, false);

        // motor for angle manipulation
        // launcher1pivotmotor = new KrakenX60Motor(CAN.LAUNCHER1_PIVOT_MOTOR_CAN_ID_1, false);


        launcher1pivotmotor.setIdleMode(IdleMode.kCoast);
        // launcher1pivotmotor.setPositionSoftLimit(degreesToMotorRotations(LAUNCHER.LAUNCHER_ANGLE_DEGREES_MIN), degreesToMotorRotations(LAUNCHER.LAUNCHER_ANGLE_DEGREES_MAX));
        launcher1pivotmotor.setCurrentLimit(ARM.ARM_CURRENT_LIMIT);

        launcher1pivotmotor.withGains(positionPid);

        // launcher1pivotmotor.configureMotionMagic(ARM.ARM_MAX_ACCELERATION, ARM.ARM_MAX_VELOCITY);
        
    }

    /** 
    * accepts desired speed as a percentage and sets the motor to that speed
    * @param percent desired speed as percentage
    */

    public void setLauncherPercentOutput(double percent)
    {
        launcher1motor1.setPercentOutput(percent);
        launcher1motor2.setPercentOutput(percent);

        SmartDashboard.putNumber("Launch Motor 1 Percent Power", percent);
        SmartDashboard.putNumber("Launch Motor 2 Percent Power", percent);
    }


    /**
     * accepts desired angle motor degrees and sets the target degrees for the angle to the desired degrees
     * @param degrees the degrees desired by the user
     */    
    public void setDegrees( double degrees) {
        targetAngleDegrees = degrees;
    }

    public double getDegrees() {
        return motorRotationsToDegrees(launcher1pivotmotor.getRotations());
    }

    /**
     * accepts degrees and converts it to rotations
     * @param degrees degrees of pivot motor
     * @return Returns converted degrees to rotations
     */
    public double degreesToMotorRotations(double degrees){
        return (degrees / (LAUNCHER.LAUNCHER1_PIVOT_GEAR_RATIO * 360));
    }

    /**
     * accepts rotations and converts it to degrees
     * @param rotations rotations of pivot motor
     * @return Returns converted rotations to degrees
     */
    public double motorRotationsToDegrees(double rotations){
        return (rotations * LAUNCHER.LAUNCHER1_PIVOT_GEAR_RATIO * 360);
    }


    /**
     * outputs whether the launcher is at its desired position
     * @return Returns if the pivot motor is in the desired position as a boolean
     */
    public boolean atPosition() {
        return Utils.isWithin(getDegrees(), targetAngleDegrees, LAUNCHER.PIVOT_MOTOR_POSITION_TOLERANCE);
    }

    /**
     * accepts desired speed as a percentage and sets motor to that speed
     * @param percent desired speed as a percentage
     */

    public void setTransportPercentOutput(double percent){
        launcher1transportmotor.setPercentOutput(percent);

        SmartDashboard.putNumber("Transport Motor Percent Power", percent);
    }

    /**
     * stops transport motor by setting output percentage to 0
     */

     public void stopTransport(){
        setTransportPercentOutput(0);
     }

    /**
     * stops the launcher motor by setting output percentage to 0
     */

    public void stopLauncher(){
        setLauncherPercentOutput(0);
    }

    /**
     * accepts the desired power of the motor and sets the motor to that power
     * @param percent desired percentage of the launcher and transport motors
     * @return returns a command to set launcher and transport motor power to given percentage
     */
    public Command setLauncherCommand(double percent)
    {
        return this.run(()->
        {
            setTransportPercentOutput(percent);
            setLauncherPercentOutput(percent);
        });
    }

    /**
     * stops the transport and launcher motors by setting percent output to 0
     * @return retruns a command to stop the transport and launcher motors
     */
    public Command stopLauncherCommand()
    {
        return this.runOnce(()->
        {
            setLauncherPercentOutput(0);
            setTransportPercentOutput(0);
        });
    }
}

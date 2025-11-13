package frc.robot.subsystems;




import com.revrobotics.spark.SparkClosedLoopController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;   
import frc.robot.Constants.*;
import frc.robot.helpers.motor.NewtonMotor;
import frc.robot.helpers.motor.NewtonMotor.IdleMode;
import frc.robot.helpers.motor.spark.SparkFlexMotor;
import frc.robot.helpers.motor.talonfx.KrakenX60Motor;
import frc.robot.helpers.PIDProfile;




public class TestingIntake1 extends SubsystemBase{
   private NewtonMotor IntakeMotor;
   //This needs to be configured as a Kraken Motor in order to utilize MotionMagic, found in the TalonFX Class
   private KrakenX60Motor PivotIntakeMotor;
   //If neos are used, this is necessary for PID Control
   //private SparkClosedLoopController PivotIntakeControl;
   //private SparkFlexConfig MotorConfig;


   public TestingIntake1(){
        /*
            Does this approach work with any NewtonMotor or only with Krakens? 
            Since SparkFlex extends NewtonMotors and NewtonMotors utilizes PIDProfiles, I think it does?
            Cannot find any previous implementation of PID and Motion Magic with NEO Motors
        */
        PIDProfile PositionPID = new PIDProfile();
        PositionPID.setPID(INTAKE.INTAKE_POSITION_P, INTAKE.INTAKE_POSITION_I, INTAKE.INTAKE_POSITION_D);
       //Declaring both motors based on CAN ID from CanBus, and running them in the normal direction
       //These WILL be changed to Kraken motors later, but for prototyping purposes we are utilizing neo motors
       IntakeMotor = new KrakenX60Motor(CAN.INTAKE_MOTOR_CAN_ID,false);
       PivotIntakeMotor = new KrakenX60Motor(CAN.PIVOT_INTAKE_MOTOR_CAN_ID,false);


       //Setting the idle(normal/resting) state
       IntakeMotor.setIdleMode(IdleMode.kBrake);
       PivotIntakeMotor.setIdleMode(IdleMode.kBrake);
        //Setting current limits on the motors to prevent burn out and overheating

        IntakeMotor.setCurrentLimit(INTAKE.INTAKE_CURRENT_LIMIT);
        PivotIntakeMotor.setCurrentLimit(INTAKE.PIVOT_INTAKE_CURRENT_LIMIT);
       /*
        * Alternate option (if using neos) based on REVLib Website:

            IntakeMotor = new SparkFlexMotor(CAN.INTAKE_MOTOR_CAN_ID,false);
            PivotIntakeMotor = new SparkFlexMotor(CAN.PIVOT_INTAKE_MOTOR_CAN_ID,false);
            PivotIntakeControl = PivotIntakeMotor.getClosedLoopControl();
            PivotIntakeControl.setReference(setPoint, ControlType.kPosition);
            MotorConfig = new SparkFlexConfig()
            config.closedLoop
                .p(kP)
                .i(kI)
                .d(kD)
                .outputRange(kMinOutput, kMaxOutput);

            
        */


       PivotIntakeMotor.withGains(PositionPID);

       PivotIntakeMotor.configureMotionMagic(INTAKE.PIVOT_INTAKE_MAX_ACCELERATION, INTAKE.PIVOT_INTAKE_MAX_VELOCITY);


   }

   public void setPercentOut(NewtonMotor motor,double percent){
    motor.setPercentOutput(percent);
   }


   public void stop(NewtonMotor motor){
       motor.setVelocity(0);
   }


   public Command setIntakeCommand(NewtonMotor motor,double percent){
           return this.run(()->{setPercentOut(motor,percent);});
   } 
  
   public Command stopIntakeCommand(NewtonMotor motor){
       return this.runOnce(()->{stop(motor);});
   }

   public double RotationstoDegrees(double motorRotations){
    return motorRotations * 1/(INTAKE.INTAKE_DEGREES_TO_MOTOR_ROTATIONS);
   }

   public double DegreestoRotations(double degrees){
    return degrees * INTAKE.INTAKE_DEGREES_TO_MOTOR_ROTATIONS;
   }

   public NewtonMotor accessIntakeMotor(){
    return IntakeMotor;
   }

   public KrakenX60Motor accessPivotIntakeMotor(){
    return PivotIntakeMotor;
   }



   public boolean TestMotorRotations(){
    return IntakeMotor.getRotations() == 1000.0;
   }



}

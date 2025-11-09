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
   private NewtonMotor PivotIntakeMotor;
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


   }

   public void setPercentOut(NewtonMotor motor, double percent){
       motor.setPercentOutput(percent);
   }


   public void stop(NewtonMotor motor){
       motor.setVelocity(0);
   }


   public Command setIntakeCommand(NewtonMotor motor, double percent){
           return this.run(()->{setPercentOut(motor, percent);});
   } 
  
   public Command stopIntakeCommand(){
       return this.runOnce(()->{stop(PivotIntakeMotor);});
   }

   public double RotationstoDegrees(double motorRotations){
    return motorRotations * INTAKE.INTAKE_MOTOR_ROTATIONS_TO_DEGREES;
   }


}

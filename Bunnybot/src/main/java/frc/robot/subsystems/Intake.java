package frc.robot.subsystems;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

//import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import org.littletonrobotics.junction.Logger;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.SparkBase.*;
import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;   
import frc.robot.Constants.*;
import frc.robot.helpers.motor.NewtonMotor;
import frc.robot.helpers.motor.NewtonMotor.IdleMode;
import frc.robot.helpers.motor.spark.ThisIsSparkFlexMotor;
import frc.robot.helpers.PIDProfile;

public class Intake extends SubsystemBase{
   private SparkFlex IntakeMotorSide;
   private SparkFlex IntakeMotorBottom;
   //This needs to be configured as a Kraken Motor in order to utilize MotionMagic, found in the TalonFX Class
   private SparkFlex PivotIntakeMotor;
   private SparkBase PivotMotor;
   private SparkBaseConfig PivotIntakeMotorConfig;
   //If neos are used, this is necessary for PID Control
   //private SparkClosedLoopController PivotIntakeControl;
   //private SparkFlexConfig MotorConfig;
   private boolean IndexerIntake;
   private PIDProfile PositionPID;


   public Intake(){
        /*
            Does this approach work with any NewtonMotor or only with Krakens? 
            Since SparkFlex extends NewtonMotors and NewtonMotors utilizes PIDProfiles, I think it does?
            Cannot find any previous implementation of PID and Motion Magic with NEO Motors
        */
        //NEED to change this implementation
        IndexerIntake = false;
        PositionPID = new PIDProfile();
        PositionPID.setPID(INTAKE.INTAKE_POSITION_P, INTAKE.INTAKE_POSITION_I, INTAKE.INTAKE_POSITION_D);
       //Declaring both motors based on CAN ID from CanBus, and running them in the normal direction
       //These WILL be changed to Kraken motors later, but for prototyping purposes we are utilizing neo motors
       IntakeMotorSide = new SparkFlex(CAN.INTAKE_MOTOR_SIDE_CAN_ID,MotorType.kBrushless);
       PivotMotor = new SparkFlex(CAN.PIVOT_INTAKE_MOTOR_CAN_ID, MotorType.kBrushless);
       IntakeMotorBottom = new SparkFlex(CAN.INTAKE_MOTOR_BOTTOM_CAN_ID,MotorType.kBrushless);
       //SparkFlexConfig config = new SparkFlexConfig().closedLoop.pid(INTAKE.INTAKE_POSITION_P, INTAKE.INTAKE_POSITION_I, INTAKE.INTAKE_POSITION_D); 
    //62.832 motor rotations, fully deployed
    //Biggest force fighting, highest constant, 41.284
    // Where we start fighting gravity, 14.205

    // Create a new ArmFeedforward with gains kS, kG, kV, and kA
    SmartDashboard.putNumber("Pivot_Motor_Intake_Voltage", 0);


       //Setting the idle(normal/resting) state
       //IntakeMotorSide.setIdleMode(IdleMode.kBrake);
       ClosedLoopConfig config = new ClosedLoopConfig();
        config = new SparkFlexConfig().closedLoop.pid(5,0,0.1);
        PivotIntakeMotorConfig.apply(config);
       PivotIntakeMotor.configure(PivotIntakeMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
       //IntakeMotorBottom.setIdleMode(IdleMode.kBrake);
        //Setting current limits on the motors to prevent burn out and overheating

        // IntakeMotorSide.setCurrentLimit(INTAKE.INTAKE_CURRENT_LIMIT);
        // PivotIntakeMotor.setCurrentLimit(INTAKE.PIVOT_INTAKE_CURRENT_LIMIT);
        // IntakeMotorBottom.setCurrentLimit(INTAKE.INTAKE_CURRENT_LIMIT);

        // IntakeMotorBottom.setFollowerTo(IntakeMotorSide);

        // PivotIntakeMotor.withGains(PositionPID);
        //PivotIntakeMotor.configureMAXMotion(INTAKE.PIVOT_INTAKE_MAX_ACCELERATION, INTAKE.PIVOT_INTAKE_MAX_VELOCITY, INTAKE.PIVOT_INTAKE_TOLERANCE, PositionPID);
   }


   public void setPercentOut(NewtonMotor motor,double percent){
    motor.setPercentOutput(percent);
   }


   public void stop(NewtonMotor motor){
       motor.setPercentOutput(0);
   }

//    public ThisIsSparkFlexMotor getPivotMotor(){
//     return PivotIntakeMotor;
//    }


    // public Command setIntakeSideCommand(double percent){
    //     return this.run(() -> setPercentOut(IntakeMotorSide, percent));
    // }
    
    // public Command setIntakeBottomCommand(double percent){
    //     return this.run(() -> setPercentOut(IntakeMotorBottom, percent));
    // }

  
//    public Command stopIntakeSideCommand(){
//     return this.runOnce(() -> stop(IntakeMotorSide));
//    }

//    public Command stopIntakeBottomCommand(){
//     return this.runOnce(() -> stop(IntakeMotorBottom));
//    }

//    public Command stopPivotCommand(){
//     return this.runOnce(() -> stop(PivotIntakeMotor));
//    }

   public double rotationstoDegrees(double motorRotations){
    return motorRotations * 1/(INTAKE.INTAKE_DEGREES_TO_MOTOR_ROTATIONS);
   }

   public double degreestoRotations(double degrees){
    return degrees * INTAKE.INTAKE_DEGREES_TO_MOTOR_ROTATIONS;
   }

   //This is for the intake motor, runs the intake motor to a certain number of motor rotations
   public void runIntakeToPosition(double desiredPosition){
    double speed = 0;
    double currentPosition =  PivotIntakeMotor.getEncoder().getPosition();
    if(currentPosition < desiredPosition){
        speed = 0.2;
    }
    else if(desiredPosition < currentPosition){
        speed = -0.2;
    }
    while(Math.abs(currentPosition - PivotIntakeMotor.getEncoder().getPosition()) > 0.1){
        PivotIntakeMotor.set(speed);
    }
    PivotIntakeMotor.set(0);
   }
   //Command version of the runIntakeToPosition method
   public Command runIntakeToPositionCommand(){
    return this.run(() -> runIntakeToPosition(-1000));
   }


   public Command setToPositionCommand(double position){
    return this.run(()-> runIntakeToPosition(position));
   }

//    public Command runIntakeOnVoltageCommand(){
//     return this.run(()-> PivotIntakeMotor.setVoltage(SmartDashboard.getNumber("Pivot_Motor_Intake_Voltage", 0),0));
//    }

    // public void deploy(double setPosition, PIDProfile gains){
    //         // ArmFeedforward feedforward = new ArmFeedforward(gains.kS, gains.kG, gains.kV, gains.kA);
    //         // this.motor.setVoltage(feedforward.calculate(10,5, 10));
    //     // TrapezoidProfile motionProfile =  new TrapezoidProfile(new TrapezoidProfile.Constraints(gains.getMaxAcceleration(), gains.getMaxVelocity()));
    //     // State ExtendSetPoint = motionProfile.calculate(5.0, new TrapezoidProfile.State(0,0), new TrapezoidProfile.State(10,0));
    //     PivotIntakeMotor.setReference(13, ControlType.kPosition);
    // }
    // public void stow(){
    //     PivotIntakeMotor.setReference(10, ControlType.kPosition);
    // }
 
   @Override
   public void periodic(){
    Logger.recordOutput(INTAKE.LOG_PATH + "Pivot Motor Rotations", PivotIntakeMotor.getEncoder().getPosition());
   }


}


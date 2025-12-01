package frc.robot.subsystems;

import java.util.Set;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.DeferredCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
//import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;   
import frc.robot.Constants.*;
import frc.robot.helpers.motor.NewtonMotor;
import frc.robot.helpers.motor.NewtonMotor.IdleMode;
import frc.robot.helpers.motor.spark.SparkFlexMotor;
import frc.robot.helpers.PIDProfile;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Scoring extends SubsystemBase{
    private Intake intake;
    private Indexer indexer;
    private Launcher launcher;
    private double intakePos;


    public Scoring(Intake intake, Indexer indexer, Launcher launcher){
        this.intake = intake;
        this.indexer = indexer;
        this.launcher = launcher;
    }

    public Command EjectLunite(){
    return new DeferredCommand(() -> EjectLuniteIntake(), Set.of(intake))
    .andThen(DefaultRunIntake())
    .andThen(StowIntake());
    }

    public Command IntakeLunite(){
    //This needs to be changed, methodology behind it is to rotate the indexer down, and run the intake until Indexer knows it has the ball
    return new DeferredCommand(() -> DeployIntake(), Set.of(intake))
    .andThen(intake.setIntakeSideCommand(0.7));
    }

    public Command StowIntake(){
        intakePos = INTAKE.STOW_PIVOT_INTAKE;
    return this.runOnce(() -> intake.setToPositionCommand(INTAKE.STOW_PIVOT_INTAKE));
    }

    public Command DeployIntake(){
        intakePos = INTAKE.SET_PIVOT_INTAKE;
    return this.runOnce(() -> intake.setToPositionCommand(INTAKE.SET_PIVOT_INTAKE));
    }

    public Command EjectLuniteIntake(){
        intakePos = INTAKE.EJECT_LUNITE_POSITION;
    return this.runOnce(() -> intake.setToPositionCommand(INTAKE.EJECT_LUNITE_POSITION));
    }

    public Command DefaultRunIntake(){
    return this.runOnce(() -> intake.runIntakeToPositionCommand());
    }

    public void periodic(){
        Logger.recordOutput(SCORING.LOG_PATH+ "Intake Position", intakePos);
    }
}

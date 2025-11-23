// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Set;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.DeferredCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.TestingIntake1;
import frc.robot.Constants.*;

public class ExampleSubsystem extends SubsystemBase {
  private TestingIntake1 testingIntake;
  private boolean IndexerIntake;
  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem() {
    testingIntake = new TestingIntake1();
    IndexerIntake = false;
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void TestingMethod(){
    if(IndexerIntake){
      new DeferredCommand(() -> testingIntake.setToPositionCommand(testingIntake.accessPivotIntakeMotor(), INTAKE.EJECT_LUNITE_POSITION), Set.of(testingIntake)).andThen(testingIntake.runIntakeToPositionCommand(testingIntake.accessIntakeMotor(), -1000)).andThen(testingIntake.setToPositionCommand(testingIntake.accessPivotIntakeMotor(), INTAKE.STOW_PIVOT_INTAKE));

      //Run the intake motor for a certain amount of time, or for a certain amount of motor rotations
      //Then run the pivot intake motor for a certain amount of motor rotations, and then hold it there and eject the thing
      
      //So the order is as follows: Stop the intake if it is not already stopped, run the pivot motor so the intake is held up,
      //run the intake motor in the opposite direction to eject the lunite, run the pivot motor again to fully store the intake.
      //Continue with normal operation.
   }
  }
 public Command EjectLunite(){
  return new DeferredCommand(() -> testingIntake.setToPositionCommand(testingIntake.accessPivotIntakeMotor(), INTAKE.EJECT_LUNITE_POSITION), Set.of(testingIntake)).andThen(testingIntake.runIntakeToPositionCommand(testingIntake.accessIntakeMotor(), -1000)).andThen(testingIntake.setToPositionCommand(testingIntake.accessPivotIntakeMotor(), INTAKE.STOW_PIVOT_INTAKE));
 }

 public Command IntakeLunite(){
  return new DeferredCommand();
 }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.nio.file.OpenOption;
import java.util.Set;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.swerve.CommandSwerveDrivetrain;
import frc.robot.subsystems.swerve.Swerve;
import frc.robot.subsystems.swerve.Telemetry;
import frc.robot.subsystems.swerve.TunerConstants;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.vision.Vision;
import frc.robot.Constants.*;
import frc.robot.commands.autonomous.AutoManager;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */

public class RobotContainer {
    private static final CommandXboxController driverController = new CommandXboxController(
        CONTROLLERS.DRIVER_PORT
    );
    private static final CommandGenericHID operatorController = new CommandXboxController(
        CONTROLLERS.OPERATOR_PORT
    );
    
    private final Telemetry logger = new Telemetry(SWERVE.MAX_SPEED);
    public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();

    // The robot's subsystems
    private final Swerve swerve;
    private final OdometryUpdates odometryUpdates;
    private final Vision vision;
    private final Indexer indexer;
    private final Intake intake;
    private final Launcher launcher;
    private final Scoring scoring;

    
    private final Trigger RESET_HEADING = driverController.back();
    // private final Trigger SLOW_MODE = driverController.rightBumper();
    private final Trigger LAUNCH = operatorController.button(1);
    //private Trigger RUN = operatorController.rightBumper();
    private final Trigger TESTINGINTAKEBOTTOMBUTTON = operatorController.button(2);

    //private final Trigger TESTINGINTAKEBUTTON = driverController.rightTrigger();
    private final Trigger TESTINGINTAKESIDEBUTTON = operatorController.button(3);
    

    /**
     * Create the robot container. This creates and configures subsystems, sets
     * up button bindings, and prepares for autonomous.
     */
    public RobotContainer() {
        swerve = new Swerve(drivetrain);
        vision = new Vision(VISION.CAMERA_NAME, VISION.CAMERA_OFFSETS);
        odometryUpdates = new OdometryUpdates(vision, swerve);
        launcher = new Launcher();
        indexer = new Indexer();
        intake = new Intake();

        configureBindings();
        configureDefaults();
        
        AutoManager.prepare();
        scoring  = new Scoring(intake,indexer,launcher);
    }

    /**
     * Configure default commands for the subsystems
     */
    private void configureDefaults(){
        // Set the swerve's default command to drive with joysticks

        setDefaultCommand(swerve, swerve.run(() -> {
            swerve.drive(swerve.processJoystickInputs(
                -driverController.getLeftX(),
                -driverController.getLeftY(),
                -driverController.getRightX()
            ));
        }).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

        setDefaultCommand(indexer, indexer.run(() -> indexer.autoIndexCommand()));
    }

    /**
     * Configure all button bindings
     */
    private void configureBindings() {
        // SLOW_MODE.onTrue(Commands.runOnce(() -> swerve.setSlowMode(true)).ignoringDisable(true))
                //  .onFalse(Commands.runOnce(() -> swerve.setSlowMode(false)).ignoringDisable(true));

        // Reset to (0,0) 
        RESET_HEADING.onTrue(swerve.runOnce(() -> swerve.resetHeading()));


        LAUNCH.whileTrue(new DeferredCommand(() -> launcher.setLauncherCommand(SmartDashboard.getNumber("bottom_launcher_motor", 0.4), SmartDashboard.getNumber("top_launcher_motor", 0.4)), Set.of(launcher))
        ).onFalse(launcher.stopLauncherCommand());


        TESTINGINTAKESIDEBUTTON.onTrue(new DeferredCommand(() -> intake.setIntakeSideCommand(0.75), Set.of(intake))).onFalse(intake.stopIntakeSideCommand());
        //TESTINGINTAKEBOTTOMBUTTON.onTrue(new DeferredCommand(() -> testingIntake.setIntakeBottomCommand(0.5), Set.of(testingIntake))).onFalse(testingIntake.stopIntakeBottomCommand());
        //TESTINGPIVOTINTAKEBUTTON.onTrue(new DeferredCommand(() ->testingIntake.setIntakeCommand(testingIntake.accessPivotIntakeMotor(),0.5), Set.of(testingIntake))).onFalse(testingIntake.stopIntakeCommand(testingIntake.accessPivotIntakeMotor()));
  
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return AutoManager.getAutonomousCommand();
    }

    /**
     * Set the default command of a subsystem (what to run if no other command requiring it is running).
     * <p> NOTE: all subsystems also have a setDefaultCommand method; this version includes a check for
     * default commands that cancel incoming commands that require the subsystem. Unless you're sure
     * of what you're doing, you should use this one.
     *
     * @param subsystem the subsystem to apply the default command to
     * @param command to command to set as default
     */
    private void setDefaultCommand(SubsystemBase subsystem, Command command){
        if(command.getInterruptionBehavior() == InterruptionBehavior.kCancelSelf){
            subsystem.setDefaultCommand(command);
        }
        else{
            //If you want to force-allow setting a cancel-incoming default command, directly call `subsystem.setDefaultCommand()` instead
            throw new UnsupportedOperationException("Can't set a default command that cancels incoming!");
        }
    }

}

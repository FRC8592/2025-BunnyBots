package frc.robot.commands.autonomous.autos;
//FINAL
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Suppliers;
import frc.robot.commands.autonomous.AutoCommand;
import frc.robot.commands.largecommands.*;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;


public class BlueNFShootHighMove extends AutoCommand {
    public BlueNFShootHighMove(Launcher launcher, Indexer indexer, Intake intake){
        super(
            launcher.setLauncherCommand(0.44, 0.30) //USE "CLOSE"
            .andThen(new WaitUntilCommand(0.5))
            .andThen(indexer.setMotorPercentOutputCommand(4, 1)).withTimeout(1.2)

            .andThen(new FollowPathCommand(getChoreoTrajectory("BlueNFMoveOut"), Suppliers.isRedAlliance, ""))
                .alongWith(launcher.stopLauncherCommand())
                .alongWith(indexer.stopMotorCommand(4))
                // .alongWith(intake.setToPositionCommand()) //if we need to raise the intake while driving

        );
    }
}




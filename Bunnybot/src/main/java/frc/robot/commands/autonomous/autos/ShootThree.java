package frc.robot.commands.autonomous.autos;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.commands.autonomous.AutoCommand;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Launcher;

public class ShootThree extends AutoCommand {
    public ShootThree(Launcher launcher, Indexer indexer){
        super(
            launcher.setLauncherCommand(0.44, 0.3)
            .andThen(new WaitUntilCommand(1))
            .andThen(new RunCommand(() -> indexer.shoot(1)))
        );
    }
}

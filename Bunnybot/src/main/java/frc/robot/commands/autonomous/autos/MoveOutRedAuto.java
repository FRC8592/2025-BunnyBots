package frc.robot.commands.autonomous.autos;

import java.util.Set;

import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Suppliers;
import frc.robot.commands.autonomous.AutoCommand;

public class MoveOutRedAuto extends AutoCommand {
    public MoveOutRedAuto(){
        super(
            (
                new FollowPathCommand(getChoreoTrajectory("RedOuterMove"), Suppliers.isRedAlliance, "")
            )         
        );

    } 
}

package frc.robot.commands.autonomous.autos;

import frc.robot.Suppliers;
import frc.robot.commands.autonomous.AutoCommand;

public class MoveOutBlueAuto extends AutoCommand {
    public MoveOutBlueAuto(){
        super(
            (
                new FollowPathCommand(getChoreoTrajectory("BlueOuterMove"), Suppliers.isRedAlliance, "")
            )         
        );

    } 
    
}

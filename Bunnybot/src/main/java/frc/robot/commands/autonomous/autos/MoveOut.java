package frc.robot.commands.autonomous.autos;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Suppliers;
import frc.robot.commands.autonomous.AutoCommand;
import frc.robot.commands.largecommands.*;

public class MoveOut extends AutoCommand {
    public MoveOut(String color){
        new FollowPathCommand(getChoreoTrajectory("MoveForward" + color), Suppliers.isRedAlliance, "");
    }
}

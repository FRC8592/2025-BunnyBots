package frc.robot.commands.autonomous.autos;

//FINAL
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Suppliers;
import frc.robot.commands.autonomous.AutoCommand;
import frc.robot.commands.largecommands.*;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.Indexer;



public class BlueMoveOut extends AutoCommand {
   public BlueMoveOut(){
         super(
            new FollowPathCommand(getChoreoTrajectory("BlueMoveOut"), Suppliers.isRedAlliance, "")
         );


   }


}




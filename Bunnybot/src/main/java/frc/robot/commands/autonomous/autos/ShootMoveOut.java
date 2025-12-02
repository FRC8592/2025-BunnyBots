package frc.robot.commands.autonomous.autos;


import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Suppliers;
import frc.robot.commands.autonomous.AutoCommand;
import frc.robot.commands.largecommands.*;


public class ShootMoveOut extends AutoCommand {
   public ShootMoveOut(String color){
      super(
        
       new FollowPathCommand(getChoreoTrajectory("CollectFirst"), Suppliers.isRedAlliance, ""),
       /*
        * .andThen(Intake Command until Indexer detects 1 Lumen in its system))
        * .andThen(Indexer Command until their conditional is satisfied)
       */

       new FollowPathCommand(getChoreoTrajectory("RotateFirst"), Suppliers.isRedAlliance, ""),
         /*
         * No need to do anything here
         */

        new FollowPathCommand(getChoreoTrajectory("MoveOutAfterLine"), Suppliers.isRedAlliance, ""));
        /*
 * Nothing really needed here
 *
 */



   }


}




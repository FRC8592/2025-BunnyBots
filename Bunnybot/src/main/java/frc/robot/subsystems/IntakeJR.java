package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;
import frc.robot.helpers.PIDProfile;
import frc.robot.helpers.motor.NewtonMotor;
import frc.robot.helpers.motor.NewtonMotor.IdleMode;
import frc.robot.helpers.motor.talonfx.KrakenX60Motor;

public class IntakeJR extends SubsystemBase{

    private NewtonMotor intake2Motor1; 
    private NewtonMotor intake2Motor2; 
    //private LaserCan intake2Sensor;

    public IntakeJR() {
        //Instantiating motors
        //The 'true' on intake2Motor2 makes it inverted
        intake2Motor1 = new KrakenX60Motor(CAN.INTAKE2_MOTOR_CAN_ID_1, false);
        intake2Motor2 = new KrakenX60Motor(CAN.INTAKE2_MOTOR_CAN_ID_2, true);

        //intake2Sensor = new LaserCan(CAN.INTAKE2_BEAM_BREAK_CAN_ID);
    }

    /**
     * Starts the intake with power 100
     */
    public void startIntake(){
        intake2Motor1.setPercentOutput(100);
        intake2Motor2.setPercentOutput(100);
    }

    /**
     * Stops the intake by setting its power to 0
     */
    public void stopIntake(){
        intake2Motor1.setPercentOutput(0);
        intake2Motor2.setPercentOutput(0);
    }

    /**
     * Starts the intake with power 100
     * @return returns a command to start the intake
     */
    public Command startIntakeCommand(){
        return this.run(()->{
            startIntake();
        });
    }

    /**
     * Stops the intake by setting its power to 0
     * @return returns a command to stop the intake
     */
    public Command stopIntakeCommand(){
        return this.runOnce(()->{
            stopIntake();
        });
    }
    
}

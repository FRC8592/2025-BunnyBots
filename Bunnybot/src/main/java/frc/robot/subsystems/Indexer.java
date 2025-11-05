package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CAN;
import frc.robot.helpers.motor.NewtonMotor;
import frc.robot.helpers.motor.NewtonMotor.IdleMode;
import frc.robot.helpers.motor.talonfx.KrakenX60Motor;

public class Indexer extends SubsystemBase {
    
    private NewtonMotor indexerMotor;
    
    public Indexer() {
        // indexerMotor = new KrakenX60Motor(CAN.INDEXER_MOTOR_CAN_ID, false);
        indexerMotor.setIdleMode(IdleMode.kBrake);
    }

    /**
     * Sets the percentage for the indexer motor
     * @param percent
     */
    public void setIndexerPercentOutput(double percent) {
        indexerMotor.setPercentOutput(percent);
    }

    /** 
     * Stops the indexer motor
    */
    public void stop() {
        indexerMotor.setPercentOutput(0);
    }

    /**
     * Sets the percentage of the indexer motor to what's passed in
     * @param percent
     * @return the command to set the indexer output
     */
    public Command setIndexerPercentOutputCommand(double percent) {
        return this.run(()->{
            setIndexerPercentOutput(0);
        });
    }
/**
 * Stops the motor from running
 * @return the command to stop the motor
 */
    public Command stopIndexerCommand() {
        return this.runOnce(()-> {
            stop();
        });
    }
}

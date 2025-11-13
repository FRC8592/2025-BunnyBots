package frc.robot.subsystems;

import java.util.Set;

import au.grapplerobotics.ConfigurationFailedException;
import au.grapplerobotics.LaserCan;
import au.grapplerobotics.interfaces.LaserCanInterface.Measurement;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.DeferredCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CAN;
import frc.robot.Constants.INDEXER;
import frc.robot.helpers.motor.NewtonMotor;
import frc.robot.helpers.motor.NewtonMotor.IdleMode;
import frc.robot.helpers.motor.talonfx.KrakenX60Motor;

public class Indexer extends SubsystemBase {
    
    private NewtonMotor indexerMotor1, indexerMotor2, indexerTopMotor;
    LaserCan[] laserCan = new LaserCan[3];
    
    public Indexer() {
        indexerMotor1 = new KrakenX60Motor(CAN.INDEXER_MOTOR1_CAN_ID, false);
        indexerMotor1.setIdleMode(IdleMode.kBrake);

        indexerMotor2 = new KrakenX60Motor(CAN.INDEXER_MOTOR2_CAN_ID, false);
        indexerMotor2.setIdleMode(IdleMode.kBrake);
        indexerMotor2.setFollowerTo(indexerMotor1);

        indexerTopMotor = new KrakenX60Motor(CAN.INDEXER_TOP_MOTOR_CAN_ID, false);
        indexerTopMotor.setIdleMode(IdleMode.kBrake);
        indexerTopMotor.setFollowerTo(indexerMotor1);

        laserCan[0] = new LaserCan(CAN.INDEXER_BEAM_BREAK_FRONT_CAN_ID);
        laserCan[1] = new LaserCan(CAN.INDEXER_BEAM_BREAK_MIDDLE_CAN_ID);
        laserCan[2] = new LaserCan(CAN.INDEXER_BEAM_BREAK_BACK_CAN_ID);

        for(LaserCan sensor : laserCan){
            try {
                sensor.setRangingMode(LaserCan.RangingMode.SHORT); 
                sensor.setRegionOfInterest(new LaserCan.RegionOfInterest(8, 8, 16, 16));
                sensor.setTimingBudget(LaserCan.TimingBudget.TIMING_BUDGET_20MS);
            } catch (ConfigurationFailedException e) {
                System.err.println("Configuration failed! " + e);
            }
        }
        
    }

    /**
     * Checks to see if the beambreak detects a football at the given storage point
     * @param storagePoint given launcher is the front, the front storage point is 1, the middle is 2, and the back is 3 
     * @return whewther a football is detected at given storage point as a boolean
     */
    public boolean indexerHasBall(int storagePoint){
        Measurement measurement = laserCan[storagePoint - 1].getMeasurement();
        if (measurement != null && measurement.status == LaserCan.LASERCAN_STATUS_VALID_MEASUREMENT) {
            return measurement.distance_mm < INDEXER.INDEXER_BEAM_BREAK_THRESHOLD_MM;
        } else {
            return false;
        }
    }

    /**
     * Sets the percentage for the indexer motor
     * @param percent
     */
    public void setIndexerPercentOutput(double percent) {
         indexerMotor1.setPercentOutput(percent);
         indexerTopMotor.setPercentOutput(percent);
    }

    /** 
     * Stops the indexer motor
    */
    public void stop() {
         indexerMotor1.setPercentOutput(0);
         indexerTopMotor.setPercentOutput(0);
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
     * 
     * @param percent
     * @return
     */
    public Command intakeCommand(double percent) {
        return this.run(() -> setIndexerPercentOutput(percent));
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

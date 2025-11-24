package frc.robot.subsystems;

import au.grapplerobotics.ConfigurationFailedException;
import au.grapplerobotics.LaserCan;
import au.grapplerobotics.interfaces.LaserCanInterface.Measurement;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CAN;
import frc.robot.Constants.INDEXER;
import frc.robot.helpers.motor.NewtonMotor;
import frc.robot.helpers.motor.NewtonMotor.IdleMode;
import frc.robot.helpers.motor.spark.SparkBrushedMotor;

public class Indexer extends SubsystemBase {
    
    private NewtonMotor indexerMotor1, indexerMotor2, indexerMotor3, indexerMotor4;
    LaserCan[] laserCan = new LaserCan[3];
    NewtonMotor[] motors = new NewtonMotor[4];
    
    public Indexer() {
        motors[0] = new SparkBrushedMotor(CAN.INDEXER_MOTOR1_CAN_ID, false);
        motors[1] = new SparkBrushedMotor(CAN.INDEXER_MOTOR2_CAN_ID, false);
        motors[2] = new SparkBrushedMotor(CAN.INDEXER_MOTOR3_CAN_ID, false);
        motors[3] = new SparkBrushedMotor(CAN.INDEXER_MOTOR4_CAN_ID, false);

        indexerMotor1.setIdleMode(IdleMode.kBrake);

        indexerMotor2.setIdleMode(IdleMode.kBrake);
        indexerMotor2.setFollowerTo(indexerMotor1);

        indexerMotor3.setIdleMode(IdleMode.kBrake);
        indexerMotor4.setIdleMode(IdleMode.kBrake);

        laserCan[0] = new LaserCan(CAN.INDEXER_BEAM_BREAK_1_CAN_ID);
        laserCan[1] = new LaserCan(CAN.INDEXER_BEAM_BREAK_2_CAN_ID);
        laserCan[2] = new LaserCan(CAN.INDEXER_BEAM_BREAK_3_CAN_ID);

        for(LaserCan sensor : laserCan){
            try {
                sensor.setRangingMode(LaserCan.RangingMode.SHORT); 
                sensor.setRegionOfInterest(new LaserCan.RegionOfInterest(8, 8, 16, 16)); //check this value
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
    public boolean hasBall(int storagePoint){
        Measurement measurement = laserCan[storagePoint - 1].getMeasurement();
        if (measurement != null && measurement.status == LaserCan.LASERCAN_STATUS_VALID_MEASUREMENT) {
            return measurement.distance_mm < INDEXER.INDEXER_BEAM_BREAK_THRESHOLD_MM;
        } else {
            return false;
        }
    }

    /**
     * Checks to see if there is a football anywhere within the indexer
     * @return if there is no football detected anywhere in the indexer
     */
    public boolean hasBall(){
        for(int i = 0; i < laserCan.length; i++){
            if(hasBall(i))
                return true;
        }
        return false;
        
    }

    /**
     * Finds the number of balls in the indexer
     * @return number of balls in the indexer
     */
    public int getBallCount(){
        int count = 0;
        for(int i = 0; i < laserCan.length; i++){
            if(hasBall(i))
                count++;
        }

        return count;
    }

    public void setMotorPercentOutput(int motorPos, double percent){
        motors[motorPos].setPercentOutput(percent);
        
    }

    public void stop(int motorPos){
        motors[motorPos].setPercentOutput(0);
    }

    /**
     * Sets the percentage of all indexer motors
     * @param percent
     * @return the command to set the indexer output
     */
    public Command setMotorPercentOutputCommand(double percent) {
        return this.run(()->{
            setMotorPercentOutput(1, percent);
            setMotorPercentOutput(2, percent);
            setMotorPercentOutput(3, percent);
            setMotorPercentOutput(4, percent);
        });
    }

    public Command setMotorPercentOutputCommand(int motorPos, double percent){
        return this.run(()->{
            setMotorPercentOutput(motorPos, percent);
        });
    }

    /**
     * Stops all motors from running
     * @return the command to stop the motor
     */
    public Command stopMotorCommand() {
        return this.runOnce(()-> {
            stop(1);
            stop(2);
            stop(3);
            stop(4);
        });
    }

    public Command stopMotorCommand(int motorPos){
        return this.runOnce(()-> {
            stop(motorPos);
        });
    }
}

package frc.robot.helpers.motor.spark;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;

import frc.robot.helpers.motor.MotorConstants;

public class SparkBrushedMotor extends SparkBaseMotor<SparkFlex, SparkFlexConfig> {
    public SparkBrushedMotor(int motorID) {
        this(motorID, false);
    }

    public SparkBrushedMotor(int motorID, boolean inverted) {
        super(
            new SparkFlex(motorID, MotorType.kBrushed), 
            new SparkFlexConfig(),
            inverted,
            new MotorConstants(
                6784d, 
                211d, 
                3.6, 
                575.1
            )
        );
    }
}
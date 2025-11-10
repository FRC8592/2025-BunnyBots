package frc.robot.helpers.motor.talonfx;

import frc.robot.helpers.motor.MotorConstants;

public class KrakenX60Motor extends TalonFXMotor {
    //Calls the constructor below, this is for simplicity's sake if we don't want to utilize inverted
    public KrakenX60Motor(int motorID) {
        this(motorID, false);
    }
    /*
     * Main constructor, this calls the super class constructor(the TalonFX constructor)
     * Main advantage of this class is the MotorConstants are going to be the same for KrakenMotors,
     * so for streamlining purposes
     */

    public KrakenX60Motor(int motorID, boolean inverted) {
        super(motorID, inverted, new MotorConstants(
            6000d, 
            366d, 
            7.09,
            502.1
        ));
    }
}
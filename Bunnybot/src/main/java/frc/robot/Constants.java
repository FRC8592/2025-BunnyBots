// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;

  }

  public static class INTAKE{
    //DEFAULT VALUES, NEED TO CHANGE THESE LATER
    //these are for the PID of the Pivot Intake Motor
    public static final int INTAKE_POSITION_P = 0;
    public static final int INTAKE_POSITION_I = 0;
    public static final int INTAKE_POSITION_D = 0;
    //This is the gearbox that the motor will be plugged into
    public static final int GEARBOX_PIVOT_RATIO = 16;
    //this is to convert motor rotations to intake degrees
    public static final int INTAKE_DEGREES_TO_MOTOR_ROTATIONS = GEARBOX_PIVOT_RATIO/360;
    //This is to make sure that it stays within position, will need to change later
    public static final double WITHIN_POSITION_LIMITS = 5;
    //Set current limits of the Kraken so they do not overheat, default value of 40 for current limits for now
    public static final int INTAKE_CURRENT_LIMIT = 40;
    public static final int PIVOT_INTAKE_CURRENT_LIMIT = 40;
    //Set max velocity and max acceleration of Intake motor of Intake for motion magic set up
    public static final int PIVOT_INTAKE_MAX_VELOCITY = 0;
    public static final int PIVOT_INTAKE_MAX_ACCELERATION = 0;
    //This is the position of the intake when we want to eject additional lunites
    public static final double EJECT_LUNITE_POSITION = 0;
    //This is the position of the intake when it is touching the ground
    public static final double SET_PIVOT_INTAKE = 0;
    //This should be 0 as the absolute encoder is intialized at 0 of stow, but maybe not for match setup so leave this here
    public static final double STOW_PIVOT_INTAKE = 0;
    //Amount of motor rotations to eject
    public static final int EJECT_MOTOR_ROTATIONS = 1000;

    

  }

  public static class CAN{
    public static final int INTAKE_MOTOR_CAN_ID = 0;
    public static final int PIVOT_INTAKE_MOTOR_CAN_ID = 0;
  }
}

package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import frc.robot.subsystems.swerve.TunerConstants;
import static edu.wpi.first.units.Units.*;


public final class Constants {
    public final class SHARED {
        public static final String LOG_FOLDER = "CustomLogs";
    }

    public final class MEASUREMENTS {
        public static final double FIELD_LENGTH_METERS = 27 * MEASUREMENTS.FEET_TO_METERS;
        public static final double FIELD_WIDTH_METERS = 54 * MEASUREMENTS.FEET_TO_METERS;
        public static final double INCHES_TO_METERS = 0.0254;
        public static final double FEET_TO_METERS = 0.30478512648;
    }

    public final class CONTROLLERS {
        public static final int DRIVER_PORT = 0;
        public static final int OPERATOR_PORT = 1;
        public static final int CORAL_SELECTOR_PORT = 2;
    }

    public final class CAN {
        public static final int PDH_CAN_ID = 1;

    }

    public final class VISION {
        public static final String LOG_PATH = SHARED.LOG_FOLDER+"Vision";
        public static final Transform3d CAMERA_OFFSETS = (
            new Transform3d(new Translation3d(0.304, 0.008, 0.127), new Rotation3d(0, Math.toRadians(45), 0)));
        public static final String CAMERA_NAME = (
            "Arducam_OV9782_B" 
        );
        public static final double MAX_ACCEPTABLE_AMBIGUITY = 0.1;
        public static final double REJECT_SINGLE_TAG_POSE_ESTIMATE_RANGE = 1d;
    }
    
    public final class INTAKE {
        public static final String LOG_PATH = SHARED.LOG_FOLDER+"/Intake/";
    }

    
    public final class SWERVE {
        public static final String LOG_PATH = SHARED.LOG_FOLDER+"/Swerve/";

        //TODO: Double check that these PID constants still work
        public static final double SNAP_TO_kP = 3.7;
        public static final double SNAP_TO_kI = 0.0;
        public static final double SNAP_TO_kD = 0.1;

        public static final double MAX_TRANSLATIONAL_VELOCITY_METERS_PER_SECOND = 4.73;
        public static final TrajectoryConfig PATH_FOLLOW_TRAJECTORY_CONFIG = new TrajectoryConfig(4.5, 3);
        public static final double MAX_ROTATIONAL_VELOCITY_RADIANS_PER_SECOND = Math.toRadians(720);

        public static final boolean INVERT_LEFT_SIDE = false;
        public static final boolean INVERT_RIGHT_SIDE = true;

        public static final double SIMULATED_STEER_INERTIA = 0.00001;
        public static final double SIMULATED_DRIVE_INERTIA = 0.06;
        public static final double SIMULATION_LOOP_PERIOD = 0.005;
        public static final double STEER_FRICTION_VOLTAGE = 0.25;
        public static final double DRIVE_FRICTION_VOLTAGE = 0.25;

        //TODO: Tone these down appropriately as per BB rules
        public static final double TRANSLATE_POWER_FAST = 1.0; 
        public static final double ROTATE_POWER_FAST = 0.75; 
        public static final double TRANSLATE_POWER_SLOW = 0.5;
        public static final double ROTATE_POWER_SLOW = 0.3;

        public static final int TRANSLATION_SMOOTHING_AMOUNT = 3;
        public static final int ROTATION_SMOOTHING_AMOUNT = 1;

        public static final double JOYSTICK_EXPONENT = 1.2;

        public static final Rotation2d BLUE_PERSPECTIVE_ROTATION = Rotation2d.fromDegrees(0);
        public static final Rotation2d RED_PERSPECTIVE_ROTATION = Rotation2d.fromDegrees(180);

        public static final double PATH_FOLLOW_TRANSLATE_kP = 8d; // Was 8 in the last test
        public static final double PATH_FOLLOW_TRANSLATE_kI = 0d;
        public static final double PATH_FOLLOW_TRANSLATE_kD = 0d;

        //TODO: Double check that these still work
        public static final double PATH_FOLLOW_ROTATE_kP = 12;
        public static final double PATH_FOLLOW_ROTATE_kI = 0d;
        public static final double PATH_FOLLOW_ROTATE_kD = 0;

        public static final double PATH_FOLLOW_ROTATE_MAX_VELOCITY = 4 * Math.PI;
        public static final double PATH_FOLLOW_ROTATE_MAX_ACCELLERATION = 4 * Math.PI;

        public static final double PATH_FOLLOW_TRANSLATE_POSITION_TOLERANCE = 0.01; // Meters
        public static final double PATH_FOLLOW_TRANSLATE_VELOCITY_TOLERANCE = 0.02;

        public static final double PATH_FOLLOW_ROTATE_POSITION_TOLERANCE = 0.05; // Radians
        public static final double PATH_FOLLOW_ROTATE_VELOCITY_TOLERANCE = 0.03;

        public static final double MAX_SPEED = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond);
        public static final double MAX_ANGULAR_RATE = RotationsPerSecond.of(0.75).in(RadiansPerSecond);
    }

    public final class ROBOT {
        public static final String LOG_PATH = SHARED.LOG_FOLDER + "/Robot/";
    }

    public class SUPPLIERS{
        public static final String LOG_PATH = SHARED.LOG_FOLDER+"/Suppliers/";
    }

}
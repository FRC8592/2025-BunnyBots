package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.util.Color;

public final class Constants {
    public final class SHARED {
        public static final String LOG_FOLDER = "CustomLogs";
    }

    public final class MEASUREMENTS {
        public static final double FIELD_LENGTH_METERS = 17.548;
        public static final double FIELD_WIDTH_METERS = 8.052;
    }
    public final class CONVERSIONS {
        public static final double METERS_TO_FEET = 3.28084;
        public static final double FEET_TO_METERS = 0.3048;
    }

    public final class CONTROLLERS {
        public static final int DRIVER_PORT = 0;
        public static final int OPERATOR_PORT = 1;
    }

    public final class CAN {
        public static final int INDEXER_MOTOR1_CAN_ID = 0;
        public static final int INDEXER_MOTOR2_CAN_ID = 0;
        public static final int INDEXER_TOP_MOTOR_CAN_ID = 0;
        public static final int INDEXER_BEAM_BREAK_FRONT_CAN_ID = 0;
        public static final int INDEXER_BEAM_BREAK_MIDDLE_CAN_ID = 0;
        public static final int INDEXER_BEAM_BREAK_BACK_CAN_ID = 0;
    }

    public final class CORAL_ALIGN {
        public static final String LOG_PATH = SHARED.LOG_FOLDER+"ScoreCoral";
        public static final double OFFSET_DEPTH = 0.40; // Drivers requested for the robot to be as close to the april tag as possible
        public static final double OFFSET_LEFT_METERS = -0.137;
        public static final double OFFSET_RIGHT_METERS = 0.213; 
        public static final double ROT_OFFSET = 0d;
        public static final double SPEED_SCALE = 1.0;
        public static final double SPEED_MAX = 0.2; // originally 0.65

        public static final int MAX_LOCK_LOSS_TICKS = 20;

        public static final Transform3d CAMERA_OFFSETS = (
            new Transform3d(new Translation3d(0.17145, 0.20955, 0.2286), new Rotation3d(0, Math.toRadians(-13), Math.toRadians(-3)))
        );


        public static final String CAMERA_NAME = (
            "Arducam_OV9782_D" 
        );
        public static final String CAMERA_2_NAME = (
            "Arducam_OV9782_B"
        );
    
        public static final int[] BLUE_REEF_TAGS = {17, 18, 19, 20, 21, 22};
        public static final int[] RED_REEF_TAGS = {6, 7, 8, 9, 10, 11};

        public static final int[] BLUE_HPLAYER_TAGS = {12, 13};
        public static final int[] RED_HPLAYER_TAGS = {1, 2};

        public static final double REJECT_SINGLE_TAG_POSE_ESTIMATE_RANGE = 1d;

    }

    public final class NAVIGATION {
        public static final double MAX_ACCEPTABLE_AMBIGUITY = 0.1;
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
    }

    public final class ROBOT {
        public static final String LOG_PATH = SHARED.LOG_FOLDER + "/Robot/";
    }

    public class SUPPLIERS{
        public static final String LOG_PATH = SHARED.LOG_FOLDER+"/Suppliers/";
    }

    public final class INDEXER {
        public static final int INDEXER_BEAM_BREAK_THRESHOLD_MM = 20; //subject to change
    }
}

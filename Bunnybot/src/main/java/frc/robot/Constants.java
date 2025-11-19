package frc.robot;

import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;

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

    public final class NAVIGATION {
        public static final String LOG_PATH = SHARED.LOG_FOLDER+"Navigation";

        public static final Transform3d CAMERA_OFFSETS = (
           new Transform3d(new Translation3d(0.17145, 0.20955, 0.2286), new Rotation3d(0, Math.toRadians(-13), Math.toRadians(-3)))
        );

        public static final String CAMERA_NAME = (
            "Arducam_OV9782_B" 
        );

        public static final double REJECT_SINGLE_TAG_POSE_ESTIMATE_RANGE = 1d;

        public static final double MAX_ACCEPTABLE_AMBIGUITY = 0.1;
    }
}

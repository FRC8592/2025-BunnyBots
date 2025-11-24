package frc.robot;

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
    }

    public final class CAN {
        public static final int INDEXER_MOTOR1_CAN_ID = 40;
        public static final int INDEXER_MOTOR2_CAN_ID = 41;
        public static final int INDEXER_MOTOR3_CAN_ID = 42;
        public static final int INDEXER_MOTOR4_CAN_ID = 43;

        public static final int INDEXER_BEAM_BREAK_1_CAN_ID = 0;
        public static final int INDEXER_BEAM_BREAK_2_CAN_ID = 0;
        public static final int INDEXER_BEAM_BREAK_3_CAN_ID = 0;
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

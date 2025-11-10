// package frc.robot.subsystems;
// import frc.robot.Constants.*;

// import org.ejml.equation.IntegerSequence.Range;
// import org.littletonrobotics.junction.Logger;

// import com.google.flatbuffers.Constants;

// public class RangeTable {
//     public static final RangeEntry[] RANGE_TABLES = {
//         new RangeEntry(0, 0, 0),
//         new RangeEntry(0, 0, 0),
//         new RangeEntry(0, 0, 0),
//         new RangeEntry(0, 0, 0),
//         new RangeEntry(0, 0, 0),
//         new RangeEntry(0, 0, 0),
//         new RangeEntry(0, 0, 0), 
//     };
//     public static boolean valid;


//     /**
//     // * Returns a RangeEntry representing the flywheel speed and pivot angle that
//     // * should be used for the inputted distance
//     // * @param distance the distance to the target IN INCHES
//     // * @return a RangeEntry with flywheel speed in RPM and pivot angle in degrees.
//     // * If the distance is too far, returns {@code null}; MAKE SURE TO CHECK FOR THIS
//     // 
//     */
//     public static RangeEntry get(double distance) {
//         distance*=5;
//         Logger.recordOutput("CustomLogs/RangeTable/InputDistance", distance);
//         //TODO: Uncomment when we get a real range table
//         valid = true;
//         int wholeMeters = (int)(distance);
//         if(distance <= 0){
//             valid=false;
//             return new RangeEntry(0,0,0);
//         }
//         double decimal = distance - Math.floor(distance);
//         if (RANGE_TABLES.length > 0) {
//             if (wholeMeters < RANGE_TABLES.length) {
//                 if (wholeMeters < RANGE_TABLES.length - 1) {
//                     RangeEntry entry = RANGE_TABLES[wholeMeters].interpolate(RANGE_TABLES[wholeMeters + 1], decimal);
//                     entry.pivotAngle += RANGE_TABLE.OFFSET_ANGLE;
//                     entry.pivotAngle = Math.max(entry.pivotAngle, 0);
//                     return entry;
//                 } else {
//                     valid = false;
//                     RangeEntry entry = RANGE_TABLES[RANGE_TABLES.length - 1];
//                     entry.pivotAngle += RANGE_TABLE.OFFSET_ANGLE;
//                     entry.pivotAngle = Math.max(entry.pivotAngle, 0);
//                     return entry;
//                 }
//             } else { // Too far for the range table
//                 valid = false;
//                 RangeEntry entry = RANGE_TABLES[RANGE_TABLES.length - 1];
//                 entry.pivotAngle += RANGE_TABLE.OFFSET_ANGLE;
//                 entry.pivotAngle = Math.max(entry.pivotAngle, 0);
//                     return entry;
//             }
//         } else {
//             valid = false;
//             System.out.println("ERROR: Range table is empty");
//             return new RangeEntry(0, 0, 0);
//         }
//     }
//     public static class RangeEntry {
//         public int leftFlywheelSpeed;
//         public int rightFlywheelSpeed;
//         public double pivotAngle;
//         public double elevatorHeight;

//         public RangeEntry(int left, int right, double angle) {
//             leftFlywheelSpeed = left;
//             rightFlywheelSpeed = right;
//             pivotAngle = angle;
//             elevatorHeight = 0;
//         }

//         /**
//          * Interpolates between this RangeEntry and another one. Assumes that this is the smaller of the two.
//          * @param rEntry the range table entry to interpolate between
//          * @param value
//          * @return
//          */
//         public RangeEntry interpolate(RangeEntry rEntry, double value) {
//             double leftSpeedUnit = rEntry.leftFlywheelSpeed - this.leftFlywheelSpeed;
//             double rightSpeedUnit = rEntry.leftFlywheelSpeed - this.leftFlywheelSpeed;
//             double angleUnit = rEntry.pivotAngle - this.pivotAngle;
//             RangeEntry generated = new RangeEntry((int) (rEntry.leftFlywheelSpeed),
//                     (int) (rEntry.rightFlywheelSpeed),
//                     (this.pivotAngle + (angleUnit * value)));
//             Logger.recordOutput("CustomLogs/RangeTable/GeneratedPivotAngle", generated.pivotAngle);
//             Logger.recordOutput("CustomLogs/RangeTable/GeneratedFlywheelSpeed", generated.leftFlywheelSpeed);
//             return generated;
//         }
    
// }
// }
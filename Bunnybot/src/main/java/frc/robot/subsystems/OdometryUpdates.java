// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.Optional;

import org.littletonrobotics.junction.Logger;
import org.photonvision.EstimatedRobotPose;

import frc.robot.subsystems.swerve.Swerve;
import frc.robot.subsystems.vision.Vision;
import frc.robot.Constants.*;


public class OdometryUpdates extends SubsystemBase {

    private Swerve swerve;
    private Vision vision1;
    private Pose2d initialPose;
    private static boolean useVision;

    public OdometryUpdates(Vision vision1, Swerve swerve) {
        this.swerve = swerve;
        this.vision1 = vision1;
    }

    public void periodic() {
        runVision(vision1);
    }
    
    public void simulationPeriodic() {

    }

    public static void setVision(){
        useVision = true;
    }

    public void runVision(Vision vision){
        if (RobotBase.isReal()){
            Pose2d robotPosition = new Pose2d();
            double ambiguity = -1d;
            double timeStamp = 0.0;
    
            Optional<EstimatedRobotPose> robotPose = vision.getRobotPoseVision();
            
            if (robotPose.isPresent()) {
                robotPosition = robotPose.get().estimatedPose.toPose2d();
                ambiguity = vision.getPoseAmbiguityRatio();
                timeStamp = robotPose.get().timestampSeconds;

                if ((vision.getTargets().size() > 1) || 
                   ((Math.abs(ambiguity) < VISION.MAX_ACCEPTABLE_AMBIGUITY) && 
                    (vision.getTargets().size() > 0) && 
                    (vision.getTargets().get(0).bestCameraToTarget.getX() < VISION.REJECT_SINGLE_TAG_POSE_ESTIMATE_RANGE))) 
                        { 
                            if (DriverStation.isDisabled() && !robotPosition.equals(new Pose2d())){
                                initialPose = robotPosition;
                                swerve.setKnownOdometryPose(initialPose);
                            } else {
                                swerve.addVisionMeasurement(robotPosition, timeStamp);
                            }
                        }
    
            }

            Logger.recordOutput(SHARED.LOG_FOLDER+"/Navigation/TagsInView1", vision1.getTargets().size());
            Logger.recordOutput(SHARED.LOG_FOLDER+"/Navigation/VisionPose1", robotPosition);
            // Logger.recordOutput(SHARED.LOG_FOLDER+"/Navigation/OdometryPose", swerve.getCurrentPosition());
            Logger.recordOutput(SHARED.LOG_FOLDER+"/Navigation/AmbiguityRatio1", ambiguity);
            Logger.recordOutput(SHARED.LOG_FOLDER+"/Navigation/InitialPose", initialPose);
        }
    }
    
}
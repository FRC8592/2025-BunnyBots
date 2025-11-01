package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;
import frc.robot.helpers.motor.NewtonMotor;
import frc.robot.helpers.motor.NewtonMotor.IdleMode;
import frc.robot.helpers.motor.talonfx.KrakenX60Motor;
public class TestingLauncher1 extends SubsystemBase {
    //declaring motors used for launcher1
    private NewtonMotor launcher1motor1;
    private NewtonMotor launcher1motor2;
    
    public TestingLauncher1()
    {
        launcher1motor1 = new KrakenX60Motor(CAN.LAUNCHER1_MOTOR_CAN_ID_1, false);
        launcher1motor2 = new KrakenX60Motor(CAN.LAUNCHER1_MOTOR_CAN_ID_2, false);
    }

    /** 
    * accepts desired speed as a percentage and sets the motor to that speed
    * @param percent desired speed as percentage
    */

    public void setLauncherPercentOutput(double percent)
    {
        launcher1motor1.setPercentOutput(percent);
        launcher1motor2.setPercentOutput(percent);
    }

    /**
     * stops the launcher motor by setting output percentage to 0
     */

    public void stop()
    {
        setLauncherPercentOutput(0);
    }

    /**
     * accepts the desired power of the motor and sets the motor to that power
     * @param percent desired percentage of the motor
     * @return returns a command to set moter power to given percentage
     */
    public Command setLauncherCommand(double percent)
    {
        return this.run(()->
        {
            setLauncherPercentOutput(percent);
        });
    }

    /**
     * stops the intake motor by setting percent output to 0
     * @return retruns a command to stop the intake motor
     */
    public Command stopLauncherCommand()
    {
        return this.runOnce(()->
        {
            setLauncherPercentOutput(0);
        });
    }
}

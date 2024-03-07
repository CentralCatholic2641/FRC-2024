
package frc.team2641.robot2024.commands.auto;

import edu.wpi.first.networktables.BooleanPublisher;
import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.team2641.robot2024.subsystems.Drivetrain;

public class AutoAlign extends Command {

    private Drivetrain drivetrain;
    private int targetAngle;
    private int oppositeAngle;
    BooleanPublisher alignmentPub;
    DoublePublisher angularVelocityPub;

    public AutoAlign(int element) {
        drivetrain = Drivetrain.getInstance();

        NetworkTable table = NetworkTableInstance.getDefault().getTable("state");

        alignmentPub = table.getBooleanTopic("autoAlign").publish();
        alignmentPub.set(false);

        angularVelocityPub = table.getDoubleTopic("angularVelocity").publish();
        angularVelocityPub.set(0);

        if (element == 1) {
            targetAngle = -90;
            oppositeAngle = 90;
            // amp 
        }
        else if (element == 2) {
            targetAngle = 0;
            oppositeAngle = 180;
            // speaker 
        }
        else if (element == 3) {
            targetAngle = -150;
            oppositeAngle = 30;
            // source
        }
    }

    public void initialize() {
        alignmentPub.set(true);

        if (targetAngle > 0) {
            if (drivetrain.getYaw() < targetAngle && drivetrain.getYaw() < oppositeAngle) {
                angularVelocityPub.set(0.8);
            }
            else {
                angularVelocityPub.set(-0.8);
            }
        }
        else {
            if (drivetrain.getYaw() > targetAngle && drivetrain.getYaw() < oppositeAngle) {
                angularVelocityPub.set(-0.8);
            }
            else {
                angularVelocityPub.set(0.8);
            }
        }
       }
    
    /* We need to get the yaw angle
     * Then set that closer to the current angle
     * Continue until it's close enough
     * Angular velocity is how fast the robot should rotate to the target angle
    */
    public void execute() {
    }

    public void end(boolean interrupted) {
        alignmentPub.set(false);
        angularVelocityPub.set(0);
    }

    public boolean isFinished() {
        return (drivetrain.getYaw()<(targetAngle+1) && drivetrain.getYaw()>(targetAngle-1));
    }
}

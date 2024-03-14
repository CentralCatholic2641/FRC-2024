// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.robot2024.commands.auto;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.networktables.DoubleSubscriber;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.team2641.robot2024.subsystems.Drivetrain;

public class Creep extends Command {
  private Drivetrain drivetrain;
  private boolean isAngular;
  DoubleSubscriber angularVelocitySub;

  /** Creates a new Creep. */
  public Creep(boolean isAngular) {
    drivetrain = Drivetrain.getInstance();
    this.isAngular = isAngular;
    
    NetworkTable table = NetworkTableInstance.getDefault().getTable("state");

    angularVelocitySub = table.getDoubleTopic("angularVelocity").subscribe(0);
    
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (isAngular)
      drivetrain.drive(new Translation2d(0, 0), angularVelocitySub.get(), false);
    else
      drivetrain.drive(new Translation2d(0.8, 0), 0, false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.drive(new Translation2d(0, 0), 0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

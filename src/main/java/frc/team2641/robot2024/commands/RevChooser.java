// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.robot2024.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

// NOTE: Consider using this command inline, rather than writing a subclass. For more 
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RevChooser extends ParallelCommandGroup {

  /** Creates a new ShootHigh. */
  public RevChooser(int speed) {
    
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    if (speed == 1) {
      addCommands(new Rev(1));
    }
    else if (speed == 2) {
      addCommands(new Rev(2));
    }
    else if (speed == 3) {
      addCommands(new Rev(3));
    }
    else if (speed == 4) {
      addCommands(new Rev(4));
    }
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.time.Duration;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;

public class Spin extends Command {
  /** Creates a new Spin. */
  private DriveTrain m_driveTrain;
  public double m_duration;
  public double m_speed;
  public long m_startTime;
  public double m_rotation;

  // Todo: add a way to make it spin for a set amount of time (ie 5 seconds because we dont know the angle of the robot hence we have to rely on time)


  public Spin(DriveTrain theDriveTrain, double theSpeed, double theRotation, long theDuration) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveTrain = theDriveTrain;
    addRequirements(m_driveTrain);
    
    // Initialize the speed and duration of the turn
    m_speed = theSpeed;
    m_duration = theDuration;
    m_rotation = theRotation;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_startTime = System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // TODO: If the speed > 0, the robot will drive forward as well. Fix this?
    m_driveTrain.arcadeDrive(m_speed, m_rotation);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // When the command is finished, stop the robot.
    m_driveTrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // If the time has been elapsed, finish the command.
    return System.currentTimeMillis() - m_startTime >= m_duration;
  }
}

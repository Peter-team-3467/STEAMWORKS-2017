package org.usfirst.frc3467.robot.commands.Autonomous;

import org.usfirst.frc3467.subsystems.DriveBase.DriveSidewaysByTime;
import org.usfirst.frc3467.subsystems.Pneumatics.ToggleIntakeRamp;
import org.usfirst.frc3467.subsystems.Shooter.OperateShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoShootRight extends CommandGroup {

    public AutoShootRight() {
    	addSequential(new OperateShooter(true));
    	addSequential(new DriveSidewaysByTime(1.375, -0.5));
  }
}

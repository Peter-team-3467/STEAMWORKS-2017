package org.usfirst.frc3467.robot.commands.Autonomous;

import org.usfirst.frc3467.subsystems.DriveBase.DriveSideways;
import org.usfirst.frc3467.subsystems.Pneumatics.ToggleIntakeRamp;
import org.usfirst.frc3467.subsystems.Shooter.OperateShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoShootLeft extends CommandGroup {

    public AutoShootLeft() {
    	addSequential(new OperateShooter(true));
    	addSequential(new DriveSideways(2.75, 0.5));
    }
}

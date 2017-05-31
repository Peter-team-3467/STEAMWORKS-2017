package org.usfirst.frc3467.subsystems.GearCatcher;

import org.usfirst.frc3467.robot.CommandBase;

/**
 *
 */
public class GearIntake extends CommandBase {

	boolean gearIn = false;
	
    public GearIntake() {
        requires(gearcatch);
    }

    protected void initialize() {
        // Make sure the catcher is down before we try to intake a gear
    	gearcatch.catcherDown();
    	gearIn = false;
    }

    // Run gear intake as long as we aren't holding a gear (or if we have one and it moves out of position)
    protected void execute() {

    	if(!gearcatch.isGearHeld()) {
        	gearcatch.runGearIntake(gearcatch.GEAR_INTAKE_SPEED);
    	} else {
        	if (!gearIn) {
        	    // Stow gear catcher (and hopefully a gear!)
            	gearcatch.catcherUp();
        		gearIn = true;
        	}
        	gearcatch.runGearIntake(0.0);
    	}
    }

    // Don't end until another command overrides this one
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	gearcatch.runGearIntake(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

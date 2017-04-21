package org.usfirst.frc3467.subsystems.Shooter;

import org.usfirst.frc3467.robot.CommandBase;
import org.usfirst.frc3467.subsystems.PixyCam.NoTargetException;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoAim extends CommandBase {

	// Configurable parameters
	public static final double ANGLE_PRECISION = 2.0;
	
	boolean targetFound = false;
	int targetSearchCount = 1;
	int targetSearchDirection = 1;
	int targetSearchIncrement = 3;
	
	public AutoAim() {
		requires(pixyCamShooter);
		requires(shooterTurret);

		this.setInterruptible(true);
		SmartDashboard.putString("Shooter", "Auto Aim");
	}
	
	public void initialize() {
		targetFound = false;
	}
	
	public void execute() {

    	double [] pixyData = {0.0, 0.0, 0.0};
		try {
    		pixyData = pixyCamShooter.getBoilerLocationData();
    	} catch (NoTargetException ex) {
    		// No target found
    		searchForTarget();
    		return;
    	}
    	
    	// Name the returned data
		double distance = pixyData[0];
    	double angleX = pixyData[1];
    	double angleY = pixyData[2];

		// Command is completed if we are on target in the X direction
		if (Math.abs(angleX) <= ANGLE_PRECISION) {
			targetFound = true;
			return;
		}

	   	SmartDashboard.putString("Turret AutoAim:  distance = ", distance +"   angleX = " + angleX + "   angleY = "+ angleY);
		 		
    	//SmartDashboard.putString("Vision Tracking", angleY+"     " +distance+ "     "+targetDistance+"     "+zOut +"     "+angleX+"     "+(angleX*Z_SCALE));
    	//SmartDashboard.putString("Vision Distance Scale", "dScale " + distanceScale);

	}
	
	private void searchForTarget() {
		// Turn in place until target is found
		int movement = targetSearchCount * targetSearchIncrement * targetSearchDirection ;
	//	turretShooter.getAngle() 

	}
	
	protected boolean isFinished() {
		return targetFound;
	}
	
	public void end() {
		
	}
	
	protected void interrupted() {
		end();
	}

}

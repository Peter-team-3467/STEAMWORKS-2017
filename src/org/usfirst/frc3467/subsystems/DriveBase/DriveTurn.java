package org.usfirst.frc3467.subsystems.DriveBase;

import org.usfirst.frc3467.robot.CommandBase;
import org.usfirst.frc3467.subsystems.DriveBase.DriveStraight;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTurn extends CommandBase {

	private static final double TOLERANCE = 2;
	
	private PIDController m_pid;
	private double m_maxSpeed = 0.3;
	private double m_turnAngle = 0.0;
	
	private double KP = 1.0;
	private double KI = 0.0;
	private double KD = 2.0;
	
    public DriveTurn(double maxspeed, double angle) {
    	requires(driveBase);
		
		m_maxSpeed = maxspeed;
		m_turnAngle = angle;
		
		buildController();
    }
    
    private void buildController() {
		
		m_pid = new PIDController(KP, KI, KD,
                new PIDSource() {
                    PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

                    public double pidGet() {
                        return gyro.getAngle();
                    }

                    public void setPIDSourceType(PIDSourceType pidSource) {
                    	m_sourceType = pidSource;
                    }

                    public PIDSourceType getPIDSourceType() {
                        return m_sourceType;
                    }
                },
                new PIDOutput() {
                	
                	public void pidWrite(double d) {
                		// Drive with the magnitude returned by the PID calculation, 
                		// and spin around the center axis
            			driveBase.drive(d, 1.0);
                }});
		
        m_pid.setAbsoluteTolerance(TOLERANCE);
        m_pid.setOutputRange((m_maxSpeed * -1.0), m_maxSpeed);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    	m_pid.setSetpoint(gyro.getAngle() + m_turnAngle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
 
    	driveBase.reportEncoders();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

    	double error = m_pid.getError();
   		return (error >= 0 && error <= TOLERANCE);
    }

    // Called once after isFinished returns true
    protected void end() {
    	m_pid.disable();
        driveBase.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
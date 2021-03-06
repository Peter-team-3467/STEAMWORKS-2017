
package org.usfirst.frc3467.subsystems.FloorIntake;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import org.usfirst.frc3467.robot.RobotMap;

import com.ctre.CANTalon;

public class FloorIntake extends Subsystem {
	
	// Controls display to SmartDashboard
		private static final boolean debugging = true;
	
	public static final double kIntake = 1;
	public static final double kStop = 0;

	public CANTalon Intake_MotorOne, Intake_MotorTwo;
	public DoubleSolenoid rollerSolenoid;
	
	public FloorIntake() {

	Intake_MotorOne = new CANTalon(RobotMap.IntakeMotorOne);
	Intake_MotorTwo = new CANTalon(RobotMap.IntakeMotorTwo);
		rollerSolenoid = new DoubleSolenoid(RobotMap.intake_solenoid_extend, 
											RobotMap.intake_solenoid_retract);
		
	}

	protected void initDefaultCommand() {
		this.setDefaultCommand(new IntakeDrive());
	}
	public void DriveAuto(double Speed) {
		// TODO Auto-generated method stub
		if (debugging) {
	    	SmartDashboard.putNumber("Intake Speed", Speed);
		}
			Intake_MotorOne.set(Speed);
			Intake_MotorTwo.set(Math.abs(Speed));
	}
	
	// Extend or Retract Intake
	public void extend() {
		rollerSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void retract() {
		rollerSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void hold() {
		rollerSolenoid.set(DoubleSolenoid.Value.kOff);		
	}

	public void setIntake(Value upnDown) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}

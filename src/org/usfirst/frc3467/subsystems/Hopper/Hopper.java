package org.usfirst.frc3467.subsystems.Hopper;

import org.usfirst.frc3467.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hopper extends Subsystem {
	
	public static final Value kExtend = Value.kForward;
	public static final Value kIN = Value.kReverse;
	public static final Value kOff = Value.kOff;

	public static DoubleSolenoid hopperSolenoid;
	private static Hopper instance;
	
	public static Hopper getInstance(){
		return instance;
	}
	public Hopper(){
		instance = this;
		hopperSolenoid = new DoubleSolenoid(RobotMap.hopper_solenoid_deploy,
				RobotMap.hopper_solenoid_retract);
	}
	public void setHopper(Value EnR){
		hopperSolenoid.set(EnR);
	}
	public Value getHopperState(){
		return hopperSolenoid.get();
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}

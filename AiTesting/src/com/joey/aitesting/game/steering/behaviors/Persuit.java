package com.joey.aitesting.game.steering.behaviors;

import com.joey.aitesting.game.entities.Vehicle;
import com.joey.aitesting.game.shapes.Vector2D;
import com.joey.aitesting.game.shapes.WorldWrapper;

public class Persuit extends AbstractBehavior{

	public Persuit(Vehicle veh) {
		super(veh);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void calculate(Vector2D force) {
		// TODO Auto-generated method stub
		
	}

	public static void persuit(Vehicle vehicle, Vehicle persuit, Vector2D rst) {
		Vector2D.subtract(persuit.pos, vehicle.pos, rst);
		float RelativeHeading = vehicle.velHead.dot(persuit.velHead);
		if ((rst.dot(vehicle.velHead) > 0) && (RelativeHeading < -0.95)) { 
			Vector2D point = new Vector2D();
			WorldWrapper.moveToClosest(vehicle.pos, persuit.pos, point,
					vehicle.world.worldBounds);
			Seek.seek(vehicle,point,  rst);
			return;
		}
		float LookAheadTime = rst.length()
				/ (vehicle.maxSpeed + persuit.vel.length());
		Vector2D vec = new Vector2D(persuit.vel);
		vec.scale(LookAheadTime);
		vec.add(persuit.pos);

		Vector2D point = new Vector2D();
		WorldWrapper.moveToClosest(vehicle.pos, vec, point, vehicle.world.worldBounds);
		Seek.seek(vehicle, point, rst);
	}
}

package bullet;

import java.util.Vector;

import engine.GameEngine;
import processing.core.PVector;
import tank.Tank;

import java.util.Comparator;



public class Bullet{

	private boolean active;
	float x;
	float y;
	PVector velocity;

	static final float BULLET_SPEED = 10;

	public Bullet(){
		active = false;
		x = 0;
		y = 0;
		velocity = new PVector(0,0);
	}

	public Bullet(float x, float y, PVector direction) {
		active = true;
		this.x = x;
		this.y = y;
		this.velocity = direction.setMag(BULLET_SPEED);
	}


	void update(){
		if (!active) {
			return;
		}
		else {
			x += velocity.x;
			y += velocity.y;
		}
	}

	void destroy(Tank tank) {

	}

}

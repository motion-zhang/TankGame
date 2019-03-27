package tank;

import bullet.Bullet;

public class Tank implements ITank {

	private boolean alive = false; // Check whether tank alive
	public Steering vel; // velocity and position
	public Bullet bullet;
	private int index; // 0: player tank, else ai tank
	public int x;
	public int y;
	public int direction; // 0, 1, 2, 3, 4, 1 up, 2 down, 3 left, 4 right;



	public boolean enemyFire = false;
	public boolean isStop;
	public boolean isSuspend;



	public Tank(int index, int x, int y, boolean alive, Steering vel, int direction) {
		this.index = index;
		this.alive = alive;
		this.direction = direction;
		this.bullet = new Bullet();
		this.vel = vel;
		this.x = x;
		this.y = y;

	}


	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public Steering getVelocity() {
		return this.vel;
	}

	@Override
	public Boolean getAlive() {
		return this.alive;
	}

	@Override
	public void move(int direction) {
		if (direction == 0) {
			return;
		} else if (this.direction == 1) {
			this.y -= 5;
		} else if (this.direction == 2) {
			this.y += 5;
		} else if (this.direction == 3) {
			this.x -= 5;
		} else if (this.direction == 4) {
			this.x += 5;
		}
	}


	@Override
	// steering behaviors not applied yet
	public void collideTank(Tank tank) {
		// Collision happens by up-down, left-right, still-up, still down, still right, still left


		// Player ai collide
		if (this.index == 0 || tank.index == 0) {
			// Check the direction
			this.collideDirection(tank, 0);
			// Then Compare steering behavior to decide which will bounce away// crash

		}
		// ai ai collide
		else if (this.index != 0 && tank.index != 0) {
			// Check the direction first
			this.collideDirection(tank, 1);

		} else {
			return;
		}

	}

	@Override
	public void fire() {
		return;
	}

	private void collideDirection(Tank tank, int type) {

		// up - down collide
		if (this.direction == 1 && tank.direction == 2) {
			if (type == 0) {
				this.y += 5;
				tank.y -= 5;
			} else {
				this.y += 2;
				tank.y -= 2;
			}

		}
		// down - up collide
		if (this.direction == 2 && tank.direction == 1) {
			if (type == 0) {
				this.y -= 5;
				tank.y += 5;
			} else {
				this.y -= 2;
				tank.y += 2;
			}

		}
		// left right
		if (this.direction == 3 && tank.direction == 4) {
			if (type == 0) {
				this.x += 5;
				tank.x += 5;
			} else {
				this.x += 2;
				tank.x += 2;
			}
		}
		// right - left
		if (this.direction == 2 && tank.direction == 1) {
			if (type == 0) {
				this.x -= 5;
				tank.x -= 5;
			} else {
				this.x -= 2;
				tank.x -= 2;
			}
		}
	}

	@Override
	public void collideWall() {
		// Assume a 300 * 300 game
		if (this.x < 5 || this.x > 295 || this.y < 5 || this.y > 295) {
			if (this.x < 5) {
				this.x += 10;
			}
			if (this.x > 5) {
				this.x -= 10;
			}
			if (this.y < 5) {
				this.y += 10;
			}
			if (this.y > 295) {
				this.y -= 10;
			}
			this.reDirect();
		}
	}

	private void reDirect() {
		if (this.direction == 1) {
			this.direction = 2;
		}
		if (this.direction == 2) {
			this.direction = 1;
		}
		if (this.direction == 3) {
			this.direction = 4;
		}
		if (this.direction == 4) {
			this.direction = 3;
		}

	}
}
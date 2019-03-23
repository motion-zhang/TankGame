package tank;

public interface ITank {

	Steering getVelocity();

	Boolean getAlive();

	void move();

	void collideTank(Tank tank);

	void fire();

	void CollideWall();


}

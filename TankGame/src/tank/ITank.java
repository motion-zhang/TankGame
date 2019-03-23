package tank;

public interface ITank {

	Steering getVelocity();

	Boolean getAlive();

	void move();

	void CollideTank(Tank tank);

	void fire();

	void CollideWall();


}

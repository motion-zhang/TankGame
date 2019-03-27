package tank;

public interface ITank {

	Steering getVelocity();

	Boolean getAlive();

	void move(int direction);

	void collideTank(Tank tank);

	void fire();

	void collideWall();

	int getX();

	int getY();


}

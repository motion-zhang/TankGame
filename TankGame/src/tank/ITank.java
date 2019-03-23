package tank;

public interface ITank {

	Steering getVel();

	Boolean getAlive();

	void move();

	void CollideTank();

	void fire();

	void CollideWall();

	void getPlayerTank();

	void addAITankTank();

	void addPlayerTank();

	void addAITank();


}

package TankModel;

import java.util.List;

import processing.core.PVector;
import tank.ITank;

public interface ITankModel {

  //Get the player's tank at that time.
  public ITank getPlayerTank();

  //Move the tank by the certain direction.
  public void move(int direction);

  //Determine if the game is over.
  // 0 for keep running, 1 for win, 2 for lose..
  public int isWin();

  //Let the player;s tank fire.
  public void playerFire();

  public List<ITank> getEnemyTank();

  //Add a tank and give a unique index
  public void addEnemyTank(int x, int y);

  //remove the tank if it is dead by index.
  public void removeEnemyTank(int index);

  //remove the tank which is already dead.
  public void clearDeadTank();

  public void addBullet(PVector velocity, float direction);
}

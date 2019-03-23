package TankModel;

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
}

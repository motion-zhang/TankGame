package TankModel;

import java.util.HashMap;

import bullet.Bullet;
import tank.ITank;
import tank.Steering;
import tank.Tank;

public class TankModelImpl implements ITankModel {
  Terrain [][] background;
  HashMap<Integer, ITank> enemyTank;
  ITank playerTank;
  int index;

  public TankModelImpl(int sizeX, int sizeY){
    background = new Terrain[sizeX][sizeY];
    enemyTank = new HashMap<>();
    playerTank = null;
    index = 0;
  }

  @Override
  public ITank getPlayerTank() {
    return playerTank;
  }

  @Override
  public void move(int direction) {
    playerTank.move();
  }

  @Override
  public int isWin() {
    return 0;
  }

  @Override
  public void playerFire() {
    playerTank.fire();
  }

  @Override
  public void addEnemyTank(int x, int y) {
    ITank newTank = new Tank(index,true,new Steering(x,y), 0, new Steering(0,0),
            new Bullet(0, x, y));
    enemyTank.put(index, newTank);
    index += 1;
  }

  @Override
  public void removeEnemyTank(int index) {
    enemyTank.remove(index);
  }

  @Override
  public void clearDeadTank() {
    for(int index : enemyTank.keySet()){
      ITank tank = enemyTank.get(index);
      if(!tank.getAlive()){
        enemyTank.remove(index);
      }
    }
  }
}

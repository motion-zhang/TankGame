package TankModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bullet.Bullet;
import processing.core.PVector;
import tank.ITank;
import tank.Steering;
import tank.Tank;

public class TankModelImpl implements ITankModel {
  int sizeX, sizeY;
  List<Bullet> bulletList;
  Terrain [][] background;
  HashMap<Integer, ITank> enemyTank;
  ITank playerTank;
  int index;

  public TankModelImpl(int sizeX, int sizeY){
    this.sizeX = sizeX;
    this.sizeY = sizeY;
    background = new Terrain[sizeX][sizeY];
    enemyTank = new HashMap<>();
    playerTank = null;
    index = 0;
    bulletList = new ArrayList<>();
    // For now, set the background all plain
    for(int i = 0; i < sizeX; i ++){
      for(int j = 0; j < sizeY; j++) {
        background[i][j] = Terrain.Plain;
      }
    }
  }


  @Override
  public int getSizeX() {
    return sizeX;
  }

  @Override
  public int getSizeY() {
    return sizeY;
  }

  @Override
  public Terrain[][] getBackground() {
    return this.background;
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
  public List<ITank> getEnemyTank() {
    ArrayList<ITank> result = new ArrayList<>();
    for(int index: enemyTank.keySet()) {
      result.add(enemyTank.get(index));
    }
    return result;
  }

  @Override
  public void addEnemyTank(int x, int y) {
    ITank newTank = new Tank(index,0,0,true, new Steering(0,
            0),0);
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

  @Override
  public void addBullet(PVector velocity, float direction) {
    //Bullet newBullet = new Bullet(0,)
  }
}

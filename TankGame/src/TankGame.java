import java.io.IOException;

import TankModel.ITankModel;
import TankModel.TankModelImpl;
import TankModel.Terrain;
import UI.IView;
import UI.TankView;
import controller.MyController;
import controller.TankController;
import tank.ITank;
import tank.Steering;
import tank.Tank;

public class TankGame {
  public static void main(String[] args) {
    try{
      ITank player = new Tank(-1,10,10,true,new Steering(0,0),0);
      ITankModel model = new TankModelImpl(20,20);
      model.addEnemyTank(0,0);
      model.addEnemyTank(5,0);
      model.addEnemyTank(10,0);
      model.addPlayerTank(player);


      IView view = new TankView(model);
      MyController controller = new TankController(model,view,60);
      controller.run();
    }
    catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("IO exception");
    }
  }
}

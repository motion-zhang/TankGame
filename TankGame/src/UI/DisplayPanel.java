package UI;

import com.sun.org.apache.regexp.internal.REDebugCompiler;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;

import java.awt.*;

import javax.swing.*;

import TankModel.ITankModel;
import TankModel.Terrain;
import tank.ITank;

public class DisplayPanel extends JPanel {
  private ITankModel tankModel;
  private int tick = 0;
  private boolean isPlaying = true;

  DisplayPanel(ITankModel model) {
    if(model == null){
      throw new IllegalArgumentException("No model given in!");
    }
    this.tankModel = model;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graphics2D = (Graphics2D) g;
    paintBackGround(graphics2D);

    for(ITank enemy : tankModel.getEnemyTank()) {
      paintTank(enemy, graphics2D);
    }

    paintTank(tankModel.getPlayerTank(), graphics2D);
    g.dispose();

  }

  private void paintBackGround(Graphics2D graphics2D) {
    //Graphics2D graphics2D = (Graphics2D) g.create();
    graphics2D.setColor(Color.WHITE);
    Terrain[][] background = tankModel.getBackground();
    int sizeX = tankModel.getSizeX();
    int sizeY = tankModel.getSizeY();
    for(int i = 0; i < sizeX; i ++) {
      for(int j = 0; j < sizeY; j ++) {
        Terrain thisCell = background[i][j];
        switch (thisCell) {
          case Plain: {
            graphics2D.setColor(Color.WHITE);
            break;
          }
          case Wall: {
            graphics2D.setColor(Color.RED);
            break;
          }
          default: {
            throw new IllegalArgumentException("Cannot read this cell" + " i " + " j \n");
          }
        }
        graphics2D.fillRect(i * 20, j * 20, 20, 20);
      }
    }
  }

  private void paintTank(ITank tank, Graphics2D graphics2D) {

    graphics2D.setColor(new Color(tank.getX()* 40 % 255,tank.getX()* 20 % 255
            ,tank.getX()* 10 % 255));
    //g.translate(-1 * tank.getX(), );
    int x = tank.getX() * 20;
    int y = tank.getY() * 20;
    int width = 20;
    int height = 20;
    int centerX = x * 20+ width / 2;
    int centerY = y * 20+ height / 2;
    graphics2D.fillRect(x,y,width,height);
    //g.dispose();
  }
}

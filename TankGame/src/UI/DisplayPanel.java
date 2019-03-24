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

  public DisplayPanel(ITankModel model) {
    if(model == null){
      throw new IllegalArgumentException("No model given in!");
    }
    this.tankModel = model;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    paintBackGround(g);

    //ITank oneTank;
    for(ITank enemy : tankModel.getEnemyTank()) {
      paintTank(enemy, g);
    }

    paintTank(tankModel.getPlayerTank(), g);

  }

  private void paintBackGround(Graphics g) {
    Graphics2D graphics2D = (Graphics2D) g.create();
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
        g.fillRect(i * 20, j * 20, 20, 20);
        g.dispose();
      }
    }
  }

  private void paintTank(ITank tank, Graphics graphics) {
    Graphics2D g = (Graphics2D) graphics.create();

    g.setColor(new Color(0,0,0));
    //g.translate(-1 * tank.getX(), );
    int x = tank.getX();
    int y = tank.getY();
    int width = 20;
    int height = 20;
    int centerX = x + width / 2;
    int centerY = y + height / 2;

    g.fillRect(x,y,width,height);
    g.dispose();
  }
}

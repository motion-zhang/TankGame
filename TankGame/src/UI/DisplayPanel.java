package UI;

import com.sun.org.apache.regexp.internal.REDebugCompiler;

import java.awt.*;

import javax.swing.*;

import TankModel.ITankModel;
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
    //ITank oneTank;
    for(ITank enemy : tankModel.getEnemyTank()) {
      paintTank(enemy, g);
    }

    paintTank(tankModel.getPlayerTank(), g);

  }

  private void paintTank(ITank tank, Graphics graphics) {
    Graphics2D g = (Graphics2D) graphics.create();

    g.setColor(new Color(0,0,0));
    //g.translate(-1 * tank.getX(), );
    int x = tank.getX();
    int y = tank.getY();
  }
}

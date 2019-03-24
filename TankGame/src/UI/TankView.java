package UI;

import java.awt.*;

import javax.swing.*;

import TankModel.ITankModel;

public class TankView extends JFrame implements IView {
  //private ITankModel tankModel;
  private DisplayPanel displayPanel;
  public TankView(ITankModel model) {
    this.displayPanel = new DisplayPanel(model);
    this.displayPanel.setPreferredSize(new Dimension(model.getSizeX()*20,
            model.getSizeY() * 20));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  @Override
  public void display() {
    setVisible(true);
  }
}

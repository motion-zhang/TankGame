package UI;

import javax.swing.*;

import TankModel.ITankModel;

public class TankView extends JFrame implements IView {
  //private ITankModel tankModel;
  private DisplayPanel displayPanel;
  public TankView(ITankModel model) {
    this.displayPanel = new DisplayPanel(model);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  @Override
  public void display() {
    setVisible(true);
  }
}

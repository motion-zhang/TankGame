package UI;

import java.awt.*;

import javax.swing.*;

import TankModel.ITankModel;
//import controller.TankController;
import controller.MyController;
import controller.TankController;

public class TankView extends JFrame implements IView {
  //private ITankModel tankModel;
  private DisplayPanel displayPanel;
  private MyController listener;
  public TankView(ITankModel model) {
    listener = null;
    this.displayPanel = new DisplayPanel(model);
    JScrollPane scroll = new JScrollPane(displayPanel);
    //displayPanel.setPreferredSize(new Dimension(model.getWidth(),
    //        model.getHeight()));
    displayPanel.setPreferredSize(new Dimension(model.getSizeX()*20,
            model.getSizeY() * 20));
    this.add(scroll);
    this.pack();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  @Override
  public void display() {
    setVisible(true);
  }

  @Override
  public void refresh() {
    this.displayPanel.repaint();
  }

  @Override
  public void addListener(MyController listener) {
    this.listener = listener;
  }
}

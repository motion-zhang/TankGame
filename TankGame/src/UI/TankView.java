package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import TankModel.ITankModel;
//import controller.TankController;
import controller.MyController;
import controller.TankController;

public class TankView extends JFrame implements IView {
  //private ITankModel tankModel;
  private DisplayPanel displayPanel;
  private MyController listener;
  protected Timer timer;
  public TankView(ITankModel model) {
    listener = null;
    this.displayPanel = new DisplayPanel(model);
    JScrollPane scroll = new JScrollPane(displayPanel);
    //displayPanel.setPreferredSize(new Dimension(model.getWidth(),
    //        model.getHeight()));
    displayPanel.setPreferredSize(new Dimension(300,
            300));
    this.add(scroll);
    this.pack();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    timer = new Timer(1000 / 2, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e){

      }
    });
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

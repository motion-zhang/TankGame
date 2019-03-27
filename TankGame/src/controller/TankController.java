package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import TankModel.ITankModel;
import UI.IView;

public class TankController implements MyController, ActionListener, KeyListener {
  private ITankModel model;
  private IView view;
  private Timer timer;
  int tps;

  public TankController(ITankModel model, IView view, int tps) {
    if (model == null || view == null || tps < 1) {
      throw new IllegalArgumentException("Input cannot be null, tps must be at least 1\n");
    }
    this.model = model;
    this.view = view;
    this.tps = tps;
    this.timer = new Timer((int) ((1 / (double) tps) * 1000), this);
  }
  private void movePlayerTank(int direction) {

    model.move(direction);
    view.refresh();
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_UP) {
      System.out.print("fda");
      movePlayerTank(1);
    }
    if(e.getKeyCode() == KeyEvent.VK_DOWN) {
      movePlayerTank(2);
    }
    if(e.getKeyCode() == KeyEvent.VK_LEFT) {
      movePlayerTank(3);
    }
    if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
      movePlayerTank(4);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    //view.refresh();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
  }

  @Override
  public void run() {
    this.timer.start();
    this.view.addListener(this);
    this.view.display();
  }
}

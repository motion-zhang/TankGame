package UI;

import javax.swing.*;

import controller.MyController;

public interface IView {
  void display();

  //refresh the current view
  void refresh();

  void addListener(MyController listener);

  //TODO : refresh may not work fine.
}

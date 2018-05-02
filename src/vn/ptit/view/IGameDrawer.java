package vn.ptit.view;

import java.awt.*;

import vn.ptit.model.GameObject;


public interface IGameDrawer {
    void paint(GameObject gameObject, Graphics g);
}

package vn.ptit.controller;

import java.awt.Graphics;

import vn.ptit.model.GameObject;
import vn.ptit.model.GameVector;
import vn.ptit.view.IGameDrawer;

public class SingleController implements IController {
    protected GameObject gameObject;
    protected GameVector gameVector;
    protected IGameDrawer gameDrawer;

    public SingleController(GameObject gameObject, IGameDrawer gameDrawer, GameVector gameVector) {
        this.gameObject = gameObject;
        this.gameDrawer = gameDrawer;
        this.gameVector = gameVector;
    }

    @Override
    public void run() {
        this.gameObject.move(this.gameVector);
    }

    @Override
    public void paint(Graphics g) {
        this.gameDrawer.paint(this.gameObject, g);
    }
    
}

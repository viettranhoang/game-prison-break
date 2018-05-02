package vn.ptit.model;

import java.awt.Rectangle;


public class GameObject {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isAlive = true;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public Rectangle getNextRect(GameVector gameVector) {
        return new Rectangle(x+gameVector.dx, y+gameVector.dy, width, height);
    }

    public void move(GameVector gameVector) {
        this.x += gameVector.dx;
        this.y += gameVector.dy;
    }


}

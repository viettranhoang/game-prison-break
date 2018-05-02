package vn.ptit.controller;

import vn.ptit.model.GameObject;

public interface IColliable {
    void onCollide(IColliable c);
    GameObject getGameObject();
}

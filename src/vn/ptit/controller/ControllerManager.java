package vn.ptit.controller;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.Vector;

import vn.ptit.controller.SingleController;


public class ControllerManager implements IController {

    protected Vector<SingleController> controllerVector;

    public ControllerManager() {
        this.controllerVector = new Vector<SingleController>();
    }

    public void add(SingleController singleController) {
        this.controllerVector.add(singleController);
    }

    public int size() {
        return this.controllerVector.size();
    }

    @Override
    public void run() {
        Iterator<SingleController> iterator = this.controllerVector.iterator();
        while (iterator.hasNext()) {
            SingleController singleController = iterator.next();
            if (singleController.gameObject.isAlive() == false) {
                iterator.remove();
            } else {
                singleController.run();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
//        for (SingleController singleController : this.controllerVector) {
//            singleController.paint(g);
//        } // dùng foreach là lỗi :((((( do foreach lặp lại list -> thread thay đổi list -> EXCEPTION
        for (int i = 0; i < controllerVector.size(); i++) {
            controllerVector.get(i).paint(g);
        }
    }
    
    
}

package vn.ptit.controller;

import java.awt.Rectangle;
import java.util.Iterator;
import java.util.Vector;


public class CollisionPool {
    private Vector<IColliable> colliableVector;

    public CollisionPool() {
        this.colliableVector = new Vector<IColliable>();
    }

    public void add(IColliable c) {
        this.colliableVector.add(c);
    }

    public void run() {
        Iterator<IColliable> iterator = this.colliableVector.iterator();
        while (iterator.hasNext()) {
            IColliable colliable = iterator.next();
            if (!colliable.getGameObject().isAlive()) {
                iterator.remove();
            }
        }
        for (int i = 0; i < this.colliableVector.size() - 1; i++) {
            for (int j = i + 1; j < this.colliableVector.size(); j++) {
                IColliable ci = this.colliableVector.get(i);
                IColliable cj = this.colliableVector.get(j);
                Rectangle ri = ci.getGameObject().getRect();
                Rectangle rj = cj.getGameObject().getRect();
                if (ri.intersects(rj)) {
                    ci.onCollide(cj);
                    cj.onCollide(ci);
                }
            }
        }
    }

    private static CollisionPool collisionPool;

    public static CollisionPool getSingleton() {
        if (collisionPool == null) {
            collisionPool = new CollisionPool();
        }
        return collisionPool;
    }

    public void setNull() {
        collisionPool = null;
    }
}

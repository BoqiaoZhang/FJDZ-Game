package model.Movable;

import java.awt.image.BufferedImage;

public abstract class Movable {
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected BufferedImage img;

    public BufferedImage getImg() {
        return this.img;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public abstract void move();
}

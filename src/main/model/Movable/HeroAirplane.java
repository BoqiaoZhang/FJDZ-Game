package model.Movable;

import model.App;

public class HeroAirplane extends Movable {

    public HeroAirplane() {
        img = App.getImg("./images/hero.png"); //choose/change the image here
        x = 200;
        y = 500;
        w = 50;
        h = 50;
    }

    public void moveToForMouseEvent(int x, int y) {
        this.x = x - getW() / 2;
        this.y = y - getH() / 2;
    }

    public void moveUpForKeyEvent() {
        this.y = y - 12;
    }

    public void moveDownForKeyEvent() {
        this.y = y + 12;
    }

    public void moveToLeftForKeyEvent() {
        this.x = x - 12;
    }

    public void moveToRightForKeyEvent() {
        this.x = x + 12;
    }

    @Override
    public void move() {
        //default method
    }

    public boolean beingHitOrNot(Enemy e) {
        boolean checkingWidth;
        boolean checkingHeight;
        checkingWidth = e.getX() - this.w <= this.x && this.x <= (e.getX() + e.getW());
        checkingHeight = e.getY() - this.h <= this.y && this.y <= (e.getY() + e.getH());
        return checkingHeight && checkingWidth;
    }
}

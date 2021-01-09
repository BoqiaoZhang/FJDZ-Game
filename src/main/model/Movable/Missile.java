package model.Movable;

import model.App;

public class Missile extends Movable {
    //int dir; Comment out thsi field if we want missiles move in different directions

    public Missile(int hx, int hy) {
        img = App.getImg("./images/missile.png");
        assert(img != null);

        w = img.getWidth() / 4;
        h = img.getHeight() / 4;

        x = hx;
        y = hy - this.getH();
    }

    @Override
    public void move() {
        this.y = y - 10;
    }
}

package model.Movable;

import model.App;

import java.util.Random;

public class Enemy extends Movable {
    int xSpeed;
    int ySpeed;
    int hp;
    int type;
    int movingTypeForThe15thEnemy;
    // For 15th enemy, movingTypeForThe15thEnemy is either 0 or 1.
    // If movingTypeForThe15thEnemy = 0; it appears at the x=0 with xSpeed > 0.
    // If movingTypeForThe15thEnemy = 1; it appears at the x=512 with xSpeed < 0.

    public Enemy() {
        Random imgIndex = new Random();
        int imgIndexNum = imgIndex.nextInt(15) + 1;
        String fixedDigitString = fixSize(imgIndexNum);
        String resource = "./images/ep" + fixedDigitString + ".png";
        img = App.getImg(resource);
        assert img != null;

        w = img.getWidth();
        h = img.getHeight();

        type = imgIndexNum;

        setInitialHP(imgIndexNum);

        if (type == 15) {
            Random r = new Random();
            movingTypeForThe15thEnemy = r.nextInt(2);
            //For 15th enemy, movingTypeForThe15thEnemy can only be either 0 or 1
        } else {
            movingTypeForThe15thEnemy = 100;
            //For other enemies, movingTypeForThe15thEnemy is 100 for default
        }

        setInitialSpeed(imgIndexNum, movingTypeForThe15thEnemy);

        setInitialPosition(movingTypeForThe15thEnemy);
    }

    private void setInitialHP(int i) {
        if (1 <= i && i <= 3) {
            hp = 1;
        } else if (4 <= i && i <= 8) {
            hp = 2;
        } else if (9 <= i && i <= 11) {
            hp = 3;
        } else if (12 <= i && i <= 15) {
            hp = 4;
        }
    }

    public String fixSize(int i) {
        if (i >= 10) {
            return Integer.toString(i) ;
        } else return "0" + Integer.toString(i);
    }

    //EFFECTS: move the enemyAirplane
    @Override
    public void move() {
        if (type == 5) {
            this.y = y + ySpeed;
            this.x = x + xSpeed;
        } else if (type == 6) {
            this.y = y + ySpeed;
            this.x = x - xSpeed;
        } else if (type == 15) {
            this.y = y + ySpeed;
            this.x = x + xSpeed; //must be + sign here to make sense
        } else {
            this.y = y + ySpeed;
        }
    }


    public void move(int speed) {
        this.y = y + speed;
    }

    //EFFECTS: return ture if the enemyAirplane is shot by a missile, false otherwise
    public boolean beingHitOrNot(Missile m) {
        boolean checkingWidth;
        boolean checkingHeight;
        checkingWidth = this.x - m.getW() <= m.getX() && m.getX() <= (this.x + this.w);
        checkingHeight = this.y - m.getH() <= m.getY() && m.getY() <= (this.y + this.h);
        return checkingHeight && checkingWidth;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    //EFFECTS: set initial speed (both xSpeed and ySpeed)
    public void setInitialSpeed(int index, int movingTypeForThe15thEnemy) {
        ySpeed = 20 - index;

        if (type == 15) {
            if (movingTypeForThe15thEnemy == 0) {
                xSpeed = 8;
            } else {
                xSpeed = -8;
            }
        } else if (type == 5) {
            xSpeed = 5;
        } else if (type == 6) {
            xSpeed = -5;
        } else {
            xSpeed = 0;
        }
    }

    public void setInitialPosition(int movingTypeForThe15thEnemy) {
        Random random = new Random();
        if (type == 15) {
            y = random.nextInt(96) - h; //[-h,768/8-h]
            if (movingTypeForThe15thEnemy == 0) {
                x = -w;
            } else {
                x = 512;
            }
        } else {
            y = -h;
            x = random.nextInt(512 - w); //[0,512-w]
        }
    }
}

package ui;

import model.App;
import model.Movable.Enemy;
import model.Movable.HeroAirplane;
import model.Movable.Missile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    GameFrame frame;
    BufferedImage backgroundImage;
    HeroAirplane hero = new HeroAirplane();
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    int counterForEnemies = 0; //counter for enemyAppears() and moveAllEnemies()
    int FREQUENCY_FOR_Enemies = 20; //time gap between two appearance of enemies
                                    //The larger, the easier the game will be
    ArrayList<Missile> missiles = new ArrayList<Missile>();
    int counterForMissiles = 0;
    int FREQUENCY_FOR_MISSILE_MOVEMENT = 3; //time gap between two movements of missiles
                                            //The larger, the lower speed of missiles
    int counterForShoot = 0;
    int FREQUENCY_FOR_SHOOT = 13;           //time gap between two shoots
                                            //The larger, the easier the game will be

    int score;

    //constructor for a new GamePanel
    public GamePanel(JFrame frame) {
        this.frame = (GameFrame) frame;
        setBackground(Color.BLUE);
        backgroundImage = App.getImg("./images/bg2.jpg");
        score = 0;

        //When using keyAdapter, we need to add the keyAdapter to the JFrame (GameFrame now)
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();         //get the keyCode of the button(key) pressed
                if (keyCode == KeyEvent.VK_UP){
                    hero.moveUpForKeyEvent();
                    //Change the constant (1) to control the moving speed
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    hero.moveDownForKeyEvent();
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    hero.moveToLeftForKeyEvent();
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    hero.moveToRightForKeyEvent();
                }
                repaint();
            }
        };

        frame.addKeyListener(keyAdapter);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {

                int mouseX = e.getX();  //get the x_coord of the airplane
                int mouseY = e.getY();  //get the y_coord of the airplane
                hero.moveToForMouseEvent(mouseX,mouseY); //only change the coordinates, do not repaint
                repaint(); //repaint our heroAirplane at the new position
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    //EFFECTS: When calling the constructor of GamePanel,
    // 1.create and add the background image automatically
    // 2.paint the heroAirplane
    //3.paint all the enemies
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage,0,0,512,768,null);
        g.drawImage(hero.getImg(),hero.getX(),hero.getY(),hero.getW(),hero.getH(),null);
        for (int i = 0; i < enemies.size(); i++) {
            Enemy ei = enemies.get(i);
            g.drawImage(ei.getImg(),ei.getX(),ei.getY(),ei.getW(),ei.getH(),null);
        }
        for (int i = 0; i < missiles.size(); i++) {
            Missile m = missiles.get(i);
            g.drawImage(m.getImg(),m.getX(),m.getY(),m.getW(),m.getH(),null);
        }

        g.setColor(Color.white);
        setFont(new Font("Serif: ",Font.BOLD,20));
        g.drawString("Score: " + score,10,30);
    }

    //EFFECTS: call this method when game starts
    public void gameStarter() {
        new Thread() {
            public void run() {
                while(!gameOverOrNot()) {
                    //Add 1 enemy when calling enemyAppears() n times
                    enemyAppears();
                    //Move all the enemies 1 time when calling moveAllEnemies() 1 time
                    moveAllEnemies();
                    //Create a new missile
                    shoot();
                    moveAllMissiles();

                    //check hitting between missiles and enemies
                    hitEnemies();

                    //Let Thread sleep(pause for some time)
                    try {
                        Thread.sleep(20); //unit: ms
                    } catch (InterruptedException e) {
                        System.out.println("Something wrong when doing the sleep for Thread!");
                    }

                    repaint();  //refresh the GamePanel whenever the game is NOT over
                }
            }
        }.start();
    }

    //EFFECTS: create a new missile at the position of heroAirplane
    private void shoot() {
        counterForShoot++;
        if (counterForShoot == FREQUENCY_FOR_SHOOT) {
            this.counterForShoot = counterForShoot - FREQUENCY_FOR_SHOOT;
            Missile m1 = new Missile(hero.getX() - 10, hero.getY());
            //change the constant to change the initial position of a missile
            Missile m2 = new Missile(hero.getX() + 35, hero.getY());
            missiles.add(m1);
            missiles.add(m2);
        }
    }

    //EFFECTS: when calling this method FREQUENCY_FOR_MissilesMovement times,
    //         move all the missiles
    public void moveAllMissiles() {
        counterForMissiles++;
        if (counterForMissiles == FREQUENCY_FOR_MISSILE_MOVEMENT) {
            this.counterForMissiles = counterForMissiles - FREQUENCY_FOR_MISSILE_MOVEMENT;
            for (Missile m : missiles) {
                m.move();
            }
        }
    }

    //EFFECTS: let all the enemyAirplanes move
    private void moveAllEnemies() {
        for (Enemy e: enemies) {
            e.move();
        }
    }

    //EFFECTS: create a new enemyAirplane, repeated if the game is still running
    //         When enemyAppears() and moveAllEnemies() are called 20 times, add one enemy.
    public void enemyAppears() {
        counterForEnemies++;
        if (counterForEnemies == FREQUENCY_FOR_Enemies) {
            Enemy e = new Enemy();
            enemies.add(e);
            this.counterForEnemies = counterForEnemies - FREQUENCY_FOR_Enemies;
        }
    }

    //EFFECTS: figure out whether the game is over
    //         if the game is over, return true; otherwise return false
    public boolean gameOverOrNot() {
        for (Enemy e: enemies) {
            if (hero.beingHitOrNot(e)) {
                this.setVisible(false);
                frame.setVisible(false);
                new GameOverPage(score);
                return true;
            };
        }
        return false;
    }

    //EFFECTS: when a missile hits an enemyAirplane, remove both the missile and the enemy
    //         increment the score
    public void hitEnemies() {
        ArrayList<Missile> copiedMissiles = new ArrayList<Missile>();
        ArrayList<Enemy> copiedEnemies = new ArrayList<Enemy>();
        copiedMissiles.addAll(missiles);
        copiedEnemies.addAll(enemies);
        for (Missile m : copiedMissiles) {
            for (Enemy e : copiedEnemies) {
                if (e.beingHitOrNot(m)) {
                    missiles.remove(m);
                    e.setHp((e.getHp() - 1));  //change the constant to change the power of a missile
                    if (e.getHp() == 0) {
                        enemies.remove(e);
                        score += 1; //change the constant to change the score of each hitting
                    }
                }
            }
        }
    }
}

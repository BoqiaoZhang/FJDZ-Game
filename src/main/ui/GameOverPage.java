package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.StartPage.start;

public class GameOverPage extends JFrame implements ActionListener {
    //ImageIcon newGameImage;
    //ImageIcon exitGameImage;
    ImageIcon backgroundImg;
    JLabel lblBackground;
    JButton btnExit;
    JButton btnNewGame;
    JLabel lblGameOver;
    int score;
    JLabel lblScore;

    public GameOverPage(int score) {
        setSize(512,768);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.score = score;

        setLabels();
        setButtons(); // Have to first setButtons, otherwise the bgImg will screen out everything
        setBackgroundImage();

        setVisible(true);
    }

    //When setting up backgroundImage, we also initialize the lblBackground
    private void setBackgroundImage() {
        backgroundImg = new ImageIcon("./images/bg2.jpg") ;
        lblBackground = new JLabel(backgroundImg);
        lblBackground.setBounds(0,0,512,768); //normal size
        add(lblBackground);
    }

    //EFFECTS: setting up all the buttons
    private void setButtons() {
        btnExit = new JButton("Exit");
        btnExit.setForeground(Color.white);
        btnExit.setFont(new Font("Serif", Font.PLAIN, 40));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnExit.setBounds(90,350,300,50);

        btnExit.setOpaque(false);               //These three lines: setting the background of a button transparent
        btnExit.setContentAreaFilled(false);
        btnExit.setBorderPainted(false);

        add(btnExit);

        btnNewGame = new JButton("Restart");
        btnNewGame.setForeground(Color.white);
        btnNewGame.setFont(new Font("Serif", Font.PLAIN, 40));
        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                start();
            }
        });
        btnNewGame.setBounds(97,450,300,50);

        btnNewGame.setOpaque(false);               //These three lines: setting the background of a button transparent
        btnNewGame.setContentAreaFilled(false);
        btnNewGame.setBorderPainted(false);

        add(btnNewGame);
    }

    public void setLabels() {
        lblGameOver = new JLabel("Game Is Over");
        lblGameOver.setForeground(Color.white);
        lblGameOver.setFont(new Font("Serif", Font.PLAIN, 50));
        lblGameOver.setBounds(125,100,312,50);
        add(lblGameOver);

        lblScore = new JLabel("Your Score: " + Integer.toString(score));
        lblScore.setForeground(Color.white);
        lblScore.setFont(new Font("Serif", Font.PLAIN, 50));
        lblScore.setBounds(125,200,312,50);
        add(lblScore);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //default
    }

    public static void main(String[] args) {
        new GameOverPage(0);
    }

}

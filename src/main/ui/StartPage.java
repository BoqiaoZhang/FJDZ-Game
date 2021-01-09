package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage extends JFrame implements ActionListener {
    JLabel lblBackground;
    JButton btnStart;
    ImageIcon imgBackground;

    public StartPage() {
        init();
    }

    private void init() {
        setSize(512,768);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setButtons(); // Have to first setButtons, otherwise the bgImg will screen out everything
        setBackgroundImage();

        setVisible(true);
    }

    public void setBackgroundImage() {
        imgBackground = new ImageIcon("./images/bg2.jpg") ;
        lblBackground = new JLabel(imgBackground);
        lblBackground.setBounds(0,0,512,768); //normal size
        add(lblBackground);
    }

    public void setButtons() {
        btnStart = new JButton("Start");
        btnStart.setFont(new Font("Serif", Font.PLAIN, 40));
        btnStart.setForeground(Color.white);

        btnStart.setOpaque(false);               //These three lines: setting the background of a button transparent
        btnStart.setContentAreaFilled(false);
        btnStart.setBorderPainted(false);

        btnStart.setBounds(100,200,300,100);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
                setVisible(false);
            }
        });
        add(btnStart);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //default method
    }

    public static void start() {
        GameFrame frame = new GameFrame();
        GamePanel panel = new GamePanel(frame); //In convenience of adding keyAdapter

        frame.add(panel);
        frame.setVisible(true);

        panel.gameStarter();
    }
}

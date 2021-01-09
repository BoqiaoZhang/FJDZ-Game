package ui;

import javax.swing.*;

public class GameFrame extends JFrame {

//constructor: create a new gameFrame
    public GameFrame() {
        setTitle("fjdz");
        setSize(512,768); //normal size:(512*769), (1024*768), (1366*768)
        setLocationRelativeTo(null); //centered
        setResizable(false); //Do NOT allow users to reset the size of our main window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//set "Exit" button to entirely stop running the project
    }

}

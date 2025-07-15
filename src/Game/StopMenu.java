package Game;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

import java.awt.event.*;


public class StopMenu {
        public void StopMenu(String difficulty){
            int boardWidth = 600;
            int boardHeight = 650;

            JFrame stopMenu = new JFrame("Stop Menu");
            stopMenu.setSize(boardWidth, boardHeight);
            stopMenu.setLocationRelativeTo(null);
            stopMenu.setResizable(false);
            stopMenu.setLayout(null);

            ImageIcon backgroundImage = new ImageIcon("src/Game/minesweeper.jpg");

            JPanel backgroundPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            };
            backgroundPanel.setLayout(null);
            stopMenu.setContentPane(backgroundPanel);
                
            Color buttonColor = Color.decode("#3CA392");

            JButton resume = new JButton("Resume");
            resume.setBounds(240,225,120,40);
            resume.setBackground(buttonColor);
            resume.setForeground(Color.WHITE);
            
            JButton restart = new JButton("Restart");
            restart.setBounds(240, 305, 120, 40);
            restart.setBackground(buttonColor);
            restart.setForeground(Color.WHITE);
            
            JButton mainMenu = new JButton("Main Menu");
            mainMenu.setBounds(240, 385, 120, 40);
            mainMenu.setBackground(buttonColor);
            mainMenu.setForeground(Color.WHITE);
            
            stopMenu.add(resume);
            stopMenu.add(restart);
            stopMenu.add(mainMenu);

            stopMenu.setVisible(true);

            resume.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    stopMenu.dispose();
                }
            });

            restart.addActionListener(new ActionListener(){
                @SuppressWarnings("unused")
                public void actionPerformed(ActionEvent e){
                    stopMenu.dispose();
                    minesweeper.gameFrame.dispose();
                    minesweeper minesweeper = new minesweeper(difficulty);
                }
            });

            mainMenu.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    stopMenu.dispose();
                    minesweeper.gameFrame.dispose();
                }
            });

            stopMenu.setContentPane(backgroundPanel);
            stopMenu.setVisible(true);
        }

}

package Game;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

public class App {
    public static void main(String[] args){

        //framesize
        int boardWidth = 600;
        int boardHeight = 650;

        // mainframe
        JFrame mainMenu = new JFrame("minesweeper");
        mainMenu.setSize(boardWidth, boardHeight);
        mainMenu.setLocationRelativeTo(null);
        mainMenu.setResizable(false);
        mainMenu.setLayout(null);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setVisible(true);

        // backgroundImage
        ImageIcon backgroundImage = new ImageIcon("src/Game/minesweeper.jpg");
        JPanel backgroundPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        mainMenu.setContentPane(backgroundPanel);

        // btn color
        Color btnColor = Color.decode("#3CA392");
        JButton play = new JButton("Play");
        play.setBounds(240,225,120,40);
        play.setBackground(btnColor);
        play.setForeground(Color.WHITE);
        
        JButton difficulty = new JButton("Level");
        difficulty.setBounds(240, 305, 120, 40);
        difficulty.setBackground(btnColor);
        difficulty.setForeground(Color.WHITE);
        
        JButton exit = new JButton("Exit");
        exit.setBounds(240, 385, 120, 40);
        exit.setBackground(btnColor);
        exit.setForeground(Color.WHITE);

        mainMenu.add(play);
        mainMenu.add(difficulty);
        mainMenu.add(exit);

        play.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                @SuppressWarnings("unused")
                //runs easy by default
                minesweeper minesweeper = new minesweeper("Easy");
                }
        });

        difficulty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JFrame difficultyFrame = new JFrame("Select Difficulty");
                difficultyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                difficultyFrame.setSize(300, 150);
                difficultyFrame.setResizable(false);
                difficultyFrame.setLocationRelativeTo(mainMenu);
                difficultyFrame.setLayout(null);
                difficultyFrame.setVisible(true);

                JLabel difficultyLabel = new JLabel("Choose difficulty level:");
                difficultyLabel.setBounds(50, 20, 200, 20);
                
                String[] difficultyLevels = {"Easy", "Medium", "Hard"};
                JComboBox<String> comboBox = new JComboBox<>(difficultyLevels);
                comboBox.setBounds(50, 50, 200, 20);
                
                JButton ok = new JButton("OK");
                ok.setBounds(100, 80, 80, 25);

                difficultyFrame.add(difficultyLabel);
                difficultyFrame.add(comboBox);
                difficultyFrame.add(ok);

                ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //typecasting the selected item into string (String)
                        String selectedDifficulty = (String) comboBox.getSelectedItem();
                        
                        if(selectedDifficulty.equals("Easy")){
                            @SuppressWarnings("unused")
                            minesweeper minesweeper = new minesweeper("Easy");
                        }else if(selectedDifficulty.equals("Medium")){
                            @SuppressWarnings("unused")
                            minesweeper minesweeper = new minesweeper("Medium");
                        }else if(selectedDifficulty.equals("Hard")){
                            @SuppressWarnings("unused")
                            minesweeper minesweeper = new minesweeper("Hard");
                        }
                        difficultyFrame.dispose();
                    }
                });
            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                mainMenu.dispose();
            }
        });

        mainMenu.setContentPane(backgroundPanel);
        mainMenu.setVisible(true);
    }
}
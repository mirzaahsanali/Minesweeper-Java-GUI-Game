package Game;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

//Abstraction
abstract class prototype{
    // Polymorphism (Overriding)
    public void initializeUI(){
        //Overriden below
    }; 
    public void setUpGame(){
        //Overriden below
    }; 
    public void setMines(){
        //Overriden below
    };
    public void revealMines(){
        //Overriden below
    };
    public void checkMine(){
        //Overriden below
    }; 
    public void mousePressed(){
        //Overriden below
    };
}

//inheritance
public class minesweeper extends prototype{
    private class Tile extends JButton {
        int row;
        int col;

        //minetile constrctor called when a tile is created
        Tile(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static JFrame gameFrame;
    private JLabel textLabel;
    private JPanel textPanel;
    private JPanel gridPanel;

    private ArrayList<Tile> mineList;
    private Random random;
    private int numRows;
    private int numCols;
    private int mineCount;
    private int boardWidth;
    private int boardHeight;
    private int tilesClicked;
    public String difficulty;
    private boolean gameOver;

    minesweeper(String difficulty) {
        if(difficulty.equals("Easy")){
            this.numRows = 5;
            this.numCols = 5;
            this.mineCount = 3;
            this.boardWidth = 600;
            this.boardHeight = 650;
            this.difficulty = difficulty;
        }else if(difficulty.equals("Medium")){
            this.numRows = 10;
            this.numCols = 10;
            this.mineCount = 10;
            this.boardWidth = 600;
            this.boardHeight = 650;
            this.difficulty = difficulty;
        }else if(difficulty.equals("Hard")){
            this.numRows = 15;
            this.numCols = 15;
            this.mineCount = 15;
            this.boardWidth = 600;
            this.boardHeight = 650;
            this.difficulty = difficulty;
        }
        initializeUI();
        setUpGame();
    }

    public void initializeUI() {
        gameFrame = new JFrame("Minesweeper");
        gameFrame.setSize(boardWidth, boardHeight);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textLabel = new JLabel("Minesweeper: " + mineCount);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton menu = new JButton("Menu");
        menu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                StopMenu stopMenu = new StopMenu();
                stopMenu.StopMenu(difficulty);
            }
        });
        
        textPanel = new JPanel(new BorderLayout());
        textPanel.add(textLabel, BorderLayout.CENTER);
        textPanel.add(menu, BorderLayout.EAST);

        gridPanel = new JPanel(new GridLayout(numRows, numCols));
        gameFrame.add(textPanel, BorderLayout.NORTH);
        gameFrame.add(gridPanel);

        random = new Random();
        mineList = new ArrayList<>();
    }

    

    public void setUpGame() {
        tilesClicked = 0;
        gameOver = false;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Tile tile = new Tile(row, col);
                tile.setFocusable(false);
                tile.setMargin(new Insets(0, 0, 0, 0));
                tile.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30));
                tile.addMouseListener(new TilePressed());
                gridPanel.add(tile);
            }
        }
        gameFrame.setVisible(true);
        setMines();
    }

    public void setMines() {
        // mineCount
        // is 3 for Easy
        // is 5 for Medium
        // is 10 for Hard
        int mineLeft = mineCount;
        while (mineLeft > 0) {
            int row = random.nextInt(numRows);
            int col = random.nextInt(numCols);

            Tile bombTile = (Tile) gridPanel.getComponent(row * numCols + col);
            if (!mineList.contains(bombTile)) {
                mineList.add(bombTile);
                mineLeft--;
            }
        }
    }

    public void revealMines() {
        for (Tile tile : mineList) {
            tile.setText("💣");
        }

        gameOver = true;
        textLabel.setText("Game Over!");
    }

    public void checkMine(int row, int col) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
            return;
        }
        Tile tile = (Tile) gridPanel.getComponent(row * numCols + col);
        if (!tile.isEnabled()) {
            return;
        }
        tile.setEnabled(false);
        tilesClicked++;
        int minesFound = 0;
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int i = 0; i < directions.length; i++) {
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols) {
                if (mineList.contains(gridPanel.getComponent(newRow * numCols + newCol))) {
                    minesFound++;
                }
            }
        }

        if (minesFound > 0) {
            tile.setText(Integer.toString(minesFound));
        } else {
            tile.setText("");

            for (int i = 0; i < directions.length; i++) {
                int newRow = row + directions[i][0];
                int newCol = col + directions[i][1];
                checkMine(newRow, newCol);
            }
        }

        if (tilesClicked == numRows * numCols - mineList.size()) {
            gameOver = true;
            textLabel.setText("Mines Cleared!");
        }
    }

    private class TilePressed extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (gameOver) {
                return;
            }

            Tile tile = (Tile) e.getSource();
            if (SwingUtilities.isLeftMouseButton(e)) {
                if (tile.getText()=="") {
                    if (mineList.contains(tile)) {
                        revealMines();
                    } else {
                        checkMine(tile.row, tile.col);
                    }
                }
            } else if (SwingUtilities.isRightMouseButton(e)) {
                if (tile.getText()=="" && tile.isEnabled()) {
                    tile.setText("🚩");
                } else if (tile.getText().equals("🚩")) {
                    tile.setText("");
                }
            }
        }
    }
}

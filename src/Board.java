
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alu20925473g
 */
public class Board extends JPanel implements ActionListener {

    private class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:

                    keyMemory.add(DirectionType.LEFT);
                    keyPressed = 0;

                    if (canTurn) {

                        if (!keyMemory.isEmpty()) {
                            if (keyMemory.get(0) != null && keyMemory.get(0) != Util.getOpositeDirection(direction)) {
                                direction = keyMemory.get(0);

                                canTurn = false;
                            }
                            keyMemory.remove(0);
                        }
                    }

                    break;
                case KeyEvent.VK_RIGHT:
                    keyMemory.add(DirectionType.RIGHT);
                    keyPressed = 0;

                    if (canTurn) {

                        if (!keyMemory.isEmpty()) {
                            if (keyMemory.get(0) != null && keyMemory.get(0) != Util.getOpositeDirection(direction)) {
                                direction = keyMemory.get(0);

                                canTurn = false;
                            }
                            keyMemory.remove(0);
                        }
                    }
                    break;
                case KeyEvent.VK_SPACE:

                    break;

                case KeyEvent.VK_UP:
                    keyMemory.add(DirectionType.UP);
                    keyPressed = 0;
                    if (canTurn) {

                        if (!keyMemory.isEmpty()) {
                            if (keyMemory.get(0) != null && keyMemory.get(0) != Util.getOpositeDirection(direction)) {
                                direction = keyMemory.get(0);

                                canTurn = false;
                            }
                            keyMemory.remove(0);
                        }
                    }

                    break;
                case KeyEvent.VK_DOWN:
                    keyPressed = 0;
                    keyMemory.add(DirectionType.DOWN);
                    if (canTurn) {

                        if (!keyMemory.isEmpty()) {
                            if (keyMemory.get(0) != null && keyMemory.get(0) != Util.getOpositeDirection(direction)) {
                                direction = keyMemory.get(0);

                                canTurn = false;
                            }
                            keyMemory.remove(0);
                        }
                    }

                    break;
                case KeyEvent.VK_P:
                    if (isPlaying) {

                        if (timer.isRunning()) {
                            timer.stop();

                            MenuPause menuPause = new MenuPause(parentFrame, true, (int) (getWidth() / 2.5) + getLocationOnScreen().x, getHeight() / 2 + getLocationOnScreen().y - 50);
                            menuPause.setVisible(true);
                            timer.start();
                        }
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if (!timer.isRunning()) {
                        initGame();
                    }
                    break;
                default:
                    if (canTurn) {

                        if (!keyMemory.isEmpty()) {
                            if (keyMemory.get(0) != null && keyMemory.get(0) != Util.getOpositeDirection(direction)) {
                                direction = keyMemory.get(0);

                                canTurn = false;
                            }
                            keyMemory.remove(0);
                        }
                    }

                    break;
            }

        }

    }
    private JFrame parentFrame;

    public void setParentFrame(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    public int getNum_rows() {
        return num_rows;
    }

    public void setNum_rows(int num_rows) {
        this.num_rows = num_rows;
    }

    public int getNum_cols() {
        return num_cols;
    }

    public void setNum_cols(int num_cols) {
        this.num_cols = num_cols;
    }
    private ArrayList<DirectionType> keyMemory;
    private boolean canTurn;
    private int num_rows = 0;
    private int num_cols = 0;
    private DirectionType direction;
    private int deltaTime;
    private int currentCol;
    private int currentRow;
    private boolean isPlaying;
    private Food currentFood;
    private Timer timer;
    private Snake snake;
    private Food food;
    private SpecialFood specialFood;
    private int foodGenerator;
    private IncrementScorer scorerDelegate;
    private int keyPressed;

    private Ghost ghost;

    public Board() {
        super();
        BoardSize();

        initValues();
        timer = new Timer(deltaTime, this);
        snake = null;
        MyKeyAdapter keyb = new MyKeyAdapter();
        addKeyListener(keyb);
        currentFood = null;
        ghost = null;
        specialFood = null;
        canTurn = true;
        keyMemory = new ArrayList<DirectionType>();
        

    }
    private void drawBoard(Graphics g){
         Image image=null;
        try {
            image = ImageIO.read(getClass().getClassLoader().getResource("resources/bg.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Util.drawImage(g, new Node(0, 0), image,getWidth(), getHeight());
    }

    private void BoardSize() {
        ChoseBoardSize d = new ChoseBoardSize(parentFrame, true);
        d.setVisible(true);
        num_cols = d.getNumCols();
        num_rows = d.getNumRows();
        

       
        specialFood = null;

    }

    public void setScorer(IncrementScorer scorer) {
        this.scorerDelegate = scorer;
    }

    public void initValues() {
        setFocusable(true);

        deltaTime = 200;
        direction = DirectionType.RIGHT;

    }

    public boolean canMove(DirectionType direction) {
        Node nextMove = snake.nextMove(direction);
        ArrayList<Node> bodysnake = snake.getNodes();
        for (Node body : bodysnake) {
            if (body.isEqual(nextMove)) {
                return false;
            }
        }
        if (nextMove.col < 0 || nextMove.col >= num_cols
                || nextMove.row < 0 || nextMove.row >= num_rows) {
            return false;
        }
        if (snake.getHead().isEqual(ghost.getGhostPosition())) {
            return false;
        }
        return true;
    }

    public void initGame() {

        foodGenerator = 0;
        direction = DirectionType.RIGHT;
        snake = new Snake(new Node(num_rows / 2, num_cols / 2));
        isPlaying = true;
        scorerDelegate.reset();
        keyPressed = 0;
        keyMemory = new ArrayList<DirectionType>();
        timer.start();
        currentFood = new Food(snake, num_rows, num_cols);
        ghost = new Ghost(snake, num_rows, num_cols, currentFood, specialFood);

    }

    //Game Main Loop
    @Override
    public void actionPerformed(ActionEvent ae) {

        ghostHasEaten();
        canTurn = true;

        generateFood();

        if (canMove(direction)) {
            snake.moveTo(direction, hasEaten());
            ghost.moveGhost();

        } else {
            try {
                gameOver();
            } catch (InterruptedException ex) {
                System.err.println();
            }

        }

        if (keyPressed == 2) {
            keyMemory.clear();
            keyPressed = 0;
        }
        keyPressed++;

        repaint();
        Toolkit.getDefaultToolkit().sync();

    }

    public void ghostHasEaten() {

        if (currentFood != null && currentFood.getFoodPosition().isEqual(ghost.getGhostPosition())) {
            currentFood = null;
            scorerDelegate.increment(-12);

        }

        if (specialFood != null && specialFood.getFoodPosition().isEqual(ghost.getGhostPosition())) {
            specialFood = null;
            scorerDelegate.increment(-50);

        }

    }

    public boolean hasEaten() {

        if (currentFood != null && currentFood.getFoodPosition().isEqual(snake.getHead())) {
            currentFood = null;
            scorerDelegate.increment(12);
            if (scorerDelegate.getScore() > scorerDelegate.getLevel() * 50) {
                scorerDelegate.incrementLevel();
                decrementDelay();
            }

            return true;
        }

        if (specialFood != null && specialFood.getFoodPosition().isEqual(snake.getHead())) {
            specialFood = null;
            scorerDelegate.increment(50);
            if (scorerDelegate.getScore() > scorerDelegate.getLevel() * 500) {
                scorerDelegate.incrementLevel();
                decrementDelay();
            }

            return true;
        }

        return false;
    }

    public void decrementDelay() {
        deltaTime *= 0.8;
        timer.setDelay(deltaTime);
    }

    public void generateFood() {
        if (foodGenerator % 5 == 0 && currentFood == null && specialFood == null) {
            specialFood = new SpecialFood(snake, num_rows, num_cols);
        } else {
            if (currentFood == null && specialFood == null) {
                currentFood = new Food(snake, num_rows, num_cols);
            }
        }

        foodGenerator++;
        ghost.setFoods(currentFood, specialFood);
    }

    public void gameOver() throws InterruptedException {
        timer.stop();
        scorerDelegate.paintFinalScore();

        RecordsDialog d = new RecordsDialog(parentFrame, true, scorerDelegate.getScore());
        Thread.sleep(1000);
        d.setVisible(true);
        deltaTime = 200;
        timer.setDelay(deltaTime);
        BoardSize();
        initGame();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //drawBoard(g);
        drawBoard(g);
        if (currentFood != null) {
            currentFood.draw(g, squareWidth(), squareHeight());
        }
        if (specialFood != null) {
            specialFood.draw(g, squareWidth(), squareHeight());
        }
        if (snake != null) {

            snake.draw(g, squareWidth(), squareHeight(),direction);

        }
        
        if (ghost != null) {
            ghost.draw(g, squareWidth(), squareHeight());
        }
        
        //drawBorder(g);
    }

    public void drawBorder(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(0, 0, num_cols * squareWidth(), num_rows * squareHeight());
    }

    private int squareWidth() {
        return getWidth() / num_cols;
    }

    private int squareHeight() {
        return getHeight() / num_rows;
    }

}

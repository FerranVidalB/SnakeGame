
import java.awt.Color;
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alu20925473g
 */
public class Ghost {

    private Node position;
    private Snake snake;
    private int totalRows;
    private int totalCols;

    public Ghost(Snake snake, int totalRows, int totalCols) {
        this.snake = snake;
        this.totalCols = totalCols;
        this.totalRows = totalRows;

        boolean colisionWithSnake = true;

        while (colisionWithSnake) {
            colisionWithSnake = false;
            int row = (int) (Math.random() * totalRows);
            int col = (int) (Math.random() * totalCols);
            position = new Node(row, col);
            for (DirectionType direction : DirectionType.values()) {
                if (snake.getHead().equals(snake.nextMove(direction))) {
                    colisionWithSnake = true;
                }
            }

            for (Node body : snake.getNodes()) {
                if (body.isEqual(position)) {
                    colisionWithSnake = true;
                }
            }

        }

    }

    public void moveGhost() {

        boolean colisionWithSnake = true;

        while (colisionWithSnake) {
            colisionWithSnake = false;
            int row = (int) (Math.random() * 3);
            int col = (int) (Math.random() * 3);
            int pos = (int) (Math.random() * 2);

            if (pos == 0) {
                if ((position.getCol() + col - 1) > 0 && (position.getCol() + col - 1) < totalCols) {
                    position.fuseCols(col - 1);
                }
            } else {
                if ((position.getRow() + row - 1) > 0 && (position.getRow() + row - 1) < totalRows) {
                    position.fuseRows(row - 1);
                }
            }

            for (Node body : snake.getNodes()) {
                if (body.isEqual(position)) {
                    colisionWithSnake = true;
                }
            }
            
            
            if (colisionWithSnake) {
                if (pos == 0) {
                    if ((position.getCol() + col - 1) > 0 && (position.getCol() + col - 1) < totalCols) {
                        position.revertFuseCols(col - 1);
                    }
                } else {
                    if ((position.getRow() + row - 1) > 0 && (position.getRow() + row - 1) < totalRows) {
                        position.revertFuseRows(row - 1);
                    }
                }
            }
            if(snake.getHead().isEqual(position)){
                colisionWithSnake=false;
            }
          

        }

    }

    public Node getGhostPosition() {
        return position;
    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {

        Util.drawSquare(g, position, Color.BLUE, squareWidth, squareHeight);

    }
}

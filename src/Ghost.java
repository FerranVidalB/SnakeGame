
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
    private Food food;
    private SpecialFood specialFood;
    
    public Ghost(Snake snake, int totalRows, int totalCols, Food food, SpecialFood specialFood) {
        this.snake = snake;
        this.totalCols = totalCols;
        this.totalRows = totalRows;
        this.food = food;
        this.specialFood = specialFood;
        
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

    private Node getFoodPosition() {
        Node foodPosition = null;
        
        if (food != null) {
            foodPosition = food.getFoodPosition();
        } else {
            if (specialFood != null) {
                foodPosition = specialFood.getFoodPosition();
            }
        }
        return foodPosition;
    }

    private int getIncrementRow(Node foodPosition) {
        int row;
        if (foodPosition.getRow() > position.getRow()) {
            row = 1;
        } else {
            if (foodPosition.getRow() < position.getRow()) {
                row = -1;
            } else {
                row = 0;
            }
        }
        return row;
    }

    private int getIncrementCol(Node foodPosition) {
        int col;
        if (foodPosition.getCol() > position.getCol()) {
            col = 1;
        } else {
            if (foodPosition.getCol() < position.getCol()) {
                col = -1;
            } else {
                col = 0;
            }
        }
        return col;
    }

    private void changePosition(int row, int col, int par) {
        if (par == 0) {
            if ((position.getCol() + col) >= 0 && (position.getCol() + col) < totalCols) {
                position.fuseCols(col);
            }
        } else {
            if ((position.getRow() + row) >= 0 && (position.getRow() + row) < totalRows) {
                position.fuseRows(row);
            }
        }
    }

    private void revertPosition(int row, int col, int par) {
        if (par == 0) {
            if ((position.getCol() + col) >= 0 && (position.getCol() + col) < totalCols) {
                position.revertFuseCols(col);
            }
        } else {
            if ((position.getRow() + row) >= 0 && (position.getRow() + row) < totalRows) {
                position.revertFuseRows(row);
            }
        }
    }
    
    public void moveGhost() {
        
        boolean colisionWithSnake = true;
        
        while (colisionWithSnake) {
            colisionWithSnake = false;
            
            Node foodPosition = getFoodPosition();
            
            int row = getIncrementRow(foodPosition);
            int col = getIncrementCol(foodPosition);
            
            int noMove = (int) (Math.random() * 20);
            if (noMove == 8) {
                row = 0;
                col = 0;
            }
            int par = (int) (Math.random() * 2);
            changePosition(row, col, par);
            
            for (Node body : snake.getNodes()) {
                if (body.isEqual(position)) {
                    colisionWithSnake = true;
                }
            }
            
            if (colisionWithSnake) {
                revertPosition(row, col, par);
            }
            if (snake.getHead().isEqual(position)) {
                colisionWithSnake = false;
            }
            
        }
        
    }
    
    public Node getGhostPosition() {
        return position;
    }
    
    public void setFoods(Food food, SpecialFood specialFood) {
        this.food = food;
        this.specialFood = specialFood;
    }
    
    public void draw(Graphics g, int squareWidth, int squareHeight) {
        
        Util.drawSquare(g, position, Color.BLUE, squareWidth, squareHeight);
        
    }
}

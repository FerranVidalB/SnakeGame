
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

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
    private int id;
    private Ghost[]otherGhosts;

    public Ghost(Node node, Snake snake, int rows, int cols,int id) {
        this.id=id;
        this.snake=snake;
        totalRows=rows;
        totalCols=cols;
        position = node;
    }
    public int getId(){
        return id;
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
    public void setOtherGhosts(Ghost...ghosts){
    otherGhosts=ghosts;
}
    

    public void moveGhost() {

        boolean colision = true;

        while (colision) {
            colision = false;

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
                        colision = true;
                    }
                }
                for(Ghost g:otherGhosts){
                    if(g.getId()!=id){
                        if(g.getGhostPosition().isEqual(position)){
                            colision=true;
                        }
                    }
                }
            

            if (colision) {
                revertPosition(row, col, par);
            }
            
                if (snake.getHead().isEqual(position)) {
                    colision = false;
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
        Image image = null;
        try {
            image = ImageIO.read(getClass().getClassLoader().getResource("resources/ghost"+id+".png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Util.drawImage(g, position, image, squareWidth, squareHeight);

    }
}

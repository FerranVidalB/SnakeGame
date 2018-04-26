
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
public class Food {

    private Node position;
   

    public Food(Snake snake, int totalRows, int totalCols) {
        boolean colisionWithSnake = true;

        while (colisionWithSnake) {
            colisionWithSnake = false;
            int row = (int) (Math.random() * totalRows);
            int col = (int) (Math.random() * totalCols);
            position = new Node(row, col);
            
            for (Node body : snake.getNodes()) {
                if (body.isEqual(position)) {
                    colisionWithSnake = true;
                }
            }

        }
        

    }
    public Node getFoodPosition(){
        return position;
    }
    public void draw(Graphics g, int squareWidth, int squareHeight) {
      
                Util.drawSquare(g, position, Color.GREEN, squareWidth, squareHeight);
    

    }
}


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.Random;
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
public class SpecialFood {

  
    private int paint;

    private Node position;

    public SpecialFood(Snake snake, int totalRows, int totalCols) {
        paint =1;
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

    public Node getFoodPosition() {
        return position;
    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {
        Image image=null;
        try {
            image = ImageIO.read(getClass().getClassLoader().getResource("resources/sfood"+paint+".png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
           
        Util.drawImage(g, position, image, squareWidth, squareHeight);
        paint++;
        if(paint==4){
            paint =1;
        }

    }

}

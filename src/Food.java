
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
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
public class Food {

    private Node position;

    public Food(Snake snake, int totalRows, int totalCols, Ghost... ghosts) {
        boolean colision = true;

        while (colision) {
            colision = false;
            int row = (int) (Math.random() * totalRows);
            int col = (int) (Math.random() * totalCols);
            position = new Node(row, col);

            for (Node body : snake.getNodes()) {
                if (body.isEqual(position)) {
                    colision = true;
                }
            }
            for (Ghost g : ghosts) {
                if (g != null) {
                    if (g.getGhostPosition().isEqual(position)) {
                        colision = true;
                    }
                }
            }

        }

    }

    public Node getFoodPosition() {
        return position;
    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {
        Image image = null;
        try {
            image = ImageIO.read(getClass().getClassLoader().getResource("resources/food.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Util.drawImage(g, position, image, squareWidth, squareHeight);

    }
}

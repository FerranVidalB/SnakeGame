
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
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
public class Util {

    public static void drawSquare(Graphics g, Node node, Color color, int squareWidth, int squareHeight) {

        int x = node.col * squareWidth;
        int y = node.row * squareHeight;

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth - 2, squareHeight - 2);
        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight - 1, x, y);
        g.drawLine(x, y, x + squareWidth - 1, y);
        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight - 1,
                x + squareWidth - 1, y + squareHeight - 1);
        g.drawLine(x + squareWidth - 1,
                y + squareHeight - 1,
                x + squareWidth - 1, y + 1);
    }

    public static DirectionType getOpositeDirection(DirectionType direction) {
        switch (direction) {
            case DOWN:
                return DirectionType.UP;
            case UP:
                return DirectionType.DOWN;
            case RIGHT:
                return DirectionType.LEFT;
            case LEFT:
                return DirectionType.RIGHT;

        }
        return null;
    }
    public static void drawImage(Graphics g, Node node, Image image, int squareWidth, int squareHeight) {

        int x = node.col * squareWidth;
        int y = node.row * squareHeight;
        g.drawImage(image, x, y, squareWidth, squareHeight, null);
       
     
    }
  
}


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alu20925473g
 */
public class Snake {

    private ArrayList<Node> body;

    public Snake(Node start) {
        body = new ArrayList<Node>();
        initSnake(start);
    }

    public void initSnake(Node start) {
        body.add(new Node(start.row, start.col));
        body.add(new Node(start.row, start.col - 1));
        body.add(new Node(start.row, start.col - 2));

    }

    public ArrayList<Node> getNodes() {
        return body;
    }

    public void moveTo(DirectionType direction) {
        Node head=body.get(0);
        switch (direction) {
            case RIGHT:
                body.add(0, head.incrementCol());
                break;
            case LEFT:
                break;
            case UP:
                break;
            case DOWN:
                break;

        }
    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {
        int i = 0;
        for (Node part : body) {
            if (i == 0) {
                Util.drawSquare(g, part, Color.RED, squareWidth, squareHeight);
            } else {
                Util.drawSquare(g, part, Color.PINK, squareWidth, squareHeight);
            }
            i++;

        }

    }
}

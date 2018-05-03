
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

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
    private HashMap<Node,DirectionType> body1;
    private int increaseLength;

    public Snake(Node start) {
        body = new ArrayList<Node>();
        increaseLength=0;
        initSnake(start);
    }

    public void initSnake(Node start) {
        
        body.add(new Node(start.row, start.col));
        body.add(new Node(start.row, start.col - 1));
        body.add(new Node(start.row, start.col - 2));
        body.add(new Node(start.row, start.col - 3));
        body.add(new Node(start.row, start.col - 4));

    }

    public ArrayList<Node> getNodes() {
        return body;
    }
    public Node getHead(){
        return body.get(0);
    }

    public void moveTo(DirectionType direction, boolean increase) {
        if(increase){
        increaseLength+=4;
        }
        Node head = body.get(0);
        switch (direction) {
            case RIGHT:
                body.add(0, new Node(head.row, head.col + 1));
               

                break;
            case LEFT:
                body.add(0, new Node(head.row, head.col - 1));
                
                break;
            case UP:
                body.add(0, new Node(head.row - 1, head.col));
                
                break;
            case DOWN:
                body.add(0, new Node(head.row + 1, head.col));
                
                break;

        }
        if(increaseLength==0){
         body.remove(body.size() - 1);
        }else{
            increaseLength--;
        }
    }
     

    public Node nextMove(DirectionType direction) {
        Node head = body.get(0);
        switch (direction) {
            case RIGHT:
                return new Node(head.row, head.col + 1);

            case LEFT:
                return new Node(head.row, head.col - 1);

            case UP:
                return new Node(head.row - 1, head.col);

            case DOWN:
                return new Node(head.row + 1, head.col);

        }
        return null;
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

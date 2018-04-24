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
            int row = (int) (Math.random() * totalRows + 1);
            int col = (int) (Math.random() * totalCols + 1);
            position = new Node(row, col);
            for (Node body : snake.getNodes()) {
                if (!body.isEqual(position)) {
                    colisionWithSnake = true;
                }
            }

        }
        

    }
}

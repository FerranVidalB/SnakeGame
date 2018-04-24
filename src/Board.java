
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alu20925473g
 */
public class Board extends JPanel implements ActionListener {

    
    
    private int num_rows = 30;

    public int getNum_rows() {
        return num_rows;
    }

    public void setNum_rows(int num_rows) {
        this.num_rows = num_rows;
    }

    public int getNum_cols() {
        return num_cols;
    }

    public void setNum_cols(int num_cols) {
        this.num_cols = num_cols;
    }
    private int num_cols = 40;
    private DirectionType direction;
    private int deltatime;
    private int currentCol;
    private int currentRow;;
  
    private Timer timer;
    private Snake snake;
    private Food food;
    
    public Board(){
        super();
        initValues();
        snake = new Snake(new Node(num_rows/2, num_cols/2));
        timer = new Timer(deltatime,this);
        
        
    }
    public void initValues(){
        deltatime=500;
        direction = DirectionType.RIGHT;
        
    }
    //Game Main Loop
    @Override
    public void actionPerformed(ActionEvent ae) {
       snake.moveTo(direction);
        
        
        
        
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //drawBoard(g);
        if (snake != null) {
            
            snake.draw(g, squareWidth(), squareHeight());
          
        }
        //drawBorder(g);
    }
    
    
    private int squareWidth() {
        return getWidth() / num_cols;
    }

    private int squareHeight() {
        return getHeight() / num_rows;
    }
}

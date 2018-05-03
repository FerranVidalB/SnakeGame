/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alu20925473g
 */
public class Node  {
    public int row;
    public int col;
    
    public Node(int row, int col){
        this.row=row;
        this.col=col;
    }
    public int getCol(){
        return col;
    }
    public int getRow(){
        return row;
    }
    
    public Node decrementCol(){
        this.col--;
        return new Node(row, col);
    }
    public Node incrementCol(){
        this.col++;
        return new Node(row, col);
    }
    public Node decrementRow(){
        this.row--;
        return new Node(row, col);
    }
    public Node incrementRow(){
        this.row++;
        return new Node(row, col);
    }
    public void fuseRows(int rowMove){
        this.row+=rowMove;
        
    }
    public void fuseCols(int colMove){
       
        this.col+=colMove;
    }
    public void revertFuseRows(int rowMove){
        this.row-=rowMove;
        
    }
    public void revertFuseCols(int colMove){
       
        this.col-=colMove;
    }

   
    public boolean isEqual(Node t) {
        if(t.row == row && t.col == col){
            return true;
        }
        return false;
    }
    
    
}

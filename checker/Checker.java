/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checker;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import java.io.*;
import java.util.*;

/**
 *
 * @author sincheng
 */
public class Checker extends JPanel{

public int lastx,lasty,currx,curry;
public int row,col;
boolean empty,grey,red;
public Color tileColor;
PositionState[][] state = new PositionState[8][8];

public Checker(){
        MyMouseListener ml = new MyMouseListener();
        addMouseListener(ml);
        addMouseMotionListener(ml);
}
public PositionState getState(int i, int j) { 
        return state[i][j];
    }
class PositionState {

    boolean empty;
    private boolean grey;
    boolean red;
    
    //Constructor
    PositionState(boolean empty, boolean grey, boolean red) {
        
        this.empty = empty;
        this.grey = grey;
        this.red = red;
    }


    public void setState(boolean empty , boolean grey, boolean red) {
        this.empty = empty;
        this.grey = grey;
        this.red = red;
        }
        }

public boolean legalMove(int lastx, int lasty, int currx, int curry) {
    // 1.Check if index in range, if not, return false.
	if (lastx < 0 || lastx > 7 || lasty < 0 || lasty > 7 ||
	    currx < 0 || currx > 7 || curry < 0 || curry > 7)
        return false;
    // 2. Check if move to empty position
	else if (getState(currx,curry).empty){

    // Check case of simple move
	    if (Math.abs(lastx-currx)==1) {
		if ((getState(lastx,lasty).grey) && (curry - lasty == 1))
                return true;
		else if ((getState(lastx,lasty).red) && (curry - lasty == -1))
                return true;
	    }
    // Check case of a jump
	    else if (Math.abs(lastx-currx)==2) {
		if ((getState(lastx,lasty).grey == true) && (curry - lasty == 2) &&
		    (getState((lastx+currx)/2,(lasty+curry)/2).red))
		    return true;
		else if ((getState(lastx,lasty).red == true) && (curry - lasty == -2) &&
		      (getState((lastx+currx)/2,(lasty+curry)/2).grey))
		    return true;
	    }

    }
     // Other cases are illegal moves
	return false;
}

            
    public void move(int lastx, int lasty, int currx, int curry){
        //Check simple move or jump;
        boolean isGrey = getState(lastx,lasty).grey;
        boolean isRed = getState(lastx,lasty).red;
        //simple move
        if (Math.abs(lastx-currx)==1){
            if (isRed) {
                state[lastx][lasty].setState(true,false,false);
                state[currx][curry].setState(false,false,true);
  

            }
            else if (isGrey){
                state[lastx][lasty].setState(true,false,false);
                state[currx][curry].setState(false,true,false);
        }
        }
        //jump
        else if (Math.abs(lastx-currx)==2){
            if(isRed){
              state[lastx][lasty].setState(true,false,false);
              state[(lastx+currx)/2][(lasty+curry)/2].setState(true,false,false);
              state[currx][curry].setState(false,false,true);
            }
            else if (isGrey){
              state[lastx][lasty].setState(true,false,false);
              state[(lastx+currx)/2][(lasty+curry)/2].setState(true,false,false);
              state[currx][curry].setState(false,true,false);
        }

        }

        System.out.println("Move Successfully!");
    
}

    
    public void paint(Graphics g) {
      //Draw the checker board
      for ( row = 0;  row < 8;  row++ )
      {
         for ( col = 0;  col < 8;  col++)
         {
             if ( (row % 2) == (col % 2) )
               g.setColor(Color.WHITE);
            else
               g.setColor(Color.BLACK);
            g.fillRect(col*50, row*50, 50, 50);
         }
      }  
        
          //fill the  chess
//         for (row =0; row<8; row++)
//         {
//            for (col = 0 ; col< 8 ; col++)
//            {
//   
//                x = col * 50 + 25; 
//                y = row *50 + 25;
//                if(col<3 && (row+col)%2==1){
//                    g.setColor(Color.LIGHT_GRAY);
//                    g.fillOval(row*50,col*50,50,50);   
//                }
//                else if(col>4&&(row+col)%2==1){
//                    g.setColor(Color.RED);
//                    g.fillOval(row*50,col*50,50,50);
//                }     
//            } 
//         }
            //Fill the chess depends on Position State
            for (row=0;row<8;row++)
            {
                for(col=0;col<8;col++)
                {
                    if (getState(row,col).grey){
                        g.setColor(Color.LIGHT_GRAY);
                        g.fillOval(row*50,col*50,50,50); 
                    }
                    else if (getState(row,col).red){
                        g.setColor(Color.RED);
                        g.fillOval(row*50,col*50,50,50);          
                    }                    
                            
                }
            }
  
    }
   
    public void fillposition(){
         for ( row =0;  row<8;  row++)
         {
            for (col = 0 ; col< 8 ; col++)
            {          
                if(col<3 && (row+col)%2==1){       
                    state[row][col] = new PositionState(false,true,false);
                }
                else if(col>4&&(row+col)%2==1)
                {
                    state[row][col]= new PositionState(false,false,true);
                }
                else 
                {
                    state[row][col]= new PositionState(true,false,false);
                }
                
        }
     }
     }
    


class MyMouseListener implements MouseListener, MouseMotionListener{
                
        boolean twoPoints = false;
        @Override
        public void mouseClicked(MouseEvent e) {
 
        }

        @Override
        public void mousePressed(MouseEvent e) {
            row = (int)(e.getX())/50;
            col = (int)(e.getY())/50;
            if(twoPoints==false){
                lastx=row;
                lasty=col;
                System.out.println("Selected X:"+lastx+" Y:"+lasty);
                twoPoints=true;
            } else{
                currx=row;
                curry=col;
                int xdiff = lastx-currx;
                int ydiff = curry - lasty;
                boolean legalmove = legalMove(lastx,lasty,currx,curry);
  //              System.out.println("Last X:"+lastx+" Y:"+lasty);
               System.out.println("Current X:"+currx+" Y:"+curry);

                System.out.println(legalmove);
                if (legalmove)
                    {   
                        System.out.println("This is a legal move!");
                        move(lastx,lasty,currx,curry);

                 }
                else{
                    System.out.println("Not a legal move! Try again!");
                }
                twoPoints=false;
                lastx=row;
                lasty=col;

//                System.out.println("Last X:"+lastx+" Y:"+lasty);
////                System.out.println("Current X:"+currx+" Y:"+curry);
//                System.out.println("Check empty: "+getState(currx,curry).empty);
//                System.out.println("Check grey: "+getState(lastx,lasty).grey);
//                System.out.println("Check red: "+getState(lastx,lasty).red);
//                System.out.println("Check move: "+ xdiff+ydiff);
//                System.out.println("Check middle" + getState((lastx+currx)/2, (lasty+curry)/2).grey);
//                System.out.println("Check middle" + getState((lastx+currx)/2, (lasty+curry)/2).red);
                //System.out.println(getState(row,col).red);

                                                

            }    
            repaint();
          }



        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
             
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
        
    }
    


}
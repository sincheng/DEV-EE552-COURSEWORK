/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checker;

import javax.swing.*;
import java.awt.*; 


/**
 *
 * @author sincheng
 */
//A class draw the checkerboard
public class board extends JPanel {
    
    int row,col,x,y;
    PositionState[][] state = new PositionState[8][8];
    
    public void paint(Graphics g) {
      //Draw the checker board
      for ( row = 0;  row < 8;  row++ )
      {
         for ( col = 0;  col < 8;  col++)
         {
            x = col * 50;
            y = row * 50;
             if ( (row % 2) == (col % 2) )
               g.setColor(Color.WHITE);
            else
               g.setColor(Color.BLACK);
            g.fillRect(x, y, 50, 50);
         }
      }  
     
          //fill the  chess
//         for (row =0; row<8; row++)
//         {
//            for (col = 0 ; col< 8 ; col++)
//            {
//                x = col * 50 + 25; 
//                y = row *50 + 25;
//                if(col<3 && (row+col)%2==1){
//                    g.setColor(Color.LIGHT_GRAY);
//                    g.fillOval(row*50,col*50,50,50); 
//        
//                    
//                }
//                else if(col>4&&(row+col)%2==1){
//                    g.setColor(Color.RED);
//                    g.fillOval(row*50,col*50,50,50);
//               
//                }
//
//            } 
//         }
  
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
     
}   



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checker;

import java.awt.Color;


/**
 *
 * @author sincheng
 */
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
     




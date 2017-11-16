/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checker;

/**
 *
 * @author sincheng
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.event.*;

public class main extends JFrame {
	public main() {
        JFrame f=new JFrame(); 
	Checker cb = new Checker(); 
        f.add(cb);
        f.setTitle("CheckerBoard");
        f.setSize(400, 425);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setVisible(true);
        cb.fillposition();

	}
	
	
	public static void main(String[] args) {
		new main();
	}
}

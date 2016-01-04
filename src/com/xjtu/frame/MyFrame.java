package com.xjtu.frame;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.xjtu.Panel.MyPanel;

public class MyFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 221L;

	public MyFrame(){
		this.setTitle("¶·µØÖ÷");
		Container c = this.getContentPane();
		c.add(new MyPanel());
		
		this.setBounds(100,100,1600,900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		MyFrame win = new MyFrame();
		win.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}
	
	
}
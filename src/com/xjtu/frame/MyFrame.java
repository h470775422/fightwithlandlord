package com.xjtu.frame;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.xjtu.Panel.MainPanel;
import com.xjtu.Panel.StartPanel;

public class MyFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 221L;

	private MainPanel mainPanel = null;
	private StartPanel startPanel = null;
	public MyFrame() {
		this.setTitle("¶·µØÖ÷");
		
		start();
		
		this.setBounds(100, 100, 1600, 900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		
		
	}
	
	public void start(){
		startPanel = new StartPanel(this);
		Container c = this.getContentPane();
		c.add(startPanel);
		revalidate();
	}
	
	public void serverMain(){
		mainPanel = new MainPanel(1,null);
		Container c = this.getContentPane();
		c.remove(startPanel);
		c.add(mainPanel);
		revalidate();
	}
	
	public void clientMain(String ip){
		mainPanel = new MainPanel(2,ip);
		Container c = this.getContentPane();
		c.remove(startPanel);
		c.add(mainPanel);
		revalidate();
	}

	public static void main(String[] args) {
		MyFrame win = new MyFrame();
		win.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

}
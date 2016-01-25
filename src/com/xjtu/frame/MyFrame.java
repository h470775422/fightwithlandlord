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
	
	private final int width = 1200;
	private final int height = 700;
	public MyFrame() {
		this.setTitle("������");
		
		
		
		this.setBounds(80, 10, width, height);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		start();
		
	}
	
	public void start(){
		startPanel = new StartPanel(this);
		Container c = this.getContentPane();
		c.add(startPanel);
		revalidate();
	}
	
	public void serverMain(){
		mainPanel = new MainPanel(1,null,width,height);
		Container c = this.getContentPane();
		c.remove(startPanel);
		c.add(mainPanel);
		revalidate();
	}
	
	public void clientMain(String ip){
		mainPanel = new MainPanel(2,ip,width,height);
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
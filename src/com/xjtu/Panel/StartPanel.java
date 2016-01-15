package com.xjtu.Panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.xjtu.frame.MyFrame;
import com.xjtu.graphics.LoadImages;

public class StartPanel extends JPanel implements Runnable, ActionListener {

	private Image start = null;
	private JButton clientBtn = null;
	private JButton serverBtn = null;
	private MyFrame frame = null;

	public StartPanel(MyFrame frame) {
		this.frame = frame;
		LoadImages loadImage = new LoadImages(this);
		start = loadImage.getStart();
		initialBtn();
		//Thread t = new Thread(this);
		//t.start();
		
	}

	private void initialBtn() {
		clientBtn = new JButton("连接主机");
		clientBtn.setBounds(500, 500, 100, 30);
		clientBtn.addActionListener(this);
		clientBtn.setActionCommand("client");
		
		serverBtn = new JButton("创建房间");
		serverBtn.setBounds(500, 550, 100, 30);
		serverBtn.addActionListener(this);
		serverBtn.setActionCommand("server");
		
		this.add(clientBtn);
		this.add(serverBtn);
	}

//	public void paint(Graphics g) {
//		g.clearRect(0, 0, this.getWidth(), this.getHeight());
//		g.drawImage(start, 450, 100, this);
//	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("client")){
			
		}else if(arg0.getActionCommand().equals("server")){
			
		}
	}

}

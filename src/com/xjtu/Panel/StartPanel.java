package com.xjtu.Panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.xjtu.frame.MyFrame;
import com.xjtu.graphics.LoadImages;

public class StartPanel extends JPanel implements Runnable, ActionListener {

	private Image start = null;
	private MyFrame frame = null;
	// server
	private JButton serverBtn = null;
	private JLabel labelOIP = null;
	// client
	private JTextField textIP = null;
	private JLabel labelIP = null;
	private JButton clientBtn = null;
	private InetAddress ia = null;

	private int width;
	private int height;

	public StartPanel(MyFrame frame) {
		this.frame = frame;
		this.setLayout(null);
		this.width = frame.getBounds().width;
		this.height = frame.getBounds().height;
		System.out.println(width + "," + height);
		LoadImages loadImage = new LoadImages(this);
		start = loadImage.getStart();
		initialClientBtn();
		initialServerBtn();
		// Thread t = new Thread(this);
		// t.start();

	}

	private void initialServerBtn() {
		try {
			ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String localip = ia.getHostAddress();

		serverBtn = new JButton("创建房间");
		System.out.println(width + "," + height);
		serverBtn.setBounds(width / 2 - 200, height / 2, 100, 30);
		serverBtn.addActionListener(this);
		serverBtn.setActionCommand("server");
		labelOIP = new JLabel("本机IP:" + localip);
		labelOIP.setBounds(width / 2 - 80, height / 2, 150, 30);
		this.add(labelOIP);
		this.add(serverBtn);
		repaint();
	}

	public void initialClientBtn() {
		labelIP = new JLabel("请输入IP:");
		labelIP.setBounds(width / 2 - 200, height / 2 + 100, 60, 30);
		textIP = new JTextField();
		textIP.setText("192.168.1.107");
		textIP.setBounds(width / 2 - 130, height / 2 + 100, 180, 30);
		clientBtn = new JButton("连接主机");
		clientBtn.setBounds(width / 2 + 50, height / 2 + 100, 100, 30);
		clientBtn.addActionListener(this);
		clientBtn.setActionCommand("client");
		this.add(clientBtn);
		this.add(labelIP);
		this.add(textIP);
	}

	// public void paint(Graphics g) {
	// g.clearRect(0, 0, this.getWidth(), this.getHeight());
	// g.drawImage(start, 450, 100, this);
	// }

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
		if (arg0.getActionCommand().equals("client")) {
			String ip = textIP.getText();
			frame.clientMain(ip);
		} else if (arg0.getActionCommand().equals("server")) {
			frame.serverMain();
		}
	}

}

package com.ITS.management;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ITS.management.page.UserPanel;
import com.ITS.management.page.MainPanel;


public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private MainPanel welcomePanel;
	private UserPanel userPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 340);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		CardLayout card = new CardLayout();
		contentPane.setLayout(card);
		setContentPane(contentPane);
		
		welcomePanel = new MainPanel(this, card);
		contentPane.add(welcomePanel, "welcome");
		
		userPanel = new UserPanel(this, card);
		contentPane.add(userPanel, "user");
		
	}
	
	public JPanel getWholePanel()
	{
		return contentPane;
	}

}

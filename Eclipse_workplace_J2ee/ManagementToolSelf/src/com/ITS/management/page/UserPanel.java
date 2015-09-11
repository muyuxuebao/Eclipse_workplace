package com.ITS.management.page;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ITS.management.Connection;
import com.ITS.management.MainFrame;
import com.ITS.management.SystemConstant;
import com.ITS.management.bean.KeyValueCell;
import com.ITS.management.message.CreateUserMessage;
import com.ITS.management.tool.MessageThreadFactory;


public class UserPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame;
	
	private CardLayout cardLayout; 
	private JTextField textFieldName;
	private JTextField textFieldDept;
	private JTextField textFieldComp;
	private JTextField textFieldEmail;
	private JTextField textFieldLocation;
	private JTextField textFieldState;
	private JTextField textFieldCountry;
	private JTextField textFieldStart;
	private JTextField textFieldEnd;

	/**
	 * Create the panel.
	 * @param string 
	 * @param card 
	 * @param contentPane 
	 */
	public UserPanel(MainFrame frame, CardLayout card) {
		this.mainFrame = frame;
		this.cardLayout = card;
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
		infoPanel.setBackground(Color.WHITE);
		add(infoPanel, BorderLayout.CENTER);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[] {100, 0};
		gbl_infoPanel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
		gbl_infoPanel.columnWeights = new double[]{0.0, 1.0};
		gbl_infoPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		infoPanel.setLayout(gbl_infoPanel);
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		infoPanel.add(lblName, gbc_lblName);
		
		textFieldName = new JTextField("name");
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 0;
		infoPanel.add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblDept = new JLabel("Dept:");
		GridBagConstraints gbc_lblDept = new GridBagConstraints();
		gbc_lblDept.anchor = GridBagConstraints.EAST;
		gbc_lblDept.insets = new Insets(0, 0, 5, 5);
		gbc_lblDept.gridx = 0;
		gbc_lblDept.gridy = 1;
		infoPanel.add(lblDept, gbc_lblDept);
		
		textFieldDept = new JTextField("department");
		GridBagConstraints gbc_textFieldDept = new GridBagConstraints();
		gbc_textFieldDept.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDept.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDept.gridx = 1;
		gbc_textFieldDept.gridy = 1;
		infoPanel.add(textFieldDept, gbc_textFieldDept);
		textFieldDept.setColumns(10);
		
		JLabel lblComp = new JLabel("Comp:");
		GridBagConstraints gbc_lblComp = new GridBagConstraints();
		gbc_lblComp.anchor = GridBagConstraints.EAST;
		gbc_lblComp.insets = new Insets(0, 0, 5, 5);
		gbc_lblComp.gridx = 0;
		gbc_lblComp.gridy = 2;
		infoPanel.add(lblComp, gbc_lblComp);
		
		textFieldComp = new JTextField("company");
		GridBagConstraints gbc_textFieldComp = new GridBagConstraints();
		gbc_textFieldComp.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldComp.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldComp.gridx = 1;
		gbc_textFieldComp.gridy = 2;
		infoPanel.add(textFieldComp, gbc_textFieldComp);
		textFieldComp.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 3;
		infoPanel.add(lblEmail, gbc_lblEmail);
		
		textFieldEmail = new JTextField("email");
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEmail.gridx = 1;
		gbc_textFieldEmail.gridy = 3;
		infoPanel.add(textFieldEmail, gbc_textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location:");
		GridBagConstraints gbc_lblLocation = new GridBagConstraints();
		gbc_lblLocation.anchor = GridBagConstraints.EAST;
		gbc_lblLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocation.gridx = 0;
		gbc_lblLocation.gridy = 4;
		infoPanel.add(lblLocation, gbc_lblLocation);
		
		textFieldLocation = new JTextField("location");
		GridBagConstraints gbc_textFieldLocation = new GridBagConstraints();
		gbc_textFieldLocation.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldLocation.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldLocation.gridx = 1;
		gbc_textFieldLocation.gridy = 4;
		infoPanel.add(textFieldLocation, gbc_textFieldLocation);
		textFieldLocation.setColumns(10);
		
		JLabel lblState = new JLabel("State:");
		GridBagConstraints gbc_lblState = new GridBagConstraints();
		gbc_lblState.anchor = GridBagConstraints.EAST;
		gbc_lblState.insets = new Insets(0, 0, 5, 5);
		gbc_lblState.gridx = 0;
		gbc_lblState.gridy = 5;
		infoPanel.add(lblState, gbc_lblState);
		
		textFieldState = new JTextField("shanghai");
		GridBagConstraints gbc_textFieldState = new GridBagConstraints();
		gbc_textFieldState.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldState.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldState.gridx = 1;
		gbc_textFieldState.gridy = 5;
		infoPanel.add(textFieldState, gbc_textFieldState);
		textFieldState.setColumns(10);
		
		JLabel lblCountry = new JLabel("Country:");
		GridBagConstraints gbc_lblCountry = new GridBagConstraints();
		gbc_lblCountry.anchor = GridBagConstraints.EAST;
		gbc_lblCountry.insets = new Insets(0, 0, 5, 5);
		gbc_lblCountry.gridx = 0;
		gbc_lblCountry.gridy = 6;
		infoPanel.add(lblCountry, gbc_lblCountry);
		
		textFieldCountry = new JTextField("china");
		GridBagConstraints gbc_textFieldCountry = new GridBagConstraints();
		gbc_textFieldCountry.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCountry.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCountry.gridx = 1;
		gbc_textFieldCountry.gridy = 6;
		infoPanel.add(textFieldCountry, gbc_textFieldCountry);
		textFieldCountry.setColumns(10);
		
		JLabel lblStartPeriod = new JLabel("Start Period:");
		GridBagConstraints gbc_lblStartPeriod = new GridBagConstraints();
		gbc_lblStartPeriod.anchor = GridBagConstraints.EAST;
		gbc_lblStartPeriod.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartPeriod.gridx = 0;
		gbc_lblStartPeriod.gridy = 7;
		infoPanel.add(lblStartPeriod, gbc_lblStartPeriod);
		
		textFieldStart = new JTextField("08/21/2014");
		GridBagConstraints gbc_textFieldStart = new GridBagConstraints();
		gbc_textFieldStart.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldStart.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldStart.gridx = 1;
		gbc_textFieldStart.gridy = 7;
		infoPanel.add(textFieldStart, gbc_textFieldStart);
		textFieldStart.setColumns(10);
		
		JLabel lblEndPeriod = new JLabel("End Period:");
		GridBagConstraints gbc_lblEndPeriod = new GridBagConstraints();
		gbc_lblEndPeriod.anchor = GridBagConstraints.EAST;
		gbc_lblEndPeriod.insets = new Insets(0, 0, 0, 5);
		gbc_lblEndPeriod.gridx = 0;
		gbc_lblEndPeriod.gridy = 8;
		infoPanel.add(lblEndPeriod, gbc_lblEndPeriod);
		
		textFieldEnd = new JTextField("08/20/2015");
		GridBagConstraints gbc_textFieldEnd = new GridBagConstraints();
		gbc_textFieldEnd.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEnd.gridx = 1;
		gbc_textFieldEnd.gridy = 8;
		infoPanel.add(textFieldEnd, gbc_textFieldEnd);
		textFieldEnd.setColumns(10);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(Color.WHITE);
		btnPanel.setBorder(new EmptyBorder(10, 0, 10, 10));
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
		add(btnPanel, BorderLayout.SOUTH);
		
		JButton btnCreatUser = new JButton("Save");
		btnCreatUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CreateUserMessage msg = new CreateUserMessage();
		    	msg.action = SystemConstant.ACTION_CREATE_USER;
		    	
		    	ArrayList<KeyValueCell> list = new ArrayList<KeyValueCell>();
		    	KeyValueCell cell = null;
		    	cell = new KeyValueCell("userName", textFieldName.getText());
		    	list.add(cell);
		    	
		    	cell = new KeyValueCell("department", textFieldDept.getText());
		    	list.add(cell);
		    	
		    	cell = new KeyValueCell("company", textFieldComp.getText());
		    	list.add(cell);
		    	
		    	cell = new KeyValueCell("email", textFieldEmail.getText());
		    	list.add(cell);
		    	
		    	cell = new KeyValueCell("location", textFieldLocation.getText());
		    	list.add(cell);
		    	
		    	cell = new KeyValueCell("state", textFieldState.getText());
		    	list.add(cell);
		    	
		    	cell = new KeyValueCell("country", textFieldCountry.getText());
		    	list.add(cell);
		    	
		    	msg.extra = list;
		    	msg.expectDataType = SystemConstant.TYPE_BOOLEAN;
		    	
		    	Connection con = new Connection(SystemConstant.SERVER_URL, SystemConstant.SERVER_PORT);
		        MessageThreadFactory factory = new MessageThreadFactory(con);
		        factory.executeOneThread(msg);
		    	
		        if((Boolean) msg.expectData != Boolean.TRUE)
		        {	
		        	System.out.println("error");
		        }
		        else
		        {
		        	cardLayout.show(mainFrame.getWholePanel(), "welcome");
		        }
			}
		});
		btnPanel.add(btnCreatUser);
	}

}

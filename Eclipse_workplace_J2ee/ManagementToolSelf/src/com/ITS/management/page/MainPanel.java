package com.ITS.management.page;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ITSSecurity.ET199Tool;
import ITSSecurity.et199tool.encrytor.Encryptor;
import ITSSecurity.et199tool.encrytor.RSAEncryptor;
import ITSSecurity.et199tool.system.SystemConfig;

import com.ITS.management.Connection;
import com.ITS.management.MainFrame;
import com.ITS.management.SystemCache;
import com.ITS.management.SystemConstant;
import com.ITS.management.SystemMessage;
import com.ITS.management.bean.KeyValueCell;
import com.ITS.management.message.CreateCertMessage;
import com.ITS.management.message.ImportCertMessage;
import com.ITS.management.message.RecordCertMessage;
import com.ITS.management.tool.MessageThreadFactory;
import com.ITS.management.tool.SystemUtil;

public class MainPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame;
	private CardLayout cardLayout;

	private JTextField userNameText;
	private JLabel infoLabel;

	private JTextField expireTimeText;
	private JLabel expireTimeLabel;

	/**
	 * Create the panel.
	 * 
	 * @param next
	 * @param card
	 */
	public MainPanel(MainFrame frame, CardLayout card) {
		this.mainFrame = frame;
		this.cardLayout = card;

		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));

		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.WHITE);
		add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));

		infoLabel = new JLabel();
		northPanel.add(infoLabel);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));

		JLabel userNameLabel = new JLabel("UserName:");
		centerPanel.add(userNameLabel);

		userNameText = new JTextField();
		userNameText.setPreferredSize(new Dimension(100, 20));
		centerPanel.add(userNameText);

		expireTimeLabel = new JLabel("ExpireTime(Month):");
		centerPanel.add(expireTimeLabel);

		expireTimeText = new JTextField();
		expireTimeText.setPreferredSize(new Dimension(100, 20));
		expireTimeText.setText("24");
		centerPanel.add(expireTimeText);

		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.WHITE);
		southPanel.setBorder(new EmptyBorder(10, 0, 10, 10));
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		add(southPanel, BorderLayout.SOUTH);

		JButton btnUser = new JButton("User");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cardLayout.show(mainFrame.getWholePanel(), "user");
			}
		});
		southPanel.add(btnUser);

		JButton btnFormat = new JButton("Import Cert");
		btnFormat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SystemCache.cache.put("userName", userNameText.getText());
				SystemCache.cache.put("expireTime", expireTimeText.getText());
				// cardLayout.show(mainFrame.getWholePanel(), "format");

				// format();
				new CertThread(SystemConstant.COMMAND_INIT).start();
			}
		});
		southPanel.add(btnFormat);
	}

	class CertThread extends Thread {
		private int stepIndex;

		public CertThread(int stepIndex) {
			this.stepIndex = stepIndex;
		}

		@Override
		public void run() {
			switch (stepIndex) {
			case SystemConstant.COMMAND_INIT: {
				infoLabel.setText(SystemMessage.FORMAT_MESSAGE);
				new CertThread(SystemConstant.COMMAND_FORMAT).start();

				break;
			}

			case SystemConstant.COMMAND_FORMAT: {
				SystemUtil.formatTool();

				infoLabel.setText(SystemMessage.CREATE_CERT_MESSAGE);
				new CertThread(SystemConstant.COMMAND_CREATE_CERT).start();

				break;
			}
			case SystemConstant.COMMAND_CREATE_CERT: {
				CreateCertMessage createMsg = new CreateCertMessage();
				createMsg.action = SystemConstant.ACTION_CREATE_CERT;

				ArrayList<KeyValueCell> list = new ArrayList<KeyValueCell>();
				KeyValueCell cell = null;
				cell = new KeyValueCell("userName", (String) SystemCache.cache.get("userName"));
				list.add(cell);
				cell = new KeyValueCell("vadality", (String) SystemCache.cache.get("expireTime"));
				list.add(cell);

				createMsg.extra = list;
				createMsg.expectDataType = SystemConstant.TYPE_STRING;

				Connection con = new Connection(SystemConstant.SERVER_URL, SystemConstant.SERVER_PORT);
				MessageThreadFactory factory = new MessageThreadFactory(con);
				factory.executeOneThread(createMsg);

				if (createMsg.expectData != null) {
					infoLabel.setText(SystemMessage.IMPORT_CERT_MESSAGE);

					SystemCache.cache.put("certName", createMsg.expectData);
					new CertThread(SystemConstant.COMMAND_IMPORT_CERT).start();
				}

				break;
			}
			case SystemConstant.COMMAND_IMPORT_CERT: {
				ImportCertMessage importMsg = new ImportCertMessage();
				importMsg.action = SystemConstant.ACTION_IMPORT_CERT;
				importMsg.expectDataType = SystemConstant.TYPE_FILE;
				importMsg.extra = (String) SystemCache.cache.get("certName");

				Connection con = new Connection(SystemConstant.SERVER_URL, SystemConstant.SERVER_PORT);

				MessageThreadFactory factory = new MessageThreadFactory(con);
				factory.executeOneThread(importMsg);

				if (importMsg.expectData != null) {
					ET199Tool tool = new ET199Tool();
					Encryptor encryptor = new RSAEncryptor();
					tool.initET199(encryptor);

					if (tool.isKeyAvailable()) {
						// get userPin to login
						String userPin = SystemConfig.USERPIN;

						// open tool
						if (tool.openET199(userPin) == true) {
							if (SystemUtil.importCert() == true) {
								SystemCache.cache.put("hid", tool.getHid());
								tool.clearET199();

								infoLabel.setText(SystemMessage.RECORD_MESSAGE);
								new CertThread(SystemConstant.COMMAND_RECORD).start();
							}
						}
					}
				}

				break;
			}
			case SystemConstant.COMMAND_RECORD: {
				RecordCertMessage recordMsg = new RecordCertMessage();
				recordMsg.action = SystemConstant.ACTION_RECORD_CERT;

				ArrayList<KeyValueCell> list = new ArrayList<KeyValueCell>();
				KeyValueCell cell = null;
				cell = new KeyValueCell("hid", (String) SystemCache.cache.get("hid"));
				list.add(cell);

				cell = new KeyValueCell("userName", (String) SystemCache.cache.get("userName"));
				list.add(cell);

				cell = new KeyValueCell("certName", (String) SystemCache.cache.get("certName"));
				list.add(cell);

				recordMsg.extra = list;
				recordMsg.expectDataType = SystemConstant.TYPE_BOOLEAN;

				Connection con = new Connection(SystemConstant.SERVER_URL, SystemConstant.SERVER_PORT);

				MessageThreadFactory factory = new MessageThreadFactory(con);
				factory.executeOneThread(recordMsg);

				if ((Boolean) recordMsg.expectData == Boolean.TRUE) {
					infoLabel.setText(SystemMessage.SUCCESS_MESSAGE);
				} else {
					infoLabel.setText(SystemMessage.FAIL_MESSAGE);
				}

				break;
			}

			}
		}
	}

}

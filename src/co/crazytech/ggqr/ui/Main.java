package co.crazytech.ggqr.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;

import java.awt.Canvas;

import javax.imageio.ImageIO;
import javax.net.ssl.ExtendedSSLSession;
import javax.swing.DefaultListModel;
import javax.swing.GrayFilter;
import javax.swing.JButton;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;
import org.jdom.output.Format;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;

import java.awt.GridLayout;
import java.awt.FlowLayout;

public class Main {

	private JFrame frmGaharuGadingQr;
	private JList<String> listCodes;
	private JComboBox<String> comboBoxType,comboBoxSize,comboBoxFarm,comboBoxColumn;
	private Map<Integer, String> mapCountry,mapRegion,mapFarm;	
	private String selectedImageName;
	private String qrDir;
	private static final String QR_WEB_DIR = "https://phpmysql-crazytechco.rhcloud.com/qr.php";
	private JPanel rangeParentPanel;
	private JFormattedTextField textFieldStart,textFieldEnd;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmGaharuGadingQr.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		if(!new File("tempqr").exists())new File("tempqr").mkdirs();
		qrDir = new File("tempqr").getAbsolutePath();
		frmGaharuGadingQr = new JFrame();
		frmGaharuGadingQr.setTitle("Gaharu Gading QR Generator");
		frmGaharuGadingQr.setBounds(100, 100, 450, 300);
		frmGaharuGadingQr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGaharuGadingQr.getContentPane().setLayout(new BorderLayout(0, 0));
		frmGaharuGadingQr.setSize(new Dimension(640, 400));
		
		
		mapCountry = new HashMap<>();
		mapCountry.put(1, "Malaysia");
		
		mapRegion = new HashMap<>();
		mapRegion.put(1, "Sarawak");
		
		mapFarm = new HashMap<>();
		mapFarm.put(1, "Lundu Gunung Gading");
		
		JPanel panelLeft = new JPanel();
		frmGaharuGadingQr.getContentPane().add(panelLeft, BorderLayout.WEST);
		GridBagLayout gbl_panelLeft = new GridBagLayout();
		gbl_panelLeft.columnWidths = new int[]{42, 86, 0};
		gbl_panelLeft.rowHeights = new int[]{0, 20, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelLeft.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelLeft.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelLeft.setLayout(gbl_panelLeft);
		
		comboBoxType = new JComboBox();
		GridBagConstraints gbc_comboBoxType = new GridBagConstraints();
		gbc_comboBoxType.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxType.gridx = 0;
		gbc_comboBoxType.gridy = 0;
		comboBoxType.addItem("Bee");
		comboBoxType.addItem("Tree");
		panelLeft.add(comboBoxType, gbc_comboBoxType);
		
		comboBoxSize = new JComboBox();
		comboBoxSize.setEnabled(false);
		GridBagConstraints gbc_comboBoxSize = new GridBagConstraints();
		gbc_comboBoxSize.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSize.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSize.gridx = 1;
		gbc_comboBoxSize.gridy = 0;
		comboBoxSize.addItem("200x200");
		comboBoxSize.addItem("250x250");
		comboBoxSize.addItem("300x300");
		comboBoxSize.addItem("350x350");
		comboBoxSize.addItem("400x400");
		comboBoxSize.addItem("450x450");
		comboBoxSize.addItem("500x500");
		comboBoxSize.addItem("550x550");
		comboBoxSize.addItem("600x600");
		comboBoxSize.setSelectedIndex(2);
		panelLeft.add(comboBoxSize, gbc_comboBoxSize);
		
		comboBoxFarm = new JComboBox();
		GridBagConstraints gbc_comboBoxFarm = new GridBagConstraints();
		gbc_comboBoxFarm.gridwidth = 2;
		gbc_comboBoxFarm.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxFarm.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFarm.gridx = 0;
		gbc_comboBoxFarm.gridy = 1;
		panelLeft.add(comboBoxFarm, gbc_comboBoxFarm);
		for (String value : mapFarm.values()) {
			comboBoxFarm.addItem(value);
		}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 5;
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 2;
		panelLeft.add(scrollPane_1, gbc_scrollPane_1);
		
		rangeParentPanel = new JPanel();
		scrollPane_1.setViewportView(rangeParentPanel);
		rangeParentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		rangeParentPanel.add(rangePanel());
		
		
		JButton btnAddRange = new JButton("ADD RANGE");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 7;
		btnAddRange.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GridLayout layout = (GridLayout)rangeParentPanel.getLayout();
				int row = layout.getRows()+1;
				rangeParentPanel.setLayout(new GridLayout(row, 0, 0, 0));
				rangeParentPanel.add(rangePanel());
				rangeParentPanel.validate();
				rangeParentPanel.repaint();
				scrollPane_1.validate();
			}
		});
		panelLeft.add(btnAddRange, gbc_btnNewButton);
		
		JScrollPane scrollPaneList = new JScrollPane();
		listCodes = new JList(new DefaultListModel<String>());
		scrollPaneList.setViewportView(listCodes);
		GridBagConstraints gbc_list_codes = new GridBagConstraints();
		gbc_list_codes.gridwidth = 2;
		gbc_list_codes.fill = GridBagConstraints.BOTH;
		gbc_list_codes.gridx = 0;
		gbc_list_codes.gridy = 8;
		panelLeft.add(scrollPaneList, gbc_list_codes);
		
		
		JPanel panelCenter = new JPanel();
		frmGaharuGadingQr.getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout(0, 0));
		
		JButton btnGenerate = new JButton("GENERATE");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generate();
			}
		});
		panelCenter.add(btnGenerate, BorderLayout.SOUTH);
		
		JPanel panelImage = new JPanel();
		panelCenter.add(panelImage, BorderLayout.CENTER);
		panelImage.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelImage.add(scrollPane, BorderLayout.CENTER);
		
		JEditorPane editorPane = new JEditorPane();
		scrollPane.setViewportView(editorPane);
		
		listCodes.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ListModel<String> lm = listCodes.getModel();
				String selected = lm.getElementAt(listCodes.getSelectedIndex());
				editorPane.setContentType("text/html");
				try {
					URL url = new File(qrDir+"/"+selected+".png").toURI().toURL();
					editorPane.setText("<html><img src='"+url+"' width=380 height=300></img></html>");
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	private JPanel rangePanel() {
		JPanel panelRange = new JPanel();
		GridBagLayout gbl_panelRange = new GridBagLayout();
		gbl_panelRange.rowWeights = new double[]{0.0, 1.0, 0.0};
		gbl_panelRange.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panelRange.columnWidths = new int[] {0,30, 30, 30,30};
		gbl_panelRange.rowHeights = new int[] {0, 0, 20, 30};
		panelRange.setLayout(gbl_panelRange);
		comboBoxColumn = new JComboBox<String>();
		for(char alphabet = 'A'; alphabet <= 'Z';alphabet++) {
			comboBoxColumn.addItem(alphabet+"");
		}
		GridBagConstraints gbc_comboBoxColumn = new GridBagConstraints();
		gbc_comboBoxColumn.fill = GridBagConstraints.BOTH;
		gbc_comboBoxColumn.anchor = GridBagConstraints.WEST;
		gbc_comboBoxColumn.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxColumn.gridx = 0;
		gbc_comboBoxColumn.gridy = 0;
		panelRange.add(comboBoxColumn, gbc_comboBoxColumn);
		
		textFieldStart = new JFormattedTextField(NumberFormat.getNumberInstance());
		textFieldStart.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_textFieldStart = new GridBagConstraints();
		gbc_textFieldStart.fill = GridBagConstraints.BOTH;
		gbc_textFieldStart.anchor = GridBagConstraints.WEST;
		gbc_textFieldStart.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldStart.gridx = 1;
		gbc_textFieldStart.gridy = 0;
		panelRange.add(textFieldStart, gbc_textFieldStart);
		textFieldStart.setColumns(5);
		textFieldStart.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                textFieldStart.selectAll();
		            }
		        });
			}
		});
		
		textFieldEnd = new JFormattedTextField(NumberFormat.getNumberInstance());
		textFieldEnd.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_textFieldEnd = new GridBagConstraints();
		gbc_textFieldEnd.fill = GridBagConstraints.BOTH;
		gbc_textFieldEnd.anchor = GridBagConstraints.WEST;
		gbc_textFieldEnd.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEnd.gridx = 2;
		gbc_textFieldEnd.gridy = 0;
		panelRange.add(textFieldEnd, gbc_textFieldEnd);
		textFieldEnd.setColumns(5);
		textFieldEnd.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                textFieldEnd.selectAll();
		            }
		        });
			}
		});
		
		JButton btnX = new JButton("X");
		GridBagConstraints gbc_btnX = new GridBagConstraints();
		gbc_btnX.insets = new Insets(0, 0, 5, 5);
		gbc_btnX.fill = GridBagConstraints.BOTH;
		gbc_btnX.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnX.gridx = 3;
		gbc_btnX.gridy = 0;
		btnX.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				rangeParentPanel.remove(panelRange);
				rangeParentPanel.validate();
				rangeParentPanel.repaint();
			}
		});
		panelRange.add(btnX, gbc_btnX);
		
		JScrollPane scrollPaneNickname = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panelRange.add(scrollPaneNickname, gbc_scrollPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		scrollPaneNickname.setViewportView(panel);
		
		JButton btnAddNickname = new JButton("ADD NICKNAME");
		GridBagConstraints gbcBtnAddNickname = new GridBagConstraints();
		gbcBtnAddNickname.insets = new Insets(3, 3, 5, 5);
		gbcBtnAddNickname.fill = GridBagConstraints.HORIZONTAL;
		gbcBtnAddNickname.gridwidth = 4;
		gbcBtnAddNickname.gridx = 0;
		gbcBtnAddNickname.gridy = 2;
		panelRange.add(btnAddNickname, gbcBtnAddNickname);
		
		
		
		btnAddNickname.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GridLayout layout = (GridLayout)panel.getLayout(); 
				int row = layout.getRows()+1;
				panel.setLayout(new GridLayout(row, 0, 0, 0));
				String col = comboBoxColumn.getSelectedItem().toString();
				if (textFieldStart.getText().isEmpty()||textFieldEnd.getText().isEmpty())return;
				Integer rowStart = new  Integer(textFieldStart.getText());
				Integer rowEnd = new  Integer(textFieldEnd.getText());
				int componentCount = panel.getComponentCount();
				int count = rowEnd - rowStart;
				if(componentCount>count) return;
				panel.add(nicknamePanel(panel,col,rowStart,rowEnd));
				panel.validate();
				panel.repaint();
				scrollPaneNickname.validate();
				scrollPaneNickname.repaint();
			}
		});
		return panelRange;
	}
	
	private JPanel nicknamePanel(JPanel panel,String col, int rowStart, int rowEnd){
		JPanel nicknamePanel = new JPanel();
		GridBagLayout gblNickname = new GridBagLayout();
		gblNickname.columnWidths = new int[] {60,60,30};
		gblNickname.rowHeights = new int[] {0, 0};
		nicknamePanel.setLayout(gblNickname);
		JComboBox<String> comboBoxColRow = new JComboBox<String>();
		for (int i=rowStart;i<=rowEnd;i++){
			comboBoxColRow.addItem(col+i);
		}
		GridBagConstraints gbcCbColRow = new GridBagConstraints();
		gbcCbColRow.insets = new Insets(0, 0, 5, 5);
		gbcCbColRow.fill = GridBagConstraints.BOTH;
		gbcCbColRow.anchor = GridBagConstraints.NORTHWEST;
		gbcCbColRow.gridx = 0;
		gbcCbColRow.gridy = 0;
		nicknamePanel.add(comboBoxColRow,gbcCbColRow);
		
		JTextField tfNickname = new JTextField();
		GridBagConstraints gbcTfNickname = new GridBagConstraints();
		gbcTfNickname.insets = new Insets(0, 0, 5, 5);
		gbcTfNickname.fill = GridBagConstraints.BOTH;
		gbcTfNickname.anchor = GridBagConstraints.NORTHWEST;
		gbcTfNickname.gridx = 1;
		gbcTfNickname.gridy = 0;
		nicknamePanel.add(tfNickname,gbcTfNickname);
		tfNickname.setColumns(10);
		
		JButton btnX = new JButton("X");
		GridBagConstraints gbc_btnX = new GridBagConstraints();
		gbc_btnX.insets = new Insets(0, 0, 5, 5);
		gbc_btnX.fill = GridBagConstraints.BOTH;
		gbc_btnX.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnX.gridx = 2;
		gbc_btnX.gridy = 0;
		btnX.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.remove(nicknamePanel);
				panel.validate();
				panel.repaint();
			}
		});
		nicknamePanel.add(btnX,gbc_btnX);
		return nicknamePanel;
	}
	
	private void generate(){
		Thread thread = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Integer countryCode,regionCode,farmCode;
					String type = "T";
					if(comboBoxType.getSelectedItem().toString().equals("Bee"))type="B";
					if(comboBoxType.getSelectedItem().toString().equals("Tree"))type="T";
					countryCode = 1;regionCode=1;farmCode=1;
					for (Integer key : mapFarm.keySet()) {
						if(mapCountry.get(key)==comboBoxFarm.getSelectedItem().toString()){
							farmCode = key;
							break;
						}
					}
					for (int i=0; i<rangeParentPanel.getComponentCount();i++){
						JPanel rangePanel = (JPanel)rangeParentPanel.getComponent(i);
						JComboBox<String> cbCol = (JComboBox<String>)rangePanel.getComponent(0);
						String colStr = cbCol.getSelectedItem().toString();
						int rowStart = new Integer(((JTextField)rangePanel.getComponent(1)).getText().toString());
						int rowEnd = new Integer(((JTextField)rangePanel.getComponent(2)).getText().toString());
						DefaultListModel<String> lm = (DefaultListModel<String>) listCodes.getModel();
						JScrollPane scrollPane = (JScrollPane)rangePanel.getComponent(4);
						for (int j = rowStart; j <= rowEnd; j++) {
							
							//String data = "gga_"+countryCode+"_"+String.format("%03d", regionCode)+"_"+String.format("%04d", farmCode)+"_"+type+"_"+colStr+"_"+String.format("%d", j);
							String data = "GG"+"AB"+"ABC"+"AbC1";
							URL url = new URL(QR_WEB_DIR+"?data="+data+"&size=300x300&logo=logo.png");
							BufferedImage image = ImageIO.read(url);
							GraphicsConfiguration config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
							BufferedImage altered = config.createCompatibleImage(image.getWidth()+80, image.getHeight());
							Graphics2D rect = altered.createGraphics();
							rect.setColor(Color.WHITE);
							rect.fillRect(0, 0, 80, altered.getHeight());
							rect.drawImage(image, 80, 0, null);
							rect.setFont(rect.getFont().deriveFont(Font.BOLD,80f));
							rect.setColor(Color.BLACK);
							rect.rotate(Math.toRadians(-90));
							rect.drawString(colStr+j, -image.getHeight()+12, 80);
							rect.rotate(Math.toRadians(90));
							rect.setFont(rect.getFont().deriveFont(Font.PLAIN,20f));
							rect.drawString(type.equals("B")?"HIVE":"TREE", 100, image.getHeight()-3);
							rect.dispose();
							ImageIO.write(altered, "png", new File(qrDir+"/"+data+".png"));
							lm.addElement(data);
						}
						
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		thread.start();
	}
	
	
}

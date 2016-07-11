package co.crazytech.ggqr.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.Canvas;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frmGaharuGadingQr;
	private JTextField textFieldQty;
	private JList<String> listCodes;
	private JComboBox<String> comboBoxType,comboBoxSize,comboBoxFarm,comboBoxCountry,comboBoxRegion;
	private Map<Integer, String> mapCountry,mapRegion,mapFarm;	
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
		frmGaharuGadingQr = new JFrame();
		frmGaharuGadingQr.setTitle("Gaharu Gading QR Generator");
		frmGaharuGadingQr.setBounds(100, 100, 450, 300);
		frmGaharuGadingQr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGaharuGadingQr.getContentPane().setLayout(new BorderLayout(0, 0));
		
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
		gbl_panelLeft.rowHeights = new int[]{0, 20, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelLeft.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelLeft.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelLeft.setLayout(gbl_panelLeft);
		
		JLabel lblLastId = new JLabel("Last ID");
		GridBagConstraints gbc_lblLastId = new GridBagConstraints();
		gbc_lblLastId.anchor = GridBagConstraints.WEST;
		gbc_lblLastId.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastId.gridx = 0;
		gbc_lblLastId.gridy = 0;
		panelLeft.add(lblLastId, gbc_lblLastId);
		
		JLabel lblId = new JLabel("ID");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.WEST;
		gbc_lblId.insets = new Insets(0, 0, 5, 0);
		gbc_lblId.gridx = 1;
		gbc_lblId.gridy = 0;
		panelLeft.add(lblId, gbc_lblId);
		
		JLabel lblQuantity = new JLabel("Quantity");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.WEST;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.gridx = 0;
		gbc_lblQuantity.gridy = 1;
		panelLeft.add(lblQuantity, gbc_lblQuantity);
		
		textFieldQty = new JTextField();
		textFieldQty.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldQty.setText("1");
		GridBagConstraints gbc_textFieldQty = new GridBagConstraints();
		gbc_textFieldQty.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldQty.anchor = GridBagConstraints.NORTHEAST;
		gbc_textFieldQty.gridx = 1;
		gbc_textFieldQty.gridy = 1;
		panelLeft.add(textFieldQty, gbc_textFieldQty);
		textFieldQty.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.anchor = GridBagConstraints.EAST;
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 2;
		panelLeft.add(lblType, gbc_lblType);
		
		comboBoxType = new JComboBox();
		GridBagConstraints gbc_comboBoxType = new GridBagConstraints();
		gbc_comboBoxType.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxType.gridx = 1;
		gbc_comboBoxType.gridy = 2;
		comboBoxType.addItem("Bee");
		comboBoxType.addItem("Tree");
		panelLeft.add(comboBoxType, gbc_comboBoxType);
		
		JLabel lblSize = new JLabel("Size");
		GridBagConstraints gbc_lblSize = new GridBagConstraints();
		gbc_lblSize.anchor = GridBagConstraints.WEST;
		gbc_lblSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblSize.gridx = 0;
		gbc_lblSize.gridy = 3;
		panelLeft.add(lblSize, gbc_lblSize);
		
		comboBoxSize = new JComboBox();
		GridBagConstraints gbc_comboBoxSize = new GridBagConstraints();
		gbc_comboBoxSize.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSize.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSize.gridx = 1;
		gbc_comboBoxSize.gridy = 3;
		comboBoxSize.addItem("200x200");
		comboBoxSize.addItem("250x250");
		comboBoxSize.addItem("300x300");
		comboBoxSize.addItem("350x350");
		comboBoxSize.addItem("400x400");
		comboBoxSize.addItem("450x450");
		comboBoxSize.addItem("500x500");
		comboBoxSize.addItem("550x550");
		comboBoxSize.addItem("600x600");
		comboBoxSize.setSelectedIndex(0);
		panelLeft.add(comboBoxSize, gbc_comboBoxSize);
		
		JLabel lblFarm = new JLabel("Farm");
		lblFarm.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblFarm = new GridBagConstraints();
		gbc_lblFarm.anchor = GridBagConstraints.WEST;
		gbc_lblFarm.insets = new Insets(0, 0, 5, 5);
		gbc_lblFarm.gridx = 0;
		gbc_lblFarm.gridy = 4;
		panelLeft.add(lblFarm, gbc_lblFarm);
		
		comboBoxFarm = new JComboBox();
		GridBagConstraints gbc_comboBoxFarm = new GridBagConstraints();
		gbc_comboBoxFarm.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxFarm.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFarm.gridx = 1;
		gbc_comboBoxFarm.gridy = 4;
		for (String value : mapFarm.values()) {
			comboBoxFarm.addItem(value);
		}
		panelLeft.add(comboBoxFarm, gbc_comboBoxFarm);
		
		JLabel lblCountry = new JLabel("Country");
		GridBagConstraints gbc_lblCountry = new GridBagConstraints();
		gbc_lblCountry.anchor = GridBagConstraints.WEST;
		gbc_lblCountry.insets = new Insets(0, 0, 5, 5);
		gbc_lblCountry.gridx = 0;
		gbc_lblCountry.gridy = 5;
		panelLeft.add(lblCountry, gbc_lblCountry);
		
		comboBoxCountry = new JComboBox();
		GridBagConstraints gbc_comboBoxCountry = new GridBagConstraints();
		gbc_comboBoxCountry.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxCountry.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCountry.gridx = 1;
		gbc_comboBoxCountry.gridy = 5;
		for (String value : mapCountry.values()) {
			comboBoxCountry.addItem(value);
		}
		panelLeft.add(comboBoxCountry, gbc_comboBoxCountry);
		
		JLabel lblRegion = new JLabel("Region");
		GridBagConstraints gbc_lblRegion = new GridBagConstraints();
		gbc_lblRegion.anchor = GridBagConstraints.WEST;
		gbc_lblRegion.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegion.gridx = 0;
		gbc_lblRegion.gridy = 6;
		panelLeft.add(lblRegion, gbc_lblRegion);
		
		comboBoxRegion = new JComboBox();
		GridBagConstraints gbc_comboBoxRegion = new GridBagConstraints();
		gbc_comboBoxRegion.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxRegion.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxRegion.gridx = 1;
		gbc_comboBoxRegion.gridy = 6;
		for (String value : mapRegion.values()) {
			comboBoxRegion.addItem(value);
		}
		panelLeft.add(comboBoxRegion, gbc_comboBoxRegion);
		
		listCodes = new JList();
		GridBagConstraints gbc_list_codes = new GridBagConstraints();
		gbc_list_codes.gridwidth = 2;
		gbc_list_codes.fill = GridBagConstraints.BOTH;
		gbc_list_codes.gridx = 0;
		gbc_list_codes.gridy = 7;
		panelLeft.add(listCodes, gbc_list_codes);
		
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
	}
	
	private void generate(){
		Runnable runnable = new Runnable(){

			@Override
			public void run() {
				try {
					Integer countryCode,regionCode,farmCode,propCode;
					String type = "T";
					if(comboBoxType.getSelectedItem().toString().equals("Bee"))type="B";
					if(comboBoxType.getSelectedItem().toString().equals("Tree"))type="T";
					countryCode = 1;regionCode=1;farmCode=1;propCode=1;
					for (Integer key : mapFarm.keySet()) {
						if(mapCountry.get(key)==comboBoxFarm.getSelectedItem().toString()){
							farmCode = key;
							break;
						}
					}
					for (Integer key : mapCountry.keySet()) {
						if(mapCountry.get(key)==comboBoxCountry.getSelectedItem().toString()){
							countryCode = key;
							break;
						}
					}
					for (Integer key : mapRegion.keySet()) {
						if(mapRegion.get(key)==comboBoxRegion.getSelectedItem().toString()){
							regionCode = key;
							break;
						}
					}
					
					for (int i = propCode; i < (new Integer(textFieldQty.getText().toString())+propCode); i++) {
						String data = countryCode+"_"+String.format("%03d", farmCode)+"_"+String.format("%04d", farmCode)+"_"+type+String.format("%07d", propCode);
						URL url = new URL("http://phpmysql-crazytechco.rhcloud.com/QRLogo.php?data="+data+"&size="+comboBoxSize.getSelectedItem().toString()+"&logo=logo.png");
						InputStream in = new BufferedInputStream(url.openStream());
						ByteArrayOutputStream out = new ByteArrayOutputStream();
						byte[] buf = new byte[1024];
						int n = 0;
						while (-1!=(n=in.read(buf)))
						{
							out.write(buf, 0, n);
						}
						out.close();
						in.close();
						byte[] response = out.toByteArray();
						
						FileOutputStream fos = new FileOutputStream(data+".png");
						fos.write(response);
						fos.close();
						propCode+=1;
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
		runnable.run();
	}
}

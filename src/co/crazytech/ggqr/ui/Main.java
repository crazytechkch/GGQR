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
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frmGaharuGadingQr;
	private JTextField tf_qty;

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
		
		JPanel panel_left = new JPanel();
		frmGaharuGadingQr.getContentPane().add(panel_left, BorderLayout.WEST);
		GridBagLayout gbl_panel_left = new GridBagLayout();
		gbl_panel_left.columnWidths = new int[]{42, 86, 0};
		gbl_panel_left.rowHeights = new int[]{0, 20, 0, 0, 0, 0, 0};
		gbl_panel_left.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_left.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_left.setLayout(gbl_panel_left);
		
		JLabel lblLastId = new JLabel("Last ID");
		GridBagConstraints gbc_lblLastId = new GridBagConstraints();
		gbc_lblLastId.anchor = GridBagConstraints.WEST;
		gbc_lblLastId.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastId.gridx = 0;
		gbc_lblLastId.gridy = 0;
		panel_left.add(lblLastId, gbc_lblLastId);
		
		JLabel lbl_lastId = new JLabel("ID");
		GridBagConstraints gbc_lbl_lastId = new GridBagConstraints();
		gbc_lbl_lastId.anchor = GridBagConstraints.WEST;
		gbc_lbl_lastId.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_lastId.gridx = 1;
		gbc_lbl_lastId.gridy = 0;
		panel_left.add(lbl_lastId, gbc_lbl_lastId);
		
		JLabel lblQuantity = new JLabel("Quantity");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.WEST;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.gridx = 0;
		gbc_lblQuantity.gridy = 1;
		panel_left.add(lblQuantity, gbc_lblQuantity);
		
		tf_qty = new JTextField();
		tf_qty.setHorizontalAlignment(SwingConstants.RIGHT);
		tf_qty.setText("1");
		GridBagConstraints gbc_tf_qty = new GridBagConstraints();
		gbc_tf_qty.insets = new Insets(0, 0, 5, 0);
		gbc_tf_qty.anchor = GridBagConstraints.NORTHEAST;
		gbc_tf_qty.gridx = 1;
		gbc_tf_qty.gridy = 1;
		panel_left.add(tf_qty, gbc_tf_qty);
		tf_qty.setColumns(10);
		
		JLabel lblFarm = new JLabel("Farm");
		lblFarm.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblFarm = new GridBagConstraints();
		gbc_lblFarm.anchor = GridBagConstraints.WEST;
		gbc_lblFarm.insets = new Insets(0, 0, 5, 5);
		gbc_lblFarm.gridx = 0;
		gbc_lblFarm.gridy = 2;
		panel_left.add(lblFarm, gbc_lblFarm);
		
		JComboBox comboBox_farm = new JComboBox();
		GridBagConstraints gbc_comboBox_farm = new GridBagConstraints();
		gbc_comboBox_farm.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_farm.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_farm.gridx = 1;
		gbc_comboBox_farm.gridy = 2;
		panel_left.add(comboBox_farm, gbc_comboBox_farm);
		
		JLabel lblCountry = new JLabel("Country");
		GridBagConstraints gbc_lblCountry = new GridBagConstraints();
		gbc_lblCountry.anchor = GridBagConstraints.WEST;
		gbc_lblCountry.insets = new Insets(0, 0, 5, 5);
		gbc_lblCountry.gridx = 0;
		gbc_lblCountry.gridy = 3;
		panel_left.add(lblCountry, gbc_lblCountry);
		
		JComboBox comboBox_country = new JComboBox();
		GridBagConstraints gbc_comboBox_country = new GridBagConstraints();
		gbc_comboBox_country.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_country.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_country.gridx = 1;
		gbc_comboBox_country.gridy = 3;
		panel_left.add(comboBox_country, gbc_comboBox_country);
		
		JLabel lblRegion = new JLabel("Region");
		GridBagConstraints gbc_lblRegion = new GridBagConstraints();
		gbc_lblRegion.anchor = GridBagConstraints.WEST;
		gbc_lblRegion.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegion.gridx = 0;
		gbc_lblRegion.gridy = 4;
		panel_left.add(lblRegion, gbc_lblRegion);
		
		JComboBox comboBox_region = new JComboBox();
		GridBagConstraints gbc_comboBox_region = new GridBagConstraints();
		gbc_comboBox_region.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_region.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_region.gridx = 1;
		gbc_comboBox_region.gridy = 4;
		panel_left.add(comboBox_region, gbc_comboBox_region);
		
		JList<? extends String> list_codes = new JList();
		GridBagConstraints gbc_list_codes = new GridBagConstraints();
		gbc_list_codes.gridwidth = 2;
		gbc_list_codes.insets = new Insets(0, 0, 0, 5);
		gbc_list_codes.fill = GridBagConstraints.BOTH;
		gbc_list_codes.gridx = 0;
		gbc_list_codes.gridy = 5;
		panel_left.add(list_codes, gbc_list_codes);
		
		JPanel panel_center = new JPanel();
		frmGaharuGadingQr.getContentPane().add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));
		
		JButton btnGenerate = new JButton("GENERATE");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_center.add(btnGenerate, BorderLayout.SOUTH);
	}
	
	private void generate(){
		Runnable runnable = new Runnable(){

			@Override
			public void run() {
				try {
					BufferedImage img = ImageIO.read(new URL("http://phpmysql-crazytechco.rhcloud.com/QRLogo.php"));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
	}

}

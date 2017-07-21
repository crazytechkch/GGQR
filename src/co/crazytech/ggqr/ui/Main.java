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

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import co.crazytech.commons.json.JsonParser;
import co.crazytech.commons.util.RandomCharacters;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;

import java.awt.GridLayout;

public class Main {

	private JFrame frmGaharuGadingQr;
	private JList<String> listCodes;
	private JComboBox<String> comboBoxType,comboBoxSize,comboBoxFarm,comboBoxColumn;
	private Map<Integer, String> mapCountry,mapRegion,mapFarm;	
	private String selectedImageName;
	private String qrDir;
	private static final String QR_WEB_DIR = "https://phpmysql-crazytechco.rhcloud.com/qr.php";
	private static final String GG_WEB_DIR = "https://phpmysql-crazytechco.rhcloud.com/gga/";
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
		for (GGObject obj : getProdTypes()) {
			comboBoxType.addItem(obj.getName()+":"+obj.getCode());
		}
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
		for (GGObject obj : getFarms()) {
			comboBoxFarm.addItem(obj.getName()+":"+obj.getCode());
		}
		
		textFieldStart = new JFormattedTextField(NumberFormat.getNumberInstance());
		textFieldStart.setHorizontalAlignment(SwingConstants.RIGHT);
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
		textFieldStart.setText("1");
		GridBagConstraints gbc_textFieldStart = new GridBagConstraints();
		gbc_textFieldStart.gridheight = 5;
		gbc_textFieldStart.gridwidth = 2;
		gbc_textFieldStart.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldStart.fill = GridBagConstraints.BOTH;
		gbc_textFieldStart.gridx = 0;
		gbc_textFieldStart.gridy = 2;
		panelLeft.add(textFieldStart, gbc_textFieldStart);
		
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
					URL url = new File(qrDir+"/"+selected.substring(2, 4)+"/"+selected+".png").toURI().toURL();
					editorPane.setText("<html><img src='"+url+"' width=380 height=300></img></html>");
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void generate(){
		Thread thread = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					String[] type = comboBoxType.getSelectedItem().toString().split(":");
					String typeName = type[0];
					String typeCode = type[1];
					String[] farm = comboBoxFarm.getSelectedItem().toString().split(":");
					String farmName = farm[0];
					String farmCode = farm[1];
					Integer qrCount = Integer.valueOf(textFieldStart.getText());
					DefaultListModel<String> lm = (DefaultListModel<String>) listCodes.getModel();
					for (int i = 1; i <= qrCount; i++) {
						
						//String data = "gga_"+countryCode+"_"+String.format("%03d", regionCode)+"_"+String.format("%04d", farmCode)+"_"+type+"_"+colStr+"_"+String.format("%d", j);
						String data = "GG"+typeCode+farmCode+getRandomCode();
						URL url = new URL(QR_WEB_DIR+"?data="+data+"&size=300x300&logo=logo.png");
						BufferedImage image = ImageIO.read(url);
						GraphicsConfiguration config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
						BufferedImage altered = config.createCompatibleImage(image.getWidth()+80, image.getHeight());
						Graphics2D rect = altered.createGraphics();
						rect.setColor(Color.WHITE);
						rect.fillRect(0, 0, 80, altered.getHeight());
						rect.drawImage(image, 80, 0, null);
						rect.setFont(rect.getFont().deriveFont(Font.BOLD,10f));
						rect.setColor(Color.BLACK);
						rect.rotate(Math.toRadians(-90));
						rect.drawString(farmName.toUpperCase(), -image.getHeight()+12, 90);
						rect.drawString("ID", -image.getHeight()+12, 80);
						rect.drawString("NICKNAME", -image.getHeight()+110, 80);
						rect.rotate(Math.toRadians(90));
						rect.setFont(rect.getFont().deriveFont(Font.PLAIN,12f));
						rect.drawString(typeName.toUpperCase(), 95, image.getHeight());
						rect.dispose();
						File file = new File(qrDir+"/"+typeCode+"/");
						if (!file.exists()) file.mkdirs();
						ImageIO.write(altered, "png", new File(qrDir+"/"+typeCode+"/"+data+".png"));
						lm.addElement(data);
					}
					/*for (int i=0; i<rangeParentPanel.getComponentCount();i++){
						JPanel rangePanel = (JPanel)rangeParentPanel.getComponent(i);
						JComboBox<String> cbCol = (JComboBox<String>)rangePanel.getComponent(0);
						String colStr = cbCol.getSelectedItem().toString();
						int rowStart = new Integer(((JTextField)rangePanel.getComponent(1)).getText().toString());
						int rowEnd = new Integer(((JTextField)rangePanel.getComponent(2)).getText().toString());
						JScrollPane scrollPane = (JScrollPane)rangePanel.getComponent(4);
						
					}*/
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
	
	private List<GGObject> ggObjs(String table) {
		List<GGObject> prodTypes = new ArrayList<GGObject>();
		try {
			JsonParser jsonParser = new JsonParser(GG_WEB_DIR+"get_codes.php?table="+table);
			JSONObject jsonObj = jsonParser.parse();
			JSONArray jsonArr = (JSONArray)jsonObj.get("results");
			for (Object object : jsonArr) {
				GGObject ggo = new GGObject();
				JSONObject arrObj = (JSONObject)object;
				ggo.setId(new BigInteger((String)arrObj.get("id")));
				ggo.setName((String)arrObj.get("name"));
				ggo.setCode((String)arrObj.get("code"));
				prodTypes.add(ggo);
			}
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(prodTypes, new Comparator<GGObject>() {
		    @Override
		    public int compare(GGObject o1, GGObject o2) {
		        return o1.getCode().compareTo(o2.getCode());
		    }
		});
		return prodTypes;
	}
	
	private List<GGObject> getProdTypes(){
		return ggObjs("prod_type");
	}
	
	private List<GGObject> getFarms(){
		return ggObjs("farm");
	}
	
	private String getRandomCode(){
		return RandomCharacters.randomString(4);
	}
}

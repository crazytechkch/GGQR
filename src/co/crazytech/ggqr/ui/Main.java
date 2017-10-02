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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.swing.JList;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.crazytech.io.IOUtil;

import co.crazytech.commons.json.JsonParser;
import co.crazytech.commons.util.RandomCharacters;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
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
import java.awt.Panel;
import javax.swing.JRadioButton;
import java.awt.Choice;
import java.awt.ScrollPane;
import java.awt.Toolkit;

public class Main {

	private JFrame frmGaharuGadingQr;
	private JList<String> listCodes;
	private JComboBox<String> comboBoxSize, comboBoxUrl;
	private JComboBox<GGObject> comboBoxType,comboBoxFarm;
	private JRadioButton rdbtnNew,rdbtnExisting;
	private String qrDir;
	private AppConfig config;
	private static final String GG_WEB_DIR = "http://swopt.gaharu.co/gga/";
	private static final int AGROASS_TREE = 0xA01;
	private static final int AGROASS_HIVE = 0xA02;
	private JFormattedTextField textFieldStart;
	private JScrollPane scrollPane_1;
	private JList<GGObject> listExistingCodes;
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
		frmGaharuGadingQr.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/co/crazytech/ggqr/ui/logo.png")));
		frmGaharuGadingQr.setTitle("Gaharu Gading QR Generator");
		frmGaharuGadingQr.setBounds(100, 100, 450, 300);
		frmGaharuGadingQr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGaharuGadingQr.getContentPane().setLayout(new BorderLayout(0, 0));
		frmGaharuGadingQr.setSize(new Dimension(640, 400));
		
		
		JPanel panelLeft = new JPanel();
		frmGaharuGadingQr.getContentPane().add(panelLeft, BorderLayout.WEST);
		GridBagLayout gbl_panelLeft = new GridBagLayout();
		gbl_panelLeft.columnWidths = new int[]{42, 86, 0};
		gbl_panelLeft.rowHeights = new int[]{0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelLeft.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelLeft.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelLeft.setLayout(gbl_panelLeft);
		
		comboBoxType = new JComboBox<GGObject>();
		GridBagConstraints gbc_comboBoxType = new GridBagConstraints();
		gbc_comboBoxType.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxType.gridx = 0;
		gbc_comboBoxType.gridy = 1;
		Thread prodThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					for (GGObject obj : getProdTypes()) {
						comboBoxType.addItem(obj);
					}
				} catch (ParseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					this.run();
				}
				
			}
		});
		prodThread.start();
		comboBoxType.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				GGObject ggObj = (GGObject)e.getItem();
				if((ggObj.getCode().equals("AA")||ggObj.getCode().equals("BA"))
						&&rdbtnExisting.isSelected())
					try {
						populateExistingChoices();
					} catch (ParseException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		comboBoxUrl = new JComboBox<String>();
		comboBoxUrl.setEditable(true);
		
		try {
			config = loadConfig();
			for (String url : config.getUrls()) {
				comboBoxUrl.addItem(url);
			}
		} catch (JAXBException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		GridBagConstraints gbc_comboBoxUrl = new GridBagConstraints();
		gbc_comboBoxUrl.gridwidth = 2;
		gbc_comboBoxUrl.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxUrl.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUrl.gridx = 0;
		gbc_comboBoxUrl.gridy = 0;
		panelLeft.add(comboBoxUrl, gbc_comboBoxUrl);
		panelLeft.add(comboBoxType, gbc_comboBoxType);
		
		comboBoxSize = new JComboBox<String>();
		comboBoxSize.setEnabled(false);
		GridBagConstraints gbc_comboBoxSize = new GridBagConstraints();
		gbc_comboBoxSize.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSize.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSize.gridx = 1;
		gbc_comboBoxSize.gridy = 1;
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
		
		comboBoxFarm = new JComboBox<GGObject>();
		GridBagConstraints gbc_comboBoxFarm = new GridBagConstraints();
		gbc_comboBoxFarm.gridwidth = 2;
		gbc_comboBoxFarm.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxFarm.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFarm.gridx = 0;
		gbc_comboBoxFarm.gridy = 2;
		panelLeft.add(comboBoxFarm, gbc_comboBoxFarm);
		Thread farmThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					for (GGObject obj : getFarms()) {
						comboBoxFarm.addItem(obj);
					}
				} catch (ParseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					this.run();
				}
				
			}
		});
		farmThread.start();
		Panel panel = new Panel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		panelLeft.add(panel, gbc_panel);
		
		rdbtnNew = new JRadioButton("New");
		rdbtnNew.setSelected(true);
		rdbtnNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnNew.isSelected()) {
					listExistingCodes.setEnabled(false);
					textFieldStart.setEnabled(true);
				}
			}
		});
		rdbtnExisting = new JRadioButton("Existing");
		rdbtnExisting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listExistingCodes.setEnabled(true);
				try {
					populateExistingChoices();
				} catch (ParseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				textFieldStart.setText("1");
				textFieldStart.setEnabled(false);
			}
		});
		
		ButtonGroup btnGrp = new ButtonGroup();
		btnGrp.add(rdbtnNew);btnGrp.add(rdbtnExisting);
		
		panel.add(rdbtnNew);
		panel.add(rdbtnExisting);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 2;
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 4;
		panelLeft.add(scrollPane_1, gbc_scrollPane_1);
		
		listExistingCodes = new JList<GGObject>(new DefaultListModel<GGObject>());
		scrollPane_1.setViewportView(listExistingCodes);
		
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
		gbc_textFieldStart.gridwidth = 2;
		gbc_textFieldStart.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldStart.fill = GridBagConstraints.BOTH;
		gbc_textFieldStart.gridx = 0;
		gbc_textFieldStart.gridy = 6;
		panelLeft.add(textFieldStart, gbc_textFieldStart);
		
		JScrollPane scrollPaneList = new JScrollPane();
		listCodes = new JList<String>(new DefaultListModel<String>());
		scrollPaneList.setViewportView(listCodes);
		GridBagConstraints gbc_list_codes = new GridBagConstraints();
		gbc_list_codes.gridheight = 6;
		gbc_list_codes.gridwidth = 2;
		gbc_list_codes.fill = GridBagConstraints.BOTH;
		gbc_list_codes.gridx = 0;
		gbc_list_codes.gridy = 7;
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
				String[] strings = lm.getElementAt(listCodes.getSelectedIndex()).split(":");
				String selected = strings[strings.length-1];
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
	
	private void populateExistingChoices() throws UnknownHostException, MalformedURLException, ParseException, IOException{
		GGObject ggObj = (GGObject)comboBoxType.getSelectedItem();
		int agroass = AGROASS_TREE;
		DefaultListModel<GGObject> lm = (DefaultListModel<GGObject>)listExistingCodes.getModel();
		lm.removeAllElements();
		if(ggObj.getCode().equals("BA"))agroass = AGROASS_HIVE;
		for (GGObject obj : getAgroassets(agroass)) {
			lm.addElement(obj);
		}
		
		
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
						if(rdbtnExisting.isSelected()){
							for (GGObject ggObject : listExistingCodes.getSelectedValuesList()) {
								drawQR(true,typeCode,farmCode,farmName,typeName,
										ggObject.getTruncatedCode(),ggObject.getDcode(),ggObject.getName());
								lm.addElement(ggObject.getDcode()+":"+ggObject.getName()+":"+"GG"+typeCode+farmCode+ggObject.getTruncatedCode());
							}
							
						}
						else {
							String code = RandomCharacters.randomString(4);
							drawQR(false,typeCode, farmCode,farmName,typeName,code);
							lm.addElement("GG"+typeCode+farmCode+code);
						}
						//String data = "gga_"+countryCode+"_"+String.format("%03d", regionCode)+"_"+String.format("%04d", farmCode)+"_"+type+"_"+colStr+"_"+String.format("%d", j);
						
						
					}
					writeConfig(comboBoxUrl.getSelectedItem().toString());
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
	
	private void drawQR(boolean isExisting, String... params) throws IOException{
		String typeCode = params[0];
		String farmCode = params[1];
		String farmName = params[2];
		String typeName = params[3];
		String code = params[4];
		String data = "GG"+typeCode+farmCode+code;
		URL url = new URL(comboBoxUrl.getSelectedItem().toString()+"?data="+data+"&size=300x300&logo=logo.png");
		BufferedImage image = ImageIO.read(url);
		GraphicsConfiguration config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		BufferedImage altered = config.createCompatibleImage(image.getWidth()+100, image.getHeight());
		Graphics2D rect = altered.createGraphics();
		rect.setColor(Color.WHITE);
		rect.fillRect(0, 0, 100, altered.getHeight());
		rect.drawImage(image, 100, 0, null);
		rect.setFont(rect.getFont().deriveFont(Font.BOLD,10f));
		rect.setColor(Color.BLACK);
		rect.rotate(Math.toRadians(-90));
		rect.drawString(farmName.toUpperCase(), -image.getHeight()+12, 109);
		rect.drawString("ID", -image.getHeight()+12, 100);
		rect.drawString("NICKNAME", -image.getHeight()+110, 100);
		if (isExisting) {
			String dcode = params[5];
			String nickname = params[6];
			rect.setFont(rect.getFont().deriveFont(Font.BOLD,50f));
			rect.setColor(Color.BLACK);
			rect.rotate(Math.toRadians(90));
			rect.drawString(dcode, 10, image.getHeight()-30);
			rect.rotate(Math.toRadians(-90));
			rect.setFont(rect.getFont().deriveFont(Font.BOLD,30f));
			String[] nicknameArr = new String[]{nickname};
			for (int i = 0; i < nickname.length(); ) {
		        int codepoint = nickname.codePointAt(i);
		        i += Character.charCount(codepoint);
		        if (Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HAN) {
		           nicknameArr = new String[]{nickname.substring(0, i-1),nickname.substring(i-1,nickname.length())};
		           break;
		        }
		    }
			rect.drawString(nicknameArr[0], -image.getHeight()+110, 30);
			if (nicknameArr.length>1)rect.drawString(nicknameArr[1], -image.getHeight()+110, 60);
			
		}
		rect.rotate(Math.toRadians(90));
		rect.setFont(rect.getFont().deriveFont(Font.PLAIN,12f));
		rect.drawString(typeName.toUpperCase(), 115, image.getHeight()-2);
		rect.dispose();
		File file = new File(qrDir+"/"+typeCode+"/");
		if (!file.exists()) file.mkdirs();
		ImageIO.write(altered, "png", new File(qrDir+"/"+typeCode+"/"+data+".png"));
	}
	
	private AppConfig loadConfig() throws JAXBException,IOException{
		JAXBContext jc = JAXBContext.newInstance(AppConfig.class);
		AppConfig config = new AppConfig();
		if(new File("config.dat").exists()){
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			String xmlStr = IOUtil.readFile("config.dat");
			StringReader reader = new StringReader(xmlStr);
			config = (AppConfig) unmarshaller.unmarshal(reader);
		} else {
			Marshaller marshaller = jc.createMarshaller();
			List<String> urls = new ArrayList<String>();
			urls.add("http://swopt.crazytech.co/qr.php");
			config.setUrls(urls);
			marshaller.marshal(config, new File("config.dat"));
		}
		return config;
	}
	
	private void writeConfig(String url) {
		if(!config.getUrls().contains(url)){
			config.getUrls().add(url);
			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance(AppConfig.class);
				Marshaller marshaller = jaxbContext.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				marshaller.marshal(config, new File("config.dat"));
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private List<GGObject> ggObjs(String table) throws UnknownHostException, MalformedURLException, ParseException, IOException {
		List<GGObject> prodTypes = new ArrayList<GGObject>();
		JsonParser jsonParser = new JsonParser(GG_WEB_DIR+"get_codes.php?table="+table);
		JSONObject jsonObj = jsonParser.parse();
		JSONArray jsonArr = (JSONArray)jsonObj.get("results");
		for (Object object : jsonArr) {
			GGObject ggo = new GGObject();
			JSONObject arrObj = (JSONObject)object;
			ggo.setId(new BigInteger((String)arrObj.get("id")));
			byte[] nameByte = ((String)arrObj.get("name")).getBytes("UTF8");
			ggo.setName(new String(nameByte,"UTF8"));
			ggo.setCode((String)arrObj.get("code"));
			if(table.equals("agroasset")||table.equals("v_hive")||table.equals("v_tree"))
				ggo.setDcode((String)arrObj.get("dcode"));
			prodTypes.add(ggo);
		}
		Collections.sort(prodTypes, new Comparator<GGObject>() {
		    @Override
		    public int compare(GGObject o1, GGObject o2) {
		    	if(table.equals("agroasset")||table.equals("v_hive")||table.equals("v_tree")){
		    		int dcode1 = Integer.parseInt(o1.getDcode());
		    		int dcode2 = Integer.parseInt(o2.getDcode());
		    		if (dcode1 == dcode2) return 0;
                    else if (dcode1 < dcode2)return -1;
                    return 1;
		    	}
		    		
		        return o1.getName().compareTo(o2.getName());
		    }
		});
		return prodTypes;
	}
	
	private List<GGObject> getAgroassets(int agroass) throws UnknownHostException, MalformedURLException, ParseException, IOException{
		switch(agroass){
			case AGROASS_HIVE: return ggObjs("v_hive");
			case AGROASS_TREE: return ggObjs("v_tree");
		}
		return ggObjs("v_tree");
	}
	
	private List<GGObject> getProdTypes() throws UnknownHostException, MalformedURLException, ParseException, IOException{
		return ggObjs("prod_type");
	}
	
	private List<GGObject> getFarms() throws UnknownHostException, MalformedURLException, ParseException, IOException{
		return ggObjs("farm");
	}
}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.io.*;

import javax.swing.*;

//import PasswordHandler.ButtonListener;


public class SystemGUI extends  JPanel implements ItemListener {
	
	final static String NOTHING = " ";
	final static String BUTTONPANEL = "Card with JButtons";
	final static String TEXTPANEL = "Card with JTextField";
	
	private PersonalLogins user;
	JTabbedPane tabbedPane;
	
	Container panel1;
	Container panel2;
	Container panel3;
	Container panel4;
	
	JTextField ss;
	JTextField sq;
	JTextField ser;
	JTextField something;
	JTextField username;
	JTextField password;
	JTextField wName;
	JTextField aName;
	JTextField web;
	
	String comboBoxItems1[];
	String comboBoxItems2[];
	
	JComboBox<String> cb1;
	JComboBox<String> cb2;
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String s = e.getActionCommand();
			if(s == "Search") {
				Boolean stop = false;
				int nr = -1;
				String str = ser.getText();
				clean();
				for(int i=0; i< user.getSize() && !stop;i++) {
					if(str.compareTo(user.webbLogsSearch(i))==0) {
						nr = i;
						stop = true;
					}
					if(str.compareTo(user.appLogsSearch(i))==0) {
						nr = i;
						stop = true;
					}
				}
				if(nr != -1)
					something.setText(user.getInfo(nr));
				else 
					clean();
			}
			if (s == "Add Webpage") {
				user.addWebb(username.getText(), password.getText(), wName.getText(), web.getText());
				update(1);
			}
			if(s == "Add App") {
				user.addApp(username.getText(), password.getText(), aName.getText());
				update(2);
			}
			if(s == "Remove") {
				String str = wName.getText();
				if((str == "Your web page name goes here") || (str == "")) {
					String str2 = aName.getText();
					user.remove(str2);
					update(2);
				}
				else {
					System.out.println("Removes");
					user.remove(str);
					update(1);
				}
				
				clean2();
			}
		}
		
	}
	private void update(int type) {
		if(type ==1) {
			panel1.removeAll();
			tabbedPane.remove(panel1);
			ImageIcon icon = createImageIcon("images/middle.gif");
			setPanel1(icon);
			clean2();
		}
		if(type == 2) {
			panel2.removeAll();
			tabbedPane.remove(panel2);
			ImageIcon icon = createImageIcon("images/middle.gif");
			setPanel2(icon);
			clean2();
		}
		saveToFile(this.user.getSystemUsr());
	}

	public void clean2() {
		// TODO Auto-generated method stub
		username.setText("");
		password.setText("");
		wName.setText("");
		aName.setText("");
		web.setText("");
	}

	public void clean() {
		// TODO Auto-generated method stub
		ser.setText("");
		something.setText("");
	}

	public SystemGUI(PersonalLogins theUser) {
		// TODO Auto-generated constructor stub
		super(new GridLayout(1, 1));
		setSize(500,300);
		this.user = theUser;
		try {
			readFromFile(this.user.getSystemUsr());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		tabbedPane = new JTabbedPane(SwingConstants.TOP);//JTabbedPane.SCROLL_TAB_LAYOUT
		ImageIcon icon = createImageIcon("images/middle.gif");
		
		setPanel1(icon);
		
		
		//tab2
		setPanel2(icon);

		// tab3
		panel3 = makeTextPanel("Panel #3");
		panel3 = new JPanel();
		ser = new JTextField("Logins", 70);
		ser.setBorder(BorderFactory.createTitledBorder("Web or App name you want to find"));
		something = new JTextField("Your Login will Appear here", 100);
		JButton search = new JButton("Search");
		ButtonListener buttList = new ButtonListener();
		search.addActionListener(buttList);
		
		panel3.add(ser);
		panel3.add(search);
		panel3.add(something);
		tabbedPane.addTab("Search", icon, panel3,
		                  "Search");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		// tab4
		panel4 = new JPanel();
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.PAGE_AXIS));
		panel4.setPreferredSize(new Dimension(600, 400));
		username = new JTextField("xxxx..", 70);
		username.setBorder(BorderFactory.createTitledBorder("Your Username goes here"));
		password = new JTextField("xxxx...", 70);
		password.setBorder(BorderFactory.createTitledBorder("Your password goes here"));
		wName = new JTextField("xxxx", 70);
		wName.setBorder(BorderFactory.createTitledBorder("Your web page name goes here"));
		aName = new JTextField("xxxx", 70);
		aName.setBorder(BorderFactory.createTitledBorder("Your app name goes here"));
		web = new JTextField("xxxx.com", 70);
		web.setBorder(BorderFactory.createTitledBorder("Your webpage goes here"));
		JButton addweb = new JButton("Add Webpage");
		ButtonListener buttList2 = new ButtonListener();
		addweb.addActionListener(buttList2);
		JButton addapp = new JButton("Add App");
		addapp.addActionListener(buttList2);
		JButton rem = new JButton("Remove");
		rem.addActionListener(buttList2);
		
		panel4.add(username);
		panel4.add(password);
		panel4.add(wName);
		panel4.add(web);
		panel4.add(aName);
		panel4.add(addweb);
		panel4.add(addapp);
		panel4.add(rem);
		panel4.setFocusable(false);
		((JComponent) this.panel4).setBorder(BorderFactory.createEmptyBorder(50,300,520,300));
		
		
		tabbedPane.addTab("Menage", icon, panel4, "Menage");
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
		
		//add tabbed pane to panel
        add(tabbedPane);
         
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        saveToFile(this.user.getSystemUsr());
	}
	private void setPanel1(ImageIcon icon) {
		panel1 = new JPanel();
		comboBoxItems1 = new String[this.user.websize+1];
		comboBoxItems1[0] =  NOTHING;
		int j =1;
		for(int i = 0; i< this.user.getSize();i++){
			if(this.user.getType(i) == 1) {
				System.out.println(this.user.webbLogsSearch(i));
				comboBoxItems1[j] = this.user.webbLogsSearch(i);
				j++;
			}
			
		}
		
		cb1 = new JComboBox<String>(comboBoxItems1);
		cb1.setEditable(false);
		cb1.addItemListener((ItemListener) this);
		ss = new JTextField("Logins", 70);
		panel1.add(cb1);
		panel1.add(ss);
		
		tabbedPane.addTab("Web Logins", icon, panel1,
		                  "Web-pane");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
	}
	
	private void setPanel2(ImageIcon icon) {
		panel2 = new JPanel();
		comboBoxItems2 = new String[this.user.appsize+1];
		comboBoxItems2[0] = NOTHING;
		int j=1;
		for(int i = 0; i< this.user.getSize();i++){
			if(this.user.getType(i) == 2) {
				comboBoxItems2[j] = this.user.appLogsSearch(i);
				j++;
			}
			
		}
		cb2 = new JComboBox<String>(comboBoxItems2);
		cb2.setEditable(false);
		cb2.addItemListener((ItemListener) this);
		sq = new JTextField("Logins", 70);
		panel2.add(cb2);
		panel2.add(sq);
		
		tabbedPane.addTab("App Logins", icon, panel2,
		                  "App-pane");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
	}
	
	protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
	
	protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = SystemGUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
	public void createAndShowGUI() {
        JFrame frame = new JFrame("Password Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(this, BorderLayout.CENTER);
        
        frame.pack();
        frame.setVisible(true);
    }
	
	public static void main(String[] args) {
		PasswordHandler gui = new PasswordHandler();
		gui.setVisible(true);
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		String s =(String) e.getItem();
		for(int i=0;i<this.user.getSize();i++) {
			if(this.user.webbLogsSearch(i) == s) {
				ss.setText(this.user.getInfo(i));
			}
			if(this.user.appLogsSearch(i) == s) {
				sq.setText(this.user.getInfo(i));
			}
		}
		
	}
	
	private void saveToFile(String usrName) {
		String filename = usrName+".txt";
		
		try {
			FileWriter fileWriter = new FileWriter(filename);
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			
			for(int i=0;i< this.user.getSize();i++) {
				bufferWriter.write(this.user.getUsrName(i));
				bufferWriter.newLine();
				bufferWriter.write(this.user.getUsrPass(i));
				bufferWriter.newLine();
				int nr = this.user.getType(i);
				bufferWriter.write(Integer.toString(nr));
				bufferWriter.newLine();
				if(this.user.getType(i) == 1) {
					bufferWriter.write(this.user.getWebName(i));
					bufferWriter.newLine();
					bufferWriter.write(this.user.getWeb(i));
				} else {
					bufferWriter.write(this.user.getAppName(i));
				}
				bufferWriter.newLine();
			}
			bufferWriter.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error writing to file '" + filename + "'");
		}
		try {
			encrypt(usrName);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	private void readFromFile(String name) throws CryptoExe {
		String filename = name + ".txt";
		String line=null;
		
		decrypt(name);
		
		try {
			FileReader fileReader = new FileReader(filename);
			 BufferedReader bufferReader = new BufferedReader(fileReader);
			 while((line = bufferReader.readLine()) != null) {
				 String usrN = line;
				 line = bufferReader.readLine();
				 String usrP = line;
				 line = bufferReader.readLine();
				 String type = line;
				 if(type.compareTo("1")== 0) {
					 line = bufferReader.readLine();
					 String wName = line;
					 line = bufferReader.readLine();
					 String web = line;
					 this.user.addWebb(usrN, usrP, wName, web);
				 } else {
					 line = bufferReader.readLine();
					 String appname = line;
					 this.user.addApp(usrN, usrP, appname);
				 }
			 }
			 bufferReader.close();
		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Something went horribly wrong");
		}
	}
	
	private void encrypt(String fileName) {
		String key = "Patryk Sulewski1";
		File input = new File(fileName +".txt");
		File encrFile = new File(fileName+ ".txt");
		try {
			CryptFile.encrypt(key, input, encrFile);
			System.out.println("Worked!");
		} catch (CryptoExe e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	private void decrypt(String fileName) throws CryptoExe {
		String key = "Patryk Sulewski1";
		File encrFile = new File(fileName+ ".txt");
		File decrFile = new File(fileName+ ".txt");
		
		CryptFile.decrypt(key, encrFile, decrFile);
		System.out.println("See if it worked?");
	}
	
}

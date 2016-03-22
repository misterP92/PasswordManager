import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.*;

//import GUIBottomKlickCounter.ButtonListener;


public class PasswordHandler extends JFrame {

	private static final String NAME = "accaunts";
	private JTextField textField[];
	private Container contentPane;
	private ArrayList<PersonalLogins> accounts;
	private int q = -2;
	
	private Container regContent;
	private JTextField regField[];
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String com = e.getActionCommand();
			System.out.println(q);
			System.out.println(com);
			switch (com) {
			case "Login":
				try {
					loginToSystem();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "Register":
				regInterface();
				break;
			case "Reset Password":
				System.out.println("HEj");
				reset();
				break;
				
			default:
				break;
			}
		}
	}
	
	private void clearTextFields()
	{
		for(int i=0; i< this.textField.length; i++)
			this.textField[i].setText("");
	}
	private void clearRegFields() {
		for(int i=0; i< this.regField.length;i++)
			this.regField[i].setText("");
	}
	public void reset() {
		// TODO Auto-generated method stub
		System.out.println(q);
		if(q > -1) {
			System.out.println("WHat is going on");
			
			String answ = this.regField[4].getText();
			System.out.println(answ);
			boolean ok = this.accounts.get(q).reset(answ);
			if(ok) {
				this.regField[2].setText(this.accounts.get(q).getStystemPass());
				this.regField[3].setText("");
				this.regField[4].setText("");
			}
		}
		
	}
	public void regInterface() {
		// TODO Auto-generated method stub
		if(this.regField[1] != null) {
			String user = this.regField[1].getText();
			int couter = 0;
			String pass = this.regField[2].getText();
			int i=0;
			for(int j=0; j < this.accounts.size();j++) {
				if(user.compareTo(this.accounts.get(j).getSystemUsr())== 0)
					i = pass.length();
			}
			while(i < pass.length()) {
				char ch = pass.charAt(i);
				String str = ""+ch;
				if(isInteger(str)) {
					couter++;
				}
				i++;
			}
			if((couter > 1) && (i > 5)) {
				accounts.add(new PersonalLogins(this.regField[0].getText(),this.regField[1].getText(),
						this.regField[2].getText(),this.regField[3].getText(),this.regField[4].getText()));
				JOptionPane.showMessageDialog(this, "You have been registred!");
				clearRegFields();
				
			} else {
				JOptionPane.showMessageDialog(this, "Wrong Password! \nThe password should contain at least 2 numbers and consist of\n at least 6 charackters.");
			}
		}
		
	}
	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}
	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
	
	

	public void loginToSystem() throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String name= "ps";
		String pas = "asd";
		String tes = this.textField[0].getText();
		String tes2 = this.textField[1].getText();
		int result = -2;
		this.q = -2;
		for(int i=0;i< this.accounts.size() && q == -2 && result != 0;i++) {
			if (tes.compareTo(this.accounts.get(i).getSystemUsr())== 0) {
				result = this.accounts.get(i).valid(tes, tes2);
				
				if(result == -1 || result == 0) {
					q = i;
				} else if(result == 1) {
					q = -1;
				}
			}
		}
		if(result == 0) {
			saveToFile(NAME);
			this.encrypt(NAME);
			setVisible(false);
			SystemGUI system = new SystemGUI(this.ret(q));
			system.createAndShowGUI();
		} 
		if(result == 1) {
			JOptionPane.showMessageDialog(this, "Wrong Password or Username!");
			
		} if (result == -1) {
			JOptionPane.showMessageDialog(this, "You have been banned!\nAnswer the question showned");
			this.regField[3].setText(this.accounts.get(q).getQuestion());
			
		}
		clearTextFields();
	}
	public int ok() {
		return q;
		
	}
	private PersonalLogins ret(int i) {
		return this.accounts.get(i);
	}

	public PasswordHandler() {
		// TODO Auto-generated constructor stub
		this.accounts = new ArrayList<PersonalLogins>();
		try {
			decrypt(NAME);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		try {
			
			readFromFile(NAME);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		setTitle("Password Manager");
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		textField = new JTextField[2];
		textField[0] = new JTextField(10);
		textField[0].setBounds(20, 110, 260, 50);
		this.textField[0].setBorder(BorderFactory.createTitledBorder("Username"));
		textField[1] = new JTextField(10);
		textField[1].setBounds(20, 180, 260, 50);
		this.textField[1].setBorder(BorderFactory.createTitledBorder("Password"));
		
		this.contentPane = getContentPane();
		this.contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		
		JButton log = new JButton("Login");
		ButtonListener buttList = new ButtonListener();
		log.addActionListener(buttList);
		
		this.contentPane.add(this.textField[0]);
		this.contentPane.add(this.textField[1]);
		this.contentPane.add(log);
		registerFields();
		((JComponent) this.contentPane).setBorder(BorderFactory.createEmptyBorder(50,20,50,20));
		
	}
	
	private void registerFields() {
		this.regContent = new JPanel();
		this.regContent.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		this.regField = new JTextField[5];
		for(int i=0; i<5;i++)
			this.regField[i] = new JTextField(10);
		this.regField[0].setBorder(BorderFactory.createTitledBorder("Your Name"));
		this.regField[1].setBorder(BorderFactory.createTitledBorder("Your Username"));
		this.regField[2].setBorder(BorderFactory.createTitledBorder("Your Password"));
		this.regField[3].setBorder(BorderFactory.createTitledBorder("Your Question"));
		this.regField[4].setBorder(BorderFactory.createTitledBorder("Your Answer to the Question"));
		
		JButton regg = new JButton("Register");
		ButtonListener buttList = new ButtonListener();
		regg.addActionListener(buttList);
		JButton reset = new JButton("Reset Password");
		reset.addActionListener(buttList);
		
		contentPane.add(this.regField[0]);
		contentPane.add(this.regField[1]);
		contentPane.add(this.regField[2]);
		contentPane.add(this.regField[3]);
		contentPane.add(this.regField[4]);
		contentPane.add(regg);
		contentPane.add(reset);
	}

	private void saveToFile(String name) throws UnsupportedEncodingException {
		String filename = name +".txt";
		
		try {
			FileWriter fileWriter = new FileWriter(filename);
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			
			for(int i=0;i< this.accounts.size();i++) {
				bufferWriter.write(this.accounts.get(i).getName());
				bufferWriter.newLine();
				bufferWriter.write(this.accounts.get(i).getSystemUsr());
				bufferWriter.newLine();
				bufferWriter.write(this.accounts.get(i).getStystemPass());
				bufferWriter.newLine();
				bufferWriter.write(this.accounts.get(i).getQuestion());
				bufferWriter.newLine();
				bufferWriter.write(this.accounts.get(i).getAnsw());
				bufferWriter.newLine();
			}
			bufferWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error writing to file '" + filename + "'");
		}
	}
	
	private void encrypt(String filename) throws UnsupportedEncodingException {
		String key = "Patryk Sulewski1";
		File input = new File(filename +".txt");
		File encrFile = new File(filename+ ".txt");
		try {
			CryptFile.encrypt(key, input, encrFile);
		} catch (CryptoExe e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	private void decrypt(String filename) throws CryptoExe {
		String key = "Patryk Sulewski1";
		File encrFile = new File(filename+ ".txt");
		File decrFile = new File(filename+ ".txt");
		
		CryptFile.decrypt(key, encrFile, decrFile);
	}
	
	private void readFromFile(String name) throws CryptoExe {
		String filename = name + ".txt";
		String line=null;
		
		try {
			int i =0;
			FileReader fileReader = new FileReader(filename);
			 BufferedReader bufferReader = new BufferedReader(fileReader);
			 while((line = bufferReader.readLine()) != null) {
				 String tName = line;
				 line = bufferReader.readLine();
				 String usrN = line;
				 line = bufferReader.readLine();
				 String usrP = line;
				 line = bufferReader.readLine();
				 String ques = line;
				 line = bufferReader.readLine();
				 String answ = line;
				 this.accounts.add(new PersonalLogins(tName, usrN, usrP, ques, answ));
				 System.out.println(this.accounts.get(i).getSystemUsr());
				 i++;
			 }
			 bufferReader.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Something went horribly wrong");
		}
	}

}

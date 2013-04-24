package frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JButton;

import util.Client;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainFrame {

	private JFrame frame;
	private JTextField loginEditText;
	private JTextField passwordEditText;
	private JTextPane editError;
	
	private Client client;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {		
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		initialize();
	}

	private void initialize() {
		client = new Client();
		
		new Thread(new Runnable() {	
			@Override
			public void run() {
				try {
					client.connection();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);  
		frame.getContentPane().setLayout(null);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		loginEditText = new JTextField();
		loginEditText.setToolTipText("");
		loginEditText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		loginEditText.setBounds(113, 62, 215, 36);
		frame.getContentPane().add(loginEditText);
		loginEditText.setColumns(20);
		
		passwordEditText = new JTextField();
		passwordEditText.setToolTipText("");
		passwordEditText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordEditText.setColumns(20);
		passwordEditText.setBounds(113, 150, 215, 36);
		frame.getContentPane().add(passwordEditText);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(Color.WHITE);
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPane.setText("\u041F\u0430\u0440\u043E\u043B\u044C");
		textPane.setBounds(185, 113, 60, 26);
		frame.getContentPane().add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setText("\u041B\u043E\u0433\u0456\u043D");
		textPane_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPane_1.setBackground(Color.WHITE);
		textPane_1.setBounds(185, 25, 51, 26);
		frame.getContentPane().add(textPane_1);
		
		JButton button = new JButton("\u0412\u0445\u0456\u0434");
		button.addActionListener(buttonClick());
		button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button.setBounds(169, 197, 94, 36);
		frame.getContentPane().add(button);
		
		editError = new JTextPane();
		editError.setForeground(Color.RED);
		editError.setText("\u041E\u0434\u043D\u0435 \u0437 \u043F\u043E\u043B\u0456\u0432 \u043D\u0435 \u0437\u0430\u043F\u043E\u0432\u043D\u0435\u043D\u0435");
		editError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		editError.setEditable(false);
		editError.setBackground(Color.WHITE);
		editError.setBounds(123, 244, 205, 26);
		editError.setVisible(false);
		frame.getContentPane().add(editError);
	}

	private ActionListener buttonClick() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String login = loginEditText.getText();
				String password = passwordEditText.getText();
				
				if(login.equals("") || password.equals("")){
					editError.setVisible(true);
				}
				else{	
					System.out.println("Client connection");
					client.sendMessage(login, password); 
				}
			}
		};
	}
}

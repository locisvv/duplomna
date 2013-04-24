package frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Journal;
import model.User;

import dao.JournalDAOImpl;
import dao.UserDAOImpl;
import javax.swing.JButton;
import java.io.IOException;

import util.Server;
import javax.swing.JTabbedPane;

public class MainFrame {
	public MyTableModel myTableModel;
	public ArrayList<User> users;
	
	public JournalTableModel journalTableModel;
	public ArrayList<Journal> journals;
	
	public MyTableModel usersOnlineTableModel;
	public ArrayList<User> usersOnline;
	
	private JFrame frame;
	private JTable table;
	private Thread serverThread;
	private JButton btnNewButton;
	
	
	/**
	 * Launch the application.
	 */
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
	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Меню");
		menuBar.add(mnMenu);
		
		JMenuItem mntmExit = new JMenuItem("Вихід");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnMenu.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Редагувати");
		menuBar.add(mnEdit);
		
		JMenuItem menuItem = new JMenuItem("Додати користувача");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddUserFrame addUserFrame = new AddUserFrame(MainFrame.this);
				addUserFrame.setVisible(true);
			}
		});
		
		mnEdit.add(menuItem);
		
		JMenu mnHelp = new JMenu("Допомога");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelp = new JMenuItem("Про програму");
		mnHelp.add(mntmHelp);
		
		frame.getContentPane().setLayout(null);
		
		btnNewButton = new JButton("Запустити сервер");
		btnNewButton.addActionListener(startServerButton());
		btnNewButton.setBounds(125, 0, 150, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Додати користувача");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddUserFrame addUserFrame = new AddUserFrame(MainFrame.this);
				addUserFrame.setVisible(true);
			}
		});
		
		btnNewButton_1.setBounds(285, 0, 150, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(20, 34, 501, 281);
		
		frame.getContentPane().add(tabbedPane);
		
		usersOnline = new ArrayList<User>();
		usersOnlineTableModel = new MyTableModel(usersOnline);
		JTable jTable = new JTable(usersOnlineTableModel);
		JScrollPane jscrlp2 = new JScrollPane(jTable);
		tabbedPane.addTab("Підключені користувачі", null, jscrlp2, null);

		jTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 2 ){
						JTable obj = (JTable)e.getSource();
						
						System.out.println("obj.getSelectedRow()="+obj.getSelectedRow());
					}	
			}
		});
		
		users = getUsers();
		myTableModel = new MyTableModel(users);
		table = new JTable(myTableModel);
		JScrollPane jscrlp = new JScrollPane(table);
		tabbedPane.addTab("Список користувачів", null, jscrlp, null);
		
		journals = getJournals();
		journalTableModel = new JournalTableModel(journals);
		JTable journalTable = new JTable(journalTableModel);
		JScrollPane journalScrollPanel = new JScrollPane(journalTable);
		tabbedPane.addTab("Журнал", null, journalScrollPanel, null);
	}
	
	private ActionListener startServerButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				
				if(serverThread == null){
					btnNewButton.setText("Зупинити сервер");
					serverThread = new Thread(new Runnable() {	
						@Override
						public void run() {
							Server server = new Server(MainFrame.this);
							try {
								server.startServer();
							} catch (IOException e) {
								JOptionPane.showMessageDialog(null, e.getMessage(), "Помилка запуску сервера", JOptionPane.OK_OPTION);
							}
						}
					});
					
					serverThread.start();
				}
				else{
					serverThread.stop();
					serverThread = null;
					System.out.println("Зупинка сервера... \nСервур зупинено");
					btnNewButton.setText("Запустити сервер");
				}
			}
		};
	}

	public ArrayList<User> getUsers(){
		UserDAOImpl dao = new UserDAOImpl();
		ArrayList<User> usersList = new ArrayList<User>();
		try {
			usersList = dao.getAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}
	
	public ArrayList<Journal> getJournals(){
		JournalDAOImpl dao = new JournalDAOImpl();
		ArrayList<Journal> journals = new ArrayList<Journal>();
		try {
			journals = dao.getAllItem();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return journals;
	}
}

package frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

import model.User;

import dao.UserDAOImpl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AddUserFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the frame.
	 */
	public AddUserFrame(final MainFrame mainFrame) {
		
		setBounds(100, 100, 245, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(109, 28, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(109, 59, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(109, 90, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(109, 121, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton button = new JButton("Додати користувача");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				
				user.setFirstName(textField.getText());
				user.setName(textField_1.getText());
				user.setLogin(textField_2.getText());
				user.setPassword(textField_3.getText());
				
				UserDAOImpl userDAOImpl = new UserDAOImpl();
				try {
					userDAOImpl.addUser(user);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				mainFrame.users.add(user);
				mainFrame.myTableModel.fireTableDataChanged();
				
				AddUserFrame.this.setVisible(false);
			}
		});
		button.setBounds(37, 158, 158, 23);
		contentPane.add(button);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setText("Імя");
		textPane.setBounds(37, 28, 62, 20);
		contentPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setText("Прізвище");
		textPane_1.setBounds(37, 59, 62, 20);
		contentPane.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setText("Логін");
		textPane_2.setBounds(37, 90, 62, 20);
		contentPane.add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setEditable(false);
		textPane_3.setText("Пароль");
		textPane_3.setBounds(37, 121, 62, 20);
		contentPane.add(textPane_3);
	}
}

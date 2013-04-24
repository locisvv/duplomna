package frame;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.User;

public class MyTableModel extends AbstractTableModel {

	ArrayList<User> users = new ArrayList<User>();
	
	public MyTableModel(ArrayList<User> users){
		this.users = users;
	}
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return users.size();
	}

	@Override
	public Object getValueAt(int row, int colum) {
		switch (colum) {
		case 0:
			return ++row;
		case 1:
			return users.get(row).getFirstName();
		case 2:
			return users.get(row).getName();
		case 3:
			return users.get(row).getLogin();

		default:
			return "";
		}
	}
	
	@Override
	public String getColumnName(int colum) {
		String result = "";
	    switch (colum) {
	        case 0:
	            result = "№";
	            break;
	        case 1:
	            result = "Ім'я";
	            break;
	        case 2:
	            result = "Прізвище";
	            break;
	        case 3:
	            result = "Login";
	            break;
	    }
	    return result;
	}

}

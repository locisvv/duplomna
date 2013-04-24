package frame;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Journal;
import model.User;

public class JournalTableModel extends AbstractTableModel {

	ArrayList<Journal> journals = new ArrayList<Journal>();
	
	public JournalTableModel(ArrayList<Journal> journals){
		this.journals = journals;
	}
	
	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return journals.size();
	}

	@Override
	public Object getValueAt(int row, int colum) {
		switch (colum) {
		case 0:
			return ++row;
		case 1:
			return journals.get(row).getDateTime();
		case 2:
			return journals.get(row).getTime();
		case 3:
			return journals.get(row).getUser();
		case 4:
			return journals.get(row).getAction();

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
	            result = "Дата";
	            break;
	        case 2:
	            result = "Час";
	            break;
	        case 3:
	            result = "Логін";
	            break;
	        case 4:
	            result = "Статус";
	            break;
	    }
	    return result;
	}
}

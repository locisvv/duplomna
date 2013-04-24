package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Journal;
import model.User;

import dao.JournalDAOImpl;
import dao.UserDAOImpl;
import frame.JournalTableModel;
import frame.MainFrame;

public class Server {
	public static final int PORT = 8080;
	private MainFrame frame;
	
	public Server(MainFrame frame){
		this.frame = frame;
	}
	public void startServer() throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Started: " + s);
		try {
			Socket socket = s.accept();
			try {
            
				System.out.println("Connection accepted: " + socket);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            
				while (true) {
					String str = in.readLine();
					
	               if(str != null){
	            	   System.out.println(str);
		               String[] array = str.split("[,]");
		               String isSucces = String.valueOf(checkUser(array));
		               out.println(isSucces);
		               System.out.println(isSucces);
		               out.flush();
		               System.out.println("Echoing: " + array[0] + ": " + array[1]);
	               }         
	            }
	         }
	         finally {
	            System.out.println("закриття...");
	            socket.close();
	         }
	      }
	      finally {
	         s.close();
	      }
	}
	
	private boolean checkUser(String[] array){
		UserDAOImpl dao = new UserDAOImpl();
		
		try {
			User user = dao.getUserByLogin(array[0]);
			
			if(user != null && user.getLogin().equals(array[0]) && user.getPassword().equals(array[1])){
				
				SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
				SimpleDateFormat time = new SimpleDateFormat("HH:mm");
				
				Journal journal = new Journal();
				journal.setDateTime(date.format(new Date()));
				journal.setTime(time.format(new Date()));
				journal.setUser(user.getLogin());
				journal.setAction("користувач залогінився");
				
				JournalDAOImpl journalDAOImpl = new JournalDAOImpl();
				journalDAOImpl.addItem(journal);
				
				frame.journals.add(journal);
				frame.journalTableModel.fireTableDataChanged();
				
				frame.usersOnline.add(user);
				frame.usersOnlineTableModel.fireTableDataChanged();
				
		     	return true;
	        }
	        else{
	        	return false;
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
}
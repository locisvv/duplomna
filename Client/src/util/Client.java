package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {
	
	private BufferedReader in;
	private PrintWriter out;
	private Socket socket;
	
	public Client(){
		InetAddress addr;
		
		try{
			addr = InetAddress.getByName(null);
	
			System.out.println("addr = " + addr);
		
			socket = new Socket(addr, 8080);
		}
		catch (IOException e){
			System.out.println("Невдала спроба підключення...");
		}
	}
	
	public boolean connection() throws IOException{
		boolean isSucces = false;
		
		try {
			System.out.println("socket = " + socket);
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

			while(true){
	            String str = in.readLine();
	            System.out.println("Response: " + str);
				
	            if(str != null){
	            	isSucces = Boolean.parseBoolean(str);
	            	closeFrame(isSucces);
            		if(isSucces){
            			break;
	            	}
	            }
			}
		}
		finally {
			System.out.println("closing...");
		}
		return isSucces;
	}
	
	public void sendMessage(String login, String password){
		out.println(login + "," + password);
		out.flush();
		
		System.out.println("server>" + login + " - " + password);
	}
	
	private void closeFrame(boolean isSucces){
		if(isSucces){
			System.out.println("Exit");
			
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			JOptionPane.showMessageDialog(null, "Ласкаво просимо", "Авторизація пройшла успішно", JOptionPane.OK_OPTION);
			System.exit(1);
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Невдала спроба", "Попробуйте ще раз", JOptionPane.OK_OPTION);
		}
	}
}
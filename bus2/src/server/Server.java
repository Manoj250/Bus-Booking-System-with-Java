package server;

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Server {
	public static void main(String[] args) throws IOException {

		ServerSocket ss = new ServerSocket(5056);

		while (true) {
			Socket s = null;

			try {
				s = ss.accept();

				System.out.println("A new client is connected : " + s);

				DataInputStream dis = new DataInputStream(s.getInputStream());
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());

				System.out.println("Assigning new thread for this client");

				Thread t = new ClientHandler(s, dis, dos);

				t.start();

			} catch (Exception e) {
				s.close();
				e.printStackTrace();
			}
		}
	}
}

class ClientHandler extends Thread {
	final DataInputStream dis;
	final DataOutputStream dos;
	final Socket s;
	final ObjectInputStream oin;
	final ObjectOutputStream out;
	final Database db = new Database();
	// Constructor
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) throws IOException {
		this.s = s;
		this.dis = dis;
		this.dos = dos;
		this.oin = new ObjectInputStream(s.getInputStream());
		this.out = new ObjectOutputStream(s.getOutputStream());
	}

	@Override
	public void run() {
		String[] received;
		
		while (true) {
			try {
				received = (String[]) oin.readObject();

				if (received[0].equals("userLogin")) {
					boolean result = db.checkLogin(received[2],received[1]);
					if(result) {
						dos.writeUTF("yes");
					}
					else {
						dos.writeUTF("no");
					}
				}
				
				else if(received[0].equals("getFrom")) {
					String[] r = db.fromList();
					out.writeObject(r);
				}
				
				else if(received[0].equals("getTo")) {
					String[] r = db.toList();
					out.writeObject(r);
				}
				
				else if (received[0].equals("adminLogin")) {
//					{ "adminLogin", name, pasw }
					if(received[1].equals("admin") && received[2].equals("admin")) {
						dos.writeUTF("yes");
					}
					else {
						dos.writeUTF("no");
					}
				}

				else if (received[0].equals("submit")) {

				}
				
				else if (received[0].equals("passengerDetails")) {

				}

				else if (received[0].equals("addEmployee")) {
					String args[] = {received[1],received[2],received[3]};
					boolean result = db.insertEmployee(args);
					if(result) {
						dos.writeUTF("yes");
					}
					else {
						dos.writeUTF("no");
					}
				}

				else if (received[0].equals("addVehicle")) {
					String args[] = {received[1],received[2],received[3],received[4],received[5]};
					boolean result = db.insertVehicle(args);
					if(result) {
						dos.writeUTF("yes");
					}
					else {
						dos.writeUTF("no");
					}
				}

				else if (received[0].equals("totalCollection")) {

				}
				
				else if (received[0].equals("newAccount")) {
//					{ "newAccount", un, pasw, state, dst, pin, dob, pn, gender };
					String args[] = {received[1],received[2],received[3],received[4],received[5],received[6],received[7],received[8]};
					boolean result = db.insertClients(args);
					if(result) {
						dos.writeUTF("yes");
					}
					else {
						dos.writeUTF("no");
					}}
					
					else if (received[0].equals("removeEmployee")) {
					
						boolean result = db.deleteEmployee(received[1]);
						if(result) {
							dos.writeUTF("yes");
						}
						else {
							dos.writeUTF("no");
						}}
				
					else if (received[0].equals("removeVehicle")) {
						
						boolean result = db.deleteVehicle(received[1]);
						if(result) {
							dos.writeUTF("yes");
						}
						else {
							dos.writeUTF("no");
						}}
				
				
				}
				


//				if (received.equals("Exit")) {
//					System.out.println("bi " + this.s);
//					this.s.close();
//					break;
//				}
			 catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
	}}
//		try {
//			// closing resources
//			this.dis.close();
//			this.dos.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}



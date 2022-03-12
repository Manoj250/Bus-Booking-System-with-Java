package client;

import javax.swing.*;
import java.awt.event.*;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Entry extends JFrame {
	InetAddress ip;
	Socket s;
	DataInputStream dis;
	DataOutputStream dos;
	ObjectOutputStream out;
	ObjectInputStream oin;

	private intro introPage = new intro();
	private login loginPage = new login();
	private client clientPage;
	private admin adminPage = new admin();
	private create createPage = new create();
	private adminLogin adminLoginPage = new adminLogin();
	private landing landingPage = new landing();
	public static String selectedSeats = new String();
	public static String[] fromList = null;
	public static String[] toList = null;
	
	public Entry() {
		super("Bus Booking");
		socketConnector();
		init();
	}

	public void socketConnector() {
		try {
			ip = InetAddress.getByName("localhost");
			s = new Socket(ip, 5056);
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			out = new ObjectOutputStream(s.getOutputStream());
			oin = new ObjectInputStream(s.getInputStream());
			String[] req = {"getFrom"};
			String[] req2 = {"getTo"};
			out.writeObject(req);
			fromList = (String[]) oin.readObject();
			out.writeObject(req2);
			toList = (String[]) oin.readObject();
			// the following loop performs the exchange of
			// information between client and client handler
			// while (true) {
			// // System.out.println(dis.readUTF());
			// String tosend ="pending"; //= scn.nextLine();
			// dos.writeUTF(tosend);
			clientPage = new client();
			// // If client sends exit,close this connection
			// // and then break from the while loop
			// if (tosend.equals("Exit")) {
			// s.close();
			// break;
			// }

			// // printing date or time as requested by client
			// // String received = dis.readUTF();
			// // System.out.println(received);
			// }

			// // closing resources
			// scn.close();
			// dis.close();
			// dos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void init() {

		setSize(500, 500);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		introPage.adminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				introPage.setVisible(false);
				adminLoginPage.setVisible(true);

			}
		});

		introPage.userButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPage.setVisible(true);
				introPage.setVisible(false);

			}
		});

		loginPage.loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String[] details = { "userLogin", loginPage.passwordField.getText(),
							loginPage.userNameField.getText() };
					out.writeObject(details);
					String received = dis.readUTF();
					if (received.equals("yes")) {
						loginPage.setVisible(false);
						landingPage.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(loginPage, "UserName or Password incorrect");
					}
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(loginPage, "UserName or Password incorrect");
					e1.printStackTrace();
				}

			}
		});

		loginPage.createAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPage.setVisible(false);
				createPage.setVisible(true);
			}
		});

		createPage.createAccountForCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (accountStatus() == true) {
					JOptionPane.showMessageDialog(createPage, "Successful!");
					loginPage.setVisible(true);
					createPage.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(createPage, "Something went wrong!");
					loginPage.setVisible(false);
					createPage.setVisible(true);
				}
			}
			
			
			
			private boolean accountStatus() {
				String gender = createPage.genderCombo.getSelectedItem().toString();
				String pn = createPage.phoneNoField.getText();
				String dob = createPage.dobField.getText();
				String pin = createPage.pincodeField.getText();
				String dst = createPage.districtField.getText();
				String state = createPage.stateField.getText();
				String pasw = createPage.passwordFieldForCreate.getText();
				String un = createPage.userNameFieldForCreate.getText();
				String[] details = { "newAccount", un, pasw, state, dst, pin, dob, pn, gender };
				try {
					out.writeObject(details);
					String recieved = dis.readUTF();
					if(recieved.equals("yes")) {
						return true;
					}
					else {
						return false;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return true;
			}
		});

		adminPage.addVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String[] args = {"addVehicle",adminPage.regNo.getText(),adminPage.empID.getText(),adminPage.from.getText(),adminPage.to.getText(),adminPage.time.getText()};  
				try {
					out.writeObject(args);
					String result = dis.readUTF();
					if(result.equals("yes")) {
						JOptionPane.showMessageDialog(adminPage, "Success!");
					}
					else {
						JOptionPane.showMessageDialog(adminPage, "Something went wrong!");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} );
		
		adminPage.addEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String[] args = {"addEmployee",adminPage.driverName.getText(),adminPage.salary.getText(),adminPage.dob.getText()};  
				try {
					out.writeObject(args);
					String result = dis.readUTF();
					if(result.equals("yes")) {
						JOptionPane.showMessageDialog(adminPage, "Success!");
					}
					else {
						JOptionPane.showMessageDialog(adminPage, "Something went wrong!");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} );
		
		adminPage.removeEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				try {
					String[] args = {"removeEmployee",adminPage.ID.getText()};
					out.writeObject(args);
					String result = dis.readUTF();
					if(result.equals("yes")) {
						JOptionPane.showMessageDialog(adminPage, "Success!");
					}
					else {
						JOptionPane.showMessageDialog(adminPage, "Something went wrong!");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} );
		
		adminPage.removeVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				try {
					String[] args = {"removeVehicle",adminPage.regNo.getText()};
					out.writeObject(args);
					String result = dis.readUTF();
					if(result.equals("yes")) {
						JOptionPane.showMessageDialog(adminPage, "Success!");
					}
					else {
						JOptionPane.showMessageDialog(adminPage, "Something went wrong!");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} );
		
		adminLoginPage.loginButtonAdim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = adminLoginPage.passwordFieldAdmin.getText();
				String pasw = adminLoginPage.userNameFieldAdmin.getText();
				String[] details = { "adminLogin", name, pasw };
				try {
					out.writeObject(details);
					String received = dis.readUTF();
					if(received.equals("yes")) {
						adminLoginPage.setVisible(false);
						adminPage.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(adminLoginPage, "Something went wrong!");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		clientPage.contactField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				if (clientPage.contactField.getText().length() >= 10
						&& !(evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
					evt.consume();
				}
				if (!(evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9')) {
					evt.consume();
				}
			}
		});

		createPage.phoneNoField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				if (createPage.phoneNoField.getText().length() >= 10
						&& !(evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
					evt.consume();
				}
				if (!(evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9')) {
					evt.consume();
				}
			}
		});

		createPage.pincodeField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				if (createPage.pincodeField.getText().length() >= 6
						&& !(evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
					evt.consume();
				}
				if (!(evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9')) {
					evt.consume();
				}
			}
		});

		landingPage.bookTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				landingPage.setVisible(false);
				clientPage.setVisible(true);

			}
		});

		clientPage.submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String age = (String) clientPage.ageSpinner.getValue();
				String name = clientPage.bookingNameField.getText();
				String pn = clientPage.contactField.getText();
				String date = clientPage.dateCombo.getSelectedItem().toString();
				String from = clientPage.fromCombo.getSelectedItem().toString();
				String to = clientPage.toCombo.getSelectedItem().toString();
				String gender = clientPage.genderCombo.getSelectedItem().toString();
				String time = "";
				String[] details = {"ticket",name,from,to,"",selectedSeats,pn,date,time};
			}
		});

		add(introPage);
		add(loginPage);
		add(clientPage);
		add(adminPage);
		add(createPage);
		add(adminLoginPage);
		add(landingPage);
		setVisible(true);

	}

	public String hierarchy() {
		return "clientPage";
	}

	public static void main(String[] args) {
		new Entry();
	}
}
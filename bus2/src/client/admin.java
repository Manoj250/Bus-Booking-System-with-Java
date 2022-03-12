package client;

import javax.swing.*;

import java.awt.Dimension;


public class admin extends JPanel {

	public String fromList[] = {"Bengaluru","Mangaluru","Dharwad","Hubli","Belagavi","Mysore","Kolara","Mandya","Sirsi","Tumkur","Pavagada","Anekal"}; 
    public String toList[] = {"Bengaluru","Mangaluru","Dharwad","Hubli","Belagavi","Mysore","Kolara","Mandya","Sirsi","Tumkur","Pavagada","Anekal"}; 
	public JComboBox fromComboAdmin = new JComboBox(fromList);
	public JComboBox toComboAdmin = new JComboBox(toList);
	public JButton getStaff = new JButton("Get Staff Details");
	public JButton getPassengers = new JButton("Get Passengers");
	public JButton getTotalCollection = new JButton("Get Total Collection");
	public JButton getBusDetails = new JButton("Get Bus Details");
	
	
	public JTextField regNo = new JTextField();
	public JLabel busregNo = new JLabel("Bus REG_NO:");
	public JTextField from = new JTextField();
	public JTextField to = new JTextField();
	public JLabel fromL = new JLabel("From:");
	public JLabel toL = new JLabel("To:");
	public JLabel priceL= new JLabel("Price:");
	public JTextField price = new JTextField();
	public JLabel empL= new JLabel("Price:");
	public JTextField empID = new JTextField();
	public JLabel timeL= new JLabel("Time:");
	public JTextField time = new JTextField();
	public JButton addVehicle = new JButton("Add Vehicle");
	public JButton removeVehicle = new JButton("Remove Vehicle");
	
	public JTextField driverName = new JTextField();
	public JTextField salary = new JTextField();
	public JTextField dob = new JTextField();
	public JLabel salaryL = new JLabel("Salary:");
	public JLabel dobL = new JLabel("DOB:");
	public JLabel nameL= new JLabel("Name:");
	public JTextField ID = new JTextField("Only for Removal");
	public JLabel IDL = new JLabel("ID:");
	public JButton addEmp = new JButton("Add Employee");
	public JButton removeEmp = new JButton("remove Employee");
	
	public admin() {
		super();
		setSize(800,800);
		setLayout(null);
		init();
	}

	public void init() {
	    busregNo.setBounds(10,10,200,20);
	    regNo.setBounds(230,10,200,20);
	    fromL.setBounds(10,40,200,20);
	    from.setBounds(230,40,200,20);
	    toL.setBounds(10,70,200,20);
	    to.setBounds(230,70,200,20);
	    priceL.setBounds(10,100,200,20);
	    price.setBounds(230,100,200,20);
	    empL.setBounds(10,130,200,20);
	    empID.setBounds(230,130,200,20);
	    timeL.setBounds(10,160,200,20);
	    time.setBounds(230,160,200,20);
	    addVehicle.setBounds(10,190,200,20);
	    removeVehicle.setBounds(230,190,200,20);
    
	    nameL.setBounds(10,200,200,20);
	    salaryL.setBounds(10,230,200,20);
	    dobL.setBounds(10,260,200,20);
	    driverName.setBounds(230,200,200,20);
	    salary.setBounds(230,230,200,20);
	    dob.setBounds(230,260,200,20);
	    IDL.setBounds(10,290,200,20);
	    ID.setBounds(230,290,200,20);
	    removeEmp.setBounds(10,320,200,20);
	    addEmp.setBounds(230,320,200,20);
	    
//        add(fromComboAdmin);
//        add(toComboAdmin);
//        add(getStaff);
//        add(getPassengers);
//        add(getTotalCollection);
        add(getBusDetails);
        add(busregNo);
        add(regNo);
        add(fromL);
        add(from);
        add(toL);
        add(to);
        add(priceL);
        add(price);
        add(addVehicle);
        add(removeVehicle);
        add(empL);
        add(empID);
        add(removeEmp);
        add(addEmp);
        add(nameL);
        add(dobL);
        add(salaryL);
        add(dob);
        add(salary);
        add(driverName);
        add(ID);
        add(IDL);
        add(timeL);
        add(time);
        setVisible(false);

	}
}

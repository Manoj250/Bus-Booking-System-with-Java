package client;

import javax.swing.*;

import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class client extends JPanel{
	public JLabel bookingName = new JLabel("Name:");
    public JLabel age = new JLabel("Age:");
    public JLabel gender = new JLabel("Gender:");
    public JLabel contactNumber = new JLabel("Contact number:");
    public JLabel from = new JLabel("From:");
    public JLabel to = new JLabel("To:");

    public JLabel date = new JLabel("Date:");
    
    public String genders[] = {"Male","Female","Other"};
    public JTextField bookingNameField = new JTextField();
    public JSpinner ageSpinner = new JSpinner(new SpinnerNumberModel(1,1,100,1));
    public JComboBox genderCombo = new JComboBox(genders);
    public JTextField contactField = new JTextField();



    public String[] dateList = new String[7]; 
    
    
    
    public JComboBox fromCombo ;
    public JComboBox toCombo ;
    public JButton submit = new JButton("Submit");
	public panelForTickets ticketSelection = new panelForTickets();
    public JComboBox dateCombo ;
    public static JToggleButton[] buttons = new JToggleButton[20];
    public static JToggleButton[] buttons2 = new JToggleButton[20];
    
	public client() {
	    super();
	    setSize(1000,1000);
	    setLayout(null);
	    init();
	}
	
	public static String getNextDate(String  curDate) throws ParseException {
		  final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		  final Date date = format.parse(curDate);
		  final Calendar calendar = Calendar.getInstance();
		  calendar.setTime(date);
		  calendar.add(Calendar.DAY_OF_YEAR, 1);
		  return format.format(calendar.getTime()); 
		}
	
	
	public void init() {
		
		
		////should be added in a seperate thread for fast start//////
		System.out.println(Entry.fromList.length);
		fromCombo = new JComboBox(Entry.fromList);
		toCombo = new JComboBox(Entry.toList);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    Date curdate = new Date();
	    dateList[0] = formatter.format(curdate);
	    String pdate = dateList[0];
	    System.out.print(pdate);
	    for(int i=1;i<7;i++) {
	    	try {
	    		dateList[i] = getNextDate(pdate);
	    		pdate = dateList[i];
	    	} catch (ParseException e) {
	    		e.printStackTrace();
	    	}  
	    }
	    
	    ///////////////////////////////////////////////////////////////
	    
	    dateCombo = new JComboBox(dateList);
	    
	    Dimension bookingNameSize = bookingName.getPreferredSize();
        bookingName.setBounds(10,10,bookingNameSize.width,bookingNameSize.height);

        Dimension ageSize = age.getPreferredSize();
        age.setBounds(10,55,ageSize.width,ageSize.height);

        Dimension genderSize = gender.getPreferredSize();
        gender.setBounds(10,100,50,genderSize.height);
        
        Dimension contactNumberSize = contactNumber.getPreferredSize();
        contactNumber.setBounds(10,145,contactNumberSize.width,contactNumberSize.height);

        Dimension fromSize = from.getPreferredSize();
        from.setBounds(10,190,fromSize.width,fromSize.height);

        Dimension toSize = to.getPreferredSize();
        to.setBounds(10,235,25,toSize.height);
   
        Dimension bookingNameFieldSize = bookingNameField.getPreferredSize();
        bookingNameField.setBounds(75,10,200,bookingNameFieldSize.height);

        Dimension ageSpinnerSize = ageSpinner.getPreferredSize();
        ageSpinner.setBounds(75,55,ageSpinnerSize.width,ageSpinnerSize.height);

        Dimension genderComboSize = genderCombo.getPreferredSize();
        genderCombo.setBounds(75,100,genderComboSize.width,genderComboSize.height);

        Dimension contactFieldSize = contactField.getPreferredSize();
        contactField.setBounds(120,145,200,contactFieldSize.height);

        Dimension fromComboSize = fromCombo.getPreferredSize();
        fromCombo.setBounds(75,190,fromComboSize.width,fromComboSize.height);

        Dimension toComboSize = toCombo.getPreferredSize();
        toCombo.setBounds(75,235,toComboSize.width,toComboSize.height);

        
        Dimension dateComboSize = dateCombo.getPreferredSize();
        dateCombo.setBounds(75,275,dateComboSize.width,dateComboSize.height);

        Dimension dateSize = date.getPreferredSize();
        date.setBounds(10,275,200,dateSize.height);
        
        
        Dimension submitSize = toCombo.getPreferredSize();
        submit.setBounds(10, 315,200, submitSize.height);
        
        ticketSelection.setBounds(450,10,480,570); 
        

        add(bookingName);
        add(age);
        add(gender);
        add(contactNumber);
        add(from);
        add(to);
        add(bookingNameField);
        add(ageSpinner);
        add(genderCombo);
        add(contactField);
        add(fromCombo);
        add(toCombo);
        add(submit);
        add(ticketSelection);


        add(date);
        add(dateCombo);
        setVisible(false);
	}
}

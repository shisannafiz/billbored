import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

//GUI for storage search
public class storageGUI extends JFrame {
	
 	JPanel jp = new JPanel();
    JLabel jl = new JLabel();
    JTextField jt = new JTextField(20);
    JTextField jtUser = new JTextField(20);
    JButton jb = new JButton("Search");
    JButton jb2 = new JButton("View");
    JButton jb3 = new JButton("Storage");

    SpinnerDateModel dateModel = new SpinnerDateModel();
    JSpinner jsStart = new JSpinner(dateModel);
    JSpinner jsEnd = new JSpinner(dateModel);
    
    JSpinner.DateEditor editor = new JSpinner.DateEditor(jsStart, "dd.MM.yyyy");
    
    GridBagConstraints gbc = new GridBagConstraints();

    public storageGUI (Storage ht) {
    	jp.setLayout(new GridBagLayout());
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.gridwidth = 2;
    	jp.add(jt, gbc);
    	gbc.gridx = 2;
    	gbc.gridy = 0;	
    	gbc.gridwidth = 1;
    	jp.add(jb, gbc); 
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.gridx = 0;
    	gbc.gridy = 1;
    	gbc.gridwidth = 1;
    	jp.add(jsStart, gbc); 
    	gbc.gridx = 1;
    	gbc.gridy = 1;  
    	gbc.gridwidth = 1;
    	jp.add(jsEnd,gbc);  
    	gbc.gridx = 2;
    	gbc.gridy = 1;  
    	gbc.gridwidth = 1;
    	jp.add(jb3,gbc);  
		
//		  gbc.gridx = 0;
//		  gbc.gridy = 2;      
//		  gbc.fill = GridBagConstraints.HORIZONTAL;
//		  gbc.gridwidth = 1;
//		  jp.add(jtUser, gbc);  
//		  
//		  gbc.gridx = 1;
//		  gbc.gridy = 2;  
//		  gbc.gridwidth = 1;
//		  jp.add(new JRadioButton("Print", false) ,gbc);
		  
//		  gbc.fill = GridBagConstraints.HORIZONTAL;
//		  gbc.gridx = 0;
//		  gbc.gridy = 3;
//		  gbc.gridwidth = 3;

        setTitle("Search Data Storage");
        setVisible(true);
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        
        Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		
		int xPos = (dim.width / 2) - (this.getWidth()/2);
		int yPos = (dim.height / 2) - (this.getWidth()/2);
		
		this.setLocation(xPos, yPos);
		
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String input = jt.getText();
				if(input != null && !input.isEmpty()) {
					try {
						Storage.find(input);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
                    try {
                    	jsStart.commitEdit();
                    } catch ( java.text.ParseException x ) { 
                    	
                    }
                    	 System.out.println(jsStart.getValue());
                    	 
                  } else {
                	  jl.setText("Try again");
                  }             
               }                      
         });
		
	     jb3.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 viewGUI v = new viewGUI(ht);
	             setVisible(false);
	          }	            
	     });
		
//        jp.add(jt);
//        jp.add(jb);
//        jp.add(jsStart);
//        jp.add(jsEnd, BorderLayout.EAST);
	     
        add(jp);
    }
}
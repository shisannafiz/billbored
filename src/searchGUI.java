import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//GUI for website search
public class searchGUI extends JFrame {
	 JPanel jp = new JPanel();
     JLabel jl = new JLabel();
     JTextField jt = new JTextField(10);
     JButton search = new JButton("Search");
     JButton logo = new JButton("Logo");
     JButton home = new JButton("Home");
   
     JRadioButton article = new JRadioButton("Articles", true);
     JRadioButton media = new JRadioButton("Photos/Videos", false);
     JRadioButton both = new JRadioButton("Both", false);
     ButtonGroup group = new ButtonGroup();

     public searchGUI(Storage ht) {
    	 setLayout(new FlowLayout());
    	 setTitle("Search Artist");
         setVisible(true);
         setSize(400, 200);
         setDefaultCloseOperation(EXIT_ON_CLOSE); 
            
         Toolkit tk = Toolkit.getDefaultToolkit();
         Dimension dim = tk.getScreenSize();
    				
         int xPos = (dim.width / 2) - (this.getWidth()/2);
         int yPos = (dim.height / 2) - (this.getWidth()/2);
    		
         this.setLocation(xPos, yPos);
    		
         jp.add(jt);
            
         jt.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        		 String input = jt.getText();
        		 if(input != null && !input.isEmpty()) {
        			 try {
        				 Search.startArticle(input, ht);
        			 } catch (IOException e1) {
        				 e1.printStackTrace(); 
                     }
        		 } else {
        			 jl.setText("Try again");}             
        	 }
//                         jl.setText(input); 
                         
         });

         jp.add(search);
         search.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        		 String input = jt.getText();
//                           jl.setText(input);
        		 if(input != null && !input.isEmpty()) {
        			 if(article.isSelected()) {
        				 try {
        					 Search.startArticle(input, ht);
        				 } catch (IOException e1) {
        					 e1.printStackTrace();
        				 }
        			 } else if (media.isSelected()) {
        				 try {
        					 Search.startMedia(input, ht);
        				 } catch (IOException e1) {
        					 e1.printStackTrace();
        				 }
        			 } else {
        				 try {
        					 Search.startBoth(input, ht);
        				 } catch (IOException e1) {
        					 e1.printStackTrace();
        				 }
        			 }                       	 
        		 } else {
        			 jl.setText("Try again");
        		 }             
        	 }	           
         });
            
         logo.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        		 try {
        			 Image.start();
        		 } catch (IOException e1) {
        			 e1.printStackTrace();
        		 }
        	 }	
         });
            
         home.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        		 masterGUI v = new masterGUI(ht);
        		 setVisible(false);
        	 }	            
         });
            
         jp.add(logo);
         jp.add(home);
            
         jp.add(jl);
         add(jp);
            
         add(article);
         add(media);
         add(both);
            
         group.add(article);
         group.add(media);
         group.add(both);


 } //constructor  
} //searchGUI
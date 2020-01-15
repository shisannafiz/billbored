import java.awt.BorderLayout;
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

///GUI for home 
public class masterGUI extends JFrame {
	JPanel jp = new JPanel();
    JButton searchArtist = new JButton("Search for an Artist");
    JButton viewStorage = new JButton("View Data Storage");
    JLabel jl = new JLabel("Billbored");
    
    public masterGUI(Storage ht) {
    	jp.setLayout(new BorderLayout());
        setTitle("Billbored");
        setVisible(true);
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        
        Toolkit tk = Toolkit.getDefaultToolkit();
   		Dimension dim = tk.getScreenSize();
   				
   		int xPos = (dim.width / 2) - (this.getWidth()/2);
   		int yPos = (dim.height / 2) - (this.getWidth()/2);
   		
   		this.setLocation(xPos, yPos);
   		
   		jp.add(searchArtist, BorderLayout.WEST);
   		jp.add(viewStorage, BorderLayout.EAST);
//   		jp.add(jl, BorderLayout.NORTH);
   		add(jp);
   		
        searchArtist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	searchGUI s = new searchGUI(ht);
                setVisible(false);
            }	         
        });
        
        viewStorage.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		viewGUI v = new viewGUI(ht);
                setVisible(false);
            }	            
        });
    } 
    
}

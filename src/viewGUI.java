import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

//GUI for viewing hashtable data 
public class viewGUI extends JFrame {
	JPanel jp = new JPanel();
 	JTable jt;
 	JTextField jt2 = new JTextField(5);
 	JLabel jl = new JLabel();
    JButton jb1 = new JButton("Search Storage");
    JButton jb2 = new JButton("Home");
    JButton jb3 = new JButton("Remove");

    public viewGUI(Storage ht) {
    	jp.setLayout(new FlowLayout());

    	Object[] column = {"ID", "Username", "Timestamp", "Query", "Headlines"};
    	Object[][] data = Storage.listArray();
		  
    	TableModel tb = new DefaultTableModel(data, column);
		  
    	jp.add(jb1);
    	jp.add(jb2);
		jp.add(jt2);
		jp.add(jb3);
		jp.add(jl);

		jt = new JTable(tb);
		jt.setPreferredScrollableViewportSize(new Dimension(1050, 300));
		jt.setFillsViewportHeight(true);
					
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jt.getColumnModel().getColumn(0).setPreferredWidth(100);
		jt.getColumnModel().getColumn(1).setPreferredWidth(100);
		jt.getColumnModel().getColumn(2).setPreferredWidth(150);
		jt.getColumnModel().getColumn(3).setPreferredWidth(100);
		jt.getColumnModel().getColumn(4).setPreferredWidth(600);

			
		DefaultTableModel model = (DefaultTableModel)jt.getModel();
		
		JScrollPane sp = new JScrollPane(jt);
			
		jp.add(sp);
			
		setTitle("Data Storage");
	    setVisible(true);
	    setSize(1150, 450);
	    setDefaultCloseOperation(EXIT_ON_CLOSE); 
	        
	    Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
			
		int xPos = (dim.width / 2) - (this.getWidth()/2);
		int yPos = (dim.height / 2) - (this.getWidth()/2);
			
		this.setLocation(xPos, yPos);
				
		add(jp);
			
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				storageGUI st = new storageGUI(ht);
				setVisible(false);
			}	         
		});
		     
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				masterGUI v = new masterGUI(ht);
				setVisible(false);
			}	            
		});
		     
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rows = jt.getRowCount();
				String input = jt2.getText();
				Object id;
				Object query;
				int value = Integer.valueOf(input);
				if(input != null && !input.isEmpty() && value > 0 && value < rows) {
					model.removeRow(value - 1);
					rows = jt.getRowCount();
	                        	
					id = jt.getValueAt(value, 0);
					int idI = Integer.valueOf((String) id);
					idI--;
					String idS = Integer.toString(idI);
		                        
					query = jt.getValueAt(value, 3);
					String queryS = query.toString();
	                        	
//	                        	Storage.delete(ht, );
				} else {
					jl.setText("Try again");}             
			}                          
		});     
	} //constructor 
} //viewGUI

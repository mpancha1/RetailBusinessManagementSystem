import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oracle.jdbc.pool.OracleDataSource;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Delete_Purchase extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete_Purchase frame = new Delete_Purchase();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Delete_Purchase() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		comboBox= new JComboBox();
		try {
			ArrayList<String> list = pur_ids();
			for(int i =0;i<list.size();i++){
				
				comboBox.addItem(list.get(i));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox.setBounds(171, 36, 122, 20);
		
		contentPane.add(comboBox);
		JLabel lblPurchaseId = new JLabel("Purchase Id : ");
		lblPurchaseId.setBounds(82, 39, 91, 14);
		contentPane.add(lblPurchaseId);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				delete_pur(comboBox.getSelectedItem().toString());
			}
		});
		btnNewButton.setBounds(133, 87, 143, 23);
		contentPane.add(btnNewButton);
		
		
	}
 public void delete_pur(String pur_id){
	 
	 try{
			OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

			ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
			        Connection conn = ds.getConnection("mpancha1", "pa664640");
			       // Connection conn = ds.getConnection("mpancha1", "pa664640");

			        //Prepare to call stored procedure:
			        CallableStatement cs = conn.prepareCall("{ call project2.delete_purchase(?)}");

			           //register the out parameter (the first parameter)
			      

			        // execute and retrieve the result set
			        cs.setString(1, pur_id);
			        cs.execute();
			       
			        //float result = cs.getFloat(1);
			        //System.out.println(result);
			        // print the results
			       JOptionPane.showMessageDialog(null, "Purchase Deleted : "+pur_id);

			        //close the result set, statement, and the connection
			         cs.close(); conn.close();
			   }
			   catch(SQLException ex) { 
				   JOptionPane.showMessageDialog(null, "Something went Wrong!!");
				   System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
	 
 }
 //getting purchase ids to fill combobox
 public ArrayList<String> pur_ids() throws SQLException{
		ArrayList<String> ar = new ArrayList<String>();

		
		
			OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

			ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
			        Connection conn = ds.getConnection("mpancha1", "pa664640");
			       // Connection conn = ds.getConnection("mpancha1", "pa664640");
			        java.sql.Statement stmt = conn.createStatement();
			        //Prepare to call stored procedure:
			     

			           //register the out parameter (the first parameter)
			        

			        // execute and retrieve the result set
			        
			        ResultSet rs = stmt.executeQuery("select pur# from purchases");
			       // ResultSetMetaData rsmd = rs.getMetaData();
			        //float result = cs.getFloat(1);
			        //System.out.println(result);
			        // print the results
			        
			       // System.out.println(rsmd.getColumnName(1)+"\t"+rsmd.getColumnName(2)+"\t"+rsmd.getColumnName(3)+"\t"+rsmd.getColumnName(4)+"\t"+rsmd.getColumnName(5));
			        while (rs.next()) {
			        	ar.add(rs.getString(1));
			        	//ids[i] = rs.getString(1);
			        	
			        }
			       // System.out.println(ids);
			        stmt.close();
			        conn.close();
					return ar;

			        //close the result set, statement, and the connection
			          
			   
			   
		
	}
}

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Add_Purchase extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Purchase frame = new Add_Purchase();
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
	public Add_Purchase() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProductId = new JLabel("Product Id :");
		lblProductId.setBounds(52, 50, 76, 14);
		contentPane.add(lblProductId);
		
		JLabel lblEmployeeId = new JLabel("Employee Id :");
		lblEmployeeId.setBounds(52, 75, 82, 14);
		contentPane.add(lblEmployeeId);
		
		JLabel lblNewLabel = new JLabel("Customer Id :");
		lblNewLabel.setBounds(52, 100, 76, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setBounds(52, 126, 92, 14);
		contentPane.add(lblQuantity);
		
		 comboBox = new JComboBox<String>();
		comboBox.setBounds(154, 47, 93, 20);
		contentPane.add(comboBox);
		try {
			ArrayList<String> list = pids();
			for(int i =0;i<list.size();i++){
				
				comboBox.addItem(list.get(i));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(154, 72, 93, 20);
		contentPane.add(comboBox_1);
		try {
			ArrayList<String> list_eid = eids();
			for(int i =0;i<list_eid.size();i++){
				
				comboBox_1.addItem(list_eid.get(i));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(154, 99, 93, 20);
		contentPane.add(comboBox_2);
		try {
			ArrayList<String> list_cid = cids();
			for(int i =0;i<list_cid.size();i++){
				
				comboBox_2.addItem(list_cid.get(i));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		textField = new JTextField();
		textField.setBounds(154, 123, 93, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				purchase_add();
			}
		});
		btnAdd.setBounds(184, 177, 89, 23);
		contentPane.add(btnAdd);
	}
	public ArrayList<String> eids() throws SQLException{
		ArrayList<String> ar = new ArrayList<String>();

		
		
			OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

			ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
			        Connection conn = ds.getConnection("mpancha1", "pa664640");
			       // Connection conn = ds.getConnection("mpancha1", "pa664640");
			        java.sql.Statement stmt = conn.createStatement();
			        //Prepare to call stored procedure:
			     

			           //register the out parameter (the first parameter)
			        

			        // execute and retrieve the result set
			        
			        ResultSet rs = stmt.executeQuery("select eid from employees");
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
	public ArrayList<String> pids() throws SQLException{
		ArrayList<String> ar = new ArrayList<String>();

		
		
			OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

			ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
			        Connection conn = ds.getConnection("mpancha1", "pa664640");
			       // Connection conn = ds.getConnection("mpancha1", "pa664640");
			        java.sql.Statement stmt = conn.createStatement();
			        //Prepare to call stored procedure:
			     

			           //register the out parameter (the first parameter)
			        

			        // execute and retrieve the result set
			        
			        ResultSet rs = stmt.executeQuery("select pid from products");
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
			        System.out.println(ar);
			        stmt.close();
			        conn.close();
					return ar;

			        //close the result set, statement, and the connection
			          
			   
			   
		
	}
	public ArrayList<String> cids() throws SQLException{
		ArrayList<String> ar = new ArrayList<String>();

		
		
			OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

			ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
			        Connection conn = ds.getConnection("mpancha1", "pa664640");
			       // Connection conn = ds.getConnection("mpancha1", "pa664640");
			        java.sql.Statement stmt = conn.createStatement();
			        //Prepare to call stored procedure:
			     

			           //register the out parameter (the first parameter)
			        

			        // execute and retrieve the result set
			        
			        ResultSet rs = stmt.executeQuery("select cid from customers");
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
	public void purchase_add(){
		
		try{
			OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

			ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
			        Connection conn = ds.getConnection("mpancha1", "pa664640");
			       // Connection conn = ds.getConnection("mpancha1", "pa664640");

			        //Prepare to call stored procedure:
			        CallableStatement cs = conn.prepareCall("begin ? := project2.add_purchase(?,?,?,?); end;");

			           //register the out parameter (the first parameter)
			        cs.registerOutParameter(1, OracleTypes.NUMBER);
			      //  System.out.println("Test");
			        //setting parameters
			        if(textField.getText().matches("^\\d+(\\.\\d+)?")){
			       cs.setString(2, comboBox_1.getSelectedItem().toString());
			       System.out.println(comboBox_1.getSelectedItem().toString());
			       cs.setString(3, comboBox.getSelectedItem().toString());
			       cs.setString(4, comboBox_2.getSelectedItem().toString());
			       cs.setInt(5, Integer.parseInt(textField.getText()));
			       

			        // execute and retrieve the result set
			        cs.execute();
			       
			        int result = cs.getInt(1);
			        if(result == -123){
			        	JOptionPane.showMessageDialog(null, "Insufficient quantity in stock!!");
			        }
			        else{
			        	
			        	JOptionPane.showMessageDialog(null, "Purchase Successfully Added!!");
			        	JOptionPane.showMessageDialog(null, "Now Product Quantity of "+ comboBox.getSelectedItem().toString() + "= "+result);
			        }
			       
			        }else{
			        	JOptionPane.showMessageDialog(null, "Please Insert Integer as value for Quantity!!");
			        	
			        }
			        //close the result set, statement, and the connection
			         cs.close(); conn.close();
			   }
			   catch(SQLException ex) {
				   JOptionPane.showMessageDialog(null, "Something went Wrong!!");
				   System.out.println(ex.getMessage());}
	}

}

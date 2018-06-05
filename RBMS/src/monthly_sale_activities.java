import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class monthly_sale_activities extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					monthly_sale_activities frame = new monthly_sale_activities();
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
	public monthly_sale_activities() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmployeeId = new JLabel("Employee ID : ");
		lblEmployeeId.setBounds(98, 24, 82, 14);
		contentPane.add(lblEmployeeId);
		 comboBox = new JComboBox();
			try {
				ArrayList<String> list = eids();
				for(int i =0;i<list.size();i++){
					
					comboBox.addItem(list.get(i));
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			comboBox.setBounds(171, 21, 118, 20);
			contentPane.add(comboBox);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				monthly_sale(comboBox.getSelectedItem().toString());
			}
		});
		btnSubmit.setBounds(299, 20, 89, 23);
		contentPane.add(btnSubmit);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 49, 424, 212);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		 textArea = new JTextArea();
		 JScrollPane scroll = new JScrollPane (textArea);
		 scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
         scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
         panel.add(scroll);
		
		
		
	}
	public void monthly_sale(String eid){
		
		try{
			OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

			ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
			        Connection conn = ds.getConnection("mpancha1", "pa664640");
			       // Connection conn = ds.getConnection("mpancha1", "pa664640");

			        //Prepare to call stored procedure:
			        CallableStatement cs = conn.prepareCall("begin ? := project2.monthly_sale_activities(?); end;");

			           //register the out parameter (the first parameter)
			        cs.registerOutParameter(1, OracleTypes.CURSOR);
			        cs.setString(2, eid);

			        // execute and retrieve the result set
			        cs.execute();
			        ResultSet rs = (ResultSet)cs.getObject(1);
			        String column_data = "MONTH"+"\t"+"TOTAL_SALE"+"\t"+"QUANTITY"+"\t"+"NUMBER_OF_SALE"+"\t"+"NAME\n\n";
			      textArea.append(column_data);
			        while (rs.next()) {
			        	
			        	textArea.append(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3)+"\t" + rs.getString(4) + "\t"+ "\t"  + rs.getString(5)+"\n");
			        }

			        //close the result set, statement, and the connection
			         cs.close(); conn.close();
			   }
			   catch(SQLException ex) {
				   JOptionPane.showMessageDialog(null, "Something went Wrong!!");
				   System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
		
	}
	//gettting eids to fill combobox
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
}

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class purchase_saving extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					purchase_saving frame = new purchase_saving();
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
	public purchase_saving() {
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
				//filling combobox
				comboBox.addItem(list.get(i));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox.setBounds(176, 11, 74, 20);
		
		contentPane.add(comboBox);
		
		JLabel lblPurchaseId = new JLabel("Purchase ID :");
		lblPurchaseId.setBounds(62, 14, 102, 14);
		lblPurchaseId.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPurchaseId);
		
		JButton btnNewButton = new JButton("calculate");
		btnNewButton.setBounds(260, 10, 120, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saving(comboBox.getSelectedItem().toString());
			}
		});
		contentPane.add(btnNewButton);
		
		JLabel lblTotalSaving = new JLabel("Total Saving :");
		lblTotalSaving.setBounds(72, 48, 102, 14);
		contentPane.add(lblTotalSaving);
		
		textField_1 = new JTextField();
		textField_1.setBounds(176, 45, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		 
	}
public void saving(String id){
	
	try{
		OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

		ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
		        Connection conn = ds.getConnection("mpancha1", "pa664640");
		       // Connection conn = ds.getConnection("mpancha1", "pa664640");

		        //Prepare to call stored procedure:
		        CallableStatement cs = conn.prepareCall("begin ? := project2.purchase_saving(?); end;");

		           //register the out parameter (the first parameter)
		        cs.registerOutParameter(1, OracleTypes.NUMBER);
		        cs.setString(2, id);

		        // execute and retrieve the result set
		        cs.execute();
		        //ResultSet rs = (ResultSet)cs.getObject(1);
		        float result = cs.getFloat(1);
		       // System.out.println("Total Savings :");
		        if(result == -1){
		        	
		        	JOptionPane.showMessageDialog(null, "OOPS!! NO DATA FOUND!!");
		        }else{
		        textField_1.setText(result+"");}
		        // print the results
		        //while (rs.next()) {
		          //  System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + rs.getString(4) + "\t" + rs.getString(5)
		            //   );
		        //}

		        //close the result set, statement, and the connection
		         cs.close(); conn.close();
		   }
		   catch(SQLException ex) { JOptionPane.showMessageDialog(null, "OOps Can not find The Purchase ID..");}

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
		        conn.close();
				return ar;

		        //close the result set, statement, and the connection
		          
		   
		   
	
}
}

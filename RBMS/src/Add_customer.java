import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oracle.jdbc.pool.OracleDataSource;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Add_customer extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_customer frame = new Add_customer();
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
	public Add_customer() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADD CUSTOMER");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(106, 11, 250, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblCustomerId = new JLabel("Customer ID : ");
		lblCustomerId.setBounds(10, 53, 120, 14);
		contentPane.add(lblCustomerId);
		
		textField = new JTextField();
		textField.setBounds(153, 50, 165, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblCustomerName = new JLabel("Customer Name :");
		lblCustomerName.setBounds(10, 90, 120, 14);
		contentPane.add(lblCustomerName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(153, 87, 165, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTelephoneNumber = new JLabel("Telephone Number : ");
		lblTelephoneNumber.setBounds(10, 129, 120, 14);
		contentPane.add(lblTelephoneNumber);
		
		textField_2 = new JTextField();
		textField_2.setBounds(153, 126, 165, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customr_add(textField.getText(), textField_1.getText(), textField_2.getText());
			}
		});
		btnAdd.setBounds(153, 204, 179, 23);
		contentPane.add(btnAdd);
	}
public void customr_add(String id,String name,String telephone){
	
	try{
		OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

		ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
		        Connection conn = ds.getConnection("mpancha1", "pa664640");
		       // Connection conn = ds.getConnection("mpancha1", "pa664640");

		        //Prepare to call stored procedure:
		        CallableStatement cs = conn.prepareCall("{ call project2.add_customer(?,?,?)}");

		        //setting parameters
		        if((id.charAt(0) == 'c' || id.charAt(0) == 'C') && id.length() == 4){
		       cs.setString(1, id);
		       cs.setString(2, name);
		       cs.setString(3, telephone);


		        // execute and retrieve the result set
		        cs.execute();
		        JOptionPane.showMessageDialog(null, "Customer Added!!!");
		        }else{
		        	
		        	JOptionPane.showMessageDialog(null, "Invalid Id Must start with c or Incorrect Length!");
		        }
		        //float result = cs.getFloat(1);
		        //System.out.println(result);
		        // print the results
		        
		       //System.out.println("Customer ADDED.......");

		        //close the result set, statement, and the connection
		         cs.close(); conn.close();
		   }
		   catch(SQLException ex) { JOptionPane.showMessageDialog(null, "Oops Custumer Id already Exist!!");}
}

}


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GUI_Database extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Database frame = new GUI_Database();
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
	public GUI_Database() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnDisplay = new JMenu("Display");
		menuBar.add(mnDisplay);
		//Handling clicks of menu
		JMenuItem mntmCustomers = new JMenuItem("Customers");
		mntmCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				show_customer();
			}
		});
		mnDisplay.add(mntmCustomers);
		
		JMenuItem mntmEmployees = new JMenuItem("Employees");
		mntmEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				show_employees();
			}
		});
		mnDisplay.add(mntmEmployees);
		
		JMenuItem mntmProducts = new JMenuItem("Products");
		mntmProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				show_products();
			}
		});
		mnDisplay.add(mntmProducts);
		
		JMenuItem mntmPurchases = new JMenuItem("Purchases");
		mntmPurchases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				show_purchases();
			}
		});
		mnDisplay.add(mntmPurchases);
		
		JMenuItem mntmSuppliers = new JMenuItem("Suppliers");
		mntmSuppliers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				show_suppliers();
			}
		});
		mnDisplay.add(mntmSuppliers);
		
		JMenuItem mntmSupplies = new JMenuItem("Supplies");
		mntmSupplies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				show_supplies();
			}
		});
		mnDisplay.add(mntmSupplies);
		
		JMenuItem mntmLogs = new JMenuItem("Logs");
		mntmLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				show_logs();
			}
		});
		mnDisplay.add(mntmLogs);
		
		JMenu mnFunctionalities = new JMenu("Functionalities");
		menuBar.add(mnFunctionalities);
		
		JMenuItem mntmAddCustomer = new JMenuItem("Add Customer");
		mntmAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Add_customer().setVisible(true);
			}
		});
		mnFunctionalities.add(mntmAddCustomer);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Purchase_saving");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new purchase_saving().setVisible(true);
			}
		});
		mnFunctionalities.add(mntmNewMenuItem);
		
		JMenuItem mntmMonthlysaleactivities = new JMenuItem("Monthly_sale_Activities");
		mntmMonthlysaleactivities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new monthly_sale_activities().setVisible(true);
			}
		});
		mnFunctionalities.add(mntmMonthlysaleactivities);
		
		JMenuItem mntmDeletePurchase = new JMenuItem("Delete Purchase");
		mntmDeletePurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Delete_Purchase().setVisible(true);
			}
		});
		mnFunctionalities.add(mntmDeletePurchase);
		
		JMenuItem mntmAddpurchase = new JMenuItem("Add_Purchase");
		mntmAddpurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Add_Purchase().setVisible(true);
			}
		});
		mnFunctionalities.add(mntmAddpurchase);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		 textArea = new JTextArea();
		textArea.setColumns(10);
		contentPane.add(textArea, BorderLayout.CENTER);
	}
	public void show_customer(){
		
		try{
			 OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

			 ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
			         Connection conn = ds.getConnection("mpancha1", "pa664640");

			         //Prepare to call stored procedure:
			         CallableStatement cs = conn.prepareCall("begin ? := project2.show_customers(); end;");

			            //register the out parameter (the first parameter)
			         cs.registerOutParameter(1, OracleTypes.CURSOR);


			         // execute and retrieve the result set
			         cs.execute();
			         ResultSet rs = (ResultSet)cs.getObject(1);
			         String column_name = "CID"+"\t"+"NAME"+"\t"+"TELEPHONE#"+"\t"+"VISITS_MADE"+"\t"+"LAST_VISIT_MADE\n\n";
			         // print the results
			         textArea.append(column_name);
			         while (rs.next()) {
			             textArea.append(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3)+"\t" + rs.getString(4)+"\t"+rs.getDate(5)+"\n" 
			                );
			         }

			         //close the result set, statement, and the connection
			          cs.close(); conn.close();
			    }
			    catch (SQLException ex) { 
			    	JOptionPane.showMessageDialog(null, "Something went Wrong!!");
			    	System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
	}

public void show_employees() {
		

		try{
			 OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

			 ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
			         Connection conn = ds.getConnection("mpancha1", "pa664640");

			         //Prepare to call stored procedure:
			         CallableStatement cs = conn.prepareCall("begin ? := project2.show_employees(); end;");

			            //register the out parameter (the first parameter)
			         cs.registerOutParameter(1, OracleTypes.CURSOR);
			         String Column_name = "EID"+"\t"+"NAME"+"\t"+"TELEPHONE#"+"\t"+"EMAIL\n\n";
			         textArea.append(Column_name);

			         // execute and retrieve the result set
			         cs.execute();
			         ResultSet rs = (ResultSet)cs.getObject(1);
			     
			         
			         // print the results
			         
			         while (rs.next()) {
			        	//dtm.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
			             textArea.append(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3)+"\t" + rs.getString(4)+"\n");
			             
			         }
			       
			        // JTable table = new JTable(data, column);
			        
			         //close the result set, statement, and the connection
			          cs.close(); conn.close();
			    }
			    catch (SQLException ex) { 
			    	JOptionPane.showMessageDialog(null, "Something went Wrong!!");
			    	System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
	}
public void show_products(){
	
	try{
		 OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

		 ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
		         Connection conn = ds.getConnection("mpancha1", "pa664640");

		         //Prepare to call stored procedure:
		         CallableStatement cs = conn.prepareCall("begin ? := project2.show_products(); end;");

		            //register the out parameter (the first parameter)
		         cs.registerOutParameter(1, OracleTypes.CURSOR);


		         // execute and retrieve the result set
		         cs.execute();
		         ResultSet rs = (ResultSet)cs.getObject(1);
		         String column_name = "PID"+"\t"+"NAME"+"\t"+"QOH"+"\t"+"QOH_THRESHOLD"+"\t"+"ORIGINAL_PRICE"+"\t"+"DISCNT_CATEGORY\n\n";
		         // print the results
		         textArea.append(column_name);
		         while (rs.next()) {
		             textArea.append(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3)+"\t" + rs.getString(4)+"\t"+"\t" + rs.getString(5)+"\t"+"\t"+rs.getString(6)+"\n" );
		         
		         }

		         //close the result set, statement, and the connection
		          cs.close(); conn.close();
		    }
		    catch (SQLException ex) { 
		    	JOptionPane.showMessageDialog(null, "Something went Wrong!!");
		    	System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
}
public void show_purchases(){
	
	try{
		 OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

		 ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
		         Connection conn = ds.getConnection("mpancha1", "pa664640");

		         //Prepare to call stored procedure:
		         CallableStatement cs = conn.prepareCall("begin ? := project2.show_purchases(); end;");

		            //register the out parameter (the first parameter)
		         cs.registerOutParameter(1, OracleTypes.CURSOR);


		         // execute and retrieve the result set
		         cs.execute();
		         ResultSet rs = (ResultSet)cs.getObject(1);
		         String column_name = "PUR#"+"\t"+"EID"+"\t"+"PID"+"\t"+"CID"+"\t"+"QTY"+"\t"+"PTIME"+"\t"+"TOTAL_PRICE\n\n";
		         textArea.append(column_name);
		         // print the results
		         while (rs.next()) {
		             textArea.append(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3)+"\t" + rs.getString(4)+"\t" + rs.getString(5)+"\t"+rs.getDate(6)+"\t"+rs.getString(7)+"\n" 
		                );
		         }

		         //close the result set, statement, and the connection
		          cs.close(); conn.close();
		    }
		    catch (SQLException ex) { 
		    	JOptionPane.showMessageDialog(null, "Something went Wrong!!");
		    	System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
}
public void show_logs(){
	
	try{
		 OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

		 ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
		         Connection conn = ds.getConnection("mpancha1", "pa664640");

		         //Prepare to call stored procedure:
		         CallableStatement cs = conn.prepareCall("begin ? := project2.show_logs(); end;");

		            //register the out parameter (the first parameter)
		         cs.registerOutParameter(1, OracleTypes.CURSOR);


		         // execute and retrieve the result set
		         cs.execute();
		         ResultSet rs = (ResultSet)cs.getObject(1);
		        String column_names = "LOG#"+"\t" +"USER_NAME"+ "\t" +"OPERATION" +"\t" +"OP_TIME"+"\t" + "TABLE_NAME"+"\t" + "TUPLE_KEY\n\n";
		        textArea.append(column_names);
		         // print the results
		         while (rs.next()) {
		             textArea.append(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3)+"\t" + rs.getDate(4)+"\t" + rs.getString(5)+"\t"+rs.getString(6)+"\n" 
		                );
		         }

		         //close the result set, statement, and the connection
		          cs.close(); conn.close();
		    }
		    catch (SQLException ex) { 
		    	JOptionPane.showMessageDialog(null, "Something went Wrong!!");
		    	System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
}
public void show_suppliers(){
	
	try{
		 OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

		 ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
		         Connection conn = ds.getConnection("mpancha1", "pa664640");

		         //Prepare to call stored procedure:
		         CallableStatement cs = conn.prepareCall("begin ? := project2.show_suppliers(); end;");

		            //register the out parameter (the first parameter)
		         cs.registerOutParameter(1, OracleTypes.CURSOR);


		         // execute and retrieve the result set
		         cs.execute();
		         ResultSet rs = (ResultSet)cs.getObject(1);
		         ResultSetMetaData rsmd = rs.getMetaData();
		         String col_names = rsmd.getColumnName(1)+"\t"+rsmd.getColumnName(2)+"\t"+rsmd.getColumnName(3)+"\t"+rsmd.getColumnName(4)+"\t"+rsmd.getColumnName(5)+"\n"+"\n";
		         textArea.append(col_names);
		         // print the results
		         while (rs.next()) {
		             textArea.append(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3)+"\t" + rs.getString(4)+"\t" + rs.getString(5)+"\n" 
		                );
		         }

		         //close the result set, statement, and the connection
		          cs.close(); conn.close();
		    }
		    catch (SQLException ex) {
		    	JOptionPane.showMessageDialog(null, "Something went Wrong!!");
		    	System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
}
public void show_supplies(){
	
	try{
		 OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

		 ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
		         Connection conn = ds.getConnection("mpancha1", "pa664640");

		         //Prepare to call stored procedure:
		         CallableStatement cs = conn.prepareCall("begin ? := project2.show_supplies(); end;");

		            //register the out parameter (the first parameter)
		         cs.registerOutParameter(1, OracleTypes.CURSOR);


		         // execute and retrieve the result set
		         cs.execute();
		         ResultSet rs = (ResultSet)cs.getObject(1);
		         ResultSetMetaData rsmd = rs.getMetaData();
		         String col_names = rsmd.getColumnName(1)+"\t"+rsmd.getColumnName(2)+"\t"+rsmd.getColumnName(3)+"\t"+rsmd.getColumnName(4)+"\t"+"\t"+rsmd.getColumnName(5)+"\n"+"\n";
		         textArea.append(col_names);
		         // print the results
		         while (rs.next()) {
		             textArea.append(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3)+"\t" + rs.getDate(4)+"\t"+"\t" + rs.getString(5)+"\n" 
		                );
		         }

		         //close the result set, statement, and the connection
		          cs.close(); conn.close();
		    }
		    catch (SQLException ex) { 
		    	JOptionPane.showMessageDialog(null, "Something went Wrong!!");
		    	System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
}

}

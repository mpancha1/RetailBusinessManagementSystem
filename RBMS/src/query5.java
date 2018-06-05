import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

public class query5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String id,name,telephone;
		System.out.println("-------- Add Customer -----------");
		System.out.println("ID : ");
		id = in.nextLine();
		System.out.println("Name : ");
		name = in.nextLine();
		System.out.println("Telephone : ");
		telephone = in.nextLine();
		
		try{
			OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

			ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
			        Connection conn = ds.getConnection("mpancha1", "pa664640");
			       // Connection conn = ds.getConnection("mpancha1", "pa664640");

			        //Prepare to call stored procedure:
			        CallableStatement cs = conn.prepareCall("{ call add_customer(?,?,?)}");

			           //register the out parameter (the first parameter)
			       cs.setString(1, id);
			       cs.setString(2, name);
			       cs.setString(3, telephone);


			        // execute and retrieve the result set
			        cs.execute();
			       
			        //float result = cs.getFloat(1);
			        //System.out.println(result);
			        // print the results
			       System.out.println("Customer ADDED.......");

			        //close the result set, statement, and the connection
			         cs.close(); conn.close();
			   }
			   catch(SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
	}

}

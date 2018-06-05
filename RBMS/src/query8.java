import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class query8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

			ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
			        Connection conn = ds.getConnection("mpancha1", "pa664640");
			       // Connection conn = ds.getConnection("mpancha1", "pa664640");

			        //Prepare to call stored procedure:
			        CallableStatement cs = conn.prepareCall("{ call delete_purchase(?)}");

			           //register the out parameter (the first parameter)
			      

			        // execute and retrieve the result set
			        cs.setString(1, "100001");
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

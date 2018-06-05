import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

public class query3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
		OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();

		ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
		        Connection conn = ds.getConnection("mpancha1", "pa664640");
		       // Connection conn = ds.getConnection("mpancha1", "pa664640");

		        //Prepare to call stored procedure:
		        CallableStatement cs = conn.prepareCall("begin ? := find_saving(100001); end;");

		           //register the out parameter (the first parameter)
		        cs.registerOutParameter(1, OracleTypes.NUMBER);


		        // execute and retrieve the result set
		        cs.execute();
		        //ResultSet rs = (ResultSet)cs.getObject(1);
		        float result = cs.getFloat(1);
		        System.out.println("Total Savings :");
		        System.out.println(result);
		        // print the results
		        //while (rs.next()) {
		          //  System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + rs.getString(4) + "\t" + rs.getString(5)
		            //   );
		        //}

		        //close the result set, statement, and the connection
		         cs.close(); conn.close();
		   }
		   catch(SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}


	}

}

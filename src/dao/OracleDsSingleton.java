package dao;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * dieses Singleton stellt den Zugriff aus die Oracle Datenbank der HWR dar.
 * 
 * @author Prof. Dr. Markus Schaal (Vorlesungsfolien "Lecture 8 - Files & JDBC)
 */
public class OracleDsSingleton {

    private static OracleDsSingleton dss = null;
    private static OracleDataSource ds = null;



    private OracleDsSingleton() throws SQLException {

        try {
            ds = new OracleDataSource();

            ds.setDataSourceName("<SourceName>");
            ds.setURL("<URL>");

            ds.setUser("<User>");
            ds.setPassword("<Password>");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static OracleDsSingleton getInstance() throws SQLException {
        if(dss == null) dss = new OracleDsSingleton();
        return dss;
    }

    public Connection getConnection() throws SQLException{
        Connection con = null;
        con = ds.getConnection();
        return con;
    }

}

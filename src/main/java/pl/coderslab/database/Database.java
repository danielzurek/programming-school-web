package pl.coderslab.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;



public class Database {

    private static final String RESOURCE = "java:comp/env/jdbc/school";
    private static DataSource dataSource;

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }

    private static DataSource getInstance() {
        if (dataSource == null) {
            try {
                Context context = new InitialContext();
                dataSource = (DataSource) context.lookup(RESOURCE);
            } catch (NamingException ex) {
                ex.printStackTrace();
            }
        }
        return dataSource;
    }
}
package uni.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDB {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection cn;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://localhost:1433;"
                       + "databaseName=clinica;"
                       + "user=sa1;"
                       + "password=12345678;"
                       + "encrypt=false;"
                       + "trustServerCertificate=true;";

            cn = DriverManager.getConnection(url);
            return cn;

        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }
}

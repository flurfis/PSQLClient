package testConnection;

import java.sql.*;
import java.util.Properties;

public class ConnectJdbcPolypheny {
    private final static String dbHost = "localhost";
    private final static int port = 20591;

    static void startConnection () throws SQLException {
        try {
            Class.forName( "org.polypheny.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
            System.out.println("Polypheny JDBC Driver not found");
            e.printStackTrace();
        }

        //final String url = "jdbc:polypheny:http://" + dbHost + ":" + port;
        final String url = "jdbc:polypheny:http://localhost";
        System.out.println( "Connecting to database @ {}" );

        Properties props = new Properties();
        props.setProperty( "user", "pa" );
        props.setProperty("sslmode", "disable");
        //props.setProperty( "serialization", "PROTOBUF" );

        Connection conn = DriverManager.getConnection( url, props );
        conn.setAutoCommit( true );

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM public.emps");
        printEmpsResultSet(rs);

    }

    private static void printEmpsResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            int empid = rs.getInt("empid");
            int deptno = rs.getInt("deptno");
            String name = rs.getString("name");
            int salary = rs.getInt("salary");
            int commission = rs.getInt("commission");

            //System.out.printf( "AlbumId = %s , Title = %s, ArtistId = %s ", albumid,title, artistid );
            System.out.printf("LolId = %s \n", empid);
            System.out.printf("deptno = %s \n", deptno);
            System.out.printf("name = %s \n", name);
            System.out.printf("salary = %s \n", salary);
            System.out.printf("commission = %s \n", commission);
            System.out.println();
        }
    }
}

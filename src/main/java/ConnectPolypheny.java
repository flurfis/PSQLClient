import java.sql.*;
import java.util.Properties;

public class ConnectPolypheny {

    static void startConnection () throws SQLException {

        try {
            Connection c = null;
            Statement statement = null;
            Properties connectionProps = new Properties();
            //connectionProps.put("user", this.userName);
            //connectionProps.put("password", this.password);
            //connectionProps.setProperty("ssl", "false");
            connectionProps.setProperty("sslmode", "disable");
            //connectionProps.setProperty("replication", "true");
            //connectionProps.setProperty("assumeMinServerVersion", "9.0");
            //connectionProps.setProperty("ServerVersion", "v14");
            //connectionProps.setProperty("preferQueryMode", "simple");
            String url = "jdbc:postgresql://localhost:5444/";

            c = DriverManager.getConnection(url, connectionProps);

            System.out.println("Connected to Polypheny");


            statement = c.createStatement();
            //statement.executeUpdate("INSERT INTO Album(AlbumId, Title, ArtistId) VALUES (1, 'Hello', 1), (2, 'Hello', 2), (3, 'lol', 3);");
            //statement.executeUpdate("INSERT INTO lol(LolId) VALUES (1);");
            //statement.executeUpdate("SELECT empid FROM public.emps");

            /*
            statement.executeUpdate("CREATE TABLE lol (\n" +
                    "    LolId       INT    PRIMARY KEY\n" +
                    ");");

            System.out.println("Hallo");

             */


            /*
            statement.executeUpdate("CREATE TABLE Album (\n" +
                    "   AlbumId          INT     PRIMARY KEY     NOT NULL,\n" +
                    "   Title            TEXT    NOT NULL,\n" +
                    "   ArtistId         INT     NOT NULL\n" +
                    ");");

             */



            ResultSet rs = statement.executeQuery("SELECT empid FROM public.emps LIMIT 1"); //empid, deptno, salary, commission, name
            //ResultSet rs = statement.executeQuery("SELECT * FROM public.emps"); //empid
            //rs.close();
            statement.close();


            while (rs.next()) {
                int lol = rs.getInt("empid");
                String  title = rs.getString("name");
                //int artistid  = rs.getInt("deptno");

                //System.out.printf( "AlbumId = %s , Title = %s, ArtistId = %s ", albumid,title, artistid );
                System.out.printf( "LolId = %s", lol);
                System.out.printf( "name = %s", title);
                System.out.println();
            }



            rs.close();



            System.out.println("SQL-part executed successfully");
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        Connection c = null;
        Properties connectionProps = new Properties();
        //connectionProps.put("user", this.userName);
        //connectionProps.put("password", this.password);
        connectionProps.setProperty("useSSL", "false");
        connectionProps.put("sslMode", "DISABLE");

        c = DriverManager.getConnection("jdbc:postgresql://localhost:5444/", connectionProps);

        System.out.println("Connected to Polypheny");

        c.close();

         */


    }

    /*
    static void startConnection2() {

        Connection c = null;
        Statement statement = null;
        // jdbc:postgresql://<database_host>:<port>/<database_name>, username, pw
        //docker container name: psql_simple
        String url = "jdbc:postgresql://localhost:5443/simple_db";

        try {
            Class.forName ("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5443/simple_db","flu", "123");
            //c = DriverManager.getConnection("jdbc:postgresql://localhost:1234/hello");
            //c.setAutoCommit(false);
            System.out.println("Successfully Connected.");

            statement = c.createStatement();
            // statement.executeUpdate("INSERT INTO lol(LolId) VALUES (3);");  //mit executeQuery erwartet es ein resultat... gibt fehlermeldung



     */
            /* from the website */

    /*

            ResultSet rs = statement.executeQuery("SELECT LolId FROM lol;");

            while (rs.next()) {
                int lol = rs.getInt("LolId");
                //String  title = rs.getString("Title");
                //int artistid  = rs.getInt("ArtistId");

                //System.out.printf( "AlbumId = %s , Title = %s, ArtistId = %s ", albumid,title, artistid );
                System.out.printf( "LolId = %s", lol);
                System.out.println();
            }

            System.out.println("SQL-part executed successfully");

            rs.close();
            statement.close();
            c.close();

        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        System.out.println("End of Program");

    }

     */

}

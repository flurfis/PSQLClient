import java.io.BufferedReader;
import java.io.InputStreamReader;
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
            //c.commit();     //luege was bem server aachonnt (sql?) --> emmer no speichere öb autocommit autocommit esch denne
            //c.setAutoCommit(false);   //kei spezifische command e de iileitig, aber met BEGIN...

            System.out.println("Connected to Polypheny");


            statement = c.createStatement();

            System.out.println("Enter 1 to SELECT,");
            System.out.println("Enter 2 to INSERT,");
            System.out.println("Enter 3 to CREATE TABLE,");
            System.out.println("Enter 4 for tests:");
            int choice = 0;

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            try {
                choice = Integer.parseInt(br.readLine());

            } catch (NumberFormatException nfe) {
                System.err.println("Invalid Format");
            }

            switch (choice) {
                case 0:
                    System.out.println("Number conversion failed");
                    break;

                case 1:
                    System.out.println("executing select");
                    ResultSet rs = statement.executeQuery("SELECT * FROM public.emps"); //empid, deptno, name, salary, commission
                    //ResultSet rs = statement.executeQuery("SELECT * FROM public.Album"); //AlbumId, Title, ArtistId
                    System.out.println("SQL-part executed successfully");

                    while (rs.next()) {
                        int empid = rs.getInt("empid");
                        int deptno  = rs.getInt("deptno");
                        String name = rs.getString("name");
                        int salary  = rs.getInt("salary");
                        int commission  = rs.getInt("commission");

                        //System.out.printf( "AlbumId = %s , Title = %s, ArtistId = %s ", albumid,title, artistid );
                        System.out.printf( "LolId = %s \n", empid);
                        System.out.printf( "deptno = %s \n", deptno);
                        System.out.printf( "name = %s \n", name);
                        System.out.printf( "salary = %s \n", salary);
                        System.out.printf( "commission = %s \n", commission);
                        System.out.println();
                    }
                    rs.close();
                    break;

                case 2:
                    System.out.println("executing insert");
                    statement.executeUpdate("INSERT INTO public.Album(AlbumId, Title, ArtistId) VALUES (1, 'Franz', 2);");
                    //statement.executeUpdate("INSERT INTO public.emps VALUES (1, 2, Franz, 3, 4);");
                    System.out.println("SQL-part executed successfully");

                    break;

                case 3:
                    statement.executeUpdate("CREATE TABLE \"public\".\"Album\"(\"AlbumId\" INTEGER NOT NULL,\"Title\" VARCHAR(255),\"ArtistId\" INTEGER,PRIMARY KEY (\"AlbumId\"))");
                    System.out.println("Create Table worked");
                    break;

                case 4:
                    //statement.executeQuery("PREPARE lol (int) AS SELECT empid FROM public.emps WHERE empid = $1;");
                    //ResultSet rss = statement.executeQuery("EXECUTE lol (100);");
                    //ResultSet rss = statement.executeQuery("PREPARE lol (int) AS SELECT empid FROM public.emps WHERE empid = $1; EXECUTE lol (100);");
                    statement.executeQuery("PREPARE lol (int) AS SELECT empid FROM public.emps WHERE empid = $1;");
                    ResultSet rss2 = statement.executeQuery("EXECUTE lol (100);");

                    //statement.executeQuery("PREPARE fooplan (int, text, bool, numeric) AS INSERT INTO foo VALUES($1, $2, $3, $4); EXECUTE fooplan(1, 'Hunter Valley', 't', 200.00);");
                    //statement.executeUpdate("PREPARE fooplan (int, text, bool, numeric) AS INSERT INTO foo VALUES($1, $2, $3, $4);");
                    //statement.executeQuery("EXECUTE fooplan(1, 'Hunter Valley', 't', 200.00);");

                    //int empidd = rss2.getInt("empid");
                    //System.out.printf("lol = %s", empidd);
                    break;
            }
            //statement.executeUpdate("INSERT INTO Album(AlbumId, Title, ArtistId) VALUES (1, 'Hello', 1), (2, 'Hello', 2), (3, 'lol', 3);");
            //statement.executeUpdate("INSERT INTO lol(LolId) VALUES (1);");
            //statement.executeUpdate("SELECT empid FROM public.emps");

            /*
            statement.executeUpdate("CREATE TABLE lol (\n" +
                    "    LolId       INT    PRIMARY KEY\n" +
                    ");");

            System.out.println("Hallo");

             */







            statement.close();
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

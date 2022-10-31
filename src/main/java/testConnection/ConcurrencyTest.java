package testConnection;

import java.sql.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ConcurrencyTest {

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
            int iteration = 0;

            while (true) {
                statement = c.createStatement();

                System.out.println("executing select");
                ResultSet rs = statement.executeQuery("SELECT * FROM public.album"); //empid, deptno, name, salary, commission
                //ResultSet rs = statement.executeQuery("SELECT * FROM public.Album"); //AlbumId, Title, ArtistId
                System.out.println("SQL-part executed successfully");

                while (rs.next()) {
                    int albumid = rs.getInt("albumid");
                    //int deptno = rs.getInt("deptno");
                    String title = rs.getString("title");
                    //int salary = rs.getInt("salary");
                    int artistid = rs.getInt("artistid");

                    //System.out.printf( "AlbumId = %s , Title = %s, ArtistId = %s ", albumid,title, artistid );
                    //System.out.printf("LolId = %s \n", empid);
                    System.out.printf("albumid = %s \n", albumid);
                    System.out.printf("title = %s \n", title);
                    System.out.printf("artistid = %s \n", artistid);
                    //System.out.printf("commission = %s \n", commission);
                    System.out.println();


                }
                System.out.printf("iteration number: %s \n", iteration);
                System.out.println();

                rs.close();
                statement.close();
                iteration++;

                TimeUnit.SECONDS.sleep(15);
            }

            //c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void startConnection2 () throws SQLException {

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
            int iteration = 0;

            while (true) {
                statement = c.createStatement();

                /*
                System.out.println("executing select");
                ResultSet rs = statement.executeQuery("SELECT * FROM public.emps"); //empid, deptno, name, salary, commission
                //ResultSet rs = statement.executeQuery("SELECT * FROM public.Album"); //AlbumId, Title, ArtistId
                System.out.println("SQL-part executed successfully");

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

                 */

                System.out.println("executing insert");
                statement.executeUpdate("INSERT INTO public.Album(AlbumId, Title, ArtistId) VALUES (1, 'Franzzz', 2);");
                //statement.executeUpdate("INSERT INTO public.emps VALUES (1, 2, Franz, 3, 4);");
                System.out.println("SQL-part executed successfully");

                System.out.printf("iteration number: %s \n", iteration);
                System.out.println();
                iteration++;
                //rs.close();
                statement.close();

                TimeUnit.SECONDS.sleep(15);
            }

            //c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static void startConnection3 () throws SQLException {

        try {
            Connection c = null;
            Statement statement = null;
            Properties connectionProps = new Properties();
            connectionProps.setProperty("sslmode", "disable");
            String url = "jdbc:postgresql://localhost:5444/";

            c = DriverManager.getConnection(url, connectionProps);

            System.out.println("1Connected to Polypheny1");
            int iteration = 0;

            while (true) {
                statement = c.createStatement();

                /*
                System.out.println("executing select");
                ResultSet rs = statement.executeQuery("SELECT * FROM public.emps"); //empid, deptno, name, salary, commission
                //ResultSet rs = statement.executeQuery("SELECT * FROM public.Album"); //AlbumId, Title, ArtistId
                System.out.println("SQL-part executed successfully");

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

                 */

                System.out.println("1executing insert1");
                statement.executeUpdate("INSERT INTO public.Album(AlbumId, Title, ArtistId) VALUES (2, 'Franz', 2);");
                //statement.executeUpdate("INSERT INTO public.emps VALUES (1, 2, Franz, 3, 4);");
                System.out.println("1SQL-part executed successfully1");

                System.out.printf("1iteration number: %s \n", iteration);
                System.out.println();
                iteration++;
                //rs.close();
                statement.close();
                TimeUnit.SECONDS.sleep(1);

            }

            //c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static void startConnection4 () throws SQLException {

        try {
            Connection c = null;
            Statement statement = null;
            Properties connectionProps = new Properties();
            connectionProps.setProperty("sslmode", "disable");
            String url = "jdbc:postgresql://localhost:5444/";

            c = DriverManager.getConnection(url, connectionProps);

            System.out.println("2Connected to Polypheny");
            int iteration = 0;

            while (true) {
                statement = c.createStatement();

                /*
                System.out.println("executing select");
                ResultSet rs = statement.executeQuery("SELECT * FROM public.emps"); //empid, deptno, name, salary, commission
                //ResultSet rs = statement.executeQuery("SELECT * FROM public.Album"); //AlbumId, Title, ArtistId
                System.out.println("SQL-part executed successfully");

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

                 */

                System.out.println("2executing insert");
                statement.executeUpdate("INSERT INTO public.Album(AlbumId, Title, ArtistId) VALUES (3, 'Lisa', 3);");
                //statement.executeUpdate("INSERT INTO public.emps VALUES (1, 2, Franz, 3, 4);");
                System.out.println("2SQL-part executed successfully");

                System.out.printf("2iteration number: %s \n", iteration);
                System.out.println();
                iteration++;
                //rs.close();
                statement.close();
                TimeUnit.SECONDS.sleep(1);

            }

            //c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

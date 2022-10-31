package testConnection;

import java.sql.*;
import java.util.Properties;

public class ConnectPSQL {

    static void startConnection() {

        Properties connectionProps = new Properties();
        connectionProps.setProperty("ssl", "false");
        connectionProps.setProperty("sslmode", "disable");
        connectionProps.setProperty("user", "flu");
        connectionProps.setProperty("password", "123");
        Connection c = null;
        Statement statement = null;
        // jdbc:postgresql://<database_host>:<port>/<database_name>, username, pw
        //docker container name: psql_simple
        String url = "jdbc:postgresql://localhost:5443/simple_db";

        try {
            Class.forName ("org.postgresql.Driver");
            c = DriverManager.getConnection(url, connectionProps);
            //c = DriverManager.getConnection("jdbc:postgresql://localhost:1234/hello");
            //c.setAutoCommit(false);
            System.out.println("Successfully Connected.");

            statement = c.createStatement();
            //statement.executeUpdate("INSERT INTO lol(LolId) VALUES (5);");  //mit executeQuery erwartet es ein resultat... gibt fehlermeldung
            //statement.executeUpdate("INSERT INTO Album(AlbumId, Title, ArtistId) VALUES (40, 'Hellosöodfj', 2222), (5, 'lolsdfsfsdff', 3000)");

            //ResultSet rs = statement.executeQuery("SELECT * FROM Album;");
            //ResultSet rss = statement.executeQuery("SELECT * FROM public.hihi");
            //ResultSet rs = statement.executeQuery("SELECT * FROM Album;");

            //statement.executeUpdate("PREPARE lol (int) AS SELECT empid FROM public.emps WHERE empid = $1; EXECUTE lol (100);EXECUTE lol (100);");
            //statement.executeUpdate("PREPARE lol (int) AS SELECT empid FROM public.emps WHERE empid = $1;");
            //statement.executeQuery("EXECUTE lol (100);");
            //int lol = statement.executeUpdate("PREPARE lol (int) AS INSERT INTO public.Album(AlbumId, Title, ArtistId) VALUES ($1, $2, $3); EXECUTE lol (114, 'Franz', 3);");
            //PreparedStatement pst = c.prepareStatement("SELECT empid FROM public.emps WHERE empid = ?;");
            //pst.setInt(1, 100);
            //pst.execute();
            int lol1 = statement.executeUpdate("DROP TABLE emps");
            int lol = statement.executeUpdate("CREATE TABLE emps(empid INTEGER NOT NULL, deptno INTEGER, name VARCHAR(20), salary INTEGER, commission INTEGER,PRIMARY KEY (empid));");
            int lol2 = statement.executeUpdate("INSERT INTO emps(empid, deptno, name, salary, commission) VALUES (100, 10, 'Bill', 10000, 1000), (110, 10, 'Theodore', 11500, 250), (150, 20, 'Sebastian', 7000, 400), (200, 30, 'Eric', 8000, 500);");


            System.out.println("sql worked");
            System.out.println(lol1);
            System.out.println(lol);
            System.out.println(lol2);

            //ResultSet rs = null;

            /*
            while (rs.next()) {
                int lol = rs.getInt("empid");
                //String  title = rs.getString("Title");
                //int artistid  = rs.getInt("ArtistId");

                //System.out.printf( "AlbumId = %s , Title = %s, ArtistId = %s ", albumid,title, artistid );
                System.out.printf( "LolId = %s", lol);
                System.out.println();
            }
            rs.close();
             */

            System.out.println("SQL-part executed successfully");

            statement.close();
            c.close();

        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        System.out.println("End of Program");

    }

}

/*

    docker run --name psql_simple -e POSTGRES_USER=flu -e POSTGRES_PASSWORD=123 -p 5443:5432 -v C:\Users\esigu\SynologyDrive\01_Uni\UniBasel\22Polypheny\pgsql_server:/var/lib/postgresql/data -d postgres
    i just changed the second port, port mapping....

    docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres

    docker run --name postgresql -e POSTGRES_USER=myusername -e POSTGRES_PASSWORD=mypassword -p 5432:5432 -v /data:/var/lib/postgresql/data -d postgres

    --name: name of docker container
    -e: sets unique username/pw for postgres db
    -p 80:80 : map port 80 of the host to port 80 in the container
    -v:synchronizez postgresdata with a local folder (data survives even if docker container is terminated
    -d: run the container in detached mode (in the background) --> means that a Docker container runs in the background of your terminal.
        It does not receive input or display output.
        is the parameter that runs the Docker Container in the detached mode, i.e., in the background.
        If you accidentally close or terminate the Command Prompt, the Docker Container will still run in the background.
    postgres: name of Docker image downloaded to run container

    check status of newly created postgrescontainer:
    docker ps -a

    start container:
    docker start psql_simple

    stop container:
    docker stop psql_simple


    port idea: (5442)



   CREATE TABLE Author (
   AUTHOR_ID             INT     PRIMARY KEY     NOT NULL,
   AUTHOR_NAME           TEXT    NOT NULL,
   AUTHOR_AGE            INT     NOT NULL,
   AUTHOR_LEVEL          INT     NOT NULL
);

AlbumId, Title, ArtistId
CREATE TABLE Album (
   AlbumId          INT     PRIMARY KEY     NOT NULL,
   Title            TEXT    NOT NULL,
   ArtistId         INT     NOT NULL
);

CREATE TABLE emps (
    empid       INTEGER PRIMARY KEY
);

INSERT INTO lol(LolId) VALUES (3);
SELECT LolId FROM lol;


 */





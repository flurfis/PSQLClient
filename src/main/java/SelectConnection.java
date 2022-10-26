import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SelectConnection {
    static void startConnection () throws SQLException {

        try {
            Connection c = null;
            Statement statement = null;
            Properties connectionProps = new Properties();
            connectionProps.setProperty("sslmode", "disable");
            String url = "jdbc:postgresql://localhost:5444/";

            c = DriverManager.getConnection(url, connectionProps);
            int iteration = 0;

            while (true) {
                statement = c.createStatement();

                statement.executeUpdate("INSERT INTO public.Album(AlbumId, Title, ArtistId) VALUES (1, 'Franzzz', 2);");

                iteration++;
                statement.close();
            }

            //c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

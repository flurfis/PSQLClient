import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SelectConnection2 {
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


        //One line of all types OID's hardcoded in TypeInfoCache.class
        /*
        int2 = pgNmae
        21 = oid
        5 = sqlType
        java.lang.Integer = java class
        1028 = ?
         */

         final Object[][] types = new Object[][]{
                 {"int2", 21, 5, "java.lang.Integer", 1005},
                 {"int4", 23, 4, "java.lang.Integer", 1007},
                 {"oid", 26, -5, "java.lang.Long", 1028},
                 {"int8", 20, -5, "java.lang.Long", 1016},
                 {"money", 790, 8, "java.lang.Double", 791},
                 {"numeric", 1700, 2, "java.math.BigDecimal", 1231},
                 {"float4", 700, 7, "java.lang.Float", 1021},
                 {"float8", 701, 8, "java.lang.Double", 1022},
                 {"char", 18, 1, "java.lang.String", 1002},
                 {"bpchar", 1042, 1, "java.lang.String", 1014},
                 {"varchar", 1043, 12, "java.lang.String", 1015},
                 {"text", 25, 12, "java.lang.String", 1009},
                 {"name", 19, 12, "java.lang.String", 1003},
                 {"bytea", 17, -2, "[B", 1001},
                 {"bool", 16, -7, "java.lang.Boolean", 1000},
                 {"bit", 1560, -7, "java.lang.Boolean", 1561},
                 {"date", 1082, 91, "java.sql.Date", 1182},
                 {"time", 1083, 92, "java.sql.Time", 1183},
                 {"timetz", 1266, 92, "java.sql.Time", 1270},
                 {"timestamp", 1114, 93, "java.sql.Timestamp", 1115},
                 {"timestamptz", 1184, 93, "java.sql.Timestamp", 1185},
                 {"refcursor", 1790, 2012, "java.sql.ResultSet", 2201},
                 {"json", 114, 1111, "org.postgresql.util.PGobject", 199},
                 {"point", 600, 1111, "org.postgresql.geometric.PGpoint", 1017}};


    }
}

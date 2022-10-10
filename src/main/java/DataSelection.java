import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class DataSelection {

    public static void main (String args[]) throws IOException, SQLException {
        System.out.println("Enter 1 if you want to connect to PSQL,");
        System.out.println("Enter 2 if you want to connect to Polypheny:");
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
                System.out.println("Connecting to PSQL..");
                ConnectPSQL.startConnection();
                break;
            case 2:
                System.out.println("Connecting to Polypheny...");
                ConnectPolypheny.startConnection();
        }

    }
}


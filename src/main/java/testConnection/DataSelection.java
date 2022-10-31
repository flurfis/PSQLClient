package testConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.concurrent.TimeUnit;

public class DataSelection {

    public static void main (String args[]) throws IOException, SQLException, InterruptedException {
        System.out.println("Enter 1 if you want to connect to PSQL,");
        System.out.println("Enter 2 if you want to connect to Polypheny:");
        System.out.println("Enter 3 if you want to connect to Polypheny - concurrency:");
        System.out.println("Enter 4 if you want to connect to Polypheny - concurrency2:");
        System.out.println("Enter 5 if you want to connect to Polypheny - concurrency at the same time:");
        System.out.println("Enter 6 if you want to connect to Polypheny - concurrency at the same time2:");
        System.out.println("Enter 7 if you want to connect via JDBC to Polypheny:");
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
                break;
            case 3:
                ConcurrencyTest.startConnection();
                TimeUnit.SECONDS.sleep(7);
                ConcurrencyTest.startConnection2();
                break;

            case 4:
                ConcurrencyTest.startConnection2();
                break;

            case 5:
                ConcurrencyTest.startConnection3();
                break;

            case 6:
                ConcurrencyTest.startConnection4();
                break;
            case 7:
                ConnectJdbcPolypheny.startConnection();
        }

    }
}


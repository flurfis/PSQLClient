package Evaluation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class SelectConnection {

    public static void main (String args[]) throws IOException, SQLException, InterruptedException {
        String q1Andq2 = "SELECT * FROM emps";
        String q3Andq4 = "CREATE TABLE accounts (\n" +
                "\tuser_id integer NOT NULL,\n" +
                "\tusername VARCHAR ( 50 ) NOT NULL,\n" +
                "\tpassword VARCHAR ( 50 ) NOT NULL,\n" +
                "\temail VARCHAR ( 255 ) NOT NULL,\n" +
                "\tcreated_on integer NOT NULL,\n" +
                "    last_login integer ,\n" +
                "    primary key (user_id)\n" +
                ")";
        String q5Andq6 = "INSERT INTO accounts (user_id, username, password, email, created_on, last_login) VALUES \n" +
                "    (12, 'Kurt', 'password123', 'kurt@gmail.com', 1234, 1234), (3434, 'Sabine', 'SuperSafe', 'Sabine@outlook.com', 98734, 837)";

        String name = "";


        System.out.println("Enter 1 if you want to connect to PSQL,");
        System.out.println("Enter 2 if you want to connect to Polypheny:");
        System.out.println("Enter 3 if you want to connect via JDBC to Polypheny:");
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
                name = "PSQLQ1E1000";
                //PSQLEvaluation.startConnectionSelectOngoingConnection(q1Andq2, name);
                System.out.println("finished part1");

                name = "PSQLQ2E1000";
                //PSQLEvaluation.startConnectionSelectNewConnection(q1Andq2, name);
                System.out.println("finished part2");

                name = "PSQLQ3E1000";
                PSQLEvaluation.dropTable();
                //PSQLEvaluation.startConnectionUpdateOngoingConnection(q3Andq4, name, true);
                System.out.println("finished part3");
                PSQLEvaluation.dropTable();

                name = "PSQLQ4E1000";
                //PSQLEvaluation.startConnectionUpdateNewConnection(q3Andq4, name, true);
                System.out.println("finished part4");

                name = "PSQLQ5E1000";
                PSQLEvaluation.startConnectionUpdateOngoingConnection(q5Andq6, name, false);
                System.out.println("finished part5");

                name = "PSQLQ6E1000";
                PSQLEvaluation.startConnectionUpdateNewConnection(q5Andq6, name, false);
                System.out.println("finished part6");
                break;
            case 2:
                name = "PolyPGIQ1E1000";
                PolyPGIEvaluation.startConnectionSelectOngoingConnection(q1Andq2, name);
                System.out.println("finished part1");

                name = "PolyPGIQ2E1000";
                PolyPGIEvaluation.startConnectionSelectNewConnection(q1Andq2, name);
                System.out.println("finished part2");

                name = "PolyPGIQ3E1000";
                PolyPGIEvaluation.startConnectionUpdateOngoingConnection(q3Andq4, name, true);
                System.out.println("finished part3");
                PolyPGIEvaluation.dropTable();

                name = "PolyPGIQ4E1000";
                PolyPGIEvaluation.startConnectionUpdateNewConnection(q3Andq4, name, true);
                System.out.println("finished part4");

                name = "PolyPGIQ5E1000";
                PolyPGIEvaluation.startConnectionUpdateOngoingConnection(q5Andq6, name, false);
                System.out.println("finished part5");

                name = "PolyPGIQ6E1000";
                PolyPGIEvaluation.startConnectionUpdateNewConnection(q5Andq6, name, false);
                System.out.println("finished part6");
                break;
            case 3:
                name = "PolyJdbcQ1E1000";
                PolyJdbcEvaluation.startConnectionSelectOngoingConnection(q1Andq2, name);
                System.out.println("finished part1");

                name = "PolyJdbcQ2E1000";
                PolyJdbcEvaluation.startConnectionSelectNewConnection(q1Andq2, name);
                System.out.println("finished part2");

                name = "PolyJdbcQ3E1000";
                PolyJdbcEvaluation.startConnectionUpdateOngoingConnection(q3Andq4, name, true);
                System.out.println("finished part3");
                PolyJdbcEvaluation.dropTable();

                name = "PolyJdbcQ4E1000";
                PolyJdbcEvaluation.startConnectionUpdateNewConnection(q3Andq4, name, true);
                System.out.println("finished part4");

                name = "PolyJdbcQ5E1000";
                PolyJdbcEvaluation.startConnectionUpdateOngoingConnection(q1Andq2, name, false);
                System.out.println("finished part5");

                name = "PolyJdbcQ6E1000";
                PolyJdbcEvaluation.startConnectionUpdateNewConnection(q1Andq2, name, false);
                System.out.println("finished part6");
                break;
        }
    }
}

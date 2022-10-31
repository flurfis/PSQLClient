package Evaluation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class PSQLEvaluation {
    static String query2 = "CREATE TABLE accounts (\n" +
            "\tuser_id integer NOT NULL,\n" +
            "\tusername VARCHAR ( 50 ) NOT NULL,\n" +
            "\tpassword VARCHAR ( 50 ) NOT NULL,\n" +
            "\temail VARCHAR ( 255 ) NOT NULL,\n" +
            "\tcreated_on integer NOT NULL,\n" +
            "    last_login integer ,\n" +
            "    primary key (user_id)\n" +
            ");";
    static void startConnectionSelectOngoingConnection(String query, String name) {

        Properties connectionProps = new Properties();
        connectionProps.setProperty("ssl", "false");
        connectionProps.setProperty("sslmode", "disable");
        connectionProps.setProperty("user", "flu");
        connectionProps.setProperty("password", "123");
        Connection c = null;
        Statement statement = null;
        String url = "jdbc:postgresql://localhost:5443/simple_db";
        ArrayList<Long> measuredTime = new ArrayList<Long>();

        try {
            Class.forName ("org.postgresql.Driver");
            c = DriverManager.getConnection(url, connectionProps);

            statement = c.createStatement();

            long startTime;
            long elapsedTime;

            for(int i=0; i< 1000; i++){
                startTime = System.nanoTime();
                statement.executeQuery(query);
                elapsedTime = System.nanoTime() - startTime;
                measuredTime.add(elapsedTime);
            }

            statement.close();
            c.close();

            WriteToCSV writeToCSV = new WriteToCSV();
            writeToCSV.writeToCSV(measuredTime, name);

        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

    }

    static void startConnectionSelectNewConnection(String query, String name) {


        Properties connectionProps = new Properties();
        connectionProps.setProperty("ssl", "false");
        connectionProps.setProperty("sslmode", "disable");
        connectionProps.setProperty("user", "flu");
        connectionProps.setProperty("password", "123");
        Connection c = null;
        Statement statement = null;
        String url = "jdbc:postgresql://localhost:5443/simple_db";
        ArrayList<Long> measuredTime = new ArrayList<Long>();

        try {
            Class.forName ("org.postgresql.Driver");

            long startTime;
            long elapsedTime;

            for(int i=0; i< 1000; i++){
                startTime = System.nanoTime();
                c = DriverManager.getConnection(url, connectionProps);
                statement = c.createStatement();
                statement.executeQuery(query);
                elapsedTime = System.nanoTime() - startTime;
                measuredTime.add(elapsedTime);
                statement.close();
                c.close();
            }


            statement.close();
            c.close();

            WriteToCSV writeToCSV = new WriteToCSV();
            writeToCSV.writeToCSV(measuredTime, name);

        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

    }

    static void startConnectionUpdateOngoingConnection(String query, String name, boolean createTable) {

        Properties connectionProps = new Properties();
        connectionProps.setProperty("ssl", "false");
        connectionProps.setProperty("sslmode", "disable");
        connectionProps.setProperty("user", "flu");
        connectionProps.setProperty("password", "123");
        Connection c = null;
        Statement statement = null;
        String url = "jdbc:postgresql://localhost:5443/simple_db";
        ArrayList<Long> measuredTime = new ArrayList<Long>();

        try {
            Class.forName ("org.postgresql.Driver");
            c = DriverManager.getConnection(url, connectionProps);

            statement = c.createStatement();

            long startTime;
            long elapsedTime;

            if (!createTable) {
                dropTable();
                statement.executeUpdate(query2);
            }

            for(int i=0; i< 1000; i++){
                String q5Andq6 = "INSERT INTO accounts (user_id, username, password, email, created_on, last_login) VALUES (" + i +", 'Kurt', 'password123', 'kurt@gmail.com', 1234, 1234), (" + (1001+i) + ", 'Sabine', 'SuperSafe', 'Sabine@outlook.com', 98734, 837)";
                if (createTable) {
                    startTime = System.nanoTime();
                    statement.executeUpdate(query);
                    elapsedTime = System.nanoTime() - startTime;
                    measuredTime.add(elapsedTime);
                    dropTable();
                } else {
                    startTime = System.nanoTime();
                    statement.executeUpdate(q5Andq6);
                    elapsedTime = System.nanoTime() - startTime;
                    measuredTime.add(elapsedTime);
                }

            }

            statement.close();
            c.close();

            WriteToCSV writeToCSV = new WriteToCSV();
            writeToCSV.writeToCSV(measuredTime, name);

        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

    }

    static void startConnectionUpdateNewConnection(String query, String name, boolean createTable) {

        Properties connectionProps = new Properties();
        connectionProps.setProperty("ssl", "false");
        connectionProps.setProperty("sslmode", "disable");
        connectionProps.setProperty("user", "flu");
        connectionProps.setProperty("password", "123");
        Connection c = null;
        Statement statement = null;
        String url = "jdbc:postgresql://localhost:5443/simple_db";
        ArrayList<Long> measuredTime = new ArrayList<Long>();

        try {
            Class.forName ("org.postgresql.Driver");

            long startTime;
            long elapsedTime;

            if(!createTable) {
                dropTable();
                c = DriverManager.getConnection(url, connectionProps);
                statement = c.createStatement();
                statement.executeUpdate(query2);
                statement.close();
                c.close();
            }

            for(int i=0; i< 1000; i++){
                String q5Andq6 = "INSERT INTO accounts (user_id, username, password, email, created_on, last_login) VALUES (" + i +", 'Kurt', 'password123', 'kurt@gmail.com', 1234, 1234), (" + (1001+i) + ", 'Sabine', 'SuperSafe', 'Sabine@outlook.com', 98734, 837)";
                if (createTable) {
                    startTime = System.nanoTime();
                    c = DriverManager.getConnection(url, connectionProps);
                    statement = c.createStatement();
                    statement.executeUpdate(query);
                    elapsedTime = System.nanoTime() - startTime;
                    measuredTime.add(elapsedTime);
                    dropTable();
                    statement.close();
                    c.close();
                } else {
                    startTime = System.nanoTime();
                    c = DriverManager.getConnection(url, connectionProps);
                    statement = c.createStatement();
                    statement.executeUpdate(q5Andq6);
                    elapsedTime = System.nanoTime() - startTime;
                    measuredTime.add(elapsedTime);
                    statement.close();
                    c.close();
                }

            }


            statement.close();
            c.close();

            WriteToCSV writeToCSV = new WriteToCSV();
            writeToCSV.writeToCSV(measuredTime, name);

        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

    }

    static void dropTable() {

        Properties connectionProps = new Properties();
        connectionProps.setProperty("ssl", "false");
        connectionProps.setProperty("sslmode", "disable");
        connectionProps.setProperty("user", "flu");
        connectionProps.setProperty("password", "123");
        Connection c = null;
        Statement statement = null;
        String url = "jdbc:postgresql://localhost:5443/simple_db";
        ArrayList<Long> measuredTime = new ArrayList<Long>();

        try {
            Class.forName ("org.postgresql.Driver");
            c = DriverManager.getConnection(url, connectionProps);

            statement = c.createStatement();

            statement.executeUpdate("DROP TABLE IF EXISTS accounts; ");

            statement.close();
            c.close();

        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

    }
}

package Evaluation;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class PolyJdbcEvaluation {
    static String query2 = "CREATE TABLE accounts (\n" +
            "\tuser_id integer NOT NULL,\n" +
            "\tusername VARCHAR ( 50 ) NOT NULL,\n" +
            "\tpassword VARCHAR ( 50 ) NOT NULL,\n" +
            "\temail VARCHAR ( 255 ) NOT NULL,\n" +
            "\tcreated_on integer NOT NULL,\n" +
            "    last_login integer ,\n" +
            "    primary key (user_id)\n" +
            ")";

    static void startConnectionSelectOngoingConnection(String query, String name) throws SQLException {
        final String dbHost = "localhost";
        final int port = 20591;
        Connection c = null;
        Statement statement = null;
        final String url = "jdbc:polypheny:http://localhost";
        Properties connectionProps = new Properties();
        connectionProps.setProperty( "user", "pa" );
        connectionProps.setProperty("sslmode", "disable");
        ArrayList<Long> measuredTime = new ArrayList<Long>();

        try {
            Class.forName( "org.polypheny.jdbc.Driver" );
            c = DriverManager.getConnection( url, connectionProps );
            c.setAutoCommit( true );
            statement = c.createStatement();

            long startTime;
            long elapsedTime;

            for(int i=0; i< 1000; i++){
                startTime = System.nanoTime();
                statement.executeQuery(query);
                elapsedTime = System.nanoTime() - startTime;
                measuredTime.add(elapsedTime);
            }

            System.out.println("Executed Statements");



            statement.close();
            c.close();

            WriteToCSV writeToCSV = new WriteToCSV();
            writeToCSV.writeToCSV(measuredTime, "PolyJdbcQ1E1000");
        } catch ( ClassNotFoundException e ) {
            System.out.println("Polypheny JDBC Driver not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void startConnectionSelectNewConnection(String query, String name) throws SQLException {
        final String dbHost = "localhost";
        final int port = 20591;
        Connection c = null;
        Statement statement = null;
        final String url = "jdbc:polypheny:http://localhost";
        Properties connectionProps = new Properties();
        connectionProps.setProperty( "user", "pa" );
        connectionProps.setProperty("sslmode", "disable");
        ArrayList<Long> measuredTime = new ArrayList<Long>();

        try {
            Class.forName( "org.polypheny.jdbc.Driver" );


            long startTime;
            long elapsedTime;

            for(int i=0; i< 1000; i++){
                startTime = System.nanoTime();
                c = DriverManager.getConnection( url, connectionProps );
                c.setAutoCommit( true );
                statement = c.createStatement();
                statement.executeQuery(query);
                elapsedTime = System.nanoTime() - startTime;
                measuredTime.add(elapsedTime);
                statement.close();
                c.close();
            }


            WriteToCSV writeToCSV = new WriteToCSV();
            writeToCSV.writeToCSV(measuredTime, "PolyJdbcQ2E1000");
        } catch ( ClassNotFoundException e ) {
            System.out.println("Polypheny JDBC Driver not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void startConnectionUpdateOngoingConnection(String query, String name, boolean createTable) throws SQLException {
        final String dbHost = "localhost";
        final int port = 20591;
        Connection c = null;
        Statement statement = null;
        final String url = "jdbc:polypheny:http://localhost";
        Properties connectionProps = new Properties();
        connectionProps.setProperty( "user", "pa" );
        connectionProps.setProperty("sslmode", "disable");
        ArrayList<Long> measuredTime = new ArrayList<Long>();

        try {
            Class.forName( "org.polypheny.jdbc.Driver" );
            c = DriverManager.getConnection( url, connectionProps );
            c.setAutoCommit( true );
            statement = c.createStatement();

            long startTime;
            long elapsedTime;

            if(!createTable) {
                dropTable();
                statement.executeUpdate(query2);
            }

            for(int i=0; i< 1000; i++){
                if (createTable) {
                    startTime = System.nanoTime();
                    statement.executeUpdate(query);
                    elapsedTime = System.nanoTime() - startTime;
                    measuredTime.add(elapsedTime);
                    dropTable();
                } else {
                    startTime = System.nanoTime();
                    statement.executeUpdate(query);
                    elapsedTime = System.nanoTime() - startTime;
                    measuredTime.add(elapsedTime);
                }

            }

            statement.close();
            c.close();

            WriteToCSV writeToCSV = new WriteToCSV();
            writeToCSV.writeToCSV(measuredTime, name);
        } catch ( ClassNotFoundException e ) {
            System.out.println("Polypheny JDBC Driver not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void startConnectionUpdateNewConnection(String query, String name, boolean createTable) throws SQLException {
        final String dbHost = "localhost";
        final int port = 20591;
        Connection c = null;
        Statement statement = null;
        final String url = "jdbc:polypheny:http://localhost";
        Properties connectionProps = new Properties();
        connectionProps.setProperty("user", "pa");
        connectionProps.setProperty("sslmode", "disable");
        ArrayList<Long> measuredTime = new ArrayList<Long>();

        try {
            Class.forName("org.polypheny.jdbc.Driver");


            long startTime;
            long elapsedTime;

            if(!createTable) {
                dropTable();
                c = DriverManager.getConnection(url, connectionProps);
                c.setAutoCommit(true);
                statement = c.createStatement();
                statement.executeUpdate(query2);
                statement.close();
                c.close();
            }

            for (int i = 0; i < 1000; i++) {
                if (createTable) {
                    startTime = System.nanoTime();
                    c = DriverManager.getConnection(url, connectionProps);
                    c.setAutoCommit(true);
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
                    c.setAutoCommit(true);
                    statement = c.createStatement();
                    statement.executeUpdate(query);
                    elapsedTime = System.nanoTime() - startTime;
                    measuredTime.add(elapsedTime);
                    statement.close();
                    c.close();
                }

            }

            WriteToCSV writeToCSV = new WriteToCSV();
            writeToCSV.writeToCSV(measuredTime, name);
        } catch (ClassNotFoundException e) {
            System.out.println("Polypheny JDBC Driver not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void dropTable() throws SQLException {
        final String dbHost = "localhost";
        final int port = 20591;
        Connection c = null;
        Statement statement = null;
        final String url = "jdbc:polypheny:http://localhost";
        Properties connectionProps = new Properties();
        connectionProps.setProperty( "user", "pa" );
        connectionProps.setProperty("sslmode", "disable");
        ArrayList<Long> measuredTime = new ArrayList<Long>();

        try {
            Class.forName( "org.polypheny.jdbc.Driver" );
            c = DriverManager.getConnection( url, connectionProps );
            c.setAutoCommit( true );
            statement = c.createStatement();

            statement.executeUpdate("DROP TABLE IF EXISTS accounts");

            statement.close();
            c.close();

        } catch ( ClassNotFoundException e ) {
            System.out.println("Polypheny JDBC Driver not found");
            e.printStackTrace();
        }

    }

    }

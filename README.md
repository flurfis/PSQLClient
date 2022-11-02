# PSQLClient
A simple Client that offers several connection options (not very pretty or tidy).

The packet testConnection offers two connections (both via a PostgreSQL JDBC driver) with two different ports. 
One is intended to connect to a native PostgreSQL database (port 5443), and the other is intended to connect to Polypheny (port 5444).
There are also some options for some basic concurrency tests (also intended for Polypheny). 

The packet Evaluation offers three connections. Two via the PostgreSQL JDBC driver (Polypheny and PostgreSQL, same ports) and one via the Polypheny JDBC driver.
Three query types are executed and the time measured and written to a file.

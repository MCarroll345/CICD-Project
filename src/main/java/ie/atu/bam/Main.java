package ie.atu.bam;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        String host = "localhost";
        String user = "root";
        String password = "Password";
        String database = "bamdatabase";
        String adminPassword = "admin";


        DatabaseManager db = new DatabaseManager(host, user, password, database);

    }
}

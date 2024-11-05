package ie.atu.bam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

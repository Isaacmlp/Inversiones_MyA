package Tets;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectDBTest {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=InversionesMyA;encrypt=false;trustServerCertificate=true";
        String user = "Admin";
        String password = "Sacler123.#";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexión exitosa!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

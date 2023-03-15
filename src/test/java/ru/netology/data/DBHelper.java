package ru.netology.data;


import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

@UtilityClass

public class DBHelper {

    private static final QueryRunner runner = new QueryRunner();
    private static Properties prop = prop();
    private static final Connection conn = getConnection();

    public static Properties prop() {
        Properties properties = new Properties();
        try (InputStream is = DBHelper.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

   @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(
                prop.getProperty("spring.datasource.url"),
                prop.getProperty("spring.datasource.username"),
                prop.getProperty("spring.datasource.password")
        );
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var cleanPaymentEntity = "DELETE FROM payment_entity;";
        var cleanCreditEntity = "DELETE FROM credit_request_entity;";
        var cleanOrderEntity = "DELETE FROM order_entity;";

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
                var deleteStmt = conn.createStatement();
        ) {

            val payment = deleteStmt.executeUpdate(cleanPaymentEntity);
            val credit = deleteStmt.executeUpdate(cleanCreditEntity);
            val order = deleteStmt.executeUpdate(cleanOrderEntity);
        }
    }

    @SneakyThrows
    public static String getCreditPaymentStatus() {
        String statusSQL = "SELECT status FROM credit_request_entity";
        return runner.query(conn, statusSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getCardPaymentStatus() {
        String statusSQL = "SELECT status FROM payment_entity";
        return runner.query(conn, statusSQL, new ScalarHandler<>());
    }
}
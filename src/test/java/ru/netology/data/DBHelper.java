package ru.netology.data;


import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("db.user");
    private static String password = System.getProperty("db.password");

    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }

    @SneakyThrows
    public static void cleanDatabase() {
        val cleanPaymentEntity = "DELETE FROM payment_entity";
        val cleanCreditEntity = "DELETE FROM credit_request_entity";
        val cleanOrderEntity = "DELETE FROM order_entity";
        val runner = new QueryRunner();
        try (val connection = DriverManager.getConnection(
                url, user, password)
        ) {
            runner.update(connection, cleanPaymentEntity);
            runner.update(connection, cleanCreditEntity);
            runner.update(connection, cleanOrderEntity);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @SneakyThrows
    public static String getCreditPaymentStatus() {
        String statusSQL = "SELECT status FROM credit_request_entity";
        return getStatus(statusSQL);
    }

    @SneakyThrows
    public static String getCardPaymentStatus() {
        String statusSQL = "SELECT status FROM payment_entity";
        return getStatus(statusSQL);
    }

    @SneakyThrows
    private static String getStatus(String query) {
        String result = "";
        val runner = new QueryRunner();
        try
                (val conn = DriverManager.getConnection(
                        url, user, password)
                ) {

            result = runner.query(conn, query, new ScalarHandler<String>());
            System.out.println(result);
            return result;
        }

    }
}
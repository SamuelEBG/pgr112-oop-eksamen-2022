package main.java.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class QuizDao<T> {

    public abstract void create(T t) throws SQLException;
    public abstract T retrieve(T t) throws SQLException;
    public abstract T mapFromResultSet(ResultSet resultSet) throws SQLException;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/quizDb?useSSL=false",
                "samuel",
                "1234"
        );
    }
}

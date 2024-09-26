package server.psql;

import common.datamodule.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserCreator {
    private final Connection connection;

    public UserCreator(Connection connection) {
        this.connection = connection;
    }

    private void createUserTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS users "
                + " (id SERIAL PRIMARY KEY,"
                + "  username VARCHAR(50) UNIQUE NOT NULL, "
                + "  password VARCHAR(100) NOT NULL,"
                + "  salt TEXT NOT NULL)");
    }

    public ArrayList<User> init() throws SQLException {
        createUserTable();
        ArrayList<User> Users = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM users");
        while (result.next()) {
            Users.add(new User(result.getString("username"), result.getString("password"), result.getString("salt")));
        }
        return Users;
    }
}

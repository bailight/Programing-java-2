package server.psql;

import common.PullingRequest;
import common.TypeRegistration;
import common.datamodule.Response;
import common.datamodule.User;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import server.processing.PasswordEncode;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;


public class UserManager {
    private final Connection connection;
    private final ArrayList<User> users;
    private final ReadWriteLock lock;
    private static final String CHARACTERS = "0123456789";

    public UserManager(Connection connection, ArrayList<User> users, ReadWriteLock lock) {
        this.connection = connection;
        this.users = users;
        this.lock = lock;
    }

    public Response login(PullingRequest request) throws NoSuchAlgorithmException {
        User user = new User(request.getUsername(), PasswordEncode.encryptPassword(request.getPassword()));
        if (isUserExists(user.getName())) {
            if (checkPassword(user)) {
                return new Response(TypeRegistration.AUTHORIZED);
            } else {
                return new Response(TypeRegistration.DENIED);
            }
        } else {
            registerUser(user);
            return new Response(TypeRegistration.REGISTERED, "пользователь успешно зарегистрирован");
        }
    }

    public boolean isUserExists(String username) {
        try {
            lock.readLock().lock();
            if (users.stream().anyMatch(i -> i.getName().equals(username))){
                return true;
            }
            return false;
        } finally {
            lock.readLock().unlock();
        }
    }

    public boolean checkUser(User user) {
        if (!isUserExists(user.getName())) {
            return false;
        }
        return checkPassword(user);
    }

    public boolean checkPassword(User user) {
        try {
            lock.readLock().lock();
            for (User userc : users) {
                if (userc.getName().equals(user.getName())) {
                    user.setPassword(user.getPassword() + userc.getSalt());
                    break;
                }
            }
            return users.stream().anyMatch(i -> i.getPassword().equals(user.getPassword()));
        } finally {
            lock.readLock().unlock();
        }
    }

    public void registerUser(User user){
        try {
            String username = user.getName();
            String salt = RandomString();
            String pass = user.getPassword() + salt;
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username,password,salt) VALUES (?,?,?)");
            statement.setString(1, username);
            statement.setString(2, pass);
            statement.setString(3, salt);
            statement.execute();
            lock.writeLock().lock();
            users.add(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    private String RandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

}

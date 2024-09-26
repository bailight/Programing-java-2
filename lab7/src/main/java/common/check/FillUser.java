package common.check;

import common.datamodule.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class FillUser extends Fill<User>{
    @Override
    public User build() throws IOException {
        return new User(
                askLogin(),
                askPassword()
        );
    }

    private String askLogin() throws IOException {
        while (true){
            System.out.println("Введите ваш логин:");
            BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
            String login = reader.readLine().trim();
            if (login.isEmpty()){
                System.out.println("Логин не может быть пустым");
            }else{
                return login;
            }
        }
    }

    private String askPassword() throws IOException {
        String pass;
        while (true){
            System.out.println("Введите пароль: ");
            BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
            pass = (Objects.isNull(System.console()))
                    ? reader.readLine().trim()
                    : new String(System.console().readPassword());
            if (pass.isEmpty()){
                System.out.println("Пароль не может быть пустым");
            }
            else{
                return pass;
            }
        }
    }

}



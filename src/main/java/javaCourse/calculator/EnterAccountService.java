package javaCourse.calculator;

import exceptions.WrongLoginException;
import exceptions.WrongPasswordException;
import org.springframework.stereotype.Service;

@Service
public class EnterAccountService implements EnterAccountInterface {

    String acceptedSymbols = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";

    @Override
    public void checkLogin(String login) {
        if (login.length() > 20) {
            throw new WrongLoginException("Login is too long");
        }
        for (int i = 0; i < login.length(); i++) {
            if (acceptedSymbols.indexOf(login.charAt(i)) == -1) {
                throw new WrongLoginException("Invalid symbol in login");
            }
        }
    }

    @Override
    public void checkPasswords(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Passwords do not match");
        } else if (password.length() > 20) {
            throw new WrongPasswordException("Password too long");
        }
        for (int i = 0; i < password.length(); i++) {
            if (acceptedSymbols.indexOf(password.charAt(i)) == -1) {
                throw new WrongPasswordException("Invalid symbol in password");
            }
        }
    }
}

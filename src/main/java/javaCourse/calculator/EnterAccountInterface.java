package javaCourse.calculator;

public interface EnterAccountInterface {
    String acceptedSymbols = "";

    void checkLogin(String login);

    void checkPasswords(String password, String confirmPassword);
}

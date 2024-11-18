package javaCourse.calculator;

import exceptions.WrongLoginException;
import exceptions.WrongPasswordException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnterAccountController {
    private final EnterAccountInterface enterAccount;

    public EnterAccountController(EnterAccountInterface enterAccount) {
        this.enterAccount = enterAccount;
    }

    @GetMapping(path = "login")
    public String login(@RequestParam("login") String login, @RequestParam("password") String password, @RequestParam("passwordConfirm") String passwordConfirm) {
        try {
            enterAccount.checkLogin(login);
            enterAccount.checkPasswords(password, passwordConfirm);
            return "Success";
        } catch (WrongLoginException e) {
            e.printStackTrace();
            return "Wrong login";
        } catch (WrongPasswordException e) {
            e.printStackTrace();
            return "Wrong password";
        } catch (Exception e) {
            return "Error. We are fixing problem.";
        }
    }
}

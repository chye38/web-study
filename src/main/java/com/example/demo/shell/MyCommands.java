package com.example.demo.shell;

import com.example.demo.account.dto.Account;
import com.example.demo.account.service.AuthenticationService;
import com.example.demo.price.service.PriceService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class MyCommands {

    private final AuthenticationService authenticationService;
    private final PriceService priceService;

    @ShellMethod
    public String login(long id, String password) {
        Account account = authenticationService.login(id, password);
        if(Objects.isNull(account)){
            throw new IllegalArgumentException("id or password not correct");
        }
        return account.toString();
    }

    @ShellMethod
    public String logout() {
        authenticationService.logout();
        return "good bye";
    }

    @ShellMethod
    public String currentUser() {
        Account currentAccount = authenticationService.getCurrentAccount();
        if(Objects.isNull(currentAccount)){
            throw new IllegalArgumentException("Login Required");
        }

        return currentAccount.toString();
    }

    @ShellMethod
    public String city() {
        return null;
    }

    @ShellMethod
    public String sector(String city) {
        return null;
    }

    @ShellMethod
    public String price(String city, String sector) {
        return null;
    }

    @ShellMethod
    public String billTotal(String city, String sector, int usage) {
        return null;
    }


}
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

    // 오류를 던지지않고 return으로 오류를 출력하는것이 좋음 하지만 오류를 던지는걸 의도했다고 생각
    @ShellMethod
    public String login(long id, String password) throws IllegalArgumentException {
        Account account = authenticationService.login(id, password);
        return account.toString();
    }

    @ShellMethod
    public String logout() throws IllegalArgumentException{
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
    public String city() throws IllegalArgumentException{
        return priceService.cities().toString();
    }

    @ShellMethod
    public String sector(String city) {
        return priceService.sectors(city).toString();
    }

    @ShellMethod
    public String price(String city, String sector) {
        return priceService.price(city, sector).toString();
    }

    @ShellMethod
    public String billTotal(String city, String sector, int usage) {
        return priceService.billTotal(city, sector, usage);
    }


}
package com.example.demo.account.service;

import com.example.demo.account.dto.Account;
import com.example.demo.common.dataparser.DataParser;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class AuthenticationService {

    private Account currentAccount;
    private final DataParser dataParser;

    public Account login(Long id, String password) {
        List<Account> accountList = dataParser.accounts();
        for (Account account : accountList) {
            if(Objects.equals(account.getId(), id) && Objects.equals(account.getPassword(), password)){
                currentAccount = account;
                return currentAccount;
            }
        }
        return null;
    }

    public void logout() {
        if(Objects.isNull(currentAccount)){
            throw new IllegalArgumentException("Login Required");
        }
        currentAccount = null;
    }

}

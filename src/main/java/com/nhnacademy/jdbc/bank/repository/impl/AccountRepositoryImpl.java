package com.nhnacademy.jdbc.bank.repository.impl;

import com.nhnacademy.jdbc.bank.domain.Account;
import com.nhnacademy.jdbc.bank.repository.AccountRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository {

    public Optional<Account> findByAccountNumber(Connection connection, long accountNumber){
        //todo#1 계좌-조회
        String sql = "SELECT account_number, name, balance\n" +
                "FROM jdbc_account\n" +
                "WHERE account_number = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setLong(1, accountNumber);
            try(ResultSet rs = pstmt.executeQuery();){
                if(rs.next()){
                    return Optional.of(new Account(
                            rs.getLong("account_number"),
                            rs.getString("name"),
                            rs.getLong("balance")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int save(Connection connection, Account account) {
        //todo#2 계좌-등록, executeUpdate() 결과를 반환 합니다.
        String sql = "INSERT INTO jdbc_account VALUES (?, ?, ?)";
        try(PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setLong(1, account.getAccountNumber());
            pstmt.setString(2, account.getName());
            pstmt.setLong(3, account.getBalance());

            return pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByAccountNumber(Connection connection, long accountNumber){
        int count=0;
        //todo#3 select count(*)를 이용해서 계좌의 개수를 count해서 반환
        String sql = "SELECT COUNT(*) `count`\n" +
                "FROM jdbc_account\n" +
                "WHERE account_number = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setLong(1, accountNumber);
            try(ResultSet rs = pstmt.executeQuery();){
                if(rs.next()){
                    return rs.getInt("count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return count;
    }

    @Override
    public int deposit(Connection connection, long accountNumber, long amount){
        //todo#4 입금, executeUpdate() 결과를 반환 합니다.
        String sql = "UPDATE jdbc_account SET balance = balance + ? WHERE account_number = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setLong(1, amount);
            pstmt.setLong(2, accountNumber);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int withdraw(Connection connection, long accountNumber, long amount){
        //todo#5 출금, executeUpdate() 결과를 반환 합니다.
        String sql = "UPDATE jdbc_account SET balance = balance - ? WHERE account_number = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setLong(1, amount);
            pstmt.setLong(2, accountNumber);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByAccountNumber(Connection connection, long accountNumber) {
        //todo#6 계좌 삭제, executeUpdate() 결과를 반환 합니다.
        String sql = "DELETE FROM jdbc_account WHERE account_number = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setLong(1, accountNumber);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

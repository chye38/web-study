package com.nhnacademy.shoppingmall.user.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findByUserIdAndUserPassword(String userId, String userPassword) {
        /*todo#3-1 회원의 아이디와 비밀번호를 이용해서 조회하는 코드 입니다.(로그인)
          해당 코드는 SQL Injection이 발생합니다. SQL Injection이 발생하지 않도록 수정하세요.
         */
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select UserId, UserName, UserPassword, UserBirth, UserAuth, UserPoint, CreatedAt, LatestLogin_at from Users where UserId=? and UserPassword =?";

        log.debug("sql:{}",sql);

        try( PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setString(1, userId);
            pstmt.setString(2, userPassword);

            try( ResultSet rs = pstmt.executeQuery();){
                if(rs.next()){
                    User user = new User(
                            rs.getString("UserId"),
                            rs.getString("UserName"),
                            rs.getString("UserPassword"),
                            rs.getString("UserBirth"),
                            User.Auth.valueOf(rs.getString("UserAuth")),
                            rs.getInt("UserPoint"),
                            Objects.nonNull(rs.getTimestamp("CreatedAt")) ? rs.getTimestamp("CreatedAt").toLocalDateTime() : null,
                            Objects.nonNull(rs.getTimestamp("LatestLogin_at")) ? rs.getTimestamp("LatestLogin_at").toLocalDateTime() : null
                    );
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String userId) {
        //todo#3-2 회원조회
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select UserId, UserName, UserPassword, UserBirth, UserAuth, UserPoint, CreatedAt, LatestLogin_at from Users where UserId=?";

        log.debug("sql:{}",sql);

        try( PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setString(1, userId);
            try( ResultSet rs = pstmt.executeQuery();){
                if(rs.next()){
                    User user = new User(
                            rs.getString("UserId"),
                            rs.getString("UserName"),
                            rs.getString("UserPassword"),
                            rs.getString("UserBirth"),
                            User.Auth.valueOf(rs.getString("UserAuth")),
                            rs.getInt("UserPoint"),
                            Objects.nonNull(rs.getTimestamp("CreatedAt")) ? rs.getTimestamp("CreatedAt").toLocalDateTime() : null,
                            Objects.nonNull(rs.getTimestamp("LatestLogin_at")) ? rs.getTimestamp("LatestLogin_at").toLocalDateTime() : null
                    );
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public int save(User user) {
        //todo#3-3 회원등록, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO Users VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        log.debug("sql:{}",sql);

        try(PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getUserPassword());
            pstmt.setString(4, user.getUserBirth());
            pstmt.setString(5, user.getUserAuth().toString());
            pstmt.setInt(6, user.getUserPoint());
            pstmt.setObject(7, user.getCreatedAt());
            pstmt.setObject(8, user.getLatestLoginAt());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByUserId(String userId) {
        //todo#3-4 회원삭제, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "DELETE FROM Users WHERE UserId=?";
        log.debug("sql:{}",sql);

        try(PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setString(1, userId);

            return pstmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(User user) {
        //todo#3-5 회원수정, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE Users SET UserName = ?, UserPassword = ?, UserBirth = ? WHERE UserId = ?";
        log.debug("sql:{}",sql);

        try(PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getUserPassword());
            pstmt.setString(3, user.getUserBirth());
            pstmt.setString(4, user.getUserId());

            return pstmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateLatestLoginAtByUserId(String userId, LocalDateTime latestLoginAt) {
        //todo#3-6, 마지막 로그인 시간 업데이트, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE Users SET LatestLogin_at = ? WHERE UserId=?";
        log.debug("sql:{}",sql);

        try(PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setObject(1, latestLoginAt);
            pstmt.setString(2, userId);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByUserId(String userId) {
        //todo#3-7 userId와 일치하는 회원의 count를 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT count(*) `count` FROM Users WHERE UserId=?";
        log.debug("sql:{}",sql);

        try(PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setString(1, userId);
            try(ResultSet rs = pstmt.executeQuery();){
                if(rs.next()){
                    return rs.getInt("count");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}

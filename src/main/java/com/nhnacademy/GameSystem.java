package com.nhnacademy;

import com.nhnacademy.util.InputValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class GameSystem {
    public void start() {
        GameSystem gs = new GameSystem();
        Scanner sc = new Scanner(System.in);

        boolean flag = true;
        while (flag){
            System.out.println("\n=== 게임 관리 시스템 ===");
            System.out.println("1. 게임 플레이 기록 입력");
            System.out.println("2. 플레이어 랭킹 조회");
            System.out.println("3. 게임별 통계 분석");
            System.out.println("4. 보안 실습 - SQL Injection 차단");
            System.out.println("5. 결제 내역 관리 시스템");
            System.out.println("6. 플레이어 프로필 조회");
            System.out.println("7. 특정 기간 활성 유저 조회");
            System.out.println("8. 결제 수단 분석 대시보드");
            System.out.println("0. 시스템 종료");

            System.out.print("\n메뉴를 입력하세요 : ");

            int menu = InputValidator.getMenu(sc);

            switch (menu){
                case 1 -> gs.addGamePlayHistory(sc);
                case 2 -> gs.getPlayerRanking();
                case 3 -> gs.getStatisticalAnalysisByGame();
                case 4 -> gs.blockingSQLInjection(sc);
                case 5 -> gs.getPaymentSystem(sc);
                case 6 -> gs.getPlayerProfile(sc);
                case 7 -> gs.getActiveUsersByPeriod(sc);
                case 8 -> gs.getPaymentMethodStats();
                case 0 -> {
                    System.out.println("\n시스템을 종료합니다");
                    flag = false;
                }
                default -> {
                    System.out.println("\n잘못 입력하셨습니다 다시 입력하세요");
                }
            }
        }

        sc.close(); // close
    }

    //    기능 1. 게임 플레이 기록 입력
    private void addGamePlayHistory(Scanner sc){
        // SQL 설정
        String insertPlayHistoriesSQL = "INSERT INTO PlayHistories(player_id, game_id, started_at, ended_at) VALUES (?, ?, ?, ?)";
        String insertScoresSQL = "INSERT INTO Scores(score, result, play_history_id)\n" +
                "SELECT ?, ?, play_history_id\n" +
                "FROM PlayHistories\n" +
                "WHERE player_id = ? AND game_id = ? AND started_at = ? AND ended_at = ?";

        try(Connection conn = DatabaseConfig.getConnection()){
            // 자동 커밋 OFF
            conn.setAutoCommit(false);
            //        사용자로부터 플레이어 ID, 게임 ID, 시작/종료 시간, 점수, 결과를 입력받기
            int playerId = InputValidator.getIntInput(sc, "플레이어 ID");
            int gameId = InputValidator.getIntInput(sc, "게임 ID");
            LocalDateTime startedAt = InputValidator.getDateTimeInput(sc, "시작");
            LocalDateTime endedAt = InputValidator.getDateTimeInput(sc, "종료");

            InputValidator.comparisonDateTime(startedAt, endedAt);

            int score = InputValidator.getIntInput(sc, "점수");
            Result result = InputValidator.getEnumInput(sc, Result.class);

            try(PreparedStatement pstmt1 = conn.prepareStatement(insertPlayHistoriesSQL);
                PreparedStatement pstmt2 = conn.prepareStatement(insertScoresSQL);
            ){
                //        PlayHistories 테이블에 경기 정보 저장
                pstmt1.setInt(1, playerId);
                pstmt1.setInt(2, gameId);
                pstmt1.setObject(3, startedAt);
                pstmt1.setObject(4, endedAt);
                //        Scores 테이블에 점수 정보 저장
                pstmt2.setInt(1, score);
                pstmt2.setString(2, result.name());
                pstmt2.setInt(3, playerId);
                pstmt2.setInt(4, gameId);
                pstmt2.setObject(5, startedAt);
                pstmt2.setObject(6, endedAt);

                //        둘 다 성공하거나 둘 다 실패하도록 트랜잭션 처리
                int rs1 = pstmt1.executeUpdate();
                int rs2 = pstmt2.executeUpdate();

                if(rs1 == 1 && rs2 == 1){
                    System.out.println("기록 완료");
                    conn.commit();
                }else{
                    System.out.println("기록 실패");
                    conn.rollback();
                }
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("SQL 오류 발생 : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("입력 오류 발생 : " + e.getMessage());
        }
    }

    //    기능 2. 플레이어 랭킹 조회
    private void getPlayerRanking(){
        String sql = "SELECT t.닉네임, t.최고점수, t.평균점수, RANK() OVER(ORDER BY t.평균점수 DESC) 등수\n" +
                "FROM (SELECT p.nickname 닉네임, MAX(s.score) 최고점수, ROUND(AVG(s.score)) 평균점수\n" +
                "FROM PlayHistories ph\n" +
                "JOIN Players p ON p.player_id = ph.player_id\n" +
                "JOIN Scores s ON ph.play_history_id = s.play_history_id\n" +
                "GROUP BY nickname\n" +
                "ORDER BY 평균점수 DESC) t";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();)
        {
            System.out.println("=== 플레이어 랭킹 조회 ===");
            System.out.println("-".repeat(40));
            System.out.printf("%-15s %-10s %-10s %-10s\n", "닉네임", "최고점수", "평균점수", "등수");
            System.out.println("-".repeat(40));
            while(rs.next()){
                String nickname = rs.getString("t.닉네임");
                int maxPoint = rs.getInt("t.최고점수");
                int avgPoint = rs.getInt("t.평균점수");
                int rank = rs.getInt("등수");

                System.out.printf("%-15s %-10d %-10d %-10d\n", nickname, maxPoint, avgPoint, rank);
            }

            System.out.println("-".repeat(40));

        } catch (SQLException e) {
            System.err.println("SQL 오류 발생 : " + e.getMessage());
        } catch (Exception e){
            System.err.println("오류 발생 : " + e.getMessage());
        }
    }

    // 기능 3. 게임별 통계 분석
    private void getStatisticalAnalysisByGame(){
        String sql = "SELECT g.title 게임제목, ROUND(AVG(s.score)) 평균점수, MAX(s.score) 최고점수\n" +
                "FROM PlayHistories ph\n" +
                "JOIN Games g ON ph.game_id = g.game_id\n" +
                "JOIN Scores s ON ph.play_history_id = s.play_history_id\n" +
                "GROUP BY g.title";

        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();)
        {
            System.out.println("=== 게임별 통계 분석 ===");
            System.out.println("-".repeat(40));
            System.out.printf("%-15s %-10s %-10s\n", "게임제목", "평균점수", "최고점수");
            System.out.println("-".repeat(40));

            while (rs.next()){
                String title = rs.getString("게임제목");
                int avgPoint = rs.getInt("평균점수");
                int maxPoint = rs.getInt("최고점수");

                System.out.printf("%-15s %-10d %-10d\n", title, avgPoint, maxPoint);
            }

            System.out.println("-".repeat(40));
        } catch (SQLException e){
            System.err.println("SQL 오류 발생 : " + e.getMessage());
        } catch (Exception e){
            System.err.println("오류 발생 : " + e.getMessage());
        }
    }

    // 기능 4. 보안 실습 - SQL Injection 차단
    private void blockingSQLInjection(Scanner sc){
        try(Connection conn = DatabaseConfig.getConnection();){
            System.out.println("정상적인 입력과 비정상적인 입력을 비교합니다");
            System.out.println("정상 입력 : 닉네임 입력");
            System.out.println("비정상 입력 : 프로게이머' OR 1=1 OR nickname='게임마스터");
            System.out.print("[위험한 방법] 닉네임 입력 : ");
            String userInput1 = sc.nextLine();

            String notSafeSQL = "SELECT * FROM Players WHERE nickname = '" + userInput1 + "'";
            String safeSQL = "SELECT * FROM Players WHERE nickname = ?";

            try(Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(notSafeSQL);)
            {
                System.out.println("=== [위험] 플레이어 정보 ===");
                System.out.println("-".repeat(40));
                System.out.printf("%-10s %-15s %-20s %-20s\n", "플레이어 번호", "닉네임", "이메일", "국가");
                System.out.println("-".repeat(40));

                while(rs.next()){
                    System.out.printf("%-10d %-15s %-20s %-20s\n",
                            rs.getInt("player_id"),
                            rs.getString("nickname"),
                            rs.getString("email"),
                            rs.getString("country")
                    );
                }
                System.out.println("-".repeat(40));
            } catch (SQLException e) {
                System.err.println("(stmt)SQL 오류 발생 : " + e.getMessage());
            }

            System.out.print("[안전한 방법] 닉네임 입력 : ");
            String userInput2 = sc.nextLine();
            try(PreparedStatement pstmt = conn.prepareStatement(safeSQL);)
            {
                pstmt.setString(1, userInput2);
                try(ResultSet rs = pstmt.executeQuery();) {

                    System.out.println("=== [안전] 플레이어 정보 ===");
                    System.out.println("-".repeat(40));
                    System.out.printf("%-10s %-15s %-20s %-20s\n", "플레이어 번호", "닉네임", "이메일", "국가");
                    System.out.println("-".repeat(40));

                    while (rs.next()) {
                        System.out.printf("%-10d %-15s %-20s %-20s\n",
                                rs.getInt("player_id"),
                                rs.getString("nickname"),
                                rs.getString("email"),
                                rs.getString("country")
                        );
                    }
                    System.out.println("-".repeat(40));
                }
            } catch (SQLException e) {
                System.err.println("(pstmt) SQL 오류 발생 : " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("오류 발생 : " + e.getMessage());
        }
    }

    // 기능 5. 결제 내역 관리 시스템
    private void getPaymentSystem(Scanner sc){
        System.out.println("=== 결제 내역 관리 시스템 ===");
        System.out.println("-".repeat(40));
        System.out.println("1. 결제 내역 등록");
        System.out.println("2. VIP 고객 찾기");
        System.out.println("-".repeat(40));
        try(Connection conn = DatabaseConfig.getConnection()){
            System.out.print("메뉴 입력 : ");
            int menu = InputValidator.getMenu(sc);
            switch (menu){
                case 1 -> addPaymentHistory(sc, conn);
                case 2 -> getListPaymentHistory(sc, conn);
                default -> throw new IllegalArgumentException("잘못된 값 입력");
            }
            System.out.println("-".repeat(40));
        } catch (SQLException e){
            System.err.println("SQL 오류 발생 : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("오류 발생 : " + e.getMessage());
        }
    }

    // 5-1. 결제 내역 관리 시스템
    private void addPaymentHistory(Scanner sc, Connection conn) throws SQLException{
        int playerId = InputValidator.getIntInput(sc, "플레이어 ID");
        int amount = InputValidator.getIntInput(sc, "금액");
        PaymentMethod paymentMethod = InputValidator.getEnumInput(sc, PaymentMethod.class);
        LocalDateTime paidAt = InputValidator.getDateTimeInput(sc, "구매");
        String description = InputValidator.getStringInput(sc, "아이템 설명");

        String insertSQL = "INSERT INTO Payments(player_id, amount, payment_method, paid_at, item_description) "
                + "VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement pstmt = conn.prepareStatement(insertSQL);){
            pstmt.setInt(1, playerId);
            pstmt.setInt(2, amount);
            pstmt.setString(3, paymentMethod.name());
            pstmt.setObject(4, paidAt);
            pstmt.setString(5, description);

            int flag = pstmt.executeUpdate();

            if(flag == 1){
                System.out.println("플레이어 ID : " + playerId + " 등록 완료");
            }else {
                System.out.println("플레이어 ID : " + playerId + " 등록 실패");
            }
        }
    }

    // 5-2. VIP 고객 찾기
    private void getListPaymentHistory(Scanner sc, Connection conn) throws SQLException{
        String getListSQL = "SELECT p.nickname `닉네임`, SUM(pay.amount) `누적 결제 금액`\n" +
                "FROM Payments pay\n" +
                "JOIN Players p ON p.player_id = pay.player_id\n" +
                "GROUP BY 닉네임\n" +
                "ORDER BY `누적 결제 금액` DESC";

        try(PreparedStatement pstmt = conn.prepareStatement(getListSQL);
            ResultSet rs = pstmt.executeQuery();)
        {
            System.out.printf("%-10s %-10s\n", "닉네임", "누적 결제 금액");
            System.out.println("-".repeat(40));
            while(rs.next()){
                String nickname = rs.getString("닉네임");
                int sumPayAmount = rs.getInt("누적 결제 금액");
                System.out.printf("%-10s %-10d\n", nickname, sumPayAmount);
            }
        }
    }

    // 기능 6. 플레이어 프로필 조회
    private void getPlayerProfile(Scanner sc){
        // SQL 설정
        String getPlayerInfoSQL = "SELECT nickname, country, joined_at FROM Players WHERE player_id = ?";
        String getGameInfoSQL = "SELECT ph.play_history_id, g.title `게임 제목`, TIMEDIFF(ended_at, started_at) `플레이 시간`, s.score `점수`\n" +
                "FROM PlayHistories ph\n" +
                "JOIN Games g ON ph.game_id = g.game_id\n" +
                "JOIN Scores s ON ph.play_history_id = s.play_history_id\n" +
                "WHERE player_id = ?\n" +
                "ORDER BY play_history_id DESC\n" +
                "LIMIT 3";

        try(Connection conn = DatabaseConfig.getConnection()){
            int playerId = InputValidator.getIntInput(sc, "플레이어 ID");

            try(PreparedStatement pstmt = conn.prepareStatement(getPlayerInfoSQL);)
            {
                pstmt.setInt(1, playerId);
                try(ResultSet rs = pstmt.executeQuery();){
                    System.out.println("=== 플레이어 프로필 조회 ===");
                    System.out.println("-".repeat(50));
                    System.out.printf("%-15s %-20s %-10s\n", "닉네임", "국가", "가입일");
                    System.out.println("-".repeat(50));
                    if(rs.next()){
                        String nickname = rs.getString("nickname");
                        String country = rs.getString("country");
                        LocalDateTime joinedAt = rs.getObject("joined_at",  LocalDateTime.class);

                        System.out.printf("%-15s %-20s %-10s\n", nickname, country, joinedAt);
                    }else{
                        throw new SQLException("해당 플레이어가 존재하지않습니다");
                    }
                    System.out.println("-".repeat(50));
                }
            }

            try(PreparedStatement pstmt = conn.prepareStatement(getGameInfoSQL);){
                pstmt.setInt(1, playerId);
                try(ResultSet rs = pstmt.executeQuery();){
                    System.out.println("=== 최근 경기 이력 (3게임) ===");
                    System.out.println("-".repeat(50));
                    System.out.printf("%-15s %-8s %-8s\n", "게임 제목", "플레이 시간", "점수");
                    System.out.println("-".repeat(50));

                    while (rs.next()){
                        String title = rs.getString("게임 제목");
                        String playTime = rs.getString("플레이 시간");
                        int score = rs.getInt("점수");

                        System.out.printf("%-15s %-8s %-8s\n", title, playTime, score);
                    }

                    System.out.println("-".repeat(50));
                }
            }
        } catch(SQLException e){
            System.err.println("SQL 오류 발생 : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("오류 발생 : " + e.getMessage());
        }
    }

    // 기능 7. 특정 기간 활성 유저 조회

    private void getActiveUsersByPeriod(Scanner sc) {
//        2024년 1월 31일 기준으로 최근 2주간(1월 18일~31일) 플레이한 유저 조회
//
//        각 유저의 해당 기간 총 게임 수, 첫 플레이 일시, 마지막 플레이 일시 출력
//
//        게임 수가 많은 순으로 정렬
        String sql = "SELECT nickname `닉네임`,  COUNT(nickname) `총 게임수`, GREATEST(MIN(started_at), ?) `첫 플레이 일시`, LEAST(MAX(ended_at), ?) `마지막 플레이 일시`\n" +
                "from Players p\n" +
                "JOIN PlayHistories ph ON p.player_id = ph.player_id\n" +
                "WHERE started_at <= ?\n" +
                "  AND ended_at\t >= ?\n" +
                "GROUP BY 닉네임\n" +
                "ORDER BY `총 게임수` DESC";

        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);)
        {
            LocalDateTime searchStartedAt = InputValidator.getDateTimeInput(sc, "시작");
            LocalDateTime searchEndedAt = InputValidator.getDateTimeInput(sc, "마지막");

            pstmt.setObject(1, searchStartedAt);
            pstmt.setObject(2, searchEndedAt);
            pstmt.setObject(3, searchEndedAt);
            pstmt.setObject(4, searchStartedAt);


            System.out.println("=== 특정 기간 활성 유저 조회 ===");
            System.out.println("-".repeat(80));
            System.out.printf("%-15s %-10s %-30s %-30s\n", "닉네임", "총 게임수", "첫 플레이 일시", "마지막 플레이 일시");
            System.out.println("-".repeat(80));

            try(ResultSet rs = pstmt.executeQuery();){
                while(rs.next()){
                    String nickname = rs.getString("닉네임");
                    int gameCount = rs.getInt("총 게임수");

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                    String startedAt = formatter.format(rs.getObject("첫 플레이 일시", LocalDateTime.class));
                    String endedAt = formatter.format(rs.getObject("마지막 플레이 일시", LocalDateTime.class));

                    System.out.printf("%-15s %-10d %-30s %-30s\n",  nickname, gameCount, startedAt, endedAt);
                }
            }

            System.out.println("-".repeat(80));
        } catch (SQLException e){
            System.err.println("SQL 오류 발생 : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("오류 발생 : " + e.getMessage());
        }
    }

    // 기능 8. 결제 수단 분석 대시보드
    private void getPaymentMethodStats() {
        String sql = "SELECT payment_method `결제 방법`, SUM(amount) `총 금액`, COUNT(payment_method) `거래 건수`, ROUND(AVG(amount)) `평균 금액`\n" +
                "FROM Payments\n" +
                "GROUP BY payment_method\n" +
                "ORDER BY `거래 건수` DESC";

        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();)
        {
            System.out.println("=== 결제 수단별 분석 리포트 ===");
            System.out.println("-".repeat(40));

            while(rs.next()){
                PaymentMethod paymentMethod = PaymentMethod.valueOf(rs.getString("결제 방법"));
                int totalPrice = rs.getInt("총 금액");
                int transactionCount = rs.getInt("거래 건수");
                int avgPrice = rs.getInt("평균 금액");

                String formatTotalPrice = NumberFormat.getInstance().format(totalPrice);
                String formatAvgPrice = NumberFormat.getInstance().format(avgPrice);

                System.out.printf("%-10s : %-15s원 (%d건, 평균 %s원)\n", paymentMethod.name(), formatTotalPrice, transactionCount, formatAvgPrice);
            }
            System.out.println("-".repeat(40));
        } catch (SQLException e) {
            System.err.println("SQL 오류 발생 : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("오류 발생 : " + e.getMessage());
        }
    }
}

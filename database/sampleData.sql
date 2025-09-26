-- Players 데이터 (10명)
INSERT INTO Players (player_id, nickname, email, country) VALUES
(1, '게임마스터', 'master@game.com', 'Korea'),
(2, '프로게이머', 'pro@game.com', 'Korea'),
(3, '초보탈출', 'newbie@game.com', 'Japan'),
(4, '랭킹킹', 'ranking@game.com', 'USA'),
(5, '스피드런', 'speedrun@game.com', 'Korea'),
(6, '전략가', 'strategy@game.com', 'China'),
(7, '캐주얼', 'casual@game.com', 'Korea'),
(8, '하드코어', 'hardcore@game.com', 'Germany'),
(9, '럭키', 'lucky@game.com', 'Korea'),
(10, '챌린저', 'challenger@game.com', 'France');

-- Games 데이터 (5개)
INSERT INTO Games (game_id, title, genre, release_date) VALUES
(1, '배틀로얄 킹', 'Action', '2023-01-15'),
(2, '퍼즐 마스터', 'Puzzle', '2022-06-10'),
(3, '레이싱 챔피언', 'Racing', '2023-03-20'),
(4, 'RPG 어드벤처', 'RPG', '2021-11-05'),
(5, '스포츠 시뮬레이터', 'Sports', '2023-05-12');

-- PlayHistories 데이터 (20경기)
INSERT INTO PlayHistories (play_history_id, player_id, game_id, started_at, ended_at) VALUES
(1, 1, 1, '2024-01-10 14:30:00', '2024-01-10 15:15:00'),
(2, 2, 1, '2024-01-10 15:00:00', '2024-01-10 15:45:00'),
(3, 3, 2, '2024-01-11 10:20:00', '2024-01-11 11:10:00'),
(4, 4, 2, '2024-01-11 11:30:00', '2024-01-11 12:00:00'),
(5, 5, 3, '2024-01-12 16:45:00', '2024-01-12 17:30:00'),
(6, 1, 2, '2024-01-12 18:00:00', '2024-01-12 18:40:00'),
(7, 2, 3, '2024-01-13 13:15:00', '2024-01-13 14:00:00'),
(8, 6, 1, '2024-01-13 19:20:00', '2024-01-13 20:05:00'),
(9, 7, 4, '2024-01-14 12:30:00', '2024-01-14 13:45:00'),
(10, 8, 4, '2024-01-14 14:15:00', '2024-01-14 15:30:00'),
(11, 9, 5, '2024-01-15 11:00:00', '2024-01-15 12:15:00'),
(12, 10, 5, '2024-01-15 16:30:00', '2024-01-15 17:45:00'),
(13, 1, 3, '2024-01-16 20:00:00', '2024-01-16 20:45:00'),
(14, 3, 1, '2024-01-17 15:30:00', '2024-01-17 16:15:00'),
(15, 4, 3, '2024-01-18 14:00:00', '2024-01-18 14:50:00'),
(16, 5, 2, '2024-01-19 10:15:00', '2024-01-19 11:00:00'),
(17, 6, 4, '2024-01-20 17:45:00', '2024-01-20 19:00:00'),
(18, 7, 1, '2024-01-21 13:20:00', '2024-01-21 14:10:00'),
(19, 8, 2, '2024-01-22 11:40:00', '2024-01-22 12:30:00'),
(20, 9, 3, '2024-01-23 16:10:00', '2024-01-23 17:00:00');

-- Scores 데이터 (각 경기별 점수)
INSERT INTO Scores (score_id, play_history_id, score, result) VALUES
(1, 1, 8500, 'WIN'),
(2, 2, 7200, 'LOSE'),
(3, 3, 9500, 'WIN'),
(4, 4, 6800, 'LOSE'),
(5, 5, 12000, 'WIN'),
(6, 6, 8800, 'WIN'),
(7, 7, 11500, 'WIN'),
(8, 8, 7800, 'LOSE'),
(9, 9, 15200, 'WIN'),
(10, 10, 14800, 'WIN'),
(11, 11, 9200, 'WIN'),
(12, 12, 8900, 'LOSE'),
(13, 13, 13500, 'WIN'),
(14, 14, 6500, 'LOSE'),
(15, 15, 10200, 'WIN'),
(16, 16, 9800, 'WIN'),
(17, 17, 16500, 'WIN'),
(18, 18, 7500, 'LOSE'),
(19, 19, 8200, 'WIN'),
(20, 20, 11800, 'WIN');

-- Payments 데이터 (결제 내역)
INSERT INTO Payments (payment_id, player_id, amount, payment_method, item_description) VALUES
(1, 1, 50000, 'CARD', '프리미엄 캐릭터 스킨'),
(2, 2, 25000, 'BANK', '경험치 부스터 팩'),
(3, 3, 15000, 'CARD', '무기 강화 아이템'),
(4, 4, 80000, 'CARD', 'VIP 멤버십 1개월'),
(5, 5, 35000, 'CASH', '레어 장비 세트'),
(6, 1, 30000, 'CARD', '인벤토리 확장'),
(7, 6, 45000, 'BANK', '스페셜 마운트'),
(8, 7, 20000, 'STORE', '게임 패스'),
(9, 8, 60000, 'CARD', '프리미엄 배틀패스'),
(10, 2, 40000, 'BANK', '캐릭터 슬롯 확장'),
(11, 9, 25000, 'CARD', '코스튬 컬렉션'),
(12, 10, 55000, 'CARD', '길드 부스터'),
(13, 3, 15000, 'CASH', '펫 소환권'),
(14, 4, 70000, 'BANK', 'VIP 멤버십 연장'),
(15, 5, 30000, 'CARD', '스킬 리셋 아이템');

-- 데이터 확인
-- === Players ===
SELECT * FROM Players LIMIT 5;

-- === Games ===
SELECT * FROM Games;

-- === Recent Play Histories ===
SELECT ph.play_history_id, p.nickname, g.title, ph.started_at 
FROM PlayHistories ph 
JOIN Players p ON ph.player_id = p.player_id 
JOIN Games g ON ph.game_id = g.game_id 
ORDER BY ph.started_at DESC LIMIT 5;

-- === Top Scores ===
SELECT p.nickname, g.title, s.score, s.result
FROM Scores s
JOIN PlayHistories ph ON s.play_history_id = ph.play_history_id
JOIN Players p ON ph.player_id = p.player_id  
JOIN Games g ON ph.game_id = g.game_id
ORDER BY s.score DESC LIMIT 5;

-- === Payment Summary ===
SELECT payment_method, COUNT(*) as count, SUM(amount) as total
FROM Payments 
GROUP BY payment_method;
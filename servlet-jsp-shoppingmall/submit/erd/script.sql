CREATE TABLE `Users` (
                         `UserId` varchar(50) NOT NULL COMMENT '아이디',
                         `UserName` varchar(50) NOT NULL COMMENT '이름',
                         `UserPassword` varchar(200) NOT NULL COMMENT 'mysql password 사용',
                         `UserBirth` varchar(10) NOT NULL COMMENT '생년월일 : 1984-05-03',
                         `UserAuth` varchar(10) NOT NULL COMMENT '권한: ROLE_ADMIN,ROLE_USER',
                         `UserPoint` int NOT NULL COMMENT 'default : 1000000',
                         `CreatedAt` datetime NOT NULL COMMENT '가입일자',
                         `LatestLogin_at` datetime DEFAULT NULL COMMENT '마지막 로그인 일자',
                         PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원';

CREATE TABLE `Products` (
                            `ProductId` INT AUTO_INCREMENT COMMENT '아이디',
                            `ProductName` varchar(50) NOT NULL COMMENT '이름',
                            `ProductDescription` VARCHAR(100) COMMENT '설명',
                            `Price` int NOT NULL COMMENT '가격',
                            `Path` VARCHAR(100) NOT NULL COMMENT '이미지 경로',
                            PRIMARY KEY (`ProductId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품';
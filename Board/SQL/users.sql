DROP TABLE IF EXISTS board;
CREATE TABLE `aloha`.`users` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `id` VARCHAR(64) NOT NULL,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT now(),
  `updated_at` TIMESTAMP NOT NULL DEFAULT now(),
  PRIMARY KEY (`no`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
COMMENT = '회원';


-- 샘플 데이터
INSERT INTO `users` 
VALUES (1,UUID(),'user01','$2a$10$uvs53E3VpzyplYT6M6OrOuVjcUErqmItX4rR.uMi1DVDQ9UCS5EMu','사용자','user@naver.com')
      ,(2,UUID(),'user02','$2a$10$YZRF/uYIclkS6Bio3HGN1.sWFWcxemsQ4l7nsJoj4YwEzVRMZtH3S','사용자이','user02@naver.com')
      ,(3,UUID(),'joeun','$2a$10$slU9gAu8siJ1.wN5N0FVcuC46FA/SI5ZWW0Ie3HDyje7bq0O1eEuq','김조은','joeun@naver.com');















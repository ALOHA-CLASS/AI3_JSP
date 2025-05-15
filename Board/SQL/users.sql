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
INSERT INTO users( id, username, password, name, email ) 
VALUES( UUID(), 'user', '123456', '김조은', 'joeun@naver.com' );















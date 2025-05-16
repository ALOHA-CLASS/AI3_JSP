DROP TABLE IF EXISTS board;
CREATE TABLE board (
    no INT AUTO_INCREMENT PRIMARY KEY COMMENT 'PK',
    id VARCHAR(64) NOT NULL UNIQUE COMMENT 'UK',
    title VARCHAR(255) NOT NULL COMMENT '제목',
    user_no INT NOT NULL COMMENT '작성자 PK',
    content TEXT NOT NULL COMMENT '내용',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '작성일',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
) COMMENT '게시판';



TRUNCATE TABLE board;
INSERT INTO board(id, title, user_no, content)
VALUES 
(UUID(), '제목001', 1, '내용001'),
(UUID(), '제목002', 2, '내용002'),
(UUID(), '제목003', 3, '내용003'),
(UUID(), '제목004', 1, '내용004'),
(UUID(), '제목005', 2, '내용004'),
(UUID(), '제목003', 3, '내용003')
;

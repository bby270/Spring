-- 권한
 INSERT INTO 권한 (authority_name) VALUES ('ADMIN');
 INSERT INTO 권한 (authority_name) VALUES ('USER');
 INSERT INTO 권한 (authority_name) VALUES ('INSTRUCTOR');

--공지사항
INSERT INTO 공지사항 (title, content, created_date)
VALUES
    ('긴급 휴강 안내', '강사님의 사정으로 인해 2025.01.01은 휴강으로 조정 되었습니다.', NOW()),
    ('새해맞이 이벤트 안내', '새해를 맞아 학업에 지친 수강생님들을 위해 조식 떡국 제공 이벤트를 진행 합니다.',NOW());
-- 사용자
INSERT INTO 사용자 (user_name, password, email, real_name, birthdate,gender, user_authority, created_at, point)
VALUES
    ('admin', '$2b$12$A0kgVpplgbH3ZZ1E89441eacUXljTTt7nP8I3RdLtW0P6/CXdEnCm','admin1234@gmail.com','관리자','1999-01-01','남','ADMIN',NOW(),500000),
    ('pengsoo', '$2b$12$A0kgVpplgbH3ZZ1E89441eacUXljTTt7nP8I3RdLtW0P6/CXdEnCm', 'pengsoo@email.com', '백병열' , '1999-09-09','남', 'USER', NOW(), 50000),
    ('totoro', '$2b$12$A0kgVpplgbH3ZZ1E89441eacUXljTTt7nP8I3RdLtW0P6/CXdEnCm', 'totoro@email.com', '강준우',  '1999-05-24','남', 'USER', NOW(), 50000),
    ('fourbie', '$2b$12$A0kgVpplgbH3ZZ1E89441eacUXljTTt7nP8I3RdLtW0P6/CXdEnCm', 'fourbie@email.com', '정길수', '1999-06-06','남', 'USER', NOW(), 50000),
    ('pororo', '$2b$12$A0kgVpplgbH3ZZ1E89441eacUXljTTt7nP8I3RdLtW0P6/CXdEnCm', 'pororo@email.com', '김성우', '1999-06-09','남', 'USER', NOW(), 50000),
    ('panda', '$2b$12$A0kgVpplgbH3ZZ1E89441eacUXljTTt7nP8I3RdLtW0P6/CXdEnCm', 'panda@email.com', '정강철', '1994-11-11','남', 'INSTRUCTOR', NOW(), 50000),
    ('eddie', '$2b$12$A0kgVpplgbH3ZZ1E89441eacUXljTTt7nP8I3RdLtW0P6/CXdEnCm', 'eddie@email.com', '남재우', '1995-09-21','남', 'INSTRUCTOR', NOW(), 50000);

--과목
INSERT INTO 과목 (title, explanation, price, instructor_name)
VALUES
    ('1종 자동.1종 수동 면허','1종 자동 면허는 자동변속기 차량만 운전할 수 있는 면허이며, 1종 수동 면허는 자동변속기와 수동변속기 차량 모두 운전할 수 있는 면허입니다.', 10000,'panda'),
    ('2종 보통 면허', '2종 보통면허는 승용차, 15인 이하 승합차, 3.5톤 이하 화물차 등을 운전할 수 있는 면허입니다.',10000,'panda'),
    ('1종 대형 면허', '1종 대형면허는 10톤 이상 대형 화물차 및 버스 등 대형 차량을 운전할 수 있는 면허입니다.',10000,'eddie'),
    ('2종 소형 면허','2종 소형면허는 4륜 이륜차와 3톤 이하 화물차, 9인 이하 승합차 등을 운전할 수 있는 면허입니다.',10000,'eddie');



-- 게시판
INSERT INTO 게시판 (title,content,user_name,created_date,modified_date)
VALUES
('질문','질문있습니다','pengsoo',NOW(),NOW()),
('질문','질문있습니다','fourbie',NOW(),NOW());

-- 답글
INSERT INTO 답글 (user_name, comment,board_id)
VALUES
('pengsoo','ㅇㅇ',1),
('totoro','ㄴㄴ',1);

-- 자동차종류
INSERT INTO 자동차종류 (name)
VALUES
('승용차'),
('화물차'),
('승합차'),
('버스'),
('스포츠카'),
('SUV'),
('트럭'),
('전기차'),
('하이브리드차'),
('밴승용차'),
('15인 이하 승합차'),
('3.5톤 이하 화물차'),
('소형 트레일러'),
('4륜 이륜차'),
('10톤 이상 대형 화물차'),
('대형 버스 (승객 16인 이상)'),
('특수 차량 (크레인, 불도저 등)'),
('4륜 이륜차 (125cc 이하)'),
('3.5톤 이하 화물차'),
('9인 이하 승합차'),
('소형 트레일러'),
('경형 자동차 (일반적으로 660cc 이하)');

-- 이미지
INSERT INTO 이미지 (image_url,subject_id,file_name)
VALUES
('img1.1.GIF',1,'oneNormalExplain'),
('image.png',2,'twoNormalExplain'),
('bus0.jpg',3,'oneBigExplain'),
('img2.1.GIF',4,'twoSmallExplain');

-- 비디오
INSERT INTO 비디오 (video_url,subject_id,img_url)
VALUES
('/mp4/1종자동수동.mp4',1,'/img/img1.1.GIF'),
('/mp4/2종 보통.mp4',2,'/img/image.png'),
('/mp4/1종 대형.mp4',3,'/img/bus0.jpg'),
('/mp4/2종 소형.mp4',4,'/img/img2.1.GIF');

--수강신청
INSERT INTO 수강신청 (user_name, subject_id, purchase_time, completed)
VALUES ('fourbie', 1 ,'2025-01-02 10:00:00',0),
       ('pororo', 2 ,'2025-01-02 11:00:00',0),
       ('totoro',3,'2025-01-03 11:00:00',1);

-- 장바구니
INSERT INTO 장바구니 (subject_id,user_name)
VALUES
(2,'totoro'),
(1,'pengsoo'),
(3,'pororo');


-- subject_type
INSERT INTO subject_type (subject_id,type_id)
VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 15),
(1, 16),
(1, 17),
(1, 18),
(1, 19),
(1, 20),
(1, 21),
(1, 22),
(2, 1),
(2, 2),
(2, 3),
(2, 5),
(2, 6),
(2, 7),
(2, 8),
(2, 9),
(2, 10),
(2, 11),
(2, 12),
(2, 19),
(2, 20),
(2, 21),
(2, 22),
(3, 2),
(3, 3),
(3, 4),
(3, 7),
(3, 15),
(3, 16),
(3, 17),
(4, 22),
(4, 18),
(4, 21);

-- 데이터 삭제 후 id를 1부터 다시 삽입하는 법
--DELETE FROM 공지사항;
--ALTER TABLE 공지사항 AUTO_INCREMENT = 1;


ALTER TABLE 장바구니 DROP FOREIGN KEY FK143xiyeukn5pxyutgd5hdekjs;
ALTER TABLE 장바구니 DROP INDEX UKko5l80niauxjt6seh7wtws999;

ALTER TABLE 장바구니 DROP FOREIGN KEY FK19yn8cr9b97eobdv7fkojxcst;
ALTER TABLE 장바구니 DROP INDEX UKmcfbxhp457ldnb04irg14pcjj;


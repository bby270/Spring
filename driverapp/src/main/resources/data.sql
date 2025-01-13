---- 권한
 INSERT INTO 권한 (authority_name) VALUES ('ADMIN');
 INSERT INTO 권한 (authority_name) VALUES ('USER');
 INSERT INTO 권한 (authority_name) VALUES ('INSTRUCTOR');

-- 공지사항
INSERT INTO 공지사항 (title, content, created_date)
VALUES
('긴급 휴강 안내', '강사님의 사정으로 인해 2025.01.01은 휴강으로 조정 되었습니다.', NOW()),
('새해맞이 이벤트 안내', '새해를 맞아 학업에 지친 수강생님들을 위해 조식 떡국 제공 이벤트를 진행 합니다.',NOW());
-- 사용자
INSERT INTO 사용자 (user_name, password, email, real_name,birthdate, user_authority, created_at, point)
VALUES
('pengsoo', '$2b$12$A0kgVpplgbH3ZZ1E894UXljTTt7nP8I3RdLtW0P6/CXdEnCm', 'pengsoo@email.com', '백병열' , '1999-09-09', 'ADMIN', NOW(), 10000),
('totoro', '$2b$12$A0kgVpplgbH3ZZ1E89441eacUXljTTt7nP8I3RdLtW0P6/CXdEnCm', 'totoro@email.com', '강준우',  '1999-05-24', 'USER', NOW(), 20000),
('fourbie', '$2b$12$A0kgVpplgbH3E89441eacUXljTTt7nP8I3RdLtW0P6/CXdEnCm', 'fourbie@email.com', '정길수', '1999-06-06', 'INSTRUCTOR', NOW(), 10000),
('four', '$2b$12$A0kgVpplgbH3ZZ1E89441eacUjTTt7nP8I3RdLtW0P6/CXdEnCm', 'four@email.com', '김똥개', '1999-06-09', 'INSTRUCTOR', NOW(), 10000),
('fourb', '$2b$12$A0kgVpplgbH3ZZ1E89441eacUXljTTt7nP8I36/CXdEnCm', 'fourb@email.com', '김아무개', '1999-09-06', 'INSTRUCTOR', NOW(), 10000),
('fourbie12', '$2b$12$A0kgVpplgbH3ZZ1E89441eacUX8I3RdLtW0P6/CXdEnCm', 'fourbie12@email.com', '정구지', '1997-06-06', 'INSTRUCTOR', NOW(), 10000);

--과목
INSERT INTO 과목 (title, explanation, price, instructor_name)
VALUES('1종 자동.1종 수동 면허',
'1종 자동 면허는 자동변속기 차량만 운전할 수 있는 면허이며, 1종 수동 면허는 자동변속기와 수동변속기 차량 모두 운전할 수 있는 면허입니다.', 10000,'fourbie'),
('2종 보통 면허', '2종 보통면허는 승용차, 15인 이하 승합차, 3.5톤 이하 화물차 등을 운전할 수 있는 면허입니다.',10000,'four'),
('1종 대형 면허', '1종 대형면허는 10톤 이상 대형 화물차 및 버스 등 대형 차량을 운전할 수 있는 면허입니다.',10000,'fourb'),
('2종 소형 면허','2종 소형면허는 4륜 이륜차와 3톤 이하 화물차, 9인 이하 승합차 등을 운전할 수 있는 면허입니다.',10000,'fourbie12');

-- 게시판
INSERT INTO 게시판 (title,content,user_name,created_date,modified_date)
VALUES
('질문','질문있습니다','pengsoo',NOW(),NOW()),
('질문','질문있습니다','fourbie',NOW(),NOW());

INSERT INTO 답글 (user_name, comment)
VALUES ('pengsoo','ㅇㅇ');

-- 자동차종류
INSERT INTO 자동차종류 (name)
VALUES
('승용차'),
('1톤화물차'),
('대형버스'),
('오토바이 125CC 이상고배기량');

-- 이미지
INSERT INTO 이미지 (image_url)
VALUES
('car photo_url'),
('motor cycle photo_url'),
('bus photo_url');

-- 비디오
INSERT INTO 비디오 (video_url)
VALUES
('Study video_url(1종보통)'),
('Study video_url(2종보통)'),
('Study video_url(1종대형)'),
('Study video_url(2종소형)');

-- 수강신청
INSERT INTO 수강신청 (user_name,subject_id,purchase_time)
VALUES ('totoro',2,'2024-10-27 10:00:00')//




-- 장바구니
INSERT INTO 장바구니 (subject_id,user_name)
VALUES (2,'totoro')
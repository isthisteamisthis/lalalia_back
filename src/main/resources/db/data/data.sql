INSERT INTO TBL_USER(user_no, user_id, user_intro, email, category)
VALUES (1, 1234123530, '가수가 되고 싶은 학생입니다!', 'singer@gmail.com', 'SINGER');

INSERT INTO TBL_POST(post_no, compose_song_no, content, date, like_cnt, perfect_score_no, title, user_no)
VALUES (1, 1, 'contetetnet', '2020-03-26', 1, null, 'titlle', 1);
INSERT INTO TBL_POST(post_no, compose_song_no, content, date, like_cnt, perfect_score_no, title, user_no)
VALUES (2, null, 'contetedfdtnet', '2020-03-26', 3, 2, 'titladsfale', 1);
INSERT INTO TBL_POST(post_no, compose_song_no, content, date, like_cnt, perfect_score_no, title, user_no)
VALUES (3, 1, 'contetetadsfadfnet', '2020-03-26', 2, null, 'titasdfadsfasdflle', 1);

INSERT INTO TBL_LIKE(post_no, user_no)
VALUES (1, 1);
INSERT INTO TBL_LIKE(post_no, user_no)
VALUES (2, 1);
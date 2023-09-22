INSERT INTO TBL_USER(user_no, user_id, user_intro, email, category, max_frequency, min_frequency, avg_score)
VALUES (1, 3016936010, '가수가 되고 싶은 학생입니다!', 'singer@gmail.com', 'SINGER', 100, 10, 0);

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

INSERT INTO TBL_COMPOSE_SONG(compose_song_no, title, user_no)
VALUES (1, 'test1', 1);
INSERT INTO TBL_COMPOSE_SONG(compose_song_no, title, user_no)
VALUES (2, 'test2', 1);
INSERT INTO TBL_COMPOSE_SONG(compose_song_no, title, user_no)
VALUES (3, 'test3', 1);

INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (1, '모든날모든순간','폴 킴', 'https://image.bugsm.co.kr/album/images/200/201547/20154722.jpg?version=20230601001519.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (2, '나에게그대만이','탑현', 'https://image.bugsm.co.kr/album/images/200/40876/4087602.jpg?version=20230612063347.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (3, '내가아니라도','주호', 'https://image.bugsm.co.kr/album/images/200/40732/4073229.jpg?version=20220325180012.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (4, '너의모든순간','성시경', 'https://image.bugsm.co.kr/album/images/200/4132/413209.jpg?version=20210204110908.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (5, '다시만날수있을까','임영웅','https://image.bugsm.co.kr/album/images/200/204625/20462525.jpg?version=20230706021629.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (6, '달빛에그려지는','조미연','https://image.bugsm.co.kr/album/images/200/40903/4090321.jpg?version=20230822012514.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (7, '모래알갱이','임영웅','https://image.bugsm.co.kr/album/images/200/205700/20570088.jpg?version=20230615005409.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (8, '물론','허각','https://image.bugsm.co.kr/album/images/200/40859/4085908.jpg?version=20230429003924.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (9, '사건의지평선','윤하', 'https://image.bugsm.co.kr/album/images/200/40734/4073469.jpg?version=20230110005119.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (10, '사랑그게뭔데','지아','https://image.bugsm.co.kr/album/images/200/40863/4086381.jpg?version=20230518002725.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (11, '사랑은늘도망가','이문세','https://image.bugsm.co.kr/album/images/200/2428/242813.jpg?version=20220722014457.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (12, '사랑의바보','더 넛츠','https://image.bugsm.co.kr/album/images/200/352/35269.jpg?version=20230701040843.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (13, '사랑인가봐','멜로망스', 'https://image.bugsm.co.kr/album/images/200/40713/4071363.jpg?version=20220330120007.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (14, '사랑하지않아서그랬어','임한별', 'https://image.bugsm.co.kr/album/images/200/40850/4085086.jpg?version=20230403063926.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (15, '사막에서꽃을피우듯','우디','https://image.bugsm.co.kr/album/images/200/205776/20577670.jpg?version=20230718012257.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (16, '사실말야내가말야그게그러니까말이야','케이시','https://image.bugsm.co.kr/album/images/200/40873/4087317.jpg?version=20230606012344.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (17, '우리들의블루스','임영웅', 'https://image.bugsm.co.kr/album/images/200/204625/20462525.jpg?version=20230706021629.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (18, '잘지내자우리','로이킴', 'https://image.bugsm.co.kr/album/images/200/40884/4088497.jpg?version=20230707064247.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (19, '취중고백','김민석', 'https://image.bugsm.co.kr/album/images/200/40691/4069154.jpg?version=20230622003642.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (20, '헤어지자말해요','박재정', 'https://image.bugsm.co.kr/album/images/200/40856/4085673.jpg?version=20230421064519.0');
INSERT INTO TBL_SONG_DATA(song_data_no, song_name, artist_name, image_url)
VALUES (21, '희재','성시경', 'https://image.bugsm.co.kr/album/images/200/90006/9000635.jpg?version=20211216021442.0');


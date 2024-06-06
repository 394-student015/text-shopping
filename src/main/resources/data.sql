-- カテゴリーテーブルデータ
INSERT INTO professor(name,major) VALUES('アンパンマン','量子宇宙論');
INSERT INTO professor(name,major) VALUES('ねこねこ','文学部');
INSERT INTO professor(name,major) VALUES('イーロンマスク','社会学部');
INSERT INTO lesson(name) VALUES('水中におけるダンスの効用');
INSERT INTO lesson(name) VALUES('文学概論');
INSERT INTO lesson(name) VALUES('社会学概論');


-- 商品テーブルデータ
INSERT INTO textbook(title,author,price,stock,professor_id,lesson_id) VALUES('Javaの基本', '佐藤一郎',2500,3,1,1);
INSERT INTO textbook(title,author,price,stock,professor_id,lesson_id) VALUES('ごんぎつね', '佐藤二郎',2000,4,1,1);
INSERT INTO textbook(title,author,price,stock,professor_id,lesson_id) VALUES('幸せなたぬき', '佐藤三郎',1900,5,1,1);
INSERT INTO textbook(title,author,price,stock,professor_id,lesson_id) VALUES('ことば', 'ねこ',1000,11,2,2);
INSERT INTO textbook(title,author,price,stock,professor_id,lesson_id) VALUES('車工学', 'イーロンマスク',1000,15,3,3);
-- 管理者テーブルデータ
INSERT INTO management(id,password) VALUES(1,'himitu');
--会員テーブルデータ
INSERT INTO member(id,name,address,tel,email,password,coupon) VALUES(1,'watanabe','aaa','aaa','qqq','himitu',2);
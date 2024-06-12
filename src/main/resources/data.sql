-- カテゴリーテーブルデータ
--INSERT INTO professor(name,major) VALUES('アンパンマン','量子宇宙論');
--INSERT INTO professor(name,major) VALUES('ねこねこ','文学部');
--INSERT INTO professor(name,major) VALUES('イーロンマスク','社会学部');
--INSERT INTO lesson(name) VALUES('水中におけるダンスの効用');
--INSERT INTO lesson(name) VALUES('文学概論');
--INSERT INTO lesson(name) VALUES('社会学概論');


-- 商品テーブルデータ
--INSERT INTO textbook(title,author,price,stock,professor_id,lesson_id) VALUES('Javaの基本', '佐藤一郎',2500,3,1,1);
--INSERT INTO textbook(title,author,price,stock,professor_id,lesson_id) VALUES('ごんぎつね', '佐藤二郎',2000,4,1,1);
--INSERT INTO textbook(title,author,price,stock,professor_id,lesson_id) VALUES('幸せなたぬき', '佐藤三郎',1900,5,1,1);
--INSERT INTO textbook(title,author,price,stock,professor_id,lesson_id) VALUES('ことば', 'ねこ',1000,11,2,2);
--INSERT INTO textbook(title,author,price,stock,professor_id,lesson_id) VALUES('車工学', 'イーロンマスク',1000,15,3,3);
-- 管理者テーブルデータ
INSERT INTO management(id,password) VALUES(1,'himitu');
--会員テーブルデータ
--INSERT INTO member(id,name,address,tel,email,password,coupon) VALUES(1,'watanabe','aaa','aaa','qqq','himitu',0);
--INSERT INTO member(id,name,address,tel,email,password,coupon) VALUES(2,'watanabe','aaa','aaa','x.com','himitu',2);
--注文詳細データ
--INSERT INTO order_details(id,text_id,information_id,quantity) VALUES(1,4,1,3);
--INSERT INTO order_details(id,text_id,information_id,quantity) VALUES(2,3,1,4);
--INSERT INTO order_details(id,text_id,information_id,quantity) VALUES(3,2,1,4);
--INSERT INTO order_details(id,text_id,information_id,quantity) VALUES(4,5,2,4);
--注文データ
--INSERT INTO information(id,member_id,date,totalprice,payment,receive) VALUES(1,1,'2024-06-09',3000,1,1);
--INSERT INTO information(id,member_id,date,totalprice,payment,receive) VALUES(2,1,'2024-06-13',5000,2,2);

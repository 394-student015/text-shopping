-- カテゴリーテーブルデータ
INSERT INTO professor(name,major) VALUES('アンパンマン','量子宇宙論');
INSERT INTO class(name) VALUES('水中におけるダンスの効用');



-- 商品テーブルデータ
INSERT INTO textbook(title,author,price,stock,professor_id,class_id) VALUES('Javaの基本', '佐藤一郎',2500,3,1,1);
INSERT INTO textbook(title,author,price,stock,professor_id,class_id) VALUES('ごんぎつね', '佐藤二郎',2000,4,1,1);
INSERT INTO textbook(title,author,price,stock,professor_id,class_id) VALUES('幸せなたぬき', '佐藤三郎',1900,5,1,1);
-- 管理者テーブルデータ
INSERT INTO management(id,password) VALUES(1,'himitu');
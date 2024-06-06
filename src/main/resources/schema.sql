
DROP VIEW IF EXISTS v_textbook;
DROP VIEW IF EXISTS v_information;
DROP TABLE IF EXISTS management;
DROP TABLE IF EXISTS information;
DROP TABLE IF EXISTS textbook;
DROP TABLE IF EXISTS professor;
DROP TABLE IF EXISTS lesson;
DROP TABLE IF EXISTS member;


/**********************************/
/* テーブル名: 会員テーブル */
/**********************************/
CREATE TABLE member(
		id SERIAL NOT NULL,
		name VARCHAR(30) NOT NULL,
		address VARCHAR(40) NOT NULL,
		tel VARCHAR(12) NOT NULL,
		email VARCHAR(50) NOT NULL,
		password VARCHAR(10),
		coupon INTEGER NOT NULL
);

/**********************************/
/* テーブル名: 授業テーブル */
/**********************************/
CREATE TABLE lesson(
		id SERIAL NOT NULL,
		name VARCHAR(30) NOT NULL
);

/**********************************/
/* テーブル名: 教授テーブル */
/**********************************/
CREATE TABLE professor(
		id SERIAL NOT NULL,
		name VARCHAR(30) NOT NULL,
		major VARCHAR(30) NOT NULL
);

/**********************************/
/* テーブル名: 教科書テーブル */
/**********************************/
CREATE TABLE textbook(
		id SERIAL NOT NULL ,
		title VARCHAR(50) NOT NULL,
		author VARCHAR(30) NOT NULL,
		price INTEGER NOT NULL,
		stock INTEGER NOT NULL,
		professor_id INTEGER NOT NULL,
		lesson_id INTEGER NOT NULL
);

/**********************************/
/* テーブル名: 購入情報テーブル */
/**********************************/
CREATE TABLE information(
		id SERIAL NOT NULL,
		member_id INTEGER NOT NULL,
		text_id INTEGER NOT NULL,
		date DATE NOT NULL,
		totalprice INTEGER NOT NULL,
		payment INTEGER NOT NULL,
		receive INTEGER NOT NULL
);

/**********************************/
/* テーブル名: 管理者テーブル */
/**********************************/
CREATE TABLE management(
		id SERIAL NOT NULL,
		password VARCHAR(10) NOT NULL
		
);

/******************/
/*view：textbook*/
/******************/
create view v_textbook as
select 
lesson.name as lesson,
professor.name as professor,
textbook.id,
textbook.title,
textbook.author,
textbook.price,
textbook.stock 
from textbook 
join professor on professor.id=textbook.professor_id 
join lesson on lesson.id=textbook.lesson_id;

/*********************/
/*view：information*/
/*********************/
create view v_information as
select
member.id,
member.name,
member.address,
member.tel,
member.email,
member.password,
member.coupon

from information 
join member on member.id=information.id 
join textbook on textbook.id=information.id;



ALTER TABLE member ADD CONSTRAINT IDX_member_PK PRIMARY KEY (id);

ALTER TABLE lesson ADD CONSTRAINT IDX_lesson_PK PRIMARY KEY (id);

ALTER TABLE professor ADD CONSTRAINT IDX_professor_PK PRIMARY KEY (id);

ALTER TABLE textbook ADD CONSTRAINT IDX_textbook_PK PRIMARY KEY (id);
ALTER TABLE textbook ADD CONSTRAINT IDX_textbook_FK0 FOREIGN KEY (lesson_id) REFERENCES lesson (id);
ALTER TABLE textbook ADD CONSTRAINT IDX_textbook_FK1 FOREIGN KEY (professor_id) REFERENCES professor (id);

ALTER TABLE information ADD CONSTRAINT IDX_information_PK PRIMARY KEY (id);
ALTER TABLE information ADD CONSTRAINT IDX_information_FK0 FOREIGN KEY (text_id) REFERENCES textbook (id);
ALTER TABLE information ADD CONSTRAINT IDX_information_FK1 FOREIGN KEY (member_id) REFERENCES member (id);

ALTER TABLE management ADD CONSTRAINT IDX_management_PK PRIMARY KEY (password);


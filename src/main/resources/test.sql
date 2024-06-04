create view v_textbook as
select class.name as lecture,professor.name as professor,textbook.id,textbook.title,textbook.author,textbook.price,textbook.stock 
from textbook join professor on professor.id=textbook.professor_id join class on class.id=textbook.class_id;

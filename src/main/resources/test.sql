select class.name,professor.name,textbook.id,textbook.title,textbook.author,textbook.price,textbook.stock,textbook.professor_id,textbook.class_id 
from textbook join professor on professor.id=textbook.professor_id join class on class.id=textbook.class_id;

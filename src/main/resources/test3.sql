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
join lesson on lesson.id=textbook.lesson_id
order by textbook.id;

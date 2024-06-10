create view v_information as
select 

information.id,
information.title,
information.quantity,
information.date,
information.totalprice,
information.payment,
information.receive 

from information 
join member on information.id=member.id 
join textbook on information.id=textbook.id;

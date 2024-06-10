select 

orderDetail.information_id,
orderDetail.quantity,
information.member_id,
information.date,
information.totalprice
information.payment,
information,receive,
textbook.id,



from information 
join member on information.id=member.id 
join textbook on information.id=textbook.id;
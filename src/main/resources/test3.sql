select 

imformation.id,
member.name,
member.address,
member.tel,
member.email,
member.password,
member.coupon 

from information 
join member on information.id=member.id 
join textbook on information.id=textbook.id;
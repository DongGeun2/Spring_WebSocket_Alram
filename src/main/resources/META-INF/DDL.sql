--member_alert : 회원

create table member_alert(
  userid varchar2(100),
  pwd varchar2(2000),
  name varchar2(100)
);

--roll_alert : 회원롤(시큐리티)

create table roll_alert(
  userid varchar2(20),
  role_name varchar2(30)
);

--회원가입 시 roll 추가 트리거 생성

create or replace trigger alert_t_01 
after insert on member_alert
for each row
BEGIN
  insert into roll_alert(userid, Role_name)
  values(:NEW.userid ,'ROLE_USER');
END;

--message : 쪽지

create table message (
  m_to varchar2(100),
  m_from varchar2(100),
  msg varchar2(2000),
  m_check number default 0
);


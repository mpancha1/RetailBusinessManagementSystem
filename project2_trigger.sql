create or replace trigger t
after insert on customers
for each row
declare
 begin
 insert into logs values(log_sequence.nextval,user,'insert',to_char(sysdate,'DD-MON-YY'),'customers',:new.cid);
end;
/

create or replace trigger t1
after update of last_visit_date on customers
for each row
begin
insert into logs values(log_sequence.nextval,user,'update',to_char(sysdate,'DD-MON-YY'),'customers',:new.cid);
end;
/

create or replace trigger t2
after insert on purchases
for each row
begin
insert into logs values(log_sequence.nextval, user, 'insert',to_char(sysdate,'DD-MON-YY'), 'purchases', :new.pur#);
end;
/

create or replace trigger t3
after update of qoh on products
for each row
begin
insert into logs values(log_sequence.nextval,user,'update',to_char(sysdate,'DD-MON-YY'),'products',:new.pid);
end;  
/

create or replace trigger t4
after insert on suppliers
for each row
begin
insert into logs values(log_sequence.nextval,user,'insert',to_char(sysdate,'DD-MON-YY'),'suppliers',:new.sid);
end;
/

create or replace trigger purchase_made after insert on purchases
for each row
Declare
new_date date;
visits_no number(4);
qoh_temp number(5);
final_qoh number(5);
Qoh_th number(4);
M number(5);
M_temp number(5);
Sid_no char(2);
Begin
Select last_visit_date into new_date from customers where cid= :new.cid;
Select visits_made into visits_no from customers where cid= :new.cid;
IF new_date != SYSDATE THEN
visits_no := visits_no+1;
Update customers set last_visit_date= to_char(sysdate,'DD-MON-YY') where cid= :new.cid;
Update customers set visits_made = visits_no where cid= :new.cid;
END IF;
Select qoh into qoh_temp from products where pid = :new.pid;
Select qoh_threshold  into qoh_th from products where pid = :new.pid;
IF qoh_temp < qoh_th THEN
M := qoh_th - qoh_temp + 1;
M_temp := M +qoh_temp +10;
final_qoh := qoh_temp + M_temp;
Select sid into Sid_no from supplies where pid = :new.pid and ROWNUM=1 
Order by sid;
Insert into supplies values(sup_sequence.NEXTVAL,:new.pid, sid_no, sysdate, M_temp);
dbms_output.put_line('new qoh=' || final_qoh);
Update products set qoh = final_qoh where pid=:new.pid;
END IF;
end;
/

create or replace trigger delete_trigger
after delete on purchases
for each row
declare
begin
update products set qoh = qoh + :old.qty where pid = :old.pid;
update customers set visits_made = visits_made + 1, last_visit_date = to_char(sysdate,'DD-MON-YY')  where cid = :old.cid;
end;
 /

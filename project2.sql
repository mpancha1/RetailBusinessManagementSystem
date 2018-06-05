
create  sequence sup_sequence start with 1010 increment by 1;
 create  sequence log_sequence start with 10000 increment by 1;
 create  sequence pur_sequence start with 100015 increment by 1;




create or replace package project2 as
    type ref_cursor is ref cursor;
    function show_employees
	return ref_cursor;
	function show_customers
	return ref_cursor;
	function show_purchases
	return ref_cursor;
	function show_products
	return ref_cursor;
	function show_logs
	return ref_cursor;
	function show_suppliers
	return ref_cursor;
	function show_supplies
    return ref_cursor;
	function purchase_saving(pur IN NUMBER) 
	RETURN NUMBER;
	procedure delete_purchase(pur purchases.pur#%TYPE );
	function monthly_sale_activities(emp_data in CHAR) 
	return ref_cursor;
	procedure add_customer(
 c_id customers.cid%TYPE,
  c_name customers.name%TYPE,
  c_telephone# customers.telephone#%TYPE);
	function add_purchase(e_id in CHAR, p_id in CHAR,
  c_id in CHAR, pur_qty in NUMBER) return NUMBER ;
  
    end;
	/
	
	
create or replace package body project2 as
    function show_employees
    return ref_cursor is
    rce ref_cursor;
    begin
    open rce for
    select * from employees;
    return rce;
    end;
	function show_customers
    return ref_cursor is
    rcc ref_cursor;
    begin
    open rcc for
    select * from customers;
    return rcc;
    end;
	function show_products
    return ref_cursor is
    rcp ref_cursor;
    begin
    open rcp for
    select * from products;
    return rcp;
    end;
	function show_purchases
    return ref_cursor is
    rcpu ref_cursor;
    begin
    open rcpu for
    select * from purchases;
    return rcpu;
    end;
	function show_logs
    return ref_cursor is
    rcl ref_cursor;
    begin
    open rcl for
    select * from logs;
    return rcl;
    end;
	function show_suppliers
    return ref_cursor is
    rcsupplier ref_cursor;
    begin
    open rcsupplier for
    select * from suppliers;
    return rcsupplier;
    end;
	function show_supplies
    return ref_cursor is
    rcsupplies ref_cursor;
    begin
    open rcsupplies for
    select * from supplies;
    return rcsupplies;
    end;
	FUNCTION purchase_saving(pur IN NUMBER) RETURN NUMBER
	IS saving NUMBER;
	BEGIN
	select original_price * qty - total_price into saving
	from purchases pu, products pr
	where pu.pid = pr.pid and pu.pur# = pur;
	RETURN saving;
	EXCEPTION
   	WHEN NO_DATA_FOUND THEN
	saving:=-1;
	Return saving;
	END;
	procedure delete_purchase(
  pur purchases.pur#%TYPE )
  is
  begin
  delete from purchases where pur# = pur;
  commit;
  end;
function monthly_sale_activities(emp_data in char) 
 return ref_cursor is rc ref_cursor;
begin
open rc for
select to_char(ptime,'MON-YYYY') as month,  sum(pu.total_price) as total_sale, sum(pu.qty) as quantity, count(pu.eid) as number_of_sale, emp.name from purchases pu, (select name from employees where eid = emp_data) emp where
pu.eid = emp_data group by to_char(pu.ptime,'MON-YYYY'), emp.name;
return rc;
end;
PROCEDURE add_customer(
 c_id customers.cid%TYPE,
  c_name customers.name%TYPE,
  c_telephone# customers.telephone#%TYPE)
  is
  begin
  insert into customers(cid,name,telephone#,visits_made,last_visit_date) values (c_id,c_name,c_telephone#,'1',to_char(SYSDATE,'DD-MON-YYYY'));
 commit;
 End;
 function add_purchase(e_id in CHAR, p_id in CHAR, c_id in CHAR, pur_qty in NUMBER) return NUMBER is q_oh NUMBER; discount_cat NUMBER; discount NUMBER(3,2); price number(6,2); total_pr number(6,2);
begin
Select qoh into q_oh from products where pid=p_id;
If q_oh >= pur_qty THEN
dbms_output.put_line('GOOD');
Select discnt_category into discount_cat from products where pid=p_id;
	Select discnt_rate into discount from discounts where discnt_category=discount_cat;
	Select original_price into price from products where pid=p_id;
	
	total_pr:=price*(1-discount)*pur_qty;
	q_oh:=q_oh-pur_qty;
	UPDATE products SET qoh = q_oh WHERE pid=p_id;
insert into purchases values(pur_sequence.NEXTVAL,e_id,p_id,c_id,pur_qty,sysdate, total_pr);
select qoh into q_oh from products where pid = p_id;	
	

ELSE	
	q_oh := -123;
	dbms_output.put_line('Insufficient quantity in stock. ');
END IF;
Return q_oh;
end;
end;
/

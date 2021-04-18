use Moneytrack;

insert into category (name,created_at,type) values ('Paycheck','2021-04-18 08:00:00.000000','Income');
insert into category (name,created_at,type) values ('Trade','2021-04-18 08:00:00.000000','Income');
insert into category (name,created_at,type) values ('Dividend','2021-04-18 08:00:00.000000','Income');
insert into category (name,created_at,type) values ('Rent','2021-04-18 08:00:00.000000','Income');
insert into category (name,created_at,type) values ('Gift','2021-04-18 08:00:00.000000','Income');


insert into category (name,created_at,type) values ('Bill','2021-04-18 08:00:00.000000','Expense');
insert into category (name,created_at,type) values ('Electronic','2021-04-18 08:00:00.000000','Expense');
insert into category (name,created_at,type) values ('Entertainment','2021-04-18 08:00:00.000000','Expense');
insert into category (name,created_at,type) values ('Food','2021-04-18 08:00:00.000000','Expense');
insert into category (name,created_at,type) values ('Gift','2021-04-18 08:00:00.000000','Expense');
insert into category (name,created_at,type) values ('House Item','2021-04-18 08:00:00.000000','Expense');
insert into category (name,created_at,type) values ('Junk Food','2021-04-18 08:00:00.000000','Expense');
insert into category (name,created_at,type) values ('Loan','2021-04-18 08:00:00.000000','Expense');
insert into category (name,created_at,type) values ('Medical','2021-04-18 08:00:00.000000','Expense');
insert into category (name,created_at,type) values ('Transportation','2021-04-18 08:00:00.000000','Expense');
insert into category (name,created_at,type) values ('Miscellaneous','2021-04-18 08:00:00.000000','Expense');

select * from category order by type desc,name;
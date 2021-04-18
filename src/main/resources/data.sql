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


insert into user(username, enabled, last_login, locked, password, registration_date, user_role) values('exophrenik@gmail.com',true,'2020-04-16 16:21:21.000000',false,'ma582468','2020-04-16 16:21:21.000000','USER');
insert into user(username, enabled, last_login, locked, password, registration_date, user_role) values('memechi@hotmail.com',true,'2020-04-16 16:21:21.000000',false,'ma582468','2020-04-16 16:21:21.000000','USER');

select * from user;

insert into user_categories(username, name, type) values ('exophrenik@gmail.com','Paycheck','Income');
insert into user_categories(username, name, type) values ('exophrenik@gmail.com','Trade','Income');
insert into user_categories(username, name, type) values ('exophrenik@gmail.com','Dividend','Income');
insert into user_categories(username, name, type) values ('exophrenik@gmail.com','Rent','Income');
insert into user_categories(username, name, type) values ('exophrenik@gmail.com','Gift','Income');
insert into user_categories(username, name, type) values ('exophrenik@gmail.com','Bill','Expense');
insert into user_categories(username, name, type) values ('exophrenik@gmail.com','Electronic','Expense');
insert into user_categories(username, name, type) values ('exophrenik@gmail.com','Entertainment','Expense');
insert into user_categories(username, name, type) values ('exophrenik@gmail.com','Food','Expense');
insert into user_categories(username, name, type) values ('exophrenik@gmail.com','Gift','Expense');
insert into user_categories(username, name, type) values ('exophrenik@gmail.com','House Item','Expense');
insert into user_categories(username, name, type) values ('exophrenik@gmail.com','Junk Food','Expense');
insert into user_categories(username, name, type) values ('exophrenik@gmail.com','Loan','Expense');
insert into user_categories(username, name, type) values ('exophrenik@gmail.com','Medical','Expense');
insert into user_categories(username, name, type) values ('exophrenik@gmail.com','Transportation','Expense');
insert into user_categories(username, name, type) values ('exophrenik@gmail.com','Miscellaneous','Expense');

insert into user_categories(username, name, type) values ('memechi@hotmail.com','Paycheck','Income');
insert into user_categories(username, name, type) values ('memechi@hotmail.com','Trade','Income');
insert into user_categories(username, name, type) values ('memechi@hotmail.com','Dividend','Income');
insert into user_categories(username, name, type) values ('memechi@hotmail.com','Rent','Income');
insert into user_categories(username, name, type) values ('memechi@hotmail.com','Gift','Income');
insert into user_categories(username, name, type) values ('memechi@hotmail.com','Bill','Expense');
insert into user_categories(username, name, type) values ('memechi@hotmail.com','Electronic','Expense');
insert into user_categories(username, name, type) values ('memechi@hotmail.com','Entertainment','Expense');
insert into user_categories(username, name, type) values ('memechi@hotmail.com','Food','Expense');
insert into user_categories(username, name, type) values ('memechi@hotmail.com','Gift','Expense');
insert into user_categories(username, name, type) values ('memechi@hotmail.com','House Item','Expense');
insert into user_categories(username, name, type) values ('memechi@hotmail.com','Junk Food','Expense');
insert into user_categories(username, name, type) values ('memechi@hotmail.com','Loan','Expense');
insert into user_categories(username, name, type) values ('memechi@hotmail.com','Medical','Expense');
insert into user_categories(username, name, type) values ('memechi@hotmail.com','Transportation','Expense');
insert into user_categories(username, name, type) values ('memechi@hotmail.com','Miscellaneous','Expense');

select * from user_categories;



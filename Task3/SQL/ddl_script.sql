drop table posting_product;
drop table posting;
drop table product;
drop table account;
drop table department;
drop table job; 
drop table hibernate_sequence;
drop table application;

create table Application (
	id bigint primary key auto_increment,
	title varchar(50) not null unique
);

create table Job (
	id bigint primary key auto_increment,
	title varchar(100) not null
);

create table Department (
	id bigint primary key auto_increment,
	title varchar(100) not null
);

create table Account (
	name varchar(30) primary key,
	is_active bool not null,
	job_id bigint,
	department_id bigint,
	application_id bigint not null,
	foreign key (job_id) references job (id),
	foreign key (department_id) references department(id),
	foreign key (application_id) references application(id)
);

create table Product (
	id bigint primary key auto_increment,
	description varchar(150) not null
);

create table Posting (
	number bigint primary key auto_increment,
	doc_date date,
	posting_date date,
	account_name varchar(30),
	is_authorized bool
);


create table Posting_Product (
	id bigint primary key auto_increment,
	post_number bigint not null,
	product_id bigint not null,
	item int not null,
	quantity int not null,
	unit varchar(10) not null,
	amount_lc double not null,
	currency varchar(10) not null,
	foreign key (post_number) references posting (number),
	foreign key (product_id) references product (id)
);

CREATE TABLE a1company.hibernate_sequence (
	next_val bigint NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci
COMMENT='';

insert into hibernate_sequence values(1);

-- not used
create trigger authorize_posting_on_login_insert
	after insert on account for each row
	begin
		if (new.is_active is true) then
			update posting set posting.is_authorized = true 
				where posting.account_name = new.name;
		end if;
	end;
		
		





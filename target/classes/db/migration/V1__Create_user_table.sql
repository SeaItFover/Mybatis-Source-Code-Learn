create table user
(
	id int auto_increment
		primary key,
	userName varchar(50) not null,
	address varchar(50) null,
	sex char null,
	birthday date null
);


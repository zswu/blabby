drop database if exists microBlog;
create database if not exists microBlog default character set utf8;

--选择数据库
use microBlog;

create table user(
	id bigint auto_increment primary key,
	email varchar(25) unique not null,
	password varchar(25) not null,
	nickName varchar(25) unique not null,
	name varchar(20),
	gender varchar(6),
	bornDate date,
	accessAddress varchar(20)
);


create table address(
	id bigint auto_increment primary key,
	province varchar(20),
	city varchar(20),
	userId bigint unique,
	foreign key(userId) references user(id)
);


create table friend(
	id bigint auto_increment primary key,
	userId bigint,
	fuserId bigint,
	unique(userId,fuserId),
	foreign key(userId) references user(id),
	foreign key(fuserId) references user(id)
);

create table microBlog(
	id bigint auto_increment primary key,
	content varchar(140) not null,
	publishTime datetime not null,
	userId bigint,
	foreign key(userId) references user(id)
);

create table comment(
	id bigint auto_increment primary key,
	content varchar(140) not null,
	commentTime datetime not null,
	userId bigint,
	blogId bigint,
	foreign key(userId) references user(id),
	foreign key(blogId) references microBlog(id)
);


--外键引用，只能引用其他表中的唯一字段(unique)



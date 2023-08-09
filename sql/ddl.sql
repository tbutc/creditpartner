drop table if exists member CASCADE;
create table member
(
    id varchar(255),
    name varchar(255),
    pwd varchar(255),
    pid long PRIMARY KEY AUTO_INCREMENT
);

create table subject
(
    sid int,
    name varchar(255),
    primary key(sid)
);

create table class
(
    cid int,
    name varchar(255),
    credit int,
    sid int,
    primary key(cid),
    foreign key(sid) references subject(sid)
);

create table class_list
(
    pid long PRIMARY KEY AUTO_INCREMENT,
    semester int,
    member_id varchar(255),
    class_id int,
    credit int,
    foreign key(class_id) references class(cid),
    foreign key(member_id) references member(id)
);


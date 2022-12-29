--20221211
create table pmember(
email varchar(100) not null primary key,
password varchar(100) not null,
nickname varchar(100) not null);

 alter table pmember add reg_at datetime default current_timestamp;
 
 --20221213
create table pboard(
bNo int primary key,
writer varchar(100),
reg_at datetime default current_timestamp,
content text,
image_file varchar(100),
heart int);

alter table pmember add constraint uq_pmember_nickname unique(nickname);
alter table pboard add constraint fk_pboard_writer foreign key(writer) references pmember(nickname);

--20221213(집)
alter table pboard change column bNo bNo int auto_increment;
alter table pboard modify column heart int default 0;
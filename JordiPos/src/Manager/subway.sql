select * from MMG;
select * from EMG;
select * from PMG;
select * from SMG;
select * from TPM;
select * from notice;

drop table MMG cascade constraint;
drop table EMG cascade constraint;
drop table PMG cascade constraint;
drop table SMG cascade constraint;
drop table TPM cascade constraint;
drop table notice cascade constraint;




create table MMG(
m_number number(10),
m_id VARCHAR2(20),
m_pw VARCHAR2(20),
m_loc number(10),
CONSTRAINT m_number_pk PRIMARY key(m_number));
alter table MMG add UNIQUE(m_id,m_pw);

create table emg(
e_number number(10),
e_name varchar2(20) not null,
e_commuting varchar2(5),
constraint emg_e_number_pk primary key(e_number));

create table notice
(n_number number(10),
n_title varchar2(50) not null,
n_date date default sysdate,
n_content varchar2(100),
constraint notice_n_number_pk primary key(n_number)
);

create table PMG
(p_code number(10),
p_name varchar2(30) not null,
p_price number(10) not null,
r_quantity number(5) not null,
d_sq number(5) not null,
e_date date not null,
constraint P_pcode_pk primary key(p_code)
);

create table SMG
(m_number number(10),
d_sale number(10) not null,
m_sale number(10) not null,
constraint m_nuber_pk primary key(m_number),
constraint SMG_mn_fk foreign key(m_number) references MMG(m_number)
);

create table TPM
(m_number number(10),
 m_loc number(10),
 tp_n1 varchar2(50),
 tp_q1 number(10),
 tp_n2 varchar2(50),
 tp_q2 number(10),
 tp_n3 varchar2(50),
 tp_q3 number(10),
constraint m_num_pk primary key(m_number),
constraint m_num_fk foreign key(m_number) references MMG(m_number)
);
/*�������ݿ⣺RobotCompetitionLogin*/
create database RobotCompetitionLogin
on primary
(
name='RobotCompetitionLogin_data',
filename='D:\\RobotCompetitionLogin_data.mdf',
size=10mb,
maxsize=100mb,
filegrowth=15%
)
log on(
name='RobotCompetitionLogin_log',
filename='D:\\RobotCompetitionLogin_log.ldf'
)
go

use RobotCompetitionLogin
go

if exists(select * from sys.tables where name='subjectInfo')
drop table subjectInfo
go

/*��Ŀ��Ϣ��*/
create table subjectInfo
(
subjectId int identity(1,1) primary key,
--��Ŀ���
subjectName char(30) not null,
--��Ŀ����
peopleNumber int not null,
--�������������
subjectInformation text,
--��Ŀ���
remark text
--��ע��Ϣ
)
go


/*����Ա��Ϣ��*/
create table administratorInfo
(
administratorName char(30) primary key,
--����Ա�û���
administratorPassword char(30) not null,
--����Ա����
remark text
--��ע��Ϣ
)
go


/*������Ա��Ϣ��*/
create table studentInfo
(
studentId char(30) primary key,
--ѧ��
studentName char(30) not null,
--����
studentInstitute char(30) not null,
--����ѧԺ
studentPhone char(30) ,
--��ϵ�绰
studentEmail char(30),
--����
remark text
--��ע��Ϣ
)
go


/*���������Ϣ��*/
create table teamInfo(
teamId int identity(1,1) primary key ,
--��ӱ��
teamName char(30) not null,
--�������
teamPassword char(30) not null,
--����
subjectId int foreign key references subjectInfo(subjectId),
--��Ŀ���
teacherName char(30),
--ָ����ʦ����
student1 char(30) foreign key references studentInfo(studentId),
--��Աһѧ��
student2 char(30) foreign key references studentInfo(studentId),
--��Ա��ѧ��
student3 char(30) foreign key references studentInfo(studentId),
--��Ա��ѧ��
student4 char(30) foreign key references studentInfo(studentId),
--��Ա��ѧ��
student5 char(30) foreign key references studentInfo(studentId),
--��Ա��ѧ��
remark text
--��ע��Ϣ
)
go


go
/*������Ϣ��*/
create table newsInfo(
newsId int identity(1,1) primary key,
--���ű��
newsName char(30) not null,
--��������
newsContent text not null,
--��������
newsDate datetime ,
--����ʱ��
remark text
--��ע��Ϣ

)
go

if exists(select * from sys.tables where name='materialInfo')
drop table materialInfo

/*������Ϣ��*/
create table materialInfo(
materialId int identity(1,1) primary key,
--���ϱ��
materialName text not null,
--��������
materialLimit  int not null default(2),
--����Ȩ��
materialURL text,
--�������ص�ַ
remark text
--��ע��Ϣ
)
go



/*�ɼ��ȼ���*/
create table rewardInfo(
rewardId int  primary key,
--�ɼ��ȼ����
rewardName char(30) not null,
--�ɼ�����
remark text
--��ע��Ϣ
)
go


/*�ɼ���Ϣ��*/
create table resultInfo(
resultId int identity(1,1) primary key,
--�ɼ����
teamId int foreign key references teamInfo(teamId),
--��ӱ��
rewardName nchar(30) ,
--�ɼ��ȼ�
remark text
--��ע��Ϣ
)
go

select studentId ,studentName,studentInstitute,studentPhone,studentEmail from studentInfo where studentId =(select student1 from teamInfo where teamId=3) or studentId=(select student2 from teamInfo where teamId=3) or studentId=(select student3 from teamInfo where teamId=3) or studentId=(select student4 from teamInfo where teamId=3) or studentId=(select student5 from teamInfo where teamId=3) order by studentId desc

select * from teamInfo
update teamInfo set teamName='���',teamPassword='10101010',teacherName='���' where teamId=3

select teamPassword from teamInfo where teamName='�칬һ��'

insert into teamInfo(teamName,teamPassword,subjectId,teacherName,student1,student2,student3,student4,student5,remark)
values('�����','1212121',23,'����','09020202','09020201','','','','remark')
select * from studentInfo
select * from teamInfo
delete from studentInfo where studentId=''
if exists(select * from sys.tables where name='subjectInfo')

select * from subjectInfo

insert into administratorInfo values('bjut','bjut123','')


insert into subjectInfo values('ss���л���������',1,'���ڷ�������˵Ĺ��������','')
select subjectId,subjectName,peopleNumber,subjectInformation,remark from subjectInfo
update subjectInfo set subjectName='���������',peopleNumber=3,subjectInformation='������������˵���Ϣ�����'
where subjectId=23

select teamId from teamInfo where teamName='�����' and teamPassword='1312121'
select * from teamInfo
delete from subjectInfo where subjectId=5


insert into newsInfo(newsName,newsContent,newsDate,remark) values('�ɷ���֪ͨ','2��13������9���ٽɷ�',convert(varchar(10),getdate(),120),'')
select * from newsInfo
delete from newsInfo where newsId=1
update newsInfo set newsName='�����ը',newsContent='���������ը�Ĺ����뷽ʽ',newsDate=convert(varchar(10),getdate(),120) where newsId=4

select newsId,newsName,newsContent,newsDate,remark from newsInfo
select * from studentInfo

update studentInfo set studentId='11100011' where studentId='08020209'

select materialId,materialName,materialLimit,materialURL from materialInfo

select subjectId from teamInfo where student1='09020202' or student2='09020202' or student3='09020202' or student4='09020202' or student5='09020202'
select COUNT(studentId) as 'havaStudentId' from studentInfo where studentId='09020202' and 
insert into studentInfo(studentId,studentName,studentInstitute,studentPhone,studentEmail,remark) values('09020201','yuefe','������Ϣ����ƹ���ѧԺ','148324422453','yy@121.com','')

delete from materialInfo where materialId=1

select peopleNumber from subjectInfo where subjectId=(select subjectId from teamInfo where teamId=4)
select subjectId from teamInfo where teamId=4

insert into materialInfo(materialName,materialLimit,materialURL) values('a������������������������������',3,'upload//���ھ�������.docx')

select teamName,teamPassword,teacherName,subjectName from teamInfo inner join subjectInfo on subjectInfo.subjectId=teamInfo.subjectId where teamId=3 

update studentInfo set studentName='�Դ��' ,studentInstitute='�����ѧԺ',studentPhone='323123231',studentEmail='lingdian@sina.com' where studentId='08020201'

update teamInfo set student1='r333r3' where student1='' and teamId=3
update teamInfo set student2='r333r3' where student2='' and teamId=3
update teamInfo set student3='r333r3' where student3='' and teamId=3
update teamInfo set student4='r333r3' where student4='' and teamId=3
update teamInfo set student5='r333r3' where student5=''and teamId=3


select * from studentInfo
select * from teamInfo

delete from studentInfo where studentId='09020201'
select student1 from teamInfo where student1='090202'
select teamId,teamName,teamPassword from teamInfo where subjectId=23

select teamName,teamPassword,subjectName,teacherName,student1,studentName,studentInstitute,StudentPhone,studentEmail,student2,student3,student4,student5 from teamInfo inner join subjectInfo on teamInfo.subjectId=subjectInfo.subjectId inner join studentInfo on student1=studentId where teamId=2
select studentId,studentName,studentInstitute,studentPhone from studentInfo


--����Ӳ�ѯ
select subjectName,teamName,studentId,studentName,studentInstitute,studentPhone from teamInfo
 inner join subjectInfo on subjectInfo.subjectId=teamInfo.subjectId 
 inner join studentInfo on student1=studentInfo.studentId
union 
select subjectName,teamName,studentId,studentName,studentInstitute,studentPhone from teamInfo
 inner join subjectInfo on subjectInfo.subjectId=teamInfo.subjectId 
 inner join studentInfo on student2=studentInfo.studentId
union 
select subjectName,teamName,studentId,studentName,studentInstitute,studentPhone from teamInfo
 inner join subjectInfo on subjectInfo.subjectId=teamInfo.subjectId 
 inner join studentInfo on student3=studentInfo.studentId
 union 
select subjectName,teamName,studentId,studentName,studentInstitute,studentPhone from teamInfo
 inner join subjectInfo on subjectInfo.subjectId=teamInfo.subjectId 
 inner join studentInfo on student4=studentInfo.studentId
 union 
select subjectName,teamName,studentId,studentName,studentInstitute,studentPhone from teamInfo
 inner join subjectInfo on subjectInfo.subjectId=teamInfo.subjectId 
 inner join studentInfo on student5=studentInfo.studentId
 order by subjectName 

 


--��ѧ����ѯ
select studentId,studentName,studentInstitute,studentPhone,(select COUNT(teamId) from teamInfo where student1=studentInfo.studentId or student2=studentInfo.studentId or student3=studentInfo.studentId or student4=studentInfo.studentId or student5=studentInfo.studentId) as 'count' from studentInfo order by studentInstitute desc
select subjectName,teamName from teamInfo inner join subjectInfo on teamInfo.subjectId=subjectInfo.subjectId where student1='r333r3' or student2='r333r3' or student3='r333r3' or student4='r333r3' or student5='r333r3'

select subjectName,teamName from teamInfo inner join subjectInfo on teamInfo.subjectId=subjectInfo.subjectId where student1="+si.getStudentId()+" or student2="+si.getStudentId()+" or student3="+si.getStudentId()+" or student4="+si.getStudentId()+" or student5=''

select rewardName from resultInfo where resultInfo.teamId=2
update resultInfo set rewardName='���Ƚ�' where resultInfo.teamId=2
select * from studentInfo
select * from teamInfo 
select * from resultInfo
delete resultInfo from resultInfo 

select subjectName,teamInfo.teamId,teamName,student1,student1Name=(select studentName from studentInfo where studentId=student1),
student2, student2Name=(select studentName from studentInfo where studentId=student2),
student3, student3Name=(select studentName from studentInfo where studentId=student3),
student4, student4Name=(select studentName from studentInfo where studentId=student4),
student5, student5Name=(select studentName from studentInfo where studentId=student5),
rewardName from teamInfo
left join subjectInfo on subjectInfo.subjectId=teamInfo.subjectId 
 left join resultInfo on resultInfo.teamId=teamInfo.teamId
 order by subjectName,rewardName
 
select * from subjectInfo
insert into resultInfo values(2,'���Ƚ�','')
select * from materialInfo

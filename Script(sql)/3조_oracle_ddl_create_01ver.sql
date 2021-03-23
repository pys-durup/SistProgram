-- 3조 oracle DDL(create)

-----테이블 강제로 날리기
---- 5단계 Drop 
--drop table tblJobInfo cascade constraints purge;
--drop table tblRecommend cascade constraints purge;
--drop table tblStudentLanguage cascade constraints purge;
--drop table tblTeacherEvaluation cascade constraints purge;
--
---- 4단계 Drop
--drop table tblQualification cascade constraints purge;
--drop table tblInterviewResult cascade constraints purge;
--drop table tblInterviewsEvaluation cascade constraints purge;
--drop table tblJobConsultation cascade constraints purge;
--drop table tblCourseConsultation cascade constraints purge;
--drop table tblTalentedStudent cascade constraints purge;
--drop table tblAttendance cascade constraints purge;
--drop table tblScore cascade constraints purge;
--drop table tblCourceComplet cascade constraints purge;
--
---- 3단계 Drop
--drop table tblRegiCource cascade constraints purge;
--drop table tblInterviewApply cascade constraints purge;
--drop table tblSetScore cascade constraints purge;
--drop table tblCourceSubjectAssign cascade constraints purge;
--
---- 2단계 Drop
--drop table tblMakeSubject cascade constraints purge;
--drop table tblMakeCource cascade constraints purge;
--drop table tblTeacherSubjectList cascade constraints purge;
--drop table tblCompanyLanguage cascade constraints purge;
--
---- 1단계 Drop
--drop table tblLanguage cascade constraints purge;
--drop table tblLinkCompany cascade constraints purge;
--drop table tblStudent cascade constraints purge;
--drop table tblInterview cascade constraints purge;
--drop table tblRoom cascade constraints purge;
--drop table tblTeacher cascade constraints purge;
--drop table tblBook cascade constraints purge;
--drop table tblSubject cascade constraints purge;
--drop table tblCourse cascade constraints purge;
--drop table tblReserveStudent cascade constraints purge;
--drop table tblMaster cascade constraints purge;
--drop table tblRegistate cascade constraints purge;
--drop table tblConsultationReason cascade constraints purge;


-- 5단계 Drop 
drop table tblJobInfo;
drop table tblRecommend;
drop table tblStudentLanguage;
drop table tblTeacherEvaluation;

-- 4단계 Drop
drop table tblQualification;
drop table tblInterviewResult;
drop table tblInterviewsEvaluation;
drop table tblJobConsultation;
drop table tblCourseConsultation;
drop table tblTalentedStudent;
drop table tblAttendance;
drop table tblScore;
drop table tblCourceComplet;

-- 3단계 Drop
drop table tblRegiCource;
drop table tblInterviewApply;
drop table tblSetScore;
drop table tblCourceSubjectAssign;

-- 2단계 Drop
drop table tblMakeSubject;
drop table tblMakeCource;
drop table tblTeacherSubjectList;
drop table tblCompanyLanguage;

-- 1단계 Drop
drop table tblLanguage;
drop table tblLinkCompany;
drop table tblStudent;
drop table tblInterview;
drop table tblRoom;
drop table tblTeacher;
drop table tblBook;
drop table tblSubject;
drop table tblCourse;
drop table tblReserveStudent;
drop table tblMaster;
drop table tblRegistate;
drop table tblConsultationReason;

-- 시퀀스 날리기

--1단계 드랍
drop SEQUENCE seqLanguage;
drop SEQUENCE seqLinkCompany;
drop SEQUENCE seqStudent;
drop sequence seqInterview;
drop sequence seqRoom;
drop sequence seqTeacher;
drop sequence seqBook;
drop sequence seqSubject; 
drop sequence seqCourse;
drop sequence seqReserveStudent;
drop SEQUENCE seqMaster;
drop SEQUENCE seqRegistate;
drop sequence seqConsultationReason;

--2단계 드랍
drop sequence seqMakeSubject;
drop sequence seqMakeCource;
drop sequence seqSubjectList;
drop SEQUENCE seqCompanyLanguage;

--3단계 드랍
drop SEQUENCE seqRegiCource;
drop sequence seqInterviewApply; 
drop sequence seqSetScore;
drop sequence seqCourceSubjectAssign; 

--4단계 드랍
drop sequence seqQualification;
drop sequence  seqInterviewResult;
drop sequence seqInterviewsEvaluation;
drop sequence seqJobConsultation;
drop sequence seqCourseConsultation;
drop SEQUENCE seqTalentedStudent;
drop SEQUENCE seqAttendance;
drop sequence seqScore;
drop sequence seqCourceComplet;

--5단계 드랍
drop sequence seqJobInfo;
drop sequence seqRecommend;
drop SEQUENCE seqStudentLanguage;
drop sequence seqTeacherEvaluation;

/*

1단계 생성 (FK가 없는 테이블들) (숲색)
tblLanguage (언어) 
tblLinkCompany (연계기업)  
tblStudent (교육생)  
tblInterview (모의면접) 
tblRoom (강의실) 
tblTeacher (교사) 
tblBook (교재) 
tblSubject (과목) 
tblCourse (과정) 
tblReserveStudent (예비교육생) 
tblMaster(관리자) 
tblRegistate(수강상태) 
tblConsultationReason(상담사유) 


*/



-- 1단계 생성 -----------------------------------------------------------------------------------------------------------

-- 언어 테이블
CREATE TABLE tblLanguage (
	seq NUMBER PRIMARY KEY,                    /* 번호 */
	language VARCHAR2(40) NOT NULL      /* 언어 */
);

CREATE SEQUENCE seqLanguage;


-- 연계기업 테이블
CREATE TABLE tblLinkCompany (
	seq NUMBER PRIMARY KEY,                             /* 번호 */
	name VARCHAR2(40) NOT NULL,                 /* 기업명 */
	address VARCHAR2(100) NOT NULL,              /* 기업주소 */
	department VARCHAR2(20) NOT NULL,           /* 구인희망부서 */
	salary NUMBER NOT NULL                          /* 급여 */
    
);
CREATE SEQUENCE seqLinkCompany;



-- 교육생 테이블
CREATE TABLE tblStudent (
	seq NUMBER PRIMARY KEY, /* 번호 */
	name VARCHAR2(20) NOT NULL, /* 이름 */
	jumin VARCHAR2(20) NOT NULL, /* 주민번호 */
	tel VARCHAR2(20) NOT NULL, /* 전화번호 */
	regdate DATE DEFAULT SYSDATE /* 등록일 */
);

CREATE SEQUENCE seqStudent;



--모의면접
CREATE TABLE tblInterview (
	seq NUMBER PRIMARY KEY,             /* 면접번호 */
	question VARCHAR2(2000) NOT NULL,   /* 면접질문 */
	standard VARCHAR2(2000) NOT NULL    /* 점수기준 */
);

--모의면접 테이블의 시퀀스 생성
create sequence seqInterview;



-- 강의실 테이블 (fk 없음)
CREATE TABLE tblRoom (
	seq NUMBER NOT NULL primary key, /* 번호 */
	roomNum VARCHAR2(20) NOT NULL, /* 호수 */
	capacity NUMBER NOT NULL /* 수용인원 */
);

create sequence seqRoom;


/* 교사 */
CREATE TABLE tblTeacher (
	seq NUMBER NOT NULL PRIMARY KEY, /* 번호 */
	name VARCHAR2(20) NOT NULL, /* 이름 */
	jumin VARCHAR2(20) NOT NULL, /* 주민번호 */
	tel VARCHAR2(20) NOT NULL, /* 전화번호 */
	regdate DATE DEFAULT SYSDATE /* 등록일 */
);
create sequence seqTeacher; -- 교사 시퀀스


-- 교재
create table tblBook (
	seq number primary Key, /* 번호 */
	name varchar2(60) not null, /* 교재명 */
	writer varchar2(20) not null, /* 저자 */
	publisher varchar2(40) not null, /* 출판사 */
	price number not null, /* 가격 */
	count number not null  /* 수량 */
    
);
create sequence seqBook; /* 교재 seq*/


-- 과목 
create table tblSubject ( 
	seq number primary Key, /* 번호 */
	name varchar2(100) not null, /* 과목명 */
	duration number not null /* 소요일수 */

);
create sequence seqSubject; /* 과목 seq */



-- 과정 테이블(fk 없음)
CREATE TABLE tblCourse (
	seq NUMBER NOT NULL primary key, /* 번호 */
	name VARCHAR2(100) NOT NULL, /* 과정명 */
	purpose VARCHAR2(1000) NOT NULL /* 과정목표 */
);

create sequence seqCourse;




/* 예비교육생 */
CREATE TABLE tblReserveStudent (
	seq NUMBER PRIMARY KEY, /* 번호 */
	name VARCHAR2(20) NOT NULL, /* 이름 */
	jumin VARCHAR2(20), /* 주민등록번호 */
	tel VARCHAR2(20), /* 전화번호 */
	address VARCHAR2(100) NOT NULL, /* 주소 */
	field VARCHAR2(100) NOT NULL, /* 취업희망분야 */
	knowledge VARCHAR2(1000) NOT NULL /* 사전지식 */
);
create sequence seqReserveStudent; -- 예비교육생 시퀀스



-- 관리자 테이블
CREATE TABLE tblMaster (
	seq NUMBER NOT NULL, /* 번호 */
	id VARCHAR2(20) NOT NULL, /* 아이디 */
	jumin VARCHAR2(20) NOT NULL /* 주민번호 */
);
CREATE SEQUENCE seqMaster;



-- 수강상태 테이블
CREATE TABLE tblRegistate (
	seq NUMBER PRIMARY KEY, /* 번호 */
	regiState VARCHAR2(30) NOT NULL /* 수강상태 */
);
CREATE SEQUENCE seqRegistate;



--상담사유
CREATE TABLE tblConsultationReason (
	seq NUMBER PRIMARY KEY,         /* 번호 */
	reason VARCHAR2(40) NOT NULL    /* 상담사유 */
);

--상담사유 테이블의 시퀀스 생성
create sequence seqConsultationReason;


select * from tblConsultationReason;


/*
2단계 생성 (커피색)
tblMakeSubject (과목개설)
tblMakeCource (과정개설) 
tblTeacherSubjectList(교사과목목록)  
tblCompanyLanguage(기업필요언어목록)  
*/

-- 2단계 생성 -----------------------------------------------------------------------------------------------------------

-- 과목개설
create table tblMakeSubject (
	seq number primary Key, /* 번호 */
	subjectNum number not null, /* 과목번호 ref */
	startDate date not null, /* 과목시작일 */
	endDate date not null, /* 과목종료일 */
	bookNum number not null,  /* 교재번호 ref */
    bookDistriState varchar2(20) not null, /* 분배상태 */
    
    CONSTRAINT tblMakeSubject_fk1 FOREIGN KEY(subjectNum) REFERENCES tblSubject(seq) ON DELETE CASCADE,
    CONSTRAINT tblMakeSubject_fk2 FOREIGN KEY(bookNum) REFERENCES tblBook(seq) ON DELETE CASCADE
);    

create sequence seqMakeSubject; /* 과목개설 seq */




-- 과정개설 테이블 (fk: 강의실번호, 교사번호, 과정번호)
CREATE TABLE tblMakeCource (
	seq NUMBER NOT NULL primary key, /* 번호 */
	startDate DATE NOT NULL, /* 과정시작일 */
	endDate DATE NOT NULL, /* 과정종료일 */
	roomNum NUMBER NOT NULL, /* 강의실번호 fk*/
	teacherNum NUMBER NOT NULL, /* 교사번호 fk*/
	courceNum NUMBER NOT NULL, /* 과정번호 fk*/
    
     CONSTRAINT tblMakeCource_fk1 FOREIGN KEY(roomNum)
     REFERENCES tblRoom(seq) ON DELETE CASCADE, -- 강의실번호 참조
    
     CONSTRAINT tblMakeCource_fk2 FOREIGN KEY(teacherNum)
     REFERENCES tblTeacher(seq) ON DELETE CASCADE, -- 교사번호 참조
    
     CONSTRAINT tblMakeCource_fk3 FOREIGN KEY(courceNum)
     REFERENCES tblCourse(seq) ON DELETE CASCADE -- 과정번호 참조
);

create sequence seqMakeCource;



-- 과목목록 테이블
CREATE TABLE tblTeacherSubjectList (
	seq NUMBER NOT NULL primary key, /* 번호 */
	teacherNum NUMBER NOT NULL, /* 교사번호 fk*/
	subjectNum NUMBER NOT NULL, /* 과목번호 fk*/
    
    CONSTRAINT tblTeacherSubjectList_fk1 FOREIGN KEY(teacherNum)
    REFERENCES tblTeacher(seq) ON DELETE CASCADE, -- 교사번호 참조
    
    CONSTRAINT tblTeacherSubjectList_fk2 FOREIGN KEY(subjectNum)
    REFERENCES tblSubject(seq) ON DELETE CASCADE -- 과목번호 참조
    
);

create sequence seqSubjectList;



-- 기업필요언어
CREATE TABLE tblCompanyLanguage (
	seq NUMBER PRIMARY KEY,           /* 번호 */
	languageNum NUMBER NOT NULL,                /* 언어번호 */
	companyNum NUMBER NOT NULL,                 /* 기업번호 */
    
    CONSTRAINT tblCompanyLanguage_fk1 FOREIGN KEY(languageNum) REFERENCES tblLanguage(seq)  ON DELETE CASCADE,
    CONSTRAINT tblCompanyLanguage_fk2 FOREIGN KEY(companyNum) REFERENCES tblLinkCompany(seq)  ON DELETE CASCADE
);

CREATE SEQUENCE seqCompanyLanguage;







/*
3단계 생성 (오팔색)
tblRegiCource(수강)
tblInterviewApply(면접신청)
tblSetScore(배점) 
tblCourceSubjectAssign(과정과목배정)
*/


--3단계 생성 -----------------------------------------------------------------------------------------------------------


-- 수강 테이블
CREATE TABLE tblRegiCource (
	seq NUMBER PRIMARY KEY, /* 번호 */
	studentNum NUMBER NOT NULL, /* 교육생 번호 */
	createdCourceNum NUMBER NOT NULL, /* 개설과정 번호 */
	regiStateNum NUMBER NOT NULL, /* 수강상태번호 */

 CONSTRAINT tblRegiCource_fk1 FOREIGN KEY(studentNum) REFERENCES tblStudent(seq)  ON DELETE CASCADE,
 CONSTRAINT tblRegiCource_fk2 FOREIGN KEY(createdCourceNum) REFERENCES tblMakeCource(seq)  ON DELETE CASCADE,
 CONSTRAINT tblRegiCource_fk3 FOREIGN KEY(regiStateNum) REFERENCES tblRegistate(seq)  ON DELETE CASCADE

);
CREATE SEQUENCE seqRegiCource;



/* 면접신청 */
CREATE TABLE tblInterviewApply (
	seq NUMBER NOT NULL PRIMARY KEY, /* 번호 */
    createdCourceNum NUMBER NOT NULL,
    reserveStudentNum NUMBER NOT NULL,
    InterviewDate DATE,
     
	CONSTRAINT tblInterviewApply_fk1 FOREIGN KEY (createdCourceNum) REFERENCES tblMakeCource (seq)  ON DELETE CASCADE, /* 개설과정번호 */
	CONSTRAINT tblInterviewApply_fk2 FOREIGN KEY (reserveStudentNum) REFERENCES tblReserveStudent (seq)  ON DELETE CASCADE /* 예비교육생번호 */
);
create sequence seqInterviewApply; -- 면접신청 시퀀스


-- 배점
create table tblSetScore (
	seq number primary key, /* 번호 */
	attendance number not null, /* 출결배점 */
	write number not null, /* 필기배점 */
	practice number not null, /* 실기배점 */
	makeSubjectNum number not null,  /* 개설과목번호 ref */
    
    CONSTRAINT tblSetScore_fk1 FOREIGN KEY (makeSubjectNum) REFERENCES tblMakeSubject (seq)  ON DELETE CASCADE
);
create sequence seqSetScore /* 배점 seq */;



-- 과정과목 테이블 (fk)
CREATE TABLE tblCourceSubjectAssign (
	seq NUMBER NOT NULL primary key, /* 새 컬럼 */
	createdCourceNum NUMBER NOT NULL, /* 과정번호 fk*/
	subjectNum NUMBER NOT NULL, /* 개설과목번호 fk*/
    
    CONSTRAINT tblCourceSubjectAssign_fk1 FOREIGN KEY(createdCourceNum)
    REFERENCES tblMakeCource(seq) ON DELETE CASCADE, -- 개설과정번호 참조
        
    CONSTRAINT tblCourceSubjectAssign_fk2 FOREIGN KEY(subjectNum)
    REFERENCES tblMakeSubject(seq) ON DELETE CASCADE -- 과목번호 참조
);

create sequence seqCourceSubjectAssign;






/*
4단계 생성 (코발트 블루색)
tblQualification(구직활동정보)
tblInterviewResult(면접결과) 
tblInterviewsEvaluation(모의면접평가) 
tblJobConsultation(취업상담) 
tblCourseConsultation(수업상담) 
tblTalentedStudent(인재) 
tblAttendance(출결) 
tblScore(성적) 
tblCourceComplet(수료) 
*/

--4단계 생성 -----------------------------------------------------------------------------------------------------------

/* 구직활동정보 */

CREATE TABLE tblQualification (
	seq NUMBER PRIMARY KEY, /* 구직활동번호 */
	license VARCHAR2(80) NOT NULL, /* 자격증 */
	resume VARCHAR2(80) NOT NULL, /* 이력서 */
	job VARCHAR2(50) NOT NULL, /* 희망 직무 */
	github VARCHAR2(50) NOT NULL, /* 깃허브주소 */
	salary NUMBER NOT NULL, /* 희망 연봉 */
	regiNum NUMBER NOT NULL, /* 수강번호 fk */ 
    
    CONSTRAINT tblQualification_fk1 FOREIGN KEY(regiNum)
    REFERENCES tblRegiCource(seq) ON DELETE CASCADE -- 수강 번호 참조
);
create sequence seqQualification;


/* 면접결과 */
CREATE TABLE tblInterviewResult (
	
    seq NUMBER PRIMARY KEY, /* 번호 */
    interviewNum NUMBER NOT NULL,/* 면접번호 */
	result VARCHAR2(20) NOT NULL, /* 결과 */
    
    CONSTRAINT tblInterviewResult_fk1 FOREIGN KEY (interviewNum) REFERENCES tblInterviewApply (seq)  ON DELETE CASCADE
);
create sequence  seqInterviewResult; -- 면접결과 시퀀스



--모의면접평가 테이블
CREATE TABLE tblInterviewsEvaluation (
	seq NUMBER PRIMARY KEY,                 /* 평가번호 */
	interviewNum NUMBER NOT NULL,           /* 면접번호 */
	score NUMBER NOT NULL,                  /* 면접점수 */
	evaluation VARCHAR2(2000) NOT NULL,     /* 면접평가 */
	teacherNum NUMBER NOT NULL,             /* 교사번호 */
	regiNum NUMBER NOT NULL,                /* 수강번호 */
    
    CONSTRAINT tblInterviewsEvaluation_fk1 FOREIGN KEY(interviewNum) REFERENCES tblInterview(seq)  ON DELETE CASCADE,
    CONSTRAINT tblInterviewsEvaluation_fk2 FOREIGN KEY(teacherNum) REFERENCES tblTeacher(seq)  ON DELETE CASCADE,
    CONSTRAINT tblInterviewsEvaluation_fk3 FOREIGN KEY(regiNum) REFERENCES tblRegiCource(seq)  ON DELETE CASCADE
);

--모의면접평가 테이블의 시퀀스 생성
create sequence seqInterviewsEvaluation;




--취업상담 테이블
CREATE TABLE tblJobConsultation (
	seq NUMBER PRIMARY KEY,             /* 상담번호 */
	content VARCHAR2(2000) NOT NULL,    /* 상담내용 */
	regiNum NUMBER NOT NULL,             /* 수강번호 */
    consDate DATE NOT NULL,              /* 상담날짜 */
    

    CONSTRAINT tblJobConsultation_fk2 FOREIGN KEY(regiNum) REFERENCES tblRegiCource(seq) ON DELETE CASCADE 
);

--취업상담 테이블의 시퀀스 생성
create sequence seqJobConsultation;

select * from tblJobConsultation;




--수업상담
CREATE TABLE tblCourseConsultation (
	seq NUMBER PRIMARY KEY,             /* 상담번호 */
	consultDate DATE NOT NULL,                 /* 상담날짜 */
	content VARCHAR2(2000) NOT NULL,    /* 상담내용 */

	makeSubjectNum NUMBER NOT NULL,     /* 개설과목번호 */
	reasonNum NUMBER NOT NULL,          /* 상담사유번호 */
	regiNum NUMBER NOT NULL,             /* 수강번호 */
    
    CONSTRAINT tblCourseConsultation_fk2 FOREIGN KEY(makeSubjectNum) REFERENCES tblMakeSubject(seq)  ON DELETE CASCADE,
    CONSTRAINT tblCourseConsultation_fk3 FOREIGN KEY(reasonNum) REFERENCES tblConsultationReason(seq)  ON DELETE CASCADE,
    CONSTRAINT tblCourseConsultation_fk4 FOREIGN KEY(regiNum) REFERENCES tblRegiCource(seq)  ON DELETE CASCADE
);

--수업상담 테이블의 시퀀스 생성
create sequence seqCourseConsultation;


-- 인재 테이블
CREATE TABLE tblTalentedStudent (
	seq NUMBER NOT NULL PRIMARY KEY,           /* 번호 */
	portfolio VARCHAR2(50) NOT NULL,               /* 포트폴리오 */
	reason VARCHAR2(1000) NOT NULL,             /* 추천이유 */
	regiNum NUMBER NOT NULL,                    /* 수강번호 */
    
    CONSTRAINT tblTalentedStudent_fk FOREIGN KEY(regiNum) REFERENCES tblRegiCource(seq)  ON DELETE CASCADE
    
);

CREATE SEQUENCE seqTalentedStudent;





-- 출결 테이블
CREATE TABLE tblAttendance (
	seq NUMBER PRIMARY KEY, /* 번호 */
	time DATE NOT NULL, /* 시각 */
	regiNum NUMBER NOT NULL, /* 수강번호 */

 CONSTRAINT tblAttendance_fk FOREIGN KEY(regiNum) REFERENCES tblRegiCource(seq)  ON DELETE CASCADE

);
CREATE SEQUENCE seqAttendance;




--성적
create table tblScore (
	seq number primary key, /* 번호 */
	attendance number not null, /* 출결점수 */
	practice number not null, /* 실기점수 */
	writer number not null, /* 필기점수 */
	makeSubjectNum number not null , /* 개설과목번호 ref */
	regiNum number not null , /* 수강번호 ref */
    
    CONSTRAINT tblScore_fk1 FOREIGN KEY(makeSubjectNum) REFERENCES tblMakeSubject(seq)  ON DELETE CASCADE,
    CONSTRAINT tblScore_fk2 FOREIGN KEY(regiNum) REFERENCES tblRegiCource(seq)  ON DELETE CASCADE
);
select * from tblScore;

create sequence seqScore; /* 성적 */ 


-- 수료 테이블
CREATE TABLE tblCourceComplet (
	seq NUMBER NOT NULL primary key, /* 번호 */
	regiNum NUMBER NOT NULL, /* 수강번호 fk*/
    completDate date NOT NULL, /*수료날짜 */
    
    CONSTRAINT tblCourceComplet_fk2 FOREIGN KEY(regiNum)
    REFERENCES tblRegiCource(seq) ON DELETE CASCADE -- 수강번호 참조
    
);

create sequence seqCourceComplet;



/*
5단계 생성 (빨간색)
tblJobInfo(취업내역)
tblRecommend (기업인재추천)
tblStudentLanguage(인재사용언어목록)
tblTeacherEvaluation(교사평가)
*/

--5단계 생성 -----------------------------------------------------------------------------------------------------------

/* 취업내역 */
CREATE TABLE tblJobInfo (
	seq NUMBER PRIMARY KEY, /* 번호 */
	startDate DATE NOT NULL, /* 취업일 */
	insurance VARCHAR2(40) NOT NULL, /* 4대보험 가입여부 */
	form VARCHAR2(50) NOT NULL, /* 고용형태 */
	career VARCHAR(50) NOT NULL, /* 직무 */
	income NUMBER NOT NULL, /* 연봉 */
	completNum NUMBER NOT NULL, /* 수료번호 fk*/
    
    CONSTRAINT tblJobInfo_fk1 FOREIGN KEY(completNum)
    REFERENCES tblCourceComplet(seq) ON DELETE CASCADE -- 수료 번호 참조
);
create sequence seqJobInfo;


-- 기업인재 추천 테이블
CREATE TABLE tblRecommend (
	seq NUMBER NOT NULL PRIMARY KEY,        /* 번호 */
	studentNum NUMBER NOT NULL,                 /* 인재번호 */
	companyNum NUMBER NOT NULL,              /* 기업번호 */
	recoDate DATE DEFAULT SYSDATE,                      /* 추천일시 */
    
    CONSTRAINT tblRecommend_fk1 FOREIGN KEY(studentNum) REFERENCES tblTalentedStudent(seq)  ON DELETE CASCADE,
    CONSTRAINT tblRecommend_fk2 FOREIGN KEY(companyNum) REFERENCES tblLinkCompany(seq)  ON DELETE CASCADE
);
CREATE SEQUENCE seqRecommend;




-- 인재사용언어
CREATE TABLE tblStudentLanguage (
	seq NUMBER PRIMARY KEY, /* 번호 */
	studentNum NUMBER NOT NULL, /* 인재번호 */
	languageNum NUMBER NOT NULL, /* 언어번호 */
    
    CONSTRAINT tblStudentLanguage_fk1 FOREIGN KEY(studentNum) REFERENCES tblTalentedStudent(seq)  ON DELETE CASCADE,
    CONSTRAINT tblStudentLanguage_fk2 FOREIGN KEY(languageNum) REFERENCES tblLanguage(seq)  ON DELETE CASCADE
);
CREATE SEQUENCE seqStudentLanguage;




/* 교사평가 */
CREATE TABLE tblTeacherEvaluation (-- 교사평가
	seq NUMBER NOT NULL PRIMARY KEY, /* 번호 */
	materials NUMBER NOT NULL, /* 학습자료 */
	communication NUMBER NOT NULL, /* 소통 */
	jobPreparing NUMBER NOT NULL, /* 취업준비 */
	divisionTime NUMBER NOT NULL, /* 시간분배 */
	totalPoint NUMBER NOT NULL, /* 총평점 */
   
    completNum NUMBER NOT NULL, /* 수료번호 */   
    	
	CONSTRAINT tblTeacherEvaluation_fk2 FOREIGN KEY (completNum) REFERENCES tblCourceComplet (seq)  ON DELETE CASCADE                                                                                   
);
create sequence seqTeacherEvaluation; -- 교사평가 시퀀스









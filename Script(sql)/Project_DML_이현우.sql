

/*

1.강의스케줄 조회 : 교사번호 기준 강의스케줄 조회기능
2.교사 평가 조회: 교육생들이 평가한 교사평가내역 조회
3.취업 상담 관리: 수료자 기준 취업상담리스트 추가, 삭제, 수정 기능
4.배점입출력 : 과목별 배점 추가, 수정, 삭제 기능
5.성적입출력 : 과목별 교육생 성적조회 및 입력기능


*/


--1 전체 강의스케줄 조회
CREATE OR REPLACE PROCEDURE proc_teacherScheduleAll
(

   pcursor OUT sys_refcursor
)
IS
BEGIN 
	OPEN pcursor FOR
        
    select 
        b.createdCourceNum as CourseNum, 
        g. name as teacherName,
        f. name as courseName,
        b.subjectnum as subjectNum,
        e.name as Subject, 
        d.name as book,
        a.roomNum as room, 
        to_char(a.startDate,'yyyy-mm-dd') as startDate, 
        to_char(a.endDate,'yyyy-mm-dd') as endDate,
        h.등록인원수 as totalStudent,
        case
            when a.enddate > to_char(sysdate,'yy-mm-dd') then '과정진행중'
            when a.enddate < to_char(sysdate,'yy-mm-dd') then '과정종료'
            when a.startdate > to_char(sysdate,'yy-mm-dd') then '강의예정'
        end as courseStatus
    from 
   
        tblMakeCource a 
        inner join tblCourceSubjectAssign b
        on a.seq = b.createdCourcenum
        inner join tblMakeSubject c
        on b.subjectnum = c.seq
        inner join tblbook d
        on c.booknum = d.seq
        inner join tblsubject e
        on c.subjectnum = e.seq
        inner join tblcourse f
        on a.courceNum = f.seq
        inner join tblteacher g
        on a.teachernum = g.seq
        inner join (select 
                 j.createdCourceNum,
                count(*) as 등록인원수
                from tblStudent y
                inner join tblRegiCource j
                on y.seq = j.studentNum
                where j.seq between 1 and 90 
                group by j.createdCourceNum) h
                on a.seq = h.createdCourceNum                                                   
       
    order by 
        h.createdCourceNum;
    	
END proc_teacherScheduleAll;

--1-2 특정 교사 강의스케줄 조회
--교사번호 기준 강의스케줄 조회기능
CREATE OR REPLACE PROCEDURE proc_teacherSchedule
(
   pseq IN NUMBER, --교사번호
   pcursor OUT sys_refcursor
)
IS
BEGIN 
	OPEN pcursor FOR
        
    select 
        b.createdCourceNum as CourseNum, 
        g. name as teacherName,
        f. name as courseName,
        b.subjectnum as subjectNum,
        e.name as Subject, 
        d.name as book,
        a.roomNum as room, 
        to_char(a.startDate,'yyyy-mm-dd') as startDate, 
        to_char(a.endDate,'yyyy-mm-dd') as endDate,
        h.등록인원수 as totalStudent,
        case
            when a.enddate > to_char(sysdate,'yy-mm-dd') then '과정진행중'
            when a.enddate < to_char(sysdate,'yy-mm-dd') then '과정종료'
            when a.startdate > to_char(sysdate,'yy-mm-dd') then '강의예정'
        end as courseStatus
    from 
   
        tblMakeCource a 
        inner join tblCourceSubjectAssign b
        on a.seq = b.createdCourcenum
        inner join tblMakeSubject c
        on b.subjectnum = c.seq
        inner join tblbook d
        on c.booknum = d.seq
        inner join tblsubject e
        on c.subjectnum = e.seq
        inner join tblcourse f
        on a.courceNum = f.seq
        inner join tblteacher g
        on a.teachernum = g.seq
        inner join (select 
                 j.createdCourceNum,
                count(*) as 등록인원수
                from tblStudent y
                inner join tblRegiCource j
                on y.seq = j.studentNum
                where j.seq between 1 and 90 
                group by j.createdCourceNum) h
                on a.seq = h.createdCourceNum                                                   
    where 
        g.seq = pseq
       
    order by 
        h.createdCourceNum;
    	
END proc_teacherSchedule;




--1-3 특정 개설 과정의 교육생 목록 출력
--개설과목 선택시, 해당과목에 등록된 교육생 출력
CREATE OR REPLACE PROCEDURE proc_CourseStudentList
(
   pseq IN NUMBER, --개설과목번호
   pcursor OUT sys_refcursor
)
IS
BEGIN 
	OPEN pcursor FOR
    
    SELECT 
        a.seq as seq,
        a.name as name,
        a.jumin as jumin,
        a.tel as tel,
        to_char(a.regdate,'yyyy-mm-dd') as regdate,
        c.registate as registate
        
    FROM 
    tblstudent a
    inner join tblRegiCource b
    on a.seq = b.studentnum
    inner join tblregistate c 
    on b.regiStateNum = c.seq
    inner join tblMakeCource d
    on b.createdCourceNum = d.seq
    inner join tblCourceSubjectAssign e
    on d.seq = e.createdCourceNum
    
    where 
        a.seq between 1 and 90 and e.subjectnum=pseq;
END proc_CourseStudentList;

--2. 교사평가조회
--과정 수료자들이 항목별로 평가한 교사평가점수 평균값 출력
 CREATE OR REPLACE PROCEDURE proc_TeacherEvaluationList
(
   pseq IN NUMBER, --교사번호
   pcursor OUT sys_refcursor
)

IS
BEGIN 
	OPEN pcursor FOR
    select 
    
    a.seq as seq,
    b.name as name,
    e.과정번호 as courseNum,
    to_char(a.startdate, 'yyyy-mm-dd') as startdate,
    to_char(a.enddate, 'yyyy-mm-dd') as enddate,
    e.평가인원수 as totalStudent,
    e.자료만족도 as materials,
    e.소통만족도 as communication,
    e.취업만족도 as jobpreparing,
    e.시간분배만족도 as divisiontime
    from  
        tblMakeCource a
        inner join (
                select                                                                               
                     j.createdCourceNum as 과정번호,                                                           
                    count(*)/7 as 평가인원수, 
                    round(avg(g.materials),1) as 자료만족도, 
                    round(avg(g.communication),1) as 소통만족도, 
                    round(avg(g.jobpreparing),1) as 취업만족도, 
                    round(avg(g.divisiontime),1) as 시간분배만족도 
                                                                                    
                from 
                    tblTeacherEvaluation g
                    inner join tblCourceComplet h
                    on g.completNum = h.seq
                    inner join tblMakeCource y
                    on y.seq = h.createdCourceNum
                    inner join tblCourceSubjectAssign j
                    on y.seq = j.createdCourceNum
                    inner join tblMakeSubject k
                    on j.subjectNum = k.seq
                    inner join tblSubject l
                    on k.subjectNum = l.seq
                                                                                                                                                                                                                                                             
                where
                    j.subjectNum between 1 and 21 
                    and y.enddate < to_char(sysdate, 'yyyy-mm-dd') 
                    and y.seq between 1 and 3 
                    and h.seq between 1 and 27
                                                                                                       
                group by 
                    j.createdCourceNum
                                                                                                            
                order by 
                    j.createdCourceNum) e
                    on a.courcenum=e.과정번호 
                        inner join tblcourse b
                            on a.courcenum = b.seq
        where 
            a.enddate < to_char(sysdate, 'yyyy-mm-dd') 
            and a.seq between 1 and 3            
            and a.teachernum = pseq;
 
                        
                            
END proc_TeacherEvaluationList;


--3 취업상담일지 조회 (--수료자)

CREATE OR REPLACE PROCEDURE proc_JobConsultationList
(
   pseq IN NUMBER,--교사번호
   pcursor OUT sys_refcursor
)
IS
BEGIN 
	OPEN pcursor FOR

    select 
        e.seq as consultationNum,
        a.seq as studentNum,
        a.name as StudentName,
        f.name as teacherName,
        b.createdCourceNum as courseNum,
        g.name as courseName,
        e.content as content,
        to_char(e.consdate,'yyyy-mm-dd') as consdate,
        c.registate as registate
       
    from 
        tblStudent a 
        inner join tblRegiCource b
        on a.seq = b.studentNum
        inner join tblRegistate c 
        on b.registateNum = c.seq
        inner join tblMakeCource d 
        on b.createdCourceNum = d.seq
        inner join tblJobConsultation e
        on b.seq = e.regiNum     
        inner join tblTeacher f                                        
        on f.seq = d.teacherNum
        inner join tblCourse g
        on d.CourceNum = g.seq
     
    where 
        c.registate ='수료'  and f.seq = pseq
        
    order by 
        e.seq;

END proc_JobConsultationList;


--3-1취업상담 edit를 위한 수정 전 목록보여주기
CREATE OR REPLACE PROCEDURE proc_editJobConsultationList
(
   pseq IN NUMBER,--교사번호
   cseq IN NUMBER, --상담번호
   pcursor OUT sys_refcursor
)
IS
BEGIN 
	OPEN pcursor FOR

    select 
        e.seq as consultationNum,
        a.seq as studentNum,
        a.name as StudentName,
        f.name as teacherName,
        b.createdCourceNum as courseNum,
        g.name as courseName,
        e.content as content,
        to_char(e.consdate,'yyyy-mm-dd') as consdate,
        c.registate as registate
       
    from 
        tblStudent a 
        inner join tblRegiCource b
        on a.seq = b.studentNum
        inner join tblRegistate c 
        on b.registateNum = c.seq
        inner join tblMakeCource d 
        on b.createdCourceNum = d.seq
        inner join tblJobConsultation e
        on b.seq = e.regiNum     
        inner join tblTeacher f                                        
        on f.seq = d.teacherNum
        inner join tblCourse g
        on d.CourceNum = g.seq
     
    where 
        c.registate ='수료'  and f.seq = pseq and e.seq = cseq
        
    order by 
        e.seq;

END proc_editJobConsultationList;


--3-2 상담일지작성이 가능한 학생조회(수료자)
CREATE OR REPLACE PROCEDURE proc_CompletionStudentList
(
   pseq IN NUMBER, --교사번호
   pcursor OUT sys_refcursor
)

IS
BEGIN 
	OPEN pcursor FOR
    
        select 
            b.studentNum as studentNum,
            a.name as studentName,
            f.name as teacherName,
            b.createdCourceNum as courseNum,
            g.name as courseName,
            to_char(d.startDate,'yyyy-mm-dd') as startDate,
            to_char(d.endDate,'yyyy-mm-dd') as endDate,
            c.registate as registate
      
        from tblStudent a 
            inner join tblRegiCource b
            on a.seq = b.studentNum
            inner join tblRegistate c 
            on b.registateNum = c.seq
            inner join tblMakeCource d 
            on b.createdCourceNum = d.seq
            inner join tblTeacher f                                        
            on f.seq = d.teacherNum
            inner join tblcourse g
            on d.CourceNum = g.seq
            
        where 
            c.registate ='수료' and  f.seq = pseq
                                    
        order by 
            a.seq;

END proc_CompletionStudentList;


--3-3 취업상담일지 작성
CREATE OR REPLACE PROCEDURE proc_jobConsultingWrite
(
	pcontent IN varchar2,		--상담내용
	preginum IN NUMBER	    --상담학생번호
)
IS
BEGIN
	Insert into tblJobConsultation (seq, content, regiNum, consDate) values (SEQJOBCONSULTATION.nextval, pcontent , preginum, to_char(sysdate, 'yyyy-mm-dd'));
	COMMIT;
    
EXCEPTION 
	WHEN OTHERS THEN
		ROLLBACK;

END proc_jobConsultingWrite;




--3-4 취업상담일지 수정 
CREATE OR REPLACE PROCEDURE porc_rvJobConsultation
(   
    preginum in tblJobConsultation.reginum%type, -- 상담학생
    pcontent in tblJobConsultation.content%type, --상담내용
    pseq in number --상담번호

) 
IS
BEGIN
	update 
        tblJobConsultation
    set 
        reginum = preginum, --상담학생변경
        content = pcontent --상담내용        
    where 
        seq = pseq;
END porc_rvJobConsultation;



--4.과목별 배점리스트 조회
CREATE OR REPLACE PROCEDURE proc_setScoreList
(
   pseq IN NUMBER, --교사번호
   pcursor OUT sys_refcursor
)
IS
BEGIN 
	OPEN pcursor FOR
    
    select 

        b.subjectnum as subjectNum,
        f.name as subjectName,
        e.name as bookName,
        d.attendance as attendance,
        d.write as write,
        d.practice as practice,
        to_char(c.startdate, 'yy-mm-dd') as subjectStartDate,
        to_char(c.enddate, 'yy-mm-dd') as subjectEndDate,
        g.name as courseName,
        to_char(a.startDate, 'yy-mm-dd') as courseStartDate,
        to_char(a.endDate,'yy-mm-dd') as courseEndDate,
        a.roomNum as roomName

    from 
        tblmakecource a
        inner join tblCourceSubjectAssign b
        on a.seq = b.createdCourceNum
        inner join tblMakeSubject c
        on b.subjectNum = c.seq
        inner join tblSetScore d
        on c.seq = d.seq 
        inner join tblbook e
        on c.bookNum = e.seq
        inner join tblSubject f
        on f.seq = c.subjectNum
        inner join tblCourse g
        on g.seq = a.courceNum
        inner join tblroom h
        on h.seq = a.roomNum
    
where 
    a.seq between 1 and 3 and a.teachernum = pseq;
    
END proc_setScoreList;


--4-1 배점수정
CREATE OR REPLACE PROCEDURE proc_editsetScore
(
	pattendance NUMBER,--출석점수
	pwrite NUMBER,--필기점수
    ppractice NUMBER, --실기점수
    pseq NUMBER -- 번호
) 
IS
BEGIN
	update 
        tblsetscore 
    set 
        attendance = pattendance, 
        write = pwrite, 
        practice = ppractice
    where 
        seq = pseq;
END proc_editsetScore;

select * from tblsetscore


--4-2배점 추가
CREATE OR REPLACE PROCEDURE proc_addSetScore
(
    pattendance number, -- 출석점수
    pwrite number, -- 필기점수
    ppractice number, -- 실기점수
    pmakesubjectnum number --개설과목

)
IS
BEGIN
	INSERT INTO tblSetScore (seq, attendance, write, practice, makeSubjectNum) VALUES (SEQSCORE.nextval, pattendance, pwrite, ppractice, pmakesubjectnum);
	COMMIT;
    
EXCEPTION 
	WHEN OTHERS THEN
		ROLLBACK;
        
END proc_addSetScore;


--5.과목정보조회
--로그인한 교사가 담당하고있는 과목의 정보를 조회
CREATE OR REPLACE PROCEDURE proc_ScoreList
(
   pseq IN NUMBER, --교사번호
   pcursor OUT sys_refcursor
)
IS
BEGIN 
	OPEN pcursor FOR
    
    select 
    
        b.subjectnum as subjectNum,
        f.name as subjectName,
        e.name as bookName,
        d.attendance as attendance,
        d.write as write,
        d.practice as practice,
        to_char(c.startdate, 'yy-mm-dd') as subjectStartDate,
        to_char(c.enddate, 'yy-mm-dd') as subjectEndDate,
        g.name as courseName,
        to_char(a.startDate, 'yy-mm-dd') as courseStartDate,
        to_char(a.endDate,'yy-mm-dd') as courseEndDate,
        h.roomNum as roomName,
        case
            when
                d.attendance IS NOT NULL AND
                d.practice IS NOT NULL AND
                d.write IS NOT NULL
               Then '등록'
               Else '미등록'
        end as scoreStatus       

    from 
        tblmakecource a
        inner join tblCourceSubjectAssign b
        on a.seq = b.createdCourceNum
        inner join tblMakeSubject c
        on b.subjectNum = c.seq
        inner join tblSetScore d
        on c.seq = d.seq 
        inner join tblbook e
        on c.bookNum = e.seq
        inner join tblSubject f
        on f.seq = c.subjectNum
        inner join tblCourse g
        on g.seq = a.courceNum
        inner join tblroom h
        on h.seq = a.roomNum
    
where 
    a.seq between 1 and 3 and a.teachernum = pseq and c.seq between 1 and 21 and c.enddate < to_char(sysdate, 'yyyy-mm-dd');
    
END proc_ScoreList;

select * from tblmakecource



-- 5-1 과목별 학생성적조회
-- 담당하고있는 과목 선택시 학생성적리스트 출력
CREATE OR REPLACE PROCEDURE proc_StudentScoreList
(
   pseq IN NUMBER, -- 과목번호
   tseq IN NUMBER, -- 교사번호
   pcursor OUT sys_refcursor
)
IS
BEGIN 
	OPEN pcursor FOR
    
    SELECT 

        a.seq as seq,
        a.name as studentName,
        f.seq as scoreSeq,
        f.attendance as attendance,
        f.practice as practice,
        f.writer as writer,
        c.registate as registate
        
    FROM 
    tblstudent a
    inner join tblRegiCource b
    on a.seq = b.studentnum
    inner join tblregistate c 
    on b.regiStateNum = c.seq
    inner join tblMakeCource d
    on b.createdCourceNum = d.seq
    inner join tblCourceSubjectAssign e
    on d.seq = e.createdCourceNum
    inner join tblScore f
    on b.seq = f.reginum
    
    where 
    
        a.seq between 1 and 90 and f.seq between 1 and 189 and e.subjectnum=2 and f.makesubjectnum = pseq and d.teachernum = tseq  
        
    order by a.seq;    
   
END proc_StudentScoreList;



--5-2.성적 입력
CREATE OR REPLACE PROCEDURE proc_editScore
(
	pattendance NUMBER,--출석점수
	pwrite NUMBER,--필기점수
    ppractice NUMBER, --실기점수
    pseq NUMBER -- 번호
) 
IS
BEGIN
	update 
        tblscore 
    set 
        attendance = pattendance, 
        writer = pwrite, 
        practice = ppractice
    where 
        seq = pseq;
END proc_editScore;

















--project_DML.sql

-- A008 시험 관리 및 성적 조회
-- 관리자 
-- [성적조회]>[과정별]
-- 과정리스트 (이름, 성적 등록 여부)
create or replace procedure procCourseList
(
	psseq IN NUMBER,
	pcursor OUT sys_refcursor
)
IS
BEGIN
	OPEN pcursor FOR
select 
    c.seq as seq,   --과정번호
    c.name as cname,           --과정명
    mc.startDate as startDate,       --과정시작일
    mc.endDate as endDate          --과정종료일        
from tblCourse c        --과정
    inner join tblMakeCource mc     --과정개설     
        on c.seq = mc.CourceNum
order by mc.startDate asc;
end;

                                   
-- [성적조회] > [과정별] > [과정선택]
-- 과목리스트(과목명, 개설 과목기간, 강의실명, 개설 과목명, 교사명, 교재명 등)을 출력
create or replace procedure procScSubjectList
(
    vcursor out sys_refcursor  
)
is
begin
    open vcursor for
select 
    sj.seq as seq,                      --과목번호
   sj.name as sjname,                --과목명
   sj.duration as duration,      --과목기간
   t.name as tname,                 --강사명
   b.name as book                  --교재명

from tblCourse c
    inner join tblMakeCource mc --과정개설
        on c.seq = mc.CourceNum
            inner join tblCourceSubjectAssign csa   --과정과목개설
                on csa.createdCourceNum = mc.seq
                    inner join tblMakeSubject ms   --과목개설
                        on ms.seq = csa.subjectNum
                            inner join tblSubject sj   --과목
                             on ms.subjectNum = sj.seq
                        inner join tblTeacher t     --강사명
                            on mc.teacherNum = t.seq                           
                inner join tblBook b     --교재
                    on ms.bookNum = b.seq;
                        
end;
 
                    
-- [성적조회] > [과정별] > [과정선택] > [과목선택]
-- 교육생 성적 정보(교육생 이름, 주민번호 뒷자리, 필기, 실기) 출력
create or replace procedure procScCourseSubjectScore
(
    vcursor out sys_refcursor
    
)
is
begin
    open vcursor for
select 
    st.name as "교육생이름",         --교육생이름
    substr(st.jumin,8) as "주민번호",          --주민번호 뒷자리
    sc.writer as "필기점수",        --필기점수    
    sc.practice as "실기점수"        --실기점수
    
from tblCourse c
    inner join tblMakeCource mc --과정개설
        on c.seq = mc.CourceNum
            inner join tblCourceSubjectAssign csa   --과정과목개설
                on csa.createdCourceNum = mc.seq
                    inner join tblMakeSubject ms   --과목개설
                        on ms.seq = csa.subjectNum
                            inner join tblSubject sj   --과목
                             on ms.subjectNum = sj.seq
                    inner join tblScore sc      --성적
                        on sc.makeSubjectNum = ms.seq
                            inner join tblRegiCource rc     --수강
                                on sc.regiNum = rc.seq
                                    inner join tblStudent st        --교육생
                                        on rc.studentNum = st.seq;                                 
end;
                                        



-- A009 시험 관리 및 성적조회
-- [성적조회] > [교육생별]
-- 교육생 리스트 (교육생 이름, 주민번호 뒷자리, 개설 과정명, 개설 과정기간, 강의실명) 출력
CREATE OR REPLACE PROCEDURE procScoreListStudent
(
	pcursor OUT sys_refcursor
)
IS
BEGIN
	OPEN pcursor FOR
select 
    st.seq,  
    st.name as sname,                                --교육생이름
    substr(st.jumin,8) as jumin,                     --주민번호 뒷자리
    c.name as cname,                                 --과정명
    mc.startDate || '-' || mc.endDate as courseterm  --과정기간
    
from tblCourse c
    inner join tblMakeCource mc --과정개설
        on c.seq = mc.courceNum 
            inner join tblRegiCource rc     --수강
                on rc.createdCourceNum = mc.seq
                    inner join tblStudent st        --교육생
                        on rc.studentNum = st.seq
                    order by st.name asc;
end;



declare
    vcursor sys_refcursor;
    vrow procStudentCourseList%rowtype;
begin
    procStudentCourseList(1, vcursor);
    loop
        fetch vcursor into vrow;
            dbms_output.put_line(vrow.sname);
        exit when vcursor%notfound;
    end loop;
end;



                    
-- [성적조회] > [교육생별] >  [교육생 선택]
-- 교육생 개인이 수강한 모든 개설 과목에 대한 성적 정보(개설 과목명, 소요일수, 교사명, 출결, 필기, 실기        
CREATE OR REPLACE PROCEDURE procScStudentSubject
(
	pcursor OUT sys_refcursor
)
IS
BEGIN
	OPEN pcursor FOR
select
    sj.name as sjname,                --과목명
    sj.duration as duration,          --소요일수
    t.name as tname,                 --교사명
    sc.attendance as attendance,        --출결점수
    sc.writer as write,            --필기점수
    sc.practice as practice           --실기점수


from tblSubject sj          --과목
    inner join tblMakeSubject ms     --과목개설
        on ms.subjectNum = sj.seq
            inner join tblScore sc       --성적
                on sc.makeSubjectNum = ms.seq
                    inner join tblTeacherSubjectList tsl      --과목목록
                        on  tsl.subjectNum = sj.seq
                            inner join tblTeacher t           --교사           
                                on tsl.teacherNum = t.seq;
end;                                
        

--[성적추가]
CREATE OR REPLACE PROCEDURE procAddScStudentSubject
(
    pattendance in number, 
    ppractice in number,
    pwrite in number
    
    )
is
begin 
    insert into tblScore (seq, attendance, practice, writer) values (SEQScore.nextVal, pattendance, ppractice, pwrite);
    commit;
exception 
	when others then
		rollback;
end;    

                
--[성적 삭제]
CREATE OR REPLACE PROCEDURE procDeleteScStudentSubject
(
	pseq IN NUMBER			
)
IS 
BEGIN
	DELETE FROM tblScore WHERE seq = pseq;
	COMMIT;
EXCEPTION 
	WHEN OTHERS THEN
		ROLLBACK;
END;


--[성적 수정]
create or replace procedure procEditScStudentSubject
(
    pattendance in number, 
    ppractice in number,
    pwrite in number
)
is
begin
    update tblScore set 
        attendance = pattendance,
        practice = ppractice,
        writer = pwrite;
        commit;
exception
    when others then
    rollback;
end;




---------------------

--A007 교육생 상담일지 관리
-- [관리자] > [상담일지 관리] > [전체 교육생 조회]
-- 교육생 리스트 출력 (교육생이름, 주민번호뒷자리, 수강상태)
CREATE OR REPLACE PROCEDURE proclistStudentList
(
	pcursor OUT sys_refcursor
)
IS
BEGIN
	OPEN pcursor FOR
        select * from vwStudentList;
end;


create or replace view vwStudentList
as
select
    c.seq as seq,
    st.name as sname,     --이름
    substr(st.jumin,8) as jumin,        --주민번호 뒷자리
    rs.regiState as regiState              --수강상태
from tblRegiCource c        --수강 
    inner join tblstudent st        --학생
        on c.studentNum = st.seq
            inner join tblRegistate rs    --수강상태
                on c.regiStateNum = rs.seq
                    order by seq asc;

------------------------------------------------------------------------------------------------------------
-- [관리자] > [상담일지 관리] > [전체 교육생 조회]
-- 교육생 리스트 출력 (교육생이름, 주민번호뒷자리, 수강상태)
CREATE OR REPLACE PROCEDURE procStudentList
(
	pcursor OUT sys_refcursor
)
IS
BEGIN
	OPEN pcursor FOR
select
    c.seq as seq,
    st.name as sname,     --이름
    substr(st.jumin,8) as jumin,        --주민번호 뒷자리
    rs.regiState as regiState              --수강상태
from tblRegiCource c        --수강 
    inner join tblstudent st        --학생
        on c.studentNum = st.seq
            inner join tblRegistate rs    --수강상태
                on c.regiStateNum = rs.seq
                    order by seq asc;
end;




declare
    vcursor sys_refcursor;
    vrow vwStudentList%rowtype;
begin
    procStudentList(1, vcursor);
    loop
        fetch vcursor into vrow;
            dbms_output.put_line(vrow.sname);
        exit when vcursor%notfound;
    end loop;
end;



-- [관리자] > [상담일지 관리] > [전체 교육생 조회] > [교육생 선택]
--교육생의 전 과목 상담 일지 리스트 (상담번호, 작성날짜, 교육생이름, 과목번호, 과목명, 과정기간, 수료여부, 상담사유, 상담내용 ) 출력
CREATE OR REPLACE PROCEDURE procStudentConsultList
(
	pcursor OUT sys_refcursor
)
IS
BEGIN
	OPEN pcursor FOR
select 
    c.seq as seq,                --상담번호
    c.consultdate as consultDate,        --상담날짜
    st.name as sname,            --교육생이름
    sj.seq as subjectSeq,               --과목번호
    sj.name as subjectName,                --과목명
    mc.startDate || '-' || mc.endDate as courseDate,        --과정기간
    --rs.regiState as regiState,         --수강상태
    cr.reason as consultReason,            --상담사유
    c.content as consultContent             --상담내용
    
from tblCourseConsultation c 
        inner join tblConsultationReason cr -- 상담-상담사유
            on cr.seq = c.reasonNum
                inner join tblRegiCource rc -- 상담-수강
                    on  c.regiNum = rc.seq
                        inner join tblStudent st        --학생
                            on rc.studentNum = st.seq
                                inner join tblRegistate rs      --수강상태
                                    on rc.regiStateNum = rs.seq
                inner join tblMakeSubject ms        --과목개설
                    on c.makeSubjectNum = ms.seq
                        inner join tblSubject sj        --과목
                            on ms.subjectNum = sj.seq                    
                            inner join tblMakeCource mc     --과정개설
                                on rc.createdCourceNum = mc.seq                               
                                order by c.consultdate asc;
end;



--[상담추가]
CREATE OR REPLACE PROCEDURE procAddConsultation
(
    pdate in date, --상담날짜
    pcontent in varchar2    --상담내용
    )
is
begin 
    insert into tblCourseConsultation (seq, consultDate, content) values (SEQCourseConsultation.nextVal, pdate, pcontent);
    commit;
exception 
	when others then
		rollback;
end;    

                
--[상담 삭제]
CREATE OR REPLACE PROCEDURE procDeleteConsultation
(
	pseq IN NUMBER			
)
IS 
BEGIN
	DELETE FROM tblCourseConsultation WHERE seq = pseq;
	COMMIT;
EXCEPTION 
	WHEN OTHERS THEN
		ROLLBACK;
END;


--[상담 수정]
create or replace procedure procEditInterviewsEvaluation
(
    pdate in date, --상담날짜
    pcontent in varchar2    --상담내용
)
is
begin
    update tblCourseConsultation set 
        consultdate = pdate,
        content = pcontent;
        commit;
exception
    when others then
    rollback;
end;




-----------------------------------------------------------------------------------------------------------------------------

-- [교사]
--[사후 처리 조회]
--[취업 상담내역 관리] 
-- 수료하여 취업상담을 진행한 교육생리스트 출력
select    
    st.name as sname,   --교육생이름
    rs.regiState as regiState  --수강상태
from tblRegiCource rc   --수강  
    inner join tblJobConsultation jc    --취업상담
        on jc.regiNum = rc.seq
            inner join tblRegistate rs  --수강상태
                on rc.regiStateNum = rs.seq
                    inner join tblStudent st    --학생  
                        on rc.studentNum = st.seq 
                            where rs.regiState = '수료'
                            order by st.name;
                            
--[교육생 선택]
--상담번호, 상담날짜, 교육생이름, 교사이름, 과정명, 상담내용) 출력  
select
    jc.seq as jcseq,      --상담번호
    jc.consdate as consdate,  --상담날짜
    st.name as sname,      --교육생명
    t.name as tname,        --교사명
    c.name as cname,        --과정명
    jc.content as jcContent    --상담내용
    
from tblRegiCource rc   --수강  
    inner join tblJobConsultation jc    --취업상담
        on jc.regiNum = rc.seq
            inner join tblRegistate rs  --수강상태
                on rc.regiStateNum = rs.seq
                    inner join tblStudent st    --학생  
                        on rc.studentNum = st.seq
                            inner join tblMakeCource mc     --과정개설
                                on rc.createdCourceNum = mc.seq
                                    inner join tblTeacher t     --강사명
                                        on mc.teacherNum = t.seq     
                                            inner join tblCourse c   --과정
                                                on mc.courceNum = c.seq
                            where rs.regiState = '수료'
                            order by st.name;
-----------------------------------------------------------------------------------------------
-- B005[상담일지 작성]
-- [교사] >  [상담일지] > [조회] or [등록]
-- [조회]

select
    cc.seq as seq,          --상담번호
    st.name as sname,    --교육생명
    t.name as tname,     --교사명
    sj.seq as sjseq,     --과목번호
    sj.name as sjname,    --과목명
    to_char(mc.startDate, 'yyyy/mm/dd') ||'-'|| to_char(mc.endDate, 'yyyy/mm/dd') as coursedate,     --과정기간
    cc.consultDate as date,     --상담날짜(작성일)
    cr.reason as reason,        --상담사유
    cc.content as content       --상담내용
    
from tblCourseConsultation cc       --수업상담
    inner join tblRegiCource rc --수강
        on cc.regiNum = rc.seq
    inner join tblConsultationReason cr --상담사유
        on cr.seq = cc.reasonNum
    inner join tblStudent st    --교육생
        on rc.studentNum = st.seq
    inner join tblMakeCource mc  --과정개설
        on mc.seq = rc.createdCourceNum
    inner join tblTeacher t   --교사
        on cc.teacherNum = t.seq
    inner join tblRegistate rs   --수강상태
        on rs.seq = rc.regiStateNum
    inner join tblMakeSubject ms        --과목개설
        on ms.seq = cc.makeSubjectNum
    inner join tblSubject sj        --과목
        on ms.subjectNum = sj.seq
            where rs.regiState = '수강중';


-- [교사] >  [상담일지] > [조회] or [등록]

-- [등록]
create or replace procedure procAddCConsultation
(
    pconsultdate varchar2,
    pcontent varchar2
)
is
begin
    insert into tblCourseConsultation (consultdate, content) values (pconsultdate, pcontent);
commit;
exception 
	when others then
		rollback;
end;
            

-----------------------------------------------------------------------------------------------------------
                            

--[교사] > [모의 면접 관리]
--[모의면접 평가관리 조회]
-- 모의면접 번호, 모의면접 날짜, 교육생이름, 교사이름, 모의면접 질문 리스트,평가, 점수) 출력

create or replace procedure proclistInterviewsEvaluation
(
    pseq in number,
	pcursor OUT sys_refcursor
) 
IS
BEGIN
	OPEN pcursor FOR
select
    st.seq,      --번호
    st.name as sname,      --교육생명
    t.name as tname,        --교사명
    iv.question as question,        --모의면접 질문
    ie.evaluation as evaluation,    --면접평가
    ie.score as score      --면접점수
    
from tblRegiCource rc   --수강  
    inner join tblInterviewsEvaluation ie    --모의면접평가
        on ie.regiNum = rc.seq
            inner join tblRegistate rs  --수강상태
                on rc.regiStateNum = rs.seq
                    inner join tblStudent st    --학생  
                        on rc.studentNum = st.seq
                            inner join tblTeacher t     --강사명
                                on ie.teacherNum = t.seq  
                                    inner join tblInterview iv  --모의면접
                                        on ie.interviewNum = iv.seq
                            where rs.regiState = '수료' and st.seq = pseq
                            order by st.name;
end;                            


--[모의면접 평가관리 추가]
create or replace procedure procAddInterviewsEvaluation
(
    pevaluation varchar2,
    pscore varchar2
        
)
is
begin
    insert into tblInterviewsEvaluation (evaluation, score) values ('evaluation', 'pscore');
	commit;
exception 
	when others then
		rollback;
end;


--[모의면접 평가관리 삭제]
CREATE OR REPLACE PROCEDURE procdeleteInterviewsEvaluation
(
	pseq IN NUMBER			
)
IS 
BEGIN
	DELETE FROM tblInterviewsEvaluation WHERE seq = pseq;
	COMMIT;
EXCEPTION 
	WHEN OTHERS THEN
		ROLLBACK;
END;


--[모의면접 평가 수정]
create or replace procedure procEditInterviewsEvaluation
(   
    pseq in number,
    pevaluation in varchar2,      --면접질문
    pscore in number       --평가기준
)
is
begin
    update tblInterviewsEvaluation set 
        evaluation = pevaluation,
        score = pscore
        where seq = pseq;
        commit;
exception
    when others then
    rollback;
end;







---------------------------------------------------------------------------------------------





--[교사] > [모의 면접 관리] > [모의면접 데이터관리]    
--[조회]
--(모의면접 번호(PK), 담당 교사이름, 모의면접 질문 리스트, 답변에 따른 점수 기준) 출력

select
        iv.seq as seq,      --모의면접번호
        iv.question as Question,      --면접질문
        iv.standard as Standard       --점수기준
    from tblInterview iv;    

                          
select * from tblInterview;                

                
--[모의면접 데이터 추가]
CREATE OR REPLACE PROCEDURE procAddInterviewData
(
    pquestion in varchar2,      
    pstandard in varchar2       
)
IS
BEGIN
	insert into tblInterview (seq, question, standard) values (SEQInterview.nextVal, pquestion, pstandard);
	COMMIT;
EXCEPTION 
	WHEN OTHERS THEN
		ROLLBACK;
END;



--[모의면접 데이터 수정]
create or replace procedure procEditInterviewData
(   
    pseq in number,
    pquestion in varchar2,      --면접질문
    pstandard in varchar2       --평가기준
)
is
begin
    update tblInterview set 
        question = pquestion,
        standard = pstandard
        where seq = pseq;
        commit;
exception
    when others then
    rollback;
end;
    
    
--[모의면접 데이터 삭제]    
create or replace procedure procDeleteInterviewData
(
    pseq in number
)
is
begin
    delete from tblInterview where seq = pseq;
    commit;
exception
    when others then
    rollback;
end;
                



-----------------------------------------------------------------------------------------




--[각종 데이터 통계 기능(일,주,월,년 단위)]
--[메인] > [교사] > [데이터통계] 
--  >[출석률] or [과목별 출결] or [과목별 평균 시험 점수] or [담당 강의 평점]

--[출석률선택]
--[과정별] or [수료생별]
--[과정별 선택]



--[수료생별]



--[과목별 평균 시험 점수]
select * from tblScore;

select 
    sj.name as "과목명", --과목명
    (select round(avg(sc.writer),1) from tblScore sc) as 필기점수,
    (select round(avg(sc.practice),1) from tblScore sc) as 실기점수
            from tblMakeSubject ms        --과목개설
                    inner join tblSubject sj        --과목
                        on ms.subjectnum = sj.seq
                            where sj.seq = 2;


select 
    sj.name as "과목명", --과목명
    
    ms.seq as 개설과목번호,
    
    (select round(avg(writer),1) from tblScore
    where makesubjectnum = ms.seq
    group by makesubjectnum) as 필기점수,
    
    (select round(avg(practice),1) from tblScore
    where makesubjectnum = ms.seq
    group by makesubjectnum) as 실기점수
    
            from tblMakeSubject ms        --과목개설
                    inner join tblSubject sj        --과목
                        on ms.subjectnum = sj.seq
                            where ms.seq in (select subjectNum from tblCourceSubjectAssign  where createdCourceNum = 4);


select subjectNum from tblCourceSubjectAssign  where createdCourceNum = 2;  

select * from tblCourceSubjectAssign; 
select * from tblMakeSubject;
select * from tblSubject;


select sj.seq
from tblSubject sj
inner join tblMakeSubject ms
    on ms.subjectNum = sj.seq
        where ms.seq=1;

--과정-과목
select 
    mc.courceNum,
    csa.subjectNum,
    sj.name
from tblMakeCource mc  --과정개설
    inner join tblCourceSubjectAssign csa   --과정과목배정
        on csa.createdCourceNum = mc.seq
            inner join tblMakeSubject ms
                on csa.subjectNum = ms.seq
                    inner join tblSubject sj
                        on ms.subjectNum = sj.seq
            where mc.courceNum =2
            order by csa.subjectNum asc;




--[담당 강의 평점]
select distinct c.name as 과정명, 
                (select round(avg(te.materials),1)
                    from tblMakeCource mc 
                        inner join tblCourceComplet cc
                            on cc.createdCourceNum = mc.seq
                                inner join tblTeacherEvaluation te 
                                    on te.completNum = cc.seq
                                         where mc.teacherNum = pmc.teacherNum) as 교재 ,                                        
                (select round(avg(te.communication),1)
                    from tblMakeCource mc 
                        inner join tblCourceComplet cc
                            on cc.createdCourceNum = mc.seq
                                inner join tblTeacherEvaluation te 
                                    on te.completNum = cc.seq
                                         where mc.teacherNum = pmc.teacherNum) as 소통,
                (select round(avg(te.jobpreparing),1)
                    from tblMakeCource mc 
                        inner join tblCourceComplet cc
                            on cc.createdCourceNum = mc.seq
                                inner join tblTeacherEvaluation te 
                                    on te.completNum = cc.seq
                                         where mc.teacherNum = pmc.teacherNum) as 취업,
                (select round(avg(te.divisionTime),1)
                    from tblMakeCource mc 
                        inner join tblCourceComplet cc
                            on cc.createdCourceNum = mc.seq
                                inner join tblTeacherEvaluation te 
                                    on te.completNum = cc.seq
                                         where mc.teacherNum = pmc.teacherNum) as 시간분배                                        
                                         from tblCourse c 
                                                inner join tblMakeCource pmc 
                                                    on c.seq = courceNum
                                                    where pmc.teacherNum = 5;























   
                                                                       
create or replace procedure procAttCourseList(
    pstudentnum number,
    pcursor out sys_refcursor
)
is
    vreginum number;
    vcoursenum number;
begin

    select rc.seq into vreginum from tblstudent s
    inner join tblregicource rc
    on s.seq = rc.studentNum
    where s.seq = pstudentnum;
    
    select mc.seq into vcoursenum from tblregicource rc
    inner join tblMakecource mc
    on rc.createdcourcenum = mc.seq
    where rc.seq = vreginum;
    

    open pcursor for
        select
            to_char(alldate, 'yyyy-mm-dd') as alldate,
            intime,
            outtime,
           case
                when to_char(to_date(alldate, 'yyyy-mm-dd'), 'd') in (5, 6) then '주말(토, 일)'
                when regdate is null then '결석'
                when intime < '09:10:00' and outtime > '17:50:00' and count = 2 then '출석'
                when intime > '09:10:00' and outtime > '17:50:00' and count = 2 then '지각'
                when intime < '09:10:00' and outtime < '17:50:00' and count = 2 then '조퇴'
            end as "attstate"
            from (select 
                    vw.alldate as alldate, 
                    att.regdate as regdate, 
                    att.count as count,
                    (select min(to_char(time, 'HH24:mi:ss')) as  intime from tblattendance
                    where regiNum = vreginum and to_date(time, 'YYYY-MM-DD') = to_date(vw.alldate, 'YYYY-MM-DD')) as intime,
                    (select max(to_char(time, 'HH24:mi:ss')) as  intime from tblattendance
                    where regiNum = vreginum and to_date(time, 'YYYY-MM-DD') = to_date(vw.alldate, 'YYYY-MM-DD')) as outtime
            from (select to_date(time, 'YYYY-MM-DD') as regdate, count(*) as count from tblattendance
                    where regiNum = vreginum -- 수강번호
                    group by to_date(time, 'YYYY-MM-DD')) att
            right outer join (select 
                                            (select startDate from tblMakeCource where seq = vcoursenum) + level - 1 as alldate
                                            from dual
                                            connect by level < (select to_date(endDate, 'yyyy-mm-dd') from tblMakeCource where seq = vcoursenum) - (select to_date(startDate, 'yyyy-mm-dd') from tblMakeCource where seq = vcoursenum) + 2) vw
            on to_date(vw.alldate, 'YYYY-MM-DD') = to_date(att.regdate, 'YYYY-MM-DD')
            order by vw.alldate);
end procAttCourseList;

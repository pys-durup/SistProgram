-- 스크립트 통합본_박영수

-- [취업지원 관리] - [연계기업 관리] - [연계기업 조회]

select *  from tblLinkCompany order by seq;


-- [취업지원 관리] - [연계기업 관리] - [연계기업 검색]

create or replace procedure procSearchCompany(
    pword varchar2,
    prowtype out tblLinkCompany%rowType
)
is
begin
    select *  into prowtype from tblLinkCompany 
    where name like '%' || pword || '%'
    order by seq;
end procSearchCompany;

declare
    vrowtype tblLinkCompany%rowType;
begin
    procSearchCompany('우아', vrowtype);
    dbms_output.put_line(vrowtype.name);
end;


-- [취업지원 관리] - [연계기업 관리] - [연계기업 추가]

create or replace procedure procAddCompany(
    pname varchar2,
    paddress varchar2,
    pdepart varchar2,
    psalary number
)
is
begin
    insert into tblLinkCompany (seq, name, address, department, salary)
        values (101, pname , paddress, pdepart, psalary);
    
    commit;
exception
    when others then
        rollback;
end procAddCompany; 
 
begin
    procAddCompany('쌍용교육센터', '한독약품빌딩', '개발팀', 2400);
end;



-- [취업지원 관리] - [연계기업 관리] - [연계기업 수정]

create or replace procedure procModifyCompany(
    pseq number,
    pname varchar2,
    paddress varchar2,
    pdepart varchar2,
    psalary number
)
is
begin
    update tblLinkCompany set name = pname , address=paddress, department = pdepart, salary = psalary
        where seq = pseq;
    commit;
exception
    when others then
        rollback;
end procModifyCompany; 


begin
    procModifyCompany(101, '쌍용교육센터', '한독약품빌딩수정', '개발팀', 2600);
end;



-- [취업지원 관리] - [연계기업 관리] - [연계기업 삭제]

create or replace procedure procDeleteCompany(
    pseq number
)
is
begin
    delete from tblLinkCompany where seq = pseq;
    commit;
exception
    when others then
        rollback;
end procDeleteCompany; 

begin
    procDeleteCompany(101);
end;


------------------------------------------------------------------------------------------------

-- [추천 인재 목록 관리] - [추천 인재 조회]
-- 인재 목록을 조회할 수 있는 뷰

create or replace view vwtalentedStudentList
as
select 
    st.seq as seq, -- 학생번호
    st.name as name, -- 학생명
    st.tel as tel, --전화번호
    ts.portfolio as portfolio, -- 포트폴리오
    ts.reason as reason -- 추천이유
from tblTalentedStudent ts
    inner join tblregicource rc
    on ts.regiNum = rc.seq
        inner join tblstudent st
        on st.seq = rc.studentNum
            order by st.seq;

select * from vwtalentedStudentList;
select * from  vwAbleTStudentScoreList where seq = 2;


-- [추천 인재 목록 관리] - [추천 인재 검색]
-- java 에서 preparedStatement로 처리

select * from vwtalentedStudentList
where seq = 2;


-- [추천 인재 목록 관리] - [추천 인재 추가]
-- 1, 추천할 인재의 목록이 출력된다 ( 성적순 상위 10명 인재의 성적정보 )

create or replace view vwAbleTStudentScoreList
as
select * from
(select 
s.reginum as reginum,
st.name as name,
round(avg(s.writer),1) as writer,
round(avg(s.practice),1) as practice,
round(avg(s.attendance),1) attendance
from tblscore s
inner join tblRegiCource rc
on rc.seq = s.reginum
inner join tblStudent st
on st.seq = rc.studentnum
where s.reginum in (select regiNum from tblCourceComplet)
and s.reginum not in (select regiNum from tblTalentedStudent)
group by s.reginum, st.name
order by round(avg(s.writer),1) desc, round(avg(s.practice),1) desc, round(avg(s.attendance),1) desc)
where rownum <= 10;

-- 2. 인재 활용 가능 언어 (추가가 필요한 기능)
select
    l.language 
from tblstudentlanguage sl
inner join tblLanguage l
on sl.languagenum = l.seq
where studentNum = 6; -- 수강생 별로 검색



-- 3. 인재 추가 프로시저
create or replace procedure procAddTalentedStudent(
    preginum number, 
    pportfolio varchar2,
    preason varchar2
)
is
begin
    insert into tblTalentedStudent (seq, portfolio, reason, reginum)
        values (seqTalentedStudent.nextVal, pportfolio, preason, preginum);
    commit;
exception
    when others then
        rollback;
end procAddTalentedStudent;

begin
    procAddTalentedStudent(2, 'testURL', '테스트용2' );
end;



-- [추천 인재 목록 관리] - [추천 인재 수정]
-- 인재 수정 프로시저
create or replace procedure procEditTalentedStudent(
    pseq number,
    pportfolio varchar2,
    preason varchar2
)
is
    vreginum number;
begin
    -- 학생 번호를 받아서 수강 번호를 반환
    select rc.seq into vreginum from tblregicource rc
    inner join tblStudent s
    on rc.studentnum = s.seq
    where s.seq = pseq;
    
    dbms_output.put_line(vreginum);
    
    update tbltalentedStudent
    set portfolio = pportfolio, reason = preason
    where reginum = vreginum;
    
    commit;
end;

begin
    procEditTalentedStudent(2, 'git' , 'gitgit');
end;



-- [추천 인재 목록 관리] - [추천 인재 삭제]
-- 인재 삭제 프로시저
create or replace procedure procDeleteTalentedStudent(
    pseq number
)
is
begin
    delete from tblTalentedStudent where reginum = pseq;
    commit;
exception
    when others then
        rollback;
end procDeleteTalentedStudent;

begin
    procDeleteTalentedStudent(41);
end;

-- 결과 조회
select * from tblTalentedStudent ts
order by reginum;



------------------------------------------------------------------------------------------------

-- [기업에 인재 추천] - [추천현황 조회]
-- 기업 - 인재 추천 현황을 조회하는 뷰
create or replace view vwRecommendStudentList
as
select 
    r.seq as seq,
    st.name as studentname,
    lc.name as companyname,
    to_char(r.recodate, 'yyyy-mm-dd') as recodate
from tblRecommend r
    inner join tblLinkCompany lc
    on r.companynum = lc.seq
        inner join tbltalentedstudent ts
        on r.studentnum = ts.seq
            inner join tblregicource rc
            on ts.regiNum = rc.seq
                inner join tblstudent st
                on st.seq = rc.studentNum
                    order by recodate desc;

select * from vwRecommendStudentList;



-- [기업에 인재 추천] - [인재 추천하기]
-- 실사용 프로시저*****************************************************************
-- 1. 추천할 전체 인재의 목록 출력
create or replace view vwtalentedStudentList
as
select 
    st.seq as seq, -- 학생번호
    st.name as name, -- 학생명
    st.tel as tel, --전화번호
    ts.portfolio as portfolio, -- 포트폴리오
    ts.reason as reason -- 추천이유
from tblTalentedStudent ts
    inner join tblregicource rc
    on ts.regiNum = rc.seq
        inner join tblstudent st
        on st.seq = rc.studentNum
            order by st.seq;
            
-- 2. 인재번호를 선택해서 추천하는 프로시저
create or replace procedure procAddRecommend (
    pseq number, -- 학생번호
    pcompanynum number -- 연계기업 번호
    
)
is
    vstudentNum number; -- tblTalentedStudent테이블 seq 번호
begin
    -- 학생 번호를 받아서 tblTalentedStudent테이블 seq 번호를 반환
   select ts.seq into vstudentNum from tblregicource rc
    inner join tblStudent s
    on rc.studentnum = s.seq
    inner join tblTalentedStudent ts
    on rc.seq = ts.regiNum
    where s.seq = pseq;

    -- 추천 테이블에 insert
    insert into tblRecommend (seq, studentNum, companyNum, recodate)
        values (seqRecommend.nextVal, vstudentNum, pcompanynum, sysdate);
        
    dbms_output.put_line('인재 추천 등록 성공');
    
exception
    when others then
        rollback;
end procAddRecommend;

-- 프로시저 실행 테스트
begin
    procAddRecommend(2, 1);
end;



-- [기업에 인재 추천] - [추천 취소하기]
-- 인재 추천을 취소하는 프로시저
create or replace procedure procDeleteRecommend (
    pseq number
)
is
begin
    delete from tblRecommend where seq = pseq;
exception
    when others then
        rollback;
end procDeleteRecommend;

-- 프로시저 실행 테스트
begin
    procDeleteRecommend(121);
end;



---------------------------------------------------------------------------------------------------
-- [취업활동관리] - [취업현황조회] - [과정별]
-- 1. 종료된 과정의 목록을 출력한다
-- 종료된 과정의 목록을 출력하는 뷰
create or replace view vwEndCourseList
as
select 
mc.seq as seq,
c.name as coursename,
to_char(mc.startDate, 'yyyy-mm-dd') as startDate,
to_char(mc.endDate, 'yyyy-mm-dd') as endDate,
t.name as teacherName,
r.roomnum as room
from tblMakeCource mc
inner join tblCourse c
on mc.courcenum = c.seq
inner join tblTeacher t
on mc.teachernum = t.seq
inner join tblRoom r
on mc.roomnum = r.seq
where mc.endDate < sysdate
order by seq;

select * from vwEndCourseList;

-- 2. 해당 과정을 수료한 수료생의 목록을 출력한다
-- 과정번호를 받아 해당 과정을 수료한 수료생의 목록을 리턴하는 프로시저
create or replace procedure procCompletStudentList(
    pseq number, -- 과정번호
    pcursor out sys_refcursor
)
is
begin
    
    open pcursor for
        select
            s.name as name,
            s.jumin as jumin,
            s.tel as tel,
            to_char(s.regdate, 'yyyy-mm-dd') as regdate ,
            rc.seq as reginum
        from tblCourceComplet cc
                inner join tblregicource rc
                on rc.seq = cc.reginum
                    inner join tblstudent s
                    on s.seq = rc.studentnum
                        inner join tblmakecource mc
                        on mc.seq = rc.createdCourceNum
                            where mc.seq = pseq; -- 선택한 과정에 대한 결과

end procCompletStudentList;



-- 2.1 구직활동 보기
-- 수강번호를 매개변수로 받아 해당 학생의 구직활동정보를 리턴하는 프로시저
CREATE OR REPLACE PROCEDURE procJobActivitiesInfo (
    pregiNum IN NUMBER,
    pcursor OUT sys_refcursor
)
IS
BEGIN
    OPEN pcursor FOR
    select 
        c.license as license,
        c.resume as resume,
        c.job as Job,
        c.github as github,
        c.salary as salary
            from tblRegiCource b
                    inner join tblQualification c
                        on c.regiNum = b.seq
                            where b.seq = pregiNum; --수강번호로  조회

END procJobActivitiesInfo;



-- 2.2 취업정보 보기
-- 수강번호를 매개변수로 받아 해당 학생의 취업정보를 리턴하는 프로시저
create or replace procedure procJobinfoList(
    preginum number,
    pcursor out sys_refcursor
)
is
begin
    open pcursor for
        select 
            ji.seq as seq,
            to_char(ji.startdate, 'yyyy-mm-dd') as startdate,
            ji.insurance as insurance,
            ji.form as form,
            ji.career as career,
            ji.income as income,
            ji.completnum as completnum
        from tblJobInfo ji
            inner join tblcourcecomplet cc
            on ji.seq = cc.reginum
                where cc.regiNum = 3;
            
end procJobinfoList;



-- [취업활동관리] - [취업현황조회] - [수료생 검색]
-- 수료생 목록인 vwCompletStudentList 를 이용
-- 이름으로 검색
select * from vwCompletStudentList where name like '%손%';



-- [취업활동관리] - [취업현황조회] - [수료생 목록]
-- 수료한 교육생의 전체 목록을 출력하는 뷰
create or replace view vwCompletStudentList
as
select
    s.name as name,
    s.jumin as jumin,
    s.tel as tel,
    to_char(s.regdate, 'yyyy-mm-dd') as regdate,
    rc.seq as reginum
from tblCourceComplet cc
    inner join tblJobinfo ji
    on cc.seq = ji.completnum
        inner join tblregicource rc
        on rc.seq = cc.reginum
            inner join tblstudent s
            on s.seq = rc.studentnum
                inner join tblmakecource mc
                on mc.seq = rc.createdCourceNum
                    order by cc.reginum;


--------------------------------------------------------------------------------------------

-- [데이터통계] - [과정별 출석률]

-- 1. 종료된 과정 목록을 출력
-- vwEndCourseList 종료된 과정을 출력하는 뷰

-- 2. 해당 과정번호로 수료생의 목록을 가져옴
-- procCompletStudentList 프로시저 사용

-- 3. 수료생의 출결상태 통계 데이터를 찍어야함
-- 3.1 -- 수강상태 카운트 정보를 반환하는 프로시저
create or replace procedure procGetAttStateCount (
    pseq number,            -- 수강번호
    pcourcenum number,      -- 수강중인 개설과정 번호
    pstate varchar2,
    presult out number
)
is
begin

    select stateCount into presult from (select 
    atts.attstate,
--    attstate."attstate" as attstate, 
    count(attstate."attstate") as stateCount
from (select
    alldate,
   case
        when to_char(to_date(alldate, 'yyyy-mm-dd'), 'd') in (5, 6) then '주말(토,일)'
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
            where regiNum = pseq and to_date(time, 'YYYY-MM-DD') = to_date(vw.alldate, 'YYYY-MM-DD')) as intime,
            (select max(to_char(time, 'HH24:mi:ss')) as  intime from tblattendance
            where regiNum = pseq and to_date(time, 'YYYY-MM-DD') = to_date(vw.alldate, 'YYYY-MM-DD')) as outtime
from (select to_date(time, 'YYYY-MM-DD') as regdate, count(*) as count from tblattendance
        where regiNum = pseq -- 수강번호
        group by to_date(time, 'YYYY-MM-DD')) att
right outer join (select 
                            (select to_date(startDate, 'yyyy-mm-dd') from tblMakeCource where seq = pcourcenum) + level - 1 as alldate
                            from dual
                            connect by level < (select to_date(endDate, 'yyyy-mm-dd') from tblMakeCource where seq = pcourcenum) - (select to_date(startDate, 'yyyy-mm-dd') from tblMakeCource where seq = pcourcenum) + 2) vw
on to_date(vw.alldate, 'YYYY-MM-DD') = to_date(att.regdate, 'YYYY-MM-DD')
order by vw.alldate)) attstate
right outer join tblAttState atts
on attstate."attstate" = atts.attstate
group by attstate."attstate", atts.attstate)
where attstate = pstate;
    
end procGetAttStateCount;



--3.2 해당 수강번호의 출석상태 횟수들과 출석률을 반환하는 프로시저
create or replace procedure procGetAttRate(
    pseq number, -- 수강번호
    presult1 out number,
    presult2 out number,
    presult3 out number,
    presult4 out number,
    presult5 out number
--    presult6 out varchar2
--    presult number -- 출석률
)
is
    vcourceNum number;
--    vresult2 number;
--    vresult3 number;
--    vresult4 number;
begin
    select createdCourceNum into vcourceNum from tblRegiCource where seq = pseq;
--    select createdCourceNum from tblRegiCource where seq = 6;
--    dbms_output.put_line(vcourceNum);

--    select s.name into presult6 from tblStudent s
--    inner join tblRegiCource rc
--    on s.seq = rc.studentnum
--    where rc.seq = pseq;
    
    -- 수강번호, 수강중인 과정 개설번호, '상태정보'
    procGetAttStateCount(pseq, vcourceNum,'출석', presult1);
    procGetAttStateCount(pseq, vcourceNum,'결석', presult2);
    procGetAttStateCount(pseq, vcourceNum,'지각', presult3);
    procGetAttStateCount(pseq, vcourceNum,'조퇴', presult4);
    
    presult5 := (presult1+presult3) / (presult4 + presult3 + presult2 + presult1) * 100;
--    dbms_output.put_line('출석 : ' ||vresult1);
--    dbms_output.put_line('결석 : ' ||vresult2);
--    dbms_output.put_line('지각 : ' ||vresult3);
--    dbms_output.put_line('조퇴 : ' ||vresult4);
--    
--    dbms_output.put_line('출석률 : ' || (vresult1+vresult3) / (vresult4 + vresult3 + vresult2 + vresult1) * 100);
end procGetAttRate;




-- [데이터통계] - [과정별 수료율]

-- 1. 종료된 과정 목록을 출력
-- vwEndCourseList 종료된 과정을 출력하는 뷰

-- 2. 선택한 과정의 상세정보를 보여준다
-- vwEndCourseList 에 where 조건문 사용


-- 3. 선택한 과정의 수료율 정보를 보여준다
-- 과정의 수료율을 반환하는 프로시저

create or replace procedure procGetCompletRate(
    pcourceNum number,
    pcomplet out number,
    pfail out number,
    prate out number
)
is
    vcomplet number;
    vfail number;
begin
    select
            (select statecount from (select 
                rs.registate as state,
                count(rs.registate) as statecount
            from tblRegiCource rc
            right outer join tblRegistate rs
            on rc.registatenum = rs.seq
            where rc.createdcourcenum = pcourceNum
            group by rs.registate) -- group by 자동 수정된 부분
            where state = '수료') as complet,
            (select statecount from (select 
                rs.registate as state,
                count(rs.registate) as statecount
            from tblRegiCource rc
            right outer join tblRegistate rs
            on rc.registatenum = rs.seq
            where rc.createdcourcenum = pcourceNum
            group by rs.registate) -- group by 자동 수정된 부분
            where state = '중도탈락') as fail
            into pcomplet, pfail
    from dual;
    
    prate := vcomplet / (vcomplet + vfail) * 100;
--    dbms_output.put_line('수료인원 : ' || vcomplet);
--    dbms_output.put_line('중도포기인원 : ' || vfail);
--    dbms_output.put_line('수료율 : ' || vcomplet / (vcomplet + vfail) * 100);
    
end procGetCompletRate;


-- procGetCompletRate 프로시저 테스트
declare
    vcomplet number;
    vfail number;
    vrate number;
begin
    -- 과정번호
    procGetCompletRate(1, vcomplet, vfail, vrate);
        dbms_output.put_line('수료인원 : ' || vcomplet);
    dbms_output.put_line('중도포기인원 : ' || vfail);
    dbms_output.put_line('수료율 : ' || vcomplet / (vcomplet + vfail) * 100);
end;


-- [데이터통계] - [과정별 취업률]

-- 1. 종료된 과정 목록을 출력
-- vwEndCourseList 종료된 과정을 출력하는 뷰

-- 2. 선택한 과정의 상세정보를 보여준다
-- vwEndCourseList 에 where 조건문 사용



-- 3. 선택한 과정의 취업률 정보를 보여준다
-- 해당 과정의 취업률을 반환하는 프로시저
create or replace procedure procGetJobRate(
    pcourcenum number,
    pstudentcount out number,
    pgetjobcount out number,
    pcursor out sys_refcursor,
    pgetrate out number
    
)
is
    vstudentcount number;
    vgetjobcount number;
   
begin
    -- 수료한인원 27명 구하기
    select count(*) into pstudentcount from tblRegiCource
    where createdCourceNum = pcourcenum and registatenum = 2;
    dbms_output.put_line('수료 인원 : ' || pstudentcount);
    
    -- 취업한인원 22명 구하기
    select count(*) into pgetjobcount
    from tblCourceComplet cc
    inner join tblRegiCource rc
    on cc.reginum = rc.seq
    inner join tbljobinfo ji
    on ji.completNum = cc.seq
    where rc.createdcourcenum = 1;
    dbms_output.put_line('취업 인원 : ' || pgetjobcount);
    
    open pcursor for 
                                select
                                     to_char(ji.startDate, 'yyyymm') as empdate,
                                     count(to_char(ji.startDate, 'yyyymm')) as stcount
                                from tblCourceComplet cc
                                    inner join tblRegiCource rc
                                    on cc.reginum = rc.seq
                                        inner join tbljobinfo ji
                                        on ji.completNum = cc.seq
                                            where rc.createdcourcenum = 1
                                                group by to_char(ji.startDate, 'yyyymm');

    pgetrate := round((pgetjobcount / pstudentcount) * 100, 1);
    
end procGetJobRate;

-- procGetJobRate 테스트
begin
    procGetJobRate(1);
end;


---------------------------------------------------------------------------------------------

-- [출결관리] - [교육생 번호로 검색]
-- 1. 교육생 번호로 검색해서 교육생의 정보를 받아온다
select * from tblStudent where seq = ?;


-- 2.1 [과정 출결 보기]
-- 교육생이 수강하는 과정에 대한 모든 출결데이터를 출력한다
-- 수강번호로 해당 수강생의 전체 출결정보를 출력하는 프로시저
-- 수료생의 수강번호, 개설 과정의 seq가 필요함

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


-- 2.2 [과목별 출결 보기]
-- 2.2.1 교육생이 수강하는 과목 리스트를 리턴
-- 교육생이 수강하는 과목 리스트를 리턴하는 프로시저
create or replace procedure procStudentregiSubjectList(
    pstudentnum number,
    pcursor out sys_refcursor
)
is
begin

    open pcursor for
            select 
                ms.seq as subjectseq, -- 개설과목 seq
                s.name as subjectname,
                to_char(ms.startdate, 'yyyy-mm-dd') as startdate,
                to_char(ms.enddate, 'yyyy-mm-dd') as enddate,
                b.name as bookname,
                t.name as teachername
            from tblcourcesubjectassign cs
                inner join tblmakecource mc
                on cs.createdcourcenum = mc.seq
                    inner join tblmakesubject ms
                    on cs.subjectNum = ms.seq
                        inner join tblsubject s 
                        on ms.subjectNum = s.seq
                            inner join tblCourse c
                            on mc.courceNum = c.seq
                                inner join tblregicource rc
                                on rc.createdCourceNum = mc.seq
                                    inner join tblstudent st
                                    on rc.studentnum = st.seq
                                        inner join tblTeacher t
                                        on mc.teachernum = t.seq
                                            inner join tblBook b
                                            on ms.booknum = b.seq
                                                where st.seq = pstudentnum
                                                    order by ms.startdate asc;

end procStudentregiSubjectList;



-- 2.2.1 교육생이 수강한 과목 하나에대한 출결데이터를 뽑아낸다
-- 수강번호로 해당 수강생의 과목당 출결정보를 가져오는 프로시저
-- 수료생의 수강번호, 개설 과목의 seq가 필요함
create or replace procedure procAttSubjectList(
    pstudentnum number,
    psubjectseq number,
    pcursor out sys_refcursor
)
is
    vreginum number;
begin

    select rc.seq into vreginum from tblstudent s
    inner join tblregicource rc
    on s.seq = rc.studentNum
    where s.seq = pstudentnum;

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
                                                                    (select startDate from tblMakeSubject where seq = psubjectseq) + level - 1 as alldate
                                                                    from dual
                                                                    connect by level < (select to_date(endDate, 'yyyy-mm-dd') from tblMakeSubject where seq = psubjectseq) - (select to_date(startDate, 'yyyy-mm-dd') from tblMakeSubject where seq = psubjectseq) + 2) vw
                                    on to_date(vw.alldate, 'YYYY-MM-DD') = to_date(att.regdate, 'YYYY-MM-DD')
                                    order by vw.alldate);

end procAttSubjectList;




-- [출결관리] - [과정별]
-- 1. 모든 과정의 목록을 출력한다
-- 모든 과정의 목록을 출력하는 뷰
create or replace view vwAllCourseList
as
select 
mc.seq as seq,
c.name as coursename,
to_char(mc.startDate, 'yyyy-mm-dd') as startDate,
to_char(mc.endDate, 'yyyy-mm-dd') as endDate,
t.name as teacherName,
r.roomnum as room
from tblMakeCource mc
inner join tblCourse c
on mc.courcenum = c.seq
inner join tblTeacher t
on mc.teachernum = t.seq
inner join tblRoom r
on mc.roomnum = r.seq
order by seq;

select * from vwAllCourseList;



-- 2. 과정 번호를 입력받아 해당 과정을 듣는 교육생의 목록 출력
--  과정을 수강하는 교육생의 목록을 출력하는 프로시저

create or replace procedure procCourseStudentList(
    pcourseNum number,
    pcursor out sys_refcursor
)
is
begin
    open pcursor for
        select
            s.seq as seq,
            s.name as name,
            s.jumin as jumin,
            s.tel as tel,
            to_char(s.regdate, 'yyyy-mm-dd') as regdate
        from tblCourceComplet cc
            inner join tblregicource rc
            on rc.seq = cc.reginum
                inner join tblstudent s
                on s.seq = rc.studentnum
                    inner join tblmakecource mc
                    on mc.seq = rc.createdCourceNum
                        where mc.seq = pcourseNum
                            order by s.seq;
            
end procCourseStudentList;

-- 3.  교육생을 선택하고 [과정출결 보기], [과목별 출결보기] 진행 위와 동일


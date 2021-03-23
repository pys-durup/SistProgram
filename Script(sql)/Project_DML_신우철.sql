-------- 기초정보 관리 

SET SERVEROUTPUT ON ; 
-- 1. 강의실 조회 [메인] > [관리자] > [기초 정보 관리] > [강의실 정보] > [강의실 조회]

/* 강의실 명, 강의실 정원, 각 강의실에서 진행되는 과정을 확인 할 수 있다. */
create or replace view vwRoom
as
select a.seq, a.roomnum, a.capacity, c.name 
from tblRoom a
    inner join tblMakeCource b
    on a.seq = b.roomnum
        inner join tblcourse c
        on b.courcenum = c.seq
            where b.enddate > '20/11/6'; --현재 진행중인 강의만 표시하기 위해서 강의 종료일이 sysdate 이전 강의들은 표시하지 않는다.
            
select * from tblMakeCource



            
-- 2. 강의실 정보 변경 [메인] > [관리자] > [기초 정보 관리] > [강의실 정보] > [강의실 정보 변경] > [강의실 이름 변경]

/* 강의실 이름을 변경할 수 있다. */
CREATE OR REPLACE PROCEDURE procUpdateRoomNum (
    pSeq IN NUMBER,         --시퀄 번호
    pRoomNum IN VARCHAR2    --강의실 이름
    
)
IS
    vRoomNum tblRoom.roomnum%TYPE; --이전 강의실 이름
    v_tblRoom tblRoom%ROWTYPE;     --출력용 테이블 복사
BEGIN    
    select roomnum 
    into vRoomNum 
    from tblRoom
    where seq = pSeq;
    
    update tblRoom set roomnum = pRoomNum
    where seq = pSeq;
    
    commit; 
       
    --출력용
    select seq, roomnum
    into v_tblRoom.Seq, v_tblRoom.RoomNum
    from tblRoom
    where seq = pSeq;
    
    dbms_output.put_line('---- 수 정 정 보 -----');
    dbms_output.put_line('고유번호 : '|| pseq);
    dbms_output.put_line('강의실이름 : ' || vRoomNum);
    dbms_output.put_line('수정된 강의실 이름 : ' || v_tblRoom.RoomNum); 
    
    
End;


--EXECUTE procUpdateRoomNum(1, '개나리반'); 

--실행 



select * from tblRoom;


-- 3. 강의실 정보 변경 [메인] > [관리자] > [기초 정보 관리] > [강의실 정보] > [강의실 정보 변경] > [강의실 정원 변경]

/* 강의실 정원을 변경할 수 있다. 1,2,3 강의실은 최대 30인을, 4,5,6강의실은 최대 26을 제한으로 한다. */

CREATE OR REPLACE PROCEDURE procUpdateCapacity (
    pSeq NUMBER,         --시퀄 번호
    pCapacity IN NUMBER     --강의실 정원
    
)
IS
    vCapacity tblRoom.capacity%TYPE; -- 이전 강의실 정원
    v_tblRoom tblRoom%ROWTYPE;     --출력용 테이블 복사
BEGIN    
    select capacity
    into vCapacity 
    from tblRoom
    where seq = pSeq;
    
    if pSeq in(1,2,3) and pCapacity <=30 then               --강의실 123은 최대정원이 30명
        update tblRoom set capacity = pCapacity 
        where seq = pSeq;
        
        commit;
        
        --출력용
        select seq, capacity
        into v_tblRoom.Seq, v_tblRoom.Capacity
        from tblRoom
        where seq = pSeq;
        
        dbms_output.put_line('---- 수 정 정 보 -----');             --수정된 정보를 출력한다. 
        dbms_output.put_line('고유번호 : '|| pseq);
        dbms_output.put_line('강의실 정원 : ' || vCapacity);
        dbms_output.put_line('수정된 강의실 정원 : ' || v_tblRoom.capacity);
    
    elsif pSeq in(4,5,6) and pCapacity <=26 then                    --4,5,6 강의실은 정원이 26명         
        update tblRoom set capacity = pCapacity 
        where seq = pSeq;
        
        commit;
        
        --출력용
        select seq, capacity
        into v_tblRoom.Seq, v_tblRoom.capacity
        from tblRoom
        where seq = pSeq;
        
        dbms_output.put_line('---- 수 정 정 보 -----');
        dbms_output.put_line('고유번호 : '|| pseq);
        dbms_output.put_line('강의실정원 : ' || vCapacity);
        dbms_output.put_line('수정된 강의실 정원 : ' || v_tblRoom.capacity); 
    else
        dbms_output.put_line('1,2,3 강의실은 최대 정원 30명, 2,3,4 강의실은 정원 최대 정원 26명 입니다.');
        dbms_output.put_line('정원 내 인원을 입력해주세요');
        
    end if;
    
    
    
end;

--EXECUTE procUpdateCapacity(1,30); --테스트






-- 4.[메인] > [관리자] > [기초 정보 관리] > [교재 정보] > [교재 조회]

/* 교재 상세정보를 조회할 수 있다. */
select * from tblBook a
left outer join tblMakeSubject b
on a.seq = b.Booknum
;
create or replace procedure procBook;



--5.[메인] > [관리자] > [기초 정보 관리] > [교재 정보] > [교재 정보 수정]

/* 교재의 상세정보를 수정할 수 있다. */

create or replace PROCEDURE procUpdateBook( 
    pSeq in tblbook.seq%TYPE,
    pName in tblBook.name%TYPE,
    pWriter in tblbook.writer%TYPE,
    pPublisher in tblBook.Publisher%TYPE,
    pPrice in tblBook.price%TYPE,
    pCount in tblBook.count%TYPE
)
IS 
    vSeq tblBook.seq%TYPE;
    vName tblBook.name%TYPE;    --이전 책제목
    vWriter tblBook.writer%TYPE;    --이전 책작가
    vPublisher tblBook.publisher%TYPE;  --이전 책출판사
    vPrice tblBook.price%TYPE;      --이전 
    vCount tblBook.count%TYPE;
    vTblBook tblBook%ROWTYPE; --출력용 테이블 복사
BEGIN
    
    select seq, name, writer, publisher, price, count 
    into vSeq, vName, vWriter, vPublisher, vPrice, vCount
    from tblBook
    where seq = pSeq;
    
    update tblBook set name = pName, writer = pWriter, publisher = pPublisher, price =pPrice, count = pCount
    where seq = pSeq;
    
    commit;
    
    
      
    -- 출력용
    select seq, name, writer, publisher, price, count into vTblBook.seq, vTblBook.name, vTblBook.writer, vTblBook.publisher, vTblBook.price, vTblBook.count
    from tblBook
    where seq = pSeq;
    
    dbms_output.put_line('---- 이 전 정 보 -----');
    dbms_output.put_line('고유번호 : '|| vSeq);
    dbms_output.put_line('책제목 : ' || vName);
    dbms_output.put_line('글쓴이 : ' || vWriter);
    dbms_output.put_line('출판사 : ' || vPublisher);
    dbms_output.put_line('가격 : ' || vPrice);
    dbms_output.put_line('재고 : ' || vCount);
    
    dbms_output.put_line('---- 수 정 정 보 -----');
    dbms_output.put_line('고유번호 : '|| vTblBook.seq);
    dbms_output.put_line('책제목 : ' || vTblBook.name);
    dbms_output.put_line('글쓴이 : ' || vTblBook.writer);
    dbms_output.put_line('출판사 : ' || vTblBook.publisher);
    dbms_output.put_line('가격 : ' || vTblBook.price);
    dbms_output.put_line('재고 : ' || vTblBook.count);
    

    
end;

-- execute procUpdateBook(1, 'Java의 정석 3rd Edition', '남궁성', '도우출판', 30000, 100); --테스트




--6.[메인] > [관리자] > [기초 정보 관리] > [교재 정보] > [교재 정보 작성]

/* 교재 정보를 작성할 수 있다 */

create or replace procedure procAddBook 
(
    
    pName in tblBook.name%TYPE,         --추가할 책 제목
    pWriter in tblbook.writer%TYPE,         -- 추가할 책 작가이름
    pPublisher in tblBook.Publisher%TYPE,   --추가할 책 출판사
    pPrice in tblBook.price%TYPE,           --추가할 책 가격
    pCount in tblBook.count%TYPE            --추가할 책 재고
)
IS
    vTblBook tblBook%ROWTYPE; --출력용 테이블 복사
BEGIN   
    insert into tblBook (seq, name, writer, publisher, price, count) values (seqBook.nextval, pName, pWriter, pPublisher, pPrice, pCount);
    commit;

EXCEPTION 
	WHEN OTHERS THEN
		ROLLBACK;
END;
select * from tblbook;
--execute procAddBook ('모던 자바스크립트 입문', '이소히로시', '길벗', 29000, 600);   -- 테스트




--7.[메인] > [관리자] > [기초 정보 관리] > [교재 정보] > [교재 정보 삭제]

/* 교재 정보를 삭제할 수 있다 */

create or replace procedure procDeleteBook
(
    pSeq in tblbook.seq%TYPE        -- 시퀀스 넘버
)
IS
BEGIN
    delete from tblBOok where seq = pSeq;
    commit; 
EXCEPTION
    when others then
        rollback;
END;
--select * from tblbook;
--execute procDeleteBook(42);   --테스트


--8. [메인] > [관리자] > [개설 과목 관리] > [과정 등록]
/* 과목명과 과목명에 따른 과목 기간을 지정한다. */
create or replace procedure procTitleSubject 
(
    pName IN VARCHAR2,
    pDuration IN NUMBER
)
IS
BEGIN 
    INSERT INTO tblSubject(SEQ, NAME, DURATION) Values (seqSubject.nextVal, pName, pDuration); 
    COMMIT;
    
    EXCEPTION
    when others then
        rollback;
END;

execute procTitleSubject('초스피드 자바', 1); --test






--9. [메인] > [관리자] > [개설 과목 관리] > [등록과목 수정]
/* 과목명과 과목명에 따른 과목 기간을 수정한다. */
create or replace procedure procUpdateSubject
(
    pSeq IN NUMBER,                 --과정등록번호
    pName IN VARCHAR2,              --과정 명
    pDuration IN NUMBER             --과정 소요기간
)
IS
    vName tblSubject.name%type;                 --수정전 과목명
    vDuration tblSubject.duration%type;         --수정전 소요일
    vtblSubject tblSubject%rowtype;             --출력용 테이블
BEGIN
    select name, duration into vName, vDuration from tblSubject
    where seq = pSeq;
    
    UPDATE tblSubject set name = pName, duration = pDuration where seq = pSeq;
    COMMIT;
    
    --출력용
    SELECT name, duration into vtblSubject.name, vtblSubject.duration 
    from tblSubject where seq = pSeq; 
    
    dbms_output.put_line('---- 이 전 정 보 -----');
    dbms_output.put_line('고유번호 : '|| pSeq);
    dbms_output.put_line('과목제목 : ' || vName);
    dbms_output.put_line('소요기간 : ' || vDuration);
    
    dbms_output.put_line('---- 수 정 정 보 -----');
    dbms_output.put_line('고유번호 : '|| pSeq);
    dbms_output.put_line('과목제목 : ' || vtblSubject.name);
    dbms_output.put_line('소요기간 : ' || vtblSubject.duration);
    
    
    
END;
execute procUpdateSubject(1, '초스피드 파이선', 1);  -- 테스트
select * from tblSubject;



--10. [메인] > [관리자] > [개설 과목 관리] > [등록과목 삭제]
/* 등록과목을 삭제한다. */

create or replace procedure procDeleteSubject
(
    pSeq IN NUMBER    -- 과정 등록 번호
)
IS
BEGIN
    DELETE from tblSubject where seq = pSeq;
    commit;
    
    EXCEPTION
    when others then
        rollback;
END;

EXECUTE PROCDELETESUBJECT(41);  --테스트
SELECT * FROM TBLSUBJECT;

--11.[메인] > [관리자] > [개설 과정 관리] >[과정명 등록]
/*과정명과 과정 목적을 등록한다. */


CREATE OR REPLACE PROCEDURE procAddNameCourse
(
	pName IN tblCourse.name%type,		        --과정명
	pPurpose IN tblCourse.purpose%type 			--과정 목적(일)
)
IS
BEGIN
    INSERT INTO tblCourse (seq, name, purpose) VALUES(SEQCOURSE.nextval, pName, pPurpose);
	
    commit;

EXCEPTION 
	WHEN OTHERS THEN
		ROLLBACK;
END;
--select * from tblCourse;
--Execute procAddCourse ('java짱', 'java노잼');  --test






--12.[메인] > [관리자] > [개설 과정 관리] >[과정명 삭제]

/*개설과정을 삭제한다.. */
CREATE OR REPLACE PROCEDURE procDeleteCourseName
(
	pSeq IN NUMBER			--과정 번호
)
IS 
BEGIN
	DELETE FROM tblCourse WHERE seq = pSeq;
	COMMIT;
EXCEPTION 
	WHEN OTHERS THEN
		ROLLBACK;
END;
select*from tblCourse;
Execute procDeleteCourse(7);


--13.[메인] > [관리자] > [개설 과정 관리] >[과정명 수정]

/*과정 이름과, 목표를 수정한다.*/
select * from tblCourse;
CREATE OR REPLACE PROCEDURE procUpdateCourse (
    pSeq IN NUMBER,
    pName in tblCourse.name%type,--시퀄 번호
    pPurpose In tblcourse.purpose%type    --강의실 이름

)
IS
    vName tblcourse.name%TYPE;       --기존 개설 강의 이름
    vPurpose tblCourse.purpose%TYPE;  --기존 개설 강의 목표
    vtblCourse tblcourse%ROWTYPE;    --출력용 테이블 복사
BEGIN    
    select name, purpose
    into vName, vPurpose
    from tblCourse
    where seq = pSeq;
    
    update tblCourse set name = pName, purpose = pPurpose 
    where seq = pSeq;
   
    commit; 
       
    --출력용
    
    select name, purpose
    into vtblCourse.name, vTblCourse.purpose
    from tblCourse
    where seq = pSeq;
    
    dbms_output.put_line('---- 수 정 정 보 -----');
    dbms_output.put_line('고유번호 : '|| pSeq);
    dbms_output.put_line('과정 이름 : ' || vName);
    dbms_output.put_line('수정된 과정 이름 : ' || vtblCourse.name); 
    dbms_output.put_line('목표 : ' || vPurpose);
    dbms_output.put_line('수정된 목표 : ' || vtblCourse.purpose); 
    
    
End;

select * from tblCourse;
execute procUpdateCourse(1, '과정이름2','아무거나2');














----개설 과정 관리 시퀄

SET SERVEROUTPUT ON ; 

--1.[메인] > [관리자] > [개설 과정 관리] >[개설과정 조회]

/*개설 과정의 번호, 제목, 기간, 강의실을 출력한다. */
create or replace view vwMakeCourse
as
select *
from (select rownum, seq, name, startdate, enddate, roomnum 
from (select a.name, b.seq, b.startdate, b.enddate, c.roomnum from tblCourse a    
inner join tblmakecource b
on a.seq = b.courceNum
inner join tblroom c 
on b.roomnum = c.seq
order by b.startdate desc));

select * from vwMakeCourse;
select * from tblMakeCource;





--2.[메인] > [관리자] > [개설 과정 관리] >[개설과정 조회]>[개설 과정 과목 및 교과서 조회]
/*조회하고 싶은 과정의 교과서와, 과목을 조회한다*/
CREATE OR REPLACE PROCEDURE procSearchSubjectBook
(
    
    pCursor OUT SYS_REFCURSOR,
    pSeq IN NUMBER  -- 조회할 과정 고유 번호
    
)
IS
BEGIN
    OPEN pCursor FOR
    
    select 번호, 과정번호, 교실명, 과목명, 교과서명, 기간
    from (select rownum as 번호, 과정번호, 교실명, 과목명, 교과서명, 기간
        from (select b.seq as 과정번호, c.roomnum as 교실명, f.name as 과목명, f.duration as 기간, g.name as 교과서명  
        from tblCourse a    
        inner join tblmakecource b              
        on a.seq = b.courceNum
            inner join tblroom c 
            on b.roomnum = c.seq
                inner join tblCourceSubjectAssign d
                on d.createdcourcenum =b.seq
                    inner join tblmakesubject e
                    on d.subjectnum = e.seq
                        inner join tblsubject f
                        on e.subjectnum = f.seq
                            inner join tblbook g
                            on e.booknum = g.seq
                                where b.seq = pSeq
                                order by b.startdate desc));
        
END;


VAR pCursor REFCURSOR
EXECUTE procSearchSubjectBook(:pCursor, 2)                 
PRINT pCursor





--3.[메인] > [관리자] > [개설 과정 관리] >[수강생 조회]

/*개설 과정에 등록된 수강생을 확인 할 수 있다. 수강생 개인 정보와 수강중, 수료, 중도포기등의 상태를 확인할 수 있다.*/


CREATE OR REPLACE PROCEDURE procStudentStatus
(
   pcursor OUT SYS_REFCURSOR,
   pSeq IN NUMBER 			-- 개설 과정 번호
)
IS
BEGIN
   OPEN pcursor FOR
	SELECT
		st.seq AS 수강생번호,								            	-- 수강학생 번호
		st.name AS 수강생이름,												-- 학생 이름
		st.jumin AS 주민번호,	                                            -- 학생 주민번호 
		st.tel AS 전화번호,												    -- 학생 전화번호
		TO_CHAR(st.regdate, 'yyyy-mm-dd') AS 등록일,					    -- 등록일
		rs.registate											            -- 수료 및 중도탈락 상태
	FROM
		tblstudent st
        inner join tblRegiCource rc
        on st.seq = rc.studentnum
        inner join tblMakeCource mc
        on rc.createdcourcenum = mc.seq
            inner join tblCourse cs
            on mc.courcenum = cs.seq
                inner join tblregistate rs
                on rc.registatenum = rs.seq
      where
		mc.seq = pSeq;	                                        -- 특정 과정번호 입력
END;

VAR pcursor REFCURSOR
EXECUTE procStudentStatus(:pcursor, 24)           
PRINT pcursor





--4.[메인] > [관리자] > [개설 과정 관리] >[수강생 수강 정보 일괄 수정]

/*개설 과정에 등록된 수강생 수강정보를 일괄 수정할 수 있다. 수강중, 수료, 중도포기.*/

CREATE OR REPLACE PROCEDURE procUpdateStudentStatusEach
(
    pSeq IN NUMBER, 			-- 개설 과정 번호
    prSeq IN NUMBER         -- 수강정보 1. 수강중, 2.수료, 3.중도탈락, 4. 수강전
)
IS
BEGIN
   
	update (SELECT
		st.seq AS 수강생번호,								            	-- 수강학생 번호
		st.name AS 수강생이름,												-- 학생 이름
		st.jumin AS 주민번호,	                                            -- 학생 주민번호 
		st.tel AS 전화번호,												    -- 학생 전화번호
		TO_CHAR(st.regdate, 'yyyy-mm-dd') AS 등록일,					    -- 등록일
		rc.registatenum as 과정번호,
        rs.registate											            -- 수료 및 중도탈락 상태
        FROM
		tblstudent st
        inner join tblRegiCource rc
        on st.seq = rc.studentnum
        inner join tblMakeCource mc
        on rc.createdcourcenum = mc.seq
            inner join tblCourse cs
            on mc.courcenum = cs.seq
                inner join tblregistate rs
                on rc.registatenum = rs.seq
                where mc.seq = pSeq) set 과정번호 = prSeq;                  --과정번호 변경
               commit;                                      
END;

Execute procUpdateStudentStatus(24, 4);  --테스트







--5.[메인] > [관리자] > [개설 과정 관리] >[수강생 수강 정보 수정] x

/*개설 과정에 등록된 각각의 수강생 수강정보를 수정할 수 있다. 수강중, 수료, 중도포기.*/

CREATE OR REPLACE PROCEDURE procUpdateStudentStatus
(
    pSeq IN NUMBER, 			-- 수강생 번호.
    prSeq IN NUMBER         -- 수강정보 1. 수강중, 2.수료, 3.중도탈락, 4. 수강전
)
IS
BEGIN
   
	update (SELECT
		st.seq AS 수강생번호,								            	-- 수강학생 번호
		st.name AS 수강생이름,												-- 학생 이름
		st.jumin AS 주민번호,	                                            -- 학생 주민번호 
		st.tel AS 전화번호,												    -- 학생 전화번호
		TO_CHAR(st.regdate, 'yyyy-mm-dd') AS 등록일,					    -- 등록일
		rc.registatenum as 과정번호,
        rs.registate											            -- 수료 및 중도탈락 상태
        FROM
		tblstudent st
        inner join tblRegiCource rc
        on st.seq = rc.studentnum
        inner join tblMakeCource mc
        on rc.createdcourcenum = mc.seq
            inner join tblCourse cs
            on mc.courcenum = cs.seq
                inner join tblregistate rs
                on rc.registatenum = rs.seq
                where st.seq = pSeq) set 과정번호 = prSeq;                  --과정번호 변경
               commit;                                      
END;

execute procUpdateStudentStatus(1, 1);  --테스트







--6.[메인] > [관리자] > [개설 과정 관리] >[과정 개설]
/*과정명을 이용하여 과정을 개설한다.*/
CREATE OR REPLACE PROCEDURE procMakeCourse
(
    pStartDate IN DATE,
    pEndDate IN DATE,
    pRoomNum In NUMBER,
    pTeacherNum IN NUMBER,
    pCourceNum IN NUMBER
)
IS 


BEGIN
	INSERT INTO tblMakeCource(seq, startDate, endDate, roomNum, teacherNum, courceNum) VALUES (seqMakeCource.nextval, pStartDate, pEndDate, pRoomNum, pTeacherNum, pCourceNum);
    Commit;

END;


Execute procMakeCourse('2019-06-24','2019-07-24',2,1,6); -- test
select * from tblMakeCource;


--7.[메인] > [관리자] > [개설 과정 관리] >[과정 수정]
/*개설된 과정정보를 수정한다.(개강일, 종료일, 강의실, 선생님) */

create or replace procedure procUpdateMakeCourse
(
    pSeq IN NUMBER,
    pStartDate IN DATE,
    pENDDATE IN DATE,
    pRoomNum IN NUMBER,
    pTeacherNum IN NUMBER,
    pCourceNum IN NUMBER
)
IS
    vStartDate tblMakeCource.startdate%TYPE;            --변경전 과정시작일
    vEndDate tblMakeCource.enddate%type;                --변경전 과정종료일
    vRoomNum tblMakeCource.roomnum%type;             --변경전 강의실번호
    vTeacherNum tblMakeCource.TeacherNum%type;            --변경전 선생번호
    vCourceNum tblMakeCource.CourceNum%type;            --변경전 강의번호
    vtblMakeCource tblMakeCource%rowtype;               --출력용 테이블 복사 
BEGIN
    
    select 
    startDate, endDate, roomNum, teacherNum, courceNum INTO 
    vStartDate, vEndDate, vRoomNum, vTeacherNum, vCourceNum
    from tblMakeCource
    where seq = pSeq;
    
    update tblMakeCource set startDate = pStartDate, endDate = pEndDate, roomNum = pRoomNum, teacherNum = pTeacherNum, courceNum = pCourceNum
    where seq = pSeq;
    
    commit;
    
    --출력용
    select
    startDate, endDate, roomNum, teacherNum, courceNum INTO
    vtblMakeCource.startDate, vtblMakeCource.endDate, vtblMakeCource.roomNum, vtblMakeCource.teacherNum, vtblMakeCource.courceNum
    from tblMakeCource
    where seq = pSeq;
    
    dbms_output.put_line('---- 이 전 정 보 -----');
    dbms_output.put_line('고유번호 : '|| pSeq);
    dbms_output.put_line('시작일 : ' || vStartDate);
    dbms_output.put_line('종료일 : ' || vEndDate); 
    dbms_output.put_line('교실번호 : ' || vRoomNum);
    dbms_output.put_line('선생님번호 : ' || vTeacherNum); 
    dbms_output.put_line('개설강의번호 : ' || pCourceNum);  
    
    dbms_output.put_line('---- 수 정 정 보 -----');
    dbms_output.put_line('고유번호 : '|| pSeq);
    dbms_output.put_line('시작일 : ' || vtblMakeCource.StartDate);
    dbms_output.put_line('종료일 : ' || vtblMakeCource.EndDate); 
    dbms_output.put_line('교실번호 : ' || vtblMakeCource.RoomNum);
    dbms_output.put_line('선생님번호 : ' || vtblMakeCource.TeacherNum); 
    dbms_output.put_line('개설강의번호 : ' || vtblMakeCource.CourceNum);  
    
END;
select * from tblMakeCource;
execute procUpdateMakeCourse(26,'2030-06-24','2031-06-24', 1,3,1);    -- 테스트 


--8.[메인] > [관리자] > [개설 과정 관리] >[과정 삭제] 
/* 개설 과정을 삭제한다.*/
CREATE OR REPLACE PROCEDURE procDeleteMakeCource
(
    pSeq IN NUMBER
)   
IS
BEGIN
       
    DELETE FROM tblMakeCource where seq = pSeq;
    Commit;
EXCEPTION
	WHEN OTHERS THEN
		ROLLBACK;

END;
select * from tblMakeCource;

Execute procDeleteMakeCource(26);   --테스트
















--개설 과목 관리 

--1. [메인] > [관리자] > [개설 과목 관리] > [개설 과목 조회]
/*개설과목명, 과목 일수, 과목 상태(진행중, 종료, 진행전), 강사이름, 책, 책 분배상태를 조회할 수 있다.*/
create or replace view vwMakeSubject
as
select 
ms.seq as 번호,
sj.name as 과목명,
sj.duration as 일수,
case
when enddate < to_char(sysdate, 'yy/mm/dd') then '과목종료'
when enddate > to_char(sysdate, 'yy/mm/dd') and startdate >= to_char(sysdate, 'yy/mm/dd') then '개설전'
when enddate >= to_char(sysdate, 'yy/mm/dd') and startdate < to_char(sysdate, 'yy/mm/dd') then '진행중'
end as 과목상태,
tc.name as 교사명,
bo.name as 책제목,
ms.bookdistristate as 분배상태
from tblSubject sj 
inner join tblMakeSubject ms
on sj.seq = ms.subjectnum
inner join tblTeacherSubjectList ts
on ms.subjectnum = ts.subjectnum
inner join tblTeacher tc
on tc.seq = ts.teachernum
inner join tblBook bo
on ms.booknum = bo.seq
order by ms.seq desc;


select * from vwMakeSubject;
commit;



select * from tblMakeSubject;

--2. [메인] > [관리자] > [개설 과목 관리] > [과목 개설]
/* 과목을 개설한다. */
create or replace procedure procMakeSubject
(
    pSubjectNum IN NUMBER,         --과목명 번호
    pSTARTDATE IN DATE,            --과목 시작일
    pENDDATE IN DATE,              --과목 종료일
    pBookNum IN VARCHAR2           --교과서 
    
)
IS
BEGIN
    
    if pstartdate < penddate then  --과목 종료일이 시작일 보다 미래인 경우에만 개설이 가능
    INSERT INTO tblMakeSubject (seq, subjectNum, startDate, endDate, bookNum, bookdistristate) 
        values (seqMakeSubject.nextVal, pSubjectNum, pSTARTDATE, pENDDATE, pBookNum, '미완');  --교과서는 분배되지 않은 상태로 한다.
    commit;
    
    
    else 
    dbms_output.put_line('입력하신 과목의 기간이 유효하지 않습니다 다시 입력해 주세요');
    commit;
    
    end if;
END;

execute procMakeSubject(4,'20/12/30','21/03/04', 30);  --test
select * from tblMakeSubject;





--4. [메인] > [관리자] > [개설 과목 관리] > [개설 과목 수정]
/*개설된 과목을 수정한다. 수정할 수 있는 정보는 과목명, 과목 시작일, 교과서*/

create or replace procedure procUdateMakeSubject
(
    pSeq IN NUMBER,                --수정할 개설 과목 번호
    pSubjectNum IN NUMBER,         --과목명 번호
    pSTARTDATE IN DATE,            --과목 시작일
    pENDDATE IN DATE,              --과목 종료일
    pBookNum IN NUMBER
    
)
IS
BEGIN
    if pstartdate < penddate then
    
    Update tblMakeSubject 
        set subjectnum = pSubjectNum, startDate = pSTARTDATE, ENDDATE = pENDDATE, bookNum = pBookNum
        where seq = pSeq;
    commit;  
       
    else 
    dbms_output.put_line('입력하신 과목의 기간이 유효하지 않습니다 다시 입력해 주세요');
    commit;
    
    end if;
END;

execute procUdateMakeSubject(161, 20, '20/06/02', '20/06/04', 3)




--5. [메인] > [관리자] > [개설 과목 관리] > [개설 과목 삭제]
/* 개설된 과목을 과목 번호로 삭제한다. */

create or replace procedure procDeleteMakeSubject
(
    pSeq IN NUMBER
)
IS
BEGIN
    DELETE FROM tblMakeSubject where seq = pseq;
    commit;
    
    exception
    when others then
        rollback;


END;

select * from tblMakeSubject;
execute procdeleteMakeSubject(161);   --테스트

--6. [메인] > [관리자] > [개설 과목 관리] > [과정 선택]
/*과정과 과목을 연계할 수 있다.*/
create or replace procedure procSelectCourse
(
pSeq IN NUMBER,
pSeq2 IN NUMBER
)
IS
BEGIN
    insert into tblCourceSubjectAssign (seq, createdcourcenum, subjectnum) 
    values (SEQCOURCESUBJECTASSIGN.nextval, pseq, pseq2);
    commit;


END;


execute procSelectCourse(14,2); --test
--select * from tblmakecource mc
--    inner join tblcourcesubjectassign sa
--    on mc.seq = sa.createdcourcenum 
--    inner join tblmakesubject ms
--    on ms.seq = sa.subjectnum
--    inner join tblSubject su
--    on su.seq = ms.subjectnum



--7. [메인] > [관리자] > [개설 과목 관리] > [과정 연계 삭제]
/*과정과 과목 연계를 삭제 할 수 있다.*/
create or replace procedure ProcDeleteSubjectConnected
(
    pSeq IN NUMBER
)
IS
BEGIN
    delete from tblCourceSubjectAssign where seq = pSeq;
    commit;
END;

execute ProcDeleteSubjectConnected(161); -- test

--8. [메인] > [관리자] > [개설 과목 관리] > [책 분배]
/*책 분배가 완료되었는지 안되었는지 지정할 수 있다.*/
create or replace procedure procBookDistristate
(
    pSeq IN NUMBER,
    pState IN VARCHAR2
)
IS
BEGIN
    update tblMakeSubject set bookdistristate = pState where seq = pseq;
    commit;
END;
    
select * from tblmakeSubject;
Execute procBookDistristate(21, '완료');
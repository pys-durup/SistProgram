-- tblRegistate_수강상태
-- 수강상태(번호, 수강상태(수강중, 수료, 중도탈락) 구글드라이브 기본데이터 참고)
select * from tblRegistate;

insert into tblRegistate (seq, regiState) values (1, '수강중');
insert into tblRegistate (seq, regiState) values (2, '수료');
insert into tblRegistate (seq, regiState) values (3, '중도탈락');
insert into tblRegistate (seq, regiState) values (4, '수강전');
-- tblConsultationReason_상담사유 

select * from tblConsultationReason; 
insert into tblConsultationReason (seq, reason) values (seqConsultationReason.nextval, '포트폴리오');
insert into tblConsultationReason (seq, reason) values (seqConsultationReason.nextval, '취업');
insert into tblConsultationReason (seq, reason) values (seqConsultationReason.nextval, '수업');
insert into tblConsultationReason (seq, reason) values (seqConsultationReason.nextval, '면접');
insert into tblConsultationReason (seq, reason) values (seqConsultationReason.nextval, '기타');
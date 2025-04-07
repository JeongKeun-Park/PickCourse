CREATE SEQUENCE SEQ_QUESTION;
CREATE TABLE TBL_QUESTION (
                              ID NUMBER CONSTRAINT PK_QEUSTION PRIMARY KEY,
                              QUESTION_CONTENT VARCHAR2(1000) NOT NULL,
                              MEMBER_ID NUMBER NOT NULL,
                              PLAN_ID NUMBER NOT NULL,
                              CREATE_DATE DATE DEFAULT SYSDATE,
                              CONSTRAINT FK_QEUSTION_MEMBER FOREIGN KEY(MEMBER_ID)
                              REFERENCES TBL_MEMBER(ID) ON DELETE CASCADE,
                              CONSTRAINT FK_QEUSTION_PLAN FOREIGN KEY(PLAN_ID)
                              REFERENCES TBL_PLAN(ID) ON DELETE CASCADE
);

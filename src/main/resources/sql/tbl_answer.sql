CREATE SEQUENCE SEQ_ANSWER;
CREATE TABLE TBL_ANSWER (
                            ID NUMBER CONSTRAINT PK_ANSWER PRIMARY KEY,
                            ANSWER_CONTENT VARCHAR2(2000) NOT NULL,
                            MEMBER_ID NUMBER NOT NULL,
                            QUESTION_ID NUMBER NOT NULL,
                            PLAN_ID NUMBER NOT NULL,
                            CONSTRAINT FK_ANSWER_MEMBER FOREIGN KEY(MEMBER_ID)
                            REFERENCES TBL_MEMBER(ID) ON DELETE CASCADE ,
                            CONSTRAINT FK_ANSWER_QEUSTION FOREIGN KEY(QUESTION_ID)
                            REFERENCES TBL_QUESTION(ID) ON DELETE CASCADE,
                            CONSTRAINT FK_ANSWER_PLAN FOREIGN KEY (PLAN_ID)
                            REFERENCES TBL_PLAN(ID) ON DELETE CASCADE
);


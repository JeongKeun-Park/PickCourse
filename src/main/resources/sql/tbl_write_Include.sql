CREATE SEQUENCE SEQ_WRITE_INCLUDE;
CREATE TABLE TBL_WRITE_INCLUDE (
                                   ID NUMBER CONSTRAINT PK_WRITE_INCLUDE PRIMARY KEY,
                                   INCLUDE_CONTENT VARCHAR2(1000) DEFAULT '',
                                   PLAN_ID NUMBER NOT NULL,
                                   CONSTRAINT FK_INCLUDE_PLAN FOREIGN KEY (PLAN_ID)
                                   REFERENCES TBL_PLAN(ID) ON DELETE CASCADE
);

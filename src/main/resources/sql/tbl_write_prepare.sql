CREATE SEQUENCE SEQ_WRITE_PREPARE;
CREATE TABLE TBL_WRITE_PREPARE (
                                   ID NUMBER CONSTRAINT PK_WRITE_PREPARE PRIMARY KEY,
                                   PREPARE_CONTENT VARCHAR2(1000) DEFAULT '',
                                   PLAN_ID NUMBER NOT NULL,
                                   CONSTRAINT FK_PREPARE_PLAN FOREIGN KEY (PLAN_ID)
                                   REFERENCES TBL_PLAN(ID) ON DELETE CASCADE
);

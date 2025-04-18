CREATE SEQUENCE SEQ_REAL_FILE;
CREATE TABLE TBL_REAL_FILE(
                              ID NUMBER CONSTRAINT PK_REAL_FILE PRIMARY KEY,
                              FEED_ID NUMBER NOT NULL,
                              CONSTRAINT FK_REAL_FILE_FILE FOREIGN KEY(ID)
                              REFERENCES TBL_FILE(ID) ON DELETE CASCADE ,
                              CONSTRAINT FK_REAL_FILE_REAL_FEED FOREIGN KEY(FEED_ID)
                              REFERENCES TBL_REAL_FEED(ID) ON DELETE CASCADE
);

COMMENT ON TABLE TBL_REAL_FILE IS '실제 피드 파일 테이블';
COMMENT ON COLUMN TBL_REAL_FILE.ID IS '실제 파일의 고유 ID';
COMMENT ON COLUMN TBL_REAL_FILE.FEED_ID IS '실제 피드의 고유 ID';
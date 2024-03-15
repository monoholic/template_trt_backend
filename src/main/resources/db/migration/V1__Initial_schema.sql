CREATE TABLE TBSY_USER_INFO (
    USER_ID VARCHAR(30) NOT NULL,
    PASSWD VARCHAR(100) NOT NULL,
    USER_NM VARCHAR(20) NOT NULL,
    SAWON_NO VARCHAR(7),
    USE_YN VARCHAR(1),
    REG_DATE DATE,
    UPT_DATE DATE,
    REG_ID VARCHAR(30),
    UPT_ID VARCHAR(30),
    PRIMARY KEY (USER_ID)
);

CREATE TABLE TBSY_SAWON_INFO (
    SAWON_NO VARCHAR(7) NOT NULL,
    SAWON_GBN VARCHAR(10) NOT NULL,
    SAWON_NM VARCHAR(20) NOT NULL,
    DEPT_CD VARCHAR(10),
    JIKGYUB VARCHAR(10) NOT NULL,
    JIKCHECK VARCHAR(50),
    IN_DATE VARCHAR(8) NOT NULL,
    OT_DATE VARCHAR(8),
    BT_DATE VARCHAR(8),
    SEX_GB VARCHAR(1) NOT NULL,
    HP_TEL VARCHAR(20),
    TEL_NO VARCHAR(20),
    POST_NO VARCHAR(6),
    ADDR1 VARCHAR(200),
    ADDR2 VARCHAR(1),
    TEAM_YN VARCHAR(1),
    REG_DATE DATE,
    UPT_DATE DATE,
    REG_ID VARCHAR(30),
    UPT_ID VARCHAR(30),
    PRIMARY KEY(SAWON_NO)
);
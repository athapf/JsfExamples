--
-- Create table T_CUSTOMER
--
CREATE TABLE T_CUSTOMER (
  ID DECIMAL(18,0) NOT NULL,
  NAME VARCHAR(32),
  NUMBER DECIMAL(10,0),
  RANGE CHAR(2),
  OPENING DATE,
  PRIMARY KEY (ID)
);

CREATE SEQUENCE SEQ_ID_CUSTOMER START WITH 1000000 INCREMENT BY 10;


CREATE FUNCTION IDENT (NUMBER DECIMAL(10,0), RANGE CHAR(2), OPENING DATE)
  RETURNS VARCHAR(64)
  LANGUAGE SQL
  CONTAINS SQL
  NO EXTERNAL ACTION
  DETERMINISTIC
  BEGIN
    IF OPENING IS NULL THEN
      RETURN NUMBER CONCAT '+' CONCAT RANGE CONCAT '+' CONCAT '-';
    ELSE
      RETURN NUMBER CONCAT '+' CONCAT RANGE CONCAT '+' CONCAT CHAR(OPENING);
    END IF;
  END;

CREATE VIEW V_CUSTOMER AS
SELECT ID, IDENT(NUMBER, RANGE, OPENING) AS IDENT FROM T_CUSTOMER;

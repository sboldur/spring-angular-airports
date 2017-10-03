DROP TABLE IF EXISTS COUNTRY;
CREATE TABLE COUNTRY(
  id INT PRIMARY KEY NOT NULL ,
  code VARCHAR(2) UNIQUE NOT NULL ,
  name VARCHAR(255) UNIQUE,
  continent VARCHAR(2)
) AS SELECT id,code,name,continent FROM CSVREAD('classpath:/csvFiles/countries.csv');
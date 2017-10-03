DROP TABLE IF EXISTS AIRPORT;
CREATE TABLE AIRPORT (
  id           INT PRIMARY KEY NOT NULL ,
  ident        VARCHAR(10) NOT NULL ,
  type         VARCHAR(255),
  name         VARCHAR(255),
  continent    VARCHAR(2),
  iso_country  VARCHAR(2) NOT NULL ,
  iso_region   VARCHAR(10) NOT NULL ,
  municipality VARCHAR(255),
  FOREIGN KEY (iso_country) references public.COUNTRY(code) ON UPDATE CASCADE
) AS  SELECT
    id,
    ident,
    type,
    name,
    continent,
    iso_country,
    iso_region,
    municipality
  FROM CSVREAD('classpath:/csvFiles/airports-test.csv');
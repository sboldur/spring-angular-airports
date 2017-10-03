DROP TABLE IF EXISTS RUNWAY;
CREATE TABLE RUNWAY (
  id            INT PRIMARY KEY NOT NULL ,
  airport_ref   INT NOT NULL ,
  airport_ident VARCHAR(10) NOT NULL ,
  surface       VARCHAR(255),
  le_ident      VARCHAR(5),
  FOREIGN KEY (airport_ref) references public.AIRPORT(id) ON UPDATE CASCADE
) AS  SELECT
    id,
    airport_ref,
    airport_ident,
    surface,
    le_ident
  FROM CSVREAD('classpath:/csvFiles/runways.csv');
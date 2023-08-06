create table clearing_cost(
  id int not null AUTO_INCREMENT,
  issuing_country varchar(400) not null,
  clearing_cost decimal(1000, 3),
  PRIMARY KEY ( ID )
);
CREATE TABLE book (
  code int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  pages int(11) NOT NULL,
  genre varchar(50) NOT NULL,
  author varchar(100) NOT NULL,
  PRIMARY KEY (code)
);

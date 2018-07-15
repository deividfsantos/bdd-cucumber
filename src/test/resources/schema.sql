CREATE TABLE book (
  code int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  pages int(11) NOT NULL,
  genre varchar(50) NOT NULL,
  author varchar(100) NOT NULL,
  stock int(5) NOT NULL,
  PRIMARY KEY (code)
);

CREATE TABLE client (
  code int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  phone varchar(20) NOT NULL,
  nationality varchar(2) NOT NULL,
  PRIMARY KEY (code)
);

create table rental(
	code integer NOT NULL AUTO_INCREMENT,
    client_code int NOT NULL,
    book_code int NOT NULL,
    PRIMARY KEY (code),
    FOREIGN KEY (client_code) REFERENCES client(code),
    FOREIGN KEY (book_code) REFERENCES book(code)
);
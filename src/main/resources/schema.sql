CREATE  DATABASE IF NOT EXISTS Moneytrack;
USE Moneytrack;

CREATE TABLE IF NOT EXISTS Category(

    id VARCHAR (45) NOT NULL,
    PRIMARY KEY (id)

    );

CREATE TABLE IF NOT EXISTS Entry(

    id VARCHAR (60) NOT NULL,
    username VARCHAR(45) NOT NULL,
    type VARCHAR (7) NOT NULL,
    category VARCHAR (45) NOT NULL,
    description VARCHAR (45) NOT NULL,
    amount DECIMAL (10,2) NOT NULL,
    date DATE NOT NULL,
    lastUpdate DATETIME NOT NULL,
    isDeleted BIT NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT FK_Category_Entry FOREIGN KEY (category) REFERENCES Category(id)

    );
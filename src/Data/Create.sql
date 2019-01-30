
CREATE DATABASE QUANLIDIEMHOCSINH;

USE QUANLIDIEMHOCSINH;

CREATE TABLE Person(
    ID varchar(10) not null,
    Name varchar(100) not null,
    NgaySinh DATE not null,
    GioiTinh varchar(5) not null,
    Address varchar(50) not null,
    Email varchar(50) not null,
    Phone varchar(20) not null,
    Image varchar(100),
    PRIMARY KEY (ID)
);


CREATE TABLE Teacher(
    ID_Teacher varchar(10) not null,
    Mon varchar(5) not null,
    Khoi varchar(5) not null,
    FOREIGN KEY (ID_Teacher) REFERENCES Person (ID),
    PRIMARY KEY (ID_Teacher)
);

CREATE TABLE Student(
    ID_Student varchar(10) not null,
    Class varchar(10) not null,
    FOREIGN KEY (ID_Student) REFERENCES Person (ID),
    PRIMARY KEY(ID_Student)
);

CREATE TABLE User(
    ID varchar(5) not null,
    userName varchar(30) not null,
    password varchar(20) not null,
    role varchar(5) not null,
    avatar varchar(100),
    FOREIGN KEY (ID) REFERENCES Person (ID),
    PRIMARY KEY (userName)
);

CREATE TABLE Diem(
    ID_Student varchar(5) not null,
    Mon varchar(10) not null,
    KTM varchar(20),
    KTMuoiLam varchar(20),
    KTMotTietMot varchar(5),
    KTMotTietHai varchar(5),
    Hk varchar(5) ,
    DiemTB varchar(50) ,
    FOREIGN KEY (ID_Student) REFERENCES Person (ID),
    PRIMARY KEY(ID_Student,Mon)
);

CREATE TABLE Lop(
    Class varchar(10) NOT NULL,
    SoLuong INT NOT NULL,
    ChuNhiem varchar(50) NOT NULL,
    FOREIGN KEY (ChuNhiem) REFERENCES Person (ID),
    PRIMARY KEY(Class)
);

INSERT INTO Lop
VALUES 
    ('6A',10,'T604'),
    ('6B',10,'T607'),
    ('6C',10,'T608'),
    ('7A',10,'T709'),
    ('7B',10,'T704'),
    ('7C',10,'T706'),
    ('8A',10,'T807'),
    ('8B',10,'T803'),
    ('8C',10,'T804'),
    ('9A',10,'T902'),
    ('9B',10,'T901'),
    ('9C',10,'T908');




SELECT person.ID, person.Name FROM person, student WHERE person.ID = student.ID_Student AND student.class = '6A';
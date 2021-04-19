CREATE TABLE IF NOT EXISTS Organization (
    id         INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL COMMENT 'Сокращенное название организации',
    full_name  VARCHAR(255) NOT NULL COMMENT 'Полное название организации',
    inn        VARCHAR(12)  NOT NULL COMMENT 'ИНН' UNIQUE,
    kpp        VARCHAR(9)   NOT NULL COMMENT 'КПП',
    address    VARCHAR(255) NOT NULL COMMENT 'Юридический адрес',
    phone      VARCHAR(16)           COMMENT 'Телефон',
    is_active  BOOLEAN  DEFAULT true COMMENT 'Статус',
    version    INTEGER      NOT NULL COMMENT 'Служебное поле hibernate'
);

CREATE TABLE IF NOT EXISTS Office (
    id         INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    org_id     INTEGER      NOT NULL COMMENT 'Идентификатор организации',
    name       VARCHAR(255)          COMMENT 'Название офиса',
    address    VARCHAR(255)          COMMENT 'Адрес офиса',
    phone      VARCHAR(16)           COMMENT 'Телефон',
    is_active  BOOLEAN DEFAULT true  COMMENT 'Статус',
    version    INTEGER      NOT NULL COMMENT 'Служебное поле hibernate'
);


CREATE TABLE IF NOT EXISTS User (
    id               INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    office_id        INTEGER      NOT NULL COMMENT 'Идентификатор офиса',
    doc_id           INTEGER               COMMENT 'Идентификатор документа',
    citizenship_code INTEGER               COMMENT 'Код гражданства',
    first_name       VARCHAR(255) NOT NULL COMMENT 'Имя',
    second_name      VARCHAR(255)          COMMENT 'Фамилия',
    middle_name      VARCHAR(255)          COMMENT 'Отчество',
    position         VARCHAR(255) NOT NULL COMMENT 'Должность',
    phone            VARCHAR(16)           COMMENT 'Телефон',
    is_identified    BOOLEAN DEFAULT false COMMENT 'Проверка проведена',
    version          INTEGER      NOT NULL COMMENT 'Служебное поле hibernate'
);

CREATE TABLE IF NOT EXISTS Doc (
    id     INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    code   INTEGER     NOT NULL COMMENT 'Код документа',
    number VARCHAR(50) NOT NULL COMMENT 'Номер документа',
    date   DATE        NOT NULL COMMENT 'Дата документа',
);

CREATE TABLE IF NOT EXISTS Doc_type (
    code       INTEGER              COMMENT 'Код документа' PRIMARY KEY,
    name       VARCHAR(50) NOT NULL COMMENT 'Название документа',
);

CREATE TABLE IF NOT EXISTS Сitizenship (
    code       INTEGER              COMMENT 'Код страны' PRIMARY KEY,
    name       VARCHAR(50) NOT NULL COMMENT 'Название страны',
);

CREATE INDEX IX_Organization_Name ON Organization(name);

CREATE INDEX IX_Office_Org_Id ON Office(org_id);
ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization(id);

CREATE INDEX IX_User_Office_Id        ON User(office_id);
CREATE INDEX IX_User_Doc_Id           ON User(doc_id);
CREATE INDEX IX_User_Citizenship_Code ON User(citizenship_code);
ALTER TABLE User ADD FOREIGN KEY (office_id)        REFERENCES Office(id);
ALTER TABLE User ADD FOREIGN KEY (doc_id)           REFERENCES Doc(id);
ALTER TABLE User ADD FOREIGN KEY (citizenship_code) REFERENCES Сitizenship(code);

CREATE INDEX IX_Doc_Code ON Doc(code);
ALTER TABLE Doc ADD FOREIGN KEY (code) REFERENCES Doc_type(code);
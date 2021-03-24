INSERT INTO Organization (id, name, full_name, inn, kpp, address, version) VALUES 
(1, 'Рога', 'Общество с ограниченной ответственностью Рога', '778944556611','111111111','ул.Лунина, 7',0),
(2, 'Копыта', 'Общество с ограниченной ответственностью Копыта', '488944556611','111111111','ул.Лунина, 7',0),
(3, 'Гольфстрим', 'Общество с ограниченной ответственностью Гольфстрим', '518944556611','111111211','ул.Героев, 107',0),
(4, 'Достаток', 'Общество с ограниченной ответственностью Достаток', '238944556611','111113111','ул.Базарная, 1',0),
(5, 'Торус', 'Общество с ограниченной ответственностью Торус', '237744556611','111771111','ул.Новороссийская, 188',0);

INSERT INTO Office (id, org_id, name, address, version) VALUES
(1, 1, '102', 'ул.Лунина, 7', 0),
(2, 1, '103', 'ул.Лунина, 7', 0),
(3, 1, '104', 'ул.Лунина, 7', 0),
(4, 1, '105', 'ул.Лунина, 7', 0),
(5, 2, '140', 'ул.Лунина, 7', 0),
(6, 3, 'Первый этаж', 'ул.Героев, 107', 0),
(7, 3, 'Второй этаж', 'ул.Героев, 107', 0),
(8, 4, 'Павильон 207', 'ул.Базарная, 1', 0),
(9, 4, 'оф.208', 'ул.Складская, 9', 0);

INSERT INTO Doc_type (name, code) VALUES
('Паспорт гражданина РФ', 21),
('Удостоверение беженца', 13),
('Вид на жительство в РФ', 12),
('Паспорт иностранного гражданина', 10),
('Военный билет', 07);

INSERT INTO Сitizenship (name, code) VALUES
('Российская Федерация', 643),
('Казахстан', 976),
('Грузия', 319);

INSERT INTO Doc (id, code, number, date) VALUES
(1, 12, '4444 555666', '2010-10-25'),
(2, 10, '3333 111222', '2015-10-25'),
(3, 10, 'уу 56123987', '2020-01-05'),
(5, 13, '6546 231531', '2017-12-25'),
(6, 13, '1538 211444', '2010-10-21'),
(7, 10, '5410 123153', '2001-06-06'),
(8, 21, '4087 098654', '2005-06-05'),
(9, 21, '4422 568942', '2011-11-11'),
(4, 07, '555444', '2012-12-12');

INSERT INTO User (office_id, first_name, position, version) VALUES
(1,'Микола', 'Слесарь', 0),
(1,'Ангелина', 'Офис-менеджер', 0),
(1,'Александр', 'Курьер', 0);

INSERT INTO User (office_id, doc_id, citizenship_code, first_name, second_name, middle_name, position, phone, is_identified, version) VALUES
(2, 1, 976, 'Рустам', 'Казахов', 'Рустамович', 'Верстальщик', '+7(900)000-00-11', true,0),
(3, 2, 976, 'Жанбек', 'Ибадулы', 'Жанбекович', 'Водитель', '+7(910)222-00-11', true,0),
(4, 3, 976, 'Искандер', 'Махамбетов', 'Искандерочич', 'Завхоз', '+7(915)717-02-16', true,0),
(6, 4, 643, 'Алексей', 'Стрельников', 'Иванович', 'Сотрудник охраны', '+7(911)656-42-13', true,0),
(7, 5, 319, 'Сулико', 'Тереберидзе', 'Витальевна', 'Управляющая', '+7(922)491-45-34', true,0),
(7, 6, 319, 'Гиви', 'Гелашвили', 'Гочаевич', 'Повар', '+7(922)491-45-35', true,0),
(7, 7, 319, 'Отар', 'Алиев', 'Ревазович', 'Закупщик', '+7(922)491-45-36', true,0),
(8, 8, 643, 'Владимир', 'Мрачков', 'Витальевич', 'Дизайнер', '+7(910)656-45-16', true,0),
(9, 9, 643, 'Константин', 'Елпанов', 'Семёнович', 'Заместитель директора', '+7(910)343-55-92', true,0);
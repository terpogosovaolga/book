CREATE TABLE `users` (
                         `User_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                         `Email` varchar(100) NOT NULL,
                         `Password` varchar(100) NOT NULL,
                         PRIMARY KEY (`User_id`),
                         UNIQUE KEY `User_id_UNIQUE` (`User_id`),
                         UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
create table `roles` (
   `id` int(10) unsigned NOT NULL AUTO_INCREMENT primary key,
    `Name` varchar(100) NOT NULL,
    unique key `id_unique` (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
create table `users_roles` (
                         `user_id` int(10) unsigned NOT NULL,
                         `role_id` int(10) unsigned NOT NULL,
                         foreign key (`user_id`) references users (User_id),
                         foreign key (`role_id`) references roles (id),
                         unique key `id_unique` (`user_id`, `role_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `books` (
                         `Book_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                         `Author_name` varchar(100) NOT NULL,
                         `Author_secondname` varchar(100) DEFAULT NULL,
                         `Author_surname` varchar(100) NOT NULL,
                         `Book_name` varchar(300) NOT NULL,
                         `Year_of_writing` int(11) DEFAULT NULL,
                         `Publisher` varchar(45) NOT NULL,
                         `Year_of_publishing` int(11) NOT NULL,
                         `Translater` varchar(200) DEFAULT NULL,
                         `Pages_number` int(11) DEFAULT NULL,
                         `Genre` varchar(45) DEFAULT NULL,
                         `Original_language` varchar(45) NOT NULL,
                         `Language` varchar(45) DEFAULT NULL,
                         `Price` double NOT NULL,
                         `Count` int(11) NOT NULL,
                         `Description` text,
                         `Number_of_watchings` int(11) NOT NULL DEFAULT '0',
                         PRIMARY KEY (`Book_id`),
                         UNIQUE KEY `Book_id_UNIQUE` (`Book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

CREATE TABLE `baskets` (
                           `Basket_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                           `User_id` int(10) unsigned DEFAULT NULL,
                           `Cost` double DEFAULT NULL,
                           `Date_of_purchase` date DEFAULT NULL,
                           `Delievered` binary(2) DEFAULT NULL,
                           PRIMARY KEY (`Basket_id`),
                           UNIQUE KEY `Basket_id_UNIQUE` (`Basket_id`),
                           CONSTRAINT `Users` FOREIGN KEY (`User_id`) REFERENCES `users` (`User_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8;

CREATE TABLE `basketparagraphs` (
                                    `BasketParagraph_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                                    `Basket_id` int(10) unsigned NOT NULL,
                                    `Book_id` int(10) unsigned NOT NULL,
                                    `Count` int(11) NOT NULL DEFAULT '1',
                                    `Cost` double DEFAULT NULL,
                                    PRIMARY KEY (`BasketParagraph_id`),
                                    UNIQUE KEY `BasketParagraph_id_UNIQUE` (`BasketParagraph_id`) /*!80000 INVISIBLE */,
                                    KEY `Basket_id_idx` (`Basket_id`),
                                    KEY `BasketParagraph_Book_idx` (`Book_id`),
                                    CONSTRAINT `BasketParagraph_Basket` FOREIGN KEY (`Basket_id`) REFERENCES `baskets` (`Basket_id`),
                                    CONSTRAINT `BasketParagraph_Book` FOREIGN KEY (`Book_id`) REFERENCES `books` (`Book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8;

INSERT INTO `my_book_shop`.`users`
(
 `Password`)
VALUES
(   'пользователь',
    'польз',
    'user@mail.ru',
    'user');
INSERT INTO `my_book_shop`.`users`
(
  `Password`)
VALUES
(   'админ',
    'адм',
    'admin@mail.ru',
    'admin');


INSERT INTO `my_book_shop`.`roles`
(
 `Name`)
VALUES
('ROLE_USER');

INSERT INTO `my_book_shop`.`roles`
(
    `Name`)
VALUES
('ROLE_ADMIN');

select * from users;
select * from users_roles;
INSERT INTO `my_book_shop`.`users_roles`
(`user_id`,
 `role_id`)
VALUES
(1,1);
INSERT INTO `my_book_shop`.`users_roles`
(`user_id`,
 `role_id`)
VALUES
(2,2);

INSERT INTO `my_book_shop`.`books`
( `Author_name`,
 `Author_secondname`,
 `Author_surname`,
 `Book_name`,
 `Year_of_writing`,
 `Publisher`,
 `Year_of_publishing`,
 `Pages_number`,
 `Genre`,
 `Original_language`,
 `Price`,
 `Count`,
 `Description`,
 `Number_of_watchings`)
VALUES
(    'Лев',
     'Николаевич',
     'Толстой',
     'Война и мир: Том I',
     1863,
     'Эксмо',
    2018,
    450,
     'роман-эпопея',
     'русский',
    590,
    389,
    'Бессмертная классика великого русского писателя',
     1290);


INSERT INTO `my_book_shop`.`books`
( `Author_name`,
  `Author_secondname`,
  `Author_surname`,
  `Book_name`,
  `Year_of_writing`,
  `Publisher`,
  `Year_of_publishing`,
  `Pages_number`,
  `Genre`,
  `Original_language`,
  `Price`,
  `Count`,
  `Description`,
  `Number_of_watchings`)
VALUES
(    'Лев',
     'Николаевич',
     'Толстой',
     'Война и мир: Том II',
     1863,
     'Эксмо',
     2018,
     450,
     'роман-эпопея',
     'русский',
     590,
     389,
     'Бессмертная классика великого русского писателя',
     1290);


INSERT INTO `my_book_shop`.`books`
( `Author_name`,
  `Author_secondname`,
  `Author_surname`,
  `Book_name`,
  `Year_of_writing`,
  `Publisher`,
  `Year_of_publishing`,
  `Pages_number`,
  `Genre`,
  `Original_language`,
  `Price`,
  `Count`,
  `Description`,
  `Number_of_watchings`)
VALUES
(    'Лев',
     'Николаевич',
     'Толстой',
     'Война и мир: Том III',
     1863,
     'Эксмо',
     2018,
     450,
     'роман-эпопея',
     'русский',
     590,
     389,
     'Бессмертная классика великого русского писателя',
     1290);


INSERT INTO `my_book_shop`.`books`
( `Author_name`,
  `Author_secondname`,
  `Author_surname`,
  `Book_name`,
  `Year_of_writing`,
  `Publisher`,
  `Year_of_publishing`,
  `Pages_number`,
  `Genre`,
  `Original_language`,
  `Price`,
  `Count`,
  `Description`,
  `Number_of_watchings`)
VALUES
(    'Лев',
     'Николаевич',
     'Толстой',
     'Война и мир: Том IV',
     1863,
     'Эксмо',
     2018,
     450,
     'роман-эпопея',
     'русский',
     590,
     389,
     'Бессмертная классика великого русского писателя',
     1290);

INSERT INTO `my_book_shop`.`books`
( `Author_name`,
  `Author_secondname`,
  `Author_surname`,
  `Book_name`,
  `Year_of_writing`,
  `Publisher`,
  `Year_of_publishing`,
  `Pages_number`,
  `Genre`,
  `Original_language`,
  `Price`,
  `Count`,
  `Description`,
  `Number_of_watchings`)
VALUES
(    'Федор',
     'Михаилович',
     'Достоевский',
     'Преступление и наказание',
     1866,
     'Эксмо',
     2016,
     450,
     'роман',
     'русский',
     435,
     457,
     'Бессмертная классика великого русского писателя',
     870);

INSERT INTO `my_book_shop`.`books`
( `Author_name`,
  `Author_secondname`,
  `Author_surname`,
  `Book_name`,
  `Year_of_writing`,
  `Publisher`,
  `Year_of_publishing`,
  `Pages_number`,
  `Genre`,
  `Original_language`,
  `Price`,
  `Count`,
  `Description`,
  `Number_of_watchings`)
VALUES
(    'Владимир',
     'Владимирович',
     'Маяковский',
     'Сборник лучших стихов',
     1910,
     'Просвещение',
     2015,
     123,
     'стихи',
     'русский',
     324,
     478,
     'Стихи, любимые многими',
     278);

ALTER TABLE `users` CHANGE COLUMN `email` `username` varchar(100) NOT NULL




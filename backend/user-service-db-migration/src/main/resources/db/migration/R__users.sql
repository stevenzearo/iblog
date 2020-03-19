CREATE TABLE IF NOT EXISTS `users`(
  `id`                      INT             NOT NULL AUTO_INCREMENT,
  `name`                    VARCHAR(50)     NULL,
  `age`                     INT             NULL,
  `email`                   VARCHAR(50)     NOT NULL,
  `password`                VARCHAR(50)     NOT NULL,
  PRIMARY KEY   (`id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SELECT id INTO @index FROM users WHERE id = 1;
SET @SQL = IF(@index IS NULL, "insert into users values (1, 'steve', 23, 'qqq@qq.com', '1234')", 'select \'Exist index\';');
PREPARE statement FROM @SQL;
EXECUTE statement;
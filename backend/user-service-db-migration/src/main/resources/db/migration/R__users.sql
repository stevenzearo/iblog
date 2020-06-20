CREATE TABLE IF NOT EXISTS `users`
(
    `id`             INT          NOT NULL AUTO_INCREMENT,
    `name`           VARCHAR(50)  NULL,
    `age`            INT          NULL,
    `email`          VARCHAR(50)  NOT NULL,
    `password`       VARCHAR(50)  NOT NULL,
    `salt`           VARCHAR(50)  NOT NULL,
    `iterated_times` VARCHAR(50)  NOT NULL,
    `created_by`     VARCHAR(50)  NOT NULL,
    `created_time`   TIMESTAMP(6) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE uix_email ('email')
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;
/*
SELECT COUNT(1)
INTO @index
FROM information_schema.COLUMNS
WHERE 'table_name' = 'users'
  and 'column_name' = 'salt';
SET @SQL = IF(@`index` = 0, 'ALTER TABLE users ADD COLUMN salt VARCHAR(50) NULL AFTER `password`',
              'SELECT \'Not Exist Column\'');
prepare STATEMENT from @`SQL`;
execute STATEMENT;
*/
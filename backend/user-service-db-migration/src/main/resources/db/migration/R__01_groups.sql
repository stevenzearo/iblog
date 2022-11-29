CREATE TABLE IF NOT EXISTS `groups`
(
    `id`           VARCHAR(50)  NOT NULL,
    `name`         VARCHAR(50)  NOT NULL,
    `created_by`   VARCHAR(50)  NOT NULL,
    `created_time` TIMESTAMP(6) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX ix_created_time (`created_time`)
)
    ENGINE = InnoDB;
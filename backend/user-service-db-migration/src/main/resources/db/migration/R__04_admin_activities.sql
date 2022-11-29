CREATE TABLE IF NOT EXISTS `admin_activities`
(
    `id`           VARCHAR(50)  NOT NULL,
    `admin_id`     VARCHAR(50)  NOT NULL,
    `comment`      VARCHAR(200) NOT NULL,
    `created_by`   VARCHAR(50)  not null,
    `created_time` TIMESTAMP(6) NULL,
    PRIMARY KEY (`id`),
    INDEX ix_created_time (`created_time`)
)
    ENGINE = InnoDB;
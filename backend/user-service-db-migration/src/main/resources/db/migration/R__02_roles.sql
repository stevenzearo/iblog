CREATE TABLE IF NOT EXISTS `roles`
(
    `id`           VARCHAR(50)  NOT NULL,
    `group_id`     VARCHAR(50)  NOT NULL,
    `name`         VARCHAR(50)  NOT NULL,
    `authority`    VARCHAR(50)  NOT NULL,
    `created_by`   VARCHAR(50)  NOT NULL,
    `created_time` timestamp(6) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX ix_group_id_created_time (`group_id`, `created_time`)
)
    ENGINE = InnoDB;
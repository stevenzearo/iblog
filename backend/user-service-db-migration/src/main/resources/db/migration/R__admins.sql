CREATE TABLE IF NOT EXISTS `admins`
(
    `id`             VARCHAR(50)  NOT NULL,
    `group_id`       VARCHAR(50)  NOT NULL,
    `name`           VARCHAR(50)  NOT NULL,
    `email`          VARCHAR(50)  NOT NULL,
    `password`       VARCHAR(500) NOT NULL,
    `salt`           VARCHAR(200) NOT NULL,
    `iterated_times` INT          NOT NULL,
    `created_by`     VARCHAR(50)  not null,
    `created_time`   TIMESTAMP(6) NULL,
    PRIMARY KEY (`id`),
    UNIQUE uix_email (`email`),
    INDEX ix_group_id (`group_id`, `created_time`),
    INDEX ix_email_salt_iterated_times (`email`, `salt`, `iterated_times`)
)
    ENGINE = InnoDB;
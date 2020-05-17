CREATE TABLE IF NOT EXISTS `blocks`(
    `id`           INT          NOT NULL AUTO_INCREMENT,
    `code`         VARCHAR(50)  NOT NULL,
    `name`         VARCHAR(50)  NOT NULL,
    `created_time` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `created_by`   VARCHAR(50)  NOT NULL,
    `updated_time` TIMESTAMP(6) NULL,
    `updated_by`   VARCHAR(50)  NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;
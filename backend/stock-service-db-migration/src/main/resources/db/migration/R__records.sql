CREATE TABLE IF NOT EXISTS `records`
(
    `id`           INT            NOT NULL AUTO_INCREMENT,
    `code`         VARCHAR(50)    NOT NULL,
    `name`         VARCHAR(50)    NOT NULL,
    `record_type`  VARCHAR(50)    NOT NULL,
    `date`         TIMESTAMP(6)   NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `latest`       decimal(20, 5) NULL,
    `open`         decimal(20, 5) NULL,
    `close`        decimal(20, 5) NULL,
    `high`         decimal(20, 5) NULL,
    `low`          decimal(20, 5) NULL,
    `volume`       decimal(20, 5) NULL,
    `amplitude`    decimal(20, 5) NULL,
    `change_rate`  decimal(20, 5) NULL,
    `created_time` TIMESTAMP(6)   NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `created_by`   VARCHAR(50)    NOT NULL,
    `updated_time` TIMESTAMP(6)   NULL,
    `updated_by`   VARCHAR(50)    NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;
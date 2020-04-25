CREATE TABLE IF NOT EXISTS `stocks`
(
    `id`             INT            NOT NULL AUTO_INCREMENT,
    `code`           VARCHAR(50)    NULL,
    `block_code`     VARCHAR(50)    NULL,
    `name`           VARCHAR(50)    NULL,
    `latest`         DECIMAL(20, 5) NULL,
    `increased`      DECIMAL(20, 5) NULL,
    `increased_rate` DECIMAL(20, 5) NULL,
    `open`           DECIMAL(20, 5) NULL,
    `close`          DECIMAL(20, 5) NULL,
    `high`           DECIMAL(20, 5) NULL,
    `low`            DECIMAL(20, 5) NULL,
    `volume`         DECIMAL(20, 5) NULL,
    `volume_rate`    DECIMAL(20, 5) NULL,
    `amount`         DECIMAL(20, 5) NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;
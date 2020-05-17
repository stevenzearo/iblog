CREATE TABLE IF NOT EXISTS `stock_blocks`
(
    `stock_id`     INT          NOT NULL,
    `block_id`    INT          NOT NULL,
    `stock_name`   VARCHAR(50)  NOT NULL,
    `market_name`  VARCHAR(50)  NOT NULL,
    `created_time` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `created_by`   VARCHAR(50)  NOT NULL,
    `updated_time` TIMESTAMP(6) NULL,
    `updated_by`   VARCHAR(50)  NULL,
    PRIMARY KEY (`stock_id`, `block_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;
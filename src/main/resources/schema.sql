use ing_homework;
CREATE TABLE if not exists `customer`
(
    `id`
    bigint
    NOT
    NULL AUTO_INCREMENT,
    `business_type`
    varchar
(
    255
) DEFAULT NULL,
    `created_date` bigint NOT NULL,
    `customer_id` bigint DEFAULT NULL,
    `date` date DEFAULT NULL,
    `income` decimal
(
    19,
    2
) DEFAULT NULL,
    `modified_date` bigint DEFAULT NULL,
    `name` varchar
(
    255
) DEFAULT NULL,
    `risk_class` varchar
(
    255
) DEFAULT NULL,
    `start_date` date DEFAULT NULL,
    `type` varchar
(
    255
) DEFAULT NULL,
    PRIMARY KEY
(
    `id`
),
    UNIQUE KEY `UKj2bqemu4nnhrf27bs3ig2f9to`
(
    `customer_id`,
    `date`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_0900_ai_ci;

CREATE TABLE if not exists `customer_input`
(
    `id`
    bigint
    NOT
    NULL AUTO_INCREMENT,
    `created_date`
    bigint
    NOT
    NULL,
    `exception`
    varchar
(
    255
) DEFAULT NULL,
    `input` varchar
(
    255
) DEFAULT NULL,
    `modified_date` bigint DEFAULT NULL,
    `status` varchar
(
    255
) DEFAULT NULL,
    PRIMARY KEY
(
    `id`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_0900_ai_ci;

CREATE TABLE if not exists `customer_risks`
(
    `id`
    bigint
    NOT
    NULL AUTO_INCREMENT,
    `created_date`
    bigint
    NOT
    NULL,
    `customer_id`
    bigint
    DEFAULT
    NULL,
    `date`
    date
    DEFAULT
    NULL,
    `modified_date`
    bigint
    DEFAULT
    NULL,
    `r1`
    decimal
(
    19,
    2
) DEFAULT NULL,
    `r2` decimal
(
    19,
    2
) DEFAULT NULL,
    PRIMARY KEY
(
    `id`
),
    UNIQUE KEY `UK23kglepvpwkb2xhpc8ej56rh0`
(
    `customer_id`,
    `date`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_0900_ai_ci;

CREATE TABLE if not exists `mail_params`
(
    `id`
    bigint
    NOT
    NULL AUTO_INCREMENT,
    `content`
    varchar
(
    255
) DEFAULT NULL,
    `created_date` bigint NOT NULL,
    `mail_type` varchar
(
    255
) DEFAULT NULL,
    `modified_date` bigint DEFAULT NULL,
    `recipients` varchar
(
    255
) DEFAULT NULL,
    `title` varchar
(
    255
) DEFAULT NULL,
    PRIMARY KEY
(
    `id`
),
    UNIQUE KEY `UKq66owvk5lilyacjifrd3sy970`
(
    `mail_type`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_0900_ai_ci;
CREATE TABLE IF NOT EXISTS tasks
(
    `id`         INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `deliver_at` datetime(6)                    NOT NULL,
    `status`     varchar(50)                    NOT NULL,
    `url`        varchar(512)                   NOT NULL,
    `runner`     varchar(50)                    NULL,
    `created_at` datetime(6)                    NOT NULL,

    index `deliver_status` (`status`, `deliver_at`)
)

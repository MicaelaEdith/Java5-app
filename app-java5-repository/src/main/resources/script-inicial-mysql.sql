CREATE TABLE `pokemon` (
	`id` INT(11) NOT NULL,
	`nombre` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'latin1_spanish_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB
;

CREATE TABLE `covid` (
	`date` DATE NOT NULL,
	`cases` BIGINT(20) NOT NULL DEFAULT '0',
	PRIMARY KEY (`date`) USING BTREE
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB
;
CREATE TABLE `json_post` (
	`id` INT(11) NOT NULL,
	`json` TEXT NOT NULL COLLATE 'latin1_spanish_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB
;

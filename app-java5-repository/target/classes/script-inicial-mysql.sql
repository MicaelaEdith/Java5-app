CREATE TABLE `pokemon` (
	`id` INT(11) NOT NULL,
	`nombre` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'latin1_spanish_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB
;

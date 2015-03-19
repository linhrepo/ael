CREATE TABLE `attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fileName` varchar(250) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `offerPrice` bigint(20) DEFAULT NULL,
  `docsgeneral` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `filetosale_idx` (`offerPrice`),
  KEY `filetodoc_idx` (`docsgeneral`),
  CONSTRAINT `filetodoc` FOREIGN KEY (`docsgeneral`) REFERENCES `docsgeneral` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `filetosale` FOREIGN KEY (`offerPrice`) REFERENCES `offerprice` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;


CREATE TABLE `ael`.`realattachment` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `data` LONGBLOB NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `ael`.`realattachment` 
ADD INDEX `toAtt_idx` (`attachment` ASC);
ALTER TABLE `ael`.`realattachment` 
ADD CONSTRAINT `toAtt`
  FOREIGN KEY (`attachment`)
  REFERENCES `ael`.`attachment` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;

set global max_allowed_packet=16777216;
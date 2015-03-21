CREATE TABLE `attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fileName` varchar(250) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `offerPrice` bigint(20) DEFAULT NULL,
  `docsgeneral` bigint(20) DEFAULT NULL,
  `trucking` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `filetosale_idx` (`offerPrice`),
  KEY `filetodoc_idx` (`docsgeneral`),
  KEY `filetoTruck_idx` (`trucking`),
  CONSTRAINT `filetoTruck` FOREIGN KEY (`trucking`) REFERENCES `truckingservice` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `filetodoc` FOREIGN KEY (`docsgeneral`) REFERENCES `docsgeneral` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `filetosale` FOREIGN KEY (`offerPrice`) REFERENCES `offerprice` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;



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


CREATE TABLE `advanceform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employee` bigint(20) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `updator` bigint(20) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `refNo` varchar(45) DEFAULT NULL,
  `timeRefund` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `toOwner_idx` (`employee`),
  CONSTRAINT `toEmployeeAd` FOREIGN KEY (`employee`) REFERENCES `app_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `advancedetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `docs` bigint(20) DEFAULT NULL,
  `advanceForm` bigint(20) DEFAULT NULL,
  `amount` decimal(20,0) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `toAdvane_idx` (`advanceForm`),
  KEY `toDocs_idx` (`docs`),
  CONSTRAINT `toDocs` FOREIGN KEY (`docs`) REFERENCES `docsgeneral` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `toAdvane` FOREIGN KEY (`advanceForm`) REFERENCES `advanceform` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ael`.`receiptacc` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `type` INT(2) NOT NULL,
  `employee` BIGINT(20) NULL,
  `amount` DECIMAL(20,2) NULL,
  `reason` VARCHAR(1000) NULL,
  `date` DATETIME NULL,
  `ourRef` VARCHAR(45) NULL,
  `refNo` VARCHAR(45) NULL,
  `book` VARCHAR(45) NULL,
  `doApproval` INT(1) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


ALTER TABLE `ael`.`nhathau` 
ADD COLUMN `address` VARCHAR(45) NULL AFTER `type`,
ADD COLUMN `code` VARCHAR(45) NULL AFTER `address`;
ALTER TABLE truckingservice
	ADD COLUMN `contactDelivery` VARCHAR(255),
	ADD COLUMN `infoInvoice` VARCHAR(255);
ALTER TABLE `ael`.`exfeetable` 
ADD COLUMN `total` decimal(20,2) NULL AFTER `checkByAdmin`
	----------- Truckingdetail
ALTER TABLE truckingdetail
	ADD COLUMN `phuthau1` bigint(20),
	ADD COLUMN `phuthau2` bigint(20);
	
ALTER TABLE truckingdetail ADD FOREIGN KEY (phuthau1) REFERENCES nhathau(id);
ALTER TABLE truckingdetail ADD FOREIGN KEY (phuthau2) REFERENCES nhathau(id);
-----------PackageInfo
ALTER TABLE packageinfo MODIFY COLUMN freeDemDate int;
ALTER TABLE packageinfo MODIFY COLUMN freeDetDate int;

-------------------Truckingdetail
ALTER TABLE `ael`.`truckingdetail` 
	ADD COLUMN `phuthu` decimal(20,2),
	ADD COLUMN `noContractUp` int,
	ADD COLUMN `noContractDown` int;
	
------------COnfig
INSERT INTO `ael`.`configuration`
	VALUES(288,'Chi ph� n�ng',19,'chi phi nang',1,null);
INSERT INTO `ael`.`configuration`
	VALUES(289,'Chi ph� h?',19,'chi phi ha',1,null);
	

-------------------Package info
ALTER TABLE `ael`.`packageinfo` 
ADD COLUMN `contNo` VARCHAR(100) NULL AFTER `bookingNo`;

INSERT INTO `ael`.`role` (`description`, `name`) VALUES ('Configuration', 'ROLE_CONFIG');
INSERT INTO `ael`.`nhathau` (`id`, `type`) VALUES ('-1', '1');

------------------- Realattachment
ALTER TABLE `ael`.`realattachment` 
ADD COLUMN `extension` VARCHAR(5) NULL DEFAULT 'pdf' AFTER `attachment`;

ALTER TABLE `ael`.`docsgeneral` 
ADD COLUMN `docsgeneralcol` VARCHAR(45) NULL AFTER `contactReceive`,
ADD COLUMN `sochuyen` INT(10) NULL AFTER `docsgeneralcol`,
ADD COLUMN `nameVehicle` VARCHAR(255) NULL AFTER `sochuyen`;

ALTER TABLE `ael`.`inland` 
DROP COLUMN `nameVehicle`;

ALTER TABLE `ael`.`packageinfo` 
DROP COLUMN `nameVehicle`;
----------------------------

ALTER TABLE `ael`.`docsgeneral` 
CHANGE COLUMN `sochuyen` `sochuyen` VARCHAR(45) NULL DEFAULT NULL ;

ALTER TABLE `ael`.`truckingdetail` 
CHANGE COLUMN `noOfVehicle` `noOfVehicle` VARCHAR(45) NULL DEFAULT NULL ;
ALTER TABLE `ael`.`truckingdetail` 
ADD COLUMN `accountingPrice` DECIMAL(20,2) NULL AFTER `noContractDown`,
ADD COLUMN `otherFees` DECIMAL(20,2) NULL AFTER `accountingPrice`;

ALTER TABLE `ael`.`inland` 
DROP COLUMN `otherFees`,
DROP COLUMN `accountingPrice`,
DROP INDEX `priceItemAcc_idx` ;

ALTER TABLE `ael`.`truckingdetail` 
ADD COLUMN `payForNhaThau` BIGINT(20) NULL AFTER `otherFees`;

ALTER TABLE `ael`.`truckingdetail` 
ADD INDEX `payforNhathau_idx` (`payForNhaThau` ASC);
ALTER TABLE `ael`.`truckingdetail` 
ADD CONSTRAINT `payforNhathau`
  FOREIGN KEY (`payForNhaThau`)
  REFERENCES `ael`.`nhathau` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ael`.`truckingdetail` 
CHANGE COLUMN `noContractUp` `noContractUp` VARCHAR(45) NULL DEFAULT NULL ,
CHANGE COLUMN `noContractDown` `noContractDown` VARCHAR(45) NULL DEFAULT NULL ;

ALTER TABLE `ael`.`truckingdetail` 
DROP FOREIGN KEY `truckingdetail_ibfk_2`,
DROP FOREIGN KEY `truckingdetail_ibfk_1`;
ALTER TABLE `ael`.`truckingdetail` 
DROP COLUMN `phuthau2`,
DROP COLUMN `phuthau1`,
DROP INDEX `phuthau2` ,
DROP INDEX `phuthau1` ;
ALTER TABLE `ael`.`truckingdetail` 
DROP COLUMN `noContractDown`,
DROP COLUMN `noContractUp`;


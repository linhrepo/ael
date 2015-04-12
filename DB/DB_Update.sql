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

-------------------Package info
ALTER TABLE `ael`.`packageinfo` 
ADD COLUMN `contNo` VARCHAR(100) NULL AFTER `bookingNo`;

INSERT INTO `ael`.`role` (`description`, `name`) VALUES ('Configuration', 'ROLE_CONFIG');
INSERT INTO `ael`.`nhathau` (`id`, `type`) VALUES ('-1', '1');


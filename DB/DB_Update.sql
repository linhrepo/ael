ALTER TABLE `ael`.`nhathau` 
ADD COLUMN `address` VARCHAR(45) NULL AFTER `type`,
ADD COLUMN `code` VARCHAR(45) NULL AFTER `address`;
ALTER TABLE truckingservice
	ADD COLUMN `contactDelivery` VARCHAR(255),
	ADD COLUMN `infoInvoice` VARCHAR(255);
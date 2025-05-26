drop database if exists techinventory;
create database techinventory;
use techinventory;

create table Rol(
    id int primary key auto_increment,
    `name` varchar(50) not null unique
);
create table Users(
    id int primary key auto_increment,
    `name` varchar(50) not null unique,
    password varchar(100) not null,
    idRol int,
    foreign key (idRol) references Rol(id)
);

CREATE TABLE DNIType (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `type` VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE Person (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    DNItype INT NOT NULL,
    DNI VARCHAR(20) NOT NULL UNIQUE,
    address VARCHAR(50) NOT NULL, 
    telephone VARCHAR(20) NOT NULL,
    mail VARCHAR(70) UNIQUE,
    registrationdate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (DNItype) REFERENCES DNIType(id)
);
create table PaymentMethods(
    id int primary key auto_increment,
    methods varchar(50) not null unique
);
create table FaultType(
    id int primary key auto_increment,
    fault varchar(50) not null unique
);
create table Service(
    id int primary key auto_increment,
    service varchar(50) not null unique
);
create table `Status`(
    id int primary key auto_increment,
    `name` varchar(50) not null unique
);
create table Staff(
    id int primary key auto_increment,
    staffType varchar(50) not null,
    idPerson int,
    foreign key(idPerson) references Person(id)
);
create table `Client`(
    id int primary key auto_increment,
    idPerson int,
    foreign key (idPerson) references Person(id)
); 
create table Supplier(
    id int primary key auto_increment,
    companyName varchar(50) not null,
    supplierType varchar(50) not null,
    idPerson int,
    idPaymentMethods int,
    foreign key (idPerson) references Person(id),
    foreign key (idPaymentMethods) references PaymentMethods(id)
);
create table Material(
    id int primary key auto_increment,
    materialType varchar(50) not null,
    materialName varchar(50) not null,
    purchaseCost decimal(10,2) not null,
    purchaseDate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    idSupplier int,
    foreign key (idSupplier) references Supplier(id)
);
create table Products(
    id int primary key auto_increment,
    productType varchar(50) not null,
    productName varchar(50) not null,
    `description` varchar(100) not null,
    purchasePrice decimal(10,2) not null,
    purchaseDate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    idSupplier int,
    foreign key (idSupplier) references Supplier(id)
);
create table Warehouse(
    id int primary key auto_increment,
    stock int not null,
    amount int not null,
    salePrice decimal(10,2) not null,
    storageDate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    idProducts int,
    foreign key (idProducts) references Products(id)
);
create table Sale(
    id int primary key auto_increment,
    saleDate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    idProducts int,
    idPaymentMethods int,
    foreign key (idProducts) references Products(id),
    foreign key (idPaymentMethods) references PaymentMethods(id)
);
create table SaleDetail(
    id int primary key auto_increment,
    soldAmount int not null,
    unitPrice decimal(10,2) not null,
    idSale int,
    idProducts int,
    idClient int,
    foreign key (idSale) references Sale(id),
    foreign key (idProducts) references Products(id),
    foreign key (idClient) references `Client`(id)
);
CREATE TABLE Repairs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    device VARCHAR(50) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    model VARCHAR(20) NOT NULL,
    faultDescription TEXT NOT NULL,
    entryDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deliveryDate DATETIME,
    repairCost DOUBLE NOT NULL,
    idClient INT,
    idMaterial INT,
    idFaultType INT,
    idPaymentMethods INT,
    idStatus INT,
    idService INT,
    FOREIGN KEY (idClient) REFERENCES Client(id),
    FOREIGN KEY (idMaterial) REFERENCES Material(id),
    FOREIGN KEY (idFaultType) REFERENCES FaultType(id),
    FOREIGN KEY (idPaymentMethods) REFERENCES PaymentMethods(id),
    FOREIGN KEY (idStatus) REFERENCES `Status`(id),
    FOREIGN KEY (idService) REFERENCES Service(id)
);
create table RepairMaterial(
    id int primary key auto_increment,
    idRepair int,
    idMaterial int,
    quantityUsed int not null,
    foreign key (idRepair) references Repairs(id),
    foreign key (idMaterial) references Material(id)
);
INSERT INTO DNIType (`type`) VALUES 
('CC'),  
('CE'),   
('TI'),   
('Pasaporte'),
('NIT');

INSERT INTO FaultType (fault) VALUES ('Pantalla rota');
INSERT INTO FaultType (fault) VALUES ('Batería dañada');
INSERT INTO FaultType (fault) VALUES ('Problema de software');
INSERT INTO FaultType (fault) VALUES ('Altavoz defectuoso');
INSERT INTO Service (service) VALUES ('Cambio de pantalla');
INSERT INTO Service (service) VALUES ('Reemplazo de batería');
INSERT INTO Service (service) VALUES ('Reparación de software');
INSERT INTO Service (service) VALUES ('Reemplazo de altavoz');
-- Asumiendo que el tipo 'CC' tiene ID = 1
INSERT INTO Person (name, lastName, DNItype, DNI, address, telephone, mail)
VALUES ('Juan', 'Pérez', 1, '12345678', 'Av. Principal 123', '3001234567', 'juan.perez@email.com');

INSERT INTO Person (name, lastName, DNItype, DNI, address, telephone, mail)
VALUES ('Maria', 'Gómez', 1, '87654321', 'Calle Secundaria 456', '3109876543', 'maria.gomez@email.com');

INSERT INTO Person (name, lastName, DNItype, DNI, address, telephone, mail)
VALUES ('Carlos', 'Fernández', 1, '11112222', 'Av. Secundaria 789', '3154446677', 'carlos.fernandez@email.com');

INSERT INTO Client (idPerson) VALUES (1);
INSERT INTO Client (idPerson) VALUES (2);
INSERT INTO Client (idPerson) VALUES (3);

INSERT INTO Repairs (device, brand, model, faultDescription, entryDate, deliveryDate, repairCost, idClient, idMaterial, idFaultType, idPaymentMethods, idStatus, idService)
VALUES ('Smartphone', 'Samsung', 'Galaxy S21', 'Pantalla rota', '2025-04-01 10:00:00', '2025-04-05 15:30:00', 200.00, 1, NULL, 1, NULL, NULL, 1);

INSERT INTO Repairs (device, brand, model, faultDescription, entryDate, deliveryDate, repairCost,idClient, idMaterial, idFaultType, idPaymentMethods, idStatus, idService)
VALUES ('Laptop', 'Dell', 'Inspiron 15', 'Problema de software', '2025-04-02 12:15:00', '2025-04-06 16:45:00', 150.00,2, NULL, 3, NULL, NULL, 3);

INSERT INTO Repairs (device, brand, model, faultDescription, entryDate, deliveryDate, repairCost, idClient, idMaterial, idFaultType, idPaymentMethods, idStatus, idService)
VALUES ('Tablet', 'Apple', 'iPad Pro', 'Altavoz defectuoso', '2025-04-03 09:30:00', '2025-04-07 14:00:00', 180.00, 3, NULL, 4, NULL, NULL, 4);
INSERT INTO Repairs (device, brand, model, faultDescription, entryDate, deliveryDate, repairCost, idClient, idMaterial, idFaultType, idPaymentMethods, idStatus, idService)
VALUES ('Smartphone', 'Apple', 'iPhone 12', 'Batería dañada', '2025-04-10 14:00:00', '2025-04-15 11:45:00', 120.00, 2, NULL, 2, NULL, NULL, 2);

INSERT INTO Repairs (device, brand, model, faultDescription, entryDate, deliveryDate, repairCost, idClient, idMaterial, idFaultType, idPaymentMethods, idStatus, idService)
VALUES ('Laptop', 'HP', 'Pavilion', 'Pantalla rota', '2025-04-12 09:30:00', '2025-04-17 18:20:00', 220.00, 3, NULL, 1, NULL, NULL, 1);

INSERT INTO `Status` (name) VALUES 
('Pending'), 
('In progress'), 
('Repaired');
-- ---------------------------------------------------------
-- CALL getServiceIdByName('Cambio de display');
-- CALL getPaymentMethodIdByName('efectivo');
-- CALL commonfaultreport();
-- CALL AverageFaultTimeReport();
-- CALL FrequentCustomersReport();
-- CALL RepairIncomeReport();
-- call ShowClient();
delimiter //
create procedure insert_Rol(`name` varchar(50))
begin
    insert into Rol(`name`) values (`name`);
end;//
call insert_Rol ('Admin');//
call insert_Rol ('Staff');//

create procedure insert_Users(`name` varchar(50), password varchar(100), idRol int)
begin
    insert into Users(`name`, password, idRol)
    values (`name`, password, idRol);
end;//
call insert_Users('admin','admin',1);//
call insert_Users('fernando','fernando',1);//
call insert_Users('staff','staff',2);//
call insert_Users('jhon','jhon',2);//

DROP PROCEDURE IF EXISTS Login;//
CREATE PROCEDURE Login(IN p_name VARCHAR(50), IN p_password VARCHAR(100))
BEGIN
    SELECT 	
        u.name AS username, 
        u.password, 
        r.name AS role,
        r.id AS idRol
    FROM Users u
    JOIN Rol r ON u.idRol = r.id
    WHERE u.name = p_name AND u.password = p_password
    LIMIT 1;
END;//

create procedure insertService(service varchar(100))
begin
    insert into Service(service) values(service);
end;//
call insertService('Cambio de display');//
call insertService('Pin de carga');//
call insertService('Mantenimiento preventivo');//
call insertService('Software');//
call insertService('Cambio de repuesto');//
call insertService('Hardware');//
call insertService('Microsoldadura');//

CREATE PROCEDURE getServices()
BEGIN
    SELECT service FROM Service;
END;//

CREATE PROCEDURE getServiceIdByName(IN serviceName VARCHAR(100))
BEGIN
    SELECT id FROM service WHERE service = serviceName;
END;//

create procedure insertPaymentMethod(methods varchar(150))
begin
    insert into PaymentMethods(methods) values (methods);
end;//

call insertPaymentMethod('efectivo');//
call insertPaymentMethod('tarjeta');//
call insertPaymentMethod('transferencia');//

CREATE PROCEDURE getPaymentMethods()
BEGIN
    SELECT methods FROM paymentmethods;
END;//

CREATE PROCEDURE getPaymentMethodIdByName(IN methodName VARCHAR(100))
BEGIN
    SELECT id FROM paymentmethods WHERE methods = methodName;
END;//

-- para insertar reparaciones
CREATE PROCEDURE insertRepair(
    IN p_device VARCHAR(50),
    IN p_brand VARCHAR(50),
    IN p_model VARCHAR(20),
    IN p_faultDescription TEXT,
    IN p_deliveryDate DATETIME,
    IN p_repairCost DOUBLE,
    IN p_idClient INT,
    IN p_idMaterial INT,
    IN p_idFaultType INT,
    IN p_idPaymentMethods INT,
    IN p_idStatus INT,
    IN p_idService INT
)
BEGIN
    INSERT INTO Repairs (
        device, brand, model, faultDescription, entryDate, deliveryDate, repairCost, 
        idClient, idMaterial, idFaultType, idPaymentMethods, idStatus, idService
    ) 
    VALUES (
        p_device, p_brand, p_model, p_faultDescription, NOW(), p_deliveryDate, p_repairCost,
        p_idClient, p_idMaterial, p_idFaultType, p_idPaymentMethods, p_idStatus, p_idService
    );
END;//
-- para mostrar las reparaciones: 
CREATE PROCEDURE ShowRepairs()
BEGIN
    SELECT * FROM Repairs;
END;//
-- ------------------------------------------------------------------------------------------------
-- reportes: 
CREATE PROCEDURE commonfaultreport()
BEGIN
    SELECT 
        ft.fault AS 'Fault Type',
        COUNT(r.id) AS 'Amount',
        MIN(r.entryDate) AS 'First Occurrence',
        MAX(r.entryDate) AS 'Latest Occurrence'
    FROM Repairs r
    INNER JOIN FaultType ft ON r.idFaultType = ft.id
    GROUP BY ft.fault 
    ORDER BY Amount DESC;
END;//

CREATE PROCEDURE AverageFaultTimeReport()
BEGIN
    SELECT 
        ft.fault AS FaultType,
        AVG(TIMESTAMPDIFF(HOUR, r.entryDate, r.deliveryDate)) AS AverageHours,
        MIN(r.entryDate) AS 'Earliest Repair',
        MAX(r.deliveryDate) AS 'Latest Repair'
    FROM Repairs r
    INNER JOIN FaultType ft ON r.idFaultType = ft.id
    WHERE r.deliveryDate IS NOT NULL
    GROUP BY ft.fault
    ORDER BY AverageHours DESC;
END;//

CREATE PROCEDURE FrequentCustomersReport()
BEGIN
    SELECT 
        CONCAT(p.`name`, ' ', p.lastName) AS 'Client',
        COUNT(r.id) AS TotalRepairs,
        COALESCE(GROUP_CONCAT(DISTINCT s.service SEPARATOR ', '), 'No Service') AS RequestedServices,
        MIN(r.entryDate) AS 'First Repair',
        MAX(r.deliveryDate) AS 'Latest Repair' 
    FROM Repairs r
    INNER JOIN `Client` c ON r.idClient = c.id
    INNER JOIN Person p ON c.idPerson = p.id
    LEFT JOIN Service s ON r.idService = s.id
    GROUP BY p.id, p.`name`, p.lastName
    ORDER BY TotalRepairs DESC;
END;//

CREATE PROCEDURE RepairIncomeReport()
BEGIN
    SELECT 
        DATE(r.deliveryDate) AS 'Repair Date', 
        COUNT(r.id) AS QuantityRepairs,
        SUM(r.repairCost) AS TotalRevenue
    FROM Repairs r
    WHERE r.deliveryDate IS NOT NULL 
    GROUP BY DATE(r.deliveryDate)
    ORDER BY DATE(r.deliveryDate) DESC;
END;//

CREATE PROCEDURE CheckExistingRepair(
    IN p_clientId INT,
    IN p_idService INT,
    IN p_device VARCHAR(50),
    IN p_model VARCHAR(50)
)
BEGIN
    SELECT COUNT(*) AS repair_count
    FROM Repairs
    WHERE idClient = p_clientId AND idService = p_idService AND device = p_device AND model = p_model;
END;//

-- ------------------------------------------------------------------------------------------------
CREATE PROCEDURE AddClient(
    IN p_name VARCHAR(50),
    IN p_lastName VARCHAR(50),
    IN p_DNIType VARCHAR(20),
    IN p_DNI VARCHAR(20),
    IN p_address VARCHAR(50),
    IN p_telephone VARCHAR(20),
    IN p_mail VARCHAR(70)
)
BEGIN
    DECLARE idDNI INT;
    DECLARE id_persona INT;
    SELECT id INTO idDNI FROM DNIType WHERE type = p_DNIType LIMIT 1;
    IF idDNI IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: Invalid DNI Type';
    END IF;
    INSERT INTO Person(name, lastName, DNItype, DNI, address, telephone, mail)
    VALUES (p_name, p_lastName, idDNI, p_DNI, p_address, p_telephone, p_mail);
    SET id_persona = LAST_INSERT_ID();
    INSERT INTO `Client`(idPerson) VALUES (id_persona);
END;//


create procedure ShowClient()
BEGIN
    SELECT 
        p.id AS 'Person ID',
        p.name AS 'Name',
        p.lastName AS 'Last Name',
        dt.type AS 'DNI Type', 
        p.DNI AS 'DNI',
        p.address AS 'Address',
        p.telephone AS 'Phone',
        p.mail AS 'Email',
        p.registrationdate AS 'Registration Date'
    FROM Person p
    INNER JOIN DNIType dt ON p.DNItype = dt.id; 
END;//

CREATE PROCEDURE AddSupplier(
    in p_companyName VARCHAR(50),
    in p_supplierType VARCHAR(50),
    in p_name VARCHAR(50),
    in p_lastName VARCHAR(50),
    in p_DNItype VARCHAR(20),
    in p_DNI VARCHAR(20),
    in p_address VARCHAR(50),
    in p_telephone VARCHAR(20),
    in p_mail VARCHAR(70),
    in p_idPaymentMethods inT
)
BEGin
    DECLARE id_persona inT;
    insert into Person(name, lastName, DNItype, DNI, address, telephone, mail)
    values (p_name, p_lastName, p_DNItype, p_DNI, p_address, p_telephone, p_mail);
    set id_persona = LAST_inSERT_ID();
    insert into Supplier(companyName, supplierType, idPerson, idPaymentMethods)
    values (p_companyName, p_supplierType, id_persona, p_idPaymentMethods);
    end;//
    
    
    CREATE PROCEDURE ShowSupplier()
BEGin
    SELECT 
        s.id AS idProveedor,
        s.companyName AS nombreEmpresa,
        s.supplierType AS tipoProveedor,
        p.name AS nombre,
        p.lastName AS apellido,
        p.DNItype AS tipoDocumento,
        p.DNI AS documento,
        p.address AS direccion,
        p.telephone AS telefono,
        p.mail AS correo,
        pm.methods AS metodoPago
    FROM Supplier s
    inner join Person p ON s.idPerson = p.id
    inner join PaymentMethods pm ON s.idPaymentMethods = pm.id;
    end;//
    
    CREATE PROCEDURE AddProduct(
    in p_productType VARCHAR(50),
    in p_productName VARCHAR(50),
    in p_description VARCHAR(100),
    in p_purchasePrice DECIMAL(10,2),
    in p_purchaseDate DATETIME,
    in p_idSupplier inT
)
BEGin
    insert into Products(productType, productName, description, purchasePrice, purchaseDate, idSupplier)
    values (p_productType, p_productName, p_description, p_purchasePrice, p_purchaseDate, p_idSupplier);
end;//

CREATE PROCEDURE FillDNIType()
BEGIN
    INSERT INTO DNIType (`type`) VALUES 
    ('CC'),  
    ('CE'),   
    ('TI'),   
    ('Pasaporte'),
    ('NIT');
END;//

-- agrega stock del producto en la tabla warehouse

CREATE PROCEDURE AddStockProduct(
    in p_stock inT,
    in p_amount inT,
    in p_salePrice DECIMAL(10,2),
    in p_idProducts inT
)
BEGin
    insert into Warehouse(stock, amount, salePrice, idProducts)
    values (p_stock, p_amount, p_salePrice, p_idProducts);
    end;//

CREATE PROCEDURE ShowDNITypes()
BEGIN
    SELECT `type` FROM DNIType;
END;//


--  triggers -------------------------------------------------------

CREATE TRIGGER after_insert_person
AFTER INSERT ON Person
FOR EACH ROW
BEGIN
    INSERT INTO `Client`(idPerson) VALUES (NEW.id);
END;//
-- ----------------
DELIMITER ;
INSERT INTO Supplier (companyName, supplierType, idPerson, idPaymentMethods)
VALUES ('Tech Parts Ltd.', 'Electronics', 1, 1);

INSERT INTO Supplier (companyName, supplierType, idPerson, idPaymentMethods)
VALUES ('Gadget Repair Supplies', 'Repair Components', 2, 2);

INSERT INTO Material (materialType, materialName, purchaseCost, purchaseDate, idSupplier)
VALUES ('Pantalla', 'Pantalla Samsung Galaxy S21', 100.00, '2025-04-01 09:00:00', 1);

INSERT INTO Material (materialType, materialName, purchaseCost, purchaseDate, idSupplier)
VALUES ('Batería', 'Batería para iPhone 12', 80.00, '2025-04-02 10:30:00', 2);





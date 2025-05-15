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
create table Person(
    id int primary key auto_increment,
    `name` varchar(50) not null,
    lastName varchar(50) not null,
    DNItype varchar(20) not null,
    DNI varchar(20) not null unique,
    address varchar(50) not null, 
    telephone varchar(20) not null,
    mail varchar(70) unique
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
    idSupplier int,
    foreign key (idSupplier) references Supplier(id)
);
create table Products(
    id int primary key auto_increment,
    productType varchar(50) not null,
    productName varchar(50) not null,
    `description` varchar(100) not null,
    purchasePrice decimal(10,2) not null,
    purchaseDate datetime not null,
    idSupplier int,
    foreign key (idSupplier) references Supplier(id)
);
create table Warehouse(
    id int primary key auto_increment,
    stock int not null,
    amount int not null,
    salePrice decimal(10,2) not null,
    idProducts int,
    foreign key (idProducts) references Products(id)
);
create table Sale(
    id int primary key auto_increment,
    saleDate datetime not null,
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
    id inT PRIMARY KEY AUTO_inCREMENT,
    device varchar(50) NOT NULL,
    mark varchar(50) NOT NULL,
    model varchar(20) NOT NULL,
    faultDescription TEXT NOT NULL,
    entryDate DATETIME NOT NULL,
    deliveryDate DATETIME NOT NULL,
    repairCost DOUBLE NOT NULL,
    observation TEXT NOT NULL,
    hasSIM BOOLEAN NOT NULL DEFAULT FALSE,
    hasCase BOOLEAN NOT NULL DEFAULT FALSE,
    idClient inT,
    idMaterial inT,
    idFaultType inT,
    idPaymentMethods inT,
    idStatus inT,
    idService inT,
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

INSERT INTO FaultType (fault) VALUES ('Pantalla rota');
INSERT INTO FaultType (fault) VALUES ('Batería dañada');
INSERT INTO FaultType (fault) VALUES ('Problema de software');
INSERT INTO FaultType (fault) VALUES ('Altavoz defectuoso');
INSERT INTO Service (service) VALUES ('Cambio de pantalla');
INSERT INTO Service (service) VALUES ('Reemplazo de batería');
INSERT INTO Service (service) VALUES ('Reparación de software');
INSERT INTO Service (service) VALUES ('Reemplazo de altavoz');
INSERT INTO Person (name, lastName, DNItype, DNI, address, telephone, mail)
VALUES ('Juan', 'Pérez', 'CC', '12345678', 'Av. Principal 123', '3001234567', 'juan.perez@email.com');

INSERT INTO Person (name, lastName, DNItype, DNI, address, telephone, mail)
VALUES ('Maria', 'Gómez', 'CC', '87654321', 'Calle Secundaria 456', '3109876543', 'maria.gomez@email.com');

INSERT INTO Person (name, lastName, DNItype, DNI, address, telephone, mail)
VALUES ('Carlos', 'Fernández', 'CC', '11112222', 'Av. Secundaria 789', '3154446677', 'carlos.fernandez@email.com');
INSERT INTO Client (idPerson) VALUES (1);
INSERT INTO Client (idPerson) VALUES (2);
INSERT INTO Client (idPerson) VALUES (3);
INSERT INTO Repairs (device, mark, model, faultDescription, entryDate, deliveryDate, repairCost, observation, hasSIM, hasCase, idClient, idMaterial, idFaultType, idPaymentMethods, idStatus, idService)
VALUES ('Smartphone', 'Samsung', 'Galaxy S21', 'Pantalla rota', '2025-04-01 10:00:00', '2025-04-05 15:30:00', 200.00, 'Reemplazo completo de pantalla', TRUE, FALSE, 1, NULL, 1, NULL, NULL, 1);

INSERT INTO Repairs (device, mark, model, faultDescription, entryDate, deliveryDate, repairCost, observation, hasSIM, hasCase, idClient, idMaterial, idFaultType, idPaymentMethods, idStatus, idService)
VALUES ('Laptop', 'Dell', 'Inspiron 15', 'Problema de software', '2025-04-02 12:15:00', '2025-04-06 16:45:00', 150.00, 'Reinstalación de sistema operativo', FALSE, FALSE, 2, NULL, 3, NULL, NULL, 3);

INSERT INTO Repairs (device, mark, model, faultDescription, entryDate, deliveryDate, repairCost, observation, hasSIM, hasCase, idClient, idMaterial, idFaultType, idPaymentMethods, idStatus, idService)
VALUES ('Tablet', 'Apple', 'iPad Pro', 'Altavoz defectuoso', '2025-04-03 09:30:00', '2025-04-07 14:00:00', 180.00, 'Reemplazo de altavoz', FALSE, FALSE, 3, NULL, 4, NULL, NULL, 4);
INSERT INTO Repairs (device, mark, model, faultDescription, entryDate, deliveryDate, repairCost, observation, hasSIM, hasCase, idClient, idMaterial, idFaultType, idPaymentMethods, idStatus, idService)
VALUES ('Smartphone', 'Apple', 'iPhone 12', 'Batería dañada', '2025-04-10 14:00:00', '2025-04-15 11:45:00', 120.00, 'Reemplazo de batería', FALSE, FALSE, 2, NULL, 2, NULL, NULL, 2);

INSERT INTO Repairs (device, mark, model, faultDescription, entryDate, deliveryDate, repairCost, observation, hasSIM, hasCase, idClient, idMaterial, idFaultType, idPaymentMethods, idStatus, idService)
VALUES ('Laptop', 'HP', 'Pavilion', 'Pantalla rota', '2025-04-12 09:30:00', '2025-04-17 18:20:00', 220.00, 'Reemplazo de pantalla', FALSE, TRUE, 3, NULL, 1, NULL, NULL, 1);

-- CALL commonfaultreport();
-- CALL AverageFaultTimeReport();
-- CALL FrequentCustomersReport();
-- CALL RepairIncomeReport();

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
call insert_Users('staff','staff',2);//

-- para el combo box de roles
create procedure ViewRols()
begin
    select id, `name` from Rol;
end;//

create procedure Login(p_rol varchar(50),  p_name varchar(50), p_password varchar(100))
begin
    declare v_count int default 0;
    select COUNT(*) into v_count
    from Users U
    join Rol R on U.idRol = R.id
    where R.`name` = p_rol and U.`name` = p_name and U.password = p_password;

    select v_count as coincidencias;
end;//

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

create procedure insertPaymentMethod(methods varchar(150))
begin
    insert into PaymentMethods(methods) values (methods);
end;//

call insertPaymentMethod('efectivo');//
call insertPaymentMethod('tarjeta');//
call insertPaymentMethod('transferencia');//


-- para insertar reparaciones
create procedure setRepair(
    in p_device varchar(50),
    in p_mark varchar(50),
    in p_model varchar(20),
    in p_faultDescription text,
    in p_entryDate datetime,
    in p_deliveryDate datetime,
    in p_repairCost double,
    in p_observation text,
    in p_hasSIM boolean,
    in p_hasCase boolean,
    in p_idClient int,
    in p_idMaterial int,
    in p_idFaultType int,
    in p_idPaymentMethods int,
    in p_idStatus int
)
begin
    insert into Repairs (
        device, mark, model, faultDescription,
        entryDate, deliveryDate, repairCost, observation,
        hasSIM, hasCase,
        idClient, idMaterial, idFaultType,
        idPaymentMethods, idStatus
    )
    values (
        p_device, p_mark, p_model, p_faultDescription,
        p_entryDate, p_deliveryDate, p_repairCost, p_observation,
        p_hasSIM, p_hasCase,
        p_idClient, p_idMaterial, p_idFaultType,
        p_idPaymentMethods, p_idStatus
    );
end;//



-- ------------------------------------------------------------------------------------------------
-- reportes: 

create procedure commonfaultreport()
begin
    select ft.fault as 'fault type',COUNT(r.id) as 'amount'
    from Repairs r
    inner join FaultType ft on r.idFaultType = ft.id
    group by ft.fault
    order by amount desc;
end;//


create procedure AverageFaultTimeReport()
begin
    select 
        ft.fault as FaultType,
        avg(timestampdiff(hour, r.entryDate, r.deliveryDate)) as AverageHours
    from Repairs r
    inner join FaultType ft on r.idFaultType = ft.id
    group by ft.fault
	order by AverageHours desc;
end;//


create procedure FrequentCustomersReport()
begin
    select 
        CONCAT(p.`name`, ' ', p.lastName) as 'Client',
        COUNT(r.id) as TotalRepairs,
        GROUP_CONCAT(distinct s.service separator ', ') as RequestedServices
    from Repairs r
    inner join `Client` c on r.idClient = c.id
    inner join Person p on c.idPerson = p.id
    LEFT JOin Service s on r.idService = s.id
    group by p.id, p.`name`, p.lastName
    order by TotalRepairs desc;
end;//

create procedure RepairIncomeReport()
begin
    select 
        date(r.entryDate) as date,
        COUNT(r.id) as QuantityRepairs,
        SUM(r.repairCost) as TotalRevenue
    from Repairs r
    group by date(r.entryDate)
    order by date(r.entryDate) desc;
end;//

-- ------------------------------------------------------------------------------------------------


create procedure AddClient(
    in p_name varchar(50),
    in p_lastName varchar(50),
    in p_DNItype varchar(20),
    in p_DNI varchar(20),
    in p_address varchar(50),
    in p_telephone varchar(20),
    in p_mail varchar(70))
begin
    declare id_persona int;
    insert into Person(name, lastName, DNItype, DNI, address, telephone, mail)
    values (p_name, p_lastName, p_DNItype, p_DNI, p_address, p_telephone, p_mail);
    -- Obtener el id generado
    set id_persona = LasT_inSERT_ID();
    -- insertar en la tabla Client
    insert into `Client`(idPerson) values (id_persona);
    end;//
    
create procedure ShowClient()
begin
    select 
        Person.`name` as 'Name',
        Person.lastName as 'Last name',
        Person.DNItype as 'DNI type',
        Person.DNI as 'DNI',
        Person.address as 'Address',
        Person.telephone as 'Telephone',
        Person.mail as 'Mail'
    from Person
    inner join `Client` on Person.id = `Client`.idPerson;
end;//

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


--  triggers -------------------------------------------------------

CREATE TRIGGER after_insert_person
AFTER INSERT ON Person
FOR EACH ROW
BEGIN
    INSERT INTO `Client`(idPerson) VALUES (NEW.id);
END;//
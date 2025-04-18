drop database if exists techinventory;
create database techinventory;
use techinventory;

create table Rol(
id int primary key auto_increment,
name varchar(50) not null
);

create table Users(
id int primary key auto_increment,
 `name` varchar(50) not null,
password varchar(50) not null,
idRol int,
foreign key (idRol) references Rol(id)
);

create table Person(
id int primary key auto_increment,
 `name` varchar(50) not null,
lastName varchar(50) not null,
DNItype varchar (20) not null,
DNI varchar (20) not null,
address varchar (50) not null, 
telephone varchar (20) not null,
mail varchar (70)
);

create table PaymentMethods(
id int primary key auto_increment,
methods varchar(50) not null
);

create table FaultType(
id int primary key auto_increment,
fault varchar (50) not null
);

create table Service(
id int primary key auto_increment,
service varchar (50)
);

create table `Status`(
id int primary key auto_increment
);

create table Staff(
id int primary key auto_increment,
staffType varchar(50),
idPerson int,
foreign key  (idPerson) references Person(id)
);

create table `client`(
id int primary key auto_increment,
idPerson int,
foreign key (idPerson) references Person(id)
); 

create table Supplier(
id int primary key auto_increment,
companyName varchar (50) not null,
supplierType varchar (50) not null,
idPerson int,
idPaymentMethods int,
foreign key (idPerson) references Person(id),
foreign key (idPaymentMethods) references PaymentMethods(id)
);

create table Material(
id int primary key auto_increment,
materialType varchar (50) not null,
materialName varchar(50)not null,
purchaseCost double not null,
idSupplier int,
foreign key (idSupplier) references Supplier(id)
);

create table Products(
id int primary key auto_increment,
productType varchar (50) not null,
productName varchar (50) not null,
`description` varchar (50) not null,
purchasePrice int not null,
purchaseDate datetime not null,
idSupplier int,
foreign key (idSupplier) references Supplier(id)
);

create table Warehouse(
id int primary key auto_increment,
stock int not null,
amount int not null,
salePrice double not null,
idProducts int,
foreign key (idProducts) references Products(id)
);

create table Sale(
id int primary key auto_increment,
stock int not null,
saleDate datetime not null,
idProducts int,
idPaymentMethods int,
foreign key (idProducts) references Products(id),
foreign key (idPaymentMethods) references PaymentMethods(id)
);

create table SaleDetail(
id int primary key auto_increment,
souldAmount int not null,
unitPrice int not null,
idSale int,
idProducts int,
idClient int,
foreign key (idSale) references Sale(id),
foreign key (idProducts) references Products(id),
foreign key (idClient) references `client`(id)
);

create table Repairs(
id int primary key auto_increment,
device varchar(50) not null,
mark varchar (50) not null,
model varchar (20) not null,
faultDescription text not null,
entryDate datetime not null,
deliveryDate datetime not null,
repairCost double not null,
observation text not null,
idClient int,
idMaterial int,
idFaultType int,
idPaymentMethods int,
idStatus int,
foreign key (idClient) references `Client`(id),
foreign key (idMaterial) references Material(id),
foreign key (idFaultType) references FaultType(id),
foreign key (idPaymentMethods) references PaymentMethods(id),
foreign key (idStatus) references `Status`(id)
);

-- -------------------------- procedures -------------------------- --
delimiter //

create procedure Insert_Rol(`name`varchar(50))
begin
	insert into Rol(`name`)
    values (`name`);
end;//
call Insert_Rol ('Admin');//
call Insert_Rol ('staff');//


create procedure Insert_Users(`name` varchar(50), password varchar(50), idRol int)
begin
	insert into Users(`name`,password,idRol)
    values (`name`,password,idRol);
end;//
-- Admin
call Insert_Users('admin','admin',1);//
-- staff
call Insert_Users('staff','staff',2);//


create procedure ViewRols()
begin
select id, `name` from rol;
end;//


create procedure Login(p_rol VARCHAR(50),  p_name VARCHAR(50),p_password VARCHAR(50))
begin
    declare v_count int default 0;
    select COUNT(*)
    into v_count
    from Users U
    join Rol R on U.idRol = R.id
    where R.`name` = p_rol and U.`name` = p_name and U.password = p_password;
    select v_count as coincidencias;
END;//



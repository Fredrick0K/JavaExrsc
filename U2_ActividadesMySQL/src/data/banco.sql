-- ESQUEMA para BD-R MySQL banco

create table cliente (
    codigo         integer     not null auto_increment,
    nombre         varchar(50) not null,
    domicilio      varchar(80) not null,
    correo         varchar(50) not null,
    fecha_registro date        not null, -- formato AAAA-MM-DD
    primary key (codigo),
    unique (nombre),
    unique (correo)
);

create table sucursal (
    codigo         integer     not null auto_increment,
    direccion      varchar(80) not null,
    localidad      varchar(30) not null,
    telefono       varchar(15) not null,
    numero_puestos integer     not null default 2,
    primary key (codigo),
    unique (telefono),
    check (numero_puestos >= 2)
);

create table cuenta (
    codigo_cliente  integer      not null,
    codigo_sucursal integer      not null,
    fecha_apertura  date         not null,              -- formato AAAA-MM-DD
    tipo            varchar(15)  not null,
    saldo           decimal(9,2) not null default 0.00, -- euros
    interes         decimal(4,2)          default null,
    primary key (codigo_cliente, codigo_sucursal, fecha_apertura),
    foreign key (codigo_cliente) references cliente(codigo),
    foreign key (codigo_sucursal) references sucursal(codigo),
    check (tipo in ('corriente', 'ahorro', 'nómina', 'remunerada')),
    check (saldo >= 0.00),
    check (interes is null or (interes > 0.00 and interes < 50.00))
);

-- DATOS para BD-R MySQL banco

insert into cliente
values
(1, 'Laura Gómez', 'Calle Luna 14, Madrid', 'laura.gomez@example.com', '2023-02-10'),
(2, 'Carlos Pérez', 'Av. del Sol 102, Valencia', 'carlos.perez@example.com', '2023-05-21'),
(3, 'Ana Torres', 'Calle Jardín 33, Sevilla', 'ana.torres@example.com', '2024-01-09'),
(4, 'David Ruiz', 'Calle Mayor 45, Bilbao', 'david.ruiz@example.com', '2024-03-18'),
(5, 'Sofía Martínez', 'Paseo del Río 77, Zaragoza', 'sofia.martinez@example.com', '2024-11-02');

insert into sucursal
values
(1, 'Calle Alcalá 25', 'Madrid', '910123456', 4),
(2, 'Av. del Puerto 88', 'Valencia', '960987654', 3),
(3, 'Plaza Nueva 11', 'Sevilla', '955333222', 5),
(4, 'Calle Gran Vía 40', 'Bilbao', '944112233', 2),
(5, 'Av. Independencia 5', 'Zaragoza', '976445566', 3);

insert into cuenta 
values
(1, 1, '2023-02-15', 'ahorro', 3500.75, 1.25),
(2, 2, '2023-06-01', 'corriente', 1250.00, NULL),
(3, 3, '2024-01-15', 'nómina', 800.00, 0.50),
(4, 4, '2024-03-25', 'remunerada', 15000.00, 2.10),
(5, 5, '2024-11-05', 'ahorro', 2400.00, 1.10),
(1, 1, '2024-07-10', 'corriente', 450.50, NULL),
(2, 3, '2024-09-01', 'ahorro', 975.25, 0.90);


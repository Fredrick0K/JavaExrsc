-- ESQUEMA para BD-R SQLite libreria

create table escritor (
    codigo              integer not null,
    nombre              text    not null,
    nacionalidad        text    not null,
    fecha_nacimiento    text    not null,     -- formato AAAA-MM-DD
    fecha_fallecimiento text    default null, -- formato AAAA-MM-DD
    primary key (codigo autoincrement),
    unique (nombre),
    check (fecha_fallecimiento is null or fecha_fallecimiento > fecha_nacimiento)
);

create table libro (
    codigo           integer not null,
    codigo_escritor  integer not null,
    titulo           text    not null,
    anio_publicacion integer not null,
    extension        integer not null, -- páginas
    precio           real    not null, -- euros
    primary key (codigo autoincrement),
    foreign key (codigo_escritor) references escritor(codigo)
);

-- DATOS para BD-R SQLite libreria

insert into escritor
values (1, 'Alexandre Dumas', 'francesa', '1802-07-24', '1870-12-05');
insert into escritor
values (2, 'Jules Verne', 'francesa', '1828-02-08', '1905-03-24');
insert into escritor
values (3, 'Miguel de Unamuno', 'española', '1864-09-29', '1936-12-31');
insert into escritor
values (4, 'J. R. R. Tolkien', 'británica', '1892-01-03', '1973-09-02');
insert into escritor
values (5, 'Frank Herbert', 'estadounidense', '1920-10-08', '1986-02-11');
insert into escritor
values (6, 'George R. R. Martin', 'estadounidense', '1948-09-20', null);
insert into escritor
values (7, 'Arturo Perez-Reverte', 'española', '1951-11-25', null);
insert into escritor
values (8, 'J. K. Rowling', 'británica', '1965-07-31', null);

insert into libro
values (1, 1, 'Los Tres Mosqueteros', 1844, 1312, 27.90);
insert into libro
values (2, 1, 'El Conde de Montecristo', 1845, 1144, 27.90);
insert into libro
values (3, 1, 'Veinte Años Después', 1845, 1020, 23.80);
insert into libro
values (4, 1, 'El Vizconde de Bragelonne', 1847, 1920, 36.20);
insert into libro
values (5, 2, 'Cinco Semanas en Globo', 1863, 288, 15.10);
insert into libro
values (6, 2, 'Viaje al Centro de la Tierra', 1864, 256, 15.10);
insert into libro
values (7, 2, 'De la Tierra a la Luna', 1865, 288, 15.10);
insert into libro
values (8, 2, 'Veinte Mil Leguas de Viaje Submarino', 1869, 288, 15.10);
insert into libro
values (9, 2, 'La Vuelta al Mundo en Ochenta Días', 1873, 240, 15.10);
insert into libro
values (10, 2, 'La Isla Misteriosa', 1874, 288, 15.10);
insert into libro
values (11, 2, 'Miguel Strogoff', 1876, 288, 15.10);
insert into libro
values (12, 4, 'El Hobbit', 1937, 304, 21.75);
insert into libro
values (13, 4, 'El Señor de los Anillos', 1955, 1392, 66.25);
insert into libro
values (14, 5, 'Dune', 1965, 784, 20.95);
insert into libro
values (15, 5, 'El Mesías de Dune', 1969, 304, 20.95);
insert into libro
values (16, 5, 'Hijos de Dune', 1976, 608, 20.95);
insert into libro
values (17, 6, 'Juego de Tronos', 1996, 800, 24.60);
insert into libro
values (18, 6, 'Choque de Reyes', 1998, 936, 24.60);
insert into libro
values (19, 6, 'Tormenta de Espadas', 2000, 1176, 28.40);
insert into libro
values (20, 6, 'Festín de Cuervos', 2005, 872, 24.60);
insert into libro
values (21, 6, 'Danza de Dragones', 2011, 1136, 28.40);
insert into libro
values (22, 7, 'El Maestro de Esgrima', 1988, 336, 20.25);
insert into libro
values (23, 7, 'La Tabla de Flandes', 1990, 416, 21.25);
insert into libro
values (24, 7, 'El Club Dumas', 1993, 448, 21.25);
insert into libro
values (25, 7, 'El Capitán Alatriste', 1996, 248, 17.95);
insert into libro
values (26, 7, 'Limpieza de Sangre', 1997, 264, 17.95);
insert into libro
values (27, 7, 'El Sol de Breda', 1998, 264, 17.95);
insert into libro
values (28, 7, 'El Oro del Rey', 2000, 280, 17.95);
insert into libro
values (29, 7, 'El Caballero del Jubón Amarillo', 2003, 360, 19.50);
insert into libro
values (30, 7, 'Corsarios de Levante', 2006, 360, 19.50);
insert into libro
values (31, 7, 'El Puente de los Asesinos', 2011, 368, 19.50);
insert into libro
values (32, 8, 'Harry Potter y la Piedra Filosofal', 1997, 264, 19.20);
insert into libro
values (33, 8, 'Harry Potter y la Cámara Secreta', 1998, 296, 19.20);
insert into libro
values (34, 8, 'Harry Potter y el Prisionero de Azkaban', 1999, 352, 19.20);
insert into libro
values (35, 8, 'Harry Potter y el Cáliz de Fuego', 2000, 672, 21.80);
insert into libro
values (36, 8, 'Harry Potter y la Orden del Fenix', 2003, 928, 23.20);
insert into libro
values (37, 8, 'Harry Potter y el Misterio del Príncipe', 2005, 576, 21.80);
insert into libro
values (38, 8, 'Harry Potter y las Reliquias de la Muerte', 2007, 704, 21.80);


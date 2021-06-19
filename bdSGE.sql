create table profesor
(
	idProfesor integer not null
		constraint profesor_pk
			primary key autoincrement,
	idLinea integer not null,
	nombre text not null,
	linea text not null
);

create unique index profesor_idProfesor_uindex
	on profesor (idProfesor);

create table alumno
(
	idAlumno integer not null
		constraint alumno_pk
			primary key autoincrement,
	nombre text not null,
	apellidos text not null
);

create unique index alumno_idAlumno_uindex
	on alumno (idAlumno);

create table proyecto
(
	idProyecto integer not null
		constraint proyecto_pk
			primary key autoincrement
		references alumno
		references profesor,
	nombre text not null,
	empresa text not null,
	descripcion text not null,
	lgac text not null
);

create unique index proyecto_idProyecto_uindex
	on proyecto (idProyecto);

create table reporte
(
	idReporte integer not null
		constraint reporte_pk
			primary key autoincrement
		references proyecto,
	parte integer not null,
	version integer not null,
	aprovado integer not null
);

create unique index reporte_idReporte_uindex
	on reporte (idReporte);


create schema if not exists vferoma;
use VFEROMA;

create table Instituto(
	id_Instituto int not null auto_increment,
    qtd_Alunos int not null,
    localidade varchar(45) not null,
    cod_Grafo varchar(200) not null,
	primary key (id_Instituto)
);

create table Usuario(
	login int not null auto_increment primary key,
    email varchar(50) not null,
    senha varchar(45) not null,
    adm boolean not null,
    nome varchar(70) not null,
    cpf varchar(11),
    cargo varchar(45),
    funcao varchar(45),
    instituto_Id_Instituto int,
    foreign key (instituto_Id_Instituto) references Instituto (id_Instituto) on delete cascade on update cascade
);

create table Tipo_Lixo(
	id_Tipo_Lixo int not null auto_increment,
    tipo_de_lixo varchar(45) not null,
    descricao varchar(200) not null,
    facilidade_reciclagem varchar (100),
    primary key (id_Tipo_Lixo)
);

create table Lixeira(
	id_Lixeira int not null auto_increment,
    qtd_Coletada double not null,
    localidade_Lixeira varchar(45) not null,
    qtd_Coletada_Total double not null,
    insituto_Id_Instituto int not null,
    tipo_Id_Tipo_Lixo int not null,
    primary key (id_Lixeira),
    foreign key (insituto_Id_Instituto) references Instituto (id_Instituto) on delete cascade on update cascade,
    foreign key (tipo_Id_Tipo_Lixo) references Tipo_Lixo (id_Tipo_Lixo) on delete cascade on update cascade
);

create table Mudanca(
	descricao varchar(500) not null,
    id_mudanca int auto_increment not null primary key,
    id_adm int not null,
    cod_Grafo varchar(200),
    foreign key (id_adm) references Usuario (login) on delete cascade on update cascade
);

create table Feedback(
	nome_if varchar(50) not null,
	feedback_Usuario varchar(500) not null,
    id_aluno int not null,
    id_feedback int auto_increment not null primary key,
    foreign key (id_aluno) references Usuario (login) on delete cascade on update cascade
);

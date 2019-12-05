create database VFEROMA;
use VFEROMA;

#GRANT SELECT, INSERT, UPDATE, DELETE ON * TO 'UsuarioBean'@'localhost';
#GRANT SELECT, INSERT, UPDATE, DELETE ON * TO 'root'@'localhost';

create table Instituto(
	id_Instituto int not null auto_increment,
    qtd_Alunos int not null,
    localidade varchar(45) not null,
    cod_Grafo varchar(200) not null,
    campus varchar(20) not null,
	primary key (id_Instituto)
);

create table Usuario(
	usuario varchar(15) not null  primary key,
    email varchar(50) not null,
    senha varchar(45) not null,
    confirmaSenha varchar(45) not null,
    adm boolean not null default false,
    nome varchar(70) not null,
    cpf varchar(11),
    cargo varchar(45),
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


create table Feedback(
	id_Instituto int,
	feedback_Usuario varchar(1000) not null,
    #id_aluno varchar(8) not null,
    id_feedback int primary key auto_increment not null,
    foreign key (id_Instituto) references Instituto (id_Instituto) on delete cascade on update cascade
    #foreign key (id_aluno) references Usuario (login) on delete cascade on update cascade
);

create table Mudanca(
	descricao varchar(500) not null,
    id_mudanca int auto_increment not null primary key,
    id_adm varchar(70) not null,
    cod_Grafo varchar(200),
    foreign key (id_adm) references Usuario (usuario) on delete cascade on update cascade
);

insert into Instituto values (null, 600, ' Rodovia MGC 262 Sobradinho, MG', 'inserir', 'Campus Sabará');
insert into Instituto values (null, 1400, ' R. Itaguaçu, 595 - São Caetano, Betim - MG', 'inserir', 'Betim');

insert into Usuario values ('rogrigo_', 'rodrigomalaquias5@gmail.com', '12345678', '12345678', false, 'Rodrigo Malaquias', '11111111111', 'developer', 1);
insert into Usuario values ('teteu123', 'mateusfilipe557@gmail.com', '12345678', '12345678', false, 'Mateus Filipe', '22222222222', 'developer', 1);
insert into Usuario values ('_vruno', 'brunovictorvasconcelos@gmail.com', '12345678', '12345678', false, 'Bruno Vitor', '33333333333', 'developer', 1);
insert into Usuario values ('marcos8370', 'marcospires8370@gmail.com', '12345678', '12345678', true, 'Marcos Pires', '44444444444', 'developer', 1);
insert into Usuario values ('_bimbole', 'felipe.rosa100@gmail.com', '12345678', '12345678', false, 'Felipe Urias', '55555555555', 'developer', 1);

insert into tipo_lixo values (null,'Orgânica','Lixo orgânico é composto basicamente por restos de alimento. Este tipo de lixo deve ser separado dos demais, pois costuma ser destinado a aterros sanitários','Médio');
insert into tipo_lixo values (null,'Reciclável','É o lixo que pode ser transformado em outros materiais. Deve ser separado e enviado à coleta seletiva para que chegue às cooperativas de reciclagem.','Médio');
insert into tipo_lixo values (null,'Verde','Resultado da poda de árvores ou do recolhimento de folhas nas ruas. Por ser orgânico, pode ser utilizado para compostagem.','Difícil');
insert into tipo_lixo values (null,'Eletrônico','É gerado pelo descarte de eletrônicos que entraram em desuso. Entre os exemplos estão TVs, rádios, computadores e telefones.','Difícil');

insert into Lixeira values (null, 150, '12345678', 200, 1, 2);
insert into Lixeira values (null, 100, '12345678', 140, 2, 3);
insert into Lixeira values (null, 130, '12345678', 150, 1, 1);

insert into Feedback values (1, 'Poderiam colocar a lixeira 4 mais proxima da entrada, é desgastante ter que desviar do caminho para jogar o lixo na lixeira', null);
insert into Feedback values (2, 'A disposição da lixeira 3 está perfeita', null);
insert into Feedback values (2, 'Poderiam colocar mais lixeiras proximo ao predio 3', null);
insert into Feedback values (1, 'A disposição das lixeiras na entrada do instituto surtiram muito efeito, parabens!!', null);

select * from Usuario;
select * from Instituto;
select * from Lixeira;
select * from Feedback;
select * from Tipo_Lixo;

show tables;
create table localizacao(
id_localizacao SERIAL primary key,
planeta varchar(255),
base varchar(255)
);

create  table inventario(
id_inventario SERIAL primary key,
arma varchar(255),
armadura varchar(255),
magia varchar(200)
);

create table rebeldes(
id_rebelde SERIAL primary key,
nome varchar(255),
idade Integer,
genero varchar(255),
ativo boolean,
id_rebelde_localizacao Integer,
id_rebelde_inventario Integer,
foreign  key(id_rebelde_localizacao) references localizacao(id_localizacao),
foreign  key(id_rebelde_inventario) references inventario(id_inventario)
);
alter  table rebeldes add column qtd_denuncias Integer;

create table compras(
id_compras SERIAL primary key,
item varchar(255)
);

create table compradores(
id_compras_item Integer,
id_compras_rebelde Integer,
foreign key(id_compras_item) references compras(id_compras),
foreign key(id_compras_rebelde) references rebeldes(id_rebelde)                  
);


insert  into localizacao(planeta,base) values ('KGS','KTHUJ');
insert  into localizacao(planeta,base) values ('Terra','GHJUTY');
insert  into localizacao(planeta,base) values ('Athena','2-PYK');
insert  into localizacao(planeta,base) values ('Kolog','023-BG');
insert  into localizacao(planeta,base) values ('FRDEFXUG','FR-45');
insert  into localizacao(planeta,base) values ('IXTAL','Comungu');
insert  into localizacao(planeta,base) values ('Ionia','Floresta vivente');
insert  into localizacao(planeta,base) values ('Freijord','Montanha glacinata');
insert  into localizacao(planeta,base) values ('DES-RTH','089-45');
insert  into localizacao(planeta,base) values ('Palutena','DFVBN');

insert into inventario(arma,armadura,magia) values ('Sabre de Luz','Metamórfica','Elemental');
insert into inventario(arma,armadura,magia) values ('Sabre da escuridão','Metal pesado','Negra');
insert into inventario(arma,armadura,magia) values ('Lança','Pele Alienigena','Luz');
insert into inventario(arma,armadura,magia) values ('Arco Reluzente','Angelical','Natureza');


select * from rebeldes ;
select * from localizacao;


insert into rebeldes(nome,idade,genero,ativo,id_rebelde_localizacao,id_rebelde_inventario,qtd_denuncias) 
values ('Muryllo',21,'Humano',true,2,1,0);

insert into rebeldes(nome,idade,genero,ativo,id_rebelde_localizacao,id_rebelde_inventario,qtd_denuncias) 
values ('Kog-Maw',255,'Vazio',true,5,4,0);

insert into rebeldes(nome,idade,genero,ativo,id_rebelde_localizacao,id_rebelde_inventario,qtd_denuncias) 
values ('Mattheus',25,null,true,1,3,0);

insert into rebeldes(nome,idade,genero,ativo,id_rebelde_localizacao,id_rebelde_inventario,qtd_denuncias) 
values ('Nidalee',40,'Vastaya',true,3,2,0);

insert into rebeldes(nome,idade,genero,ativo,id_rebelde_localizacao,id_rebelde_inventario,qtd_denuncias) 
values ('Neeko- cho',180,'Alienigena',true,null,1,0);

insert into rebeldes(nome,idade,genero,ativo,id_rebelde_localizacao,id_rebelde_inventario,qtd_denuncias) 
values ('StemGrog',200,null,true,null,2,0);

insert into rebeldes(nome,idade,genero,ativo,id_rebelde_localizacao,id_rebelde_inventario,qtd_denuncias) 
values ('Taliah',25,'Humana',true,8,4,0);

insert into rebeldes(nome,idade,genero,ativo,id_rebelde_localizacao,id_rebelde_inventario,qtd_denuncias) 
values ('Gayah',500,'Arvore divina',true,9,3,0);
select qtd_denuncias+1 from rebeldes;

insert  into compras(item) values ('Água');
insert  into compras(item) values ('Comida');
insert  into compras(item) values ('Arma');
insert  into compras(item) values ('Armadura');

insert  into compradores(id_compras_item,id_compras_rebelde) values (1,1);
insert  into compradores(id_compras_item,id_compras_rebelde) values (2,1);
insert  into compradores(id_compras_item,id_compras_rebelde) values (3,4);
insert  into compradores(id_compras_item,id_compras_rebelde) values (4,4);


-- Fazendo todos os Joins

-- inner join
select id_rebelde,nome,idade,genero,ativo, planeta,base from rebeldes
inner join localizacao
on
id_rebelde_localizacao=id_localizacao;
;

-- full join
select id_rebelde,nome,idade,genero,ativo, planeta,base from rebeldes
full join localizacao
on
id_rebelde_localizacao=id_localizacao;
;

-- left join
select id_rebelde,nome,idade,genero,ativo,qtd_denuncias,planeta,base,arma,armadura,magia from rebeldes
left join localizacao
on
id_rebelde_localizacao=id_localizacao
left join inventario
on
id_rebelde_inventario=id_inventario
order by id_rebelde asc
;

-- right join
select id_rebelde,nome,idade,genero,ativo, planeta,base from rebeldes
right join localizacao
on
id_rebelde_localizacao=id_localizacao;
;

select distinct  rebeldes.nome,rebeldes.ativo from compradores 
inner join rebeldes
on id_compras_rebelde = id_rebelde 
where id_compras_rebelde=1
;
select  compras.item from compradores 
inner join compras 
on id_compras_item = id_compras
where id_compras_rebelde=1
;

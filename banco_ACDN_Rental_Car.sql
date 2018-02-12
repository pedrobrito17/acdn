create database acdn_bd;

use acdn_bd;

insert into classes_carros (classe,valor) values('subcompacto', 90),('compacto',105),
	('tamanho médio',118),('tamanho grande',126),('luxo',155);

describe reserva;
select * from cliente;
select * from reserva;
select * from carro;

delete from cliente where cpf='all';
describe classes_carros;

insert into reserva (qtd_diarias, situacao, valor_total, cpf) values(2, 'finalizada', 90, '00188193383');
select * from classes_carros;

update classes_carros set valor=90 where id_classes='8';
update reserva set situacao='finalizada' WHERE num_reserva='10';
update carro set descricao='alguns arranhões na lateral e porta malas' where placa='OXW-0754';

delete from reserva where num_reserva='3';
create database bd_pedemeia;

use bd_pedemeia;

CREATE TABLE conta (
id_conta int(11) NOT NULL AUTO_INCREMENT,
nome varchar(60) NOT NULL,
email varchar(60) NOT NULL,
senha varchar(60) NOT NULL,
avatar varchar(200) DEFAULT NULL,
PRIMARY KEY (id_conta)
);


CREATE TABLE cartao_debito (
n_cartao_debito bigint NOT NULL ,
valor_atual float NOT NULL,
bandeira varchar(60) NOT NULL,
conta_id_conta int(11) NOT NULL,
PRIMARY KEY (n_cartao_debito),
FOREIGN KEY (conta_id_conta) REFERENCES
conta (id_conta) ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE cartao_credito (

n_cartao_credito bigint NOT NULL ,
limite float NOT NULL,
dia_fatura int(11) NOT NULL,
valor_fatura float NOT NULL,
bandeira varchar(60) NOT NULL,
conta_id_conta int(11) NOT NULL,
PRIMARY KEY (n_cartao_credito),
FOREIGN KEY (conta_id_conta) REFERENCES
conta (id_conta) ON DELETE CASCADE ON UPDATE CASCADE

);

create table receita_data(

cod_receita int not null auto_increment,
dia int not null,
mes int not null,
ano int not null,
primary key(cod_receita)

);


create table receita(

receita_data_cod_receita int not null,
total float DEFAULT null,
conta_id_conta int not null, 
foreign key (conta_id_conta) 
references conta (id_conta) on delete cascade on update cascade,
foreign key (receita_data_cod_receita) 
references receita_data (cod_receita) on delete cascade on update cascade

);


CREATE TABLE despesa_data (

cod_despesa int(11) NOT NULL auto_increment,
receita_data_cod_receita int(11) NOT NULL,
dia int(11) NOT NULL,
mes int(11) NOT NULL,
ano int(11) NOT NULL,
primary key(cod_despesa),
FOREIGN KEY (receita_data_cod_receita) REFERENCES
receita_data (cod_receita) ON DELETE CASCADE ON UPDATE CASCADE

);



CREATE TABLE despesa (

despesa_data_cod_despesa int(11) NOT NULL,
valor float NOT NULL,
categoria varchar(45) NOT NULL,
descricao varchar(250) DEFAULT NULL,
f_pagamento varchar(45) NOT NULL,
cartao_debito_n_cartao_debito bigint DEFAULT NULL,
cartao_credito_n_cartao_credito bigint DEFAULT NULL,
estatus varchar(20) NOT NULL,
conta_id_conta int(11) NOT NULL,
FOREIGN KEY (conta_id_conta) REFERENCES conta (id_conta) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (despesa_data_cod_despesa) REFERENCES
despesa_data (cod_despesa) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cartao_debito_n_cartao_debito) REFERENCES cartao_debito (n_cartao_debito) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cartao_credito_n_cartao_credito) REFERENCES cartao_credito (n_cartao_credito) ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE despesa_credito (

n_parcelas int(11) NOT NULL,
despesa_data_cod_despesa int(11) NOT NULL,
FOREIGN KEY (despesa_data_cod_despesa) REFERENCES
despesa_data (cod_despesa) ON DELETE CASCADE ON UPDATE CASCADE

);




#Inserindo valores


#Conta

INSERT INTO conta (nome,email,senha)
VALUES("ALAN","alanstark@gmail.com", 123);
INSERT INTO conta (nome,email,senha)
VALUES("MARIA","lala@gmail.com", 122);
INSERT INTO conta (nome,email,senha)
VALUES("ALESSANDRA","tutu@gmail.com", 333);
INSERT INTO conta (nome,email,senha)
VALUES("TERESA","kaka@gmail.com", 444);
INSERT INTO conta (nome,email,senha)
VALUES("RAIMUNDO","maria@gmail.com", 555);
INSERT INTO conta (nome,email,senha)
VALUES("DAVI","boladefogo@gmail.com", 666);
INSERT INTO conta (nome,email,senha)
VALUES("MARLON","bomdebriga@gmail.com", 777);
INSERT INTO conta (nome,email,senha)
VALUES("DIRLIA","lindadamae@gmail.com", 888);
INSERT INTO conta (nome,email,senha)
VALUES("REGINA","jobs@gmail.com", 999);
INSERT INTO conta (nome,email,senha)
VALUES("JOBS","alfredo@gmail.com", 111);
INSERT INTO conta (nome,email,senha)
VALUES("LAYLA","hermes@gmail.com", 222);
INSERT INTO conta (nome,email,senha)
VALUES("KARINE","zeus@gmail.com", 332);
INSERT INTO conta (nome,email,senha)
VALUES("JULIA","thor@gmail.com", 221);
INSERT INTO conta (nome,email,senha)
VALUES("CRISTINA","loki@gmail.com", 445);
INSERT INTO conta (nome,email,senha)
VALUES("ADRIANA","athena@gmail.com", 667);
INSERT INTO conta (nome,email,senha)
VALUES("ROGEIRO","naruto@gmail.com", 889);
INSERT INTO conta (nome,email,senha)
VALUES("ANA","ichigo@gmail.com", 988);
INSERT INTO conta (nome,email,senha)
VALUES("CLARA","bakugo@gmail.com", 543);
INSERT INTO conta (nome,email,senha)
VALUES("PATRICIA","tom@gmail.com", 234);
INSERT INTO conta (nome,email,senha)
VALUES("JOANA","tururu@gmail.com", 453);

#Cartão de credito

INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(5402464256786436, 200.00, 1200.00, 12, "MASTERCARD", 1);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(5339614010424884, 100.00, 12700.00, 11, "ELO", 4);
INSERT INTO cartao_credito(n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(5376135993298206, 150.00, 1400.00, 17, "VISA", 6);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(5513474009191087, 240.00, 1600.00, 20, "MASTERCARD", 8);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(5290461435867355, 220.00, 1900.00, 1, "MASTERCARD", 2);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(5509872318609200, 110.00, 15600.00, 16, "VISA", 5);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(5399281592280295 ,112.00, 11100.00, 25, "MASTERCARD", 7);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(5393080943450730, 300.00, 12900.00, 14, "VISA", 3);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(5340349537691502, 400.00, 3600.00, 8, "ELO", 10);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(5211962375229981, 600.00, 4900.00, 19, "MASTERCARD", 9);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(5492766980608202, 530.00, 6200.00, 20, "ELO", 15);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(4024007144661955, 70.00, 12900.00, 6, "MASTERCARD", 12);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(4929212774788591, 400.00, 4689.00, 11, "MASTERCARD", 11);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(4671742332509642, 452.00, 16710.00, 17, "VISA", 14);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(4024007130173411, 234.00, 1698.00, 15, "ELO", 13);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(4929701861005489, 178.00, 3978.00, 18, "MASTERCARD", 19);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(4556240699893873, 160.00, 7856.00, 16, "VISA", 16);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira, conta_id_conta)
VALUES(4539985320844966, 125.00, 3974.00, 14, "VISA", 18);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(4716574779316232, 350.00, 1596.00, 16, "MASTERCARD", 20);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,conta_id_conta)
VALUES(4556318364323101, 1000.00, 2397.00, 13, "ELO", 17);

#Cartão débito

INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(4716140503513062, 200.00, "MASTERCARD", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(4532325278607205, 100.00, "ELO", 5);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(6011365210948403, 150.00, "VISA", 4);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(6011665697125619, 240.00, "MASTERCARD", 2);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(6011196972036388, 220.00, "MASTERCARD", 3);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(3551955896655422, 110.00, "VISA", 10);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(6011960143642873, 112.00, "MASTERCARD", 9);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(6011233259347194, 300.00, "VISA", 8);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(6011984442617329, 400.00, "ELO", 20);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(4904628247877647, 600.00, "MASTERCARD", 11);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(4716717904817299, 530.00, "ELO", 12);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(4539521731323397, 70.00, "MASTERCARD", 14);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(4929158207474392, 400.00, "MASTERCARD", 17);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(3520549735882467, 452.00, "VISA", 13);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(3582141761389834, 234.00, "ELO", 15);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(3579254353604562, 178.00, "MASTERCARD", 19);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(3563546609001735, 160.00, "VISA", 18);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(3585017634246723, 125.00, "VISA", 16);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(3593658086102979, 350.00, "MASTERCARD", 6);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(3526940881025759, 1000.00, "ELO", 7);

#Receita data

INSERT INTO receita_data (dia, mes, ano) VALUES(1, 1, 2019);
INSERT INTO receita_data (dia, mes, ano) VALUES(4, 6, 2019);
INSERT INTO receita_data (dia, mes, ano) VALUES(5, 7, 2017);
INSERT INTO receita_data (dia, mes, ano) VALUES(4, 6, 2018);
INSERT INTO receita_data (dia, mes, ano) VALUES(15, 9, 2020);
INSERT INTO receita_data (dia, mes, ano) VALUES(17, 2, 2021);
INSERT INTO receita_data (dia, mes, ano) VALUES(28, 7, 2021);
INSERT INTO receita_data (dia, mes, ano) VALUES(30, 4, 2018);
INSERT INTO receita_data (dia, mes, ano) VALUES(22, 5, 2016);
INSERT INTO receita_data (dia, mes, ano) VALUES(17, 11, 2017);
INSERT INTO receita_data (dia, mes, ano) VALUES(20, 8, 2020);
INSERT INTO receita_data (dia, mes, ano) VALUES(2, 5, 2021);
INSERT INTO receita_data (dia, mes, ano) VALUES(7, 6, 2016);
INSERT INTO receita_data (dia, mes, ano) VALUES(9, 4, 2018);
INSERT INTO receita_data (dia, mes, ano) VALUES(6, 8, 2018);
INSERT INTO receita_data (dia, mes, ano) VALUES(19, 5, 2020);
INSERT INTO receita_data (dia, mes, ano) VALUES(12, 6, 2018);
INSERT INTO receita_data (dia, mes, ano) VALUES(17, 3, 2021);
INSERT INTO receita_data (dia, mes, ano) VALUES(26, 12, 2020);
INSERT INTO receita_data (dia, mes, ano) VALUES(24, 12, 2021);

#Receita


INSERT INTO receita (total, receita_data_cod_receita, conta_id_conta) VALUES(1500, 1 ,2);
INSERT INTO receita (total, receita_data_cod_receita, conta_id_conta) VALUES(2000, 2,3);
INSERT INTO receita (total, receita_data_cod_receita, conta_id_conta) VALUES (2500,3 ,4);
INSERT INTO receita (total, receita_data_cod_receita, conta_id_conta) VALUES (3000, 4,5);
INSERT INTO receita (total, receita_data_cod_receita, conta_id_conta) VALUES (3500, 5,6);
INSERT INTO receita (total, receita_data_cod_receita, conta_id_conta) VALUES (4000, 6,7);
INSERT INTO receita (total, receita_data_cod_receita, conta_id_conta) VALUES (4500, 7,8);
INSERT INTO receita (total, receita_data_cod_receita, conta_id_conta) VALUES (5000, 8,9);
INSERT INTO receita (total, receita_data_cod_receita, conta_id_conta) VALUES (5500, 9,10);
INSERT INTO receita (total, receita_data_cod_receita, conta_id_conta) VALUES (7000, 10,11);
INSERT INTO receita (total, receita_data_cod_receita, conta_id_conta) VALUES (9000, 11,12);
INSERT INTO receita (total, receita_data_cod_receita, conta_id_conta) VALUES (11000, 12,13);
INSERT INTO receita (total, receita_data_cod_receita, conta_id_conta) VALUES (12000, 13,14);
INSERT INTO receita (total, receita_data_cod_receita, conta_id_conta) VALUES (13000, 14,15);
INSERT INTO receita (total, receita_data_cod_receita, conta_id_conta) VALUES (13500, 15,16);
INSERT INTO receita (total, receita_data_cod_receita, conta_id_conta) VALUES (140000, 16,17);
INSERT INTO receita (total, receita_data_cod_receita, conta_id_conta) VALUES (15000, 17,18);
INSERT INTO receita (total, receita_data_cod_receita, conta_id_conta) VALUES (16000, 18,19);


#Despesa data

insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (2, 01,04,2001);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (3, 02,05,2002);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (4, 03,01,2003);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (5, 04,02,2004);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (6, 05,03,2005);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (7, 06,01,2006);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (8, 07,02,2007);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (9, 08,03,2008);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (10, 09,06,2008);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (11, 10,07,2009);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (12, 11,12,2010);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (13, 12,09,2011);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (14, 13,08,2012);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (15, 14,07,2013);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (16, 15,06,2014);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (17, 16,05,2015);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (18, 17,04,2016);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (19, 18,01,2017);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (20, 19,02,2018);
insert into despesa_data(receita_data_cod_receita, dia, mes, ano) values (20, 20,03,2019);

#Despesa

insert into despesa(cartao_debito_n_cartao_debito, despesa_data_cod_despesa, valor, categoria, f_pagamento, estatus, conta_id_conta) values
(3526940881025759, 1, 999.00, "CARRO", "DÉBITO", "PAGO", 1);
insert into despesa(cartao_debito_n_cartao_debito, despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(3526940881025759, 2,666.00, "CASA", "DÉBITO", "PAGO", 4);
insert into despesa(cartao_debito_n_cartao_debito, despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(3526940881025759, 3,333.00, "CARRO", "DÉBITO", "PAGO", 7);
insert into despesa(cartao_debito_n_cartao_debito, despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(3526940881025759, 4,444.00, "CASA", "DÉBITO", "PAGO", 6);
insert into despesa(cartao_debito_n_cartao_debito, despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(3526940881025759, 5,210.00, "CARRO", "DÉBITO", "PAGO", 9);


insert into despesa(despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(6, 80.00, "CARRO", "DINHEIRO", "NÃO PAGO", 11);
insert into despesa(despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(7, 50.00, "CASA", "DINHEIRO", "PAGO", 14);
insert into despesa(despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(8, 120.00, "CASA", "DINHEIRO", "PAGO", 18);
insert into despesa(despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(9, 230.00, "CARRO", "DINHEIRO", "PAGO", 19);
insert into despesa(despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(10, 300.00, "CARRO", "DINHEIRO", "NÃO PAGO", 20);



insert into despesa(cartao_credito_n_cartao_credito, despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(4556318364323101, 11, 888.00, "CARRO", "CRÉDITO", "NÃO PAGO", 2);
insert into despesa(cartao_credito_n_cartao_credito, despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(4556318364323101, 12, 777.00, "CASA", "CRÉDITO", "PAGO", 3);
insert into despesa(cartao_credito_n_cartao_credito, despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(4556318364323101, 13, 555.00, "CARRO", "CRÉDITO", "NÃO PAGO", 5);
insert into despesa(cartao_credito_n_cartao_credito, despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(4556318364323101, 14, 222.00, "CASA", "CRÉDITO", "NÃO PAGO", 8);
insert into despesa(cartao_credito_n_cartao_credito, despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(4556318364323101, 15, 90.00, "CASA", "CRÉDITO", "PAGO", 10);
insert into despesa(cartao_credito_n_cartao_credito, despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(4556318364323101, 16, 70.00, "CASA", "CRÉDITO", "PAGO", 12);
insert into despesa(cartao_credito_n_cartao_credito, despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(4556318364323101, 17, 60.00, "CARRO", "CRÉDITO", "PAGO", 13);
insert into despesa(cartao_credito_n_cartao_credito, despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(4556318364323101, 18, 40.00, "CARRO", "CRÉDITO", "NÃO PAGO", 15);
insert into despesa(cartao_credito_n_cartao_credito, despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(4556318364323101, 19, 20.00, "CASA", "DÉBITO", "PAGO", 16);
insert into despesa(cartao_credito_n_cartao_credito, despesa_data_cod_despesa,valor, categoria, f_pagamento, estatus, conta_id_conta) values
(4556318364323101, 20, 10.00, "CARRO", "CRÉDITO", "NÃO PAGO", 17);



#Despesa credito

insert into despesa_credito(n_parcelas, despesa_data_cod_despesa) values(12, 11);
insert into despesa_credito(n_parcelas, despesa_data_cod_despesa) values(09, 12);
insert into despesa_credito(n_parcelas, despesa_data_cod_despesa) values(08, 13);
insert into despesa_credito(n_parcelas, despesa_data_cod_despesa) values(07, 14);
insert into despesa_credito(n_parcelas, despesa_data_cod_despesa) values(06, 15);
insert into despesa_credito(n_parcelas, despesa_data_cod_despesa) values(05, 16);
insert into despesa_credito(n_parcelas, despesa_data_cod_despesa) values(02, 17);
insert into despesa_credito(n_parcelas, despesa_data_cod_despesa) values(01, 18);
insert into despesa_credito(n_parcelas, despesa_data_cod_despesa) values(10, 19);
insert into despesa_credito(n_parcelas, despesa_data_cod_despesa) values(11, 20);







/*

#Conta:

INSERT INTO conta (nome,email,senha) VALUES("Teste01","teste@gmail.com", "1234adf");

select * from conta;

#Obs: O avatar não foi definido porque ele pode ser nulo

delete from conta where id_conta=21;

select * from conta;

update conta set nome="NovoNome" where id_conta=21;

select * from conta;

#Cartão de credito -----------------------------------------------------------------------------------------

INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, dia_fatura, bandeira,
conta_id_conta)

VALUES (3557125765636713, 15923.00, 6200.00, 20, "ELO", 15);

select * from cartao_credito;

update cartao_credito set dia_fatura= 17 where n_cartao_credito=3557125765636713;

select * from cartao_credito;

delete from cartao_credito where n_cartao_credito=3557125765636713;

select * from cartao_credito;

#Cartão de debito

INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(5043691823857119, 2900.00, "ELO", 15);

select * from cartao_debito;

update cartao_debito set valor_atual= 1700 where n_cartao_debito=5043691823857119;

select * from cartao_debito;

delete from cartao_debito where n_cartao_debito=5043691823857119;

select * from cartao_debito;

#Despesa -------------------------------------------------------------------------------------------

insert into despesa(valor, categoria, f_pagamento, estatus, conta_id_conta) values
(1500.00, "CASA", "CRÉDITO", "NÃO PAGO", 22);

select * from despesa;

delete from despesa where codigo_despesa=22;

select * FROM despesa;

update despesa set valor=25000 where codigo_despesa=24;

select * from despesa;

#Despesa_credito -------------------------------------------------------------------------------------------

insert into despesa_credito(n_parcelas, despesa_codigo_despesa) values(27, 24);

select * from despesa_credito;

delete from despesa_credito where despesa_codigo_despesa=24;

select * from despesa_credito;

update despesa_credito set n_parcelas=20 where despesa_codigo_despesa=24;

select * from despesa_credito;

#Despesa data -------------------------------------------------------------------------------

insert into despesa_data(dia, mes, ano, despesa_codigo_despesa) values (01,08,2021, 24);

select * from despesa_data;

delete from despesa_data where despesa_codigo_despesa=24;

select * from despesa_data;

update despesa_data set dia=02 where despesa_codigo_despesa=24;

select * from despesa_data;

#receita

INSERT INTO receita (total, conta_id) VALUES (9852, 22);

select * from receita;

delete from receita where codigo_receita=20;

select * from receita;

update receita set total=15983 where codigo_receita=21;

select * from receita;

#receita_data ------------------------------------------------------------------------------------

INSERT INTO receita_data VALUES(01, 08, 2021, 21);

select * from receita_data;

delete from receita_data where cod_receita=21;

select * from receita_data;

update receita_data set dia=02 where codigo_receita=21;

select * from receita

*/
















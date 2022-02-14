
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
credito float NOT NULL,
dia_fatura int(11) NOT NULL,
valor_fatura float NOT NULL,
bandeira varchar(60) NOT NULL,
conta_id_conta int(11) NOT NULL,
PRIMARY KEY (n_cartao_credito),
FOREIGN KEY (conta_id_conta) REFERENCES
conta (id_conta) ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE `receita` (
  `cod_receita` int(11) NOT NULL AUTO_INCREMENT,
  `dia` int(11) NOT NULL,
  `mes` int(11) NOT NULL,
  `ano` int(11) NOT NULL,
  `total` float NOT NULL,
  `conta_id_conta` int(11) NOT NULL,
  PRIMARY KEY (`cod_receita`),
  KEY `conta_id_conta` (`conta_id_conta`),
  CONSTRAINT `receita_conta_id_conta` FOREIGN KEY (`conta_id_conta`) 
  REFERENCES `conta` (`id_conta`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE categoria (
  categoriaId int(11) NOT NULL AUTO_INCREMENT,
  categoriaTipo varchar(50) NOT NULL,
  conta_id_conta int(11) NOT NULL,
  PRIMARY KEY (categoriaId),
  FOREIGN KEY (conta_id_conta) REFERENCES
conta (id_conta) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `despesa` (
  `cod_despesa` int(11) NOT NULL AUTO_INCREMENT,
  `dia` int(11) NOT NULL,
  `mes` int(11) NOT NULL,
  `ano` int(11) NOT NULL,
  `num_cartao_debito` bigint(20) DEFAULT NULL,
  `num_cartao_credito` bigint(20) DEFAULT NULL,
  `valor` float NOT NULL,
  `descricao` varchar(250) DEFAULT NULL,
  `f_pagamento` varchar(45) NOT NULL,
  `estatus` varchar(20) NOT NULL,
  `categoria_id` int(11) NOT NULL,
  `receita_cod_receita` int(11) NOT NULL,
  `conta_id_conta` int(11) NOT NULL,
  PRIMARY KEY (`cod_despesa`),
  KEY `conta_id_conta` (`conta_id_conta`),
  CONSTRAINT `despesa_receita_conta_id_conta` FOREIGN KEY (`conta_id_conta`) 
  REFERENCES `conta` (`id_conta`) ON DELETE CASCADE ON UPDATE CASCADE,
  
  KEY `receita_cod_receita` (`receita_cod_receita`),
  CONSTRAINT `despesa_receita_cod_receita` FOREIGN KEY (`receita_cod_receita`)
  REFERENCES `receita` (`cod_receita`) ON DELETE CASCADE ON UPDATE CASCADE,
  
  KEY `categoria_id` (`categoria_id`),
  CONSTRAINT `despesa_categoria_id` FOREIGN KEY (`categoria_id`)
  REFERENCES `categoria` (`categoriaId`) ON DELETE RESTRICT ON UPDATE CASCADE,
  
  KEY `num_cartao_debito` (`num_cartao_debito`),
  CONSTRAINT `despesa_num_cartao_debito` FOREIGN KEY (`num_cartao_debito`) 
  REFERENCES `cartao_debito` (`n_cartao_debito`) ON DELETE CASCADE ON UPDATE CASCADE,
  
  KEY `num_cartao_credito` (`num_cartao_credito`),
  CONSTRAINT `despesa_num_cartao_credito` FOREIGN KEY (`num_cartao_credito`) 
  REFERENCES `cartao_credito` (`n_cartao_credito`) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE despesa_credito (

n_parcelas int(11) NOT NULL,
valor_parcela float(12) NOT NULL,
n_parcelas_pagas int(11) NOT NULL,
despesa_cod_despesa int(11) NOT NULL,
FOREIGN KEY (despesa_cod_despesa) REFERENCES
despesa (cod_despesa) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO conta (nome,email,senha)
VALUES("ALAN","alanstark@gmail.com", 123);



#Receita

INSERT INTO receita (dia, mes, ano, conta_id_conta, total) VALUES(1, 07, 2009, 1, 80000);
INSERT INTO receita (dia, mes, ano, conta_id_conta, total) VALUES(4, 6, 2019, 1, 2000);
INSERT INTO receita (dia, mes, ano, conta_id_conta, total) VALUES (5, 7, 2017, 1, 2500);
INSERT INTO receita (dia, mes, ano, conta_id_conta, total) VALUES (4, 6, 2018, 1, 3000);
INSERT INTO receita (dia, mes, ano, conta_id_conta, total) VALUES (15, 9, 2020, 1, 3500);
INSERT INTO receita (dia, mes, ano, conta_id_conta, total) VALUES (17, 2, 2021, 1, 4000);
INSERT INTO receita (dia, mes, ano, conta_id_conta, total) VALUES (28, 7, 2021, 1, 4500);
INSERT INTO receita (dia, mes, ano, conta_id_conta, total) VALUES (30, 4, 2018, 1, 5000);
INSERT INTO receita (dia, mes, ano, conta_id_conta, total) VALUES (22, 5, 2016, 1, 5500);
INSERT INTO receita (dia, mes, ano, conta_id_conta, total) VALUES (17, 11, 2017, 1, 7000);
INSERT INTO receita (dia, mes, ano, conta_id_conta, total) VALUES (20, 8, 2020, 1, 9000);
INSERT INTO receita (dia, mes, ano, conta_id_conta, total) VALUES (2, 5, 2021, 1, 11000);
INSERT INTO receita (dia, mes, ano, conta_id_conta, total) VALUES (7, 6, 2016, 1, 12000);
INSERT INTO receita (dia, mes, ano, conta_id_conta, total) VALUES (9, 4, 2018, 1, 13000);
INSERT INTO receita (dia, mes, ano, conta_id_conta, total) VALUES (6, 8, 2018, 1, 13500);

#Cartão de credito

INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, credito, dia_fatura, bandeira,conta_id_conta)
VALUES(5402464256786436, 200.00, 1200.00, 1200.00, 12, "MASTERCARD", 1);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, credito, dia_fatura, bandeira,conta_id_conta)
VALUES(5339614010424884, 100.00, 12700.00, 12700.00, 11, "ELO", 1);
INSERT INTO cartao_credito(n_cartao_credito, valor_fatura, limite, credito, dia_fatura, bandeira,conta_id_conta)
VALUES(5376135993298206, 150.00, 1400.00, 1400.00, 17, "VISA", 1);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, credito, dia_fatura, bandeira,conta_id_conta)
VALUES(5513474009191087, 240.00, 1600.00, 1600.00, 20, "MASTERCARD", 1);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, credito, dia_fatura, bandeira,conta_id_conta)
VALUES(5290461435867355, 220.00, 1900.00, 1900.00, 1, "MASTERCARD", 1);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, credito, dia_fatura, bandeira,conta_id_conta)
VALUES(5509872318609200, 110.00, 15600.00, 15600.00, 16, "VISA", 1);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, credito, dia_fatura, bandeira,conta_id_conta)
VALUES(5399281592280295 ,112.00, 11100.00, 11100.00, 25, "MASTERCARD", 1);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, credito, dia_fatura, bandeira,conta_id_conta)
VALUES(5393080943450730, 300.00, 12900.00, 12900.00, 14, "VISA", 1);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, credito, dia_fatura, bandeira,conta_id_conta)
VALUES(5340349537691502, 400.00, 3600.00, 3600.00, 8, "ELO", 1);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, credito, dia_fatura, bandeira,conta_id_conta)
VALUES(5211962375229981, 600.00, 4900.00, 4900.00, 19, "MASTERCARD", 1);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, credito, dia_fatura, bandeira,conta_id_conta)
VALUES(5492766980608202, 530.00, 6200.00, 6200.00, 20, "ELO", 1);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, credito, dia_fatura, bandeira,conta_id_conta)
VALUES(4024007144661955, 70.00, 12900.00, 12900.00, 6, "MASTERCARD", 1);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, credito, dia_fatura, bandeira,conta_id_conta)
VALUES(4929212774788591, 400.00, 4689.00, 4689.00, 11, "MASTERCARD", 1);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, credito, dia_fatura, bandeira,conta_id_conta)
VALUES(4671742332509642, 452.00, 16710.00, 16710.00, 17, "VISA", 1);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, credito, dia_fatura, bandeira,conta_id_conta)
VALUES(4024007130173411, 234.00, 1698.00, 1698.00, 15, "ELO", 1);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, credito, dia_fatura, bandeira,conta_id_conta)
VALUES(4929701861005489, 178.00, 3978.00, 3978.00, 18, "MASTERCARD", 1);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, credito, dia_fatura, bandeira,conta_id_conta)
VALUES(4556240699893873, 160.00, 7856.00, 7856.00, 16, "VISA", 1);
INSERT INTO cartao_credito (n_cartao_credito, valor_fatura, limite, credito, dia_fatura, bandeira, conta_id_conta)
VALUES(4539985320844966, 125.00, 3974.00, 3974.00, 14, "VISA", 1);


#Cartão débito

INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(4716140503513062, 200.00, "MASTERCARD", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(4532325278607205, 100.00, "ELO", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(6011365210948403, 150.00, "VISA", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(6011665697125619, 240.00, "MASTERCARD", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(6011196972036388, 220.00, "MASTERCARD", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(3551955896655422, 110.00, "VISA", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(6011960143642873, 112.00, "MASTERCARD", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(6011233259347194, 300.00, "VISA", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(6011984442617329, 400.00, "ELO", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(4904628247877647, 600.00, "MASTERCARD", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(4716717904817299, 530.00, "ELO", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(4539521731323397, 70.00, "MASTERCARD", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(4929158207474392, 400.00, "MASTERCARD", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(3520549735882467, 452.00, "VISA", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(3582141761389834, 234.00, "ELO", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(3579254353604562, 178.00, "MASTERCARD", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(3563546609001735, 160.00, "VISA", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(3585017634246723, 125.00, "VISA", 1);
INSERT INTO cartao_debito (n_cartao_debito, valor_atual, bandeira, conta_id_conta)
VALUES(3593658086102979, 350.00, "MASTERCARD", 1);



#Categoria 

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("CARRO", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("MOTO", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("ALUGUEL", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("ROUPA", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("CASA", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("WIFI", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("LUZ", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("ÁGUA", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("CARRO", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("SAPATO", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("ALIMENTAÇÃO", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("TV", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("CELULAR", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("NOTEBOOK", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("MOCHILA", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("REFORMA", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("MÓVEIS", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("ELETRODOMÉSTICO", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("MATERIAL DE LIMPEZA", 1);

INSERT INTO categoria (categoriaTipo,conta_id_conta)
VALUES("BUGIGANGAS", 1);


#Despesa

insert into despesa(receita_cod_receita, dia, mes, ano, conta_id_conta, categoria_id, num_cartao_debito, valor, f_pagamento, estatus) values
(1, 01, 07, 2009, 1, 1, 4716140503513062, 999.00, "DÉBITO", "PAGO");
insert into despesa(receita_cod_receita, dia, mes, ano, conta_id_conta, categoria_id, num_cartao_debito, valor, f_pagamento, estatus) values
(1, 02, 07, 2009, 1, 2, 4532325278607205, 666.00, "DÉBITO", "PAGO");
insert into despesa(receita_cod_receita, dia, mes, ano, conta_id_conta, categoria_id, num_cartao_debito, valor, f_pagamento, estatus) values
(1, 03, 07, 2009, 1, 1, 6011365210948403, 333.00, "DÉBITO", "PAGO");
insert into despesa(receita_cod_receita, dia, mes, ano, conta_id_conta, categoria_id, num_cartao_debito, valor, f_pagamento, estatus) values
(1, 04, 07, 2009, 1, 4, 6011665697125619, 444.00, "DÉBITO", "PAGO");
insert into despesa(receita_cod_receita, dia, mes, ano, conta_id_conta, categoria_id, num_cartao_debito, valor, f_pagamento, estatus) values
(1, 05, 07, 2009, 1, 5, 6011196972036388, 210.00, "DÉBITO", "PAGO");


insert into despesa(receita_cod_receita, dia, mes, ano, conta_id_conta, categoria_id, valor, f_pagamento, estatus) values
(1, 06, 07, 2009, 1, 6, 80.00, "DINHEIRO", "PAGO");
insert into despesa(receita_cod_receita, dia, mes, ano, conta_id_conta, categoria_id, valor, f_pagamento, estatus) values
(1, 07, 07, 2009, 1, 7, 50.00, "DINHEIRO", "PAGO");
insert into despesa(receita_cod_receita, dia, mes, ano, conta_id_conta, categoria_id, valor, f_pagamento, estatus) values
(1, 08, 07, 2009, 1, 8, 120.00, "DINHEIRO", "PAGO");
insert into despesa(receita_cod_receita, dia, mes, ano, conta_id_conta, categoria_id, valor, f_pagamento, estatus) values
(1, 09, 07, 2009, 1, 9, 230.00, "DINHEIRO", "PAGO");
insert into despesa(receita_cod_receita, dia, mes, ano, conta_id_conta, categoria_id, valor, f_pagamento, estatus) values
(1, 10, 07, 2009, 1, 10, 300.00, "DINHEIRO", "PAGO");


insert into despesa(receita_cod_receita, dia, mes, ano, conta_id_conta, categoria_id, num_cartao_credito, valor, f_pagamento, estatus) values
(1, 11, 07, 2009, 1, 11, 5339614010424884,	888.00, "CRÉDITO", "NÃO PAGO");
insert into despesa(receita_cod_receita, dia, mes, ano, conta_id_conta, categoria_id, num_cartao_credito, valor, f_pagamento, estatus) values
(1, 12, 07, 2009, 1, 12, 5339614010424884,	777.00, "CRÉDITO", "PAGO");
insert into despesa(receita_cod_receita, dia, mes, ano, conta_id_conta, categoria_id, num_cartao_credito, valor, f_pagamento, estatus) values
(1, 13, 07, 2009, 1, 13, 5339614010424884,	555.00, "CRÉDITO", "NÃO PAGO");
insert into despesa(receita_cod_receita, dia, mes, ano, conta_id_conta, categoria_id, num_cartao_credito, valor, f_pagamento, estatus) values
(1, 14, 07, 2009, 1, 14, 5339614010424884,	222.00, "CRÉDITO", "NÃO PAGO");


#Despesa credito

insert into despesa_credito(n_parcelas, n_parcelas_pagas, valor_parcela, despesa_cod_despesa) values(12, 6, 20, 11);
insert into despesa_credito(n_parcelas, n_parcelas_pagas, valor_parcela, despesa_cod_despesa) VALUES(1, 1, 60, 12);
insert into despesa_credito(n_parcelas, n_parcelas_pagas, valor_parcela, despesa_cod_despesa) values(08, 2, 70, 13);
insert into despesa_credito(n_parcelas, n_parcelas_pagas, valor_parcela, despesa_cod_despesa) values(07, 0, 80, 14);


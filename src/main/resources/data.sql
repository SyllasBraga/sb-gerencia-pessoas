create table pessoa(
id int primary key not null auto_increment,
nome varchar(255) not null,
data_nascimento timestamp not null
);

create table endereco(
id int primary key not null auto_increment,
logradouro varchar(255) not null,
cep varchar(9) not null,
numero int not null,
cidade varchar(255) not null,
id_pessoa int not null,
principal boolean not null,
foreign key (id_pessoa) references pessoa(id)
);

insert into pessoa values (default, 'José', '2020-10-01');
insert into pessoa values (default, 'MARIA', '2002-01-01');

insert into endereco values (default, 'Rua do jardim', '123456-78', 12, 'Goiabeira', 1, true);
insert into endereco values (default, 'Rua Josefino', '123456-78', 90, 'Goiabeira', 1, false);
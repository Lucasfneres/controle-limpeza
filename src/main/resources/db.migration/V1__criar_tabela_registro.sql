create table registros(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    data varchar(30) not null,
    tarefa varchar(100) not null,
    primary key (id)
)
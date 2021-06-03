create table produto
(
    id          int auto_increment
        primary key,
    categoria   varchar(255) not null,
    precoCompra float        not null,
    precoVenda  float        not null,
    stock       int          not null,
    stockMin    int          not null,
    modelo      varchar(255) not null
);

create table utilizadores
(
    id       int auto_increment
        primary key,
    nome     varchar(255) not null,
    password varchar(255) not null,
    tipo     varchar(255) not null,
    email    varchar(255) not null,
    morada   varchar(255) not null,
    constraint utilizadores_name_uindex
        unique (nome)
);

create table transacao
(
    tipo         varchar(255) not null,
    quantidade   int          not null,
    produtoId    int          not null,
    id           int auto_increment
        primary key,
    utilizadorId int          not null,
    data         date         not null,
    constraint produtoId
        foreign key (produtoId) references produto (id),
    constraint utilizadorId
        foreign key (utilizadorId) references utilizadores (id)
);

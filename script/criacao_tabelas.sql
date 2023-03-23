CREATE SEQUENCE tb_produto_id_seq START 1 INCREMENT 50;
CREATE SEQUENCE tb_movimento_estoque_id_seq START 1 INCREMENT 50;

CREATE TABLE tb_produto (
    id bigint not null,
    descricao varchar(120),
    quantidade_minima int not null default 0,
    quantidade_minima int not null default 0,
    data_cadastro timestamp,
    valor numeric(19,2),
    CONSTRAINT tb_produto_pkey PRIMARY KEY (id)
);

CREATE TABLE tb_movimento_estoque (
    id bigint not null,
    produto bigint not null,
    data_movimento timestamp not null,
    quantidade numeric(19,2) not null,
    tipo_movimentacao varchar(120) not null,
    CONSTRAINT tb_movimento_estoque_pkey PRIMARY KEY (id),
    CONSTRAINT fk_produto FOREIGN KEY(produto) REFERENCES tb_produto(id)
);

INSERT INTO public.tb_produto (id, descricao, quantidade_minima, data_cadastro, valor, quantidade_atual) VALUES
(53, 'Caneta big', 12, '2023-03-21 00:00:00.000', 1.95, 500),
(102, 'Caderno 100 folhas', 20, '2023-03-21 00:00:00.000', 12.99, 500),
(202, 'Borracha', 200, '2023-03-01 00:00:00.000', 12.99, 500),
(252, 'Lapiseira', 120, '2023-03-22 00:00:00.000', 15.99, 500),
(302, 'Agenda', 50, '2023-03-22 00:00:00.000', 25.00, 500),
(352, 'Papel Sulfite', 200, '2023-03-22 00:00:00.000', 19.99, 500),
(52, 'Lápis', 10, '2023-03-21 00:00:00.000', 1.20, 500),
(152, 'Estojo', 1, '2023-03-21 00:00:00.000', 10.00, 5),
(452, 'Lápis Faber Castel', 10, '2023-05-21 00:00:00.000', 29.30, 500),
(502, 'Lápis Comum', 12, '2023-05-21 00:00:00.000', 1.25, 500),
(552, '12 Lápis de cor', 12, '2023-03-22 00:00:00.000', 39.50, 500),
(652, 'Gis de Cera', 12, '2023-05-22 00:00:00.000', 33.90, 500),
(602, '12 Canetinhas Faber Color', 12, '2023-04-22 00:00:00.000', 39.99, 500),
(402, 'Lápis de Desenho', 10, '2023-04-21 00:00:00.000', 1.99, 500),
(702, 'Régua', 15, '2023-03-22 00:00:00.000', 12.90, 60);

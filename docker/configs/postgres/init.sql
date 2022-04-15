CREATE DATABASE "registration-information";

\c "registration-information"

CREATE TABLE endereco (
    id UUID NOT NULL PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    logradouro VARCHAR(100) NOT NULL,
    numero VARCHAR(5) NOT NULL,
    complemento VARCHAR(100),
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    uf CHAR(2) NOT NULL,
    cep CHAR(8) NOT NULL,
    latitude DECIMAL(10,8) NOT NULL,
    longitude DECIMAL(11,8) NOT NULL
);

CREATE TABLE cliente (
    id UUID NOT NULL PRIMARY KEY,
    endereco_id UUID NOT NULL,
    nome VARCHAR(255) NOT NULL,
    cpf CHAR(11) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(50),
    CONSTRAINT fk_endereco FOREIGN KEY (endereco_id) references endereco(id)
);

CREATE TABLE fornecedor (
    id UUID NOT NULL PRIMARY KEY,
    endereco_id UUID NOT NULL,
    nome_fantasia VARCHAR(255) NOT NULL,
    razao_social VARCHAR(255) NOT NULL,
    cnpj CHAR(14) NOT NULL,
    CONSTRAINT fk_endereco FOREIGN KEY (endereco_id) references endereco(id)
);

CREATE TABLE deposito (
    id UUID NOT NULL PRIMARY KEY,
    endereco_id UUID NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    capacidade DECIMAL(10,2) NOT NULL,
    capacidade_tipo VARCHAR(100) NOT NULL,
    CONSTRAINT fk_endereco FOREIGN KEY (endereco_id) references endereco(id)
);

CREATE TABLE mercadoria (
    id UUID NOT NULL PRIMARY KEY,
    fornecedor_id UUID NOT NULL,
    deposito_id UUID NOT NULL,
    nome VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    codigo_barras VARCHAR(50) NOT NULL,
    data_validade DATE NOT NULL,
    quantidade DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_fornecedor FOREIGN KEY (fornecedor_id) references fornecedor(id),
    CONSTRAINT fk_deposito FOREIGN KEY (deposito_id) references deposito(id)
);

CREATE INDEX idx_cliente_nome ON cliente(nome);
CREATE INDEX idx_cliente_endereco_id ON cliente(endereco_id);
CREATE INDEX idx_cliente_cpf ON cliente(cpf);
CREATE INDEX idx_fornecedor_nome_fantasia ON fornecedor(nome_fantasia);
CREATE INDEX idx_fornecedor_razao_social ON fornecedor(razao_social);
CREATE INDEX idx_fornecedor_cnpj ON fornecedor(cnpj);
CREATE INDEX idx_fornecedor_endereco_id ON fornecedor(endereco_id);
CREATE INDEX idx_deposito_endereco_id ON deposito(endereco_id);
CREATE INDEX idx_mercadoria_nome ON mercadoria(nome);
CREATE INDEX idx_mercadoria_codigo_barras ON mercadoria(codigo_barras);
CREATE INDEX idx_mercadoria_data_validade ON mercadoria(data_validade);
CREATE INDEX idx_endereco_cidade ON endereco(cidade);
CREATE INDEX idx_endereco_uf ON endereco(uf);
CREATE INDEX idx_endereco_cep ON endereco(cep);

--------------------------------------------------------------------------------------------------------

CREATE DATABASE orders;

\c orders

CREATE TABLE pedido (
    id UUID NOT NULL PRIMARY KEY,
    codigo_rastreio VARCHAR(100) NOT NULL,
    "data" TIMESTAMP WITH TIME ZONE NOT NULL,
    cliente_id UUID NOT NULL
);

CREATE TABLE pedido_mercadoria (
    pedido_id UUID NOT NULL,
    mercadoria_id UUID NOT NULL,
    quantidade DECIMAL(10,2) NOT NULL,
    PRIMARY KEY(pedido_id, mercadoria_id),
    CONSTRAINT fk_pedido FOREIGN KEY (pedido_id) references pedido(id)
);

CREATE TABLE pedido_andamento (
    id UUID NOT NULL PRIMARY KEY,
    pedido_id UUID NOT NULL,
    "data" TIMESTAMP WITH TIME ZONE NOT NULL,
    status VARCHAR(100) NOT NULL,
    CONSTRAINT fk_pedido FOREIGN KEY (pedido_id) references pedido(id)
);

CREATE INDEX idx_pedido_codigo_rastreio ON pedido(codigo_rastreio);
CREATE INDEX idx_pedido_data ON pedido("data");
CREATE INDEX idx_pedido_cliente_id ON pedido(cliente_id);
CREATE INDEX idx_pedido_andamento_pedido_id ON pedido_andamento(pedido_id);
CREATE INDEX idx_pedido_andamento_data ON pedido_andamento("data");

--------------------------------------------------------------------------------------------------------

CREATE DATABASE authenticator;

\c authenticator

CREATE TABLE credenciais (
    usuario VARCHAR(100) PRIMARY KEY NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE INDEX idx_credenciais_usuario ON credenciais(usuario);
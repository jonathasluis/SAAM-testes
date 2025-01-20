CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS Usuario (
    id uuid NOT NULL  DEFAULT uuid_generate_v4(),
    nome varchar(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    CONSTRAINT usuario_pkey PRIMARY KEY (id),
    CONSTRAINT usuario_email_key UNIQUE (email)
    );

CREATE TABLE IF NOT EXISTS public.funcionario (
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    nome character varying(255) COLLATE pg_catalog."default" NOT NULL,
    dataadmissao date NOT NULL,
    valorsalario numeric(10,2) NOT NULL,
    status boolean NOT NULL,
    usuarioID uuid NOT NULL,
    CONSTRAINT funcionario_pkey PRIMARY KEY (id),
    CONSTRAINT "FK_usuario" FOREIGN KEY (usuarioID)
        REFERENCES public.usuario (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
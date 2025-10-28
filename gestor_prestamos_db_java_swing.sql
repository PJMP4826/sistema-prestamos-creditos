BEGIN;


CREATE TABLE IF NOT EXISTS public.clientes
(
    id bigserial NOT NULL,
    nombre character varying(255) COLLATE pg_catalog."default" NOT NULL,
    rfc character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT clientes_pkey PRIMARY KEY (id),
    CONSTRAINT clientes_rfc_key UNIQUE (rfc)
);

CREATE TABLE IF NOT EXISTS public.cobros
(
    id bigserial NOT NULL,
    prestamo_id bigint NOT NULL,
    fecha_pago timestamp without time zone NOT NULL,
    importe numeric NOT NULL,
    estado text COLLATE pg_catalog."default",
    CONSTRAINT cobros_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.correos_clientes
(
    id bigserial NOT NULL,
    cliente_id bigint NOT NULL,
    correo character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT correos_clientes_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.direcciones_clientes
(
    id bigserial NOT NULL,
    cliente_id bigint NOT NULL,
    direccion character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT direcciones_clientes_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.periodicidad_pago
(
    id bigserial NOT NULL,
    nombre_periodicidad character varying(255) COLLATE pg_catalog."default" NOT NULL,
    dias_periodicidad integer NOT NULL,
    porcentaje_intereses numeric NOT NULL,
    CONSTRAINT periodicidad_pago_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.prestamos
(
    id bigserial NOT NULL,
    cliente_id bigint NOT NULL,
    periodicidad_id bigint NOT NULL,
    importe numeric NOT NULL,
    plazo integer NOT NULL,
    fecha_inicio timestamp without time zone NOT NULL,
    saldo_actual numeric NOT NULL,
    aprobado text COLLATE pg_catalog."default",
    usuario_id integer,
    CONSTRAINT prestamos_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.telefonos_clientes
(
    id bigserial NOT NULL,
    cliente_id bigint NOT NULL,
    telefono character varying(255) COLLATE pg_catalog."default" NOT NULL,
    tipo text COLLATE pg_catalog."default",
    CONSTRAINT telefonos_clientes_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.tipo_usuarios
(
    id serial NOT NULL,
    tipo character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tipo_usuarios_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.usuarios
(
    id serial NOT NULL,
    nombre character varying(100) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    tipo_usuario integer NOT NULL,
    CONSTRAINT usuarios_pkey PRIMARY KEY (id),
    CONSTRAINT usuarios_email_key UNIQUE (email)
);

ALTER TABLE IF EXISTS public.cobros
    ADD CONSTRAINT fk_cobros_prestamo_id FOREIGN KEY (prestamo_id)
    REFERENCES public.prestamos (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.correos_clientes
    ADD CONSTRAINT fk_correos_clientes_cliente_id FOREIGN KEY (cliente_id)
    REFERENCES public.clientes (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.direcciones_clientes
    ADD CONSTRAINT fk_direcciones_clientes_cliente_id FOREIGN KEY (cliente_id)
    REFERENCES public.clientes (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.prestamos
    ADD CONSTRAINT fk_prestamos_cliente_id FOREIGN KEY (cliente_id)
    REFERENCES public.clientes (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.prestamos
    ADD CONSTRAINT fk_prestamos_periodicidad_id FOREIGN KEY (periodicidad_id)
    REFERENCES public.periodicidad_pago (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.prestamos
    ADD CONSTRAINT fk_prestamos_usuario_id FOREIGN KEY (usuario_id)
    REFERENCES public.usuarios (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.telefonos_clientes
    ADD CONSTRAINT fk_clientes FOREIGN KEY (cliente_id)
    REFERENCES public.clientes (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.usuarios
    ADD CONSTRAINT fk_usuarios_tipo_id FOREIGN KEY (tipo_usuario)
    REFERENCES public.tipo_usuarios (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.usuarios
    ADD CONSTRAINT fk_usuarios_tipo_usuarios FOREIGN KEY (tipo_usuario)
    REFERENCES public.tipo_usuarios (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

END;
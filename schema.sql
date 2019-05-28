--
-- PostgreSQL database dump
--

-- Dumped from database version 10.8 (Ubuntu 10.8-0ubuntu0.18.10.1)
-- Dumped by pg_dump version 10.8 (Ubuntu 10.8-0ubuntu0.18.10.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: mbugua
--

CREATE TABLE public.clients (
    id integer NOT NULL,
    name character varying,
    number integer,
    email character varying,
    stylistid integer,
    gender character varying
);


ALTER TABLE public.clients OWNER TO mbugua;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: mbugua
--

CREATE SEQUENCE public.clients_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clients_id_seq OWNER TO mbugua;

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mbugua
--

ALTER SEQUENCE public.clients_id_seq OWNED BY public.clients.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: mbugua
--

CREATE TABLE public.stylists (
    id integer NOT NULL,
    name character varying,
    number integer,
    age integer,
    gender character varying
);


ALTER TABLE public.stylists OWNER TO mbugua;

--
-- Name: stylists_id_seq; Type: SEQUENCE; Schema: public; Owner: mbugua
--

CREATE SEQUENCE public.stylists_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.stylists_id_seq OWNER TO mbugua;

--
-- Name: stylists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mbugua
--

ALTER SEQUENCE public.stylists_id_seq OWNED BY public.stylists.id;


--
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.clients ALTER COLUMN id SET DEFAULT nextval('public.clients_id_seq'::regclass);


--
-- Name: stylists id; Type: DEFAULT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.stylists ALTER COLUMN id SET DEFAULT nextval('public.stylists_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: mbugua
--

COPY public.clients (id, name, number, email, stylistid, gender) FROM stdin;
\.


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: mbugua
--

COPY public.stylists (id, name, number, age, gender) FROM stdin;
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mbugua
--

SELECT pg_catalog.setval('public.clients_id_seq', 7, true);


--
-- Name: stylists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mbugua
--

SELECT pg_catalog.setval('public.stylists_id_seq', 7, true);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: stylists stylists_pkey; Type: CONSTRAINT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.stylists
    ADD CONSTRAINT stylists_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--


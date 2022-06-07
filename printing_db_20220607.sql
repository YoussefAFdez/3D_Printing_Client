--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3 (Debian 14.3-1.pgdg110+1)
-- Dumped by pg_dump version 14.3 (Debian 14.3-1.pgdg110+1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: images; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.images (
    imageid integer NOT NULL,
    imagedescription character varying(100),
    image bytea,
    printid integer
);


ALTER TABLE public.images OWNER TO postgres;

--
-- Name: images_imageid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.images_imageid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.images_imageid_seq OWNER TO postgres;

--
-- Name: images_imageid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.images_imageid_seq OWNED BY public.images.imageid;


--
-- Name: print; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.print (
    printid integer NOT NULL,
    printname character varying(100) NOT NULL,
    printauthor character varying(50) NOT NULL,
    printcategory character varying(50) NOT NULL,
    printsupports boolean DEFAULT false,
    printdescription text,
    printinfill character varying(10),
    printresolution character varying(10),
    printmaterial character varying(10) NOT NULL,
    printurl character varying(100),
    printfile bytea,
    printfilename character varying(50),
    usercreator integer
);


ALTER TABLE public.print OWNER TO postgres;

--
-- Name: print_printid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.print_printid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.print_printid_seq OWNER TO postgres;

--
-- Name: print_printid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.print_printid_seq OWNED BY public.print.printid;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    userid integer NOT NULL,
    username character varying(20),
    password character varying(20)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_userid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_userid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_userid_seq OWNER TO postgres;

--
-- Name: users_userid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_userid_seq OWNED BY public.users.userid;


--
-- Name: images imageid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.images ALTER COLUMN imageid SET DEFAULT nextval('public.images_imageid_seq'::regclass);


--
-- Name: print printid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.print ALTER COLUMN printid SET DEFAULT nextval('public.print_printid_seq'::regclass);


--
-- Name: users userid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN userid SET DEFAULT nextval('public.users_userid_seq'::regclass);


--
-- Data for Name: images; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.images (imageid, imagedescription, image, printid) FROM stdin;
\.


--
-- Data for Name: print; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.print (printid, printname, printauthor, printcategory, printsupports, printdescription, printinfill, printresolution, printmaterial, printurl, printfile, printfilename, usercreator) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (userid, username, password) FROM stdin;
1	user	test
\.


--
-- Name: images_imageid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.images_imageid_seq', 30, true);


--
-- Name: print_printid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.print_printid_seq', 9, true);


--
-- Name: users_userid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_userid_seq', 2, true);


--
-- Name: images images_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.images
    ADD CONSTRAINT images_pkey PRIMARY KEY (imageid);


--
-- Name: print print_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.print
    ADD CONSTRAINT print_pkey PRIMARY KEY (printid);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (userid);


--
-- Name: images images_printid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.images
    ADD CONSTRAINT images_printid_fkey FOREIGN KEY (printid) REFERENCES public.print(printid);


--
-- Name: print print_usercreator_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.print
    ADD CONSTRAINT print_usercreator_fkey FOREIGN KEY (usercreator) REFERENCES public.users(userid);


--
-- PostgreSQL database dump complete
--


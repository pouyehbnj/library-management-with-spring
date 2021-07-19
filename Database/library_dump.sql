--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3 (Debian 13.3-1.pgdg100+1)
-- Dumped by pg_dump version 13.3

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
-- Name: book_keywords; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book_keywords (
    id integer NOT NULL,
    book_id integer NOT NULL,
    keyword_id integer NOT NULL
);


ALTER TABLE public.book_keywords OWNER TO postgres;

--
-- Name: books; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.books (
    id integer NOT NULL,
    issn character varying(255),
    author character varying(255),
    created_at timestamp without time zone NOT NULL,
    image character varying(255),
    publish_year character varying(255),
    publisher character varying(255),
    title character varying(255)
);


ALTER TABLE public.books OWNER TO postgres;

--
-- Name: comments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comments (
    id integer NOT NULL,
    content character varying(255) NOT NULL,
    book_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.comments OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: hibernate_sequences; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hibernate_sequences (
    sequence_name character varying(255) NOT NULL,
    next_val bigint
);


ALTER TABLE public.hibernate_sequences OWNER TO postgres;

--
-- Name: keywords; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.keywords (
    id integer NOT NULL,
    word character varying(255) NOT NULL
);


ALTER TABLE public.keywords OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    password character varying(255),
    role character varying(255),
    username character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: book_keywords; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.book_keywords (id, book_id, keyword_id) FROM stdin;
16	26	15
18	23	17
20	23	19
25	38	19
27	23	26
29	23	28
31	38	30
\.


--
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.books (id, issn, author, created_at, image, publish_year, publisher, title) FROM stdin;
17	9783426282564	Matt Haig	2021-07-16 13:22:38.298	https://s4.uupload.ir/files/2_zc1m.jpg	2020	Viking	The Midnight Library: A Novel
24	9781250178602	Kristin Hannah	2021-07-16 17:05:17.019	https://s4.uupload.ir/files/3_rs2c.jpg	2021	Macmillan	The Four Winds
14	9788484359739	Anna Gavalda	2021-07-16 17:13:13.279	https://s4.uupload.ir/files/1_86h.jpg	2002	Easy Readers	35 kilos d'espoir
23	9780155658110	George Orwell	2021-07-16 19:14:54.976	https://s4.uupload.ir/files/4_yycy.jpg	1949	Secker & Warburg	Nineteen Eighty-Four
26	9780385539241	 Margaret Atwood	2021-07-17 19:25:56.551	https://s4.uupload.ir/files/5_b2ac.jpg	1985	Harcourt	The Handmaid's Tale
34	9780399167065	Liane Moriarty	2021-07-17 22:13:16.084	https://s4.uupload.ir/files/6_buyh.jpg	2017	Penguin Group	Big Little Lies
35	9780413544506	Bertolt Brecht	2021-07-17 22:17:01.826	https://s4.uupload.ir/files/7_mamm.jpg	1948	Heinemann	The Caucasian Chalk Circle
36	9780060734015	Katherine Paterson	2021-07-17 22:22:31.522	https://s4.uupload.ir/files/8_65cm.jpg	1977	HarperCollins	Bridge to Terabithia
37	9788483073841	Will Cuppy	2021-07-17 22:30:44.281	https://s4.uupload.ir/files/9_o3lr.jpg	1950	Barnes Noble	The Decline and Fall of Practically Everybody
38	9780694524440	Paulo Coelho	2021-07-17 23:54:09.962	https://s4.uupload.ir/files/10_fsjw.jpg	1988	HarperCollins	The Alchemist
\.


--
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comments (id, content, book_id, user_id) FROM stdin;
14	user2 comment	26	13
21	This is one of the best books evveerrrr...	17	1
23	good book	36	1
32	This book changed my life !!!!	38	22
33	I heard the audiobook and it's amazing!!!	38	1
11	test comment update 2	26	1
\.


--
-- Data for Name: hibernate_sequences; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.hibernate_sequences (sequence_name, next_val) FROM stdin;
default	39
\.


--
-- Data for Name: keywords; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.keywords (id, word) FROM stdin;
15	test keyword
17	second keyword
19	fundamentalism
26	Science fiction
28	Dystopian
30	fantasy
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, password, role, username) FROM stdin;
1	test	user	test
2	test	publisher	publisher
3	test	admin	admin
10	test update	publisher	HarperCollins
22	test	user	shaghayegh-user
13	test	user	user2-to-be-delete
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 34, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 1, false);


--
-- Name: book_keywords book_keywords_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_keywords
    ADD CONSTRAINT book_keywords_pkey PRIMARY KEY (id);


--
-- Name: books books_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (id);


--
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- Name: hibernate_sequences hibernate_sequences_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hibernate_sequences
    ADD CONSTRAINT hibernate_sequences_pkey PRIMARY KEY (sequence_name);


--
-- Name: keywords keywords_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.keywords
    ADD CONSTRAINT keywords_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: keywords word_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.keywords
    ADD CONSTRAINT word_unique UNIQUE (word);


--
-- Name: comments fk1ey8gegnanvybix5a025vepf4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT fk1ey8gegnanvybix5a025vepf4 FOREIGN KEY (book_id) REFERENCES public.books(id);


--
-- Name: comments fk8omq0tc18jd43bu5tjh6jvraq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT fk8omq0tc18jd43bu5tjh6jvraq FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: book_keywords fkiap7wf7w28xefm0r19o0ywygy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_keywords
    ADD CONSTRAINT fkiap7wf7w28xefm0r19o0ywygy FOREIGN KEY (keyword_id) REFERENCES public.keywords(id);


--
-- Name: book_keywords fknomq6ywy8xgyghy8vt3wmjm50; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_keywords
    ADD CONSTRAINT fknomq6ywy8xgyghy8vt3wmjm50 FOREIGN KEY (book_id) REFERENCES public.books(id);


--
-- PostgreSQL database dump complete
--


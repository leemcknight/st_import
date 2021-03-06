PGDMP     7                	    w           stattracker    9.6.5    10.5     f           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            g           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            h           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            i           1262    16393    stattracker    DATABASE     �   CREATE DATABASE stattracker WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE stattracker;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            j           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12387    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            k           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    16416    ballpark    TABLE       CREATE TABLE public.ballpark (
    ballpark_id character(6) NOT NULL,
    name character varying(50),
    secondary_name character varying,
    city character varying,
    state character(2),
    start_date date,
    end_date date,
    league_code character(2),
    notes text
);
    DROP TABLE public.ballpark;
       public         postgres    false    3            �            1259    16426 	   franchise    TABLE     H  CREATE TABLE public.franchise (
    current_franchise_id character(3) NOT NULL,
    league_code character(2),
    division character(1),
    location character varying(20),
    nickname character varying(35),
    alternate_nickname character varying(35),
    start_date date,
    end_date date,
    franchise_id character(3)
);
    DROP TABLE public.franchise;
       public         postgres    false    3            �            1259    16456    gamelog    TABLE     !   CREATE TABLE public.gamelog (
);
    DROP TABLE public.gamelog;
       public         postgres    false    3            �            1259    16394    person    TABLE     �   CREATE TABLE public.person (
    person_id character(8) NOT NULL,
    first_name character varying(50),
    last_name character varying(50),
    debut date
);
    DROP TABLE public.person;
       public         postgres    false    3            �            1259    16401    role    TABLE     j   CREATE TABLE public.role (
    role_id integer DEFAULT 1 NOT NULL,
    role_name character varying(20)
);
    DROP TABLE public.role;
       public         postgres    false    3            �            1259    16398    schedule    TABLE     �  CREATE TABLE public.schedule (
    date date NOT NULL,
    day_of_week character(3) NOT NULL,
    game_number smallint,
    visiting_team_id character(3),
    visiting_team_league_code character(2),
    visitor_game_number smallint,
    home_team_id character(3),
    home_team_league_code character(2),
    home_game_number smallint,
    time_of_day_code character(1),
    postponement_cancellation_code text,
    makeup_date date
);
    DROP TABLE public.schedule;
       public         postgres    false    3            �           2606    16423    ballpark pk_ballpark_id 
   CONSTRAINT     ^   ALTER TABLE ONLY public.ballpark
    ADD CONSTRAINT pk_ballpark_id PRIMARY KEY (ballpark_id);
 A   ALTER TABLE ONLY public.ballpark DROP CONSTRAINT pk_ballpark_id;
       public         postgres    false    188            �           2606    16425    person pk_person 
   CONSTRAINT     U   ALTER TABLE ONLY public.person
    ADD CONSTRAINT pk_person PRIMARY KEY (person_id);
 :   ALTER TABLE ONLY public.person DROP CONSTRAINT pk_person;
       public         postgres    false    185            �           2606    16409    role pk_role 
   CONSTRAINT     O   ALTER TABLE ONLY public.role
    ADD CONSTRAINT pk_role PRIMARY KEY (role_id);
 6   ALTER TABLE ONLY public.role DROP CONSTRAINT pk_role;
       public         postgres    false    187            �           2606    16411 
   role uk_id 
   CONSTRAINT     H   ALTER TABLE ONLY public.role
    ADD CONSTRAINT uk_id UNIQUE (role_id);
 4   ALTER TABLE ONLY public.role DROP CONSTRAINT uk_id;
       public         postgres    false    187           
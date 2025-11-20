DROP DATABASE IF EXISTS boossteam_db;
CREATE DATABASE boossteam_db CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE boossteam_db;
CREATE TABLE "student" (
                         st_id serial NOT NULL,
                         st_name character varying(250) NOT NULL,
                         st_last_name character varying(250) NOT NULL,
                         st_age integer NOT NULL,
                         st_state boolean NOT NULL,
                         PRIMARY KEY (st_id)
                        );

ALTER TABLE "student" OWNER to postgres;
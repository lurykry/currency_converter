DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS conversion_history;
DROP TABLE IF EXISTS currency;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS persistent_logins;


DROP TABLE IF EXISTS  logging_event_property;
DROP TABLE IF EXISTS   logging_event_exception;
DROP TABLE IF EXISTS   logging_event;
DROP SEQUENCE IF EXISTS logging_event_id_seq;


CREATE TABLE users
(
	user_id SERIAL PRIMARY KEY,
    user_login VARCHAR(32) NOT NULL UNIQUE,
    user_email VARCHAR(100) NOT NULL UNIQUE,
    user_password TEXT NOT NULL
);

CREATE TABLE roles
(
	role_name VARCHAR(20) NOT NULL,
    user_user_id int NOT NULL,
    FOREIGN KEY(user_user_id) REFERENCES users(user_id)
);
CREATE TABLE currency
(
	currency_id VARCHAR(10) PRIMARY KEY NOT NULL,
    currency_num_code  CHAR(3) NOT NULL,
    currency_char_code CHAR(3) NOT NULL,
    currency_name VARCHAR(100) NOT NULL,
    current_currency_value NUMERIC(7,4) NOT NULL,
    date_of_update date NOT NULL
);
CREATE TABLE conversion_history
(
	record_id SERIAL PRIMARY KEY,
	currency_from_id VARCHAR(10) NOT NULL,
    currency_to_id VARCHAR(10) NOT NULL,
    currency_from_char_code CHAR(3) NOT NULL,
    currency_to_char_code CHAR(3) NOT NULL,
    amount_of_money_from NUMERIC(12,4) NOT NULL,
    amount_of_money_to NUMERIC(12,4) NOT NULL,
    date_of_conversion date,
    currency_rate NUMERIC(7,4) NOT NULL,
    user_user_id int NOT NULL,
    FOREIGN KEY(user_user_id) REFERENCES users(user_id)
);

CREATE TABLE persistent_logins (
  username VARCHAR(100) NOT NULL,
  series VARCHAR(64) PRIMARY KEY,
  token VARCHAR(64) NOT NULL,
  last_used TIMESTAMP NOT NULL
);

CREATE SEQUENCE logging_event_id_seq MINVALUE 1 START 1;


CREATE TABLE logging_event
  (
    timestmp         BIGINT NOT NULL,
    formatted_message  TEXT NOT NULL,
    logger_name       VARCHAR(254) NOT NULL,
    level_string      VARCHAR(254) NOT NULL,
    thread_name       VARCHAR(254),
    reference_flag    SMALLINT,
    arg0              VARCHAR(254),
    arg1              VARCHAR(254),
    arg2              VARCHAR(254),
    arg3              VARCHAR(254),
    caller_filename   VARCHAR(254) NOT NULL,
    caller_class      VARCHAR(254) NOT NULL,
    caller_method     VARCHAR(254) NOT NULL,
    caller_line       CHAR(4) NOT NULL,
    event_id          BIGINT DEFAULT nextval('logging_event_id_seq') PRIMARY KEY
  );

CREATE TABLE logging_event_property
  (
    event_id	      BIGINT NOT NULL,
    mapped_key        VARCHAR(254) NOT NULL,
    mapped_value      VARCHAR(1024),
    PRIMARY KEY(event_id, mapped_key),
    FOREIGN KEY (event_id) REFERENCES logging_event(event_id)
  );

CREATE TABLE logging_event_exception
  (
    event_id         BIGINT NOT NULL,
    i                SMALLINT NOT NULL,
    trace_line       VARCHAR(254) NOT NULL,
    PRIMARY KEY(event_id, i),
    FOREIGN KEY (event_id) REFERENCES logging_event(event_id)
  );
-- TABLE vendor - BEGIN
CREATE SEQUENCE seq_vendor_id
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;

-- {"id":"1", "name":"cbrf","full_name":"cbrf"}
CREATE TABLE IF NOT EXISTS vendor (
  id         BIGINT  NOT NULL DEFAULT nextval('seq_vendor_id' :: REGCLASS),
  name       VARCHAR(21),
  full_name  VARCHAR(255),
  url        VARCHAR(255),
  additional TEXT,
  is_active  BOOLEAN NOT NULL DEFAULT TRUE,
  CONSTRAINT vendor_pkey PRIMARY KEY (id)
);
-- Not to create duplicate records
CREATE UNIQUE INDEX IF NOT EXISTS vendor_unique_name
  ON vendor (name);
-- TABLE vendor - END

INSERT INTO vendor (name, full_name, url, additional) VALUES
  ('cbr', 'Центральный Банк России', 'http://localhost:8101/currency_rates', '{"param":"params-1","test":"test_2"}');

-- TABLE currency - BEGIN
CREATE SEQUENCE seq_currency_id
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;

-- {"id":"1", "char_code":"643","num_code":"RUB", "name":"russian ruble"}
CREATE TABLE IF NOT EXISTS currency (
  id        BIGINT NOT NULL DEFAULT nextval('seq_currency_id' :: REGCLASS),
  char_code VARCHAR(3),
  num_code  VARCHAR(3),
  name      VARCHAR(255),
  CONSTRAINT currency_pkey PRIMARY KEY (id)
);
-- Not to create duplicate records
CREATE UNIQUE INDEX IF NOT EXISTS currency_unique
  ON currency (char_code, num_code);
-- TABLE currency - END

INSERT INTO currency (char_code, num_code, name) VALUES
  ('RUB', 643, 'Российский рубль'),
  ('USD', 840, 'Доллар США'),
  ('BYR', 974, 'Белорусский рубль'),
  ('EUR', 978, 'Евро');

-- TABLE vendor_currency - BEGIN
CREATE SEQUENCE seq_vendor_currency_id
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;

-- {"id":"1", "vendor_id":"1","source_currency_id":"RUB", "target_currency_id":"USD"}
CREATE TABLE IF NOT EXISTS vendor_currency (
  id                 BIGINT  NOT NULL DEFAULT nextval('seq_vendor_currency_id' :: REGCLASS),
  vendor_id          BIGINT  NOT NULL REFERENCES vendor (id),
  source_currency_id BIGINT  NOT NULL REFERENCES currency (id),
  target_currency_id BIGINT  NOT NULL REFERENCES currency (id),
  is_active          BOOLEAN NOT NULL DEFAULT TRUE,
  CONSTRAINT vendor_currency_pkey PRIMARY KEY (id)
);
-- Not to create duplicate records
CREATE UNIQUE INDEX IF NOT EXISTS vendor_currency_unique
  ON vendor_currency (vendor_id, source_currency_id, target_currency_id);
-- TABLE vendor_currency - END

INSERT INTO vendor_currency (vendor_id, source_currency_id, target_currency_id) VALUES
  (1, 1, 1),
  (1, 1, 2);

-- TABLE rates - BEGIN
CREATE SEQUENCE seq_rates_id
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;

-- {"id":"1", "vendor_currency_id":"1","nominal":"1", "rate":"35.56"}
CREATE TABLE IF NOT EXISTS rates (
  id                 BIGINT  NOT NULL DEFAULT nextval('seq_rates_id' :: REGCLASS),
  vendor_currency_id BIGINT  NOT NULL REFERENCES vendor_currency (id),
  nominal            BIGINT  NOT NULL,
  rate               DECIMAL NOT NULL,
  rate_date          DATE             DEFAULT now(),
  CONSTRAINT rates_pkey PRIMARY KEY (id)
);
-- Not to create duplicate records
CREATE UNIQUE INDEX IF NOT EXISTS rates_unique
  ON rates (vendor_currency_id, nominal, rate, rate_date);
-- TABLE rates - END
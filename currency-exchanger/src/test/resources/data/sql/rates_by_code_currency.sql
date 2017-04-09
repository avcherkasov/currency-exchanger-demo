INSERT INTO currency (id, char_code, num_code, name) VALUES
  (5, 'BYN', 933, 'Бело');

INSERT INTO vendor_currency (id, vendor_id, source_currency_id, target_currency_id) VALUES
  (3, 1, 1, 4),
  (4, 1, 1, 3),
  (5, 1, 1, 5)
;

INSERT INTO rates (vendor_currency_id, nominal, rate, rate_date) VALUES
(2, 1, 58.3776, '1016-01-17'),
(5, 1, 30.8143, '1016-01-17'),
(5, 1, 30.8143, '1016-01-21'),
(2, 1, 58.3776, '1016-01-21'),
(3, 1, 61.5417, '1016-01-21'),
(2,1,58.3776,'2017-03-17'),
(3,1,61.5417,'2017-03-17'),
(5,1,30.8143,'2017-03-17'),
(5,1,30.8143,'2017-03-21'),
(2,1,58.3776,'2017-03-21'),
(3,1,61.5417,'2017-03-21')
;

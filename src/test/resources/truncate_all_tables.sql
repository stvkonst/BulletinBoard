SET @@foreign_key_checks = 0;

TRUNCATE TABLE ad;
TRUNCATE TABLE address;
TRUNCATE TABLE author;
TRUNCATE TABLE email;
TRUNCATE TABLE phone;
TRUNCATE TABLE rubric;
TRUNCATE TABLE subscription;

SET @@foreign_key_checks = 1;
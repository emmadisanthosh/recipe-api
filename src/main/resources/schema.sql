DROP TABLE IF EXISTS recipe_users;  
CREATE TABLE recipe_users ( 
id BIGINT PRIMARY KEY,
userName VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL,
is_active BOOLEAN,
roles VARCHAR(200) NOT NULL
);
commit;
CREATE TABLE address
(
    id SERIAL PRIMARY KEY,
    country varchar(20) NOT NULL,
    city varchar(20) NOT NULL,
    street varchar(20) NOT NULL,
    house_number varchar(10) NOT NULL,
    apartment_number varchar(10)
);

CREATE TABLE photo
(
    id SERIAL PRIMARY KEY,
    file_path TEXT NOT NULL
);

CREATE TABLE phone_number
(
    id SERIAL PRIMARY KEY,
    phone_number varchar(15) NOT NULL UNIQUE
);

CREATE TABLE passport
(
    id SERIAL PRIMARY KEY,
    first_name varchar(20) NOT NULL,
    last_name varchar(20) NOT NULL,
    passport_number varchar(20) NOT NULL UNIQUE,
    sex varchar(10) NOT NULL
);

CREATE TABLE cat
(
    id SERIAL PRIMARY KEY,
    name varchar(20),
    pet_date_birth DATE,
    sex varchar(10),
    breed varchar(20),
    color varchar(20),
    description TEXT,
    photo_id int,
    FOREIGN KEY (photo_id) REFERENCES photo(id)
);

CREATE TABLE dog
(
    id SERIAL PRIMARY KEY,
    name varchar(20),
    pet_date_birth DATE,
    sex varchar(10),
    breed varchar(20),
    color varchar(20),
    description TEXT,
    photo_id int,
    FOREIGN KEY (photo_id) REFERENCES photo(id)
);

CREATE TABLE sitter
(
    id SERIAL PRIMARY KEY,
    first_name varchar(20) NOT NULL,
    last_name varchar(20) NOT NULL,
    date_birth DATE NOT NULL,
    phone_number_id int,
    address_id int,
    pet_id int,
    passport_id int,
    description TEXT,
    FOREIGN KEY (phone_number_id) REFERENCES phone_number(id),
    FOREIGN KEY (address_id) REFERENCES address(id),
    FOREIGN KEY (pet_id) REFERENCES cat(id),
    FOREIGN KEY (pet_id) REFERENCES dog(id),
    FOREIGN KEY (passport_id) REFERENCES passport(id)
);

CREATE TABLE pet_shelter
(
    id SERIAL PRIMARY KEY,
    organization_name varchar(100) NOT NULL,
    address_id int,
    phone_number_id int,
    FOREIGN KEY (address_id) REFERENCES address(id),
    FOREIGN KEY (phone_number_id) REFERENCES phone_number(id)
);

CREATE TABLE sitter_pet_shelter
(
    sitter_id int NOT NULL,
    pet_shelter_id int NOT NULL,
    PRIMARY KEY (sitter_id, pet_shelter_id),
    FOREIGN KEY (sitter_id) REFERENCES sitter(id),
    FOREIGN KEY (pet_shelter_id) REFERENCES pet_shelter(id)
);

CREATE TABLE person
(
    id SERIAL PRIMARY KEY,
    first_name varchar(20) NOT NULL,
    last_name varchar(20) NOT NULL,
    date_birth DATE NOT NULL,
    phone_number_id int,
    address_id int,
    pet_id int,
    FOREIGN KEY (phone_number_id) REFERENCES phone_number(id),
    FOREIGN KEY (address_id) REFERENCES address(id),
    FOREIGN KEY (pet_id) REFERENCES cat(id),
    FOREIGN KEY (pet_id) REFERENCES dog(id)
);
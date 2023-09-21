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
    file_path TEXT
);

CREATE TABLE pet
(
    id SERIAL PRIMARY KEY,
    name varchar(20),
    pet_date_birth DATE,
    sex varchar(10),
    breed varchar(20),
    color varchar(20),
    description TEXT,
    pet_shelter_id int,
    person_id int,
    sitter_id int
);

CREATE TABLE photo_pet
(
    pet_id int,
    photo_id int,
    FOREIGN KEY (pet_id) REFERENCES pet(id),
    FOREIGN KEY (photo_id) REFERENCES photo(id)
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
    surname varchar(20) NOT NULL,
    date_birth DATE NOT NULL,
    registration_address_id int NOT NULL,
    passport_number varchar(20) NOT NULL UNIQUE,
    sex varchar(10) NOT NULL,
    FOREIGN KEY (registration_address_id) REFERENCES address(id)
);

CREATE TABLE passport_photo
(
    passport_id int,
    photo_id int,
    FOREIGN KEY (passport_id) REFERENCES passport(id),
    FOREIGN KEY (photo_id) REFERENCES photo(id)
);

CREATE TABLE pet_shelter
(
    id SERIAL PRIMARY KEY,
    organization_name varchar(100) NOT NULL,
    description TEXT
);

CREATE TABLE petShelter_phoneNumber
(
    pet_shelter_id int,
    phone_number_id int,
    FOREIGN KEY (pet_shelter_id) REFERENCES pet_shelter(id),
    FOREIGN KEY (phone_number_id) REFERENCES phone_number(id)
);

CREATE TABLE petShelter_address
(
    pet_shelter_id int,
    address_id int,
    FOREIGN KEY (pet_shelter_id) REFERENCES pet_shelter(id),
    FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE petShelter_photo
(
    pet_shelter_id int,
    photo_id int,
    FOREIGN KEY (pet_shelter_id) REFERENCES pet_shelter(id),
    FOREIGN KEY (photo_id) REFERENCES photo(id)
);

CREATE TABLE sitter
(
    id SERIAL PRIMARY KEY,
    first_name varchar(20) NOT NULL,
    surname varchar(20) NOT NULL,
    description TEXT,
    address_id int,
    pet_shelter_id int,
    passport_id int,
    FOREIGN KEY (pet_shelter_id) REFERENCES pet_shelter(id),
    FOREIGN KEY (address_id) REFERENCES address(id),
    FOREIGN KEY (passport_id) REFERENCES passport(id)
);

CREATE TABLE sitter_photo
(
    sitter_id int,
    photo_id int,
    FOREIGN KEY (sitter_id) REFERENCES sitter(id),
    FOREIGN KEY (photo_id) REFERENCES photo(id)
);

CREATE TABLE sitter_phoneNumber
(
    sitter_id int,
    phone_number_id int,
    FOREIGN KEY (sitter_id) REFERENCES sitter(id),
    FOREIGN KEY (phone_number_id) REFERENCES phone_number(id)
);

CREATE TABLE person
(
    id SERIAL PRIMARY KEY,
    first_name varchar(20) NOT NULL,
    surname varchar(20) NOT NULL,
    date_birth DATE NOT NULL
);

CREATE TABLE person_numberPhone
(
    person_id int,
    number_phone_id int,
    FOREIGN KEY (person_id) REFERENCES person(id),
    FOREIGN KEY (number_phone_id) REFERENCES phone_number(id)
)

CREATE TABLE person_photo
(
    person_id int,
    photo_id int,
    FOREIGN KEY (person_id) REFERENCES person(id),
    FOREIGN KEY (photo_id) REFERENCES photo(id)
);

-- может быть ошибка
ALTER TABLE pet ADD CONSTRAINT FOREIGN KEY (pet_shelter_id) REFERENCES pet_shelter(id);
ALTER TABLE pet ADD CONSTRAINT FOREIGN KEY (person_id) REFERENCES person(id);
ALTER TABLE pet ADD CONSTRAINT FOREIGN KEY (sitter_id) REFERENCES sitter(id);

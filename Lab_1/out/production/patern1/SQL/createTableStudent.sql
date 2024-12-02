CREATE TABLE student ( 
    id SERIAL PRIMARY KEY, 
    lastName VARCHAR(100) NOT NULL, 
    name VARCHAR(100) NOT NULL, 
    fatherName VARCHAR(100), 
    phone VARCHAR(20), 
    telegram VARCHAR(50), 
    mail VARCHAR(100), 
    git VARCHAR(100) 
);
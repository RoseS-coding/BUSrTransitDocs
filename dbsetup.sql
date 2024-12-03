CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(100),
    email VARCHAR(100),
    userType VARCHAR(50)
);

CREATE TABLE Driver (
    license_number VARCHAR(20) PRIMARY KEY,
    user_id INT,
    name VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Bus (
    bus_id INT AUTO_INCREMENT PRIMARY KEY,
    maintenance VARCHAR(255),
    license_plate VARCHAR(20) NOT NULL,
    capacity INT
);

CREATE TABLE Paths (
    path_id INT AUTO_INCREMENT PRIMARY KEY,
    depart_location VARCHAR(100),
    arrive_location VARCHAR(100),
    depart_time DATETIME,
    arrive_time DATETIME
);

CREATE TABLE Route (
    route_id INT AUTO_INCREMENT PRIMARY KEY,
    path_id INT,
    bus_id INT,
    license_number VARCHAR(20),
    FOREIGN KEY (path_id) REFERENCES Paths(path_id),
    FOREIGN KEY (bus_id) REFERENCES Bus(bus_id),
    FOREIGN KEY (license_number) REFERENCES Driver(license_number)
);


CREATE TABLE messages (
	send_user int, 
    receive_user int, 
    message TEXT,
    category VARCHAR(50),
    FOREIGN KEY (send_user) references users(user_id),
    foreign key (receive_user) references users(user_id),
    primary key (send_user, receive_user)
);

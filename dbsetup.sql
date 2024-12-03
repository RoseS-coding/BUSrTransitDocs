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

INSERT INTO Users (username, password, name, email, userType) VALUES
('bob', 'password123', 'bob', 'bob@hotmail.com', 'USER'),
('marc', 'iguana', 'marc', 'marc@hotmail.com', 'ADMIN'),
('james', 'bjj', 'james', 'james@hotmail.com', 'USER'),
('jacob', 'bus4life', 'jacob', 'jacob@hotmail.com', 'DRIVER')
('bobbie', 'securePass2', 'Bobbie Brown', 'bobbie@example.com', 'USER'),
('charlie', 'securePass3', 'Charlie Davis', 'charlie@example.com', 'USER'),
('diana', 'securePass4', 'Diana Prince', 'diana@example.com', 'ADMIN'),
('edward', 'securePass5', 'Edward Norton', 'edward@example.com', 'USER'),
('frank', 'securePass6', 'Frank Castle', 'frank@example.com', 'DRIVER'),
('grace', 'securePass7', 'Grace Lee', 'grace@example.com', 'USER'),
('hannah', 'securePass8', 'Hannah Montana', 'hannah@example.com', 'USER'),
('ian', 'securePass9', 'Ian Malcolm', 'ian@example.com', 'USER'),
('julia', 'securePass10', 'Julia Roberts', 'julia@example.com', 'ADMIN');

INSERT INTO Paths (depart_location, arrive_location, depart_time, arrive_time) VALUES
('City A', 'City B', '2023-10-01 08:00:00', '2023-10-01 10:00:00'),
('City C', 'City D', '2023-10-01 09:00:00', '2023-10-01 11:30:00'),
('City E', 'City F', '2023-10-01 10:15:00', '2023-10-01 12:45:00'),
('City G', 'City H', '2023-10-01 11:00:00', '2023-10-01 13:00:00'),
('City I', 'City J', '2023-10-01 12:00:00', '2023-10-01 14:30:00'),
('City K', 'City L', '2023-10-01 13:30:00', '2023-10-01 15:30:00'),
('City M', 'City N', '2023-10-01 14:00:00', '2023-10-01 16:00:00'),
('City O', 'City P', '2023-10-01 15:00:00', '2023-10-01 17:00:00'),
('City Q', 'City R', '2023-10-01 16:00:00', '2023-10-01 18:30:00'),
('City S', 'City T', '2023-10-01 17:00:00', '2023-10-01 19:00:00');

INSERT INTO Bus (maintenance, license_plate, capacity) VALUES
('Regular', 'KA-01-HB-1234', 50),
('Needs Maintenance', 'KA-02-HB-5678', 40),
('Regular', 'KA-03-HB-9012', 60),
('Regular', 'KA-04-HB-3456', 45),
('Needs Maintenance', 'KA-05-HB-7890', 55),
('Regular', 'KA-06-HB-2345', 50),
('Regular', 'KA-07-HB-6789', 60),
('Needs Maintenance', 'KA-08-HB-0123', 40),
('Regular', 'KA-09-HB-4567', 55),
('Regular', 'KA-10-HB-8901', 50);

INSERT INTO driver (license_number, name, user_id) VALUES
('L001', 'jacob', 4),
('L002', 'Frank Castle', 9);

INSERT INTO route (path_id, bus_id, license_number) VALUES
(1, 1, 'L001'),
(1, 1, 'L001'),
(2, 2, 'L002'),
(2, 2, 'L002');

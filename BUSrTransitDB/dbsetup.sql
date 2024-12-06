CREATE DATABASE busrtransitdb;
USE busrtransitdb;
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
('julia', 'securePass10', 'Julia Roberts', 'julia@example.com', 'ADMIN'),
('mike', 'driverPass1', 'Mike Johnson', 'mike@example.com', 'DRIVER'),
('nina', 'driverPass2', 'Nina Williams', 'nina@example.com', 'DRIVER'),
('oscar', 'driverPass3', 'Oscar Isaac', 'oscar@example.com', 'DRIVER'),
('paul', 'driverPass4', 'Paul Atreides', 'paul@example.com', 'DRIVER'),
('quinn', 'driverPass5', 'Quinn Fabray', 'quinn@example.com', 'DRIVER'),
('rachel', 'driverPass6', 'Rachel Green', 'rachel@example.com', 'DRIVER'),
('sara', 'driverPass7', 'Sara Connor', 'sara@example.com', 'DRIVER'),
('tom', 'driverPass8', 'Tom Hardy', 'tom@example.com', 'DRIVER');

INSERT INTO users (username, password, full_name, email, user_type) VALUES
('anna', 'password11', 'Anna Smith', 'anna.smith@example.com', 'USER'),
('ben', 'password12', 'Ben Taylor', 'ben.taylor@example.com', 'USER'),
('claire', 'password13', 'Claire Williams', 'claire.williams@example.com', 'USER'),
('daniel', 'password14', 'Daniel Adams', 'daniel.adams@example.com', 'USER'),
('ella', 'password15', 'Ella Johnson', 'ella.johnson@example.com', 'USER'),
('finn', 'password16', 'Finn Brown', 'finn.brown@example.com', 'USER'),
('grace', 'password17', 'Grace Harris', 'grace.harris@example.com', 'USER'),
('henry', 'password18', 'Henry Clark', 'henry.clark@example.com', 'USER'),
('isabel', 'password19', 'Isabel Moore', 'isabel.moore@example.com', 'USER'),
('jack', 'password20', 'Jack Davis', 'jack.davis@example.com', 'USER'),
('karen', 'password21', 'Karen Lewis', 'karen.lewis@example.com', 'USER'),
('lily', 'password22', 'Lily Walker', 'lily.walker@example.com', 'USER'),
('matt', 'password23', 'Matt Allen', 'matt.allen@example.com', 'USER'),
('nora', 'password24', 'Nora Young', 'nora.young@example.com', 'USER'),
('oliver', 'password25', 'Oliver King', 'oliver.king@example.com', 'USER'),
('peter', 'password26', 'Peter Wright', 'peter.wright@example.com', 'USER'),
('quincy', 'password27', 'Quincy Scott', 'quincy.scott@example.com', 'USER'),
('rachel', 'password28', 'Rachel Carter', 'rachel.carter@example.com', 'USER'),
('sam', 'password29', 'Sam Mitchell', 'sam.mitchell@example.com', 'USER'),
('tessa', 'password30', 'Tessa Evans', 'tessa.evans@example.com', 'USER');

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
('L002', 'Frank Castle', 9),
('LMN4321', 'Mike Johnson', 14),
('JKL9876', 'Nina Williams', 15),
('QRS1234', 'Oscar Isaac', 16),
('TUV5678', 'Paul Atreides', 17),
('WXY4321', 'Quinn Fabray', 18),
('ZAB9876', 'Rachel Green', 19),
('CDE1234', 'Sara Connor', 20),
('FGH5678', 'Tom Hardy', 21);

INSERT INTO route (path_id, bus_id, license_number) VALUES
(1, 1, 'L001'),
(1, 1, 'L001'),
(2, 2, 'L002'),
(2, 2, 'L002');

INSERT INTO Route (path_id, bus_id, license_number) VALUES
(3, 3, 'LMN4321');

-- Route for Path ID 4
INSERT INTO Route (path_id, bus_id, license_number) VALUES
(4, 4, 'JKL9876');

-- Route for Path ID 5
INSERT INTO Route (path_id, bus_id, license_number) VALUES
(5, 5, 'QRS1234');

-- Route for Path ID 6
INSERT INTO Route (path_id, bus_id, license_number) VALUES
(6, 6, 'TUV5678');

-- Route for Path ID 7
INSERT INTO Route (path_id, bus_id, license_number) VALUES
(7, 7, 'WXY4321');

-- Route for Path ID 8
INSERT INTO Route (path_id, bus_id, license_number) VALUES
(8, 8, 'ZAB9876');

-- Route for Path ID 9
INSERT INTO Route (path_id, bus_id, license_number) VALUES
(9, 9, 'CDE1234');

-- Route for Path ID 10
INSERT INTO Route (path_id, bus_id, license_number) VALUES
(10, 10, 'FGH5678');

INSERT INTO messages (send_user, receive_user, message, category) VALUES
(1, 2, 'The bus broke down on the highway!', 'URGENT'),
(2, 1, 'I need the bus maintenance report for last month.', 'BUS_MAINTENANCE'),
(3, 4, 'The morning shift has been rescheduled to 7:00 AM.', 'SCHEDULING'),
(4, 3, 'Can you please send me the latest bus route map?', 'OTHER'),
(5, 1, 'The bus is due for its annual inspection next week.', 'BUS_MAINTENANCE'),
(1, 3, 'There is a traffic jam on the highway, please reroute the bus.', 'URGENT'),
(2, 4, 'The afternoon shift has been cancelled due to low demand.', 'SCHEDULING'),
(3, 5, 'Can you please send me the driver contact information?', 'OTHER'),
(4, 1, 'The bus maintenance team will arrive at 9:00 AM tomorrow.', 'BUS_MAINTENANCE'),
(5, 2, 'Please submit your daily report by the end of the day.', 'OTHER');

#meaningful queries
#Required to mark who created each query
    
#search for all of a driver's routes by their name
#Created by Roselyn Schnabel
# This query was created with the intent of use by a driver so that they can have a clear view of the routes assigned to their schedule. 
SELECT 
    d.name AS driver_name,
    p.depart_location,
    p.arrive_location,
    p.depart_time,
    p.arrive_time
FROM 
    Driver d
JOIN 
    Route r ON d.license_number = r.license_number
JOIN 
    Paths p ON r.path_id = p.path_id
WHERE 
    d.name = 'Frank Castle';

#search for all driver's routes
#Created by Roselyn Schnabel
# This is a basic query expanding on the search by name that displays all route data in understandable information instead of identifiers.
SELECT 
    d.name AS driver_name,
    p.depart_location,
    p.arrive_location,
    p.depart_time,
    p.arrive_time
FROM 
    Driver d
JOIN 
    Route r ON d.license_number = r.license_number
JOIN 
    Paths p ON r.path_id = p.path_id;

#identify high capacity buses and routes - A select statement with that includes at least two aggregate functions
#Created by Roselyn Schnabel
# This query can be used to identify areas of high capacity and use so bus use  can be analyzed for future expansion.
SELECT 
    b.capacity,
    b.license_plate,
    p.depart_location,
    p.arrive_location,
    p.depart_time,
    p.arrive_time
FROM 
    Bus b
JOIN 
    Route r ON b.bus_id = r.bus_id
JOIN 
    Paths p ON r.path_id = p.path_id
WHERE 
    b.capacity > 40;

#Provide route details in string format - A select statement that uses at least one join, concatenation, and distinct clause
#Created by Roselyn Schnabel
# This query displays route details in a user-friendly string format.
SELECT DISTINCT 
    CONCAT(d.name, ' - ', p.depart_location, ' to ', p.arrive_location, ' (', p.depart_time, ' to ', p.arrive_time, ')') AS Route_Details
FROM 
    Route r
JOIN 
    Driver d ON r.license_number = d.license_number
JOIN 
    Paths p ON r.path_id = p.path_id;

#Count the number of routes for each driver for payment by route, exclude drivers with no routes - A select statement that includes at least one subquery
#Created by Roselyn Schnabel
# This query can be used if driver payment is based on the number of routes traveled or to recognize drivers with an exceptional number of routes. 
SELECT 
    d.name AS Driver_Name,
    (SELECT COUNT(*) 
     FROM Route r 
     WHERE r.license_number = d.license_number) AS Route_Count
FROM 
    Driver d
WHERE 
    (SELECT COUNT(*) 
     FROM Route r 
     WHERE r.license_number = d.license_number) > 0
ORDER BY
    Route_Count DESC;

#Show routes in chronologic order - A select statement that uses an order by clause
#Created by Roselyn Schnabel
#This query can be used to display all of a day's routes in order. Administrators will have a schedule-focused view to help them anticipate events. 
SELECT 
    r.route_id,
    p.depart_location,
    p.arrive_location,
    p.depart_time,
    p.arrive_time
FROM 
    Route r
JOIN 
    Paths p ON r.path_id = p.path_id
ORDER BY 
    p.depart_time ASC;

#Automatically update the driver table - An insert statement that runs a trigger in which the trigger adds data or updates data in a table
#Created by Roselyn Schnabel
#This query will update when driver table when a new user with usertype Driver is created
CREATE TRIGGER after_user_insert
AFTER INSERT ON Users
FOR EACH ROW
BEGIN
    IF NEW.userType = 'DRIVER' THEN
        INSERT INTO Driver (license_number, user_id, name)
        VALUES (CONCAT('L', NEW.user_id), NEW.user_id, NEW.name);
    END IF;
END;

#Automatically update the driver table - A delete statement that runs a trigger in which the trigger deletes data in one table.
#Created by Roselyn Schnabel
#This query is a complement of the previous one, and automatically updates the driver table when a driver usertype is deleted. 
CREATE TRIGGER before_user_delete
BEFORE DELETE ON Users
FOR EACH ROW
BEGIN
    DELETE FROM Driver WHERE user_id = OLD.user_id;
END;


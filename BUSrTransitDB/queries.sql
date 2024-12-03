#meaningful queries

#search for all of a driver's routes by their name
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

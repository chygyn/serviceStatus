Service for creating objects with auto-intcrement id, status and current date.
Created by spring boot.
db - MySQL.

/task - POST method. Create new object. It returns 200 and ID of created object. When created finished service starts new 
thread for updating status "running" and current time, then wait 2 mins and updates status with "finished" and current time.
/task/{id} - GET method. Get object from db by id. If there is no object by id it sends 404. If id was't sent service returns 400.
/task/all - GET method. Get all objects from db.

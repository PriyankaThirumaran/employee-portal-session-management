# employee-portal-session-management
This is a Java Springboot-maven application that manages the employee information and employee sign-in, sign-out session management with all valid exceptions with employee information and session information being managed in MySQL DB.

Contains REST APIs to add/update/get/delete employee information managed using MySQL DB. Contains Employee class, Address class, Session class, ErrorMessage class. Utilizes Hibernate ORM to map POJO classes with the DB schema.

Uses Authentication controller to authenticate a valid user. The password of the employee is hashed using BCrypt Algorithm.
The login and logout functionality of an employee is managed by 'httpSession'.

Authorization is managed by filters.

The session gets timed out automatically if the user is idle for 5 minutes.

The REST APIs run on port 8082.

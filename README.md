
# Student Management System App

This system helps a school's management to keep track of their students classes and grades. The students and teachers in the school can also benefit from this system. Students can see their grades and instructors can display and update grades of their students.

# Technologies Used

This system utilizes a spring boot rest API in the backend, while React is used for the frontend. MySQL is used as a database.

# Software structure

The API has a controller for authentication purposes and 3 controllers for 3 levels of access: student, instructor and admin. Each role can access only their own controller. Authentication controller is common for all users.

# User Authentication

User authentication is done by jwt tokens. Each time a user logs in, a jwt is created an given to the user. The jwt is stored in localStorage of the browser. The user can use this token to send requests to the api.
  

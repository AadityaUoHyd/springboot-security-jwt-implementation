# Implementing springboot-security-jwt-demo project. Lets test it.

# First run the code as spring boot program.

# In Postman hit POST mapping to generate jwt, and first time have to login with username & password: http://localhost:9090/authenticate 
With Header : Content-Type Value : application/json;  <br>
{
  "userName":"aadi",
  "password":"password"
}

# Now go to GET mapping for using another method(service) and here login is not required, jwt will do it: http://localhost:9090 
Header : Content-Type Value : application/json   <br>
Header : Authorization Value : Bearer [put here jwt token received from post mapping]

# You’ll get output after accessing successfully that Get mapping method as : "Welcome to Aaditya's page !!"

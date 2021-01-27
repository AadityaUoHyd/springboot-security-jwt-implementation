# springboot-jwt-demo project.

# First run it as spring boot.

# In Postman hit POST mapping to generate jwt : http://localhost:9090/authenticate 
With Header : Content-Type, Value : application/json
{
  "userName":"aadi",
  "password":"password"
}

# Now go to GET mapping : http://localhost:9090 
Header : Content-Type, Value : application/json
Header : Authorization, Value : Bearer <jwt>

# You’ll get output as : Welcome to Aaditya's page !!

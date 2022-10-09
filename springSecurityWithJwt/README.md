# Spring Security and JWT

- Stage#1 - User/Client register process.
  Model : User(id, name, user, password, roles) – Client has to send above details as JSON format that will be converted into User class object
  and finally saved in Database.
  
  In Database, it will look alike :
  ![](https://github.com/AadityaUoHyd/springboot-security-jwt-implementation/blob/master/springSecurityWithJwt/diagram.png)

- Stage#2 – JWT Util class code.
  * Generate token using subject.
  * Validate token using username, subject and expiry date.

- Stage#3 – JWT Token generation without spring boot security
  Read username and password as JSON format, convert to UserRequest obj . Generate token and define one message , return this data as JSON format
  using UserResponse obj. Now write UserRestController class.

- Stage#4 - JWT Token after user authentication
  After adding spring boot security dependency in pom.xml, write AppConfig class for password encoding. Write SecurityConfig class for authentication & authorization. 
  Corresponding antmatchers will take care of Authorization via permitAll() or anyRequest().authenticated(). Load your user from DB via loadUserByUsername() 
  from UserDetailsService for cross checking entered credentials matches with DB or not. Ensure SessionCreationPolicy is STATELESS.
  In Authentication, UsernamePasswordAuthenticationToken is being verified via AuthenticationManager, if valid then create JWT else trigger InvalidUserAuthEntryPoint.

- Stage#5 – JWT Token verification after 2nd request onwards
  Provide filter so that it verifies the 2nd request must have token & create new token only if its valid user with user has been given no erstwhile token
  (token didn’t exist in current SecurityContextHolder) or given token time got expired.
  
- Now test your code in POSTMAN.
  
  Adding USER :
  ![](https://github.com/AadityaUoHyd/springboot-security-jwt-implementation/blob/master/springSecurityWithJwt/add_user.png)
  
  Generating TOKEN :
  ![](https://github.com/AadityaUoHyd/springboot-security-jwt-implementation/blob/master/springSecurityWithJwt/token.png)
  
  Trying to login with generated token :
  ![](https://github.com/AadityaUoHyd/springboot-security-jwt-implementation/blob/master/springSecurityWithJwt/login.png)
  
  In case we provide wrong token, gives 401 Forbidden Error :
  ![](https://github.com/AadityaUoHyd/springboot-security-jwt-implementation/blob/master/springSecurityWithJwt/withWrongToken.png)

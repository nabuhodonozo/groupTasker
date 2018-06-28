# Group Tasker
Webapp that helps orgranizing group time management, setting tasks for members. I planning to make combination of [trello](), [jira]() and [google calendar]() in one app. In the future I'd like to port it to android app. 

Functionalities:
- Spring Security jdbc login authentication
- Validation of user input data
- Registration 
- Adding groups
- Ability to add other members to specific group
- Each group holds tasks for it's members
- Each task can be commented
- Checking tasks of specific user
- Access restriction (user that is not memeber of group cannot access it)
- Many more that needs to be implemented
        
Technologies used:
* [Java8](www.google.com)
* [Spring](https://spring.io/projects/spring-framework)
    * [Spring Boot](https://spring.io/projects/spring-boot)
    * [Spring Security](https://spring.io/projects/spring-security)
    * [Spring data jpa](https://spring.io/projects/spring-data-jpa)
    * [Spring MVC]()
* [Hibernate]()
* [MySQL]()
* [Maven](https://maven.apache.org/)

## Project is not finished yet
I have plenty ideas and functionalities to add, just need some time (;

To do:
* User friendly interface (bootstrap probably)
* Replace some controllers methods with JavaScript, jQuery and Ajax
* Add reminders (Email, or just browser notification)

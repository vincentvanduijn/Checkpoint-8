//---------2-8-2022-------------
// TO DO: Get docker and mySQL connected to the java checkpoints folder

//---------5-8-2022-------------
// Updated: Changed the code in the Automaker and Vehicle class to use Lombok, and deleted all getters/setters/hashcode etc..

//---------8-8-2022-------------
// To Do: refactor points 2 to 4 from checkpoint 7
// Updated: Added the Log4j2 instead of the system out prints

//---------9-8-2022-------------
// To Do: Refactor the "while" statement that creates Vehicle objects into a method. Then call on that method from the "Find by" methods. 
// To Do: Refactor the "formatted object" statement that replaces and trims the output. Then call on that method in all the switch logic options.
// To Do: Make sure the output in switch option 2 and 6 display the Automaker name or id. 
// To Do: Add allias for the switch 1 (vehicle = v.name etc.)
// To Do: Search function save in index for Java (look up)
// To Do: Find out how to make sure the database doesn't lock on a search query 
// Updated: Add the created on variable to all classes, make sure the toString is formatted so the output is clearly readable
// Updated: Removed Date creation classes
// Updated: Refactor points 2 to 4 from checkpoint 7
// Updated: Make sure the "search by model" logic only displays vehicles that are identical to the search input. 

//---------10-8-2022-------------
// To Do: Make sure to catch exceptions when invalid input is given by the user
// To Do: Refactor the "while" statement that creates Vehicle objects into a method. Then call on that method from the "Find by" methods. 
// To Do: Refactor the "formatted object" statement that replaces and trims the output. Then call on that method in all the switch logic options.
// To Do: Make sure the output in switch option 2 and 6 display the Automaker name or id. 
// To Do: Search function save in index for Java (look up)
// To Do: Find out how to make sure the database doesn't lock on a search query
// To Do: Is the VehicleService class still needed because of the reduced size and logic? Ask Jens if it can be combined with VehicleApplicationLogic class and call directly from the Repository
// Updated: Working delete function for vehicle model
// Updated: Add allias for the switch 1 (vehicle = v.name etc.)
// Updated: Make sure the remove/delete function issues an error when there is no matching vehicle with the user input

//---------11-8-2022-------------
// To Do: Make sure to catch exceptions when invalid input is given by the user
// To Do: Refactor the "while" statement that creates Vehicle objects into a method. Then call on that method from the "Find by" methods. 
// To Do: Make sure the output in switch option 2 and 6 display the Automaker name or id. 
// To Do: Search function save in index for Java (look up)
// To Do: Find out how to make sure the database doesn't lock on a search query
// To Do: Is the VehicleService class still needed because of the reduced size and logic? Ask Jens if it can be combined with VehicleApplicationLogic class and call directly from the Repository
// Updated: Refactor the "formatted object" statement that replaces and trims the output. Then call on that method in all the switch logic options.

//---------12-8-2022------------
// To Do: Make sure to catch exceptions when invalid input is given by the user
// To Do: Make sure the output in switch option 2 and 6 display the Automaker name or id. 
// To Do: Search function save in index for Java (look up)
// To Do: Find out how to make sure the database doesn't lock on a search query
// To Do: When the checkpoint is working and finished, delete menu options 7 and 8. They are just to check if the methodes from AutomakerService/Repository work. 
// Updated: Combined the VehicleService class, AutomakerService class and the VehicleApplicationLogic class into ApplicationService class 
// Updated: Refactor the "while" statement that creates Vehicle objects into a method. Then call on that method from the "Find by" methods.

//---------15-8-2022------------
// To Do: Make sure to catch exceptions when invalid input is given by the user
// To Do: Make sure the output in switch option 2 and 6 display the Automaker name or id. 
// To Do: Search function save in index for Java (look up)
// To Do: Find out how to make sure the database doesn't lock on a search query
// To Do: When the checkpoint is working and finished, delete menu options 7 and 8. They are just to check if the methodes from AutomakerService/Repository work. 
// To Do: Make updated_on column, for the update (DateTime mySQL -> unix timestamp Java). Make the created_on for save (LocalDateTime) 

//---------16-8-2022------------
// To Do: Make sure the update does not duplicate al model types, make sure the Vehicle object gets the right Automaker_Id when updating
// To Do: Make sure to catch exceptions when invalid input is given by the user
// To Do: Make sure the output in switch option 2 and 6 display the Automaker name or id. 
// To Do: Search function save in index for Java (look up)
// To Do: Find out how to make sure the database doesn't lock on a search query
// To Do: When the checkpoint is working and finished, delete menu options 7 and 8. They are just to check if the methodes from AutomakerService/Repository work. 
// To Do: Make updated_on column, for the update (DateTime mySQL -> unix timestamp Java). Make the created_on for save (LocalDateTime)

//---------18-8-2022------------
// To Do: Make sure to catch exceptions when invalid input is given by the user
// To Do: Make sure the output in switch option 2 and 6 display the Automaker name or id. 
// To Do: Search function save in index for Java (look up)
// To Do: Find out how to make sure the database doesn't lock on a search query
// To Do: When the checkpoint is working and finished, delete menu options 7 and 8. They are just to check if the methodes from AutomakerService/Repository work. 
// To Do: Make updated_on column, for the update (DateTime mySQL -> unix timestamp Java). Make the created_on for save (LocalDateTime)
// Updated: All options from checkpoint 7 now work
// Updated: Make sure the update does not duplicate al model types, make sure the Vehicle object gets the right Automaker_Id when updating

//---------7-9-2022------------
// Updated: Rework the Vehicle builder methods + PreparedStatements for Find by Automaker, Find by Model, Delete and ShowAll to include the vehicle_type table and id/ type
// Updated: Rework the PreparedStatements for Update and Save to include the vehicle_type table 
// Updated: Checkpoint 8, rework the code according to the second point. Then move on to the Springboot framework
// To Do: Rework the menu options to GET, POST, DELETE, PUT
// To Do: Add the needed exceptions 
// To Do: After refactoring the code to the 4th point (mySQL running in a docker compose file) of checkpoint 8, make sure you look at the tips from last checkpoint 
// to Do: Check which Lombok which annotations to use

//---------8-9-2022------------
// To Do: Rework the menu options to GET, POST, DELETE, PUT
// To Do: Add the needed exceptions 
// To Do: After refactoring the code to the 4th point (mySQL running in a docker compose file) of checkpoint 8, make sure you look at the tips from last checkpoint 
// to Do: Check which Lombok which annotations to use

//---------12-9-2022------------
// To Do: Rework the menu options to GET, POST, DELETE, PUT
// To Do: Add the needed exceptions 
// To Do: After refactoring the code to the 4th point (mySQL running in a docker compose file) of checkpoint 8, make sure you look at the tips from last checkpoint 
// to Do: Check which Lombok which annotations to use

//---------13-9-2022------------
// To Do: Rework the menu options to GET, POST, DELETE, PUT
// To Do: Add the needed exceptions 
// To Do: After refactoring the code to the 4th point (mySQL running in a docker compose file) of checkpoint 8, make sure you look at the tips from last checkpoint 
// to Do: Check which Lombok which annotations to use

//---------14-9-2022------------
// Updated: Rework the menu options to GET, POST, DELETE, PUT
// To Do: Add the needed exceptions 
// To Do: After refactoring the code to the 4th point (mySQL running in a docker compose file) of checkpoint 8, make sure you look at the tips from last checkpoint 
// to Do: Check which Lombok which annotations to use

//---------15-9-2022------------
// Updated: Rework the menu options to GET, POST, DELETE, PUT
// Updated: Check which Lombok which annotations to use
// To Do: Add the needed exceptions 
// To Do: After refactoring the code to the 4th point (mySQL running in a docker compose file) of checkpoint 8, make sure you look at the tips from last checkpoint 
// To Do: Talk with Jens about the pro's and con's of the different annotations between Lombok and Spring

//---------16-9-2022------------
// Updated: Add the needed exceptions 
// To Do: After refactoring the code to the 4th point (mySQL running in a docker compose file) of checkpoint 8, make sure you look at the tips from last checkpoint 
// To Do: For the Delete option, check if you can print the deleted object
// Tip: Pageable String can be switched with Asc, Desc 

//---------19-9-2022------------
// To Do: After refactoring the code to the 4th point (mySQL running in a docker compose file) of checkpoint 8, make sure you look at the tips from last checkpoint 
// To Do: For the Delete option, check if you can print the deleted object
// To do: Ask if Parsing links (name=&Id=) matter if you have a option in Swagger to fill those criteria in the GET command (Ask Jens)

//---------20-9-2022------------
// To Do: After refactoring the code to the 4th point (mySQL running in a docker compose file) of checkpoint 8, make sure you look at the tips from last checkpoint 
// To Do: For the Delete option, check if you can print the deleted object
// To Do: Ask if Parsing links (name=&Id=) matter if you have a option in Swagger to fill those criteria in the GET command (Ask Jens) 
// To Do: Ask Jens about the update method in the ControllerTest + ServiceTest
// To Do: Check for errors in your Tests



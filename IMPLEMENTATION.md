# Implementation Decisions
## Task 1
There were some details in the program that surprised me when implementing the feature of getting all direct reports 
under a specific employee. The one major challenge though was understanding how to access the 
employee's information under another given employee. I assumed when an employee was read from the JSON file, and the APIs 
returned an employee object, that the directReports list of employees contained employees with all the attributes filled out. 
This assumption failed to get the correct number of reports due to the reports under an employee having null attributes.
Understanding that the JSON file only really held the employee IDs, I tried to find a way to retrieve their full data before 
attempting to access the root employee's directReports when trying to count. When the readReport API is called, the 
program checks each current object in the given employee's directReports list, then updates them to contain all their 
attributes that were stored in the JSON. I would however prefer to have the directReports list updated right after an 
employee is created or read. I did attempt this approach, but it didn't work out due to the JSON list holding Strings of 
IDs and not more lists of Employee attributes for the directReports, hence why I chose the other approach.
## Task 2
To connect the employee data to compensation, I chose to add a employeeId attribute to the Compensation type. This made 
both implementation and testing a lot cleaner in my opinion, as I avoided less dependencies and coupling between classes. 
I also think it more neat to store the employeeId instead of an Employee object as the databases are kept simpler, and instead
of making extra employee objects during the Compensation API calls, I was simply able to input the id, similar to inputting foreign keys 
in a SQL database to retrieve data. The one dependency I quite don't like in my design is my CompensationServiceImpl.java class having an 
instance of the EmployeeServiceImpl class. I made this choice in order to validate employees before attempting to create compensation 
data for them or accessing their already made data. Besides testing for correct data handling, I also tested if the system properly 
dealt with employees that didn't exist. I got the correct exceptions thrown. I did however remove those tests from the test class in order to 
keep the code neat and not have null expections in the index.html test output.
## Extra
I wanted to added a compensation_database.json to the system to test the compensation feature further, but unfortunately  dealt with some issues 
when it came to configuration. I attempted to create another config class to separate the two databases, but after I found myself significantly 
changing the class structure, I decided to revert the system back to its original state.
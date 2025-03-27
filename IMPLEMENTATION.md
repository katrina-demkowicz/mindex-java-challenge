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
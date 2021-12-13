# Sparta Database Project(week 5)
This project is a program that takes .CSV files, reads the file and cleans the data inside it filtering for corrupted as
well as duplicate data entries, while simultaneously adding these said entries to their own separate collections.
The main collection will be added to a database that's been produced by the program to which the user is able to see 
the entire collection and retrieve individual data entries.The program makes user of streams,lambdas and threads to 
increase in performance in reading the selected file.
***

# How to Use
* ###  connection.properties 
  * Before attempting to run the program it is IMPORTANT to setup the connection.properties file found in the resources 
  folder. In the properties file edit the database_user and the database_password to your username and password for the 
  MYSQL database as for database_url like the image below if you would like to connect to via a different url then 
  change database_url according to your chosen url but remember to leave the "/" after localhost blank/empty.
  
    ![connectionp](README/connectionp.PNG?raw=true)
    
      <b>Fig.1 - connection.properties</b>


* ### Interface
  * When running the program you will see a menu printed in console prompting you to make a selection of the type of 
  file that will be used in this session, selecting 1 or 2 will simply output a message saying that the file has been
  selected.
    ![UI1](README/UI1.PNG?raw=true)
      
      <b>Fig.2 - Main Menu on program start up</b>

  * However, selecting 3 will open up a file browser which would enable you to select a file of your own choosing,
    (it should be noted that currently the program will only accept .CSV files and any other file type will cause an
    execution to be thrown).

    ![UI2](README/UI2.PNG?raw=true)

      <b>Fig.3 - Option 3: File Browser</b>
  
  * Once the file has been selected a line will be printed stating the chosen file, and you will be then prompted with 
   the option to select the number of threads you want to use for the processing of the selected file.
  
    ![UI3](README/UI3.PNG?raw=true)
    
      <b>Fig.4 - Number of Threads Selection Prompt</b>
  
  * From there you will be able to look up individual entries via the Query selection menu which will ask for a selection.   
  
    ![UI4](README/UI4.PNG?raw=true)
      
      <b>Fig.5 - SELECT Query</b>
  
  * In every selection that is made the last input that the program will ask for is what exactly the user would like to
 search for, as shown in the example below the gender is what is being asked of the user and the user has to type M or F.
  
    ![UI5](README/UI5.PNG?raw=true)
  
    <b>Fig.6 - SELECT Query Entry Specification</b>

  * once the selection has been specified then the program will return all the rows that match the specified entry. 

    ![UI6](README/UI6.PNG?raw=true)

    <b>Fig.7 - SELECT Query Rows Output</b>
***
# Known Issues

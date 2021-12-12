# Sparta Database Project(week 5)
This project is a program that takes .CSV files, reads the file and cleans the data inside it filtering for corrupted as
well as duplicate data entries, while simultaneously adding these said entries to their own separate collections.
The main collection will be added to a database that's been produced by the program to which the user is able to see 
the entire collection ** and retrieve individual data entries ** 
** The program makes user of streams,lambdas ** and potentially threads to increase in performance ** in Reader.java  **
***

# How to Use
* ###  connection.properties 
  * Before attempting to run the program it is IMPORTANT to setup the connection.properties file found in the resources 
  folder. In the properties file edit the database_user and the database_password to your username and password for the 
  MYSQL database as for database_url like the image below if you would like to connect to via a different url then 
  change database_url according to your chosen url but remember to leave the "/" after localhost blank/empty.
  ![connectionp](README/connectionp.png?raw=true)
    
    <b>Fig.1 - connection.properties</b>


* ### Interface
  * When running the program you will see a menu printed in console prompting you to make a selection of the type of 
  file that will be used in this session, selecting 1 or 2 will simply output a message saying that the file has been
  selected.
  
  ![UI1](README/UI1.png?raw=true)

  <b>Fig.2 - Main Menu on program start up</b>


* However, selecting 3 will open up a file browser which would enable you to select a file of your own choosing,
  (it should be noted that currently the program will only accept .CSV files and any other file type will cause an
  execution to be thrown)

  ![UI2](README/UI2.png?raw=true)

    <b>Fig.3 - Option 3: File Browser</b>


***
# Known Issues

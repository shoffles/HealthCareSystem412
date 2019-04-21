# AlhpaCare
AlphaCare is a healthcare management application created for a IST 412 course at Penn State University.  Commissioned by ATZ Healthcare, AlphaCare is an opportunity to learn project management and clean coding skills.

## Getting Started

This application uses PostgreSQL for data persistency. Before using the application, follow the next instructions to prepare a PostgreSQL environment to work with the application.

#### Step 1: Downloading and configuring PostgreSQL

First, download PostgreSQL, found [here](https://www.postgresql.org/download/)
For PostgreSQL installation, our app runs on port 5432, with a username of "postgres" and a default password of "password".  However, if you have other credentials, it will work so long as you adjust the database setup and SQLConnector class in the Netbeans Project

Second, download the PostgreSQL .jar file, found [here](https://jdbc.postgresql.org/download.html)
Remember where you save this file, as it will be important for importing to the project.

#### Step 2: Netbeans Implementation
Open the project in Netbeans IDE.  Upon opening the project, you will want to right click the Libraries file, and select "Add .jar".
Select the PostgreSQL file and import it to the project.

#### Step 3: Creating Database in SQL Shell
Open psql, also known as SQL Shell. Move through the login process (For example, pressing enter when default entries are set).  
Enter the following to show all databases in your PostgreSQL database:
```
\l
```
To create a new database, we will enter the following:
```
CREATE DATABASE healthcareapp
```
Since our application is set to connect to a database on local host named "healthcareapp", we enter it here for the database name.
You may also press
```
\l
```
again to see that the database has been created.

#### Step 4: Creating the Netbeans Service
Now we navigate to Netbeans and select the services tab.  Open the Drivers directory, right click PostgreSQL, and select "Connect With"
Enter the database info we just created.  You may check the connection here to see if it is a valid connection. Accepting these values will create a new file in the Services directory for our database connection.  (In our example, we used "public" context).

#### Step 5: Creating the Database Schema
Now we will create the database tables and links.  The database schema will be listed below after the "Getting Started" section.
Navigate back to psql, and use the following to change to the "healthcareapp" database:
```
\c healthcareapp
```
Then we will execute the following SQL to the database, creating the table.  Each line break is punctuated by a press of the enter key to move to the next line of SQL in the query.
```
CREATE TABLE appuser (
user_id serial PRIMARY KEY,
user_name VARCHAR(255) UNIQUE NOT NULL,
user_type VARCHAR(255) NOT NULL,
user_password VARCHAR(255) NOT NULL,
user_first_last VARCHAR(255) NOT NULL,
user_dob VARCHAR(255) NOT NULL
);
```
Afterwards, psql should display a line that says "CREATE TABLE", which means the query ran and the table is created.  You may test to see it's (currently empty) contents by running the following
```
SELECT * FROM appuser;
```

Now, we will want to create one user in the table for simple login procedures to the application.  Run the following statement (Replacing <NAME> and <DATE> (As a string until later Implementation) with any info you wish).
```
INSERT INTO appuser (user_name, user_type, user_password, user_first_last, user_dob
VALUES (‘admin’, ‘doctor’, ‘password’, ‘<YOUR NAME>’, ‘<ANY DATE>’);
```

Now to set up the other two tables in the database.  One is the assignment table, which manages viewing permissions of users, while the other is report, which handles report data for different users.
```
CREATE TABLE doctor_to_patient_assignment (
viewer_id integer NOT NULL,
to_be_viewed_id integer NOT NULL,
creation_date timestamp without time zone,
PRIMARY KEY (viewer_id, to_be_viewed_id),
CONSTRAINT doctor_to_patient_viewer_id_fkey FOREIGN KEY (viewer_id)
REFERENCES appuser (user_id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT doctor_to_patient_to_be_viewed_id_fkey FOREIGN KEY (to_be_viewed_id)
REFERENCES appuser (user_id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE NO ACTION
);
```
And
```
CREATE TABLE report (
report_id serial PRIMARY KEY,
assigned_user_id integer NOT NULL,
report_name VARCHAR(255) NOT NULL,
report_body VARCHAR(600) NOT NULL,
CONSTRAINT report_assigned_user_id_fkey FOREIGN KEY (assigned_user_id)
REFERENCES appuser (user_id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE NO ACTION
);
```
And now our databases are set up for the application!


*There may be an issue regarding a missing jar file. To fix this, right click on the project in Netbeans, go to properties,
libraries tab, and remove the missing jar from the list. The application should then run.*

## Database Schema

Database Schema created and organized by Konnor Sidler

#### Table: appuser
##### user_id
This value is auto generated upon saving to the table, and is the primary key of the table as well as a foreign key in the other two tables
##### user_name
This is a string for a username of a user. It is used for searching when entering reports to the system
##### user_type
This is a string, which may have two values, doctor and patient.  A doctor may create reports, while a patient may not. This value determines the views loaded by the application upon logging in
##### user_password
This should not be visible, but since this is a testing demo version of the application, we wanted this to be transparent for testing purposes.  Used for login authentication
##### user_first_last
First and Last name of a user. (Ex. "Konnor Sidler").  Used for sorting data in some views
#### user_dob
Currently a string until later implementation, simply the date of birth of a user.  Format: "MM/DD/YYYY"

#### Table: doctor_to_patient_assignment
##### viewer_id
This ID is who may view the records of the other ID in the table.
##### to_be_viewed_id
This ID is who will be assigned to be viewed by the "viewer"
##### creation_date
Not implemented as of yet

#### Table: report
##### report_id
Primary key of the table and the unique ID of a report
##### assigned_user_id
This ID is who the report is for. Used for Join Queries so that reports of a specific user may be shown
##### report_name
Descriptor of a report, a title
##### report_body
The body of the report, containing various information about the assigned user for that check-up

# Refactoring Changes
## User
The refactored user class consists of userId, username, type, password, name, and DOB along with getters for obtaining this information. These map to a database table for saving.

User class refactoring done by Thomas Shoff

## UserAuth
UserAuth is the main class for getting and creating new entries into the database, and contains a static variable that the database loads its information into when queried. It has methods for getting specific users, getting a users type, checking the username and password for login, and creating new user accounts.

UserAuth class refactoring done by Thomas Shoff
UserAuth PostgreSQL implementation done by Konnor Sidler

## Report
The report class consists of information related to reports on patients. This includes a unique id, reportDate, reportTitle, reportBody, username, name, and DOB (the last 3 identify the patient the report is for). Getters have been included.

Report class refactoring done by Thomas Shoff

## ReportList
Similar to UserAuth, ReportList contains a static mapping of reports that are retrieved from the database. All loading of reports is done through here.

ReportList class refactoring done by Thomas Shoff
ReportList PostgreSQL implementation done by Konnor Sidler

## PostgresConnector
A simple class that instantiates a link to the postgres database.

PostgreSQL class refactoring done by Konnor Sidler

## Controller
This special controller class serves only one person, and that is to track who is currently logged into the system. This determines what views are displayed and also what information is received from SQL transactions. A simple class that serves a big purpose.

Controller class refactoring done by Thomas Shoff

## View Controllers
All the different views of the system have their own controller that is associated with them. It controls what actions are performed when different buttons are clicked on the various views.

View Controllers class refactoring done by Thomas Shoff and Konnor Sidler

## Assignment
The assignment button on the doctor dashboard screen allows automatic mapping of usernames from doctors to patients without a manual database insert.  

Assignment class creation done by Steven Weber, SQL was created by Konnor Sidler

## Export
The export functionality allows a user to export a report to their own local machine by first selecting a row on the dashboard and clicking the export button

Export functionality creation done by Steven Weber

## Latest Release Support
Latest release is for M05-A02, implementing Use Cases 3 and 4.

For the sake of organization and flow of the application for testing, we rearranged the order of the use cases.   

#### Set Up: Creating a new user
For this, we suggest creating a patient so that they may see their reports.  Select the "Create User" tab on the login page, and create a patient.  This will save that user to the database, along with a mapping so they may view their own report. Support for creating other mappings will be implemented next.  You can then log into the system with that username and password.  Remember the username and password for testing other use cases!

#### Use Case 1: Assignment Based Viewing of Patients
Logging into the admin viewer, you may only see reports of the patients that are assigned to you.  Of course, on a fresh release, there are no other users on the system.  We recommend that you create a few test patients and use the assignment button on the admin page to create assignments to other users you create.  

For example, if testdoctor1 is assigned to testpatient1, and testdoctor2 is assigned to testpatient2, their respective dashboard views will only display the information they are allowed to see.  

#### Use Case 2: Creating a report
Log into the admin user (Username: admin, Password: password).  This will bring you to a report view, along with a button to create a report.  On the create report page, enter the user you created into the search bar, and search for the user.  This will auto populate the fields. Now, create a report title and body, and select the "create" button.  This will save your report! Notice how it does not display on the table screen, as there is currently no association mapping from admin to the user you created.

#### Use Case 3: Displaying and Viewing a list of Report objects
If you would like to see the data for this new report, you can create a mapping by logging in as a doctor and using the "Create Assignment" button to create a mapping between two usernames. This will create a mapping which is JOINED and queried when loading the view for viewers.  Now, when logging into the admin class, you may now see that the report you entered before is now present on the list!

#### Use Case 4: Exporting a Report
First, log in as a user who had a report created.  Now, you may see their dashboard and list of reports.  Click a row, click the export button, and select a destination.  The report is now saved to your local device.

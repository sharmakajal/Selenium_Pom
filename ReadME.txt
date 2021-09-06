#Author - Kajal Sharma
#Date - 13/07/2020
#Project Name - Selenium-Test


How to Use ?

#SetUp & Installation - To setup this project, You must have the following tools -

1.Jdk - set Env variable once after installation
2.Eclipse
3.Import this project from Eclipse - File Option
4.Once Imported, open the project and add testng.jar,other Selenium jars which you see in the Referenced Libraries
5.Make sure All error are gone after importing the project and adding neccessary jars and exe files.
6. Please add chrome, firefox,IE, Safari, Edge browser exe file in com.external folder.

#Understanding the Project

1. com.external package contains all exe browser drivers
2. com.page.classes - include page class for the project
3. com.test.classes - include the test class for the Page
4. com.setup.utilities - include the test setup files, all basic browser configurations
5. Configs - folder containing the property file to read common data from.
6. TestNG - jar added to the project
7. Referenced Libraries - all necessart jars are added here.

#Running the Project.

As the project is based on POM model, follow below steps to run it.
1. Make sure there are no errors, clean the project and refresh it.
2. Go to the com.test.classes and open the file - PaymentTestCase.java, right click on the file and run as - testng.
verify the Test has run
3. Make sure to view the result in the Console of the Eclipse.


This project is an automated UI testing for the NHS Eligibility  Checker service:  
https://services.nhsbsa.nhs.uk/check-for-help-paying-nhs-costs/start

Used with -
IntelliJIDE (IDE)
- Java 21 (Java version)
- Selenium WebDriver 
- Cucumber (BehaviourDrivenDevelopment with Gherkin written feature files)
- JUnit (supports run feature)
- WebDriverManager (to handle browser drivers automatically)
- JavaScript 
- Maven (Handles all the dependencies)

---------------------

--- To Run: ----
Ensure in the 'steps' Package all the step definitons pages are written correctly.
Then - In the feature folder, click into endtoend.feature and you can run the scripts with cucumber there from the feature file itself. 

Comments are shown within the code, to explain the use and actions in the lines of code.

Once you have ran the test scenario from feature file, you can see if steps and scenarios passed or failed in the IDE itself. The last test does not seem to teardown or quit so have to manually close the last browser to then see results on IDE. 4 scenarios and 14 steps passed. (At the bottom in the run section)

TestRunner - Tells cucumber how to run feature files and glues everything together.
Hook page - implemented to handle @after and @before classes for set up and tear down. Meaning Webdriver is launched once per scenario, Webdriver is then closed, and Step definition pages can be cleaner.

1. Open resources
2. Features 
3. endtoend.feature
4. Run the 4 Scenarios from this feature file.

----------------------

Test scenarios:
1.Complete End to end test
2.DOB no input error validation
3.Restrict user journey if selecting Northern Ireland
4.Footer link validation test

GITHUB REPO


------------------------

Potential future enhancements of the automation suite:

Page Object Model for better maintainability. 
Cross Browser testing (Safari, Firefox etc)
Add Reporting features.
Have the hooks page separately away from test step definitions to avoid confusion.
Have each page on a separate Java class for better maintainability and reuse of code.

-------------------------

Potential future enhancements of the service in general:

Character limits, lets user input too many numbers/letters/invalid inputs into the input fields. (Error received but allows the input before error)



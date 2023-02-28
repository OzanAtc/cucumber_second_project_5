This is Java based BDD(Behaviour Driven Development) framework that includes Selenium as automation, JUnit as unit, and Maven as build tool.
Our core programming is Java. We use Maven to create a simple folder structure, organize our classes and centralize our dependencies with their
updated versions. This automation platform is consisted of many libraries such as WebDriverManager for cross-browser testing,
HTMLUnitDriver for headless testing and Selenium WebDriver for our UI scripts to automate web applications.
We use JUnit for its assertions, annotations and report system.

We use Cucumber tool to automate web application through Feature(Directory), Step Definition, Runner, and Pages. Features have scenarios
and these scenarios work coordinately with the test cases that are implemented in Step Definition package. We also have Hook class located
in that package which helps us to run our "After Method" after each test execution. We use Runner class to be a link between
the class and "Feature File" first, and then makes a connection to the test cases stored in Step Definition package.

The framework follows Page Object Model and Singleton design patterns.Singleton allows us to use the same driver instance that we created to use in all over the framework.
Page object model for each separate pages to locate their web elements and the methods.And we use PageFactory in page classes
to make the web elements initiated to these classes. We apply OOP principles in this framework to inherit the pages, encapsulate attributes,
and reach them out by getter and setter methods in order to prevent easy accessibility to them and increase the data security. 

We have a base class to store objects and functions created to test. We also keep our global configuration details inside private instance
of properties class, that are the details of initializing the WebDriver through browser, implicit Waits, and the application URL. 
Our Utility package stores and handles commonly used methods during the entire framework to achieve code re-usability, reduce maintenance effort
and increase readability. 

We use "GIT" for the version control tool, and Jenkins for CI/CD to execute the test cases according to the needs on daily basis.We benefit from Java 
faker library to generate the test data and use these data on the framework for testing purposes.I currently have two testing suites that works integrated with Jenkins
,and that are Smoke and Regression suites. These suites can be run with the maven commands(mvn test -DCucumber.options="--tags @Smoke) or 
(mvn test -DCucumber.options="--tags@Regression). The main goal of these commands is to make a direct connection between pom.xml and the Runner class, 
and then from there find those with @Tags on Feature Files and execute the related scenarios with these tags. Once the execution is completed, 
surefire reports are generated under the target folder. Test results are shared with corresponding people through Jenkins.


# Automation

Test 2 APIs using Rest Assured, TestNG and Maven.

## Some details to consider

Even Though the project is too small, my intention was to show as most as I know with this that's why I created extra files that may not be necessary for instance conf files, xml file and others.

* Running the PublicApiTest.java located in src/test/java path with TestNG you will get the API's responses verified. 
* I created a __config.properties file in src/main/resources/
* I've created and suite .xml file called restAssured_api_test.xml located in src/test/resources/testng-suite This may not be neccesary because the project is too small. If it would be bigger and more than one test class will call, it will have more sense.
* One test is a GET method to know the weather in Tandil city. The second one is to create a new user (POST method)
* There is a json file that is used in the POST request located in src/test/resources/api

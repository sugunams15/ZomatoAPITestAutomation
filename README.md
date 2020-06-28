# ZomatoAPITestAutomation
Test automation for Zomato APIs for Zeta

Details:

src/main/java
com.zomato.base contains the enums for Status codes and Urls
com.zomato.utils contains RestUtility class which contains all the restAssured methods


src/test/java
com.zomato.api contains all the test classes
com.zomato.lib contains all the library classes (common methods which calls APIs)

src/test/resources
contains zomato configuration file with al the API urls

Before runing the testcases set the basedir to the proper location.

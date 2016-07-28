#CONTINUOUS INTEGRATION     
This project is for continuous integration tests.

#Maven multi module test project
Components:
* Maven
* Java web project
* Hibernate db
* Liquibase db
* MySQL
* Bamboo

Live demo:
* [Module 1](http://188.166.23.135:8080/module1/)
* [Module 2](http://188.166.23.135:8080/module2/)

This project is integrated with bamboo.

Access panel:
* [Bamboo](http://188.166.23.135:8085)

username: kartaca password: kartaca

#Documentation
###Getting started
First, create a maven Java Dynamic Web Project structure.
```
$ mvn archetype:generate -DgroupId={project-packaging}
  	-DartifactId={project-name}
  	-DarchetypeArtifactId=maven-archetype-webapp
  	-DinteractiveMode=false

// example
$ mvn archetype:generate -DgroupId=com.furkan
    -DartifactId=MavenJavaWebProjectTest
    -DarchetypeArtifactId=maven-archetype-webapp
    -DinteractiveMode=false
```
The created project structure should look like this:
```
MavenJavaWebProjectTest/
├── pom.xml
└── src
    └── main
        ├── java
        │   └── com
        │       └── furkan
        │           └── HelloKartaca.java
        ├── resources
        └── webapp
            ├── index.jsp
            └── WEB-INF
                └── web.xml

```
Create manually missing folders or files if doesn't exist.

Now look at the *pom.xml* and add all the needed dependencies. From this point you can continue to creating your project files (*.java* and/or *.jsp* etc. files).

###Usages
####Maven:
There are useful commands to manage your project with maven. For instance, to install packages you added to *pom.xml* run this command from command line:
```
$ mvn install
```
for compiling your codes
```
$ mvn compile
```
Note that your *.java* files must be under the **/src/main/java** otherwise maven won't compile your project.

for packaging
```
$ mvn package
```
For further information you can look at [here.](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)

####Hibernate:
Hibernate is an object-relational mapping (ORM) framework for Java. You can find dozens of examples with a quick google search.

####Liquibase:
Liquibase is a database tracking, managing and schema change tool. It provides create, update, drop, .. abilities with its XML file. In this XML file there are changesets. Liquibase performs action according to these changesets. We need a path for our changeset XML files. Create a folder named *liquibase* under our resources folder and put the changeset XML files here.

Sample database change log file(db.changelog-1.0.xml):
```xml
<?xml version="1.1" encoding="UTF-8" standalone="no"?>
<databaseChangeLog xmlns="http://www.liquibase.org/xml/ns/dbchangelog" xmlns:ext="http://www.liquibase.org/xml/ns/dbchangelog-ext" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.5.xsd http://www.liquibase.org/xml/ns/dbchangelog-ext http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-ext.xsd">
    <changeSet author="fkarakoyunlu" id="1">
        <createTable tableName="Kartaca">
            <column name="id" type="INT">
                <constraints nullable="false"/>
            </column>
            <column name="email" type="VARCHAR(255)"/>
            <column name="name" type="VARCHAR(255)"/>
            <column name="password" type="VARCHAR(255)"/>
            <column name="surname" type="VARCHAR(255)"/>
        </createTable>
    </changeSet>
</databaseChangeLog>
```

We can create automatic changelog file with the following command:
```
$ mvn liquibase:diff
```

If we want to add another table,
  1. Create another changelog xml file,
  2. Put it in the correct directory,
  3. Create master changelog file,
  4. In master file include other changelog files.

Sample master changelog file(master.xml):
```xml
<?xml version="1.1" encoding="UTF-8" standalone="no"?>
<databaseChangeLog xmlns="http://www.liquibase.org/xml/ns/dbchangelog" xmlns:ext="http://www.liquibase.org/xml/ns/dbchangelog-ext" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.5.xsd http://www.liquibase.org/xml/ns/dbchangelog-ext http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-ext.xsd">
  <include file="db.changelog-1.0.xml" relativeToChangelogFile="true"/>
  <include file="db.changelog-1.1.xml" relativeToChangelogFile="true"/>
</databaseChangeLog>
```

After making these changes run maven command to let liquibase perform action by this command:
```
$ mvn liquibase:update
```
#####Rollbacks:
Many database refactorings can automatically create rollback statements(*create table*, *rename column* and *add column*). But other cases (*drop table*, *insert data*) you have to create rollback command.

Simple rollback command example:
```xml
<changeSet author="fkarakoyunlu" id="1">
  <sql dbms="mysql"
          endDelimiter="\nGO"
          splitStatements="true"
          stripComments="true">insert into User (id, email, name, password, surname) values ('99', 'test@t.com', 'furkan', 'password', 'karakoyunlu')
          <comment>I created a test user with the id 99</comment>
  </sql>
  <rollback>
        delete from User where id='99'
  </rollback>
</changeSet>
```
To perform a rollback there are 9 options, some of them are:
  * rollback `<tag>`
  * rollback `<value>`
  * rollbackToDate `<date/time>`

For further information click [here.](http://www.liquibase.org/documentation/command_line.html)


####Bamboo:
To install bamboo server on your server, go to [here](https://www.atlassian.com/software/bamboo/download) and download the tar file. Extract the downloaded file to an install location.
```
$ mkdir bamboo
$ tar zxf atlassian-bamboo-5.12.3.1.tar.gz -C bamboo/
```
create another folder for bamboo home
```
$ mkdir bamboo-home
```
Then configure *bamboo-init.properties* (/atlassian-bamboo/WEB-INF/classes/bamboo-init.properties) for your bamboo-home folder and navigate to *bamboo/atlassian-bamboo-5.12.3.1/bin* and run **start-bamboo.sh**

(Note: Bamboo needs Java 1.8)

#####Creating a Bamboo project plan:
Create new project plan. Initially it comes with source code checkout task. Configure it to desired code repository.

Then create necessary maven tasks (such as *maven clean*, *maven install*) in the same stage.

After you completed previous tasks create another stage for deployment. Create a *Deploy Tomcat Application* task. Fill the necessary fields and save it.


##Bug fix
I faced with some problems while doing this project, here are the problems and the solutions.

#####MySQL timezone problem
There was a bug with the MySQL dependency version. To solve I downgraded the dependency version to 5.1.39 and problem solved.

#####Liquibase AbstractMethod Error
The reason is liquibase-hibernate4 dependency. It is not compatible with Hibernate ORM 5.0.1. I found a github repo that compatible with Hibernate5 and problem solved.

#####Bamboo installation problem
When I started *start-bamboo.sh* nothing happens. Make sure your server has more than 1.5GB of RAM. If you are using virtual server you can use swap partition to increase your virtual machine's RAM. [Here is a tutorial](https://www.digitalocean.com/community/tutorials/how-to-add-swap-on-ubuntu-14-04) for creating a swap file.

#####Maven couldn't find the .war files
Give your .war path to Tomcat Deploy Application from Bamboo in WAR File field. Example: `../<JOB TITLE>/<project-name>/target/<war-file>.war`

#####Tomcat 403 Error
Make sure your *tomcat-users.xml* looks like this:
```xml
<?xml version='1.0' encoding='utf-8'?>
<tomcat-users>
        <role rolename="manager-gui"/>
        <role rolename="admin-gui"/>
        <role rolename="manager-status"/>
        <role rolename="manager-script"/>
        <user username="admin" password="1234" roles="admin-gui,manager-gui,manager-status,manager-script"/>
</tomcat-users>
```

#####Maven doesn't compile my java files
Make sure your project folder structure is correct.

#####Unsupported major.minor version 52.0
Make sure your Java compile version is not higher than the Java run time version. Since bamboo needs Java 1.8 your tomcat must use the same Java version. Go to */etc/default* and open up the *tomcat7* file and in this file find JAVA_HOME, uncomment this line and give the correct java 1.8 path.

#####hibernate.cfg.xml not found
Make sure your hibernate.cfg.xml is placed under the resources folder.

##Screenshots
![Image of plan configuration](https://github.com/spootrick/Continuous-Integration-Test/blob/master/screenshots/1.png)
![Image of tasks](https://github.com/spootrick/Continuous-Integration-Test/blob/master/screenshots/2.png)
![Image of deploy tasks](https://github.com/spootrick/Continuous-Integration-Test/blob/master/screenshots/3.png)
![Image of maven clean configuration](https://github.com/spootrick/Continuous-Integration-Test/blob/master/screenshots/4.png)
![Image of tomcat deploy configuration](https://github.com/spootrick/Continuous-Integration-Test/blob/master/screenshots/5.png)


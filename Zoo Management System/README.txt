**README**

Name:keshav Bindlish
Roll No.: 2022246
Branch: CSD

Project: Zootopia
The project is based on making a program to manage the zoo system. the code comprises of classes Admin,Visitor,Owner,Animal,Amphibians,Reptile,Mammals, Discount,Attractions,Main.

HOME_FOLDER = Main(present in src folder)

Target has jar file.

**Assumptions:**

1. admin username: admin
   admin password : admin123

2.In the beginning no attraction has been added. attractions needed to be added from admin portal-->manage attraction-->add attraction

3.intially 6 animals are hardcoded(2 of each category)

4.all type of discounts are initially set to zero(special deals and other discounts)

5.In visitor (register 1st then only login possible)

6.Has to buy membership before visiting attraction

7.attractions are all closed in starting.

8.While adding animals type:"mammal","reptile","amphibian"

9.to set percentage of discount go to manage discount

** Concepts Used **

1. Polymorphism: A Animal class is made which is later inherited by Mammal,Amphibian,Reptile classes

2. Interface: 

Interface(VisitorInterface) which is implemented in Visitor class
Interface(StatisticsInterface,AttractionManagementInterface,AnimalManagementInterface) which are implemented in Admin class;
Interface(OwnersInterface) which is implemented in Owner class.

3.Object classes: made use of .equals() to compare strings in the code

**Run**

Navigate to the directory where the project is saved.

mvn clean 

mvn compile

mvn package

cd target

java -jar .\ap_assignment_2-1.0-SNAPSHOT.jar





 
# Butik - BILVA simple application for sharing and tracking business informations for my family's retail business. 

This is a simple web application, for tracking details for textiles on stock, employees, HR details about employees, invoices, ordering and selling textiles made with Java/Spring Boot and Bootstrap, created for the needs of my dear family who runs retail business.

# Where did the initial idea come from?

After completing my undergraduate studies at the Faculty of Organizational Sciences, I decided to step out of my comfort zone and feel the challenge of programming on real problems, and thus acquire a new problem-solving skill.Therefore, my new learning journey began with the Master's program in Software Engineering and Artificial Intelligence. Is there a better way to learn programming than to learn about new technologies. Of course we are talking about Spring Boot, JPA, Hibernate, Spring Security and Bootstrap.
At the beginning of the semester, everything seemed very difficult to me, so I decided that in addition to the regular lectures at the faculty, I would also take a look at a course that will use the mentioned technologies in a practical way. By combining the information obtained at the University and from the course, I used the acquired knowledge to create an application for my own needs in a similar way.
Through the mentioned application, I tried to demonstrate acquired knowledge, so far. 
Considering that this is my first experience with with these technologies (Spring Boot, Spring Security, JPA, Hibernate) and UI programming (Bootstrap), I am very proud of the current results.

I will try to bring you closer to my family's way of doing business, for whose needs the application was made.
In our store, members of our immediate family are employed, and on the side we also employ an accountant and a lawyer who take care of the paperwork. This application should help them take care of all the information more easily and keep everyone informed about the changes at any time.

# App features

The application is divided into two parts, part for users and part for system users (employees). 

The system user in this case employee can log in with his credentials, and when he passes the check, he has access to all pages ( he can add new employee, make new order and invoice for that, add new mtextile make, model, add new textile product, see details for every subject of interest...)
After registration, the regular user can see only basic information about the store, contact phone number, name of the owner, as well as pictures of the textile products available in the store, some details about make and model, price, and stock number.

The application was made according to the MVC pattern, the option for registration and logging in was implemented with Spring Security,and communication with the database was achieved through JPA and Hibernate.

# Problems I ran into

The problems I ran into were mostly related to my inexperience, but Baeldung articles and official documentation helped a lot.
In addition, the technical problems that appeared were mostly related to dependencies and some version mismatches.

# What next?
It would be nice to sort out the little things like dates, aesthetics, interactive search, interactive addition of photos, make an online ordering option and things like that (there are plenty of possibilities and ideas).

Pleading : Please keep in mind that this is my first real project, and specially it is implemented using technologies that I am learning for the first time along the way.
I would very much like to upgrade this application even more, but I will take a little break from working on this first version of the application, due to obligations for another subject.

## Prerequisites and Usage 

You can open the application with any environment (I used Intellij IDEA), load all the necessary dependencies using Maven, then in the package src/main/java/fon.bg.ac.rs.retailApp run the  main RetailAppApplication class. In order to connect to the database you need to start MSsql server (for this I have used Xammp and SQLyog)
The application will start on the predefined port 8080. After that you need to enter localhost:8080 in your Google Chrome browser and the login/registration page will be displayed.


## License

Copyright © 2022 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.

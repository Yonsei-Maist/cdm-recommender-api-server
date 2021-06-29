<!-- PROJECT LOGO -->
<br/>
<p align="center">
  <h1 align="center"> CDM RECOMMENDER API SERVER </h3>
  <h4 align="center">
    Api server supporting cdm recommender system.
  </h4>
</p>
<br/>

<!-- TABLE OF CONTENTS -->
## Table of Contents
- [Table of Contents](#table-of-contents)
- [About The Project](#about-the-project)
- [Getting Started](#getting-started)
- [Author](#author)
- [Maintainer](#maintainer)


<!-- ABOUT THE PROJECT -->
## About The Project

### Key Features
- Save and load the EMR document.
- Returns a list of words similar to the CDM.
- Returns a list of associations to EMR & CDM.
- Initialize the association relationship list.

### Related Project
- System project : https://github.com/Yonsei-Maist/cdm-recommender-system

<!-- GETTING STARTED -->
## Getting Started

### Development Environment
- Spring Boot, 2.1.7.RELEASE
- Gradle, 4.10.2
- MongoDB

### Installation
1. Clone the repository
```sh
git clone https://github.com/Yonsei-Maist/cdm-recommender-api-server.git
```
2. IDE Setup - lombok(Intllij)
```sh
1) In the menus bar, select File > Settings.
2) Select Plugins section, then click Browse repositories.
3) In the search box, write “Lombok”.
4) Select Lombok from the search results list, then click Install.
5) Restart IntelliJ.

To enable annotation processing for your project

1)In the menus bar, select File > Settings.
2) Select Build, Execution, Deployment >  Compiler > Annotations Processors section.
3) Check “Enable annotation processing”, then click OK.
```
3. Add application.yml - mongoDB
```sh
spring:
  data:
    mongodb:
      host: 
      port: 
      database: 
      username: 
      password:
```

<!-- AUTHOR -->
## Author
Mina Kim, Yonsei Univ. Researcher since 2020.08 ~ 2021.06

<!-- MAINTAINER -->
## Maintainer
Chanwoo Gwon, arknell@yonsei.ac.kr (2021.06. ~)

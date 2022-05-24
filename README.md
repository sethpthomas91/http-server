# HTTP-SERVER

<!-- ABOUT THE PROJECT -->
## About The Project

This is my 8th Light Apprenticeship HTTP Server.

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- BUILD STATUS -->
## Current Build Status

![Java Build Status](https://github.com/sethpthomas91/http-server/actions/workflows/gradle.yml/badge.svg)
[![All Server Specifications Tests](https://github.com/sethpthomas91/http-server/actions/workflows/combinedWorkflow.yml/badge.svg)](https://github.com/sethpthomas91/http-server/actions/workflows/combinedWorkflow.yml)
<p align="right">(<a href="#top">back to top</a>)</p>


<!-- Built With -->
### Built With

* [JAVA](https://www.java.com/en/)
* [Gradle](https://gradle.org/install/)
* [Node](https://nodejs.org/en/)
* [Ruby](https://www.ruby-lang.org/en/)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started


### Prerequisites

In order to run this project you will need to install Java, Gradle, and Node.
In order to run the server specification tests you will also need to install Ruby.
Follow instructions on the websites listed above in the "Built With" section to install these. 
This project relies on two programs for execution, and one additional program for testing.


### Installation

1 Clone the repo
   ```sh
git clone git@github.com:sethpthomas91/http-server.git
   ```

2 Set up the servers

From your root directory run:
   ```sh
gradle build   

npm install -g json-server
   ```

3 Run the json api server

From the json_server directory run:
```sh
json-server --watch db.json
   ```


4 Run the server
```sh
PUBLIC_DIR="$(pwd)/Public/" gradle run
   ```
The PUBLIC_DIR environment variable is needed to set up the local files to be server with the server.

<p align="right">(<a href="#top">back to top</a>)</p>

### Testing

1 After cloning the repo run the test suite with the following command
   ```sh
PUBLIC_DIR="$(pwd)/Public/" gradle test   
   ```

## Reflections

This project started on 11 April as an echo server and slowly progressed over time as a full http server.
When I started I had never written a line of Java or used IntelliJ as an IDE.
I had also no idea how to use curl commands or how low level sockets worked.

Before getting started I would have done a few things differently.
1. Spend a few hours looking at libraries that act as http servers. Your program should have an end user in mind. What will this interaction be like?
   Having the ability to import an echo server from another class, give it a port and fire it up would have been a good idea to have in mind.
2. Ask for more implementation details of the "client" (read mentorship team) on what other features they wanted out of the server.
   I ran into an issue of just trying to meet the test requirements and not implementing a date time stamp, proper headers, etc. Looking into the most up to date http protocol would have steered me in a better direction.
3. Always estimate the stories. The client will bring up more requirements mid-week. You have to account for these on your board and tell them the risks of adding new requirements.

Starting off with pure test driven development was difficult on the echo server and while working with sockets on the http server.
Seeing an example of how a mock helps test around something you do not own would have helped me start with this project tremendously.
Even just showing how the first few lines of code were driven with TDD through a pairing session would have been great.
If you are reading this as an apprentice, demand an hour with your mentorship team to actually show how TDD helps you write code around something you do not own.
This is a very important lesson and really must be talked through and shown.
It is easy to forget how foreign this concept of mocking and testing can be.

Learning gradle, java, intellij, and low level sockets was a huge ask at the beginning of this project.
I would have never been able to effectively get through it if it was not for my fellow apprentices that were using these programs before me.
There were a few tips and tricks that proved invaluable. Starting IntelliJ from the terminal, working around compatibility issues that were not really an issue, and how to write tests were things I picked up from my fellow apprentices.

While using the server specifications it is important to realize what the tests are really testing. 
Many times the test will say it is testing for json in the body, but it is really testing for a header that says "application/json." 
When running the tests look above the spinach red/green results and see what is actually failing. This will save you a few hours wondering why the json in your body is not being accepted by the specifications.

Finally, this project is tough, but completely doable. Whoever does this will run into many issues. 
Just remember to rely on your fellow apprentices and pester your mentor team for help throughout the process. 

Also, no matter when you are reading this, if you need help please feel free to reach out to me.

-Seth

<!-- ACKNOWLEDGMENTS -->
## Contributors

* [sethpthomas91](https://github.com/sethpthomas91)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/github_username/repo_name.svg?style=for-the-badge
[contributors-url]: https://github.com/github_username/repo_name/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/github_username/repo_name.svg?style=for-the-badge
[forks-url]: https://github.com/github_username/repo_name/network/members
[stars-shield]: https://img.shields.io/github/stars/github_username/repo_name.svg?style=for-the-badge
[stars-url]: https://github.com/github_username/repo_name/stargazers
[issues-shield]: https://img.shields.io/github/issues/github_username/repo_name.svg?style=for-the-badge
[issues-url]: https://github.com/github_username/repo_name/issues
[license-shield]: https://img.shields.io/github/license/github_username/repo_name.svg?style=for-the-badge
[license-url]: https://github.com/github_username/repo_name/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/linkedin_username
[product-screenshot]: images/screenshot.png
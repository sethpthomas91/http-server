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
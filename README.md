# Chatty
A basic command line chat system built using java.

## Development Environment
This is a suggestion, and other environments may work. This is what I am using while I work on this project:
- Java 8
- IntelliJ IDEA
- Apache Maven 3.6.3

See `docs/development_setup.md` for more information  on getting started.

## Directory Structure
- Client: The client portion of the application. This is the part that most users will be using.
- docs: General Documentation on pieces of this project
- PublicServerList: An application that can be run on a server somewhere and will maintain an active
  listing of all of the public server
- Server: The server application responsible for managing all the different messages being sent.
- Shared: Components that are shared by multiple other components of this system.

## Building the code
The code can be tested and compiled by running the following command in this top-level directory:<br>
`mvn clean install`

## Running the code 
#### The easy way
A `chatty.bat` script is provided with this repository. This can be used to run the application in the
following ways:
- Run Server: `chatty.bat server`
- Run Client: `chatty.bat client`

#### The slightly harder way
Maven outputs builds into a target directory for each module. For our system, these tend to be `.jar`
files. Running these `.jar` files will start the respective component.

Ex: `java -jar Server/target/Server-1.0-SNAPSHOT.jar`
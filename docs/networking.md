# Networking Overview
This application is built using socket programming. Each of the three main components has
a socket that is always open and listening for connections. Other applications can send messages
to that listening port. Additionally, the listeners are designed in such a way that they are
not blocking for the application as a whole. That means that these will run in the background
forever.

Most of the raw networking code can be found in `com.chatty.net`. The exception to this is
when initializing network from each of the main applications as well as a couple configuration
items which have been made dynamic (such as ports) within this library so that that configuration 
can be more specific to each application.

Additionally, the networking library has designed to be as simple to use as possible from a development
perspective. All needed interaction should be included in `com.chatty.net.NetworkHandler`

Note: Right now all the values you see below are hard coded. We may want to add some ability
to specify from command line or make them somewhat dynamic (especially on the side of the
client).

## Client
Listens on Port 45557

## Server
Listens on Port 45558

## Server List
Listens on Port 45559 
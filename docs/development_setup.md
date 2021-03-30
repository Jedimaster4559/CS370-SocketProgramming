# Development Setup

## Maven
Maven can sometimes be a little tricky to get setup if it is your first time installing it.

I suggest following these instructions since they are much better than the official instructions:
[https://www.tutorialspoint.com/maven/maven_environment_setup.htm](https://www.tutorialspoint.com/maven/maven_environment_setup.htm)

A note: I often skip step 5 when installing maven. It's not needed on all systems and I prefer
to use the defaults so I don't have to go looking later.

Once you have it installed, run `mvn --version` to ensure that installation and setup occurred successfully.

## IntelliJ
Sometimes IntelliJ requires that you mark certain portions of your code as `sources root` or  `test root` in order
to properly handle intellisense and do java things correctly. For this project, you should label all directories
named `java` as sources root and all directories named `test` as test root. This can be performed by right clicking
on the folder and then selecting `Mark Directory as -> Sources Root` or `Mark Directory as -> Test Root` (depending
on if it is a test or java folder) 
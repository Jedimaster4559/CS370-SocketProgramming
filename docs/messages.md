# Messages
These applications send messages back and forth. For the most part, these messages
are somewhat one-directional. This file contains each message as well as instructions
on how to expand the current messaging system.

## Adding messages
Several Components need to be updated to add new messages. This has been designed to be
as simple to expand as possible.
- Add a new `MessageType` for the message. (Found in `com.chatty.messages`)
- Add your class for the message in the `com.chatty.messages` package. This should extend off
  the abstract class `Message`
  - When you do this, make sure to set the `type` variable to your new class.
- Add a new case statement to `MessageDeserializer` using your new class you implemented.
  (Found in `com.chatty.messages`)
  
Something to consider: The serialization engine does not handle circular references well. If your
data types include any coupling, it's unlikely they are going to be able to be serialized into
messages.  

## Messages
An overview of each message type

#### ChatMessage
An actual chat message. This also includes a couple important pieces of information 
such as who sent the message, and some important information about them.

#### Heartbeat
A message traded between clients and servers occasionally. This is used to maintain
who is actually in a server or not.

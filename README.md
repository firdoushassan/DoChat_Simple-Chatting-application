# DoChat

DoChat is a simple Java-based chat application that enables real-time communication between a client and server. The project demonstrates basic networking concepts using `Socket` programming, and a graphical user interface built with Swing.

## Technologies Used

- **Java**: The core language used for the application logic, including networking and threading.
- **Swing**: A Java GUI toolkit for creating the graphical user interface components like chat windows, text areas, buttons, and panels.
- **Socket Programming**: Used for the communication between the client and server.
- **AWT**: Abstract Window Toolkit is used for managing window events and creating custom components in the user interface.
- **I/O Streams**: `DataInputStream` and `DataOutputStream` are used for sending and receiving messages between the client and server.

## Project Structure

### Client.java

- This class is responsible for the client-side functionality of the chat application.
- The GUI components are created using Java Swing, and the layout includes a header with profile images, video call icons, and an input area where users can type messages.
- Client actions, such as sending messages, are handled through button events and action listeners.
- The client connects to the server using a `Socket` and sends the user's message via an output stream.

### Server.java

- This class handles the server-side functionality.
- It listens for incoming connections using a `ServerSocket` and creates a separate thread to handle communication with each client.
- When a client connects, the server can receive messages from the client and broadcast them to other clients (if required).
- The server continuously listens for incoming messages and updates the chat window accordingly.

## Features

- **Real-time Messaging**: Clients can send and receive messages instantly via a network connection.
- **User Interface**: A simple and intuitive chat UI built using Swing, featuring a message history pane and input field.
- **Time Stamps**: Each message is accompanied by a timestamp showing when it was sent.
- **Connection Handling**: The server can handle multiple incoming client connections.

## How to Run

1. Clone the repository.
2. Compile the Java files:
   ```
   javac Client.java Server.java
   ```
3. Run the server:
   ```
   java Server
   ```
4. In a new terminal, run the client:
   ```
   java Client
   ```

Ensure both the server and client are running on the same network or adjust the IP address in the `Client.java` file to point to the server.


# 🌐 TCP Server and Client

Welcome to the **TCP Server and Client** application 🖥️ — a Java implementation of a simple TCP server-client architecture. This tool allows you to set up a server that listens for incoming connections and a client that sends messages to the server, which are then echoed back. 🚀

## 📝 Features

- 🖥️ **TCP Server**: Listens on a specified port for incoming connections and handles communication.
- 💬 **Echo Functionality**: The server echoes back any message sent by the client.
- 🗂️ **Configuration File**: The server creates a `config.txt` file to store the port number and the server's IP address.
- 🚀 **Client Communication**: The client retrieves configuration details from the `config.txt` file and sends messages to the server.

---

## 🚀 Getting Started

### 1️⃣ **Prerequisites**

Ensure you have the following installed:

- ☕ **Java JDK** (Java Development Kit) — Version 8 or above.

### 2️⃣ **Installation**

Clone the repository and navigate to the folder:

```bash
git clone https://github.com/sean-njela/tcp_server_client.git
cd tcp_server_client
```

### 3️⃣ **Usage**

#### Start the TCP Server

To start the server, run the following command with the desired port number (e.g., `5000`):

```bash
java TCPServer <port number>
```

#### Start the TCP Client

After starting the server, run the client:

```bash
java TCPClient
```

---

## 📂 Code Explanation

### TCP Server

#### Step-by-Step Breakdown

1. **Imports**:

   ```java
   import java.io.*; // For input/output operations.
   import java.net.*; // For networking operations.
   ```

2. **Class Declaration**:

   ```java
   public class TCPServer {
       public static int PORT;
   }
   ```

   - A public class named `TCPServer` is defined. A static integer variable `PORT` is declared to store the port number.

3. **Main Method**:

   ```java
   public static void main(String[] args) throws IOException {
   ```

   - The main method, which serves as the entry point for the server application, is defined.

4. **Port Argument Check**:

   ```java
   if (args.length != 1) {
       System.err.println("Usage: java TCPServer <port number>");
       System.exit(1);
   }
   ```

   - The program checks if exactly one argument (the port number) is provided. If not, it prints an error message and exits.

5. **Local IP Address Retrieval**:

   ```java
   InetAddress localAddress = InetAddress.getLocalHost();
   ```

   - The local IP address is retrieved and stored in `localAddress`.

6. **Port Number Parsing**:

   ```java
   PORT = Integer.parseInt(args[0]);
   ```

   - The port number is parsed from the command-line argument.

7. **Configuration File Creation**:

   ```java
   File myObj = new File("config.txt");
   if (myObj.createNewFile()) {
       ...
   }
   ```

   - A file named `config.txt` is created to store the server's configuration. If the file already exists, a message is printed.

8. **Writing to Configuration File**:

   ```java
   FileWriter writer = new FileWriter("config.txt");
   writer.write(content + "\n");
   writer.write(localAddress.getHostAddress() + "\n");
   ```

   - The port number and local IP address are written to `config.txt`.

9. **ServerSocket Creation**:

   ```java
   ServerSocket s = new ServerSocket(PORT);
   ```

   - A `ServerSocket` is created to listen for incoming connections on the specified port.

10. **Client Connection Handling**:

    ```java
    Socket mysocket = s.accept();
    ```

    - The server waits for a client to connect and creates a socket to handle the communication.

11. **Receiving and Echoing Messages**:

    ```java
    String str = in.readLine();
    out.println(str);
    ```

    - The server continuously reads messages from the client and echoes them back until the client sends "END".

12. **Closing Connections**:
    ```java
    mysocket.close();
    s.close();
    ```
    - Both the client socket and the server socket are closed to free up resources.

---

### TCP Client

#### Step-by-Step Breakdown

1. **Imports**:

   ```java
   import java.io.*; // For input/output operations.
   import java.net.*; // For networking operations.
   import java.util.Scanner; // For reading from files.
   ```

2. **Class Declaration**:

   ```java
   public class TCPClient {
       public static int PORT;
       public static String SERVER_IP;
   }
   ```

   - A public class named `TCPClient` is defined with static variables for the port and server IP address.

3. **Main Method**:

   ```java
   public static void main(String[] args) throws IOException {
   ```

   - The main method is defined as the entry point for the client application.

4. **Reading Configuration**:

   ```java
   File myObj = new File("config.txt");
   Scanner myReader = new Scanner(myObj);
   ```

   - The client reads the port number and server IP address from `config.txt`.

5. **Creating Socket Connection**:

   ```java
   Socket mysocket = new Socket(addr, PORT);
   ```

   - A socket is created to connect to the server using the retrieved IP address and port.

6. **Sending Messages to Server**:

   ```java
   for(int i = 0; i < 10; i++) {
       out.println("Hi: " + i);
       String str = in.readLine();
       System.out.println(str);
   }
   ```

   - The client sends a series of messages ("Hi: 0", "Hi: 1", ..., "Hi: 9") to the server and prints the echoed response.

7. **Ending Communication**:

   ```java
   out.println("END");
   ```

   - The client sends "END" to signal the server that communication has ended.

8. **Closing Connection**:
   ```java
   mysocket.close();
   ```
   - The client socket is closed to free up resources.

---

## ⚠️ Error Handling

- 🚫 **Invalid Usage**: If the server is started without a port number, the following message will be displayed:

  ```bash
  Usage: java TCPServer <port number>
  ```

- 🛑 **File Not Found**: If the `config.txt` file cannot be found, the client will print:

  ```bash
  An error occurred.
  ```

- ⚠️ **Socket Exceptions**: Any network-related issues will yield an appropriate error message.

---

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## ❤️ Contributions

Feel free to submit pull requests or raise issues. Contributions are always welcome!

---

## 👤 Author

**TNjela**

- GitHub: [@seannjela](https://github.com/sean-njela/tcp_server_client.git)
- Project Link: [@Project_link](https://roadmap.sh/projects/tcp-server-client)

---

## 🎉 Acknowledgements

Feel free to contribute with enhancements and additional features!

Happy Coding! 🎉

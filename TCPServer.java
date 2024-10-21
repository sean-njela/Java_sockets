import java.io.*; // Import the java.io package for input/output operations.
import java.net.*; // Import the java.net package for networking operations.

public class TCPServer { // Declare a public class named TCPServer.
	public static int PORT; // Define a integer variable named PORT and set it to 5000. This represents the port number the server will listen on.

	public static void main(String[] args) throws IOException { // Main method, entry point of the program.

		// Check if a port number is provided as an argument
        if (args.length != 1) {
            System.err.println("Usage: java TCPServer <port number>");
            System.exit(1);
        }

		// Get the local IP address
        InetAddress localAddress = InetAddress.getLocalHost();

		// Parse the port number from the command line arguments
		try {
			PORT = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.err.println("Error: Port number must be an integer.");
			System.exit(1);
		}

		// Print the working directory
		System.out.println("Working Directory: " + System.getProperty("user.dir"));

		String content = args[0];  // Get the first command line argument
		try {
		File myObj = new File("config.txt");

		// Create the file if it doesn't exist
		if (myObj.createNewFile()) {
			System.out.println("File created: " + myObj.getName());
		} else {
			System.out.println("config.txt already exists.");
		}

		try ( // Write content to the file
				FileWriter writer = new FileWriter("config.txt")) {
			writer.write(content + "\n");
			writer.write(localAddress.getHostAddress() + "\n");
			writer.close();
			System.out.println("Successfully wrote to config.txt.");
		}
		
		} catch (IOException e) {
			System.out.println("An error occurred.");
			//e.printStackTrace();
		}

		ServerSocket s = new ServerSocket(PORT); // Create a new ServerSocket object 's' that listens on the specified port.
		
		// System.out.println("Working: " + s); // Print a message to the console indicating the server is running and display the server socket information.


        // Display server information
        System.out.println("Server started on IP: " + localAddress.getHostAddress() + ", Port: " + PORT);


		try { // Start a try block to handle potential exceptions.
			Socket mysocket = s.accept(); // Wait for a client to connect. Once a client connects, create a new Socket object 'mysocket' to represent the connection.
			try { // Start an inner try block to handle exceptions specific to the client connection.
				System.out.println("Connection accepted: "+ mysocket); // Print a message indicating a client connection has been accepted and display the client socket information.

				// Create a BufferedReader object 'in' to read data from the client socket.
				BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));
				// Create a PrintWriter object 'out' to write data to the client socket. The 'true' argument enables auto-flushing.
				PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mysocket.getOutputStream())),true);

				while (true) { // Enter an infinite loop to continuously receive and process data from the client.
					String str = in.readLine(); // Read a line of text from the client using the BufferedReader 'in' and store it in the 'str' variable.
					if (str.equals("END")) break; // If the received string 'str' is equal to "END", break out of the infinite loop.
					System.out.println("Received: " + str); // Print the received string 'str' to the console.
					out.println(str); // Echo the received string 'str' back to the client using the PrintWriter 'out'.
				}			
			} finally { // This block will always execute after the inner try block, whether an exception occurs or not.
				System.out.println("Closing client connection..."); // Print a message indicating the server is closing the connection.
				mysocket.close(); // Close the client socket 'mysocket'.
			}
		} finally { // This block will always execute after the outer try block, whether an exception occurs or not.
			System.out.println("Closing server socket...");
			s.close(); // Close the server socket 's'.
		}
	}
}

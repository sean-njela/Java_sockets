import java.io.*; // Import the java.net package for networking operations.
import java.net.*; // Import the java.io package for input/output operations.
import java.util.Scanner; // Import the Scanner class to read text files

public class TCPClient { // Define a public class named TCPClient.

	public static int PORT;
	public static String SERVER_IP;
	
	public static void main(String[] args) throws IOException { // Main method, entry point of the program.

		//Read PORT from config.txt
		try {
      		File myObj = new File("config.txt");
			try (Scanner myReader = new Scanner(myObj)) {
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					SERVER_IP = myReader.nextLine().trim(); // Read IP, remove spaces
					PORT = Integer.parseInt(data);
					System.out.println("Server Port: " + PORT);
					System.out.println("Server IP: " + SERVER_IP);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			//e.printStackTrace();
		}

		InetAddress addr = InetAddress.getByName(null); // Get the local host address by passing 'null' to getByName() method.
		System.out.println("addr = " + addr); // Print the local host address to the console.
		Socket mysocket = new Socket(addr, PORT); // Create a new Socket object 'mysocket' to connect to the server at the specified address and port.
		try { // Start a try block to handle potential exceptions.
			System.out.println("Socket : " + mysocket); // Print the client socket information to the console.
			BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream())); // Create a BufferedReader object 'in' to read data from the server socket.
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mysocket.getOutputStream())),true); // Create a PrintWriter object 'out' to write data to the server socket. The 'true' argument enables auto-flushing.
			for(int i = 0; i < 10; i ++) { // Loop 10 times.
				out.println("Hi: " + i); // Send a message "Hi: " followed by the loop counter value to the server.
				String str = in.readLine(); // Read a line of text from the server using the BufferedReader 'in' and store it in the 'str' variable.
				System.out.println(str); // Print the received string 'str' to the console.
			}
			out.println("END"); // Send the string "END" to the server to signal the end of the communication.
		} finally { // This block will always execute after the try block, whether an exception occurs or not.
			System.out.println("Closing..."); // Print a message indicating the client is closing the connection.
			mysocket.close(); // Close the client socket 'mysocket'.
		}
	}
}

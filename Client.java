package CS;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static Socket client;
    private static ObjectInputStream input;
    private static ObjectOutputStream output;
    private static String messageFromServer;

    public static void ConnectToServer() throws UnknownHostException, IOException {
        System.out.println("\n正在尝试连接服务器...\n");
        client = new Socket("127.0.0.1", 8888);
        System.out.println("已连接至:" + client.getInetAddress().getHostName());
    }

    public static void GetStreams() throws IOException {
        output = new ObjectOutputStream(client.getOutputStream());
        output.flush();
        input = new ObjectInputStream(client.getInputStream());
        System.out.println("IO构造完成\n");
    }

    public static void CloseConnection() throws IOException {
        output.close();
        input.close();
        client.close();
    }

    public static void SendMessage(String message) throws IOException {
        output.writeObject(message);
        output.flush();
    }

    public static void ReceiveMessage() throws ClassNotFoundException, IOException {
        messageFromServer = (String) input.readObject();
        System.out.println("SERVER>>> " + messageFromServer);
    }

}


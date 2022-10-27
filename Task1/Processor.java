package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Processor of HTTP request.
 */
public class Processor {
    private final Socket socket;
    private final HttpRequest request;

    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }

    public void process() throws IOException {
        // Print request that we received.
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        // To send response back to the client.
        PrintWriter output = new PrintWriter(socket.getOutputStream());

        String s = request.getRequestLine();

        String page = s.substring(5, 12);

        String execute = s.substring(5, 12);

        if("newPage".equals(page)){
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>I understood it ....</p><h1>YOU HAVE ENTERED THE PAGE</h1></body>");
            output.println("</html>");
            output.flush();



            socket.close();
        }
        else if("execute".equals(execute)){

            int[] arr = new int[100000];
            int n = arr.length;

            for(int i = 0; i < n; i++){
                arr[i] = i;
            }

            for (int i = 0; i < n - 1; i++)
                for (int j = 0; j < n - i - 1; j++)
                    if (arr[j] > arr[j + 1]) {
                        // swap arr[j+1] and arr[j]
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }

            for(int i = 0; i < n; i++){
                System.out.print(arr[i] + " ");
            }
            System.out.println("DWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");


            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Computingwibefiweubfubwefiewbfib</p></body>");
            output.println("</html>");
            output.flush();


            socket.close();

        }
        else{
            // We are returning a simple web page now.
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Hello, world!</p></body>");
            output.println("</html>");
            output.flush();


            socket.close();
        }


    }
}

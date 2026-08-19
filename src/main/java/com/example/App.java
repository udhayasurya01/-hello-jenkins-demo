//package com.example;
//
//public class App {
//
//    public String getMessage() {
//        return "hello";
//    }
//
//    public static void main(String[] args) {
//        System.out.println("jenkins working");
//    }
//}

package com.example;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.OutputStream;

public class App {

    public String getMessage() {
        return "hello";
    }

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", exchange -> {
            String html = "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<title>Hello Jenkins Demo</title>"
                    + "<style>"
                    + "body { font-family: Arial, sans-serif; text-align: center; margin-top: 100px; background-color: #f0f4f8; }"
                    + "h1 { color: #2c3e50; }"
                    + "p { color: #555; font-size: 18px; }"
                    + ".box { background: white; display: inline-block; padding: 40px 60px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class='box'>"
                    + "<h1>Jenkins CI/CD Working!</h1>"
                    + "<p>Deployed via Jenkins -> Docker -> Kubernetes</p>"
                    + "<p>Server: office-server (122.165.70.116)</p>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, html.length());
            OutputStream os = exchange.getResponseBody();
            os.write(html.getBytes());
            os.close();
        });

        server.start();
        System.out.println("Server started on port 8080");
    }
}
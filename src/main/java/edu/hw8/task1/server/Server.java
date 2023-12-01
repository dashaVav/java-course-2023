package edu.hw8.task1.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Server {
    private Server() {
    }

    private static final int MAX_CONNECTIONS = 5;
    private static final int PORT = 18080;
    private final static Logger LOGGER = LogManager.getLogger();
    private static ExecutorService executorService;
    private static ServerSocket serverSocket;

    public static void start() {
        executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);
        try {
            serverSocket = new ServerSocket(PORT);
            LOGGER.info("Server is listening on port" + PORT);
            while (!serverSocket.isClosed()) {
                Socket clientSocket;
                try {
                    clientSocket = serverSocket.accept();
                } catch (SocketException e) {
                    return;
                }
                executorService.execute(new ClientHandler(clientSocket));
                LOGGER.info("New connection");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void stop() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            if (executorService != null && !executorService.isShutdown()) {
                executorService.shutdown();
            }
            LOGGER.info("Server stopped");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

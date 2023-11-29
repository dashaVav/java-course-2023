package edu.hw6.task6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Task6 {
    private Task6() {
    }

    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 49151;
    private static final Map<Integer, String> KNOWN_PORTS = Map.ofEntries(
        Map.entry(135, "EPMAP"),
        Map.entry(137, "Служба имен NetBIOS"),
        Map.entry(138, "Служба датаграмм NetBIOS"),
        Map.entry(139, "Служба сеансов NetBIOS"),
        Map.entry(445, "Microsoft-DS Active Directory"),
        Map.entry(1900, "Simple Service Discovery Protocol (SSDP)"),
        Map.entry(3702, "Динамическое обнаружение веб-служб"),
        Map.entry(5353, "Многоадресный DNS"),
        Map.entry(5355, "Link-Local Multicast Name Resolution (LLMNR)"),
        Map.entry(17500, "Dropbox"),
        Map.entry(27017, "MongoDB")
    );

    public static List<String> scanPorts() {
        List<String> str = new ArrayList<>();
        for (int port = MIN_PORT; port <= MAX_PORT; port++) {
            String result = scanOnePort(port);
            if (!result.isEmpty()) {
                str.add(scanOnePort(port));
            }
        }
        return str;
    }

    @SuppressWarnings("checkstyle:EmptyBlock")
    static private String scanOnePort(int port) {
        String type = "";

        try (ServerSocket serverSocket = new ServerSocket(port)) {
        } catch (Exception e) {
            type = "TCP";
        }

        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
        } catch (Exception e) {
            type = "UDP";
        }

        if (!type.isEmpty()) {
            return String.format("%-8s %-6d %s", type, port, KNOWN_PORTS.getOrDefault(port, ""));
        }
        return "";
    }
}

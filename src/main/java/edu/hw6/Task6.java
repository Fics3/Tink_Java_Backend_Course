package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;

@Log4j2
final class Task6 {

    private static final String TCP_NAME = "TCP";
    private static final String UDP_NAME = "UDP";
    private static final int[] PORTS_FOR_SCAN = {130, 138, 258, 1024, 38932, 5353};
    private static final Map<Integer, String> CLOSED_PORTS = new HashMap<>();

    private Task6() {

    }

    public static void scanPorts() {
        for (int j : PORTS_FOR_SCAN) {
            checkPorts(j);
        }
        printPorts();
    }

    private static void checkPorts(int port) {
        boolean isTCPOpen = isPortOpenTCP(port);
        boolean isUDPOpen = isPortOpenUDP(port);

        if (!isTCPOpen && isUDPOpen) {
            CLOSED_PORTS.put(port, TCP_NAME);
        } else if (!isTCPOpen) {
            CLOSED_PORTS.put(port, "TCP/UDP");
        } else if (!isUDPOpen) {
            CLOSED_PORTS.put(port, UDP_NAME);
        }
    }

    private static boolean isPortOpenTCP(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return true;
        } catch (IOException e) {
            CLOSED_PORTS.put(port, TCP_NAME);
            return false;
        }
    }

    private static boolean isPortOpenUDP(int port) {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return true;
        } catch (IOException ignored) {
            CLOSED_PORTS.put(port, UDP_NAME);
            return false;
        }
    }

    private static void printPorts() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nProtocol Port Service\n");
        for (int port : PORTS_FOR_SCAN) {
            if (CLOSED_PORTS.containsKey(port)) {
                String serviceName = getService(port);
                if (!serviceName.isEmpty()) {
                    stringBuilder.append(String.format("%-9s%-6d%-15s\n", CLOSED_PORTS.get(port), port, serviceName));
                } else {
                    stringBuilder.append(String.format("%-9s%-6d\n", CLOSED_PORTS.get(port), port));
                }
            }
        }
        log.info(stringBuilder.toString());
    }

    @SuppressWarnings("MagicNumber")
    private static String getService(int port) {
        return switch (port) {
            case 130 -> "CISCO-FNA (Cisco FNATIVE)";
            case 138 -> "NETBIOS-DGM (NetBIOS Datagram Service)";
            case 258 -> "FW1-MC (Checkpoint FW-1 management console)";
            case 5353 -> "Multicast DNS (MDNS)";
            default -> "";
        };
    }

}

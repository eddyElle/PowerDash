package rightpanel.quickremote;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Enumeration;

import settingspanel.quickremotemanager.Machine;
import share.StdButtons;
import share.StdLabels;
import share.StdThings;

public class realMacPanel extends JPanel {
    private JLabel nameLab;
    private JButton start = new StdButtons("power");
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    public realMacPanel(Machine thisMachine){
        this.setLayout(new GridLayout(0,1));
        this.nameLab = new StdLabels(thisMachine.getName());
        this.add(nameLab);
        this.add(start);
        nameLab.setHorizontalAlignment(JLabel.CENTER);
        nameLab.setVerticalAlignment(JLabel.CENTER);
        this.setBackground(StdThings.getGray1());
        start.addActionListener(e -> {
            fstart(thisMachine, findBroadcastIp());
        });
    }
    private void fstart(Machine thisMachine, String broadcastIp){
        try {
            if(thisMachine.getConnType() == false){
                int port = 9;
                String[] hex = thisMachine.getMacAddress().split("[:\\-]");
                byte[] macBytes = new byte[6];
                for (int i = 0; i < 6; i++) {
                    macBytes[i] = (byte) Integer.parseInt(hex[i], 16);
                }
                byte[] magicPacket = new byte[102];
                for (int i = 0; i < 6; i++) {
                    magicPacket[i] = (byte) 0xFF;
                }
                for (int i = 6; i < magicPacket.length; i += 6) {
                    System.arraycopy(macBytes, 0, magicPacket, i, 6);
                }
                DatagramPacket packet = new DatagramPacket(
                    magicPacket,
                    magicPacket.length,
                    InetAddress.getByName(broadcastIp),
                    port
                );
                try (DatagramSocket socket = new DatagramSocket()) {
                    socket.setBroadcast(true);
                    socket.send(packet);
                }
                execute(thisMachine.getExecMethod());
            }  
            else{
                sendWakeRequest(thisMachine.getRelayIP(), thisMachine.getRelayPort(), thisMachine.getMacAddress());
                execute(thisMachine.getExecMethod());
                
            }     
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "someting went wrong");
        }
    }

    public void execute(String execMethod){
        if(!execMethod.equals("execMethod")){
            int delayTime = 0;
            if(execMethod.contains(":")){
                String[] execVerifier = execMethod.split(":");
                try {
                    delayTime = Integer.parseInt(execVerifier[2]);
                } catch (Exception e) {
                    delayTime = 0;
                }
            }
        }
    }

    public static String findBroadcastIp() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                if (!ni.isUp() || ni.isLoopback() || ni.isPointToPoint()) {
                    continue;
                }

                for (InterfaceAddress ia : ni.getInterfaceAddresses()) {
                    InetAddress broadcast = ia.getBroadcast();
                    if (broadcast instanceof Inet4Address) {
                        return broadcast.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            return "255.255.255.255";
        }
        return "255.255.255.255";
    }

    public static String sendWakeRequest(String host, int port, String macAddress)
            throws IOException, InterruptedException {

        String url = "http://" + host + ":" + port + "/wake";
        String body = "{\"macAddress\":\"" + macAddress + "\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}

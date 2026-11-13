package settingspanel.quickremotemanager;
import java.io.*;
public class Machine implements Serializable {
    private String name;
    private String macAddress;
    private Boolean connType;
    private String execMethod;
    private String relayIP;
    private int relayPort;
    Machine(String name, String macAddress, Boolean connType){
        this.name = name;
        this.macAddress = macAddress;
        this.connType = connType;
    }
    Machine(String name, String macAddress, Boolean connType, String execMethod){
        this.name = name;
        this.macAddress = macAddress;
        this.connType = connType;
        this.execMethod = execMethod;
    }
    Machine(String name, String macAddress, Boolean connType, String relayIP, int relayPort){
        this.name = name;
        this.macAddress = macAddress;
        this.connType = connType;
        this.relayIP = relayIP;
        this.relayPort = relayPort;
    }
    Machine(String name, String macAddress, Boolean connType, String execMethod,String relayIP, int relayPort){
        this.name = name;
        this.macAddress = macAddress;
        this.connType = connType;
        this.relayIP = relayIP;
        this.relayPort = relayPort;
        this.execMethod = execMethod;
    } 
    @Override
    public String toString() {
        return name + ' ' + macAddress + ' ' + connType + ' ' + relayIP + ' ' + relayPort + ' ' + execMethod;
    }
    public Boolean getConnType() {
        return connType;
    }
    public String getExecMethod() {
        return execMethod;
    }
    public String getMacAddress() {
        return macAddress;
    }
    public String getName() {
        return name;
    }
    public String getRelayIP() {
        return relayIP;
    }
    public int getRelayPort() {
        return relayPort;
    }
    public void setConnType(Boolean connType) {
        this.connType = connType;
    }
    public void setExecMethod(String execMethod) {
        this.execMethod = execMethod;
    }
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRelayIP(String relayIP) {
        this.relayIP = relayIP;
    }
    public void setRelayPort(int relayPort) {
        this.relayPort = relayPort;
    }
}

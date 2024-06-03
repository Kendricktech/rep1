import java.io.IOException;

public class Ip_Configurator {
    
    public static void setStaticIp(String ipAddress, String subnetMask, String gateway) {

        String[] command = new String[]{"netsh", "interface", "ip", "set", "address", "name=\"Local Area Connection\"", "static", ipAddress, subnetMask, gateway};
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.setCommand(command);
        commandExecutor.executeCommand();
    }


    public static void setDynamicIp() {

            String[] command = new String[]{"netsh", "interface", "ip", "set", "address", "name=\"Local Area Connection\"", "dhcp"};
            CommandExecutor commandExecutor = new CommandExecutor();
            commandExecutor.setCommand(command);
            commandExecutor.executeCommand();

    }
}

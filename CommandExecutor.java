import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
public class CommandExecutor {
    private StringBuilder outputString;
    private ArrayList<String> command;
    private ProcessBuilder processBuilder;
    private Process process;
    public CommandExecutor() {
        command = new ArrayList<>();
    }

    public void setCommand(String... commands) {
        command.clear();
        for (String cmd : commands) {
            command.add(cmd);
        }
    }
    public void executeCommand() {
        outputString = new StringBuilder();
        processBuilder = new ProcessBuilder(command);
        try {
            process = processBuilder.start();
            BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;

            while ((line = output.readLine()) != null) {
                outputString.append(line).append("\n");
            }
            while ((line = error.readLine()) != null) {
                outputString.append(line).append("\n");
            }
            process.waitFor();
        } catch (IOException e) {
            outputString.append("IOException from executeCommand method: ").append(e.getMessage()).append("\n");
        } catch (InterruptedException e) {
            outputString.append("InterruptedException from executeCommand method: ").append(e.getMessage()).append("\n");
        }
    }
    public String getOutput(){
        return outputString.toString();
    }
}

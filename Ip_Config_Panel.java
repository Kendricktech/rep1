import javax.swing.*;
import java.awt.*;
public class Ip_Config_Panel extends JPanel {
    private JComboBox<String> options;
    private JTextArea output;
    private JButton executeButton;
    private JProgressBar progressBar;
    public Ip_Config_Panel() {
        initialize();
    }
    private void initialize() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Label
        JLabel label = new JLabel("Select an option and see the output:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalStrut(10));
        this.add(label);

        // ComboBox with options
        String[] configOptions = {"ipconfig", "ipconfig /all", "ipconfig /renew Local Area Connection", "ipconfig /flushdns"};
        options = new JComboBox<>(configOptions);
        options.setMaximumSize(new Dimension(Integer.MAX_VALUE, options.getPreferredSize().height));
        this.add(Box.createVerticalStrut(10));
        this.add(options);
        // Button to execute the selected command
        executeButton = new JButton("Execute");
        executeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalStrut(10));
        this.add(executeButton);

        // Text area to display output
        output = new JTextArea(10, 40);
        output.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(output);
        this.add(Box.createVerticalStrut(10));
        this.add(scrollPane);
        // Progress bar to show ongoing progress
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setVisible(false);
        this.add(Box.createVerticalStrut(10));
        this.add(progressBar);
        // Add action listener to the execute button
        executeButton.addActionListener(e ->executeCommand());
    }
    private void executeCommand() {
        String selectedCommand = (String) options.getSelectedItem();
        String[] command;

        if (selectedCommand.equals("ipconfig /renew Local Area Connection")) {
            command = new String[]{"ipconfig", "/renew", "Local Area Connection"};
        } else {
            command = selectedCommand.split(" ");
        }
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.setCommand(command);
        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected void done() {
                progressBar.setVisible(false);
                executeButton.setEnabled(true);
                options.setEnabled(true);

                try {
                    output.setText(get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            protected String doInBackground() throws Exception {
                progressBar.setVisible(true);
                executeButton.setEnabled(false);
                options.setEnabled(false);
                commandExecutor.executeCommand();
                return commandExecutor.getOutput();
            }
        };
        worker.execute();
    }
}

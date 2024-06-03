import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ping_Panel extends JPanel {
    private ArrayList<String> addressList;
    private JTextField input;
    private JComboBox<String> preInput;
    private JTextArea output;
    private JButton pingButton;
    private JScrollPane scrollPane;
    private JLabel label;
    private JProgressBar progressBar;

    public Ping_Panel() {
        initialize();
    }

    private void initialize() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        label = new JLabel("Input address below or choose from a predefined list of addresses:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalStrut(10));
        this.add(label);

        input = new JTextField(20);
        input.setMaximumSize(new Dimension(Integer.MAX_VALUE, input.getPreferredSize().height));
        this.add(Box.createVerticalStrut(10));
        this.add(input);

        addressList = new ArrayList<>();
        addressList.add("192.168.0.1");
        addressList.add("google.com");
        addressList.add("yahoo.com");
        addressList.add("javatpoint.com");
        addressList.add("127.0.0.1");

        preInput = new JComboBox<>(addressList.toArray(new String[0]));
        preInput.setMaximumSize(new Dimension(Integer.MAX_VALUE, preInput.getPreferredSize().height));
        this.add(Box.createVerticalStrut(10));
        this.add(preInput);

        pingButton = new JButton("Ping");
        pingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalStrut(10));
        this.add(pingButton);

        output = new JTextArea(10, 40);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);
        this.add(Box.createVerticalStrut(10));
        this.add(scrollPane);

        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setVisible(false);
        this.add(Box.createVerticalStrut(10));
        this.add(progressBar);

        // Add action listener to the ping button
        pingButton.addActionListener(e -> {
            executePingCommand();
        });
    }

    private void executePingCommand() {
        String address = input.getText().isEmpty() ? (String) preInput.getSelectedItem() : input.getText();

        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.setCommand("ping", address);
        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected void done() {
                progressBar.setVisible(false);
                pingButton.setEnabled(true);
                input.setEnabled(true);
                preInput.setEnabled(true);
                try {
                    output.setText(get());
                } catch (Exception e) {
                    e.printStackTrace();}}
            @Override
            protected String doInBackground() throws Exception {
                progressBar.setVisible(true);
                pingButton.setEnabled(false);
                input.setEnabled(false);
                preInput.setEnabled(false);
                commandExecutor.executeCommand();
                return commandExecutor.getOutput();
            }
        };
        worker.execute();
    }}

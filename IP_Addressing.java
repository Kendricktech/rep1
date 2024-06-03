import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class IP_Addressing extends JPanel {
    private JComboBox<String> options;
    private JTextArea output;
    private JButton executeButton;
    private JProgressBar progressBar;
    private JTextField ipAddressField;
    private JTextField subnetMaskField;
    private JTextField gatewayField;
    public IP_Addressing() {
        initialize();
    }
    private void initialize() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Initialize ComboBox and add items
        options = new JComboBox<>(new String[]{"Set Static IP", "Set Dynamic IP", "Other Option"});
        options.setMaximumSize(new Dimension(Integer.MAX_VALUE, options.getPreferredSize().height));
        this.add(options);
        // Initialize JTextArea
        output = new JTextArea(10, 30);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        output.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(output);
        this.add(scrollPane);
        // Initialize JButton
        executeButton = new JButton("Execute");
        this.add(executeButton);
        // Initialize progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        this.add(progressBar);
        // Initialize text fields for static IP configuration
        JLabel ipLabel = new JLabel("IP Address:");
        this.add(ipLabel);
        ipAddressField = new JTextField(20);
        ipAddressField.setMaximumSize(new Dimension(Integer.MAX_VALUE, ipAddressField.getPreferredSize().height));
        ipAddressField.setVisible(false);
        this.add(ipAddressField);

        JLabel subnetLabel = new JLabel("Subnet Mask:");
        this.add(subnetLabel);
        subnetMaskField = new JTextField(20);
        subnetMaskField.setMaximumSize(new Dimension(Integer.MAX_VALUE, subnetMaskField.getPreferredSize().height));
        subnetMaskField.setVisible(false);
        this.add(subnetMaskField);

        JLabel gatewayLabel = new JLabel("Gateway:");
        this.add(gatewayLabel);
        gatewayField = new JTextField(20);
        gatewayField.setMaximumSize(new Dimension(Integer.MAX_VALUE, gatewayField.getPreferredSize().height));
        gatewayField.setVisible(false);
        this.add(gatewayField);
        // Add action listener to the execute button
        executeButton.addActionListener(e -> executeCommand());
        // Add action listener to the options combo box
        options.addActionListener(e -> toggleIpFields());
    }
    private void toggleIpFields() {
        String selectedOption = (String) options.getSelectedItem();
        boolean isStaticIp = "Set Static IP".equals(selectedOption);
        boolean isDynamicIp = "Set Dynamic IP".equals(selectedOption);

        ipAddressField.setVisible(isStaticIp);
        subnetMaskField.setVisible(isStaticIp);
        gatewayField.setVisible(isStaticIp);

        ipAddressField.setEnabled(isStaticIp);
        subnetMaskField.setEnabled(isStaticIp);
        gatewayField.setEnabled(isStaticIp);

       this.revalidate();
       this.repaint();
    }
    private void executeCommand() {
        String selectedCommand = (String) options.getSelectedItem();
        if (selectedCommand.equals("Set Static IP")) {

            Ip_Configurator.setStaticIp(ipAddressField.getText(), subnetMaskField.getText(), gatewayField.getText());


        return;
    }
        else if (selectedCommand.equals("Set Dynamic IP")) {
                          Ip_Configurator.setDynamicIp();


            return;
}}}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainFrame {
    private JPanel currentPanel;
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem pingMenuItem;
    private JMenuItem ipconfigItem;
    private  JMenuItem IPconfigure;
    private  JMenuItem mac;
    public MainFrame() {
        initialize();
    }
    private void initialize() {
        frame = new JFrame("Network Utilities");

        menuBar = new JMenuBar();
        menu = new JMenu("Tools");

        pingMenuItem = new JMenuItem("Ping");
        ipconfigItem = new JMenuItem("IP Config");
        IPconfigure= new JMenuItem("Configure device ip");
        mac=new JMenuItem("Get MAc");


        menu.add(pingMenuItem);
        menu.add(ipconfigItem);
        menu.add(IPconfigure);
        menu.add(mac);

        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        currentPanel = new Ping_Panel();
        frame.add(currentPanel, BorderLayout.CENTER);

        frame.setSize(700, 700);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        // Add action listener to the Ping menu item
        IPconfigure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel(new IP_Addressing());
            }
        });
        pingMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel(new Ping_Panel());
            }
        });

        mac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                switchPanel(new getMac());
            }
        });

        // Add action listener to the IP Config menu item
        ipconfigItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel(new Ip_Config_Panel());
            }
        });
    }
    private void switchPanel(JPanel newPanel) {
        frame.remove(currentPanel);
        currentPanel = newPanel;
        frame.add(currentPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}

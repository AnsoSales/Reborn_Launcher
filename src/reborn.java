
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import java.io.IOException;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.DoubleBuffer;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

import javax.swing.JTextPane;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;



public class reborn extends JFrame implements ActionListener {

    private static final String testURL = "https://www.google.com.br";
    private JRadioButton EURadioButton;
    private JPanel backgroundPanel;
    private JRadioButton BRRadioButton ;;
    private JLabel updateStatusLabel = new JLabel("Checking for updates");
    private JButton btnNewButton ;
    private ButtonGroup Gbutton = new ButtonGroup();
    private JProgressBar progressBar = new JProgressBar(0, 100);

    // public JSONArray images;

    private JFrame frmRebornFightersLauncher;
    static int i = 0;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    reborn window = new reborn();
                    // window.frmRebornFightersLauncher.setTitle("Reborn Launcher");
                
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Runnable t1 = new Runnable() {
        public void run() {
            try {
                if (!netIsAvailable()) {

                    JFrame windowError = new JFrame();
                    JTextPane textError = new JTextPane();
                    windowError.setTitle("ERROR");
                    windowError.setBounds(100, 100, 100, 100);
                    textError.setText("No internet connection! Please solve this issue and try again!");
                    windowError.add(textError);
                    windowError.setVisible(true);
                } else {
                    btnNewButton.setEnabled(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };

    private static boolean netIsAvailable() {
        try {
            final URL url = new URL(testURL);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // conn.setRequestMethod("GET");
            conn.connect();
            // Thread.sleep(5000);
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {

            return false;
        }
    }

    public reborn() throws IOException, InterruptedException {
        initialize();
    }

    private void initialize() throws IOException, InterruptedException {

        
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        // int width = 1920;
        int height = gd.getDisplayMode().getHeight();
        // int height = 1080;
        System.out.println(width);
        System.out.println(height);
        this.frmRebornFightersLauncher = new JFrame("Reborn Launcher");
        this.frmRebornFightersLauncher.setResizable(false);

        this.frmRebornFightersLauncher.setDefaultCloseOperation(3);
        try {
            this.frmRebornFightersLauncher.setIconImage(ImageIO
                    .read(reborn.class.getClassLoader().getResourceAsStream("resources/reborn icon.png")));

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        this.frmRebornFightersLauncher.setSize(580, 480);
        this.frmRebornFightersLauncher.setLocation(width / 2 - this.frmRebornFightersLauncher.getWidth() / 2,
                height / 2 - this.frmRebornFightersLauncher.getHeight() / 2);

        this.backgroundPanel = new JPanel(null, true);
        
        


        int stylefont = 0, fontsize = 12;
        String fonte = "Baskerville Old Face";

        String tempClassVersion = "3.00.00";
        String tempResourceVersion = "3.00.00";

        String classVersion = tempClassVersion;
        String resourceVersion = tempResourceVersion;

        ImageIcon icone = new ImageIcon(reborn.class.getResource("/resources/reborn icon.png"));
    

        JLabel classLabel = new JLabel("CLASS VERSION: " + classVersion);
        classLabel.setForeground(Color.decode("#808080"));
        classLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        classLabel.setHorizontalAlignment(4);
        
        classLabel.setBounds(360, 4, 160, 12);
        classLabel.setFont(new Font(fonte, stylefont, fontsize));
        classLabel.setVisible(true);
        this.backgroundPanel.add(classLabel);

        JLabel resourceLabel = new JLabel("RESOURCE VERSION: " + resourceVersion);
        resourceLabel.setForeground(Color.decode("#808080"));
        resourceLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        resourceLabel.setBounds(360, 16, 160, 14);
        resourceLabel.setFont(new Font(fonte, stylefont, fontsize));
        resourceLabel.setVisible(true);
        this.backgroundPanel.add(resourceLabel);

        this.btnNewButton = new JButton("Start Game");
        this.btnNewButton.setSize(270, 46);
        this.btnNewButton.setLocation(this.frmRebornFightersLauncher.getWidth() / 2 - this.btnNewButton.getWidth() / 2,
                this.frmRebornFightersLauncher.getHeight() - 105 - this.btnNewButton.getHeight() / 2);
        this.btnNewButton.setEnabled(false);
        this.btnNewButton.setForeground(Color.decode("#808080"));
        this.btnNewButton.setBackground(Color.decode("#f0f0f0"));
        this.btnNewButton.setOpaque(true);
        this.btnNewButton.setContentAreaFilled(true);
        this.btnNewButton.setIcon(icone);
        this.btnNewButton.addActionListener(this);
        this.btnNewButton.setVisible(true);

        this.backgroundPanel.add(this.btnNewButton);
        progressBar.setBounds(44, 9, 315, 20);
        progressBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        progressBar.setStringPainted(true);
        progressBar.setIndeterminate(true);
        progressBar.setOpaque(false);

        progressBar.setBorderPainted(false);
        progressBar.setString(this.updateStatusLabel.getText());
        progressBar.setFont(new Font(fonte, 30, 14));
        progressBar.setForeground(new Color(128, 0, 0));
        progressBar.setBackground(Color.decode("#107ed6"));

        this.backgroundPanel.add(progressBar);

        ImageIcon radioIconUnselected = new ImageIcon(reborn.class.getResource("/resources/checkbox_off0.png"));

        ImageIcon radioIconSelected = new ImageIcon(reborn.class.getResource("/resources/checkbox_on0.png"));

        ImageIcon radioIconRollover = new ImageIcon(reborn.class.getResource("/resources/checkbox_off1.png"));

        ImageIcon radioIconRolloverSelected = new ImageIcon(reborn.class.getResource("/resources/checkbox_on1.png"));
        this.BRRadioButton = new JRadioButton("BR Server");
        this.BRRadioButton.setBounds(59, 350, 100, 30);
        Gbutton.add(BRRadioButton);
       
        this.BRRadioButton.setFocusPainted(false);
        this.BRRadioButton.setHorizontalTextPosition(SwingConstants.LEFT);
        this.BRRadioButton.setSelected(true);
        this.BRRadioButton.setForeground(Color.BLACK);
        this.BRRadioButton.setOpaque(false);
        this.BRRadioButton.setContentAreaFilled(true);

        this.BRRadioButton.setBackground(Color.GRAY);

        this.BRRadioButton.setVerticalAlignment(0);
        this.BRRadioButton.setHorizontalAlignment(0);

        this.BRRadioButton.setIcon(radioIconUnselected);
        this.BRRadioButton.setSelectedIcon(radioIconSelected);
        this.BRRadioButton.setRolloverIcon(radioIconRollover);
        this.BRRadioButton.setRolloverSelectedIcon(radioIconRolloverSelected);
        this.BRRadioButton.setVisible(true);
        this.backgroundPanel.add(this.BRRadioButton);

        this.EURadioButton = new JRadioButton("EU Server");
        Gbutton.add(EURadioButton);
        this.EURadioButton.setBounds(60, 370, 100, 30);
        this.EURadioButton.setFocusPainted(false);
        this.EURadioButton.setHorizontalTextPosition(SwingConstants.LEFT);
        this.EURadioButton.setVerticalAlignment(0);
        this.EURadioButton.setHorizontalAlignment(0);

        this.EURadioButton.setForeground(Color.BLACK);
        this.EURadioButton.setOpaque(false);
        this.EURadioButton.setContentAreaFilled(true);

        this.EURadioButton.setIcon(radioIconUnselected);
        this.EURadioButton.setSelectedIcon(radioIconSelected);
        this.EURadioButton.setRolloverIcon(radioIconRollover);
        this.EURadioButton.setRolloverSelectedIcon(radioIconRolloverSelected);

        this.backgroundPanel.add(this.EURadioButton);

        if (netIsAvailable()) {
            ImageIcon icon = new ImageIcon(reborn.class.getResource("/resources/lobo estrategista.png"));
            icon = new ImageIcon(icon.getImage().getScaledInstance(this.frmRebornFightersLauncher.getWidth() - 30, 310, 1));
            JLabel label = new JLabel();
            label.setIcon(icon);

            label.setBounds(6, 36, this.frmRebornFightersLauncher.getWidth() - 30, 310);
            label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            label.setVisible(true);
            this.backgroundPanel.add(label);
            // updateStatusLabel.setText("Game already updated.");
            // progressBar.setValue(100);
            // progressBar.setString(updateStatusLabel.getText());
            updateStatusLabel.setForeground(Color.WHITE);
            btnNewButton.setEnabled(true);
            
            // this.backgroundPanel.add(label);
        } else {
            updateStatusLabel.setText("Failed to initialize the launcher.");
            progressBar.setValue(100);
            progressBar.setString(updateStatusLabel.getText());
            updateStatusLabel.setForeground(Color.WHITE);
            btnNewButton.setEnabled(false);

        }

        ImageIcon background = new ImageIcon(reborn.class.getResource("/resources/background.png"));

        JLabel titleLabel = new JLabel(background);
        titleLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
        titleLabel.setVisible(true);
        this.backgroundPanel.add(titleLabel);

        this.frmRebornFightersLauncher.getContentPane().add(this.backgroundPanel);
        this.frmRebornFightersLauncher.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        if (this.EURadioButton.isSelected()) {

            Path brsetting = Paths.get("./conf/setting-br.kar");
            Path eusetting = Paths.get("./conf/setting-eu.kar");
            Path setting = Paths.get("./conf/setting.kar");

            try {
                if (Files.exists(setting)) {
                    if (Files.exists(brsetting)) { // eu server was the last played
                        // Files.move(source, target, options);
                        // brsetting.renameTo(setting);
                        Process process = (new ProcessBuilder(new String[] { "amped.exe" })).start();
                        // temprename.delete();

                        // setting2.delete();
                        // brsetting.delete();
                        System.exit(0);

                    } else if (Files.exists(eusetting)) { // br server was the last played
                        Files.move(setting, brsetting, StandardCopyOption.REPLACE_EXISTING);
                        Files.move(eusetting, setting, StandardCopyOption.REPLACE_EXISTING);
                        Process process = (new ProcessBuilder(new String[] { "amped.exe" })).start();
                        System.exit(0);
                    }
                } else if (Files.exists(brsetting) && Files.exists(brsetting)) { // probably the first time user use the
                                                                                 // launcher
                    Files.move(eusetting.toFile().toPath(), setting.toFile().toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                    Process process = (new ProcessBuilder(new String[] { "amped.exe" })).start();
                    System.exit(0);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (this.BRRadioButton.isSelected()) {

            Path brsetting = Paths.get("./conf/setting-br.kar");
            Path eusetting = Paths.get("./conf/setting-eu.kar");
            Path setting = Paths.get("./conf/setting.kar");

            try {

                if (Files.exists(setting)) {
                    if (Files.exists(brsetting)) { // eu server was the last played

                        Files.move(setting, eusetting, StandardCopyOption.REPLACE_EXISTING);
                        Files.move(brsetting, setting, StandardCopyOption.REPLACE_EXISTING);
                        Process process = (new ProcessBuilder(new String[] { "amped.exe" })).start();
                        System.exit(0);

                    } else if (Files.exists(eusetting)) { // br server was the last played

                        Process process = (new ProcessBuilder(new String[] { "amped.exe" })).start();
                        System.exit(0);
                    }

                } else if (Files.exists(brsetting) && Files.exists(brsetting)) { // probably the first time user use the
                                                                                 // launcher
                    Files.move(brsetting.toFile().toPath(), setting.toFile().toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                    Process process = (new ProcessBuilder(new String[] { "amped.exe" })).start();
                    System.exit(0);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

}

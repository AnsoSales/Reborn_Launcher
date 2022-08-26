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

import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
    private JRadioButton EURadioButton = new JRadioButton("EU Server");
    private JLabel EUButtonlabel = new JLabel();
    private JLabel BRButtonlabel = new JLabel();

    private JRadioButton BRRadioButton = new JRadioButton("BR Server");
    // private JWindow statusJWindow = new JWindow();
    private JLabel updateStatusLabel = new JLabel();
    private JButton btnNewButton = new JButton("Start Game");
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
                    window.frmRebornFightersLauncher.setTitle("Reborn Launcher");
                    window.frmRebornFightersLauncher.setVisible(true);
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

        Gbutton.add(BRRadioButton);
        Gbutton.add(EURadioButton);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        // int width = 1920;
        int height = gd.getDisplayMode().getHeight();
        // int height = 1080;
        System.out.println(width);
        System.out.println(height);
        this.frmRebornFightersLauncher = new JFrame();
        this.frmRebornFightersLauncher.setResizable(false);
        this.frmRebornFightersLauncher.setTitle("Reborn Launcher");
        // this.frmRebornFightersLauncher.setBounds(0, 0, 580, 482);
        this.frmRebornFightersLauncher.setBounds(0, 0, 580, 482);
        System.out.println(height);
        JLabel backgroundLabel = new JLabel();

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0, -40, 580, 482);
        ImageIcon background = new ImageIcon(reborn.class.getResource("/launcher/launcher_oficial_cleanup.png"));

        background = new ImageIcon(
                background.getImage().getScaledInstance(backgroundPanel.getWidth(), backgroundPanel.getHeight(), 1));
        backgroundLabel.setIcon(background);
        backgroundPanel.add(backgroundLabel);

        int stylefont = 0, fontsize = 12;
        String fonte = "Baskerville Old Face";

        String tempClassVersion = "3.00.00";
        String tempResourceVersion = "3.00.00";

        String classVersion = tempClassVersion;
        String resourceVersion = tempResourceVersion;

        this.frmRebornFightersLauncher.setDefaultCloseOperation(3);
        ImageIcon icone = new ImageIcon(reborn.class.getResource("/launcher/reborn icon.png"));
        this.frmRebornFightersLauncher.setIconImage(icone.getImage());
        this.frmRebornFightersLauncher.getContentPane().setLayout((LayoutManager) null);

        JLabel classLabel = new JLabel("          CLASS VERSION: " + classVersion);
        classLabel.setForeground(Color.decode("#808080"));
        classLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        // classLabel.setBackground(Color.BLACK);
        classLabel.setBounds(374, -7, 200, 30);
        classLabel.setFont(new Font(fonte, stylefont, fontsize));
        classLabel.setVisible(true);

        JLabel resourceLabel = new JLabel("RESOURCE VERSION: " + resourceVersion);
        resourceLabel.setForeground(Color.decode("#808080"));
        resourceLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        resourceLabel.setBounds(375, 7, 200, 30);
        resourceLabel.setFont(new Font(fonte, stylefont, fontsize));
        resourceLabel.setVisible(true);

        btnNewButton.setBounds(175, 350, 250, 50);
        btnNewButton.setEnabled(false);
        btnNewButton.setForeground(Color.decode("#808080"));
        btnNewButton.setBackground(Color.decode("#f0f0f0"));
        btnNewButton.setOpaque(true);
        btnNewButton.setContentAreaFilled(true);
        btnNewButton.setIcon(icone);
        btnNewButton.addActionListener(this);

        progressBar.setBounds(45, 10, 312, 17);
        progressBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        progressBar.setStringPainted(true);
        // progressBar.setBackground(Color.WHITE);
        progressBar.setOpaque(false);

        progressBar.setBorderPainted(false);
        progressBar.setString(this.updateStatusLabel.getText());
        progressBar.setFont(new Font(fonte, 30, 14));
        progressBar.setForeground(Color.decode("#800000"));
        progressBar.setBackground(Color.decode("#107ed6"));
        ImageIcon radioIconUnselected = new ImageIcon(reborn.class.getResource("/launcher/radiobutton.png"));
        radioIconUnselected = new ImageIcon(radioIconUnselected.getImage().getScaledInstance(14, 14, 1));

        ImageIcon radioIconRollover = new ImageIcon(reborn.class.getResource("/launcher/selectedbutton_hover.png"));
        radioIconRollover = new ImageIcon(radioIconRollover.getImage().getScaledInstance(15, 15, 1));

        ImageIcon radioIconSelected = new ImageIcon(reborn.class.getResource("/launcher/selectedbutton.png"));

        BRRadioButton.setBounds(59, 344,
                100, 30);
        // System.out.println(100 * (width / 1920));
        int textgap = 2;
        BRButtonlabel.setFont(new Font(null, stylefont, fontsize));
        BRButtonlabel.setText("BR Server");

        BRRadioButton.setFocusPainted(false);
        BRRadioButton.setHorizontalTextPosition(SwingConstants.LEFT);
        BRRadioButton.setSelected(true);
        BRRadioButton.setForeground(Color.BLACK);
        BRRadioButton.setOpaque(false);
        BRRadioButton.setContentAreaFilled(true);
        BRRadioButton.setIcon(radioIconUnselected);
        BRRadioButton.setIconTextGap(textgap);
        BRRadioButton.setSelectedIcon(radioIconSelected);
        BRRadioButton.setBackground(Color.GRAY);
        // BRRadioButton.add(BRButtonlabel);
        BRRadioButton.setRolloverSelectedIcon(radioIconRollover);

        EUButtonlabel.setFont(new Font(null, stylefont, fontsize));
        EUButtonlabel.setText("EU Server");

        EURadioButton.setBounds(60, 362, 100, 30);
        EURadioButton.setFocusPainted(false);
        EURadioButton.setHorizontalTextPosition(SwingConstants.LEFT);
        EURadioButton.setForeground(Color.BLACK);
        EURadioButton.setOpaque(false);
        EURadioButton.setContentAreaFilled(true);
        EURadioButton.setIcon(radioIconUnselected);
        EURadioButton.setIconTextGap(textgap);
        EURadioButton.setSelectedIcon(radioIconSelected);
        // EURadioButton.add(EUButtonlabel);
        EURadioButton.setRolloverSelectedIcon(radioIconRollover);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 40, 540,
                300);
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        if (netIsAvailable()) {
            ImageIcon icon = new ImageIcon(reborn.class.getResource("/launcher/background_launcher.png"));
            icon = new ImageIcon(icon.getImage().getScaledInstance(panel.getWidth(), panel.getHeight(), 1));
            JLabel label = new JLabel();
            label.setIcon(icon);

            label.setBounds(0, 0, 540, 300);
            label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            panel.add(label);

            frmRebornFightersLauncher.setVisible(true);
            updateStatusLabel.setText("Game already updated.");
            progressBar.setValue(100);
            progressBar.setString(updateStatusLabel.getText());
            updateStatusLabel.setForeground(Color.WHITE);
            btnNewButton.setEnabled(true);
        } else {
            updateStatusLabel.setText("Failed to initialize the launcher.");
            progressBar.setValue(100);
            progressBar.setString(updateStatusLabel.getText());
            updateStatusLabel.setForeground(Color.WHITE);
            btnNewButton.setEnabled(false);

        }

        this.frmRebornFightersLauncher.getContentPane().add(updateStatusLabel);
        this.frmRebornFightersLauncher.getContentPane().add(progressBar);
        this.frmRebornFightersLauncher.getContentPane().add(btnNewButton);
        this.frmRebornFightersLauncher.getContentPane().add(BRRadioButton);
        this.frmRebornFightersLauncher.getContentPane().add(EURadioButton);
        this.frmRebornFightersLauncher.getContentPane().add(classLabel);
        this.frmRebornFightersLauncher.getContentPane().add(resourceLabel);
        this.frmRebornFightersLauncher.getContentPane().add(panel);
        this.frmRebornFightersLauncher.getContentPane().add(backgroundPanel);
        this.frmRebornFightersLauncher.setLocationRelativeTo(null);

        BRRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ImageIcon radioIconUnselectedHover = new ImageIcon(
                        reborn.class.getResource("/launcher/radiobutton_mouse.png"));
                radioIconUnselectedHover = new ImageIcon(
                        radioIconUnselectedHover.getImage().getScaledInstance(15, 14, 1));
                BRRadioButton.setIcon(radioIconUnselectedHover);
                BRRadioButton.setIconTextGap(2);

            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                ImageIcon radioIconUnselected = new ImageIcon(reborn.class.getResource("/launcher/radiobutton.png"));
                radioIconUnselected = new ImageIcon(radioIconUnselected.getImage().getScaledInstance(14, 14, 1));
                BRRadioButton.setIcon(radioIconUnselected);
                BRRadioButton.setIconTextGap(2);

            }
        });

        EURadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ImageIcon radioIconUnselectedHover = new ImageIcon(
                        reborn.class.getResource("/launcher/radiobutton_mouse.png"));
                radioIconUnselectedHover = new ImageIcon(
                        radioIconUnselectedHover.getImage().getScaledInstance(15, 14, 1));
                EURadioButton.setIcon(radioIconUnselectedHover);
                EURadioButton.setIconTextGap(2);

            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                ImageIcon radioIconUnselected = new ImageIcon(reborn.class.getResource("/launcher/radiobutton.png"));
                radioIconUnselected = new ImageIcon(radioIconUnselected.getImage().getScaledInstance(14, 14, 1));
                EURadioButton.setIcon(radioIconUnselected);
                EURadioButton.setIconTextGap(2);
            }
        });
        btnNewButton.addActionListener(this);
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

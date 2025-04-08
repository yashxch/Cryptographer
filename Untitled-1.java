import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EncryptWebsite extends JFrame {

    private static final String CHAR_SET = " " + "~!@#$%^&*()-_=+[{]}|;:'\",<.>/?"
            + "0123456789"
            + "abcdefghijklmnopqrstuvwxyz"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final List<Character> CHARS = new ArrayList<>();
    private static final List<Character> KEY = new ArrayList<>();

    static {
        for (char c : CHAR_SET.toCharArray()) {
            CHARS.add(c);
            KEY.add(c);
        }
        Collections.shuffle(KEY); // Shuffle the key to create a cipher
    }

    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JLabel charCountLabel;
    private JButton encryptButton;
    private JButton decryptButton;
    private JButton clearButton;
    private JButton resetKeyButton;
    private JButton saveKeyButton;
    private JButton loadKeyButton;
    private JButton fileInputButton;
    private JButton fileOutputButton;

    public EncryptWebsite() {
        setTitle("Encrypt | Business Value Through Digital Products");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.DARK_GRAY);

        JPanel navPanel = createNavBar();
        mainPanel.add(navPanel, BorderLayout.NORTH);

        JPanel featurePanel = createFeaturePanel();
        mainPanel.add(featurePanel, BorderLayout.CENTER);

        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setResizable(true);
        setVisible(true);
    }

    private JPanel createNavBar() {
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        navPanel.setBackground(Color.DARK_GRAY);

        JButton homeButton = createNavButton("Home", "home.png");
        JButton aboutButton = createNavButton("About", "about.png");
        JButton contactButton = createNavButton("Contact", "contact.png");

        contactButton.addActionListener(e -> showContactInfo());
        aboutButton.addActionListener(e -> showAboutInfo());

        navPanel.add(homeButton);
        navPanel.add(aboutButton);
        navPanel.add(contactButton);

        return navPanel;
    }

    private JButton createNavButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(Color.GRAY);
        button.setForeground(Color.BLACK);  // Set text color to black
        button.setToolTipText("Go to " + text);
        return button;
    }

    private JPanel createFeaturePanel() {
        JPanel featurePanel = new JPanel(new GridBagLayout());
        featurePanel.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        inputTextArea = new JTextArea(8, 40);
        inputTextArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Input Text",
                javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        inputTextArea.setBackground(Color.WHITE);
        inputTextArea.setForeground(Color.BLACK);  // Set text color to black
        inputTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        inputTextArea.setWrapStyleWord(true);
        inputTextArea.setLineWrap(true);
        JScrollPane inputScroll = new JScrollPane(inputTextArea);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.4;
        featurePanel.add(inputScroll, gbc);

        charCountLabel = new JLabel("Character Count: 0");
        charCountLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        charCountLabel.setForeground(Color.BLACK);  // Set label text color to black
        gbc.gridy = 1;
        gbc.weighty = 0.05;
        featurePanel.add(charCountLabel, gbc);

        outputTextArea = new JTextArea(8, 40);
        outputTextArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Output Text",
                javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        outputTextArea.setEditable(false);
        outputTextArea.setBackground(Color.WHITE);
        outputTextArea.setForeground(Color.BLACK);  // Set text color to black
        outputTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setLineWrap(true);
        JScrollPane outputScroll = new JScrollPane(outputTextArea);
        gbc.gridy = 2;
        gbc.weighty = 0.4;
        featurePanel.add(outputScroll, gbc);

        inputTextArea.addCaretListener(e -> {
            int charCount = inputTextArea.getText().length();
            charCountLabel.setText("Character Count: " + charCount);
        });

        return featurePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.DARK_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        encryptButton = new JButton("Encrypt");
        encryptButton.setBackground(new Color(76, 175, 80));
        encryptButton.setForeground(Color.BLACK);  // Set text color to black
        encryptButton.setFont(new Font("Arial", Font.BOLD, 14));

        decryptButton = new JButton("Decrypt");
        decryptButton.setBackground(new Color(244, 67, 54));
        decryptButton.setForeground(Color.BLACK);  // Set text color to black
        decryptButton.setFont(new Font("Arial", Font.BOLD, 14));

        clearButton = new JButton("Clear");
        clearButton.setBackground(new Color(255, 152, 0));
        clearButton.setForeground(Color.BLACK);  // Set text color to black
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));

        resetKeyButton = new JButton("Reset Key");
        resetKeyButton.setBackground(new Color(0, 150, 136));
        resetKeyButton.setForeground(Color.BLACK);  // Set text color to black
        resetKeyButton.setFont(new Font("Arial", Font.BOLD, 14));

        saveKeyButton = new JButton("Save Key");
        saveKeyButton.setBackground(new Color(63, 81, 181));
        saveKeyButton.setForeground(Color.BLACK);  // Set text color to black
        saveKeyButton.setFont(new Font("Arial", Font.BOLD, 14));

        loadKeyButton = new JButton("Load Key");
        loadKeyButton.setBackground(new Color(103, 58, 183));
        loadKeyButton.setForeground(Color.BLACK);  // Set text color to black
        loadKeyButton.setFont(new Font("Arial", Font.BOLD, 14));

        fileInputButton = new JButton("Open File");
        fileInputButton.setBackground(new Color(3, 169, 244));
        fileInputButton.setForeground(Color.BLACK);  // Set text color to black
        fileInputButton.setFont(new Font("Arial", Font.BOLD, 14));

        fileOutputButton = new JButton("Save to File");
        fileOutputButton.setBackground(new Color(0, 188, 212));
        fileOutputButton.setForeground(Color.BLACK);  // Set text color to black
        fileOutputButton.setFont(new Font("Arial", Font.BOLD, 14));

        encryptButton.addActionListener(e -> encryptText());
        decryptButton.addActionListener(e -> decryptText());
        clearButton.addActionListener(e -> clearFields());
        resetKeyButton.addActionListener(e -> resetKey());
        saveKeyButton.addActionListener(e -> saveKey());
        loadKeyButton.addActionListener(e -> loadKey());
        fileInputButton.addActionListener(e -> openFile());
        fileOutputButton.addActionListener(e -> saveFile());

        gbc.gridx = 0;
        buttonPanel.add(encryptButton, gbc);
        gbc.gridx = 1;
        buttonPanel.add(decryptButton, gbc);
        gbc.gridx = 2;
        buttonPanel.add(clearButton, gbc);
        gbc.gridx = 3;
        buttonPanel.add(resetKeyButton, gbc);
        gbc.gridx = 4;
        buttonPanel.add(saveKeyButton, gbc);
        gbc.gridx = 5;
        buttonPanel.add(loadKeyButton, gbc);
        gbc.gridx = 6;
        buttonPanel.add(fileInputButton, gbc);
        gbc.gridx = 7;
        buttonPanel.add(fileOutputButton, gbc);

        return buttonPanel;
    }

    private void encryptText() {
        String plainText = inputTextArea.getText();
        if (plainText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter text to encrypt.",
                    "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String cipherText = encrypt(plainText);
        outputTextArea.setText(cipherText);
        JOptionPane.showMessageDialog(null, "Message Encrypted!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void decryptText() {
        String cipherText = inputTextArea.getText();
        if (cipherText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter text to decrypt.",
                    "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String plainText = decrypt(cipherText);
        outputTextArea.setText(plainText);
        JOptionPane.showMessageDialog(null, "Message Decrypted!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields() {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear all fields?",
                "Confirm Clear", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            inputTextArea.setText("");
            outputTextArea.setText("");
            charCountLabel.setText("Character Count: 0");
        }
    }

    private void resetKey() {
        Collections.shuffle(KEY);
        JOptionPane.showMessageDialog(null, "Encryption Key Reset! Note: Decryption with previous key is no longer possible.",
                "Key Reset", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveKey() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Character c : KEY) {
                    writer.write(c);
                }
                JOptionPane.showMessageDialog(null, "Key saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving key!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadKey() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                KEY.clear();
                int charVal;
                while ((charVal = reader.read()) != -1) {
                    KEY.add((char) charVal);
                }
                JOptionPane.showMessageDialog(null, "Key loaded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading key!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                inputTextArea.read(reader, null);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error opening file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                outputTextArea.write(writer);
                JOptionPane.showMessageDialog(null, "File saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String encrypt(String text) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = CHARS.indexOf(c);
            if (index != -1) {
                encrypted.append(KEY.get(index));
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    private String decrypt(String text) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = KEY.indexOf(c);
            if (index != -1) {
                decrypted.append(CHARS.get(index));
            } else {
                decrypted.append(c);
            }
        }
        return decrypted.toString();
    }

    private void showContactInfo() {
        String contactMessage = "Contact us at:\nEmail: kr9221@srmist.edu.in\nPhone: +919971063223";
        JOptionPane.showMessageDialog(this, contactMessage, "Contact Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showAboutInfo() {
        String aboutMessage = "<html><body style='width: 400px;'>"
                + "<h2>About EncryptWebsite</h2>"
                + "<p>EncryptWebsite is a simple tool that allows you to encrypt and decrypt messages "
                + "using a randomized character substitution cipher.</p>"
                + "<h3>Features:</h3>"
                + "<ul>"
                + "<li>Encrypt plain text</li>"
                + "<li>Decrypt cipher text</li>"
                + "<li>Reset encryption key</li>"
                + "<li>Clear input/output fields</li>"
                + "</ul>"
                + "<br><p>Developed by: Harsh Trivedi</p>"
                + "</body></html>";
        JOptionPane.showMessageDialog(this, aboutMessage, "About EncryptWebsite", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new EncryptWebsite());
    }
}

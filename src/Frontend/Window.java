package Frontend;

import Backend.NumbersSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame implements ActionListener {
    List<String> list = new ArrayList<>();
    JTextField insField, resField;
    JButton convert;
    JMenuBar menuBar;
    JMenu help, file;
    JMenuItem about, save;
    JComboBox<String> sys1, sys2;

    Window() {
        setSize(500, 500);
        setTitle("Converter binary system on decimal system");
        setLayout(null);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        file = new JMenu("File");
        menuBar.add(file);

        help = new JMenu("Help");
        menuBar.add(help);

        save = new JMenuItem("Save as");
        save.addActionListener(this);
        file.add(save);

        about = new JMenuItem("About us");
        about.addActionListener(this);
        help.add(about);

        insField = new JTextField();
        insField.setText("insert number to convert");
        insField.setFont(new Font("Helvetica", Font.ITALIC, 13));
        insField.setForeground(Color.LIGHT_GRAY);
        insField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                insField.setText("");
                insField.setFont(new Font("Helvetica", Font.PLAIN, 14));
                insField.setForeground(Color.BLACK);
            }
        });

        insField.setBounds(200, 50, 250, 20);
        add(insField);

        convert = new JButton();
        convert.setBounds(200, 150, 250, 20);
        convert.setText("Convert");
        convert.setFont(new Font("Futura", Font.PLAIN, 14));
        convert.addActionListener(this);
        add(convert);

        resField = new JTextField();
        resField.setBounds(200, 100, 250, 20);
        add(resField);

        sys1 = new JComboBox<>();
        sys1.setBounds(40, 50, 150, 25);
        sys1.addItem("Binary");
        sys1.addItem("Decimal");
        sys1.addItem("Octal");
        sys1.addActionListener(this);
        add(sys1);

        sys2 = new JComboBox<>();
        sys2.setBounds(40, 100, 150, 25);
        sys2.addItem("Binary");
        sys2.addItem("Decimal");
        sys2.addItem("Octal");
        sys2.addActionListener(this);
        add(sys2);
    }

    public static void main(String[] args) {
        Window window = new Window();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setIconImage(new ImageIcon("icon.png").getImage());
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == convert) {
            String system1 = String.valueOf(sys1.getSelectedItem());
            String system2 = String.valueOf(sys2.getSelectedItem());

            NumbersSystem num = new NumbersSystem();
            switch (system1) {
                case "Binary" -> {
                    switch (system2) {
                        case "Binary" -> {
                            resField.setText(insField.getText());
                            list.add(String.format("%-20s %-20s", insField.getText(), resField.getText()));
                        }
                        case "Decimal" -> {
                            resField.setText(num.binDec(insField.getText()));
                            String tmp = String.format("%-20s %-20s", insField.getText(), resField.getText());
                            list.add(tmp);
                        }
                        case "Octal" -> {
                            resField.setText(num.decAno(num.binDec(insField.getText()),8));
                            String tmp = String.format("%-20s %-20s", insField.getText(), resField.getText());
                            list.add(tmp);
                        }
                    }
                }
                case "Decimal" -> {
                    switch (system2) {
                        case "Binary" -> {
                            resField.setText(num.decAno(insField.getText(), 2));
                            String tmp = String.format("%-20s %-20s", insField.getText(), resField.getText());
                            list.add(tmp);
                        }
                        case "Decimal" -> {
                            resField.setText(insField.getText());
                            String tmp=String.format("%-20s %-20s", insField.getText(), resField.getText());
                            list.add(tmp);
                        }
                        case "Octal" -> {
                            resField.setText(num.decAno(insField.getText(), 8));
                            String tmp = String.format("%-20s %-20s", insField.getText(), resField.getText());
                            list.add(tmp);
                        }
                    }
                }
                case "Octal" -> {
                    switch (system2) {
                        case "Binary" -> {
                            resField.setText(num.decAno(num.octDec(insField.getText()), 2));
                            String tmp = String.format("%-20s %-20s", insField.getText(), resField.getText());
                            list.add(tmp);
                        }
                        case "Decimal" -> {
                            resField.setText(num.octDec(insField.getText()));
                            String tmp = String.format("%-20s %-20s", insField.getText(), resField.getText());
                            list.add(tmp);
                        }
                        case "Octal" -> {
                            resField.setText(insField.getText());
                            String tmp=String.format("%-20s %-20s", insField.getText(), resField.getText());
                            list.add(tmp);
                        }
                    }
                }
            }
        }
        if (source == about) {
            String msg = "Author: Lukas Fabia\nComputer science student";
            JOptionPane.showMessageDialog(null, msg, "About me", JOptionPane.INFORMATION_MESSAGE);
        }
        if (source == save) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                    writer.write(String.format("%-5s %5s\n", "Created:", LocalDate.now()));
                    for (String s : list) {
                        writer.write(s);
                        writer.newLine();
                    }
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}

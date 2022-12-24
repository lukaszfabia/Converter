package Frontend;

import Backend.NumbersSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Window {
    private final List<String> list = new ArrayList<>();
    private JTextField insField;
    private JTextField resField;
    private JButton convert;
    private JComboBox<String> sys1;
    private JComboBox<String> sys2;
    private final String[] names = {"Binary", "Decimal", "Octal", "Hexal"};
    private JPanel panel;
    private Color defultColor;
    private JFrame frame;

    public static void main(String[] args) {
        Window window = new Window();
        window.GUI();
    }

    public void bar() {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu file = new JMenu("File");
        menuBar.add(file);

        JMenu settings = new JMenu("Settings");
        menuBar.add(settings);

        JMenu help = new JMenu("Help");
        menuBar.add(help);

        JMenuItem save = new JMenuItem("Save as");
        save.addActionListener(new Save());
        file.add(save);

        JMenuItem theme = new JMenuItem("Theme");
        theme.addActionListener(new Theme());
        settings.add(theme);


        JMenuItem about = new JMenuItem("About us");
        about.addActionListener(new AboutUs());
        help.add(about);
    }

    public void fields() {
        insField = new JTextField("insert a number here");
        insField.setFont(new Font("Helvetica", Font.ITALIC, 13));
        insField.setForeground(Color.LIGHT_GRAY);
        insField.addMouseListener(new InsFieldEvent());
        insField.setBounds(200, 50, 250, 20);
        panel.add(insField);

        resField = new JTextField();
        resField.setBounds(200, 100, 250, 20);
        resField.setFont(new Font("Helvetica", Font.PLAIN, 14));
        panel.add(resField);
    }

    public void buttons() {
        convert = new JButton();
        convert.setBounds(200, 150, 250, 20);
        convert.setText("Convert");
        convert.setFont(new Font("Futura", Font.PLAIN, 14));
        convert.addActionListener(new Convert());
        panel.add(convert);
    }

    public void comboBoxes() {
        sys1 = new JComboBox<>(names);
        sys1.setBounds(40, 50, 150, 25);
        sys1.addActionListener(new Convert());
        panel.add(sys1);

        sys2 = new JComboBox<>(names);
        sys2.setBounds(40, 100, 150, 25);
        sys2.addActionListener(new Convert());
        panel.add(sys2);
    }

    public void GUI() {
        ImageIcon image = new ImageIcon("icon.png");
        frame = new JFrame("Numbers system calculator");
        frame.setSize(500, 350);
        frame.setIconImage(image.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        defultColor = panel.getBackground();
        panel.setLayout(null);

        bar();
        fields();
        buttons();
        comboBoxes();

        frame.add(panel);
        frame.setVisible(true);
    }

    class Convert implements ActionListener {

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
                                resField.setText(num.decAno(num.binDec(insField.getText()), 8));
                                String tmp = String.format("%-20s %-20s", insField.getText(), resField.getText());
                                list.add(tmp);
                            }
                            case "Hexal" -> {
                                resField.setText(num.decAno(num.binDec(insField.getText()),16));
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
                                String tmp = String.format("%-20s %-20s", insField.getText(), resField.getText());
                                list.add(tmp);
                            }
                            case "Octal" -> {
                                resField.setText(num.decAno(insField.getText(), 8));
                                String tmp = String.format("%-20s %-20s", insField.getText(), resField.getText());
                                list.add(tmp);
                            }
                            case "Hexal" -> {
                                resField.setText(num.decAno(insField.getText(), 16));
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
                                String tmp = String.format("%-20s %-20s", insField.getText(), resField.getText());
                                list.add(tmp);
                            }
                            case "Hexal" -> {
                                resField.setText(num.decAno(num.octDec(insField.getText()),16));
                                String tmp = String.format("%-20s %-20s", insField.getText(), resField.getText());
                                list.add(tmp);
                            }
                        }
                    }
                    case "Hexal" ->{
                        switch (system2){
                            case "Binary" -> {
                                resField.setText(num.decAno(num.hexDec(insField.getText()),2));
                                String tmp = String.format("%-20s %-20s", insField.getText(), resField.getText());
                                list.add(tmp);
                            }
                            case "Decimal" -> {
                                resField.setText(num.hexDec(insField.getText()));
                                String tmp = String.format("%-20s %-20s", insField.getText(), resField.getText());
                                list.add(tmp);
                            }
                            case "Octal" -> {
                                resField.setText(num.decAno(num.hexDec(insField.getText()),8));
                                String tmp = String.format("%-20s %-20s", insField.getText(), resField.getText());
                                list.add(tmp);
                            }
                            case "Hexal" -> {
                                resField.setText(insField.getText().toUpperCase());
                                String tmp = String.format("%-20s %-20s", insField.getText(), resField.getText());
                                list.add(tmp);
                            }
                        }
                    }
                }
            }
        }
    }

    static class AboutUs implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String msg = "Author: Lukas Fabia\nComputer science student";
            JOptionPane.showMessageDialog(null, msg, "About me", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class Save implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
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

    class Theme implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] themes = {"Dark mode", "Light mode"};
            JComboBox<String> box = new JComboBox<>(themes);

            int result = JOptionPane.showConfirmDialog(null, box, "Change theme", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String option = (String) box.getSelectedItem();
                assert option != null;
                if (option.equals("Dark mode")) {
                    Color dark = new Color(67, 66, 77);
                    panel.setBackground(dark);

                }
                if (option.equals("Light mode")) {
                    panel.setBackground(defultColor);
                }
            }
        }
    }

    class InsFieldEvent implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            insField.setText(null);
            insField.setFont(new Font("Helvetica", Font.PLAIN, 14));
            insField.setForeground(Color.BLACK);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}

package Frontend;

import Backend.NumbersSystem;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame implements ActionListener {
    List<String> list = new ArrayList<>();
    JTextField insField, resField;
    JButton convert;
    JLabel result, insert;
    ButtonGroup box;
    JRadioButton binDec, decBin;
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


        insert = new JLabel("Binary number: ");
        insert.setBounds(62, 50, 200, 20);
        insert.setFont(new Font("Futura", Font.PLAIN, 15));
        //add(insert);

        insField = new JTextField("");
        insField.setBounds(200, 50, 200, 20);
        add(insField);

        convert = new JButton();
        convert.setBounds(150, 200, 200, 20);
        convert.setText("Convert");
        convert.setFont(new Font("Futura", Font.PLAIN, 14));
        convert.addActionListener(this);
        add(convert);

        result = new JLabel("Decimal number:");
        result.setBounds(50, 100, 200, 20);
        result.setFont(new Font("Futura", Font.PLAIN, 15));
        //add(result);

        resField = new JTextField();
        resField.setBounds(200, 100, 200, 20);
        add(resField);

        box = new ButtonGroup();
        binDec = new JRadioButton("Binary to decimal");
        binDec.setBounds(100, 160, 150, 20);
        binDec.setFont(new Font("Futura", Font.PLAIN, 14));
        //add(binDec);
        //box.add(binDec);

        decBin = new JRadioButton("Decimal to binary");
        decBin.setBounds(250, 160, 150, 20);
        decBin.setFont(new Font("Futura", Font.PLAIN, 14));
        //add(decBin);
        //box.add(decBin);

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
            /*if (binDec.isSelected()) {
                NumbersSystem num = new NumbersSystem();
                decField.setText(num.binDec(binField.getText()));
                String tmp = String.format("%-20s %-20s", binField.getText(), num.binDec(binField.getText()));
                list.add(tmp);
            } else if (decBin.isSelected()) {
                NumbersSystem num = new NumbersSystem();
                binField.setText(num.decBin(decField.getText()));
                String tmp = String.format("%-20s %-20s", num.decBin(decField.getText()), decField.getText());
                list.add(tmp);
            } */

            String system1 = String.valueOf(sys1.getSelectedItem());
            String system2 = String.valueOf(sys2.getSelectedItem());

            NumbersSystem num = new NumbersSystem();
            switch (system1) {
                case "Binary" -> {
                    switch (system2) {
                        case "Binary" -> {
                            resField.setText(insField.getText());
                            //String tmp = String.format("%-20s %-20s", insField.getText(), resField.getText());
                            list.add(String.format("%-20s %-20s", insField.getText(), resField.getText()));
                        }
                        case "Decimal" -> {
                            resField.setText(num.binDec(insField.getText()));
                            String tmp = String.format("%-20s %-20s", insField.getText(), num.binDec(resField.getText()));
                            list.add(tmp);
                        }
                        case "Octal" -> {
                            String tmp = String.format("%-20s %-20s", insField.getText(), num.binDec(resField.getText()));
                            list.add(tmp);
                        }
                    }
                }
                case "Decimal" -> {
                    switch (system2) {
                        case "Binary" -> {
                            resField.setText(num.decBin(insField.getText()));
                            String tmp = String.format("%-20s %-20s", insField.getText(), num.binDec(resField.getText()));
                            list.add(tmp);
                        }
                        case "Decimal" -> {
                            resField.setText(insField.getText());
                            list.add(String.format("%-20s %-20s", insField.getText(), resField.getText()));
                        }
                        case "Octal" -> {
                            String tmp = String.format("%-20s %-20s", insField.getText(), num.binDec(resField.getText()));
                            list.add(tmp);
                        }
                    }
                }
                case "Octal" -> {
                    switch (system2) {
                        case "Binary" -> {
                            String tmp = String.format("%-20s %-20s", insField.getText(), num.binDec(resField.getText()));
                            list.add(tmp);
                        }
                        case "Decimal" -> {
                            String tmp = String.format("%-20s %-20s", insField.getText(), num.binDec(resField.getText()));
                            list.add(tmp);
                        }
                        case "Octal" -> {
                            resField.setText(insField.getText());
                            list.add(String.format("%-20s %-20s", insField.getText(), resField.getText()));
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

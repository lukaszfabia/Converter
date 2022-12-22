package Frontend;

import Backend.NumbersSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame implements ActionListener {
    List<String> list = new ArrayList<>();
    JTextField binField, decField;
    JButton convert;
    JLabel result, insert;
    ButtonGroup box;
    JRadioButton binDec, decBin;
    JMenuBar menuBar;
    JMenu help, file;
    JMenuItem about, save;

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
        add(insert);

        binField = new JTextField("");
        binField.setBounds(200, 50, 200, 20);
        add(binField);

        convert = new JButton();
        convert.setBounds(150, 200, 200, 20);
        convert.setText("Convert");
        convert.setFont(new Font("Futura", Font.PLAIN, 14));
        convert.addActionListener(this);
        add(convert);

        result = new JLabel("Decimal number:");
        result.setBounds(50, 100, 200, 20);
        result.setFont(new Font("Futura", Font.PLAIN, 15));
        add(result);

        decField = new JTextField();
        decField.setBounds(200, 100, 200, 20);
        add(decField);

        box = new ButtonGroup();
        binDec = new JRadioButton("Binary to decimal");
        binDec.setBounds(100, 160, 150, 20);
        binDec.setFont(new Font("Futura", Font.PLAIN, 14));
        add(binDec);
        box.add(binDec);

        decBin = new JRadioButton("Decimal to binary");
        decBin.setBounds(250, 160, 150, 20);
        decBin.setFont(new Font("Futura", Font.PLAIN, 14));
        add(decBin);
        box.add(decBin);
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
            if (binDec.isSelected()) {
                NumbersSystem num = new NumbersSystem();
                decField.setText(num.binDec(binField.getText()));
                String tmp = String.format("%-20s %-20s", binField.getText(), num.binDec(binField.getText()));
                list.add(tmp);
            } else if (decBin.isSelected()) {
                NumbersSystem num = new NumbersSystem();
                binField.setText(num.decBin(decField.getText()));
                String tmp = String.format("%-20s %-20s", num.decBin(decField.getText()), decField.getText());
                list.add(tmp);
            }
        }
        if (source == about) {
            String msg = "Author: Lukas Fabia\nComputer science student";
            JOptionPane.showMessageDialog(null, msg, "About me", JOptionPane.INFORMATION_MESSAGE);
        }
        if (source == save) {
            int ans = JOptionPane.showConfirmDialog(null, "Do you want to save", "Save as", JOptionPane.OK_CANCEL_OPTION);
            if (ans == JOptionPane.OK_OPTION) {
                String fileName = JOptionPane.showInputDialog(null, "File name:");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                    writer.write(String.format("%-4s %4s\n", "Created:", LocalDate.now()));
                    writer.write(String.format("%-20s %-20s\n", "Binary number", "Decimal number"));
                    for (String s : list) {
                        writer.write(s);
                        writer.newLine();
                    }
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, "Could not save file", "Save as", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}

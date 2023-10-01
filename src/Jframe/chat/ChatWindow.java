package Jframe.chat;

import Jframe.SettingsWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import static jdk.xml.internal.SecuritySupport.isFileExists;


public class ChatWindow extends JFrame {
        private static final int WINDOW_HEIGHT = 600;
        private static final int WINDOW_WIDTH = 600;
        private static final int WINDOW_POSX = 800;
        private static final int WINDOW_POSY = 300;

        JButton btnConnect = new JButton("Connect");
        JButton btnExit = new JButton("Exit");
        



        ChatWindow() throws IOException {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocation(WINDOW_POSX, WINDOW_POSY);
            setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            setTitle("TicTacToe");
            setResizable(false);


            btnExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });




            JPanel panSet = new JPanel(new GridLayout(5, 1));
            JTextField loginField = new JTextField("login");
            JTextField passField = new JTextField("pass");
            JTextField ipField = new JTextField("ip");
            JTextField portField = new JTextField("port");
            panSet.add(loginField);
            panSet.add(passField);
            panSet.add(ipField);
            panSet.add(portField);
            panSet.add(btnConnect);

            add(panSet,BorderLayout.NORTH);



            FileWriter fileWriter = new FileWriter("./chat.txt",true);


            JPanel panMid = new JPanel(new GridLayout(1, 2));
            JTextArea fieldChat = new JTextArea();
            fieldChat.setEditable(false);
            JList<String> listUsers = new JList<>();
            String users[] = {"Dima","Maks","Anna","Sasha"};
            listUsers.setListData(users);
            panMid.add(fieldChat);
            panMid.add(listUsers);

            add(panMid,BorderLayout.CENTER);

            btnConnect.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    File file = new File("./chat.txt");
                    try {
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNextLine()){
                            fieldChat.append(scanner.nextLine()+ String.format("%n"));
                        }
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });



            JPanel panChat = new JPanel(new GridLayout(2, 1));

            JTextField fieldMessage = new JTextField("enter text");
            fieldMessage.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER){
                        fieldChat.append(fieldMessage.getText() + String.format("%n"));
                        try {

                            fieldMessage.write(fileWriter);
                            fileWriter.write(System.getProperty("line.separator"));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            JButton btnSend = new JButton("Send");
            btnSend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fieldChat.append(fieldMessage.getText() + String.format("%n"));
                    try {

                        fieldMessage.write(fileWriter);
                        fileWriter.write(System.getProperty("line.separator"));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            });
            panChat.add(fieldMessage);
            panChat.add(btnSend);

            add(panChat,BorderLayout.SOUTH);

            setVisible(true);

        }
    }



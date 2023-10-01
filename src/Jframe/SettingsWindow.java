package Jframe;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 230;
    private static final int WINDOW_WIDTH = 350;
    static int mode = 0;
    static int fSzX = 3;
    static int fSzY = 3;
    static int wLen = 3;


    JButton btnStart = new JButton("Start new game");


    SettingsWindow(Jframe.GameWindow gameWindow) {
        setLocationRelativeTo(gameWindow);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);




//        JPanel panBottom = new JPanel(new GridLayout(1, 2));
//        panBottom.add(btnStart);
//        panBottom.add(btnExit);
//        add(panBottom, BorderLayout.SOUTH);
//        add(map);
//        setVisible(true);

        setLayout(new GridLayout(10,1));
        add(new JLabel("Выберите режим игры"));

//        JPanel menu = new JPanel(new GridLayout(4, 1));
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton pvp = new JRadioButton("Человек против человека");
        JRadioButton pve = new JRadioButton("Человек против компьютера");
        buttonGroup.add(pvp);
        buttonGroup.add(pve);

//        add(menu, BorderLayout.CENTER);
        add(pve);
        add(pvp);




        add(btnStart);
        JLabel labelField = (new JLabel("Выберите размер"));
        add(labelField);
        JLabel labelCurrentField = new JLabel("Установленный размер");
        add(labelCurrentField);
        JSlider slider = new JSlider(3,10,3);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelCurrentField.setText("Установленный размер " + slider.getValue());
            }
        });
        add(slider);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pvp.isSelected()){
                    mode = 1;
                }
                fSzX = slider.getValue();
                fSzY = slider.getValue();
                wLen = slider.getValue();
                gameWindow.startNewGame(mode, fSzX, fSzY, wLen);
                setVisible(false);
            }
        });

        setVisible(true);
    }


}

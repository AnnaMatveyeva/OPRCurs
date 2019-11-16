package matveyeva.opr.frames;

import matveyeva.opr.Solver;
import net.sf.javailp.Result;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FrameFromFile extends JFrame {

    private JButton open;
    private JButton calculate;
    private JButton back;
    private JLabel numOfVar;
    private JLabel numOfCond;
    private ArrayList<String> str;
    private Result result;
    private int size;

    public FrameFromFile(){
        init();
    }

    private void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);
        setBounds(300, 300, 300, 300);

        numOfVar = new JLabel();
        numOfCond = new JLabel();
        setUpOpen();
        setUpCalculate();
        setUpBack();
        this.getContentPane().add(numOfVar);
        this.getContentPane().add(numOfCond);
    }

    private void setUpOpen() {
        open = new JButton("Открыть файл");
        open.setBounds(10, 10, 150, 30);
        this.getContentPane().add(open);
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File theDirectory = new File("\\");
                JFileChooser fileopen = new JFileChooser(theDirectory);
                int ret = fileopen.showDialog(null, "Открыть файл");
                try{
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        File file = fileopen.getSelectedFile();
                        FileReader fin = new FileReader(file);
                        BufferedReader buf = new BufferedReader(fin);
                        str = new ArrayList<String>();

                        String line;
                        while((line = buf.readLine()) != null) {
                            System.out.println(line); // выводим содержимое файла на экран построчно
                            str.add(line);
                        }
                        buf.close();
                        setUpFile(str);
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"File error");
                }

            }
        });
    }

    private void setUpFile(ArrayList<String> str){

        numOfVar.setText("Количество переменных: " + str.get(0));
        size = Integer.parseInt(str.get(0));
        numOfVar.setBounds(10, 40, 200, 25);
        numOfCond.setText("Количество условий: " + str.get(1));
        numOfCond.setBounds(10, 65, 200, 25);

        calculate.setVisible(true);
    }

    private void setUpBack(){
        back = new JButton("Назад!");
        back.setBounds(170, 10, 100, 30);
        this.getContentPane().add(back);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }

    private void setUpCalculate(){
        calculate = new JButton("Расчитать");
        calculate.setBounds(10, 100, 150, 30);
        this.getContentPane().add(calculate);
        calculate.setVisible(false);
        calculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Solver solver = new Solver(str);
                ResultFrame resultFrame = new ResultFrame(solver.result, size);
                resultFrame.setVisible(true);
            }
        });
    }
}

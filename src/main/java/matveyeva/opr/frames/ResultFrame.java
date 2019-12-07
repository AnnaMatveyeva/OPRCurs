package matveyeva.opr.frames;

import net.sf.javailp.Result;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ResultFrame extends JFrame {

    private Result result;
    private JButton save;
    private JButton back;
    private JButton cancel;
    private int size;
    private ArrayList<String> results;

    public ResultFrame(){    }

    public ResultFrame(Result result, int size){
        this.result = result;
        this.size = size;
        init();
    }

    private void init(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 300, 200);
        this.getContentPane().setLayout(null);
        printResult();
        setUpBack();
        setUpSave();
        setUpCancel();

    }

    private void setUpSave(){
        save = new JButton("Сохранить");
        save.setBounds(170, 10, 100, 30);
        this.getContentPane().add(save);
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File theDirectory = new File("\\");
                JFileChooser fileopen = new JFileChooser(theDirectory);
                int ret = fileopen.showDialog(null, "Открыть файл");
                try{
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        File file = fileopen.getSelectedFile();
                        FileWriter out = new FileWriter(file,false);
                        BufferedWriter buf = new BufferedWriter(out);

                        int k = 0;
                        for(int i = 0; i < results.size();i++){
                            buf.write(results.get(i)+"\n");
                            buf.flush();

                            buf.write("");
                            buf.flush();
                        }
                    }
                }catch (IOException ex){
                    JOptionPane.showMessageDialog(null,"File error");
                }
            }
        });
    }
    private void setUpBack(){
        back = new JButton("Назад!");
        back.setBounds(170, 40, 100, 30);
        this.getContentPane().add(back);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }

    private void setUpCancel(){
        cancel = new JButton("Выход");
        cancel.setBounds(170, 70, 100,30);
        this.getContentPane().add(cancel);
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.exit(1);
            }
        });
    }

    private void printResult(){


        JLabel label = new JLabel("Результат: ");
        label.setBounds(10, 10, 100, 30);
        this.getContentPane().add(label);
        JLabel resF = new JLabel(splitResult(result).get(0));
        resF.setBounds(10, 40, 300, 30);

        StringBuilder strB = new StringBuilder();
        for(int i = 1; i< splitResult(result).size(); i++){
            strB.append(splitResult(result).get(i));

            if(i%3 == 0 || i == splitResult(result).size() - 1){
                JLabel resX = new JLabel(strB.toString());
                resX.setBounds(10, 30 + i*10, 300, 30);
                this.getContentPane().add(resX);
                strB = new StringBuilder();
            }

        }


        this.getContentPane().add(resF);
    }

    private ArrayList<String> splitResult(Result result){
        String[] str = result.toString().split(":");
        String[] res = str[1].split(" ");
        results = new ArrayList<String>();
        results.add("F(xn) = " + res[1] + "; При");
        for(int i = 2; i< res.length; i++){
            results.add(res[i]);
        }
        return results;
    }
}

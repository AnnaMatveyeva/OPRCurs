package matveyeva.opr.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    private JButton next;
    private JButton fromFile;
    private JComboBox operation;
    private JLabel problem;
    private JLabel numOfCon;
    private JLabel numOfVar;
    private JTextField numOfCond;
    private JTextField varsNum;
    private int numOfVariables;
    private int numOfConditions;
    private String toSolve;

    final String[] operations = {"Maximize","Minimize"};

    public MainFrame(){
        init();
    }

    private void init()  {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 300, 200);
        this.getContentPane().setLayout(null);

        setUpNext();
        setUpFromFile();
        problem = new JLabel("Выберите задачу:");
        problem.setBounds(10,10,200,30);

        numOfVar = new JLabel("Количество переменных:");
        numOfVar.setBounds(10,50,200,30);

        operation = new JComboBox(operations);
        operation.setBounds(170,10,110,30);

        varsNum = new JTextField("2");
        varsNum.setBounds(200,50,25,25);

        numOfCon = new JLabel("Количество условий");
        numOfCon.setBounds(10, 80, 200, 30);

        numOfCond = new JTextField("2");
        numOfCond.setBounds(200,80,25,25);

        this.getContentPane().add(problem);
        this.getContentPane().add(numOfVar);
        this.getContentPane().add(operation);
        this.getContentPane().add(varsNum);
        this.getContentPane().add(numOfCon);
        this.getContentPane().add(numOfCond);
    }

    private void setUpNext() {
        next = new JButton("Далее");
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    numOfVariables = Integer.parseInt(varsNum.getText());
                    numOfConditions = Integer.parseInt(numOfCond.getText());
                    toSolve = operation.getSelectedItem().toString();
                    if(checkNumOfVars(varsNum.getParent(), varsNum, numOfCond)) {
                        setVisible(false);
                        ConditionFrame conditionFrame = new ConditionFrame(numOfVariables, toSolve, numOfConditions);
                        conditionFrame.setVisible(true);
                    }else {
                        FrameFromFile frameFromFile = new FrameFromFile();
                        frameFromFile.setVisible(true);
                        setVisible(false);
                    }
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(getComponent(0), "Заполните поля корректно!");
                    varsNum.setBackground(Color.RED);
                }
            }
        });
        next.setBounds(10,110,100,30);
        this.getContentPane().add(next);
    }

    private boolean checkNumOfVars(Container parent, JTextField numOfV,JTextField numOfC){
        boolean result = false;
        int numOfVar = Integer.parseInt(numOfV.getText());
        int numOfCond = Integer.parseInt(numOfC.getText());
        if(numOfVar <= 0 ){
            JOptionPane.showMessageDialog(parent, "Количество переменных должно быть больше нуля");
            numOfV.setBackground(Color.RED);
        } else if(numOfCond <= 0){
            JOptionPane.showMessageDialog(parent, "Количество условий должно быть больше нуля");
            numOfC.setBackground(Color.RED);
        } else result = true;

        return result;
    }

    private void setUpFromFile(){
        fromFile = new JButton("Из файла");

        fromFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrameFromFile frameFromFile = new FrameFromFile();
                frameFromFile.setVisible(true);
                setVisible(false);
            }
        });
        fromFile.setBounds(120, 110, 100, 30);
        this.getContentPane().add(fromFile);
    }

}

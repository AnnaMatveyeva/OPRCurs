package matveyeva.opr.frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    private JComboBox operation;
    private JLabel problem;
    private JLabel numOfVar;
    private JButton next;
    private JTextField varsNum;
    private JPanel panel;

    static String[] operations = {"Maximize","Minimize"};


    public MainFrame(){
        init();
    }

    private void init() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 300, 150);
        this.getContentPane().setLayout(null);

        next = new JButton("Далее");
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                ConditionFrame conditionFrame = new ConditionFrame();
            }
        });
        next.setBounds(10,80,100,30);
        problem = new JLabel("Выбирите проблему:");
        problem.setBounds(10,10,200,30);
        numOfVar = new JLabel("Количество переменных:");
        numOfVar.setBounds(10,50,200,30);
        operation = new JComboBox(operations);
        operation.setBounds(170,10,110,30);
        varsNum = new JTextField("2");
        varsNum.setBounds(200,50,25,25);

        this.getContentPane().add(problem);
        this.getContentPane().add(numOfVar);
        this.getContentPane().add(operation);
        this.getContentPane().add(varsNum);
        this.getContentPane().add(next);
        setVisible(true);
    }


}

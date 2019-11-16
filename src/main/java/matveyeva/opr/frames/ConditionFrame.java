package matveyeva.opr.frames;

import matveyeva.opr.Solver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConditionFrame extends JFrame {

    private JTextField[] rightCol;
    private JTextField[][] odds;
    private JComboBox[] conditions;
    private JButton back;
    private JButton calculate;
    private JPanel condPanel;
    private JPanel butPanel;
    private String problemType;
    private JTextField[] goalFunc;
    private int size;
    private int conSize;

    final private String[] conds = {"=", "<=", ">="};

    public ConditionFrame(){}

    public ConditionFrame(int varNum, String problem, int numOfConditions){
        this.problemType = problem;
        this.size = varNum;
        this.conSize = numOfConditions;
        if(size <= 5 && conSize <= 5) init();
        else {
            FrameFromFile frameFromFile = new FrameFromFile();
            frameFromFile.setVisible(true);
            setVisible(false);
        }
    }

    private void init() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        condPanel = new JPanel();
        condPanel.setLayout(null);
        condPanel.setBounds(0,0, size * 60 + 100, conSize * 40 + 80);

        butPanel = new JPanel();
        butPanel.setLayout(null);
        butPanel.setBounds(condPanel.getWidth(), 0, 120, condPanel.getHeight());

        System.out.println(condPanel.getWidth());
        printXs(size, conSize);
        conditions = printConditions(conSize);
        odds = printOdds(size, conSize);
        rightCol = printRightCol(conSize);
        printXsFun(size);
        goalFunc = printGoalFun(size);

        setBackButton(back);
        setCalculateButton(calculate);

        this.getContentPane().add(condPanel);
        this.getContentPane().add(butPanel);
        setBounds(300, 300, condPanel.getWidth() + butPanel.getWidth() + 10, condPanel.getHeight());
        setVisible(true);
    }

    private void printXs(int size, int conSize){
        for(int i = 0; i < conSize; i++) {
            for(int j = 0; j < size; j++) {
                JLabel lab;
                if(j < (size-1)) {
                    lab = new JLabel("x" + j + "+");
                } else {
                    lab = new JLabel("x" + j);
                }
                lab.setBounds(40 + j * 60, 20 + i * 35, 30, 30);
                condPanel.add(lab);
            }
        }
    }

    private void printXsFun(int size){
        JLabel label = new JLabel("F(xn) =");
        label.setBounds(10, condPanel.getHeight() - 70, 50, 30);
        condPanel.add(label);

        for(int i = 0; i < size; i++){
            JLabel lab;
            if(i < (size-1)) {
                lab = new JLabel("x" + i + "+");
            } else {
                lab = new JLabel("x" + i);
            }
            lab.setBounds(90 + i * 60, condPanel.getHeight() - 70, 30, 30);
            condPanel.add(lab);
        }
    }

    private JTextField[][] printOdds(int size, int conSize){
        JTextField[][] result = new JTextField[conSize][size];
        for(int i = 0; i < conSize; i++){
            for(int j = 0; j < size; j++){
                JTextField field = new JTextField("0");
                field.setBounds(10 + j * 60, 20 + i* 35, 25, 25);
                condPanel.add(field);
                result[i][j] = field;
            }
        }
        return result;
    }
    private JTextField[] printGoalFun(int size){
        JTextField[] result = new JTextField[size];
        for(int i = 0; i < size; i++){
            JTextField field = new JTextField("0");
            field.setBounds(60 + i * 60,  condPanel.getHeight() - 70 , 25, 25);
            condPanel.add(field);
            result[i] = field;
        }
        return result;
    }

    private JComboBox[] printConditions(int conSize){
        JComboBox[] result = new JComboBox[conSize];
        for(int i = 0; i < conSize; i++){
            JComboBox comboBox = new JComboBox(conds);
            comboBox.setBounds( 60 * size, 20 + i* 35, 50, 25);
            condPanel.add(comboBox);
            result[i] = comboBox;
        }
        return result;
    }

    private JTextField[] printRightCol(int conSize){
        JTextField[] result = new JTextField[conSize];
        for(int i = 0; i < conSize; i++){
            JTextField field = new JTextField("0");
            field.setBounds( 60 * size + 60, 20 + i* 35, 25, 25);
            condPanel.add(field);
            result[i] = field;
        }
        return result;
    }

    private void setBackButton(JButton back){
        back = new JButton("Назад");
        back.setBounds(20,20, 90, 25);
        butPanel.add(back);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                MainFrame frame = new MainFrame();
            }
        });
    }
    private void setCalculateButton(JButton calculate) {
        calculate = new JButton("Расчет");
        calculate.setBounds(20,55 , 90, 25);
        butPanel.add(calculate);

        calculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(checkFields(odds,rightCol)){
                    System.out.println("CALCULATE");
                    Solver solver = new Solver(size, odds, rightCol, conditions,problemType, goalFunc, conSize);
                    System.out.println(solver.result);
                    setVisible(false);
                    ResultFrame resultFrame = new ResultFrame(solver.result, size);
                    resultFrame.setVisible(true);
                }

            }
        });
    }

    private boolean checkFields(JTextField[][] odds, JTextField[] rightCol){
        boolean result = true;
        try{
            for(JTextField[] fields : odds){
                for(JTextField field : fields){
                    double num = Double.parseDouble(field.getText());
                }
            }
            for(JTextField field : rightCol){
                double num = Double.parseDouble(field.getText());
            }
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Заполните все поля корректно!");
            result = false;
        }

        return result;
    }
}

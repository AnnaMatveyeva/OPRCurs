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

    private int size;
    final private String[] conds = {">", "<", "=", "<=", ">="};

    public ConditionFrame(){}

    public ConditionFrame(int varNum, String problem){
        this.problemType = problem;
        this.size = varNum;
        if(size <= 5) init();
        else initNoVis();
    }

    private void init() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        condPanel = new JPanel();
        condPanel.setLayout(null);
        condPanel.setBounds(0,0, size * 60 + 90, size * 40 + 40);

        butPanel = new JPanel();
        butPanel.setLayout(null);
        butPanel.setBounds(condPanel.getWidth(), 0, 120, condPanel.getHeight());

        System.out.println(condPanel.getWidth());
        printXs(size);
        conditions = printConditions(size);
        odds = printOdds(size);
        rightCol = printRightCol(size);

        setBackButton(back);
        setCalculateButton(calculate);

        this.getContentPane().add(condPanel);
        this.getContentPane().add(butPanel);
        setBounds(300, 300, condPanel.getWidth() + butPanel.getWidth(), condPanel.getHeight());
        setVisible(true);
    }


    private void initNoVis(){

    }

    private void printXs(int size){
        for(int i = 0; i < size; i++) {
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


    private JTextField[][] printOdds(int size){
        JTextField[][] result = new JTextField[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                JTextField field = new JTextField("0");
                field.setBounds(10 + j * 60, 20 + i* 35, 25, 25);
                condPanel.add(field);
                result[i][j] = field;
            }
        }
        return result;
    }

    private JComboBox[] printConditions(int size){
        JComboBox[] result = new JComboBox[size];
        for(int i = 0; i < size; i++){
            JComboBox comboBox = new JComboBox(conds);
            comboBox.setBounds( 60 * size, 20 + i* 35, 50, 25);
            condPanel.add(comboBox);
            result[i] = comboBox;
        }
        return result;
    }

    private JTextField[] printRightCol(int size){
        JTextField[] result = new JTextField[size];
        for(int i = 0; i < size; i++){
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
                    Solver solver = new Solver(size, odds, rightCol, conditions,problemType);
                    System.out.println(solver.result);
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

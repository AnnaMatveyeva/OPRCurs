package matveyeva.opr;


import net.sf.javailp.*;

import javax.swing.*;
import java.util.ArrayList;

public class Solver {
    private Problem problem;
    static final SolverFactory factory = new SolverFactoryLpSolve();
    public Result result;

    public Solver(ArrayList<String> arr){

    }

    public Solver(int size, JTextField[][] odds, JTextField[] rightCol, JComboBox[] conditions,
                  String problemType, JTextField[] goalFun, int conSize){
        problem = new Problem();

        Linear linear = new Linear();
        for(int i = 0; i < goalFun.length; i++){
            double coef = Double.parseDouble(goalFun[i].getText());
            linear.add(coef, "x" + i);
        }

        if(problemType.contains("Minimize")) problem.setObjective(linear, OptType.MIN);
            else problem.setObjective(linear, OptType.MAX);

        solve(size, odds, rightCol, conditions, conSize);
    }

    public void solve(int size, JTextField[][] odds, JTextField[] rightCol, JComboBox[] conditions, int conSize) {

        for(int i = 0; i< conSize; i++){

            problem.setVarType("x" + i, Integer.class);

            Linear linear = new Linear();
            for(int j = 0; j < size; j++){
                double odd = Double.parseDouble(odds[i][j].getText());
                linear.add(odd, "x"+ j);
            }

            String condition = conditions[i].getSelectedItem().toString();
            double rightOdd = Double.parseDouble(rightCol[i].getText());

            problem.add(linear,condition,rightOdd);
        }

        net.sf.javailp.Solver solver = factory.get();
        result = solver.solve(problem);

    }

}

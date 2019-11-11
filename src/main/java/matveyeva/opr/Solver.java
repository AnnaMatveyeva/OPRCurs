package matveyeva.opr;


import net.sf.javailp.*;

import javax.swing.*;

public class Solver {
    private String problemType;
    private Problem problem;
    static final SolverFactory factory = new SolverFactoryLpSolve();
    public Result result;

    public Solver(int size, JTextField[][] odds, JTextField[] rightCol, JComboBox[] conditions, String problemType){
        problem = new Problem();

        if(problemType.contains("Minimize")) problem.setOptimizationType(OptType.MIN);
            else problem.setOptimizationType(OptType.MAX);

        solve(size, odds, rightCol, conditions);
    }

    public void solve(int size, JTextField[][] odds, JTextField[] rightCol, JComboBox[] conditions) {

        for(int i = 0; i< size; i++){
            Linear linear = new Linear();
            for(int j = 0; j < size; j++){
                double odd = Double.parseDouble(odds[i][j].getText());
                linear.add(odd, "x"+ j);
            }

            String condition = conditions[i].getSelectedItem().toString();
            double rightOdd = Double.parseDouble(rightCol[i].getText());

            problem.setObjective(linear);
            problem.add(linear,condition,rightOdd);
            problem.setVarType("x" + i, Integer.class);
        }
        net.sf.javailp.Solver solver = factory.get();
        result = solver.solve(problem);

    }

}

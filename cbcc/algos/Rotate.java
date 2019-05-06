package cbcc.algos;

import cbcc.structures.Ponto;
import cbcc.util.MatrixOpr;
import cbcc.util.TelaBuffer;

import javax.swing.*;
import java.util.ArrayList;

public class Rotate {
    private int colorVal;
    public TelaBuffer process(int colorVal, TelaBuffer tb){
        this.colorVal = colorVal;
        String message = "Informe ângulo em graus\n\nExemplo: \"30\"";
        String input = JOptionPane.showInputDialog(null, message);
        double m = 0;
        try {
            m = Math.toRadians(Double.parseDouble(input));
        }
        catch(NumberFormatException E){
            E.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Erro ao processar entrada.",null, JOptionPane.ERROR_MESSAGE);
            return tb;
        }
        ArrayList<ArrayList<Ponto>> formas = tb.getFormas();
        ArrayList<String> algos = tb.getOrder();
        double[][] rot = {
                {Math.cos(m), -Math.sin(m)},
                {Math.sin(m),  Math.cos(m)}
        };
        tb.allPxZero();
        tb.toggleRegister();
        int stop = algos.size();
        for(int i = 0; i < stop; i++){
            if(algos.get(i).equals("Bresenham")){
                for(Ponto p : formas.get(i)){
                    double[][] ponto = {
                            {p.getX()},
                            {p.getY()}
                    };
                    double[][] pt = new MatrixOpr().mult(rot,ponto);
                    tb.setPT(this.colorVal, (int)(pt[0][0]), (int)(pt[1][0]));
                }
                tb = new Bresenham(this.colorVal).process(tb);
            }
            if(algos.get(i).equals("Circle")){
                for(Ponto p : formas.get(i)){
                    double[][] ponto = {
                            {p.getX()},
                            {p.getY()}
                    };
                    double[][] pt = new MatrixOpr().mult(rot,ponto);
                    tb.setPT(this.colorVal, (int)(pt[0][0]), (int)(pt[1][0]));
                }
                tb = new Circle().process(this.colorVal, tb);
            }
            if(algos.get(i).equals("Bezier")){
                for(Ponto p : formas.get(i)){
                    double[][] ponto = {
                            {p.getX()},
                            {p.getY()}
                    };
                    double[][] pt = new MatrixOpr().mult(rot,ponto);
                    tb.setPT(this.colorVal, (int)(pt[0][0]), (int)(pt[1][0]));
                }
                tb = new Bezier().process(this.colorVal, tb);
            }
            if(algos.get(i).equals("RecurFill")){
                for(Ponto p : formas.get(i)){
                    double[][] ponto = {
                            {p.getX()},
                            {p.getY()}
                    };
                    double[][] pt = new MatrixOpr().mult(rot,ponto);
                    tb.setPT(this.colorVal, (int)(pt[0][0]), (int)(pt[1][0]));
                }
                tb = new RecurFill().process(this.colorVal, tb);
            }
        }
        tb.toggleRegister();

        return tb;
    }
}

package cbcc.algos;

import cbcc.structures.Ponto;
import cbcc.util.TelaBuffer;

import javax.swing.*;
import java.util.ArrayList;

public class Translada {
    private int colorVal;
    public TelaBuffer process(int colorVal, TelaBuffer tb){
        this.colorVal = colorVal;
        String message = "Informe x e y separados por vírgula\n\nExemplo: \"3,5\"";
        String input = JOptionPane.showInputDialog(null, message);
        double xm = 0;
        double ym = 0;
        try {
            xm = Double.parseDouble(input.split(",")[0]);
            ym = Double.parseDouble(input.split(",")[1]);
        }
        catch(NumberFormatException E){
            E.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Erro ao processar entrada.",null, JOptionPane.ERROR_MESSAGE);
            return tb;
        }
        ArrayList<ArrayList<Ponto>> formas = tb.getFormas();
        ArrayList<String> algos = tb.getOrder();
        tb.allPxZero();
        tb.toggleRegister();
        int stop = algos.size();
        for(int i = 0; i < stop; i++){
            if(algos.get(i).equals("Bresenham")){
                for(Ponto p : formas.get(i)){
                    tb.setPT(this.colorVal, (int)(p.getX()+xm), (int)(p.getY()+ym));
                }
                tb = new Bresenham(this.colorVal).process(tb);
            }
            if(algos.get(i).equals("Circle")){
                for(Ponto p : formas.get(i)){
                    tb.setPT(this.colorVal, (int)(p.getX()+xm), (int)(p.getY()+ym));
                }
                tb = new Circle().process(this.colorVal, tb);
            }
            if(algos.get(i).equals("Bezier")){
                for(Ponto p : formas.get(i)){
                    tb.setPT(this.colorVal, (int)(p.getX()+xm), (int)(p.getY()+ym));
                }
                tb = new Bezier().process(this.colorVal, tb);
            }
            if(algos.get(i).equals("RecurFill")){
                for(Ponto p : formas.get(i)){
                    tb.setPT(this.colorVal, (int)(p.getX()+xm), (int)(p.getY()+ym));
                }
                tb = new RecurFill().process(this.colorVal, tb);
            }
        }
        tb.toggleRegister();
        return tb;
    }
}

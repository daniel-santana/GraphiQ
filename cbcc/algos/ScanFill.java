package cbcc.algos;

import cbcc.structures.Ponto;
import cbcc.structures.Reta;
import cbcc.util.TelaBuffer;
import java.util.ArrayList;

public class ScanFill {
    private int colorVal;
    public TelaBuffer process(int colorVal, TelaBuffer tb){
        this.colorVal = colorVal;
        ArrayList<ArrayList<Ponto>> formas = tb.getFormas();
        for(int i = 0; i < formas.size(); i++){
            new PolyData(formas.get(i));
        }
        return tb;
    }
    private class PolyData{
        private PolyData(ArrayList<Ponto> poli){
            double[][] table = makeTable(poli);
            //set boundaries
            double minY = poli.get(0).getY();
            double maxY = poli.get(0).getY();
            double minX = poli.get(0).getX();
            double maxX = poli.get(0).getX();
            for(Ponto p: poli){
                if(p.getY() > maxY) maxY = p.getY();
                if(p.getY() < minY) minY = p.getY();
                if(p.getX() > maxX) maxX = p.getX();
                if(p.getX() < minX) minX = p.getX();
            }
            //scan lines
            for(double i = minY + 1; i < maxY; i++){
                System.out.printf("SCANNING LINE: %d\n", (int)i);
                for(int l = 0; l < table.length; l++){
                    double x = 0;
                    if(i >= table[l][1] && i <= table[l][2]) {
                        //System.out.printf("\tSIDE %d: TOUCH %f\n", l+1, );
                        x = calcX(table[l][4], i, minY, minX);
                    }
                }
            }
        }
        private double[][] makeTable(ArrayList<Ponto> forma){
            int lados = forma.size()-1;
            int colun = 5;
            double[][] table = new double[lados][colun];
            for(int i = 0; i < lados; i++){
                Reta r = new Reta(forma.get(i), forma.get(i+1));
                double m = new Bresenham(0).calcM(r);
                double Ymin = r.getPi().getY();
                double Ymax = r.getPf().getY();
                double XpYm = r.getPi().getX();
                if(r.getPi().getY() > r.getPf().getY()) {
                    Ymin = r.getPf().getY();
                    Ymax = r.getPi().getY();
                    XpYm = r.getPf().getX();
                }
                table[i][0] = i + 1.0;//lado
                table[i][1] = Ymin;
                table[i][2] = Ymax;
                table[i][3] = XpYm;
                table[i][4] = m;
                //if(m == 0.0) table[i][4] = 0.0;
            }
            return table;
        }
        private double calcX(double m, double varrY, double minY, double minX) {
            double x = (1/m)*(varrY - minY) + minX;
            //if(x < 0) x =
            return x;
        }
    }
}

package cbcc.algos;

import cbcc.structures.Ponto;
import cbcc.util.TelaBuffer;

import java.util.ArrayList;

public class Bezier {
    private int val;
    public TelaBuffer process(int val, TelaBuffer tb){
        this.val = val;
        ArrayList<Ponto> pts = tb.newForma("Bezier");
        for(int i = 0; i < pts.size()-2; i += 2){
            tb = makeBezier(tb, new ArrayList<>(pts.subList(i,i+3)));
        }
        return tb;
    }
    private TelaBuffer makeBezier(TelaBuffer tb, ArrayList<Ponto> pts){
        double step = 0.000001;
        for(double t = 0; t < 1; t+= step){
            for(int r = 0; r <= pts.size(); r++){
                for(int i = 0; i < pts.size() - r - 1; i++){
                    pts.set(i,ptSum(ptMul(pts.get(i),(1-t)),ptMul(pts.get(i+1),t)));
                }
            }
            tb.drawPX(this.val, (int)pts.get(0).getX(), (int)pts.get(0).getY());
        }
        return tb;
    }
    private Ponto ptSum(Ponto p1, Ponto p2){
        return new Ponto(p1.getX()+p2.getX(),p1.getY()+p2.getY());
    }
    private Ponto ptMul(Ponto p, double val){
        return new Ponto(p.getX()*val, p.getY()*val);
    }
}

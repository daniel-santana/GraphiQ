package cbcc.algos;

import cbcc.structures.Ponto;
import cbcc.structures.Reta;
import cbcc.util.TelaBuffer;
import java.util.ArrayList;

public class Bresenham {
    private int val;
    public Bresenham(int val){
        this.val = val;
    }
    public TelaBuffer process(TelaBuffer tb){
        ArrayList<Ponto> pontos = tb.newForma("Bresenham");
        for(int i = 0; i < pontos.size() - 1; i++){
            tb = makeReta(tb, new Reta(pontos.get(i), pontos.get(i+1)));
        }
        return tb;
    }
    private TelaBuffer makeReta(TelaBuffer tb, Reta r){
        ArrayList<Ponto> pts = new ArrayList<>();
        RetaData rd = this.reflex(r);
        r = rd.r;
        double m = this.calcM(r);
        double e = m - 0.5;
        double inicioX = r.getPi().getX();
        double inicioY = r.getPi().getY();
        double fim = r.getPf().getX() - 1;
        while(inicioX < fim) {
            if (e >= 0) {
                inicioY++;
                e--;
            }
            inicioX++;
            e += m;
            pts.add(new Ponto(inicioX, inicioY));
        }
        pts = this.deflex(pts, rd);
        for(Ponto p : pts) {
            tb.drawPX(this.val,(int)p.getX(),(int)p.getY());
        }
        return tb;
    }
    private RetaData reflex(Reta r){
        double m = this.calcM(r);
        boolean trocaxy = false;
        boolean trocax = false;
        boolean trocay = false;
        if(m > 1 || m < -1){
            Reta temp = new Reta(
                    new Ponto(r.getPi().getY(), r.getPi().getX()),
                    new Ponto(r.getPf().getY(), r.getPf().getX())
            );
            r = temp;
            trocaxy = true;
        }
        if(r.getPi().getX() > r.getPf().getX()){
            Reta temp = new Reta(
                    new Ponto(-r.getPi().getX(), r.getPi().getY()),
                    new Ponto(-r.getPf().getX(), r.getPf().getY())
            );
            r = temp;
            trocax = true;
        }
        if(r.getPi().getY() > r.getPf().getY()){
            Reta temp = new Reta(
                    new Ponto(r.getPi().getX(), -r.getPi().getY()),
                    new Ponto(r.getPf().getX(), -r.getPf().getY())
            );
            r = temp;
            trocay = true;
        }
        return new RetaData(r, trocax, trocay, trocaxy);
    }
    private ArrayList<Ponto> deflex(ArrayList<Ponto> pts, RetaData rd){
        ArrayList<Ponto> out = new ArrayList<>();
        for(Ponto p : pts){
            Ponto temp = new Ponto(p.getX(),p.getY());
            if(rd.tx)
                temp.setX(-temp.getX());
            if(rd.ty)
                temp.setY(-temp.getY());
            if(rd.txy) {
                double tmpX = temp.getX();
                temp.setX(temp.getY());
                temp.setY(tmpX);
            }
            out.add(temp);
        }
        return out;
    }
    public double calcM(Reta r){
        double dx = r.getPf().getX() - r.getPi().getX();
        double dy = r.getPf().getY() - r.getPi().getY();
        if(dx != 0.0) return dy/dx;
        return dy;
    }
    private class RetaData{
        boolean tx;
        boolean ty;
        boolean txy;
        Reta r;
        RetaData(Reta r, boolean trocax, boolean trocay, boolean trocaxy){
            //System.out.printf("\t\tRetaData: r:(%d, %d) (%d, %d) tx: %b ty: %b txy: %b\n",
            //        (int)r.getPi().getX(),(int)r.getPi().getY(),(int)r.getPf().getX(),(int)r.getPf().getY(),
            //        trocax,trocay,trocaxy
            //);
            this.r = r;
            this.tx = trocax;
            this.ty = trocay;
            this.txy = trocaxy;
        }
    }
}

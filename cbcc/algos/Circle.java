package cbcc.algos;

import cbcc.structures.Ponto;
import cbcc.util.TelaBuffer;
import java.util.ArrayList;

public class Circle {
    private int val;
    public TelaBuffer process(int val, TelaBuffer tb){
        this.val = val;
        ArrayList<Ponto> pts = tb.newForma("Circle");
        for(int i = 0; i < pts.size() - 1; i++){
            double dx = Math.pow((pts.get(i).getX() - pts.get(i+1).getX()) , 2);
            double dy = Math.pow((pts.get(i).getY() - pts.get(i+1).getY()) , 2);
            //tb.drawPX(0,(int)pts.get(i).getX(),(int)pts.get(i).getY());
            //tb.drawPX(0,(int)pts.get(i+1).getX(),(int)pts.get(i+1).getY());
            double raio = Math.sqrt(dx+dy);
            makeCircle(tb, pts.get(i), (int)(raio+0.9));
        }
        return tb;
    }
    private TelaBuffer makeCircle(TelaBuffer tb, Ponto p1, int raio){
        ArrayList<Ponto> pts = new ArrayList<>();
        double xises = 0;
        double ypses = raio;
        double step = 1 - raio;
        while(xises <= ypses){
            pts.add(new Ponto(xises, ypses));
            xises++;
            if(step < 0) step += 2*xises+3;
            else {
                ypses -= 1;
                step += 2*xises-2*ypses+5;
            }
        }
        int loop = pts.size();
        for(int i = 0; i < loop; i++) {
            Ponto p = pts.get(i);
            pts.add(new Ponto( p.getX(), -p.getY()));
            pts.add(new Ponto(-p.getX(),  p.getY()));
            pts.add(new Ponto(-p.getX(), -p.getY()));
            pts.add(new Ponto( p.getY(),  p.getX()));
            pts.add(new Ponto( p.getY(), -p.getX()));
            pts.add(new Ponto(-p.getY(),  p.getX()));
            pts.add(new Ponto(-p.getY(), -p.getX()));
        }
        for(Ponto p : pts) {
            tb.drawPX(this.val, (int)(p.getX()+p1.getX()), (int)(p.getY()+p1.getY()));
        }
        return tb;
    }
}

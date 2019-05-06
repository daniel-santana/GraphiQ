package cbcc.example;

import cbcc.algos.Bresenham;
import cbcc.structures.Ponto;
import cbcc.util.MatrixOpr;
import cbcc.util.TelaBuffer;
import java.util.ArrayList;

public class Ortographic {
    private Polygon polygon;
    private int colorVal;
    private double cavalier = 1.0;
    private double cabinet  = 0.5;
    private double factor = cavalier;
    private double[][] obliQ= {
            {1,0,-1,0},
            {0,1,-1,0},
            {0,0, 0,0},
            {0,0, 0,1}};
    public Ortographic(int colorVal){
        this.colorVal = colorVal;
        this.polygon = new SampleCube().getSampleCube();
    }
    public TelaBuffer sample(double rot, TelaBuffer tb){
        this.obliQ[0][2] = this.factor*Math.cos(Math.toRadians(rot));
        this.obliQ[1][2] = this.factor*Math.sin(Math.toRadians(rot));
        ArrayList<ArrayList<Ponto>> arestas = new ArrayList<>();
        for(int layer = 0; layer < 2; layer++) {
            ArrayList<Ponto> pArr = new ArrayList<>();
            for (int i = layer * polygon.getPQtd() / 2; i < (polygon.getPQtd() / (2 - layer)) - 1; i += 2) {
                double[][] pto1 = {
                        {this.polygon.getX(i)},
                        {this.polygon.getY(i)},
                        {this.polygon.getZ(i)},
                        {1}
                };
                double[][] pto2 = {
                        {this.polygon.getX(i + 1)},
                        {this.polygon.getY(i + 1)},
                        {this.polygon.getZ(i + 1)},
                        {1}
                };
                double[][] novoPto1 = new MatrixOpr().mult(this.obliQ, pto1);
                double[][] novoPto2 = new MatrixOpr().mult(this.obliQ, pto2);
                pArr.add(new Ponto(novoPto1[0][0], novoPto1[1][0]));
                pArr.add(new Ponto(novoPto2[0][0], novoPto2[1][0]));
                arestas.add(pArr);
                tb = this.drawClosed(tb, pArr.toArray(new Ponto[pArr.size()]));
            }
            //desenha primeiro o poligono da frente, então o do fundo
            tb = new Bresenham(this.colorVal).process(tb);
        }
        tb = this.drawArestas(tb, arestas);
        return tb;
    }
    private TelaBuffer drawClosed(TelaBuffer tb, Ponto[] form){
        //desenha o plano frontal separado do traseiro
        for(int i = 0; i < form.length-1; i+=2){
            tb.setPT(this.colorVal, (int)form[i].getX(), (int)form[i].getY());
            tb.setPT(this.colorVal, (int)form[i+1].getX(), (int)form[i+1].getY());
        }
        tb.setPT(this.colorVal, (int)form[form.length-1].getX(), (int)form[0].getY());
        return tb;
    }
    private TelaBuffer drawArestas(TelaBuffer tb, ArrayList<ArrayList<Ponto>> faces){
        //conecta o plano frontal com o traseiro
        for(int f1 = 0; f1 < faces.size()/2; f1++){
            int f2 = (faces.size()/2) + f1;
            ArrayList<Ponto> temp1 = faces.get(f1);
            ArrayList<Ponto> temp2 = faces.get(f2);
            for(int j = 0; j < temp1.size(); j++) {
                double p1x = temp1.get(j).getX();
                double p1y = temp1.get(j).getY();
                double p2x = temp2.get(j).getX();
                double p2y = temp2.get(j).getY();
                tb.setPT(this.colorVal, (int)p1x, (int)p1y);
                tb.setPT(this.colorVal, (int)p2x, (int)p2y);
                //conecta o polígono da frente com o de fundo ponto por ponto
                tb = new Bresenham(this.colorVal).process(tb);
            }
        }
        return tb;
    }
}

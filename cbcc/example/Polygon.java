package cbcc.example;

import cbcc.structures.Ponto3D;

/*
* Referência para pontos de Cubo
*
*    p5---------p6
*   /|         / |
* p1---------p2  |
*  | |        |  |
*  | p8-------|-p7
*  |/         |/
* p4---------p3
*
* */

public class Polygon {
    private Ponto3D[] pontos;
    public Polygon(int cPts, double[] xs, double[] ys, double[] zs){
        this.pontos = new Ponto3D[cPts];
        for(int i = 0; i < cPts; i++){
            this.pontos[i] = new Ponto3D(xs[i], ys[i], zs[i]);
        }
    }
    public int getPQtd(){
        return this.pontos.length;
    }
    public double getX(int p){
        return this.pontos[p].getX();
    }
    public double getY(int p){
        return this.pontos[p].getY();
    }
    public double getZ(int p){
        return this.pontos[p].getZ();
    }
}

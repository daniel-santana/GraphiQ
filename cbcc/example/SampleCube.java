package cbcc.example;

/*
 * Referência para pontos de Cubo
 *
 *    20,20,20---------40,20,20
 *   /|               / |
 * 20,20,15---------40,20,15
 *  | |              |  |
 *  | 20,40,20-------|-40,40,20
 *  |/               |/
 * 20,40,15---------40,40,15
 *
 * */

public class SampleCube {
    public Polygon getSampleCube() {
        double[] xs = {20, 40, 40, 20, 20, 40, 40, 20};
        double[] ys = {20, 20, 40, 40, 20, 20, 40, 40};
        double[] zs = {12, 12, 12, 12, 20, 20, 20, 20};
        return new Polygon(8, xs, ys, zs);
    }
}

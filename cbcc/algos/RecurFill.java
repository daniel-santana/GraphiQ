package cbcc.algos;

import cbcc.structures.Ponto;
import cbcc.util.TelaBuffer;
import java.util.ArrayList;

public class RecurFill {
    private int val;
    public TelaBuffer process(int val, TelaBuffer tb){
        this.val = val;
        ArrayList<Ponto> pts = tb.newForma("RecurFill");
        for(Ponto p : pts){
            tb = this.fill(tb, p);
        }
        return tb;
    }
    private TelaBuffer fill(TelaBuffer tb, Ponto p) {
        int x = (int)p.getX();
        int y = (int)p.getY();
        tb.drawPX(this.val, x, y);
        int[][] bff = tb.getBff();
        //shrunk screen to stop stackOverflow. It's recursive after all
        if (x < bff[0].length - 1 && bff[y][x + 1] == 0)
                tb = this.fill(tb, new Ponto((double) (x + 1), (double) (y)));
        if (y < bff.length - 1 && bff[y + 1][x] == 0)
                tb = this.fill(tb, new Ponto((double) (x), (double) (y + 1)));
        if (x > 0 && bff[y][x - 1] == 0)
                tb = this.fill(tb, new Ponto((double) (x - 1), (double) (y)));
        if (y > 0 && bff[y - 1][x] == 0)
                tb = this.fill(tb, new Ponto((double) (x), (double) (y - 1)));
        return tb;
    }
}

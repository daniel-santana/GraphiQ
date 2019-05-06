package cbcc.algos;

import cbcc.util.TelaBuffer;

public class CutLine {
    private int colorVal;
    private int bound = 4;
    public TelaBuffer process(int colorVal, TelaBuffer tb){
        this.colorVal = colorVal;
        tb = this.paintDelimiter(tb);
        tb = this.makeCut(tb);
        return tb;
    }
    private TelaBuffer makeCut(TelaBuffer tb){
        int ylen = tb.getBff().length;
        int xlen = tb.getBff()[0].length;
        int L1Y = ylen/bound;
        int L1X = xlen/bound;
        int L2Y = (this.bound-1)*ylen/this.bound;
        int L2X = (this.bound-1)*xlen/this.bound;

        return tb;
    }
    private TelaBuffer paintDelimiter(TelaBuffer tb){
        tb = new Bresenham(0).process(tb);//flushing points
        int ylen = tb.getBff().length;
        int xlen = tb.getBff()[0].length;
        int L1Y = ylen/bound;
        int L1X = xlen/bound;
        int L2Y = (this.bound-1)*ylen/this.bound;
        int L2X = (this.bound-1)*xlen/this.bound;
        tb.setPT(this.colorVal, L1X, L1Y);
        tb.setPT(this.colorVal, L2X, L1Y);
        tb.setPT(this.colorVal, L2X, L2Y);
        tb.setPT(this.colorVal, L1X, L2Y);
        tb.setPT(this.colorVal, L1X, L1Y);
        tb = new Bresenham(this.colorVal).process(tb);
        return tb;
    }
}

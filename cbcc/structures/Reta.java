package cbcc.structures;

public class Reta {
    private Ponto pi;
    private Ponto pf;
    public Reta(Ponto pinicial, Ponto pfinal){
        this.pi = pinicial;
        this.pf = pfinal;
        //System.out.printf("\tNova reta: (%d, %d) (%d, %d)\n", (int)pi.getX(), (int)pi.getY(), (int)pf.getX(), (int)pf.getY());
    }
    public Ponto getPi(){
        return this.pi;
    }
    public Ponto getPf() {
        return pf;
    }
}

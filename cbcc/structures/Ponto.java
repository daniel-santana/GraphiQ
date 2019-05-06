package cbcc.structures;

public class Ponto {
    private double x;
    private double y;
    public Ponto(double x, double y){
        this.x = x;
        this.y = y;
        //System.out.printf("Novo ponto: (%d, %d)\n",(int)x,(int)y);
    }
    public void setX(double val){
        this.x = val;
    }
    public void setY(double val){
        this.y = val;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
}

package cbcc.structures;

public class Ponto3D extends Ponto{
    private double z;
    public Ponto3D(double x, double y, double z){
        super(x, y);
        this.z = z;
    }
    public double getZ(){
        return this.z;
    }
}

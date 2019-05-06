package cbcc.util;

import cbcc.structures.Ponto;
import java.util.ArrayList;

public class TelaBuffer {
    private int[][] buffer;
    private ArrayList<String> sequencia;
    private ArrayList<Ponto> pontos;
    private ArrayList<ArrayList<Ponto>> formas;
    private boolean register = true;
    TelaBuffer(int resV, int resH){
        this.buffer = new int[resV+1][resH+1];
        this.clean();
    }
    void clean(){
        this.sequencia = new ArrayList<>();
        this.formas = new ArrayList<>();
        this.pontos = new ArrayList<>();
        for(int i = 0; i < this.buffer.length; i++)
            for(int j = 0; j < this.buffer[0].length; j++)
                this.buffer[i][j] = 0;
    }
    public ArrayList<Ponto> newForma(String caller){
        if(this.pontos.size() == 0) return new ArrayList<>();
        this.sequencia.add(caller);
        this.formas.add(this.pontos);
        this.pontos = new ArrayList<>();
        if(this.register) return this.formas.get(this.formas.size()-1);
        //para não duplicar itens transformados, remove as tranformações das listas
        ArrayList<Ponto> temp = this.formas.get(this.formas.size()-1);
        this.sequencia.remove(this.sequencia.size()-1);
        this.formas.remove(this.formas.size()-1);
        return temp;
    }
    public boolean toggleRegister(){
        this.register = !this.register;
        return this.register;
    }
    public ArrayList<ArrayList<Ponto>> getFormas(){
        return this.formas;
    }
    public ArrayList<String> getOrder(){
        return this.sequencia;
    }
    public void allPxZero(){
        for(int i = 0; i < this.buffer.length; i++)
            for(int j = 0; j < this.buffer[0].length; j++)
                this.buffer[i][j] = 0;
    }
    public void setPT(int val, int x, int y){
        if(x >= 0 && x < this.buffer[0].length && y >= 0 && y < this.buffer.length) {
            this.buffer[y][x] = val;
            this.pontos.add(new Ponto((double) x, (double) y));
        }
        else System.err.printf("Set point error (%d, %d): Out of canvas\n",x,y);
    }
    public void drawPX(int val, int x, int y){
        if(x >= 0 && x < this.buffer[0].length && y >= 0 && y < this.buffer.length) this.buffer[y][x] = val;
        else System.err.printf("Draw point error (%d, %d): Out of canvas\n",x,y);
    }
    public int[][] getBff(){
        return this.buffer;
    }
}

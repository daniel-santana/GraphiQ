package cbcc;

import cbcc.util.Tela;

/*
* Daniel Santos Santana - 201504940032
* Checklist
* [x]bresenham
* [x]circulo
* [x]bezier
* [x]preench rec
* [ ]preench scan
* [ ]corte linha
* [ ]corte poli
* [x]rotate
* [x]scale
* [x]translad
* [x]proj orto
* [x]proj persp
*
* */

public class Main {
    public static void main(String[] args){
        Thread THD = new Thread(new Tela());
        THD.start();
    }
}

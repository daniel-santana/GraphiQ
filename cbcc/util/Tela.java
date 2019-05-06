package cbcc.util;

import cbcc.algos.Bezier;
import cbcc.algos.Bresenham;
import cbcc.algos.Circle;
import cbcc.algos.CutLine;
import cbcc.algos.RecurFill;
import cbcc.algos.Rotate;
import cbcc.algos.Scale;
import cbcc.algos.ScanFill;
import cbcc.algos.Translada;
import cbcc.example.Ortographic;
import cbcc.example.Perspective;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tela implements Runnable{
    private TelaBuffer tb;
    private int dimension;
    private int px;
    private JSplitPane gui;
    public Tela(){
        this.dimension = 600;//screen size
        this.px = 10;//pixel size
        this.tb = new TelaBuffer((dimension/px) +1, (dimension/px) +1);//setting buffer
        this.makeGUI(1, 120, 30);
    }
    private void makeGUI(int colorVal, int bx, int by){
        //setting buttons
        String[] btNames = {
                "Bresenham","Círculo","Bezier", "Fill Recursivo",
                "Fill Scanline", "Corte Linha", "Corte Poli",
                "Rotaciona", "Escala", "Translada",
                "Proj Orto","Proj Pers", "Limpa"
        };
        JPanel buttonsP = new JPanel();
        buttonsP.setLayout(new GridLayout((int)(btNames.length*1.5), 1));
        JButton[] btns = new JButton[btNames.length];
        for(int i = 0; i < btNames.length; i++){
            btns[i] = new JButton(btNames[i]);
            buttonsP.add(btns[i]);
        }
        //setting grid
        Canvas art = new Canvas(px);
        art.setPreferredSize(new Dimension(dimension,dimension));
        art.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int pxX = e.getX()/px;
                int pxY = e.getY()/px;
                tb.setPT(colorVal,pxX,pxY);
                art.update(tb);
            }
        });
        //buttons actions
        //calling bresenham
        btns[0].addActionListener(actionEvent -> {
            updateTB(new Bresenham(colorVal).process(tb));
            art.update(tb);
        });
        //calling circle
        btns[1].addActionListener(actionEvent -> {
            updateTB(new Circle().process(colorVal, tb));
            art.update(tb);
        });
        //calling bezier
        btns[2].addActionListener(actionEvent -> {
            updateTB(new Bezier().process(colorVal, tb));
            art.update(tb);
        });
        //calling recursive fill
        btns[3].addActionListener(actionEvent -> {
            updateTB(new RecurFill().process(colorVal, tb));
            art.update(tb);
        });
        //rotaciona
        btns[btns.length-6].addActionListener(actionEvent -> {
            updateTB(new Rotate().process(colorVal, tb));
            art.clean();
            art.update(tb);
        });
        //escala
        btns[btns.length-5].addActionListener(actionEvent -> {
            updateTB(new Scale().process(colorVal, tb));
            art.clean();
            art.update(tb);
        });
        //translada x, y
        btns[btns.length-4].addActionListener(actionEvent -> {
            updateTB(new Translada().process(colorVal, tb));
            art.clean();
            art.update(tb);
        });
        //calling example ortogonal projection
        btns[btns.length-3].addActionListener(actionEvent -> {
            for(int rotation = 0; rotation <= 360; rotation+=15){
                tb.clean();
                art.clean();
                updateTB(new Ortographic(colorVal).sample((double)rotation, tb));
                art.update(tb);
                try {
                    Thread.sleep(500);
                }
                catch(InterruptedException E){
                    E.printStackTrace(System.err);
                }
            }
        });
        //calling example perspective projection
        btns[btns.length-2].addActionListener(actionEvent -> {
            for(int d = 0; d < 17; d++) {
                tb.clean();
                art.clean();
                updateTB(new Perspective(colorVal).sample(d, tb));
                art.update(tb);
                try {
                    Thread.sleep(500);
                }
                catch(InterruptedException E){
                    E.printStackTrace(System.err);
                }
            }
        });
        //calling clean all
        btns[btns.length-1].addActionListener(actionEvent -> {
            tb.clean();
            art.clean();
            art.update(tb);
        });
        //making split pane
        JSplitPane divisa = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, art, buttonsP);
        divisa.setDividerSize(7);
        divisa.setEnabled(false);
        this.gui = divisa;
    }
    private void updateTB(TelaBuffer tb){
        this.tb = tb;
    }
    public void run(){
        JFrame frame = new JFrame("GraphiQ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this.gui);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}

//my '''screen''', set image with "TelaBuffer" and send it here to be drawn
class Canvas extends JPanel{
    private int PIXEL;
    private boolean clear = false;
    private TelaBuffer tb = null;
    Canvas(int px){
        this.PIXEL = px;
    }
    void update(TelaBuffer tb){
        this.tb = tb;
        this.paintComponent(this.getGraphics());
    }
    void clean(){
        this.clear = true;
        this.paintComponent(this.getGraphics());
    }
    @Override
    public void paintComponent(Graphics g){
        if(this.clear) super.paintComponent(g);//clears screen
        this.clear = false;
        //draws grid
        int w = getSize().width;
        int h = getSize().height;
        for (int i = 0; i <= h / this.PIXEL; i++)
            g.drawLine(0, i * this.PIXEL, w, i * this.PIXEL);
        for (int i = 0; i <= w / this.PIXEL; i++)
            g.drawLine(i * this.PIXEL, 0, i * this.PIXEL, h);
        if(this.tb == null) return;
        //draws data from "TelaBuffer's" buffer
        for(int y = 0; y < this.tb.getBff().length; y++){
            for(int x = 0; x < this.tb.getBff()[0].length; x++){
               if(this.tb.getBff()[y][x] != 0) {
                    for(int subY = y*this.PIXEL; subY < (y+1)*this.PIXEL; subY++) {
                        if(this.tb.getBff()[y][x] == 1) g.setColor(Color.BLACK);
                        if(this.tb.getBff()[y][x] == 2) g.setColor(Color.RED);
                        g.drawLine(x * this.PIXEL, subY, (x + 1) * this.PIXEL, subY);
                    }
                }
            }
        }
    }
}

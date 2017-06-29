/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Frame;

import chess.Tablero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 * @author Angel
 */
public class Frame1 implements MouseListener {

    //private JDialog dialogo;
    private JFrame marco;
    private boolean selecc;
    JPanel ventana;
    private int x, y;
    Tablero tab;

    public Frame1(Tablero tab) {
        this.tab = tab;
        setup();
    }

    private void setup() {
        selecc = false;
        int n = 760;
        int altura = n;
        int anchura = n;
        ventana = new JPanel(new BorderLayout());
        marco = new JFrame("Tablero");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(altura, anchura);

        marco.setVisible(true);
        ventana.addMouseListener(this);
        ventana.setSize(altura, anchura);
        marco.add(ventana);
        
        MouseEvent e = new MouseEvent(marco, 0, 0, 1, x, y, 1, selecc);
        this.mouseClicked(e);
        this.mouseClicked(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //1er click
        if (!selecc) {
            this.x = e.getX() * 8 / ventana.getWidth();
            this.y = e.getY() * 8 / ventana.getHeight();
            this.paintSelec();
            if (this.tab.hayPieza(x, y) && this.tab.getPieza(x, y).getColor() == this.tab.getTurno()) {
                selecc = !selecc;
                comprobarMov(x, y);

            }

            //2o click
        } else {
            int xn = e.getX() * 8 / ventana.getWidth();
            int yn = e.getY() * 8 / ventana.getHeight();

            if (x != xn || y != yn) {
                this.tab.mover(x, y, xn, yn);
            }

            selecc = !selecc;
            this.paint();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void paint() {
        //this.ventana.repaint();
        Graphics g = ventana.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, ventana.getWidth(), ventana.getHeight());
        //g.setColor(Color.black);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((j + i) % 2 == 1) {
                    g.setColor(Color.gray);
                    g.fillRect(i * ventana.getWidth() / 8, j * ventana.getHeight() / 8,
                            ventana.getWidth() / 8, ventana.getHeight() / 8);
                }

                if (this.tab.hayPieza(i, j)) {
                    this.pintarPieza(i, j);
                }

            }
        }

    }

    public void paintSelec() {
        this.paint();
        Graphics g = ventana.getGraphics();
        g.setColor(new Color(200, 200, 150));
        if (tab.getTurno()) {
            g.setColor(Color.CYAN);
        }
        g.fillRect(x * ventana.getWidth() / 8, y * ventana.getHeight() / 8,
                ventana.getWidth() / 8, ventana.getHeight() / 8);
        if (this.tab.hayPieza(x, y)) {
            pintarPieza(x, y);
        }
    }

    private void pintarPieza(int i, int j) {
        /* c = Color.white;
        if ((i + j) % 2 == 1) {
            c = Color.gray;
        }*/
        Graphics g = ventana.getGraphics();
        Image image = (this.tab.getPieza(i, j)).getImageIcon().getImage();
        //img.paintIcon(ventana, g, i * ventana.getWidth() / 8, j * ventana.getHeight() /8);
        g.drawImage(image, i * ventana.getWidth() / 8 + 4, j * ventana.getHeight() / 8 + 4,
                ventana.getWidth() / 8 - 8, ventana.getHeight() / 8 - 8, marco);
    }

    private void comprobarMov(int x, int y) {
        Graphics g = ventana.getGraphics();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if (this.tab.getPieza(x, y).puedeMover(i, j)) {
                    //Rojo medio transparente
                    Color c = new Color(0, 255, 0, 50);
                    g.setColor(c);
                    g.fillRect(i * ventana.getWidth() / 8, j * ventana.getHeight() / 8,
                            ventana.getWidth() / 8, ventana.getHeight() / 8);
                }
                System.out.println("");
            }
        }
    }

}

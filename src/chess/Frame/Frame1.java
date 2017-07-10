/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Frame;

import chess.Piezas.*;
import chess.Tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Angel
 */
public class Frame1 implements MouseListener {

    private JFrame marco;
    private boolean selecc;
    JPanel ventana;
    private int x, y;
    private Tablero tab;

    public Frame1(Tablero tab) {
        this.tab = tab;
        setup();
    }

    private void setup() {
        selecc = false;
        int n = 720;
        ventana = new JPanel(new BorderLayout());
        marco = new JFrame("Tablero");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(n, n);

        marco.setVisible(true);
        ventana.addMouseListener(this);
        ventana.setSize(n, n);
        marco.add(ventana);

        MouseEvent e = new MouseEvent(marco, 0, 0, 1, x, y, 1, selecc);
        this.mouseClicked(e);
        this.mouseClicked(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (!selecc) {
            doFirstClick(e);
        } else {
            doSecondClick(e);
        }
    }

    private void doFirstClick(MouseEvent e) {
        this.x = e.getX() * Tablero.OCHO / ventana.getWidth();
        this.y = e.getY() * Tablero.OCHO / ventana.getHeight();
        this.paintSelec();
        if (this.tab.hayPieza(x, y) && (this.tab.getPieza(x, y).getColor() == chess.Piezas.Color.RESOURCE_CON_COLOR) == this.tab.getTurno()) {
            selecc = !selecc;
            comprobarMov(x, y);
        }
    }

    private void doSecondClick(MouseEvent e) {
        int xn = e.getX() * Tablero.OCHO / ventana.getWidth();
        int yn = e.getY() * Tablero.OCHO / ventana.getHeight();

        if (x != xn || y != yn) {
            this.tab.mover(x, y, xn, yn);
        }

        selecc = !selecc;
        this.paint();
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

    private void paint() {
        Graphics g = ventana.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, ventana.getWidth(), ventana.getHeight());
        for (int i = 0; i < Tablero.OCHO; i++) {
            for (int j = 0; j < Tablero.OCHO; j++) {
                this.pintarCasilla(i, j);
            }
        }

    }

    private void pintarCasilla(int i, int j) {
        if ((j + i) % 2 == 1) {
            Graphics g = ventana.getGraphics();
            g.setColor(Color.gray);
            g.fillRect(i * ventana.getWidth() / Tablero.OCHO, j * ventana.getHeight() / Tablero.OCHO,
                    ventana.getWidth() / Tablero.OCHO, ventana.getHeight() / Tablero.OCHO);
        }

        if (this.tab.hayPieza(i, j)) {
            this.pintarPieza(i, j);
        }
    }

    private void paintSelec() {
        this.paint();
        Graphics g = ventana.getGraphics();
        //Brown
        g.setColor(new Color(200, 200, 150));
        if (tab.getTurno()) {
            g.setColor(Color.CYAN);
        }
        g.fillRect(x * ventana.getWidth() / Tablero.OCHO, y * ventana.getHeight() / Tablero.OCHO,
                ventana.getWidth() / Tablero.OCHO, ventana.getHeight() / Tablero.OCHO);
        if (this.tab.hayPieza(x, y)) {
            pintarPieza(x, y);
        }
    }

    private void pintarPieza(int i, int j) {

        Graphics g = ventana.getGraphics();
        int corrector = 4;
        Image image = (this.tab.getPieza(i, j)).getImageIcon().getImage();
        g.drawImage(image, i * ventana.getWidth() / Tablero.OCHO + corrector, j * ventana.getHeight() / Tablero.OCHO + corrector,
                ventana.getWidth() / Tablero.OCHO - 2 * corrector, ventana.getHeight() / Tablero.OCHO - 2 * corrector, marco);
    }

    private void comprobarMov(int x, int y) {
        Graphics g = ventana.getGraphics();
        for (int j = 0; j < Tablero.OCHO; j++) {
            for (int i = 0; i < Tablero.OCHO; i++) {
                if (this.tab.getPieza(x, y).puedeMover(i, j)) {
                    //low green
                    Color c = new Color(0, 255, 0, 50);
                    g.setColor(c);
                    g.fillRect(i * ventana.getWidth() / Tablero.OCHO, j * ventana.getHeight() / Tablero.OCHO,
                            ventana.getWidth() / Tablero.OCHO, ventana.getHeight() / Tablero.OCHO);
                }
                System.out.println("");
            }
        }
    }

}

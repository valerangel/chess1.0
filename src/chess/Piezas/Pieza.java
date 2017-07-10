/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Piezas;

import chess.Tablero;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * @author Angel
 */
public abstract class Pieza {

    protected Tablero tab;
    protected int corX;
    protected int corY;
    protected Color color;
    protected ImageIcon img;
    protected boolean enroque;

    public Pieza(Tablero tab, int corX, int corY, Color color) {
        this.tab = tab;
        this.corX = corX;
        this.corY = corY;
        this.color = color;
    }

    public abstract boolean puedeMover(int corX, int corY);

    public abstract boolean puedeMoverSA(int corX, int corY);

    public void mover(int corX, int corY) {
        enroque = corX - this.corX == 2;
        this.corX = corX;
        this.corY = corY;
    }

    public boolean mismoColor(Pieza p) {
        if (p != null) {
            if (this.color == p.getColor()) {
                return true;
            }
        }
        return false;
    }

    public Color getColor() {
        return this.color;
    }

    public ImageIcon getImageIcon() {
        return this.img;
    }

    public abstract Pieza copia(Tablero tab);

    public boolean reyAmenazado(int x, int y) {
        Tablero tabl = this.tab.getCopiaTablero();
        tabl.moverSA(this.corX, this.corY, x, y);
        //Si es blanca
        int x1, y1;
        if (this.color == Color.RESOURCE_CON_COLOR) {
            x1 = tabl.getBrey()[0];
            y1 = tabl.getBrey()[1];
        } else {
            x1 = tabl.getNrey()[0];
            y1 = tabl.getNrey()[1];
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tabl.hayPieza(i, j) && tabl.getPieza(i, j).puedeMoverSA(x1, y1)) {
                    return true;
                }
            }
        }
        return false;
    }
}

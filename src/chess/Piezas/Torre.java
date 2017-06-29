/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Piezas;

import chess.Tablero;

/**
 *
 * @author Angel
 */
public class Torre extends Pieza {

    public Torre(Tablero tab, int corX, int corY, boolean color) {
        super(tab, corX, corY, color);
        //blanca
        if (color) {
            img = new javax.swing.ImageIcon(getClass().getResource("/icon/btor.png"));
            //negra
        } else {
            img = img = new javax.swing.ImageIcon(getClass().getResource("/icon/ntor.png"));
        }
    }

    @Override
    public boolean puedeMover(int corX, int corY) {
        if (this.corX == corX && this.corY == corY) {
            return false;
        }
        if (this.mismoColor(this.tab.getPieza(corX, corY))) {
            return false;
        }

        if (this.reyAmenazado(corX, corY)) {
            return false;
        }
        int My = Math.max(this.corY, corY);
        int Mx = Math.max(this.corX, corX);
        int mx = Math.min(this.corX, corX);
        int my = Math.min(this.corY, corY);

        if (My == my) {
            for (int j = mx + 1; j < Mx; j++) {
                if (this.tab.hayPieza(j, corY)) {
                    return false;
                }
            }
            return true;
        }
        if (Mx == mx) {
            for (int j = my + 1; j < My; j++) {
                if (this.tab.hayPieza(corX, j)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean puedeMoverSA(int corX, int corY) {
        if (this.corX == corX && this.corY == corY) {
            return false;
        }
        if (this.mismoColor(this.tab.getPieza(corX, corY))) {
            return false;
        }
        int My = Math.max(this.corY, corY);
        int Mx = Math.max(this.corX, corX);
        int mx = Math.min(this.corX, corX);
        int my = Math.min(this.corY, corY);

        if (My == my) {
            for (int j = mx + 1; j < Mx; j++) {
                if (this.tab.hayPieza(j, corY)) {
                    return false;
                }
            }
            return true;
        }
        if (Mx == mx) {
            for (int j = my + 1; j < My; j++) {
                if (this.tab.hayPieza(corX, j)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Pieza copia(Tablero tab) {
        Torre p = new Torre(tab, this.corX, this.corY, this.color);
        return p;
    }

}

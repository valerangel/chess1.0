/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Piezas;

import chess.Tablero;

/**
 * @author Angel
 */
public class Torre extends Pieza {

    public Torre(Tablero tab, int corX, int corY, Color color) {
        super(tab, corX, corY, color);
        if (color == Color.RESOURCE_CON_COLOR) {
            img = new javax.swing.ImageIcon(getClass().getResource("/icon/btor.png"));

        } else {
            img = new javax.swing.ImageIcon(getClass().getResource("/icon/ntor.png"));
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

        return this.movimientoTorre(this.corX, this.corY, corX, corY, this.tab);
    }

    public boolean puedeMoverSA(int corX, int corY) {
        if (this.corX == corX && this.corY == corY) {
            return false;
        }
        if (this.mismoColor(this.tab.getPieza(corX, corY))) {
            return false;
        }

        return this.movimientoTorre(this.corX, this.corY, corX, corY, this.tab);
    }

    public static boolean movimientoTorre(int corXPieza, int corYPieza, int corX, int corY, Tablero tab) {
        int My = Math.max(corYPieza, corY);
        int Mx = Math.max(corXPieza, corX);
        int mx = Math.min(corXPieza, corX);
        int my = Math.min(corYPieza, corY);
        if (My == my) {
            for (int j = mx + 1; j < Mx; j++) {
                if (tab.hayPieza(j, corY)) {
                    return false;
                }
            }
            return true;
        }
        if (Mx == mx) {
            for (int j = my + 1; j < My; j++) {
                if (tab.hayPieza(corX, j)) {
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

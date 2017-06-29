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
public class Dama extends Pieza {

    public Dama(Tablero tab, int corX, int corY, boolean color) {
        super(tab, corX, corY, color);
        //blanca
        if (color) {
            this.img = new javax.swing.ImageIcon(getClass().getResource("/icon/breina.png"));
            //negra
        } else {
            this.img = new javax.swing.ImageIcon(getClass().getResource("/icon/nreina.png"));
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

        //Se comprueba si es como un alfil
        boolean diag = false;
        if ((mx == corX && my == corY) || (Mx == corX && My == corY)) {
            diag = true;
        }

        if (My - my == Mx - mx) {
            if (diag) {
                for (int i = 1; i < My - my; i++) {
                    if (this.tab.hayPieza(mx + i, my + i)) {
                        return false;
                    }
                }
                return true;
            } else {
                for (int i = 1; i < My - my; i++) {
                    if (this.tab.hayPieza(mx + i, My - i)) {
                        return false;
                    }
                }
                return true;
            }
        }

        //Se comprueba como una torre
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

        //Se comprueba si es como un alfil
        boolean diag = false;
        if ((mx == corX && my == corY) || (Mx == corX && My == corY)) {
            diag = true;
        }

        if (My - my == Mx - mx) {
            if (diag) {
                for (int i = 1; i < My - my; i++) {
                    if (this.tab.hayPieza(mx + i, my + i)) {
                        return false;
                    }
                }
                return true;
            } else {
                for (int i = 1; i < My - my; i++) {
                    if (this.tab.hayPieza(mx + i, My - i)) {
                        return false;
                    }
                }
                return true;
            }
        }

        //Se comprueba como una torre
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
        Dama p = new Dama(tab, this.corX, this.corY, this.color);
        return p;
    }

}

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
public class Alfil extends Pieza {

    public Alfil(Tablero tab, int corX, int corY, Color color) {
        super(tab, corX, corY, color);
        //blanca
        if (color == Color.RESOURCE_CON_COLOR) {
            img = new javax.swing.ImageIcon(getClass().getResource("/icon/balf.png"));
            //negra
        } else {
            img = img = new javax.swing.ImageIcon(getClass().getResource("/icon/nalf.png"));
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

        return this.movimientoAlfil(this.corX, this.corY, corX, corY, this.tab);
    }

    public static boolean movimientoAlfil(int corXPieza, int corYPieza, int corX, int corY, Tablero tab) {
        int My = Math.max(corYPieza, corY);
        int Mx = Math.max(corXPieza, corX);
        int mx = Math.min(corXPieza, corX);
        int my = Math.min(corYPieza, corY);

        //We cheek if it's a true diagonal
        if (!(My - my == Mx - mx)) {
            return false;
        }
        /*Variable diag it's made to know the relative position of the pieces.
        * true =     left and down    or    right and up
        * false =    left and up      or    lefth and down*/
        boolean diag = false;
        if ((mx == corX && my == corY) || (Mx == corX && My == corY)) {
            diag = true;
        }
        if (diag) {
            for (int i = 1; i < My - my; i++) {
                if (tab.hayPieza(mx + i, my + i)) {
                    return false;
                }
            }

            return true;
        }
        for (int i = 1; i < My - my; i++) {
            if (tab.hayPieza(mx + i, My - i)) {
                return false;
            }
        }
        return true;

    }

    public boolean puedeMoverSA(int corX, int corY) {
        if (this.corX == corX && this.corY == corY) {
            return false;
        }
        if (this.mismoColor(this.tab.getPieza(corX, corY))) {
            return false;
        }

        return this.movimientoAlfil(this.corX, this.corY, corX, corY, this.tab);
    }

    @Override
    public Pieza copia(Tablero tab) {
        return new Alfil(tab, this.corX, this.corY, this.color);
    }

}

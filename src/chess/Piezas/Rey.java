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
public class Rey extends Pieza {

    private boolean movido;

    public Rey(Tablero tab, int corX, int corY, Color color) {
        super(tab, corX, corY, color);
        //blanca
        if (color == Color.RESOURCE_CON_COLOR) {
            img = new javax.swing.ImageIcon(getClass().getResource("/icon/brey.png"));
            //negra
        } else {
            img = img = new javax.swing.ImageIcon(getClass().getResource("/icon/nrey.png"));
        }
        movido = false;
        enroque = false;
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
        if ((Math.abs(corY - this.corY) <= 1) && (Math.abs(corX - this.corX) <= 1)) {
            return true;
        }
        if (!movido && corX == 6 && corY == 7 && this.tab.hayPieza(7, 7) && (this.tab.getPieza(7, 7)) instanceof Torre) {
            if (this.tab.getPieza(7, 7).puedeMover(5, 7) && !this.tab.amenaza(6,7,this.color== Color.RESOURCE_CON_COLOR)) {
                enroque = true;
                return true;
            }
        }
        if (!movido && corX == 6 && corY == 0 && this.tab.hayPieza(7, 0) && (this.tab.getPieza(7, 0)) instanceof Torre) {
            if (this.tab.getPieza(7, 0).puedeMover(5, 0) && !this.tab.amenaza(6,0,this.color== Color.RESOURCE_CON_COLOR)) {
                enroque = true;
                return true;
            }
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
        return Math.abs(corY - this.corY) <= 1 && Math.abs(corX - this.corX) <= 1;

    }

    @Override
    public Pieza copia(Tablero tab) {
        Rey p = new Rey(tab, this.corX, this.corY, this.color);
        return p;
    }

    public void mover(int x, int y) {
        super.mover(x, y);
        if (this.color== Color.RESOURCE_CON_COLOR) {
            this.tab.setBRey(x, y);
            if (enroque) {
                this.tab.mover(7, 7, 5, 7, true);

            }
        } else {
            this.tab.setNRey(x, y);
            if (enroque) {
                this.tab.mover(7, 0, 5, 0, true);
            }
        }
        enroque = false;
    }

}

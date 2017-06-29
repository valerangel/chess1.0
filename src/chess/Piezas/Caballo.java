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
public class Caballo extends Pieza {

    public Caballo(Tablero tab, int corX, int corY, boolean color) {
        super(tab, corX, corY, color);
        //blanca
        if (color) {
            img = new javax.swing.ImageIcon(getClass().getResource("/icon/bcab.png"));
            //negra
        } else {
            img = img = new javax.swing.ImageIcon(getClass().getResource("/icon/ncab.png"));
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
        
        if(this.reyAmenazado(corX, corY)){
            return false;
        }

        return (Math.abs(this.corX - corX) == 1 && Math.abs(this.corY - corY) == 2)
                || (Math.abs(this.corX - corX) == 2 && Math.abs(this.corY - corY) == 1);
    }
    
     public boolean puedeMoverSA(int corX, int corY) {
        if (this.corX == corX && this.corY == corY) {
            return false;
        }
        if (this.mismoColor(this.tab.getPieza(corX, corY))) {
            return false;
        }

         return (Math.abs(this.corX - corX) == 1 && Math.abs(this.corY - corY) == 2)
                 || (Math.abs(this.corX - corX) == 2 && Math.abs(this.corY - corY) == 1);
     }

    @Override
    public Pieza copia(Tablero tab) {
        Caballo p = new Caballo(tab, this.corX, this.corY, this.color);
        return p;
    }
}

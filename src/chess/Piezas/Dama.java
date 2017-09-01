/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Piezas;

import chess.Tablero;
import chess.Piezas.Alfil.*;
import chess.Piezas.Torre.*;

/**
 *
 * @author Angel
 */
public class Dama extends Pieza {

    public Dama(Tablero tab, int corX, int corY, Color color) {
        super(tab, corX, corY, color);
        if (color == Color.RESOURCE_CON_COLOR) {
            this.img = new javax.swing.ImageIcon(getClass().getResource("/icon/breina.png"));
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

        if(Alfil.movimientoAlfil(this.corX,this.corY, corX,corY, this.tab)){
            return true;
        }

        return Torre.movimientoTorre(this.corX,this.corY,corX,corY,this.tab);
    }

    public boolean puedeMoverSA(int corX, int corY) {
        if (this.corX == corX && this.corY == corY) {
            return false;
        }
        if (this.mismoColor(this.tab.getPieza(corX, corY))) {
            return false;
        }

        if(Alfil.movimientoAlfil(this.corX,this.corY, corX,corY, this.tab)){
            return true;
        }

        return Torre.movimientoTorre(this.corX,this.corY,corX,corY,this.tab);
    }

    @Override
    public Pieza copia(Tablero tab) {
        Dama p = new Dama(tab, this.corX, this.corY, this.color);
        return p;
    }

}

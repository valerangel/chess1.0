/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Piezas;

import chess.Tablero;

import javax.swing.JOptionPane;

/**
 * @author Angel
 */
public class Peon extends Pieza {

    private boolean primerMov;

    public Peon(Tablero tab, int corX, int corY, boolean color) {
        super(tab, corX, corY, color);
        //blanca
        if (color) {
            img = new javax.swing.ImageIcon(getClass().getResource("/icon/bpawn.png"));
            //negra
        } else {
            img = img = new javax.swing.ImageIcon(getClass().getResource("/icon/npawn.png"));
        }
        primerMov = true;
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

        //Si avanza 1 hacia abajo  y Si es blanco  o  avanza 1 hacia arria  y es negro
        if ((this.corY - corY == 1 && this.color) || (corY - this.corY == 1 && !this.color)) {
            //Si se mueve en diagonal y hay pieza
            if (Math.abs(this.corX - corX) == 1 && this.tab.hayPieza(corX, corY)) {
                return true;
            }
            if (this.corX - corX == 0 && !this.tab.hayPieza(corX, corY)) {
                return true;
            }
        }

        if (((this.corY - corY == 2 && this.color) || (corY - this.corY == 2 && !this.color)) && primerMov) {
            if (this.corX - corX == 0 && !this.tab.hayPieza(corX, corY)) {
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

        //Si avanza 1 hacia abajo  y Si es blanco  o  avanza 1 hacia arria  y es negro
        if ((this.corY - corY == 1 && this.color) || (corY - this.corY == 1 && !this.color)) {
            //Si se mueve en diagonal y hay pieza
            if (Math.abs(this.corX - corX) == 1 && this.tab.hayPieza(corX, corY)) {
                return true;
            }
            if (this.corX - corX == 0 && !this.tab.hayPieza(corX, corY)) {
                return true;
            }
        }

        if (((this.corY - corY == 2 && this.color) || (corY - this.corY == 2 && !this.color)) && primerMov) {
            if (this.corX - corX == 0 && !this.tab.hayPieza(corX, corY)) {
                return true;
            }

        }
        return false;
    }

    @Override
    public void mover(int corX, int corY) {
        this.corX = corX;
        this.corY = corY;
        primerMov = false;
        if (((color && corY == 0) || (!color && corY == 7)) && this.tab.getFalsoTablero()) {
            int eleccion = this.elegir();
            this.tab.convertir(eleccion, corX, corY, this.color);
        }
    }

    @Override
    public Pieza copia(Tablero tab) {
        Peon p = new Peon(tab, this.corX, this.corY, this.color);
        if (!this.primerMov) {
            p.setMovido();
        }
        return p;
    }

    private void setMovido() {
        this.primerMov = false;
    }

    private int elegir() {
        String[] opciones = {"Reina", "Torre", "Alfil", "Caballo"};
        int eleccion = JOptionPane.showOptionDialog(null, "Elige en qué convertir el peón",
                "Elige", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
        return eleccion;
    }
}

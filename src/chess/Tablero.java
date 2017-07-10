/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import chess.Piezas.*;

/**
 * @author Angel
 */
public class Tablero {

    private Pieza[][] casillas;
    private boolean turno;
    private int[] brey;
    private int[] nrey;
    private boolean tablero;
    public static int OCHO = 8;

    public Tablero() {
        this.tablero = true;
        this.casillas = new Pieza[8][8];
        //Ponemos los peones
        for (int i = 0; i < 8; i++) {
            casillas[i][1] = new Peon(this, i, 1, Color.RESOURCE_SIN_COLOR);
            casillas[i][6] = new Peon(this, i, 6, Color.RESOURCE_CON_COLOR);
        }
        casillas[0][0] = new Torre(this, 0, 0, Color.RESOURCE_SIN_COLOR);
        casillas[1][0] = new Caballo(this, 1, 0, Color.RESOURCE_SIN_COLOR);
        casillas[2][0] = new Alfil(this, 2, 0, Color.RESOURCE_SIN_COLOR);
        casillas[3][0] = new Dama(this, 3, 0, Color.RESOURCE_SIN_COLOR);

        casillas[4][0] = new Rey(this, 4, 0, Color.RESOURCE_SIN_COLOR);
        this.nrey = new int[2];
        nrey[0] = 4;
        nrey[1] = 0;

        casillas[5][0] = new Alfil(this, 5, 0, Color.RESOURCE_SIN_COLOR);
        casillas[6][0] = new Caballo(this, 6, 0, Color.RESOURCE_SIN_COLOR);
        casillas[7][0] = new Torre(this, 7, 0, Color.RESOURCE_SIN_COLOR);

        casillas[0][7] = new Torre(this, 0, 7, Color.RESOURCE_CON_COLOR);
        casillas[1][7] = new Caballo(this, 1, 7, Color.RESOURCE_CON_COLOR);
        casillas[2][7] = new Alfil(this, 2, 7, Color.RESOURCE_CON_COLOR);
        casillas[3][7] = new Dama(this, 3, 7, Color.RESOURCE_CON_COLOR);

        casillas[4][7] = new Rey(this, 4, 7, Color.RESOURCE_CON_COLOR);
        this.brey = new int[2];
        brey[0] = 4;
        brey[1] = 7;
        casillas[5][7] = new Alfil(this, 5, 7, Color.RESOURCE_CON_COLOR);
        casillas[6][7] = new Caballo(this, 6, 7, Color.RESOURCE_CON_COLOR);
        casillas[7][7] = new Torre(this, 7, 7, Color.RESOURCE_CON_COLOR);

        turno = true;

    }

    public Tablero(boolean bol) {
        this();
        this.tablero = false;
    }

    public boolean hayPieza(int x, int y) {
        return this.casillas[x][y] != null;
    }

    public void mover(int xIni, int yIni, int xFin, int yFin) {
        if (this.hayPieza(xIni, yIni) && (this.casillas[xIni][yIni].getColor() == Color.RESOURCE_CON_COLOR) == turno
                && this.casillas[xIni][yIni].puedeMover(xFin, yFin)) {

            this.casillas[xFin][yFin] = this.casillas[xIni][yIni];
            this.casillas[xFin][yFin].mover(xFin, yFin);
            this.casillas[xIni][yIni] = null;
            turno = !turno;
        }
    }

    public void mover(int xIni, int yIni, int xFin, int yFin, boolean bol) {
        this.casillas[xFin][yFin] = this.casillas[xIni][yIni];
        this.casillas[xFin][yFin].mover(xFin, yFin);
        this.casillas[xIni][yIni] = null;

    }

    public Pieza getPieza(int corX, int corY) {
        return this.casillas[corX][corY];
    }

    public void setPieza(int corX, int corY, Pieza p) {
        if (p != null) {
            this.casillas[corX][corY] = p.copia(this);
        } else {
            this.casillas[corX][corY] = null;
        }
    }

    public void moverSA(int xIni, int yIni, int xFin, int yFin) {
        if (this.hayPieza(xIni, yIni) && this.casillas[xIni][yIni].getColor()== Color.RESOURCE_CON_COLOR == turno
                && this.casillas[xIni][yIni].puedeMoverSA(xFin, yFin)) {

            this.casillas[xFin][yFin] = this.casillas[xIni][yIni];
            this.casillas[xFin][yFin].mover(xFin, yFin);
            this.casillas[xIni][yIni] = null;
            turno = !turno;
        }
    }

    public void convertir(int elec, int corX, int corY, Color color) {
        switch (elec) {
            case 3:
                this.casillas[corX][corY] = new Caballo(this, corX, corY, color);
                break;
            case 1:
                this.casillas[corX][corY] = new Torre(this, corX, corY, color);
                break;
            case 2:
                this.casillas[corX][corY] = new Alfil(this, corX, corY, color);
                break;
            default:
                this.casillas[corX][corY] = new Dama(this, corX, corY, color);
                break;
        }

    }

    /*true si está amenazado, false si no
     */
    public boolean amenaza(int x, int y, boolean color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.hayPieza(i, j) && this.casillas[i][j].getColor()== Color.RESOURCE_CON_COLOR != color
                        && this.casillas[i][j].puedeMoverSA(x, y) && !(this.casillas[i][i] instanceof Rey)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean getTurno() {
        return this.turno;
    }

    public Tablero getCopiaTablero() {
        Tablero tab = new Tablero();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tab.setPieza(i, j, this.casillas[i][j]);
            }
        }
        tab.nrey = this.nrey.clone();
        tab.brey = this.brey.clone();
        tab.turno = this.turno;
        tab.setFalsoTablero();
        return tab;
    }

    public void setNRey(int x, int y) {
        this.nrey[0] = x;
        this.nrey[1] = y;
    }

    public void setBRey(int x, int y) {
        this.brey[0] = x;
        this.brey[1] = y;
    }

    public int[] getBrey() {
        return this.brey;
    }

    public int[] getNrey() {
        return this.nrey;
    }
    public void setFalsoTablero(){
        this.tablero = false;
    }
    public boolean getFalsoTablero(){
        return this.tablero;
    }
}

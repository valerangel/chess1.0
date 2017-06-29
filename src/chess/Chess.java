/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import chess.Frame.Frame1;

/**
 * @author Angel
 */
public class Chess{

    /**
     * Chess 4 fun
     * <p>
     * It's not perfect, but it works!
     * Don't forget to report all the bugs you can find :D
     */
    public static void main(String[] args) {

        Tablero tablero = new Tablero();

        Frame1 frame = new Frame1(tablero);
    }

}

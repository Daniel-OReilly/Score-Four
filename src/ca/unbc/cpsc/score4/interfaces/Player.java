/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.unbc.cpsc.score4.interfaces;

import ca.unbc.cpsc.score4.enums.GameOverStatus;

import ca.unbc.cpsc.score4.interfaces.Colour;

import ca.unbc.cpsc.score4.interfaces.Location;

import ca.unbc.cpsc.score4.exceptions.PlayerException;

public interface Player {

    public abstract void reset() throws PlayerException;

    public abstract void startGameAs(Colour c)
            throws PlayerException;

    public abstract void noteOpponentsId(int id)
            throws PlayerException;

    public abstract void opponentPlays(Location ell)
            throws PlayerException;

    public abstract Location requestMoveLocation()
            throws PlayerException;

    public abstract Location retry() throws PlayerException;

    public abstract void noteGameOver(GameOverStatus whatHappened)
            throws PlayerException;

}   // end interface Player

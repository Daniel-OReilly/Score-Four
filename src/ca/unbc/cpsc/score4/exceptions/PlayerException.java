/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.unbc.cpsc.score4.exceptions;

public class PlayerException extends Exception

{

public PlayerException() { this("Unknown Player Exception") ; }

public PlayerException(String s)                 { super(s) ; }

public PlayerException(Throwable t)              { super(t) ; }

public PlayerException(String s, Throwable t)    { super(s,t) ; }
}
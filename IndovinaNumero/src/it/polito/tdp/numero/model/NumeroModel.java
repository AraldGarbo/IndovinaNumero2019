package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;

public class NumeroModel {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	
	public NumeroModel() {
		inGioco = false;
	}
	
	public void newGame() {
		inGioco = true;
		this.segreto = (int)(Math.random()*NMAX)+1;
    	this.tentativiFatti = 0;
	}
	
	
	/**
	 * Metodo per effettuare un tentativo
	 * @param t è il tentativo
	 * @return 1 se >, -1 se <, 0 se corretto
	 */
	public int tentativo(int t) {
		//controllo se la partita è in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita è terminata");
		}
		
		//controllo se l'input è nel range corretto
		if(!tentativoValido(t)) throw new InvalidParameterException(String.format("Inserisci un numero "
				+ "tra %d e %d", 1, NMAX));
		//gestiamo il tentativo
		this.tentativiFatti++;
		if(this.tentativiFatti == this.TMAX) inGioco = false;
		if(t == this.segreto) {
			this.inGioco = false; 
			return 0; //vittoria
		}
		if(t>this.segreto) 
			return 1; //troppo alto
		return -1; //troppo basso
	}
	
	public boolean tentativoValido(int t) {
		if(t<1||t>NMAX) return false;
		else return true;
	}

	public boolean isInGioco() {
		return inGioco;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}

	public int getSegreto() {
		return segreto;
	}
	
}

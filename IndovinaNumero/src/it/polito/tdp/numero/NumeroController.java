package it.polito.tdp.numero;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.numero.model.NumeroModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NumeroController {

	private NumeroModel model;
	
	
	
  
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox BoxcontrolloPartita;

    @FXML
    private TextField txtrimasti;

    @FXML
    private HBox BoxControlloTentativi;

    @FXML
    private TextField txttentativo;

    @FXML
    private TextArea txtmessaggi;

    @FXML
    void HandleNuovaPartita(ActionEvent event) {
    	//GESTISCE UNA NUOVA PARTITA
    	//gestione interfaccia
    	BoxcontrolloPartita.setDisable(true);
    	BoxControlloTentativi.setDisable(false);
    	txtmessaggi.clear();
    	txtrimasti.setText(Integer.toString(model.getTMAX()));
    	//comunico al modello di iniziare una nuova partita
    	model.newGame();	
    }

    @FXML
    void HandleProvaTentativo(ActionEvent event) {
    	//leggi il valore inserito
    	String ts = txttentativo.getText();
    	int tentativo;
    	//controlla se è valido il tipo di dato
    	try {
    		tentativo = Integer.parseInt(ts);
    		txttentativo.clear();
    	}
    	catch (NumberFormatException e) {
    		//la stringa non è valida
    		txtmessaggi.appendText("Numero non valido!\n");
    		return;
    	}
    	
    	if(!model.tentativoValido(tentativo)) {
    		txtmessaggi.appendText("Range non valido!\n");
    		return;
    	}
    	
    	int risultato = model.tentativo(tentativo);
    	
    	if(risultato == 0) {
    		txtmessaggi.appendText("Complimenti, hai Vinto!!! Hai uitilizzato "+model.getTentativiFatti()+
    				" tentativi.\nAdesso puoi fumarti un botto!!");
    		BoxcontrolloPartita.setDisable(false);
    		BoxControlloTentativi.setDisable(true);
    	}
    	else if(risultato < 0){
    		txtmessaggi.appendText("Tentativo Troppo Basso \nUltimo Tentativo: "+tentativo+"\n");
    	}
    	else {
    		txtmessaggi.appendText("Tentativo Troppo Alto \nUltimo Tentativo: "+tentativo+"\n");
    	}    	
    	
    	//aggiornare interfaccia num tentativi
    	txtrimasti.setText(Integer.toString(model.getTMAX()-model.getTentativiFatti()));
    	
    	if(!model.isInGioco()) {
    		if(risultato != 0) {
    			txtmessaggi.appendText("Hai perso COGLIONE!");
    			txtmessaggi.appendText(String.format("\nIl numero segreto era: %d!", model.getSegreto()));
    			BoxcontrolloPartita.setDisable(false);
        		BoxControlloTentativi.setDisable(true);
    		}
    	}
    }

    @FXML
    void initialize() {
        assert BoxcontrolloPartita != null : "fx:id=\"BoxcontrolloPartita\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txtrimasti != null : "fx:id=\"txtrimasti\" was not injected: check your FXML file 'Numero.fxml'.";
        assert BoxControlloTentativi != null : "fx:id=\"BoxControlloTentativi\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txttentativo != null : "fx:id=\"txttentativo\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txtmessaggi != null : "fx:id=\"txtmessaggi\" was not injected: check your FXML file 'Numero.fxml'.";

    }
    
    public void setModel(NumeroModel model) {
  		this.model = model;
  	}

}

 
		
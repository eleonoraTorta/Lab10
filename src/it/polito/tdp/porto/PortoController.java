package it.polito.tdp.porto;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import it.polito.tdp.porto.model.Paper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<Author> boxSecondo;

    @FXML
    private TextArea txtResult;
    
    @FXML
    private Button btnCoautori;

    @FXML
    private Button btnSequenza;


    @FXML
    void handleCoautori(ActionEvent event) {
    	
    	Author a = boxPrimo.getValue();
    	if(a == null){
    		txtResult.appendText("Selezionare un autore!\n");
    	}
    	
    	List <Author> coautori = model.getCoautori(a);
 
    	for(Author b : coautori){
    		txtResult.appendText(b.toString());
    	}
    	
    	boxSecondo.setDisable(false);
    	btnSequenza.setDisable(false);
    	
    	boxSecondo.getItems().addAll(model.getNonCoautori(a));
    	
    }

    @FXML
    void handleSequenza(ActionEvent event) {
    	txtResult.clear();
    	model.creaGrafo();
    	Author a = boxPrimo.getValue();
    	Author b = boxSecondo.getValue();
    	if(a == null || b == null){
    		txtResult.appendText("Selezionare due autori!\n");
    	}
    	List <Paper> sequenza = model.trovaSequenza(a, b);
    	for(Paper p : sequenza){
    		txtResult.appendText(p + "\n");
    	}
    	
    }

    @FXML
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnCoautori != null : "fx:id=\"btnCoautori\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnSequenza != null : "fx:id=\"btnSequenza\" was not injected: check your FXML file 'Porto.fxml'.";
        
        boxSecondo.setDisable(true);
        btnSequenza.setDisable(true);

    }

	public void setModel(Model model) {
		this.model = model;
		
		boxPrimo.getItems().addAll(model.getAutori());
		
	}
}

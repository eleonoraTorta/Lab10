package it.polito.tdp.porto.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		Author a  = model.getAutore(3528);

	//	System.out.println(model.getCoautori(a));
	//	System.out.println(model.getGrafo());
		
		Author b  = model.getAutore(719);
		System.out.println(model.trovaSequenza(a, b));
	}

}

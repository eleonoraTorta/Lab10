package it.polito.tdp.porto.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		Author a  = model.getAutore(85);
		System.out.println(model.getCoautori(a));
	//	System.out.println(model.getGrafo());
	}

}

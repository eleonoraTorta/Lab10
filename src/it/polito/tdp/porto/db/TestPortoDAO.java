package it.polito.tdp.porto.db;

import it.polito.tdp.porto.model.AuthorIdMap;

public class TestPortoDAO {
	
	public static void main(String args[]) {
		AuthorIdMap authorIdMap = new AuthorIdMap();
		PortoDAO pd = new PortoDAO();
		System.out.println(pd.listaAutori(authorIdMap));
		
		System.out.println(pd.getAutore(85));
		System.out.println(pd.getArticolo(2293546));
		System.out.println(pd.getArticolo(1941144));

	}

}

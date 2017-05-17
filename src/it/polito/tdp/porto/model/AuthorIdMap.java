package it.polito.tdp.porto.model;

import java.util.HashMap;
import java.util.*;
import java.util.Map;

public class AuthorIdMap {
	
	private Map <Integer, Author> mappa;
	
	public AuthorIdMap (){
		mappa = new HashMap <>();
	}
	
	public Author get(Integer idAutore){
		return mappa.get(idAutore);
	}
	
	public Author put(Author a){
		Author old = mappa.get(a.getId());
		if( old == null){
			mappa.put(a.getId(), a);
			return a;
		} else {
			return old;
		}
	}

}

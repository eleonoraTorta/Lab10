package it.polito.tdp.porto.model;

import java.util.HashMap;
import java.util.*;
import java.util.Map;

public class PaperIdMap {
	
	private Map <Integer, Paper> mappa;
	
	public PaperIdMap (){
		mappa = new HashMap <>();
	}
	
	public Paper get(Integer idPaper){
		return mappa.get(idPaper);
	}
	
	public Paper put(Paper p){
		Paper old = mappa.get(p.getEprintid());
		if( old == null){
			mappa.put(p.getEprintid(), p);
			return p;
		} else {
			return old;
		}
	}

}

package it.polito.tdp.porto.model;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	
	private PortoDAO p;
	private AuthorIdMap authorIdMap;
	private List <Author> autori ;
	private List <Author> coautori;
	private UndirectedGraph <Author, DefaultEdge> grafo ;
	
	public Model(){
		p = new PortoDAO();
		authorIdMap = new AuthorIdMap();
	}
	
	public Author getAutore(int id){
		return p.getAutore(id);	
	}
	
	public Paper getPaper(int cod){
		return p.getArticolo(cod);	
	}
	
	
	public List <Author> getAutori(){
		if(this.autori == null){
			this.autori = p.listaAutori(authorIdMap);
		}
		return this.autori;
		
	}
	
	public List <Author> getCoautori(Author a){
		coautori = p.getCoautori(authorIdMap, a);
		coautori.remove(a);
		return coautori;
	}
	
	public void creaGrafo(){
		grafo = new SimpleGraph<> (DefaultEdge.class);
		
		// creo i VERTICI
		Graphs.addAllVertices(grafo, this.getAutori());
		
		//creo gli ARCHI
		for( Author a : this.getAutori()){
			for( Author b : this.getCoautori(a)){
				if(! b.equals(a)){
					grafo.addEdge(a, b);
				}
			}
		}
		
	}
	
	public UndirectedGraph <Author, DefaultEdge> getGrafo(){
		if(this.grafo == null){
			this.creaGrafo();
		}
		return this.grafo;
	}

	public List <Author> getNonCoautori(Author autore) {
		List <Author> autoriTotali = this.getAutori();
		for( Author a : this.getCoautori(autore)){
			autoriTotali.remove(a);
		}
		autoriTotali.remove(autore);
		return autoriTotali;
	}
	
	public List <Paper> trovaSequenza( Author a, Author b){
		DijkstraShortestPath <Author, DefaultEdge> dsp = new DijkstraShortestPath <Author, DefaultEdge>(this.getGrafo(), a,b);
		List <DefaultEdge> archi =  dsp.findPathBetween(grafo, a, b);
		List<Paper>  articoli = new LinkedList <Paper>();
		for(DefaultEdge d : archi){
			 Author autore1 = grafo.getEdgeSource(d);
			 Author autore2 = grafo.getEdgeTarget(d);
			 articoli.addAll(p.getPapersComuni(autore1, autore2));
		}
		return articoli;
	}
	
}

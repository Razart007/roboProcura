package Buscas;

import java.util.ArrayList;
import java.util.Stack;


public class Graph {
	private final int MAX_VERTS = 8;
	private Vertex vertexList[]; 
	private int adjMat[][];   
	private int nVerts;  
	private Stack<Integer> theStack;
	private ArrayList<Vertex> mVertex = new ArrayList<Vertex>();
	// ------------------------------------------------------------
	public Graph()   
	{
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for(int y=0; y<MAX_VERTS; y++)   
			for(int x=0; x<MAX_VERTS; x++)  
				adjMat[x][y] = 0;
		theStack = new Stack();

	} 
	// ------------------------------------------------------------
	public void addVertex(boolean salaEsquerdaSuja, boolean salaDireitaSuja, char posicaoAspiradorPo)
	{
		vertexList[nVerts++] = new Vertex();
	}
	// ------------------------------------------------------------
	public void addEdge(int start, int end)
	{
		adjMat[start][end] = 1;
		//adjMat[end][start] = 1;
	}
	// ------------------------------------------------------------
	public void dfs()
	{
		vertexList[0].FoiVisitado = true;
		theStack.push(0);
		while( !theStack.isEmpty() )
		{
			int v = getAdjUnvisitedVertex( (int)theStack.peek() );
			if(v == -1)
				theStack.pop();
			else
			{
				vertexList[v].FoiVisitado = true;
				theStack.push(v);
			}
		}
		for(int j=0; j<nVerts; j++)
			vertexList[j].FoiVisitado = false;
	}
	
	
	// -------------------------------------------------------------
	public String bfs(int estadoInicial, int estadoFinal)                   
	{                               
		String resul = "";
		vertexList[estadoInicial].FoiVisitado = true;             
		theStack.push(estadoInicial);              
		int v2;
		
		mVertex.add(vertexList[estadoInicial]);
		mVertex.get(mVertex.size() - 1).vertexAnterior = null;
		mVertex.get(mVertex.size() - 1).vertexAtual = estadoInicial;
		
		while( !theStack.isEmpty() )    
		{
			int v1 = (int) theStack.pop();   

			while( (v2=getAdjUnvisitedVertex(v1)) != -1 )
			{                            
				vertexList[v2].FoiVisitado = true;        
				theStack.push(v2);              
				mVertex.add(vertexList[v2]);
				mVertex.get(mVertex.size() - 1).vertexAnterior = vertexList[v1];
				mVertex.get(mVertex.size() - 1).vertexAtual = v2;
			}   
		}  
	
		for(int j=0; j<nVerts; j++)            
			vertexList[j].FoiVisitado = false;
		
		return resul;
	}  

	public int getAdjUnvisitedVertex(int v)
	{
		for(int j=0; j<nVerts; j++)
			if(adjMat[v][j]==1 && vertexList[j].FoiVisitado == false)
				return j;
		return -1;
	} 
	
	public ArrayList<Vertex> getCaminhos(){
		return mVertex;
	}
	// ------------------------------------------------------------
	
	
}

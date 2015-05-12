package Buscas;

public class MatrizAdj {
	CelulaMatrizAdj [][] matrizAdj = new CelulaMatrizAdj [140][140];
	public MatrizAdj(){
		for (int l = 0; l < 140; l++){
			for (int c = 0; c < 140; c++){
				CelulaMatrizAdj aux = new CelulaMatrizAdj();
				aux.ligacao = 0;
				aux.linhaStart = -1;
				aux.colunaStart = -1;
				aux.linhaEnd = -1;
				aux.colunaEnd = -1;
				matrizAdj[l][c] = aux;
			}
		}
	}	
}

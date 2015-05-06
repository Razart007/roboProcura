package Frames;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import Buscas.Celula;

public class FramePrincipal extends JFrame{
	JPanel ambiente;
	JMenuBar barraMenus;
	JMenu arquivo, executar;
	JMenuItem sair, novo, abrir, salvar;
	JButton jBuExecutar, robo, porta;
	Celula [][] matrizAdj = new Celula [7][20];
	int posicaoRoboX = 0, posicaoRoboY = 240, posicaoMatrizRoboX, posicaoMatrizRoboY, posicaoPortaX = 900, 
			posicaoPortaY = 320, posicaoMatrizPortaX, posicaoMatrizPortaY;
	ImageIcon imObstaculo = new ImageIcon("Imagens/obstaculo.png");
	ImageIcon imLivre = new ImageIcon("Imagens/livre.png");
	
	public FramePrincipal(){
		inicializaFrame();
		inicializaComponentes();
		inicializaAmbiente();
	}
	
	private void inicializaFrame(){
		try {  
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  

		this.setTitle("Robo � Procura");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagens/Robo.png"));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setSize(1024, 650);
		this.setLocationRelativeTo(this);
		this.setVisible(true);
	}
	
	private void inicializaComponentes(){
		barraMenus = new JMenuBar();
		barraMenus.setPreferredSize(new java.awt.Dimension(101,23));
		this.setJMenuBar(barraMenus);
		
		novo = new JMenuItem("Novo",KeyEvent.VK_N);
		abrir = new JMenuItem("Abrir",KeyEvent.VK_O);
		salvar = new JMenuItem("Salvar",KeyEvent.VK_S);
		sair = new JMenuItem("Sair",KeyEvent.VK_E);
		
		arquivo = new JMenu("Arquivo");
		arquivo.add(novo);
		novo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		arquivo.add(abrir);
		abrir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		arquivo.add(salvar);
		salvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		arquivo.add(sair);
		sair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		barraMenus.add(arquivo);
		
		executar = new JMenu("Executar");
		
		barraMenus.add(executar);
		
		jBuExecutar = new JButton("Executar");
		jBuExecutar.setSize(100, 30);
		add(jBuExecutar);
		jBuExecutar.setBounds(475, 567, 100, 30);	
		
		jBuExecutar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
	
	private void inicializaAmbiente(){
		ambiente = new JPanel();
		ambiente.setSize(100, 200);
		ambiente.setBackground(Color.WHITE);
		add(ambiente);
		ambiente.setBounds(10, 5, 1010, 560);
		ambiente.setLayout(null);
				
	    ImageIcon imRobo = new ImageIcon("Imagens/Robo.png");  
	    														 
		robo = new JButton(imRobo);
		robo.setContentAreaFilled(false);
		ambiente.add(robo);
		robo.setBounds(0, 240, 50, 80);
		
		porta = new JButton(new ImageIcon("Imagens/Porta.gif"));
		porta.setContentAreaFilled(false);
		ambiente.add(porta);
		porta.setBounds(posicaoPortaX, posicaoPortaY, 50, 80);
		
		if(posicaoRoboX != 0){
			posicaoMatrizRoboX = posicaoRoboX / 50;
		}
		else {
			posicaoMatrizRoboX = 0;
		}
		
		posicaoMatrizRoboY = posicaoRoboY / 80;
		
		if(posicaoPortaX != 0){
			posicaoMatrizPortaX = posicaoPortaX / 50;
		}
		else{
			posicaoMatrizPortaX = 0;
		}
		
		posicaoMatrizPortaY = posicaoPortaY / 80;
		
		for (int l = 0; l < 7;l++){
			for (int c = 0; c < 20; c++){
				if (!((l == posicaoMatrizRoboY && c == posicaoMatrizRoboX)||(l == posicaoMatrizPortaY && c == posicaoMatrizPortaX))){
					try{
						Celula celula = new Celula(l, c, false);
						celula.getBotao().setContentAreaFilled(false);
						boolean caminhoLivre = false;
						
						if ((c == 1) && (l == 3)){
							caminhoLivre = true;
						}
						
						if (c == 2){
							caminhoLivre = true;
						}
						
						if (((c == 3) || (c == 5)) && ((l == 0) || (l == 6))){
							caminhoLivre = true;
						}
						
						if (c == 4){
							caminhoLivre = true;
						}
						
						if (c == 6){
							caminhoLivre = true;
						}
						
						if ((c == 7) && (l == 4)){
							caminhoLivre = true;
						}
						
						if ((c == 8) && ((l == 4) || (l == 5) || (l == 6))){
							caminhoLivre = true;
						}
						
						if ((c == 9) && (l == 6)){
							caminhoLivre = true;
						}
						
						if (c == 10){
							caminhoLivre = true;
						}
						
						if ((c == 11) && (l == 0)){
							caminhoLivre = true;
						}
						
						if (c == 12){
							caminhoLivre = true;
						}
						
						if ((c == 13) && (l == 3)){
							caminhoLivre = true;
						}
						
						if (c == 14){
							caminhoLivre = true;
						}
						
						if ((c == 15) && (l == 0)){
							caminhoLivre = true;
						}
						
						if (c == 16){
							caminhoLivre = true;
						}
						
						if ((c == 17) && (l == 6)){
							caminhoLivre = true;
						}
						
						if ((c == 18) && ((l == 6) || (l == 5) || (l == 4))){
							caminhoLivre = true;
						}
						
						if (caminhoLivre){
							celula.getBotao().setIcon(imLivre);
						}
						else {
							celula.getBotao().setIcon(imObstaculo);
						}
						
						matrizAdj[l][c] = celula;
						ambiente.add(matrizAdj[l][c].getBotao());
						matrizAdj[l][c].getBotao().setBounds(c*50, l*80, 50, 80);
						matrizAdj[l][c].getBotao().addActionListener(new eventosB(l, c)); 
					} catch(Exception e){						
					}
				}
			}
		}
	}
	
	private class eventosB implements ActionListener{
		int linha, coluna;
		
		public eventosB(int linha, int coluna){
			this.linha = linha;
			this.coluna = coluna;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (matrizAdj[linha][coluna].isObstaculo()){
				matrizAdj[linha][coluna].getBotao().setIcon(imObstaculo);
				matrizAdj[linha][coluna].setObstaculo(false);
			}
			else {
				matrizAdj[linha][coluna].getBotao().setIcon(imLivre);
				matrizAdj[linha][coluna].setObstaculo(true);
			}
		}
	}
	
	public static void main(String args[]){
		FramePrincipal frame = new FramePrincipal();
	}
}

package Frames;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import Arquivos.ManipulaArquivoAmbiente;
import Buscas.Celula;
import Buscas.Graph;

public class FramePrincipal extends JFrame{
	JPanel ambiente;
	JMenuBar barraMenus;
	JMenu arquivo, executar;
	JMenuItem sair, novo, abrir, editar;
	JButton jBuExecutar, robo, porta, jBuCaminhoCerto, jBuAmbienteOk, jBuEditarInicio, jBuEditarFim;
	Celula [][] matrizAdj = new Celula [7][20];
	int linhaRobo = -1, colunaRobo = -1, linhaObjetivo = -1, colunaObjetivo = -1, linhaInicial = -1, colunaInicial = -1;
	ArrayList<Celula> caminhoCerto = new ArrayList<Celula>();
	ImageIcon imObstaculo = new ImageIcon("Imagens/obstaculo.png");
	ImageIcon imLivre = new ImageIcon("Imagens/livre.png");
	ImageIcon imRobo = new ImageIcon("Imagens/Robo.png");  
	ImageIcon imPorta = new ImageIcon("Imagens/Porta.gif");
	ImageIcon imPassou = new ImageIcon("Imagens/roboPassou.gif");
	ImageIcon imCaminhoCerto = new ImageIcon("Imagens/caminhoCerto.jpg");
	boolean chamouProdundidade = false, editando = false;
	private Graph graph;
	int linhaMatrizAmbiente = 0, colunaMatrizAmbiente = 0;
	ManipulaArquivoAmbiente manipularArquivo = new ManipulaArquivoAmbiente();
	String tipoDado = "ambiente";


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
		sair = new JMenuItem("Sair",KeyEvent.VK_E);
		editar = new JMenuItem("Editar",KeyEvent.VK_D);

		arquivo = new JMenu("Arquivo");
		arquivo.add(novo);
		novo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				manipularArquivo.novoArquivo();
				limparTabela();
				editando = true;
				editarAmbiente();
				chamouProdundidade = false;
			}
		});
		arquivo.add(abrir);
		abrir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abrirArquivo();
			}
		});

		arquivo.add(editar);
		editar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (linhaInicial == -1){
					JOptionPane.showMessageDialog(null,"� necess�rio preciso abrir um arquivo para editar", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				else {
					editando = true;
					editarAmbiente();
					tipoDado = "ambiente";
					chamouProdundidade = false;
				}
			}
		});

		arquivo.add(sair);
		sair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});


		barraMenus.add(arquivo);

		executar = new JMenu("Executar");

		barraMenus.add(executar);

	}


	private void inicializaAmbiente(){
		ambiente = new JPanel();
		ambiente.setSize(100, 200);
		ambiente.setBackground(Color.WHITE);
		add(ambiente);
		ambiente.setBounds(10, 5, 1010, 560);
		ambiente.setLayout(null);

		limparTabela();

		jBuExecutar = new JButton("Caminho Profundidade");
		jBuExecutar.setSize(200, 30);
		add(jBuExecutar);
		jBuExecutar.setBounds(300, 567, 200, 30);	

		jBuExecutar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!chamouProdundidade){
					graph.dfs();
					chamouProdundidade = true;
				}
				mostraProfundidade();
			}
		});

		jBuCaminhoCerto = new JButton("Caminho Objetivo");
		jBuCaminhoCerto.setSize(200, 30);
		add(jBuCaminhoCerto);
		jBuCaminhoCerto.setBounds(550, 567, 200, 30);

		jBuCaminhoCerto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!chamouProdundidade){
					graph.dfs();
					chamouProdundidade = true;
				}
				mostraObjetivo();
			}
		});

		jBuAmbienteOk = new JButton("Salvar");
		add(jBuAmbienteOk);
		jBuAmbienteOk.setBounds(175, 567, 200, 30);
		jBuAmbienteOk.setVisible(false);

		jBuAmbienteOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if((linhaRobo != -1) && (linhaObjetivo != -1)){
					linhaInicial = linhaRobo;
					colunaInicial = colunaRobo;
					matrizAdj[0][0].linhaInicial = linhaInicial;
					matrizAdj[0][0].colunaInicial = colunaInicial;
					matrizAdj[0][0].linhaObjetivo = linhaObjetivo;
					matrizAdj[0][0].colunaObjetivo = colunaObjetivo;
					manipularArquivo.salvarAmbiente(matrizAdj);
					jBuEditarInicio.setVisible(false);
					jBuEditarFim.setVisible(false);
					jBuAmbienteOk.setVisible(false);
					jBuExecutar.setVisible(true);
					jBuCaminhoCerto.setVisible(true);
					graph = new Graph(matrizAdj, linhaRobo, colunaRobo, linhaObjetivo, colunaObjetivo);
					adcionarArestas();
				}
				else {
					JOptionPane.showMessageDialog(null,"� necess�rio escolher o estado inicial e o final", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				editando = false;
			}
		});

		jBuEditarInicio = new JButton("Editar posicao robo");
		add(jBuEditarInicio);
		jBuEditarInicio.setBounds(425, 567, 200, 30);
		jBuEditarInicio.setVisible(false);

		jBuEditarInicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tipoDado = "robo";
			}
		});

		jBuEditarFim = new JButton("Editar posicao porta");
		add(jBuEditarFim);
		jBuEditarFim.setBounds(675, 567, 200, 30);
		jBuEditarFim.setVisible(false);

		jBuEditarFim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tipoDado = "objetivo";
			}
		});

	}

	private class eventosB implements ActionListener{
		int linha, coluna;
		String tipoAlteracao;

		public eventosB(int linha, int coluna, String tipoAlteracao){
			this.linha = linha;
			this.coluna = coluna;
			this.tipoAlteracao = tipoAlteracao;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (editando){
				System.out.println("Entrou no m�todo do bot�o");
				switch (tipoDado){
				case "ambiente":
					if (!matrizAdj[linha][coluna].isObstaculo()){
						if (linha == linhaRobo && coluna == colunaRobo){
							linhaRobo = -1;
							colunaRobo = -1;
						}

						if (linha == linhaObjetivo && coluna == colunaObjetivo){
							linhaObjetivo = -1;
							colunaObjetivo = -1;
						}
						matrizAdj[linha][coluna].getBotao().setIcon(imObstaculo);
						matrizAdj[linha][coluna].setObstaculo(true);
					}
					else {
						matrizAdj[linha][coluna].getBotao().setIcon(imLivre);
						matrizAdj[linha][coluna].setObstaculo(false);
					}
					break;
				case "robo":
					if(!matrizAdj[linha][coluna].isObstaculo()){
						matrizAdj[linha][coluna].getBotao().setIcon(imRobo);
						matrizAdj[linha][coluna].setObstaculo(false);
						if (linhaRobo != -1){
							matrizAdj[linhaRobo][colunaRobo].getBotao().setIcon(imLivre);
						}
						linhaRobo = linha;
						colunaRobo = coluna;
						linhaInicial = linha;
						colunaInicial = coluna;
						tipoDado = "ambiente";
					}				
					break;
				case "objetivo":
					if(!matrizAdj[linha][coluna].isObstaculo()){
						matrizAdj[linha][coluna].getBotao().setIcon(imPorta);
						matrizAdj[linha][coluna].setObstaculo(false);
						if (linhaObjetivo != -1){
							matrizAdj[linhaObjetivo][colunaObjetivo].getBotao().setIcon(imLivre);
						}
						linhaObjetivo = linha;
						colunaObjetivo = coluna;
						tipoDado = "ambiente";
					}
					break;
				}
			}
		}
	}

	public void adcionarArestas(){
		int linha = 0;
		int coluna = 0;
		for (int i = 0; i < 140; i++){
			if (linha > 0){
				graph.addEdge(i, i-20, linha, coluna, linha - 1, coluna);
			}

			if (coluna > 0){
				graph.addEdge(i, i-1, linha, coluna, linha, coluna - 1);
			}

			if (linha < 6){
				graph.addEdge(i, i+20, linha, coluna, linha + 1, coluna);
			}

			if (coluna < 19){
				graph.addEdge(i, i+1, linha, coluna, linha, coluna + 1);
			}

			if (coluna == 19){
				linha++;
				coluna = 0;
			}
			else{
				coluna++;
			}
		}
	}

	public void limparTabela(){
		ambiente.removeAll();
		matrizAdj = new Celula[7][20];
		for (int l = 0; l < 7;l++){
			for(int c = 0; c < 20; c++){
				Celula celula = new Celula(l, c, false);
				celula.getBotao().setContentAreaFilled(false);
				celula.getBotao().setIcon(imLivre);
				celula.getBotao().addActionListener(new eventosB(l, c, tipoDado));
				matrizAdj[l][c] = celula;
				ambiente.add(matrizAdj[l][c].getBotao());
				matrizAdj[l][c].getBotao().setBounds(c*50, l*80, 50, 80);
			}
		}
		linhaInicial = -1;
		colunaInicial = -1;
		linhaRobo = -1;
		colunaRobo = -1;
		linhaObjetivo = -1;
		colunaObjetivo = -1;
		this.validate();
	}

	public void abrirArquivo(){
		ambiente.removeAll();
		matrizAdj = manipularArquivo.abrirAmbiente();
		for (int l = 0; l < 7; l++){
			for(int c = 0; c < 20; c++){
				ambiente.add(matrizAdj[l][c].getBotao());
				matrizAdj[l][c].getBotao().setBounds(c*50, l*80, 50, 80);
				matrizAdj[l][c].getBotao().addActionListener(new eventosB(l, c, tipoDado));
			}
		}
		graph = new Graph(matrizAdj, matrizAdj[0][0].linhaInicial, matrizAdj[0][0].colunaInicial, matrizAdj[0][0].linhaObjetivo, matrizAdj[0][0].colunaObjetivo);
		linhaInicial = matrizAdj[0][0].linhaInicial;
		colunaInicial = matrizAdj[0][0].colunaInicial;
		linhaRobo = linhaInicial;
		colunaRobo = colunaInicial;
		linhaObjetivo = matrizAdj[0][0].linhaObjetivo;
		colunaObjetivo = matrizAdj[0][0].colunaObjetivo;
		adcionarArestas();
		chamouProdundidade = false;
		ambiente.validate();
		ambiente.repaint();
	}

	public void mostraProfundidade()
	{
		new Thread() 
		{
			public void run()
			{
				Celula aux;
				matrizAdj[linhaObjetivo][colunaObjetivo].getBotao().setIcon(imPorta);
				if (((linhaRobo != linhaObjetivo) && (colunaRobo != colunaObjetivo))){					
					matrizAdj[linhaRobo][colunaRobo].getBotao().setIcon(imPassou);
				}
				linhaRobo = linhaInicial;
				colunaRobo = colunaInicial;


				for (int i = 1; i < graph.getCaminho().size(); i++)
				{
					try 
					{
						aux = graph.getCaminho().get(i);
						matrizAdj[aux.getLinha()][aux.getColuna()].getBotao().setIcon(imRobo);
						matrizAdj[linhaRobo][colunaRobo].getBotao().setIcon(imPassou);
						linhaRobo = aux.getLinha();
						colunaRobo = aux.getColuna();
						new Thread();
						Thread.sleep(500);
					} catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}	
			}
		}.start();
	}

	public void mostraObjetivo(){
		Celula objetivo = matrizAdj[linhaObjetivo][colunaObjetivo];
		ArrayList<Celula> caminhoAux = new ArrayList<Celula>();
		caminhoCerto = new ArrayList<Celula>();
		while(!(objetivo.getAnterior() == null)){
			caminhoAux.add(objetivo);
			objetivo = objetivo.getAnterior();
		}

		for (int i = caminhoAux.size() - 1; i >= 0; i--){
			caminhoCerto.add(caminhoAux.get(i));
		}

		new Thread() 
		{
			public void run()
			{
				Celula aux;
				matrizAdj[linhaObjetivo][colunaObjetivo].getBotao().setIcon(imPorta);
				if (((linhaRobo != linhaObjetivo) && (colunaRobo != colunaObjetivo))){
					matrizAdj[linhaRobo][colunaRobo].getBotao().setIcon(imPassou);
				}
				linhaRobo = linhaInicial;
				colunaRobo = colunaInicial;

				for (int i = 0; i < caminhoCerto.size(); i++)
				{
					try 
					{
						aux = caminhoCerto.get(i);
						matrizAdj[aux.getLinha()][aux.getColuna()].getBotao().setIcon(imRobo);
						matrizAdj[linhaRobo][colunaRobo].getBotao().setIcon(imCaminhoCerto);
						linhaRobo = aux.getLinha();
						colunaRobo = aux.getColuna();
						new Thread();
						Thread.sleep(500);
					} catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}	
			}
		}.start();
	}

	public void editarAmbiente(){
		jBuExecutar.setVisible(false);
		jBuCaminhoCerto.setVisible(false);
		jBuAmbienteOk.setVisible(true);
		jBuEditarFim.setVisible(true);
		jBuEditarInicio.setVisible(true);
	}

	public static void main(String args[]){
		FramePrincipal frame = new FramePrincipal();
	}
}

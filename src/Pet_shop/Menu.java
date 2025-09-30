package Pet_shop;

import java.util.InputMismatchException;
import java.util.Scanner;

import Pet_shop.model.Medicamento;
import Pet_shop.model.Produto;
import Pet_shop.model.Roupa;
import Pet_shop.util.Cores;
import controller.ProdutoController;

public class Menu {

	private static final Scanner leia = new Scanner(System.in);
	private static Object produtoController;
	int opcao;

	public static void main(String[] args) {

		int opcao;

		while (true) {

			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
					+ "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            PET SHOP CRISTINA                         ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Produto                        ");
			System.out.println("            2 - Listar todos os Produtos             ");
			System.out.println("            3 - Buscar Produto por ID                ");
			System.out.println("            4 - Atualizar Dados do Produto           ");
			System.out.println("            5 - Apagar Produto                       ");
			System.out.println("            0 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);

			try {
				opcao = leia.nextInt();
				leia.nextLine();
			} catch (InputMismatchException e) {
				opcao = -1;
				System.out.println("\nDigite um número inteiro entre 0 e 8");
				leia.nextLine();
			}

			if (opcao == 0) {
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nPet Shop Cristina - Cuidamos com amor do seu Pet!");
				sobre();
				leia.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE + "Criar Produto\n\n");
				cadastrarProduto();

				keyPress();
				break;
			case 2:
				System.out.println(Cores.TEXT_WHITE + "Listar todos os Produtos\n\n");
				listarProdutos();

				keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE + "Consultar dados do Produto - por ID\n\n");
				procurarProdutoPorId();

				keyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE + "Atualizar dados do Produto\n\n");
				atualizarProduto();

				keyPress();
				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE + "Apagar o Produto\n\n");
				deletarProduto();

				keyPress();
				break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
				keyPress();
				break;
			}
		}

	}

	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: Cristina Martins Coelho ");
		System.out.println("github.com/crissmcoelho");
		System.out.println("*********************************************************");
	}

	public static void keyPress() {
		System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para continuar...");
		leia.nextLine();
	}

	private static void criarProdutosTeste() {
		((ProdutoController) produtoController).cadastrar(new Medicamento(((ProdutoController) produtoController).gerarId(), "Dipirona", 1, 5.60f, "generico"));
		
	}

	private static void listarProdutos() {
		ProdutoController produtoController = new ProdutoController();
		produtoController.listarTodos();
	}

	private static void cadastrarProduto() {

		System.out.print("Digite o nome do produto: ");
		String nome = leia.nextLine();

		System.out.print("Digite o Categoria do produto (1 - Medicamento | 2 - Roupa | 3 - Brinqueo | 4 - Ração): ");
		int categoria = leia.nextInt();

		System.out.print("Digite o Preço do produto: ");
		float preco = leia.nextFloat();

		switch (categoria) {
		case 1 -> {
			System.out.print("Digite o mome genérico: ");
			leia.skip("\\R");
			String generico = leia.nextLine();
			
			ProdutoController produtoController = new ProdutoController();
			produtoController.cadastrar(new Medicamento(produtoController.gerarId(), nome, categoria, preco, generico));
		}
		case 2 -> {
			System.out.print("Digite o tamanho: ");
			leia.skip("\\R");
			String tamanho = leia.nextLine();
			
			ProdutoController produtoController = new ProdutoController();
			produtoController.cadastrar(new Roupa(produtoController.gerarId(), nome, categoria, preco, tamanho));
		}	
		default -> System.out.println(Cores.TEXT_RED + "Categoria de produto inválido!" + Cores.TEXT_RESET);
		}

	} 

	private static void procurarProdutoPorId() {

		System.out.print("Digite o Id do produto: ");
		int id = leia.nextInt();
		leia.nextLine();

		((ProdutoController) produtoController).procurarPorId(id);
	}

	private static void deletarProduto() {

		System.out.print("Digite o Id do produto: ");
		int id = leia.nextInt();
		leia.nextLine();

		Produto produto = ProdutoController.buscarNaCollection(id);

		if (produto != null) {

			System.out.print("\nTem certeza que deseja excluir o produto? (S/N): ");
			String confirmacao = leia.nextLine();

			if (confirmacao.equalsIgnoreCase("S")) {
				((ProdutoController) produtoController).deletar(id);
			} else {
				System.out.println("\nOperação cancelada!");
			}

		} else {
			System.out.printf("\nO produto Id %d não foi encontrado!", id);
		}
	}


	private static void atualizarProduto() {

		System.out.print("Digite o Id do produto: ");
		int id = leia.nextInt();
		leia.nextLine();

		Produto produto = ProdutoController.buscarNaCollection(id);

		if (produto != null) {

			String nome = produto.getNome();
			int categoria = produto.getCategoria();
			float preco = produto.getPreco();

			System.out.printf(
					"Nome atual: %s\nDigite o novo nome do Produto (Pressione ENTER para manter o valor atual): ", nome);
			String entrada = leia.nextLine();
			nome = entrada.isEmpty() ? nome : entrada;

			System.out.printf("Preço atual: %.2f\nDigite o novo Preço (Pressione ENTER para manter o valor atual): ",
					preco);
			entrada = leia.nextLine();
			preco = entrada.isEmpty() ? preco : Float.parseFloat(entrada.replace(",", "."));

			switch (categoria) {
			case 1 -> {
				String generico = ((Medicamento) produto).getGenerico();
				
				System.out.printf(
						"Generico atual é: %s\nDigite o novo Nome Genérico (Pressione ENTER para manter o valor atual): ",
						generico);
				entrada = leia.nextLine();
				generico = entrada.isEmpty() ? generico : entrada;
				((ProdutoController) produtoController).atualizar(new Medicamento(id, nome, categoria, preco, generico));

			}

			case 2 -> {
				String tamanho = ((Roupa) produto).getTamanho();

				System.out.printf(
						"Tamanho atual é: %s\nDigite o tamanho (Pressione ENTER para manter o valor atual): ",
						tamanho);
				entrada = leia.nextLine();
				tamanho = entrada.isEmpty() ?tamanho : entrada;
				((ProdutoController) produtoController).atualizar(new Roupa(id, nome, categoria, preco, tamanho));
			}
			default -> System.out.println(Cores.TEXT_RED + "Categoria de produto inválido!" + Cores.TEXT_RESET);
			}

		} else {
			System.out.printf("\nA produto número %d não foi encontrado!", id);
		}
	}

}
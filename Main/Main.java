import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LeitorExpressao leitor = new LeitorExpressao();
        ConversorPosFixa conversor = new ConversorPosFixa();
        Scanner scannerTeclado = new Scanner(System.in);
        char continuar = 's';

        System.out.println("=== AVALIADOR DE EXPRESSÕES MATEMÁTICAS ===");

        while (continuar == 's' || continuar == 'S') {
            System.out.println("\n-----------------------------------------");
            
            // 1. Captura e valida a expressão infixa
            String expressaoInfixa = leitor.capturarExpressaoValida();
            
            System.out.println("\n[Sucesso] Expressão Infixa Válida: " + expressaoInfixa);
            
            // 2. Converte para pós-fixa
            String expressaoPosFixa = conversor.converter(expressaoInfixa);
            System.out.println("Expressão Pós-fixa (NPI):       " + expressaoPosFixa);
            
            // 3. Avalia o valor numérico (retornando double)
            double resultado = AvaliadorPosFixa.avaliar(expressaoPosFixa);
            System.out.printf("Valor Numérico Avaliado:       %.2f%n", resultado);

            // Pergunta se deseja testar outra
            System.out.print("\nDeseja testar outra expressão? (s/n): ");
            String resposta = scannerTeclado.nextLine().trim();
            if (!resposta.isEmpty()) {
                continuar = resposta.charAt(0);
            } else {
                continuar = 'n';
            }
        }
        System.out.println("-----PROGRAMA ENCERRADO------");
        scannerTeclado.close();
    }
}

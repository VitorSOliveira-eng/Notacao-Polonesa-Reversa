import java.util.Scanner;

public class LeitorExpressao {
    private Scanner scanner;

    public LeitorExpressao() {
        this.scanner = new Scanner(System.in);
    }

    public String capturarExpressaoValida() {
        while (true) {
            System.out.print("Digite a expressão matemática (sem espaços): ");
            String expressao = scanner.nextLine();

            if (!temCaracteresValidos(expressao)) {
                System.out.println("Erro: A expressão contém caracteres inválidos.");
                continue;
            }

            if (!temParentesesBalanceados(expressao)) {
                System.out.println("Erro: Os parênteses não estão balanceados corretamente.");
                continue;
            }

            if (!temSintaxeCorreta(expressao)) {
                System.out.println("Erro: Erro de sintaxe (ex: operadores juntos, posições incorretas).");
                continue;
            }

            return expressao;
        }

    private boolean temCaracteresValidos(String expressao) {
    for (int i = 0; i < expressao.length(); i++) {
        char c = expressao.charAt(i);
        if (c >= '0' && c <= '9' || c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
        } else {
             return false;
         }
        }
        return true;
    }
    }

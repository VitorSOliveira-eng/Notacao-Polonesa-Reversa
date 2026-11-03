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
    private boolean temParentesesBalanceados(String expressao) {
        TadPilha pilha = new TadPilha();
        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);
            if (c == '(') {
                pilha.push(1);
            }
            if (c == ')') {
                if (pilha.estaVazio()) {
                    return false;
                }
                pilha.pop();
            }
        }
        if (pilha.estaVazio()) {
            return true;
        } else {
            return false;
        }
    }


    private boolean temSintaxeCorreta(String expressao) {
        char primeiro = expressao.charAt(0);
        if (primeiro == '+' || primeiro == '-' || primeiro == '*' || primeiro == '/' || primeiro == '^' || primeiro == ')') {
            return false;
        }
        char ultimo = expressao.charAt(expressao.length() - 1);
        if (ultimo == '+' || ultimo == '-' || ultimo == '*' || ultimo == '/' ||ultimo == '^' || ultimo == '(') {
            return false;
        }
        for (int i = 0; i < expressao.length() -1 ; i++) {
            char atual = expressao.charAt(i);
            char proximo = expressao.charAt(i+1);
            if(atual == '('){
            if (proximo == '+' || proximo == '-' || proximo == '*' || proximo == '/' || proximo == '^' || proximo == ')'){
                return false;
            }
            }
            if(atual == ')'){
                if(proximo >= '0' && proximo <= '9' || proximo == '('){
                    return false;
                }
            }
            if (atual == '+' || atual == '-' || atual == '*' || atual == '/' || atual == '^' ){
                if(proximo == '+' || proximo == '-' ||proximo == '*' || proximo== '/' || proximo == '^' || proximo == ')'){
                    return false;
                }
            }
        }

        return true;
    }
    }

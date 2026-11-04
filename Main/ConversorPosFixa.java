public class ConversorPosFixa {

    private int obterPrecedencia(char operador) {
        if (operador == '^') {
            return 3;
        } else if (operador == '*' || operador == '/') {
            return 2;
        } else if (operador == '+' || operador == '-') {
            return 1;
        }
        return 0;
    }

    public String converter(String expressao) {
        TadPilha<Character> pilha = new TadPilha<>();
        String resultado = "";

        for (int i = 0; i < expressao.length(); i++) {
            char atual = expressao.charAt(i);

            if (atual >= '0' && atual <= '9') {
                resultado += atual;
            } else if (atual == '(') {
                pilha.push(atual);

            } else if (atual == ')') {
                while (pilha.estaVazio() != true && pilha.peek() != '(') {
                    resultado += pilha.pop();
                }
                if (pilha.estaVazio() != true && pilha.peek() == '(') {
                    pilha.pop();
                }

            } else if (atual == '+' || atual == '-' || atual == '*' || atual == '/' || atual == '^') {
                while (pilha.estaVazio() != true && pilha.peek() != '(') {
                    int precTopo = obterPrecedencia(pilha.peek());
                    int precAtual = obterPrecedencia(atual);

                    if (precTopo > precAtual || (precTopo == precAtual && atual != '^')) {
                        resultado += pilha.pop();
                    } else {
                        break;
                    }
                }

                pilha.push(atual);
            }
        }

        while (pilha.estaVazio() != true) {
            resultado += pilha.pop();
        }
        return resultado;
    }
}

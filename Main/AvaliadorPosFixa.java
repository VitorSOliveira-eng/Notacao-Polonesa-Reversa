public class AvaliadorPosFixa {

    public static int avaliar(String posFixa) {
        TadPilha<Integer> pilha = new TadPilha<>();

        for (int i = 0; i < posFixa.length(); i++) {
            char c = posFixa.charAt(i);

            // Se for operando (número), converte para int e empilha
            if (c >= '0' && c <= '9') {
                pilha.push(c - '0');
            } 
            // Se for operador, desempilha dois operandos e calcula
            else {
                int b = pilha.pop();
                int a = pilha.pop();
                int resultado = 0;

                switch (c) {
                    case '+':
                        resultado = a + b;
                        break;
                    case '-':
                        resultado = a - b;
                        break;
                    case '*':
                        resultado = a * b;
                        break;
                    case '/':
                        resultado = a / b;
                        break;
                    case '^':
                        resultado = (int) Math.pow(a, b);
                        break;
                }
                pilha.push(resultado);
            }
        }

        return pilha.pop();
    }
}
public class AvaliadorPosFixa {

    public static double avaliar(String posFixa) {
        TadPilha<Double> pilha = new TadPilha<>();

        for (int i = 0; i < posFixa.length(); i++) {
            char c = posFixa.charAt(i);

            // Se for operando (número), converte para double e empilha
            if (c >= '0' && c <= '9') {
                pilha.push((double) (c - '0'));
            } 
            // Se for operador, desempilha dois operandos e calcula
            else {
                double b = pilha.pop();
                double a = pilha.pop();
                double resultado = 0.0;

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
                        resultado = Math.pow(a, b);
                        break;
                }
                pilha.push(resultado);
            }
        }

        return pilha.pop();
    }
}

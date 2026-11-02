public class TadPilha {
    private int topo;
    private static final int TAMANHO = 100;
    private int[] elementos;

    public TadPilha() {
        this.elementos = new int[TAMANHO];
        this.topo = -1;
    }

    public boolean estaCheio() {
        return this.topo == (TAMANHO - 1);
    }

    public boolean estaVazio() {
        return this.topo == -1;
    }

    public void push(int elemento) {
        if (estaCheio()) {
            throw new IllegalStateException("Erro: A pilha está cheia!");
        }

        this.topo++;
        this.elementos[this.topo] = elemento;
    }

    public int pop() {
        if (estaVazio()) {
            throw new IllegalStateException("Erro: A pilha está vazia");
        }

    
        this.topo--;
        return this.elementos[this.topo+1];
    }
}

public class TadPilha<T> {
    private int topo;
    private static final int TAMANHO = 100;
    private Object[] elementos;

    public TadPilha() {
        this.elementos = new Object[TAMANHO];
        this.topo = -1;
    }

    public boolean estaCheio() {
        return this.topo == (TAMANHO - 1);
    }

    public boolean estaVazio() {
        return this.topo == -1;
    }

    public void push(T elemento) {
        if (estaCheio()) {
            throw new IllegalStateException("Erro: A pilha está cheia!");
        }

        this.topo++;
        this.elementos[this.topo] = elemento;
    }

    public T pop() {
        if (estaVazio()) {
            throw new IllegalStateException("Erro: A pilha está vazia");
        }

    
        this.topo--;
        return (T) this.elementos[this.topo+1];
    }
     
    public T peek() {
        if (estaVazio()) {
            throw new IllegalStateException("Erro: A pilha está vazia");
        }
        return (T) this.elementos[this.topo];
    }
}

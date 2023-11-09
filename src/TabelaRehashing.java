import java.util.Random;
import java.lang.Math;

public class TabelaRehashing {
    private int[] tabela;
    private int chavesLivres;
    private long colisoes;
    private Random rand;
    private int hashingFunc;
    public TabelaRehashing(int tamanho, int hashingFunc) {
        tabela = new int[tamanho];
        chavesLivres = tamanho;
        colisoes = 0;
        rand = new Random(219727);
        this.hashingFunc = hashingFunc;
    }

    public long getColisoes() {
        return colisoes;
    }

    public void inserir(int chave) {
        if (chavesLivres > 0) {
            int i = hashing(chave);
            while (tabela[i] != 0) {
                colisoes++;
                i = rehashing(i);
            }
            tabela[i] = chave;
            chavesLivres--;
        }
    }

    public int consultar(int chave) {
        int i = hashing(chave);
        int buscas = 1;
        int comparacoes = 1;
        while (tabela[i] != chave && tabela[i] != 0 && buscas < tabela.length) {
            i = rehashing(i);
            buscas++;
        }
        if (tabela[i] == 0 || buscas == tabela.length){
            System.out.println("Valor nao pertence a tabela!");
            return -1;
        } else {
            System.out.printf("[%d] %d\n", i, tabela[i]);
            return buscas;
        }
    }

    private int hashing(int chave) {
        int hash;
        switch (hashingFunc) {
            case 0:
                hash = chave % tabela.length;
                if (hash < 0) {
                    hash *= -1;
                }
                return hash;
            case 1:
                hash = (int) (((chave * 0.75267) % 1) * tabela.length);
                if (hash < 0) {
                    hash *= -1;
                }
                return hash;
            case 2:
                if (chave < 0) {
                    chave *= -1;
                }
                hash = (int) (((Math.sqrt(chave)) % 1) * tabela.length);
                return hash;
        }
        return 0;
    }
    private int rehashing(int chave) {
        int rehash = chave + 1;
        if (rehash == tabela.length) {
            rehash = 0;
        }
        return rehash;
    }

    public void fill(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            this.inserir(rand.nextInt(999999999));
        }
    }
    public void imprimir() {
        for (int i = 0; i < tabela.length; i++) {
            System.out.printf("[%d] %d\n", i, tabela[i]);
        }
    }
}

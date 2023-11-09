import java.util.Random;

public class TabelaEncadeada {
    public Registro[] tabela;
    public long colisoes;
    private Random rand;
    private int hashingFunc;
    public TabelaEncadeada(int tamanho, int hashingFunc) {
        tabela = new Registro[tamanho];
        colisoes = 0;
        rand = new Random(219727);
        this.hashingFunc = hashingFunc;
    }

    public long getColisoes() {
        return colisoes;
    }

    public void inserir(int chave) {
        int i = hashing(chave);
        Registro registro = new Registro(chave);
        if (tabela[i] == null) {
            tabela[i] = registro;
        } else {
            colisoes += tabela[i].setProximo(registro);
        }
    }

    public int consultar(int chave) {
        int i = hashing(chave);
        int indiceLista = 0;
        int comparacoes = 0;
        Registro registro = tabela[i];
        while (registro != null && registro.getChave() != chave) {
            registro = registro.getProximo();
            comparacoes++;
            indiceLista++;
        }
        if (registro == null || registro.getChave() != chave) {
            System.out.println("Valor nao pertence a tabela!");
        } else if (registro.getChave() == chave) {
            System.out.printf("[%d][%d] %d\n", i, indiceLista, registro.getChave());;
            return comparacoes;
        }
        return -1;
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
                hash = (int) (((chave * 0.357840) % 1) * tabela.length);
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

    public void fill(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            this.inserir(rand.nextInt(999999999));
        }
    }

    public void imprimir() {
        for (int i = 0; i < tabela.length; i++) {
            Registro registro = tabela[i];
            String string = "["+i+"] ";
            if (tabela[i] == null) {
                string += "null";
            } else {
                string += registro.imprimir();
            }
            System.out.println(string);
        }
    }
}

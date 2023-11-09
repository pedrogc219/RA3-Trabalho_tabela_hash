public class Registro {
    private int chave;
    private Registro proximo;
    public Registro(int chave) {
        this.chave = chave;
        this.proximo = null;
    }

    public int getChave() {
        return chave;
    }

    public Registro getProximo() {
        return proximo;
    }
    public int setProximo(Registro registro) {
        if (this.proximo == null) {
            this.proximo = registro;
            return 1;
        } else if (registro.getChave() < proximo.getChave()){
            registro.setProximo(proximo);
            proximo = registro;
            return 1;
        } else {
            return 1+proximo.setProximo(registro);
        }
    }

    public String imprimir() {
        if (proximo == null){
            return chave+" ";
        } else {
            return chave+" "+proximo.imprimir();
        }
    }
}

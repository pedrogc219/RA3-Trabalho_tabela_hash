import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedWriter;


public class TabelasHash {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String[] funcoesHash ={"Divisao", "Multiplicacao", "Raiz"};
        int[] tamanhoTabela = {1000, 10000, 50000, 100000, 500000};
        int[] quantidadeRegistros = {20000, 100000, 500000, 1000000, 5000000};
        long tempoInicioExecucao;
        long tempoFimExecucao;
        long tempoExecucao;

        /*
        [499261] = 793144713
        [72874] = 581551030
        [289271] = 316201999
        [114115] = 87567398
        [52460] = 568411206
        [29510] = 61693151
        [246525] = 936150174
        [318293] = 460033506

        [2993409] = 986746453
        [1819703] = 608245275
        [3840157] = 804928231
        [4151632] = 855625573
        [4834490] = 151362367
         */
        int[] valoresConsulta = {793144713, 581551030, 316201999, 87567398, 568411206, 61693151,
                936150174, 460033506, 986746453, 608245275, 804928231, 855625573, 151362367};


//        TABELA REHASHING
        FileWriter fwRehashing = new FileWriter("output/rehashingInsercao3.txt");
        BufferedWriter bwRehashing = new BufferedWriter(fwRehashing);
        bwRehashing.write("Funcao hash,Tamanho da tabela,Numero de elementos,Tempo(ns),Colisoes");
        bwRehashing.newLine();


        FileWriter fwRehashing2 = new FileWriter("output/rehashingConsulta3.txt");
        BufferedWriter bwRehashing2 = new BufferedWriter(fwRehashing2);
        bwRehashing2.write("Funcao hash,Tamanho da tabela,Numero de elementos,Chave,Tempo(ns),Comparacoes");
        bwRehashing2.newLine();

        TabelaRehashing tabelaRehashing;
        for (int iFuncao = 0; iFuncao < 3; iFuncao++) {
            for (int tamanho: tamanhoTabela) {
//                for (int quantidade: quantidadeRegistros) {
                    tabelaRehashing = new TabelaRehashing(tamanho, iFuncao);

                    tempoInicioExecucao = System.nanoTime();
                    tabelaRehashing.fill(tamanho);
                    tempoFimExecucao = System.nanoTime();
                    tempoExecucao = tempoFimExecucao - tempoInicioExecucao;

                    bwRehashing.write(funcoesHash[iFuncao]+","+tamanho+","+tamanho+","+tempoExecucao+","+tabelaRehashing.getColisoes());
                    bwRehashing.newLine();


                    if (tamanho == 500000) {
                        for (int consulta: valoresConsulta) {
                            tempoInicioExecucao = System.nanoTime();
                            int comparacoes = tabelaRehashing.consultar(consulta);
                            tempoFimExecucao = System.nanoTime();
                            tempoExecucao = tempoFimExecucao - tempoInicioExecucao;
                            bwRehashing2.write(funcoesHash[iFuncao]+","+tamanho+","+tamanho+","+consulta+","+tempoExecucao+","+comparacoes);
                            bwRehashing2.newLine();

                        }
                    }
                    tabelaRehashing = null;

//                }
            }
        }
        bwRehashing.close();
        bwRehashing2.close();


//        TABELA ENCADEADA
        FileWriter fwEncadeada = new FileWriter("output/encadeadaInserca3.txt");
        BufferedWriter bwEncadeada = new BufferedWriter(fwEncadeada);
        bwEncadeada.write("Funcao hash,Tamanho da tabela,Numero de elementos,Tempo(ns),Colisoes");
        bwEncadeada.newLine();

        FileWriter fwEncadeada2 = new FileWriter("output/encadeadaComparacao3.txt");
        BufferedWriter bwEncadeada2 = new BufferedWriter(fwEncadeada2);
        bwEncadeada2.write("Funcao hash,Tamanho da tabela,Numero de elementos,Chave,Tempo(ns),Comparacoes");
        bwEncadeada2.newLine();


        TabelaEncadeada tabelaEncadeada;
        for (int iFuncao = 2; iFuncao < 3; iFuncao++) {
            for (int tamanho: tamanhoTabela) {
                for (int quantidade: quantidadeRegistros) {
                    tabelaEncadeada = new TabelaEncadeada(tamanho, iFuncao);

                    tempoInicioExecucao = System.nanoTime();
                    tabelaEncadeada.fill(quantidade);
                    tempoFimExecucao = System.nanoTime();
                    tempoExecucao = tempoFimExecucao - tempoInicioExecucao;

                    bwEncadeada.write(funcoesHash[iFuncao]+","+tamanho+","+quantidade+","+tempoExecucao+","+tabelaEncadeada.getColisoes());
                    bwEncadeada.newLine();


                    if (tamanho == 500000) {
                        for (int consulta: valoresConsulta) {
                            tempoInicioExecucao = System.nanoTime();
                            int comparacoes = tabelaEncadeada.consultar(consulta);
                            tempoFimExecucao = System.nanoTime();
                            tempoExecucao = tempoFimExecucao - tempoInicioExecucao;
                            bwEncadeada2.write(funcoesHash[iFuncao]+","+tamanho+","+quantidade+","+consulta+","+tempoExecucao+","+comparacoes);
                            bwEncadeada2.newLine();

                        }
                    }
                    tabelaEncadeada = null;
                }
            }
        }
        bwEncadeada.close();
        bwEncadeada2.close();
    }
}

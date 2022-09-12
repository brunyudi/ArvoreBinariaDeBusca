/**
 Aluno: Bruno Yudi Mino Okada

 Sua tarefa será construir uma árvore binária de busca, lembrando que árvore binária de busca
 são árvores que possuem no máximo dois filho de tal forma que o valor utilizado como chave, ou índice
 da árvore deve seguir a seguinte regra:
 O índice do filho à esquerda será menor que o índice do vértice pai (raiz) e o índice deste vértice
 pai será menor que o índice do filho à direita.
 A  árvore  deverá  ser  gerada  de  forma  aleatória  e  deve  possuir  um  número  de  vértices
 aleatoriamente selecionado entre 10 e 26 vértices. Para criar esta árvore você irá criar um array capaz
 de armazenar uma quantidade de itens equivalente ao número de vértices da árvore que será gerada
 e preencher estes vértices com valores aleatórios entre 1 e 1000. Este array será usado para montar a
 árvore binária de busca.
 Você deverá criar uma regra de montagem para varrer o array e colocar seus valores nos
 vértices da árvore. Com a árvore binária de busca montada, no terminal, você deverá apresentar as
 seguintes saídas:
 a) A impressão do array gerado no formato apresentado a seguir para apenas 5 vertices:
        Array Gerado: [1, 12, 45, 3, 6]
 b) A impressão da árvore em ASCII usando o modelo a seguir que mostra uma árvore com o
 valor 12 na raiz, com dois filhos com os valores 5 e 1. Nesta árvore o vértice de valor 5 possui
 dois filhos com valores 11 e 2.
        12
            +5
                +11
                +2
            +1
 Observe que os nós estão sendo deslocados horizontalmente por meio do caracter tab
 “\t” de forma que os vértices filhos estão deslocados para direita de um tab em relação
 ao seu pai. Todas as linhas começam com + para indicar que é um vértice.
 c) A  impressão  dos  valores  armazenados  no  vértices  da  árvore  usando  os  algoritmos  de
 transversalidade in-order, pre-order e post-order, segundo os modelos a seguir nos quais os
 valores utilizados são meramente ilustrativos:
        In-order: {D, B, E, A, F, C, G}
        Pre-order: {A, B, D, E, C, F, G}
        Post-order: {D, E, B, F, G, C, A}
 d) Uma lista dos valores armazenados em todas as folhas da árvore gerada segundo o modelo
 a seguir, para apenas 5 folhas:
        Folhas: [1, 5, 7, 8, 12]

 e) Uma lista do resultado da busca de 10 valores aleatoriamente gerados entre 300 e 575 e o
 diagnóstico da existência, ou não, deste número na árvore gerada. Este diagnóstico deve ser
 feito com as palavras: presente e ausente, conforme o modelo a seguir para dois valores :
        Valor gerado: 312; Diagnóstico: Presente
        Valor gerado: 542; Diagnóstico: Ausente
 Observação, os valores utilizados para criar o array serão gerados de forma aleatória,
 sendo assim, não há nenhum problema que existam valores repetidos nos vértices da árvore. Além
 disso, lembre-se uma estrutura de dados precisa da definição de uma arquitetura de memória e de
 no mínimo, um método para inclusão, um para exclusão e um para busca.
 A  operação  desta  árvore  uma  vez  montada,  será  validada  por  meio  de  um  conjunto  de
 operações de busca, inserção e exclusão. Para validar estas operações você deverá criar uma função de
 validação, segundo a seguinte funcionalidade.
 A função de validação chamada de valida, deve receber uma árvore binária e devolver True
 se a árvore fornecida for uma árvore binária de busca e False caso não seja uma árvore binária de busca.
 Para  validar  sua  ávore  você  deverá  inserir  10  vértices  novos,  cujos  valores  sejam
 aleatoriamente  gerados,  usando  o  mesmo  algoritmo  de  geração  de  números  randômicos.  A  cada
 inserção  devem  ser  medidos  os  temos  de  inserção  e  a  validade  da  árvore  resultante.  A  saída  deste
 processo será indicada por uma saída na forma:
        Inserções: 10; ávores geradas 10; árvores validadas 10.
 Todas as árvores geradas devem ser impressas segundo a regra explicita neste documento.
 Para garantir que as rotinas de inserção e exclusão estejam corretas você também precisará fazer
 10 exclusões a partir de números aleatórios. Você deverá gerar números aleatórios até que o número
 gerado tenha sido incluído na árvore original, ou esteja disponível na árvore depois dos processos de
 inclusão. A exclusão será validada pela saída indicada a seguir:
        Exclusões: 10; ávores geradas 10; árvores validadas 10.
 */

import java.util.ArrayList;

class Vertice{
    int valor;
    Vertice direito;
    Vertice esquerdo;
}

class ArvoreBinaria{

    ArrayList<Integer> arrayFolhas = new ArrayList<>();
    ArrayList<Integer> arrayInOrder = new ArrayList<>();
    ArrayList<Integer> arrayPreOrder = new ArrayList<>();
    ArrayList<Integer> arrayPostOrder = new ArrayList<>();


    //Cria um novo vértice para a árvore com o valor dado
    public Vertice novoVertice (int valor){
        Vertice vertice = new Vertice();
        vertice.valor = valor;
        vertice.direito = null;
        vertice.esquerdo = null;
        return vertice;
    }

    //Insere um novo vértice na árvore
    public Vertice inserir(Vertice vertice, int val){
        if (vertice == null){
            return novoVertice(val);
        }
        if (val < vertice.valor){
            vertice.esquerdo = inserir(vertice.esquerdo, val);
        } else if ((val > vertice.valor)) {
            vertice.direito = inserir(vertice.direito, val);
        }
        return vertice;
    }

    //Deleta um vértice da árvore e arruma o modelo
    public Vertice deletar(Vertice vertice, int val){
        if (vertice == null){
            return null;
        }
        if (val < vertice.valor){
            vertice.esquerdo = deletar(vertice.esquerdo, val);
        } else if (val > vertice.valor){
            vertice.direito = deletar(vertice.direito, val);
        } else {
            if (vertice.esquerdo == null || vertice.direito == null){
                Vertice temp = vertice.esquerdo != null ? vertice.esquerdo : vertice.direito;

                if (temp == null){
                    return null;
                } else{
                    return temp;
                }
            } else {
                Vertice sucessor = getSucesssor(vertice);
                vertice.valor = sucessor.valor;
                vertice.direito = deletar(vertice.direito, sucessor.valor);
                return vertice;
            }
        }
        return vertice;
    }

    //Pega o vertice sucessor
    public Vertice getSucesssor (Vertice vertice){
        if (vertice == null){
            return null;
        }
        Vertice temp = vertice.direito;
        while (temp.esquerdo != null){
            temp = temp.esquerdo;
        }
        return temp;
    }

    //Ordena a árvore em InOrder
    public void inOrder(Vertice vertice){
        if (vertice == null){
            return;
        }
        inOrder(vertice.esquerdo);
        arrayInOrder.add(vertice.valor);
        inOrder(vertice.direito);
    }

    //Ordena a árvore em PreOrder
    public void preOrder(Vertice vertice){
        if (vertice == null){
            return;
        }
        arrayPreOrder.add(vertice.valor);
        preOrder(vertice.esquerdo);
        preOrder(vertice.direito);
    }

    //Ordena a árvore em PostOrder
    public void postOrder(Vertice vertice){
        if (vertice == null){
            return;
        }
        postOrder(vertice.esquerdo);
        postOrder(vertice.direito);
        arrayPostOrder.add(vertice.valor);
    }

    //Verifica a existencia de um vértice na árvore
    public boolean buscarVertice(Vertice vertice, int val){
        if (vertice == null){
            return false;
        }
        boolean existe = false;

        while (vertice != null){
            if (val < vertice.valor){
                vertice = vertice.esquerdo;
            } else if (val > vertice.valor) {
                vertice = vertice.direito;
            } else {
                existe = true;
                break;
            }
        }
        return existe;
    }

    //Encontra as folhas da árvore
    public void pegarFolhas(Vertice vertice){
        if (vertice == null){
            return;
        }
        if (vertice.esquerdo == null && vertice.direito == null){
            arrayFolhas.add(vertice.valor);
        }
        if (vertice.esquerdo != null){
            pegarFolhas(vertice.esquerdo);
        }
        if (vertice.direito != null){
            pegarFolhas(vertice.direito);
        }
    }

    //Imprime a árvore em 2D em PreOrder
    public void imprimirArvore(String prefixo, Vertice vertice, boolean filhoDaEsquerda){
        if (vertice != null){
            System.out.println(prefixo + (filhoDaEsquerda ? "\t" : "\t") + "+" + vertice.valor);
            imprimirArvore(prefixo + (filhoDaEsquerda ? "\t" : "\t"), vertice.esquerdo, true);
            imprimirArvore(prefixo + (filhoDaEsquerda ? "\t" : "\t"), vertice.direito, false);
        }
    }

    Vertice anterior = null;
    //Verifica se a árvore binária é uma árvore binária de busca ou não
    public boolean valida(Vertice vertice){
        if (vertice == null){
            return true;
        }
        if (!valida(vertice.esquerdo)){
            return false;
        }
        if (anterior != null && vertice.valor < anterior.valor){
            return true;
        }
        anterior = vertice;
        return valida(vertice.direito);
    }
}

public class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
        Vertice raiz = null;
        int minVertices = 10;
        int maxVertices = 26;
        int valorMinVertices = 1;
        int valorMaxVertices = 1000;
        int minBusca = 300;
        int maxBusca = 575;
        ArrayList<Integer> arrayVertices = new ArrayList<>();

        //Gera um número aleatório entre 10 e 26 para a quantidade de vértices
        int quantidadeVertices = (int)Math.floor(Math.random()*(maxVertices-minVertices+1)+minVertices);

        //Gera números aleatórios entre 1 e 1000 para serem os N vértices da árvore
        for (int i = 1; i <= quantidadeVertices; i++){
            int vert = (int)Math.floor(Math.random()*(valorMaxVertices-valorMinVertices+1)+valorMinVertices);
            arrayVertices.add(vert);
        }
        System.out.println("Array Gerado: " + arrayVertices);

        //Adiciona os números gerados anteriormente como os vértices da árvore
        for (int i = 0; i <= arrayVertices.size()-1; i++){
            raiz = arvoreBinaria.inserir(raiz, arrayVertices.get(i));
        }

        //Imprime a árvore em 2D em PreOrder e com os vértices filhos espaçados com um tab(\t) do vértice raiz
        arvoreBinaria.imprimirArvore("",raiz,false);

        //Ordena a árvore em InOrder e imprime
        arvoreBinaria.inOrder(raiz);
        System.out.println("In-order: " + arvoreBinaria.arrayInOrder.toString().replace("[","{").replace("]","}"));

        //Ordena a árvore em PreOrder e imprime
        arvoreBinaria.preOrder(raiz);
        System.out.println("Pre-order: " + arvoreBinaria.arrayPreOrder.toString().replace("[","{").replace("]","}"));

        //Ordena a árvore em PostOrder e imprime
        arvoreBinaria.postOrder(raiz);
        System.out.println("Post-order: " + arvoreBinaria.arrayPostOrder.toString().replace("[","{").replace("]","}"));

        //Encontra os vértices folha da árvore e imprime
        arvoreBinaria.pegarFolhas(raiz);
        System.out.println("Folhas: " + arvoreBinaria.arrayFolhas);

        //Gera 10 valores aleatórios entre 300 e 575 e verifica se está presente ou não na árvore
        for (int i = 1; i <= 10; i++){
            int valBusca = (int)Math.floor(Math.random()*(maxBusca-minBusca+1)+minBusca);
            boolean diagnostico = arvoreBinaria.buscarVertice(raiz, valBusca);
            if (diagnostico == true){
                System.out.println("Valor gerado: " + valBusca + "; Diagnostico: Presente");
            } else {
                System.out.println("Valor gerado: " + valBusca + "; Diagnostico: Ausente");
            }
        }

        //Inclusão de 10 valores aleatórios entre 1 e 1000 na árvore e verificação se a árvore é binária de busca ou não
        //Também calcula o tempo de inclusão dos valores e verificação da árvore
        int validacaoInsercao = 0;
        for (int x = 1; x <= 10; x++){
            long tempoInicioInsercao = System.currentTimeMillis();
            int vert = (int)Math.floor(Math.random()*(valorMaxVertices-valorMinVertices+1)+valorMinVertices);
            raiz = arvoreBinaria.inserir(raiz, vert);
            if (arvoreBinaria.valida(raiz) == true){
                validacaoInsercao++;
                System.out.println("\nValor inserido: " + vert);
                arvoreBinaria.imprimirArvore("",raiz,false);
                System.out.println("Insercoes: " + x + "; arvores geradas: " + x + "; arvores validadas: " + validacaoInsercao + ".");
            }
            else {
                arvoreBinaria.imprimirArvore("",raiz,false);
                System.out.println("Insercoes: " + x + "; arvores geradas: " + x + "; arvores validadas: " + validacaoInsercao + ".");
            }
            long tempoFinalInsercao = System.currentTimeMillis();
            long tempoInsercao = tempoFinalInsercao - tempoInicioInsercao;
            System.out.println("Tempo de insercao + validacao: " + tempoInsercao + "ms");
        }

        //Apenas para separar as árvores de inclusão das de exclusão
        System.out.println("\n-----------------");

        //Exclusão de 10 valores aléatórios entre 1 e 1000, existentes na árvore, e verificação de a árvore é binária de busca ou não
        //Também calcula o tempo de exclusão dos valores e verificação da árvore
        int validacaoExclusao = 0;
        int exclusao = 0;
        while (exclusao != 10){
            int vert = (int)Math.floor(Math.random()*(valorMaxVertices-valorMinVertices+1)+valorMinVertices);
            boolean busca = arvoreBinaria.buscarVertice(raiz, vert);
            if (busca == true){
                long tempoInicioExclusao = System.currentTimeMillis();
                exclusao++;
                raiz = arvoreBinaria.deletar(raiz, vert);
                if (arvoreBinaria.valida(raiz) == true){
                    validacaoExclusao++;
                    System.out.println("\nValor excluido: " + vert);
                    arvoreBinaria.imprimirArvore("", raiz, false);
                    System.out.println("Exclusoes: " + exclusao + "; arvores geradas: " + exclusao + "; arvores validadas: " + validacaoExclusao + ".");
                }
                else {
                    arvoreBinaria.imprimirArvore("", raiz, false);
                    System.out.println("Exclusoes: " + exclusao + "; arvores geradas: " + exclusao + "; arvores validadas: " + validacaoExclusao + ".");
                }
                long tempoFinalExclusao = System.currentTimeMillis();
                long tempoExclusao = tempoFinalExclusao - tempoInicioExclusao;
                System.out.println("Tempo de exclusao + validacao: " + tempoExclusao + "ms");
            }
        }
    }
}
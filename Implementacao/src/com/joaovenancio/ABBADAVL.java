package com.joaovenancio;

public class ABBADAVL <E extends  IUnificavel>{
    //Atributos:
    private int qtdElementos;
    private NohAB raiz;

    //Construtor:
    public ABBADAVL () {
        this.qtdElementos = 0;
        this.raiz = null;
    }

    //Metodos:
    //Visiveis para o usuario final:
    public void inserir (E dado) {
        if (this.raiz == null) {
            this.raiz = new NohAB(dado);
        } else {
            this.insereRecursivo(dado, this.raiz);
        }
    }

    //Recursivo de insercao:
    private void insereRecursivo(E dado, NohAB raizAtual) {
        if (dado.getID() < raizAtual.getDado().getID()) { //Verificar se ele vai para a esquerda do nodulo atraves da chave do ID do objeto:
            if (raizAtual.getFilhoEsquerdo() == null) {
                raizAtual.setFilhoEsquerdo(new NohAB(dado, raizAtual));
                return;
            } else {
                this.insereRecursivo(dado, raizAtual.getFilhoEsquerdo());
            }
        } else { //Entao eh o filho da direita:
            if (raizAtual.getFilhoDireito() == null) {
                raizAtual.setFilhoDireito(new NohAB(dado, raizAtual));
                return;
            } else {
                this.insereRecursivo(dado, raizAtual.getFilhoDireito());
            }
        }
        //Atualizar os valores do nivel da arvore:
        this.definirAlturaMaxima(raiz); //Como o metodo retorna ao adicionar uma nova folha a arovre, entao so os outros nodulos (NohAB) vao ter sua altura atualizada
        //Verificar o indice de balanceamento da arvore e fazer as alteracoes nescessarias na esturutra:
        int indiceDeBalanceamento = this.balanceamento(raiz);

        //Caso do giro simples a esquerda:
        if (indiceDeBalanceamento > 1 && (raiz.getFilhoDireito().getFilhoDireito() != null)) {
            this.girarEsquerda(raiz);
            return;
        }

        //Caso do giro simples a direita:
        if (indiceDeBalanceamento < -1 && (raiz.getFilhoEsquerdo().getFilhoEsquerdo() != null)) {
            this.girarDireita(raiz);
            return;
        }

        //Caso do giro duplo a esquerda:
        if (indiceDeBalanceamento > 1 && (raiz.getFilhoDireito().getFilhoDireito() != null)) {
            this.girarDireita(raiz.getFilhoDireito());
            this.girarEsquerda(raiz);
            return;
        }

        //Caso do giro duplo a direita:
        if (indiceDeBalanceamento < -1 && (raiz.getFilhoEsquerdo().getFilhoEsquerdo() != null)) {
            this.girarEsquerda(raiz.getFilhoEsquerdo());
            this.girarDireita(raiz);
            return;
        }

        return;
    }

    private void girarEsquerda(NohAB raiz) {
        NohAB nohPai = raiz.getPai();
        if (raiz.equals(this.raiz)) {
            nohPai = this.raiz;
        }
        //Pegar os valores para as trocas:
        NohAB nohDireito = raiz.getFilhoDireito();
        NohAB nohEsquerdoFilhoDireito = nohDireito.getFilhoEsquerdo();

        //Fazer as trocas:
        raiz.setFilhoDireito(nohEsquerdoFilhoDireito);
        nohDireito.setFilhoEsquerdo(raiz);
        if (nohPai.getFilhoDireito().equals(raiz)) {
            nohPai.setFilhoDireito(nohDireito);
        } else {
            nohPai.setFilhoEsquerdo(nohDireito);
        }

        //Checar as alturas:
        nohDireito.setNivelArvore(this.definirAlturaMaxima(nohDireito));
        raiz.setNivelArvore(this.definirAlturaMaxima(raiz));
        nohPai.setNivelArvore(this.definirAlturaMaxima(nohPai));
    }

    private void girarDireita (NohAB raiz) {
        //Pegar os valores para as trocas:
        NohAB nohPai = raiz.getPai();
        if (raiz.equals(this.raiz)) {
            nohPai = this.raiz;
        }
        NohAB nohEsquerdo = raiz.getFilhoEsquerdo();
        NohAB nohDireitoFilhoEsquerdo = nohEsquerdo.getFilhoDireito();

        //Fazer as trocas:
        raiz.setFilhoDireito(nohDireitoFilhoEsquerdo);
        nohEsquerdo.setFilhoDireito(raiz);
        if (nohPai.getFilhoDireito().equals(raiz)) {
            nohPai.setFilhoDireito(nohEsquerdo);
        } else {
            nohPai.setFilhoEsquerdo(nohEsquerdo);
        }

        //Checar as alturas:
        nohEsquerdo.setNivelArvore(this.definirAlturaMaxima(nohEsquerdo));
        raiz.setNivelArvore(this.definirAlturaMaxima(raiz));
        nohPai.setNivelArvore(this.definirAlturaMaxima(nohPai));
    }

    //Metodos de ajuda:

    /**
     * Metodo para sincronizar a altura total da arvore binaria. Ele passa por cada nodulo da arvore verificando qual parte dela eh a maior. Encontrando a maior parte, itera +1 ao valor da altura do nodulo.
     * A ideia eh de nao deixar uma lado da arvore maior que o outro, visto que se eu apenas iterar a altura uma vez, acabarei deixando os valores da altura dos nodulos errados (caso inserir mais de um filho
     * no nodulo).
     * Dessa forma, eu comparo a altura do filho direito com o do esquerdo e vejo qual eh o que tem a maior altura. Descobrindo a maior altura, eu adiciono mais um no valor dela para colocar no nodulo passado por
     * paramentro.
     *
     * @param noh - Noh da Arvore Binaria com o valor da altura relativa à arvore montada.
     * @return valor da altura real das bifurcacoes binarias da minha arvore.
     */
    private int definirAlturaMaxima (NohAB noh) {
        if (noh == null) {
            return 0;
        } else {
            int nivelArvoreDireita;
            int nivelArvoreEsquerda;
            if (noh.getFilhoDireito() == null) {
                nivelArvoreDireita = 0;
            } else {
                nivelArvoreDireita = noh.getFilhoDireito().getNivelArvore();
            }
            if (noh.getFilhoEsquerdo() == null) {
                nivelArvoreEsquerda = 0;
            } else {
                nivelArvoreEsquerda = noh.getFilhoEsquerdo().getNivelArvore();
            }
            return (nivelArvoreDireita > nivelArvoreEsquerda ) ? nivelArvoreDireita : nivelArvoreEsquerda;
        }
    }

    //Verificar se o nodulo estah balanceado, comparando o nível dos filhos no estilo AVL

    /**
     * Metodo que verifica qual eh o estado de balanceamento da arvore recebendo um nodulo como parametro. Se foi retornado -2 ou 2, a arvore precisa ser balanceada.
     *
     * @param noh - Noh da Arvore Binaria
     * @return o indice de balanceamento
     */
    private int balanceamento (NohAB noh) {
        if (noh == null) {
            return 0; //Para nao quebrar o sistema
        } else {
            int alturaDireito;
            int alturaEsquerdo;
            if (noh.getFilhoDireito() == null) { //Recuparar o valor da altura do lado drieito do nodulo
                alturaDireito = -1; //Eh retornado -1 para quando for feito a subtracao, o resultado poder dar -2 ou 2.
            } else {
                alturaDireito = noh.getFilhoDireito().getNivelArvore();
            }
            if (noh.getFilhoEsquerdo() == null) { //Recuparar o valor da altura do lado drieito do nodulo
                alturaEsquerdo = -1;
            } else {
                alturaEsquerdo = noh.getFilhoEsquerdo().getNivelArvore();
            }
            //Retorna o valor do balanceamento:
            return alturaDireito - alturaEsquerdo;
        }
    }

    public void excluir () {

    }

    public void procurar () {

    }

    /**
     * Metodo para percorrer a arvore em pre ordem. Ele para quandoi nao existir mais nenhum no para ser impresso. A partir de um no, ele vai imprimindo todos os outros.
     * Ele começa percorrendo todos os valores da esquerda (que sao os menores) ateh os da direita (os maiores).
     *
     * @param noh - Raiz da arvore.
     */
    public void preOredem (NohAB noh) {
        if (noh != null) {
            System.out.print(noh.getDado().getID() + " ");
            preOredem(noh.getFilhoEsquerdo());
            preOredem(noh.getFilhoDireito());
        }
    }

    public NohAB getRaiz() {
        return raiz;
    }
}

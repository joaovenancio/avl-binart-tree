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
            this.insereRecursivo(dado, this.raiz.getFilhoEsquerdo());
        }
    }

    //Recursivo de insercao:
    private void insereRecursivo(E dado, NohAB raizAtual) {
        if (dado.getID() < raizAtual.getDado().getID()) { //Verificar se ele vai para a esquerda do noh:
            if (raizAtual.getFilhoEsquerdo() == null) {
                raizAtual.setFilhoEsquerdo(new NohAB(dado, raizAtual));

                return;
            } else {
                this.insereRecursivo(dado, raizAtual.getFilhoEsquerdo());
            }
        } else { //EEntao eh o filho da direita:
            if (raizAtual.getFilhoDireito() == null) {
                raizAtual.setFilhoDireito(new NohAB(dado, raizAtual));
                raizAtual.setNivelAVL(raizAtual.getNivelAVL()+1);
                return;
            } else {
                this.insereRecursivo(dado, raizAtual.getFilhoDireito());
            }
        }
    }

    //Metodos de ajuda:
    //Verificar se o nodulo estah balanceado, comparando o nível dos filhos no estilo AVL
    private int balanceamento (NohAB noh) {
        if (noh == null) {
            return 0; //Para nao quebrar o sistema
        } else {
            int alturaDireito;
            int alturaEsquerdo;
            if (noh.getFilhoDireito() == null) { //Recuparar o valor da altura do lado drieito do nodulo
                alturaDireito = 0;
            } else {
                alturaDireito = noh.getFilhoDireito().getNivelAVL();
            }
            if (noh.getFilhoEsquerdo() == null) { //Recuparar o valor da altura do lado drieito do nodulo
                alturaEsquerdo = 0;
            } else {
                alturaEsquerdo = noh.getFilhoEsquerdo().getNivelAVL();
            }
            //Retorna o valor do balanceamento:
            return alturaDireito - alturaEsquerdo;
        }
    }

    public void excluir () {
        
    }

    public void procurar () {

    }

}

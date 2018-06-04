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
    public void inserir (E dado) {
        if (this.raiz == null) {
            this.raiz = new NohAB(dado);
        } else {
            this.insereRecursivo(dado, this.raiz.getFilhoEsquerdo());
        }
    }

    //Recursivos:
    private void insereRecursivo(E dado, NohAB raizAtual) {
        if (dado.getID() < raizAtual.getDado().getID()) { //Verificar se ele vai para a esquerda do noh:
            if (raizAtual.getFilhoEsquerdo() == null) {
                raizAtual.setFilhoEsquerdo(new NohAB(dado, raizAtual));
                raizAtual.setNivelAVL(raizAtual.getNivelAVL()+1);
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

    public void excluir () {

    }

    public void procurar () {

    }

}

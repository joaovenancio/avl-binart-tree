package com.joaovenancio;

import java.util.ArrayList;

public class ABBADAVL <E extends  IUnificavel>{
    //Atributos:
    private int qtdElementos;
    private NohAB raiz;

    //Construtor:
    public ABBADAVL () {
        this.raiz = null;
    }

    //Metodos:
    //--------------------------------------------------INSERIR--------------------------------------------------
    //Visiveis para o usuario final:
    public void inserir (E dado) {
        if (this.raiz == null) {
            this.raiz = new NohAB(dado);
            return;
        } else {
            this.insereRecursivo(dado, this.raiz);
        }
    }

    //Recursivo de insercao:
    private void insereRecursivo(E dado, NohAB raizAtual) {
        if (dado.getID() < raizAtual.getDado().getID()) { //Verificar se ele vai para a esquerda do nodulo atraves da chave do ID do objeto:
            if (raizAtual.getFilhoEsquerdo() == null) {
                raizAtual.setFilhoEsquerdo(new NohAB(dado, raizAtual));
            } else {
                this.insereRecursivo(dado, raizAtual.getFilhoEsquerdo());
            }
        } else { //Entao eh o filho da direita:
            if (raizAtual.getFilhoDireito() == null) {
                raizAtual.setFilhoDireito(new NohAB(dado, raizAtual));
            } else {
                this.insereRecursivo(dado, raizAtual.getFilhoDireito());
            }
        }
        //Atualizar os valores do nivel da arvore:
        raizAtual.setNivelArvore(this.definirAlturaMaxima(raizAtual)); //Como o metodo retorna ao adicionar uma nova folha a arovre, entao so os outros nodulos (NohAB) vao ter sua altura atualizada
        //Verificar o indice de balanceamento da arvore e fazer as alteracoes nescessarias na esturutra:
        int indiceDeBalanceamento = this.balanceamento(raizAtual);

        //Caso do giro simples a esquerda:
        if (indiceDeBalanceamento > 1 && ( this.balanceamento(raizAtual.getFilhoDireito())  > 0 ) ) {
            this.girarEsquerda(raizAtual);
            return;
        }

        //Caso do giro simples a direita:
        if (indiceDeBalanceamento < -1 && (this.balanceamento(raizAtual.getFilhoEsquerdo() ) < 0 )) {
            this.girarDireita(raizAtual);
            return;
        }

        //Caso do giro duplo a esquerda:
        if (indiceDeBalanceamento > 1 && (this.balanceamento(raizAtual.getFilhoDireito()) < 0 ) ) {
            this.girarDireita(raizAtual.getFilhoDireito());
            this.girarEsquerda(raizAtual);
            return;
        }

        //Caso do giro duplo a direita:
        if (indiceDeBalanceamento < -1 &&  (this.balanceamento(raizAtual.getFilhoEsquerdo() ) > 0 ) ) {
            this.girarEsquerda(raizAtual.getFilhoEsquerdo());
            this.girarDireita(raizAtual);
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
        if (nohEsquerdoFilhoDireito != null) {
            nohEsquerdoFilhoDireito.setPai(raiz); //Atualizar o pai de nohEsquerdoFilhoDireito
        }

        nohDireito.setFilhoEsquerdo(raiz);
        raiz.setPai(nohDireito); //Atualizar o pai da raiz

        //Definir o link entre a arvore mais externa:
        if (raiz.equals(this.raiz)) {
           this.raiz = nohDireito;
        } else {
            if (nohPai.getFilhoDireito().equals(raiz)) {
                nohPai.setFilhoDireito(nohDireito);
            } else {
                nohPai.setFilhoEsquerdo(nohDireito);
            }
        }

        //Atualizar o pai do nohDireito:
        if (this.raiz.equals(nohDireito)) {
            nohDireito.setPai(null);
        } else {
            nohDireito.setPai(nohPai);
        }

        //Checar as alturas:
        if (nohEsquerdoFilhoDireito != null) {
            nohEsquerdoFilhoDireito.setNivelArvore(this.definirAlturaMaxima(nohEsquerdoFilhoDireito));
        }
        raiz.setNivelArvore(this.definirAlturaMaxima(raiz));
        nohDireito.setNivelArvore(this.definirAlturaMaxima(nohDireito));
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
        raiz.setFilhoEsquerdo(nohDireitoFilhoEsquerdo);
        if (nohDireitoFilhoEsquerdo != null) {
            nohDireitoFilhoEsquerdo.setPai(raiz);//Atualizar o pai de nohDireitoFilhoEsquerdo
        }

        nohEsquerdo.setFilhoDireito(raiz);
        raiz.setPai(nohEsquerdo);//Atualizar o pai do raiz

        //Definir o link entre a arvore mais externa:
        if (raiz.equals(this.raiz)) {
            this.raiz = nohEsquerdo;
        } else {
            if (nohPai.getFilhoDireito().equals(raiz)) {
                nohPai.setFilhoDireito(nohEsquerdo);
            } else {
                nohPai.setFilhoEsquerdo(nohEsquerdo);
            }
        }

        //Atualizar o pai do nohEsquerdo:
        if (this.raiz.equals(nohEsquerdo)) {
            nohEsquerdo.setPai(null);
        } else {
            nohEsquerdo.setPai(nohPai);
        }

        //Checar as alturas:
        if (nohDireitoFilhoEsquerdo != null) {
            nohDireitoFilhoEsquerdo.setNivelArvore(this.definirAlturaMaxima(nohDireitoFilhoEsquerdo));
        }
        raiz.setNivelArvore(this.definirAlturaMaxima(raiz)); //A raiz que nao eh mais raiz e sim o novo nohEsquerdo
        nohEsquerdo.setNivelArvore(this.definirAlturaMaxima(nohEsquerdo));
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
            if (noh.getFilhoDireito() == null && noh.getFilhoEsquerdo() == null) { //Caso ambos forem null, nao existem niveis, logo ele vai ser o primeiro nivel (0)
                return 0;
            }
            return (nivelArvoreDireita > nivelArvoreEsquerda ) ? nivelArvoreDireita+1 : nivelArvoreEsquerda+1;
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

    //--------------------------------------------------REMOVER--------------------------------------------------
    /**
     * Método que inicia a remoção de um nó da árvore
     *
     * @param aSerRemovido - Nó que desejamos remover
     */
    public void remover(int aSerRemovido) {
        removerAVL(this.raiz, aSerRemovido);
    }

    /**
     * Método que realiza a busca do nó a ser removido da árvore
     *
     * @param atual        - Nó de referência
     * @param aSerRemovido - Nó a ser removido
     */
    private void removerAVL(NohAB atual, int aSerRemovido) {
        if (atual == null) {
            return;

        } else {

            if (atual.getDado().getID() > aSerRemovido) {
                removerAVL(atual.getFilhoEsquerdo(), aSerRemovido);

            } else if (atual.getDado().getID() < aSerRemovido) {
                removerAVL(atual.getFilhoDireito(), aSerRemovido);

            } else if (atual.getDado().getID() == aSerRemovido) {
                removerNohABEncontrado(atual);
            }
        }
    }

    /**
     * Método que remove o Nó após realizada a busca do nó a ser removido
     *
     * @param aRemover - Nó que será removido da aŕvore
     */
    private void removerNohABEncontrado(NohAB aRemover) {
        //Crio uma varíavel na memória do tipo NohAB que servirá de apoio
        NohAB apoio;

        //Se o Nó a remover não tiver filhos a esquerda e a direta dele
        if (aRemover.getFilhoEsquerdo() == null || aRemover.getFilhoDireito() == null) {
            //Verifico se o Nó a remover não possui pai
            if (aRemover.getPai() == null) {
                //Caso não possua, significa que ele é a raiz da árvore, portanto mudo o apontamento do atributo raiz
                this.raiz = null;
                //Limpo o apontamento da variável do parâmetro
                aRemover = null;
                //Termino o método
                return;
            }
            //Caso não passe no if, a variável de apoio recebe o Nó dado via parâmetro
            apoio = aRemover;
        } else {
            //Então transformo o apoio no sucessor do nó dado como parâmetro
            apoio = sucessor(aRemover);
            //Dou um set nó a ser removido usando o dado do sucessor
            aRemover.setDado(apoio.getDado());
        }

        //Crio outra variável de apoio
        NohAB subArvoreApoio;
        //Caso a esquerda de apoio não estiver vazia
        if (apoio.getFilhoEsquerdo() != null) {
            //subArvoreApoio ganha um apontamento para a subávore a esquerda
            subArvoreApoio = apoio.getFilhoEsquerdo();
            //Caso contrário ele ganha um apontamento para a subárvore a direita
        } else {
            subArvoreApoio = apoio.getFilhoDireito();
        }
        //Se subArvoreApoio não estiver vazio
        if (subArvoreApoio != null) {
            //subArvoreApoio recebe como pai o pai de apoio
            subArvoreApoio.setPai(apoio.getPai());
        }
        //Caso o pai de apoio seja null, subArvoreApoio vira a raiz da árvore toda
        if (apoio.getPai() == null) {
            this.raiz = subArvoreApoio;
            //Caso contrário
        } else {
            //Se apoio for igual ao pai da esquerda
            if (apoio == apoio.getPai().getFilhoEsquerdo()) {
                //apoio chama o pai dele e seta a subArvoreApoio como subárvore a esquerda dele
                apoio.getPai().setFilhoEsquerdo(subArvoreApoio);
                apoio.getPai().setNivelArvore(this.definirAlturaMaxima(apoio.getPai()));
                //Caso contrário
            } else {
                //apoio chama o pai dele e seta subArvoreApoio como subárvore a direita dele
                apoio.getPai().setFilhoDireito(subArvoreApoio);
                apoio.getPai().setNivelArvore(this.definirAlturaMaxima(apoio.getPai()));
            }
            //Verfico o balanceamento passando o pai de apoio como parâmetro
            NohAB raizPivo = apoio.getPai();

            int indiceDeBalanceamento = this.balanceamento(raizPivo);

            //Caso do giro simples a esquerda:
            if (indiceDeBalanceamento > 1 && ( this.balanceamento(raizPivo.getFilhoDireito())  > 0 ) ) {
                this.girarEsquerda(raizPivo);
                return;
            }

            //Caso do giro simples a direita:
            if (indiceDeBalanceamento < -1 && (this.balanceamento(raizPivo.getFilhoEsquerdo() ) < 0 )) {
                this.girarDireita(raizPivo);
                return;
            }

            //Caso do giro duplo a esquerda:
            if (indiceDeBalanceamento > 1 && (this.balanceamento(raizPivo.getFilhoDireito()) < 0 ) ) {
                this.girarDireita(raizPivo.getFilhoDireito());
                this.girarEsquerda(raizPivo);
                return;
            }

            //Caso do giro duplo a direita:
            if (indiceDeBalanceamento < -1 &&  (this.balanceamento(raizPivo.getFilhoEsquerdo() ) > 0 ) ) {
                this.girarEsquerda(raizPivo.getFilhoEsquerdo());
                this.girarDireita(raizPivo);
                return;
            }

        }
        //apago o apontamento de apoio
        apoio = null;
    }

    /**
     * Metodo para percorrer a arvore em pre ordem. Ele para quando nao existir mais nenhum no para ser impresso. A partir de um no, ele vai imprimindo todos os outros.
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

    /**
     * @param nohParaExcluir
     * @return
     */
    private NohAB sucessor(NohAB nohParaExcluir) {
        if (nohParaExcluir.getFilhoDireito() != null) {
            NohAB nohApoio = nohParaExcluir.getFilhoDireito();
            while (nohApoio.getFilhoEsquerdo() != null) {
                nohApoio = nohApoio.getFilhoEsquerdo();
            }
            return nohApoio;
        } else {
            NohAB p = nohParaExcluir.getPai();
            while (p != null && nohParaExcluir == p.getFilhoDireito()) {
                nohParaExcluir = p;
                p = nohParaExcluir.getPai();
            }
            return p;
        }
    }

    //--------------------------------------------------BUSCAR--------------------------------------------------
    /**
     * Metodo que recebe um id e realiza uma busca binaria para achar o Objeto que possui esse parametro como ID. Retorna
     * o Objeto se por acaso ele existir dentro da arvore, caso contrario, ele joga uma execao.
     *
     * @param idElemento
     * @return
     */
    public E buscar (int idElemento) throws RuntimeException {
        if (this.raiz == null) { //Se não exisitr nenhum dado na arvore:
            throw new RuntimeException("Não há nenhum dado nesta Árvore.");//Joga uma execao
        } else { //Se o ID for o da primeira arvore:
            if (this.raiz.getDado().getID() == idElemento) {
                return (E) this.raiz.getDado();
            } else { //Caso não for, procurar recursivamente:
                if (idElemento < this.raiz.getDado().getID()) { //Ver se o elemento eh menor para passar pelo filho da esquerda
                    return this.buscarRecursivo(idElemento, this.raiz.getFilhoEsquerdo());
                } else { //Procurar pelo filho da direita
                    return this.buscarRecursivo(idElemento, this.raiz.getFilhoDireito());
                }
            }
        }
    }

    /**
     * Metodo de auxilio para realizar a busca. Realiza um busca binaria de forma recursiva, chamando ela mesma ate encontrar
     * o dado desejado.
     *
     * @param idProcurar - ID do Objeto que se deseja encontrar
     * @param noh - Noh de referencia para ser feito a comparacao
     * @return o dado que possui a ID de input
     */
    private E buscarRecursivo (int idProcurar, NohAB noh) throws RuntimeException {
        if (noh == null) { //Verificar se o noh do parametro eh nulo
            throw new RuntimeException("Não foi encontrado o elemento de ID "+idProcurar+"."); //Se for, terminar a pesquisa
        } else if (noh.getDado().getID() == idProcurar) { //Se nao for, verificar se eh o dado que estamos procurando
            return (E) noh.getDado(); //Retornar o dado
        } else { //Caso nao for o dado, procurar por ele de acordo com o valor da ID:
            if (idProcurar < noh.getDado().getID()) { //Ver se o elemento eh menor para passar pelo filho da esquerda
                return buscarRecursivo(idProcurar, noh.getFilhoEsquerdo()); //Chamar a recurssao
            } else { //Procurar pelo filho da direita
                return buscarRecursivo(idProcurar, noh.getFilhoDireito()); //Chamar a recurssao
            }
        }
    }
    //--------------------------------------------------------------------------------------------------------------
    /**
     * Método que faz a caminhada in-order na árvore.
     *
     */
    public void emOrdem() {
        // Crio um ArrayList para armazenar os dados da minha árvore
        ArrayList<NohAB> array = new ArrayList<>();
        // Chamo o método que realiza a caminhada pela árvore
        emOrdem(this.raiz, array);
        //Prepara para imprimir os IDs no console:
        int i = 0;
        NohAB noh = array.get(i);
        //Fazer um loop infinito
        while (noh != null) {
            System.out.println(noh.getDado().getID()); //Imprimir o dado
            i++; //Atualizar o iterador
            try { //Se usa try-catch porque no ultimo get, ele vai tentar acessar um index acima do que a ArrayList tem, dando outOfBounds
                noh = array.get(i);
            } catch (IndexOutOfBoundsException e) { //Quando der outOfBounds, ele sai do loop
                System.out.println("Fim."); //Avisa que terminou
                break;
            }
        }
    }


    /**
     * Método que realiza a caminhada in-order na árvore. Ele recebe um ArrayList jah criada, entao fica adicionando a ela
     * os valores em ordem.
     *
     * @param noh Raiz de referência para o ínicio da caminhada in-order
     * @param lista - ArrayList que irá receber os dados da árvore
     */
    private void emOrdem(NohAB noh, ArrayList<NohAB> lista) {
        //Se a raiz é nula, não faço nada
        if (noh == null) {
            return;
        }
        //Pego a árvore a esquerda da minha raiz e chamo o método recursivamente até chegar em uma folha
        emOrdem(noh.getFilhoEsquerdo(), lista);
        //Ao chegar na folha(nível mais profundo) Adiciono a lista o nó que foi passado por parâmetro
        lista.add(noh);
        //Logo em seguida, pego a árvore a direita do nó e chamo o método recursivamente até chegar em uma folha
        emOrdem(noh.getFilhoDireito(), lista);
    }
    
}

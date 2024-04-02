public class ArvorePretoVermelho {
    private NoRB raiz;
    private NoRB TNULL;


    public ArvorePretoVermelho() {
        TNULL = new NoRB(0);
        TNULL.cor = 0;
        TNULL.esquerda = null;
        TNULL.direita = null;
        raiz = TNULL;
    }

    // Função de inserção
    public void inserir(int chave) {
        NoRB no = new NoRB(chave);
        no.pai = null;
        no.esquerda = TNULL;
        no.direita = TNULL;
        no.cor = 1; // novo nó deve ser vermelho

        NoRB y = null;
        NoRB x = this.raiz;

        while (x != TNULL) {
            y = x;
            if (no.chave < x.chave) {
                x = x.esquerda;
            } else {
                x = x.direita;
            }
        }

        // y é o pai de x
        no.pai = y;
        if (y == null) {
            raiz = no;
        } else if (no.chave < y.chave) {
            y.esquerda = no;
        } else {
            y.direita = no;
        }

        // se o novo nó é um nó raiz, simplesmente retorne
        if (no.pai == null) {
            no.cor = 0;
            return;
        }

        // se o avô é nulo, simplesmente retorne
        if (no.pai.pai == null) {
            return;
        }

        // Corrigir a árvore
        corrigirInsercao(no);
    }

    // Função de balanceamento após a inserção
    private void corrigirInsercao(NoRB k) {
        NoRB u;
        while (k.pai.cor == 1) {
            if (k.pai == k.pai.pai.direita) {
                u = k.pai.pai.esquerda; // tio
                if (u.cor == 1) {
                    // caso 3.1
                    u.cor = 0;
                    k.pai.cor = 0;
                    k.pai.pai.cor = 1;
                    k = k.pai.pai;
                } else {
                    if (k == k.pai.esquerda) {
                        // caso 3.2.2
                        k = k.pai;
                        rotacaoDireita(k);
                    }
                    // caso 3.2.1
                    k.pai.cor = 0;
                    k.pai.pai.cor = 1;
                    rotacaoEsquerda(k.pai.pai);
                }
            } else {
                u = k.pai.pai.direita; // tio

                if (u.cor == 1) {
                    // espelho caso 3.1
                    u.cor = 0;
                    k.pai.cor = 0;
                    k.pai.pai.cor = 1;
                    k = k.pai.pai;
                } else {
                    if (k == k.pai.direita) {
                        // espelho caso 3.2.2
                        k = k.pai;
                        rotacaoEsquerda(k);
                    }
                    // espelho caso 3.2.1
                    k.pai.cor = 0;
                    k.pai.pai.cor = 1;
                    rotacaoDireita(k.pai.pai);
                }
            }
            if (k == raiz) {
                break;
            }
        }
        raiz.cor = 0;
    }

    // Função para realizar uma rotação à esquerda
    public void rotacaoEsquerda(NoRB x) {
        NoRB y = x.direita;
        x.direita = y.esquerda;
        if (y.esquerda != TNULL)
            y.esquerda.pai = x;
        y.pai = x.pai;
        if (x.pai == null)
            raiz = y;
        else if (x == x.pai.esquerda)
            x.pai.esquerda = y;
        else
            x.pai.direita = y;
        y.esquerda = x;
        x.pai = y;


    }

    // Função para realizar uma rotação à direita
    public void rotacaoDireita(NoRB x) {
        NoRB y = x.esquerda;
        x.esquerda = y.direita;
        if (y.direita != TNULL)
            y.direita.pai = x;
        y.pai = x.pai;
        if (x.pai == null)
            raiz = y;
        else if (x == x.pai.direita)
            x.pai.direita = y;
        else
            x.pai.esquerda = y;
        y.direita = x;
        x.pai = y;


    }

}
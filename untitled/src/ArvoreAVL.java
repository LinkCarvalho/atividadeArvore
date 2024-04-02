

public class ArvoreAVL {
    NoAVL raiz;

    ArvoreAVL() {
        raiz = null;
    }

    int altura(NoAVL no) {
        return no != null ? no.altura : 0;
    }

    int fatorBalanceamento(NoAVL no) {
        return no != null ? altura(no.esquerda) - altura(no.direita) : 0;
    }

    NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.esquerda;
        NoAVL T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.direita;
        NoAVL T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }

    void inserir(int info) {
        raiz = inserirRec(raiz, info);
    }

    NoAVL inserirRec(NoAVL no, int info) {
        if (no == null)
            return new NoAVL(info);

        if (info < no.chave)
            no.esquerda = inserirRec(no.esquerda, info);
        else if (info > no.chave)
            no.direita = inserirRec(no.direita, info);
        else
            return no;

        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));

        int balanceamento = fatorBalanceamento(no);

        if (balanceamento > 1 && info < no.esquerda.chave)
            return rotacaoDireita(no);
        if (balanceamento < -1 && info > no.direita.chave)
            return rotacaoEsquerda(no);
        if (balanceamento > 1 && info > no.esquerda.chave) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
        if (balanceamento < -1 && info < no.direita.chave) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }


}

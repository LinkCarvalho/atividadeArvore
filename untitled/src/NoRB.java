class NoRB {
    int chave;
    NoRB pai;
    NoRB esquerda;
    NoRB direita;
    int cor;

    public NoRB(int chave) {
        this.chave = chave;
        pai = null;
        esquerda = null;
        direita = null;
        cor = 1; // 1 para vermelho, 0 para preto
    }
}
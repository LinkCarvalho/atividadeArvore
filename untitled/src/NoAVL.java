class NoAVL {
    int chave;
    int altura;
    NoAVL esquerda, direita;

    public NoAVL(int item) {
        chave = item;
        altura = 1;
        esquerda = direita = null;
    }
}
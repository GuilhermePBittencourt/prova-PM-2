package src.pm_prova2;

public class Carro extends Veiculo implements Avaliavel {

    public static final int NOTA_MINIMA = 1;
    public static final int NOTA_MAXIMA = 5;

    private int quantidadePortas;
    private int somaNotas;
    private int quantidadeAvaliacoes;

    public Carro(String placa, String modelo, int anoFabricacao, double valorDiaria,
            double quilometragem, StatusVeiculo status, int quantidadePortas) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
        this.quantidadePortas = quantidadePortas;
    }

    public int getQuantidadePortas() {
        return quantidadePortas;
    }

    public void setQuantidadePortas(int quantidadePortas) {
        this.quantidadePortas = quantidadePortas;
    }

    public int getQuantidadeAvaliacoes() {
        return quantidadeAvaliacoes;
    }

    public double getMediaAvaliacoes() {
        return quantidadeAvaliacoes == 0 ? 0 : (double) somaNotas / quantidadeAvaliacoes;
    }
    
    @throws IllegalArgumentException

    @Override
    public void avaliar(int nota) {
        if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            throw new IllegalArgumentException("A nota deve estar entre " + NOTA_MINIMA + " e " + NOTA_MAXIMA + ".");
        }
        somaNotas += nota;
        quantidadeAvaliacoes++;
    }

    @Override
    public boolean possuiSeguroEspecial() {
        return false;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("---------- CARRO ----------");
        System.out.println(dadosBasicos());
        System.out.println("Portas: " + quantidadePortas);
        System.out.printf("Avaliação: %.1f estrela(s) (%d avaliação(ões))%n",
                getMediaAvaliacoes(), quantidadeAvaliacoes);
    }
}

package src.pm_prova2;

@author glbra

public class PM_Prova2 {
    @param args
    
    public static void main(String[] args) {
        Locadora locadora = new Locadora();
        criarDadosIniciais(locadora);
        new Menu(locadora).iniciar();
    }

    public static void criarDadosIniciais(Locadora locadora) {
        locadora.cadastrarVeiculo(new Carro("QWE4R56", "Fiat Argo", 2023, 140.0, 18000, StatusVeiculo.DISPONIVEL, 4));
        locadora.cadastrarVeiculo(new Moto("RTY5T67", "Yamaha MT-03", 2019, 110.0, 55000, StatusVeiculo.DISPONIVEL, 321));
        locadora.cadastrarVeiculo(new Van("UIO6Y78", "Renault Master", 2022, 280.0, 45000, StatusVeiculo.DISPONIVEL, 16));

        locadora.cadastrarAluguel(new Premium("Ana Lima", 4, 0, StatusAluguel.ATIVO));
        locadora.cadastrarAluguel(new Executivo("Bruno Costa", 2, 0, StatusAluguel.ATIVO, "Ricardo Alves"));
    }
}

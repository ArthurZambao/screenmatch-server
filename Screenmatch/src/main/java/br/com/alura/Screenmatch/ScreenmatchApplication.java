package br.com.alura.Screenmatch;

import br.com.alura.Screenmatch.model.DadosSerie;
import br.com.alura.Screenmatch.service.ConsumoApi;
import br.com.alura.Screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var consumoApi = new ConsumoApi();
        var json = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&apikey=f2d3a33e");
        ConverteDados conversor = new ConverteDados();
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);
    }
}

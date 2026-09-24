package br.com.alura.Screenmatch.repository;

import br.com.alura.Screenmatch.model.Categoria;
import br.com.alura.Screenmatch.model.Episodio;
import br.com.alura.Screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacao);

    @Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.avaliacao >= :avaliacao")
    List<Serie> seriesPorTemporadaEAvaliacao(int totalTemporadas, double avaliacao);

    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    @Query("SELECT s FROM Serie s " +
            "JOIN s.episodios e " +
            "GROUP BY s " +
            "ORDER BY MAX(e.dataLancamento) DESC LIMIT 5")
    List<Serie> lancamentosMaisRecentes();

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trechoEpisodio%")
    List<Episodio> episodiosPorTrecho(String trechoEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.id = :serieId ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodio> topEpisodiosPorSerie(Long serieId);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie AND YEAR(e.dataLancamento) >= :anoLancamento ")
    List<Episodio> episodioPorSerieEAno(Serie serie, int anoLancamento);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.id = :id AND e.temporada = :numero")
    List<Episodio> obterEpisodiosPorTemporada(Long id, Long numero);

}

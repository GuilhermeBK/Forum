package br.com.alura.forum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.forum.modelo.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long>{

	Page<Topico> findByCurso_Nome(String NomeCurso, Pageable paginacao);
	//curso é a entidade de relacionamente e nome é o atributo do curso
	
//	@Query("SELECT t FROM topico t WHERE t.curso.nome = :nomeCurso")
//	List<Topico> carregarPorNomeCurso(@Param("nomeCurso") String nomeCurso);
}

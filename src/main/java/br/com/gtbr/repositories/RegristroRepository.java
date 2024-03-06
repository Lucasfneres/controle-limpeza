package br.com.gtbr.repositories;


import br.com.gtbr.domain.registro.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RegristroRepository extends JpaRepository<Registro, Long> {
}

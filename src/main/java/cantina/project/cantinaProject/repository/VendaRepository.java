package cantina.project.cantinaProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cantina.project.cantinaProject.domain.Carrinho;

@Repository
public interface VendaRepository extends JpaRepository<Carrinho, String> {

}

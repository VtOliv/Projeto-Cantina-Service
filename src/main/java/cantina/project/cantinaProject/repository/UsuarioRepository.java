package cantina.project.cantinaProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cantina.project.cantinaProject.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario>{

	Optional<Usuario> findByCpfUsuarioAndSenhaUsuario(String cpfUsuario, String senhaUsuario);
}

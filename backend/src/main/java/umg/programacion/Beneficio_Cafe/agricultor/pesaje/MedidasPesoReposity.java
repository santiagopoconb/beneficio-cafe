package umg.programacion.Beneficio_Cafe.agricultor.pesaje;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedidasPesoReposity extends JpaRepository<MedidasPeso, Long> {
    Optional<MedidasPeso> findByIdMedida(Integer idMedida);
}

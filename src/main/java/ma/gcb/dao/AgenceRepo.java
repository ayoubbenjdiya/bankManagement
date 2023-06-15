
package ma.gcb.dao;

import ma.gcb.entities.Agence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
@Component
public interface AgenceRepo extends JpaRepository<Agence, Long>
{
    @Query("select a from Agence a")
    Page<Agence> listAgences(final Pageable pageable);
    
    @Query("select a from Agence a where a.id =:id")
    Page<Agence> listAgencesByCode(@Param("id") final Long id, final Pageable pageable);
}

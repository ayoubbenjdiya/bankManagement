
package ma.gcb.dao;

import ma.gcb.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
@Component
public interface ClientRepo extends JpaRepository<Client, Integer>
{
    @Query("select c from Client c")
    Page<Client> listClients(final Pageable pageable);
    
    @Query("select c from Client c where c.code_utilisateur =:code_utilisateur order by c.nom asc ")
    Page<Client> listClientsByCode(@Param("code_utilisateur") final Integer code_utilisateur, final Pageable pageable);
    
    @Query("select c from Client c where c.nom like %:nom% order by c.nom asc")
    Page<Client> listClientsByNom(@Param("nom") final String nom, final Pageable pageable);
    
    @Query("select c from Client c where c.cin like %:cin% order by c.nom asc")
    Page<Client> listClientsByCin(@Param("cin") final String cin, final Pageable pageable);
}

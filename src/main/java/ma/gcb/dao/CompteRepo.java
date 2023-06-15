
package ma.gcb.dao;

import ma.gcb.entities.Compte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
@Component
public interface CompteRepo extends JpaRepository<Compte, String>
{
    @Query("select c from Compte c order by c.dateCreation desc")
    Page<Compte> listCompte(final Pageable pageable);
    
    @Query("select c from Compte c where c.codeCompte LIKE %:codeCompte% order by c.dateCreation desc")
    Page<Compte> listCompteByCode(@Param("codeCompte") final String codeCompte, final Pageable pageable);
    
    @Query("select c from Compte c where c.client.nom like %:nom% order by c.dateCreation desc")
    Page<Compte> listCompteByNom(@Param("nom") final String nom, final Pageable pageable);
    
    @Query("select c from Compte c where c.client.cin like %:cin% order by c.dateCreation desc")
    Page<Compte> listCompteByCin(@Param("cin") final String cin, final Pageable pageable);
    
    @Query("select c from Compte c where c.client.code_utilisateur =:codeClient order by c.dateCreation desc")
    Page<Compte> listCompteByCodeClient(@Param("codeClient") final Integer codeClient, final Pageable pageable);
}

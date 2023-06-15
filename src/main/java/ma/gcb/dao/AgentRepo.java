
package ma.gcb.dao;

import ma.gcb.entities.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;


@Component
public interface AgentRepo extends JpaRepository<Agent, Integer>
{
    @Query("select a from Agent a")
    Page<Agent> listAgents(final Pageable pageable);
    
    @Query("select a from Agent a where a.code_utilisateur =:code_utilisateur order by a.nom asc ")
    Page<Agent> listAgentsByCode(@Param("code_utilisateur") final Integer code_utilisateur, final Pageable pageable);
    
    @Query("select a from Agent a where a.nom like %:nom% order by a.nom asc")
    Page<Agent> listAgentsByNom(@Param("nom") final String nom, final Pageable pageable);
    
    @Query("select a from Agent a where a.cin like %:cin% order by a.nom asc")
    Page<Agent> listAgentsByCin(@Param("cin") final String cin, final Pageable pageable);
}

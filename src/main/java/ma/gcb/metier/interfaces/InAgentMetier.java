
package ma.gcb.metier.interfaces;

import ma.gcb.entities.Agent;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface InAgentMetier
{
    Agent consulterAgent(final Integer codeAgent);
    
    Optional<Agent> consulteAgentOptional(final Integer codeAgent);
    
    Agent ajouterAgent(final Agent agent);
    
    Agent modifierAgent(final Agent agent);
    
    boolean modifierAgent(final Integer codeAgent, final String cin, final String prenom, final String nom, final String tele, final String ville, final String email, final String date);
    
    Agent supprimerAgent(final Agent agent);
    
    Page<Agent> listAgents(final int page, final int size);
    
    Page<Agent> listAgentsByCode(final Integer codeAgent, final int page, final int size);
    
    Page<Agent> listAgentsByNom(final String nom, final int page, final int size);
    
    Page<Agent> listAgentsByCin(final String cin, final int page, final int size);
}

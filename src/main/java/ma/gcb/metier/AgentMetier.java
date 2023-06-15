
package ma.gcb.metier;

import ma.gcb.dao.AgentRepo;
import ma.gcb.entities.Agent;
import ma.gcb.metier.interfaces.InAgentMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Optional;

@Service
@Transactional
public class AgentMetier implements InAgentMetier
{
    @Autowired
    private AgentRepo agentRepo;
    
    @Override
    public Agent consulterAgent(Integer codeAgent) {
         Optional<Agent> ag = (Optional<Agent>)this.agentRepo.findById(codeAgent);
        if (ag == null) {
            throw new RuntimeException("agent non trouve");
        }
        return ag.get();
    }
    
    @Override
    public Optional<Agent> consulteAgentOptional( Integer codeAgent) {
        Optional<Agent> ag = (Optional<Agent>)this.agentRepo.findById(codeAgent);
        if (ag == null) {
            throw new RuntimeException("agent not found");
        }
        return ag;
    }
    
    @Override
    public Agent ajouterAgent(Agent agent) {
        this.agentRepo.save(agent);
        return agent;
    }
    
    @Override
    public Agent modifierAgent(Agent agent) {
        this.agentRepo.save(agent);
        return agent;
    }
    
    @Override
    public boolean modifierAgent(Integer codeAgent, String cin, String prenom, String nom, String tele, String ville, String email, String date) {
        try {
            Agent agent = this.consulterAgent(codeAgent);
            agent.setCin(cin);
            agent.setPrenom(prenom);
            agent.setNom(nom);
            agent.setTelephone(tele);
            agent.setVille(ville);
            agent.setEmail(email);
            agent.setDate_naissance((java.util.Date)Date.valueOf(date));
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
    
    @Override
    public Agent supprimerAgent(Agent agent) {
        this.agentRepo.delete(agent);
        return agent;
    }
    
    @Override
    public Page<Agent> listAgents(int page,int size) {
        Page<Agent> agents = (Page<Agent>)this.agentRepo.listAgents((Pageable)PageRequest.of(page, size));
        return agents;
    }
    
    @Override
    public Page<Agent> listAgentsByCode(Integer codeAgent, int page, int size) {
        Page<Agent> agents = (Page<Agent>)this.agentRepo.listAgentsByCode(codeAgent, (Pageable)PageRequest.of(page, size));
        return agents;
    }
    
    @Override
    public Page<Agent> listAgentsByNom(String nom, int page, int size) {
        Page<Agent> agents = (Page<Agent>)this.agentRepo.listAgentsByNom(nom, (Pageable)PageRequest.of(page, size));
        return agents;
    }
    
    @Override
    public Page<Agent> listAgentsByCin(String cin, int page, int size) {
        Page<Agent> agents = (Page<Agent>)this.agentRepo.listAgentsByCin(cin, (Pageable)PageRequest.of(page, size));
        return agents;
    }
}

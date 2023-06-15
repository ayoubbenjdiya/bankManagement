
package ma.gcb.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Agence implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "code_administrateur")
    @JsonIgnore
    private Administrateur administrateur;
    @JsonIgnore
    @OneToMany(mappedBy = "agenceA")
    private Collection<Agent> agents;
    @JsonIgnore
    @OneToMany(mappedBy = "agenceC")
    private Collection<Client> clients;
    
    public Agence() {
        this.agents = new ArrayList();
        this.clients = new ArrayList();
    }
    
    public Agence(final Administrateur administrateur, final Collection<Agent> agents, final Collection<Client> clients) {
        this.agents = new ArrayList();
        this.clients = new ArrayList();
        this.administrateur = administrateur;
        this.agents = agents;
        this.clients = clients;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public Administrateur getAdministrateur() {
        return this.administrateur;
    }
    
    public void setAdministrateur(final Administrateur administrateur) {
        this.administrateur = administrateur;
    }
    
    public Collection<Agent> getAgents() {
        return (Collection<Agent>)this.agents;
    }
    
    public void setAgents(final Collection<Agent> agents) {
        this.agents = agents;
    }
    
    public Collection<Client> getClients() {
        return (Collection<Client>)this.clients;
    }
    
    public void setClients(final Collection<Client> clients) {
        this.clients = clients;
    }
    
    @Override
    public String toString() {
        return "Agence{id=" + this.id + ", administrateur=" + this.administrateur + ", agents=" + this.agents + ", clients=" + this.clients + '}';
    }
}

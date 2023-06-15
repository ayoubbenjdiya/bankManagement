
package ma.gcb.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("CL")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Client extends Utilisateur
{
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Compte> comptes;
    @ManyToOne
    @JoinColumn(name = "codeAgence_Client")
    @JsonIgnore
    private Agence agenceC;
    
    public Client() {
        this.comptes = new ArrayList();
    }
    
    public Client(final String cin, final String prenom, final String nom, final String telephone, final String ville, final String email, final Date date_naissance, final Agence agenceC) {
        super(cin, prenom, nom, telephone, ville, email, date_naissance);
        this.comptes = new ArrayList();
        this.agenceC = agenceC;
    }
    
    public List<Compte> getComptes() {
        return (List<Compte>)this.comptes;
    }
    
    public void setComptes(final List<Compte> comptes) {
        this.comptes = comptes;
    }
    
    public Agence getAgenceC() {
        return this.agenceC;
    }
    
    public void setAgenceC(final Agence agenceC) {
        this.agenceC = agenceC;
    }
    
    public String toString() {
        return "Client{comptes=" + this.comptes + ", agenceC=" + this.agenceC + '}';
    }
}

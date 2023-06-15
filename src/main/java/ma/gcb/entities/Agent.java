
package ma.gcb.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import java.util.Date;

@Entity
@DiscriminatorValue("AG")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Agent extends Utilisateur
{
    @ManyToOne
    @JoinColumn(name = "codeAgence_Agent")
    @JsonIgnore
    private Agence agenceA;
    
    public Agent() {
    }
    
    public Agent(final String cin, final String prenom, final String nom, final String telephone, final String ville, final String email, final Date date_naissance, final Agence agenceA) {
        super(cin, prenom, nom, telephone, ville, email, date_naissance);
        this.agenceA = agenceA;
    }
    
    public Agence getAgenceA() {
        return this.agenceA;
    }
    
    public void setAgenceA(final Agence agenceA) {
        this.agenceA = agenceA;
    }
    
    public String toString() {
        return "Agent{agenceA=" + this.agenceA + '}';
    }
}

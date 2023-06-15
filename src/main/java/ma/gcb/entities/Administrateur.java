
package ma.gcb.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@DiscriminatorValue("AD")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Administrateur extends Utilisateur
{
    @OneToMany(mappedBy = "administrateur")
    @JsonIgnore
    private Collection<Agence> agences;
    
    public Administrateur() {
        this.agences = new ArrayList();
    }
    
    public Administrateur(final String cin, final String prenom, final String nom, final String telephone, final String ville, final String email, final Date date_naissance, final Collection<Agence> agences) {
        super(cin, prenom, nom, telephone, ville, email, date_naissance);
        this.agences = new ArrayList();
        this.agences = agences;
    }
    
    public Collection<Agence> getAgences() {
        return (Collection<Agence>)this.agences;
    }
    
    public void setAgences(final Collection<Agence> agences) {
        this.agences = agences;
    }
}

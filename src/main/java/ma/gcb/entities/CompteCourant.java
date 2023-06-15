
package ma.gcb.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("CC")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class CompteCourant extends Compte
{
    private double decouvert;
    
    public CompteCourant() {
    }
    
    public CompteCourant(final String codeCompte, final Date dateCreation, final double solde, final Client client, final double decouvert) {
        super(codeCompte, dateCreation, solde, client);
        this.decouvert = decouvert;
    }
    
    public CompteCourant(final String codeCompte, final Client client) {
        super(codeCompte, client);
        this.decouvert = 0.0;
    }
    
    public double getDecouvert() {
        return this.decouvert;
    }
    
    public void setDecouvert(final double decouvert) {
        this.decouvert = decouvert;
    }
}

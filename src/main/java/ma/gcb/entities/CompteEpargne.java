
package ma.gcb.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("CE")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class CompteEpargne extends Compte
{
    private double taux;
    
    public CompteEpargne() {
    }
    
    public CompteEpargne(final String codeCompte, final Date dateCreation, final double solde, final Client client, final double taux) {
        super(codeCompte, dateCreation, solde, client);
        this.taux = taux;
    }
    
    public CompteEpargne(final String codeCompte, final Client client) {
        super(codeCompte, client);
        final double n = 0.0;
        this.taux = n;
        this.taux = n;
    }
    
    public double getTaux() {
        return this.taux;
    }
    
    public void setTaux(final double taux) {
        this.taux = taux;
    }
}


package ma.gcb.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("OR")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Retrait extends Operation
{
    public Retrait() {
    }
    
    public Retrait(final Date date, final double nomtant, final Compte compte) {
        super(date, nomtant, compte);
    }
}

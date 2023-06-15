
package ma.gcb.metier.interfaces;

import ma.gcb.entities.Compte;

import java.util.Collection;

public interface InOperationMetier
{
    Collection<Compte> listOperationComplet();
    
    void verser(final String codeCompte, final double montant);
    
    void retirer(final String codeCompte, final double montant);
    
    void virment(final String codeCompte1, final String codeCompte2, final double montant);
}

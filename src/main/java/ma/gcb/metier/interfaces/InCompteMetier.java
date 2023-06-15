
package ma.gcb.metier.interfaces;

import ma.gcb.entities.Compte;
import ma.gcb.entities.Operation;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface InCompteMetier
{
    Compte consulterCompte(final String codeCompte);
    
    Optional<Compte> consulterCompteOptional(final String codeCompte);
    
    Compte ajouterCompte(final Compte compte);
    
    Compte ajouterCompte(final Integer codeClient, final String type);
    
    Compte modifierCompte(final Compte compte);
    
    Compte supprimerCompte(final Compte compte);
    
    Page<Compte> listCompte(final int page, final int size);
    
    Page<Compte> listCompteByCode(final String codeCompte, final int page, final int size);
    
    Page<Compte> listCompteByNom(final String nom, final int page, final int size);
    
    Page<Compte> listCompteByCin(final String cin, final int page, final int size);
    
    Page<Operation> listOperation(final String codeCompte, final int page, final int size);
}

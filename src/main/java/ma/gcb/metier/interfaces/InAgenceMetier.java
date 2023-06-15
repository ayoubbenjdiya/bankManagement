

package ma.gcb.metier.interfaces;

import ma.gcb.entities.Agence;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface InAgenceMetier
{
    Agence consulterAgence(final Integer codeAgence);
    
    Optional<Agence> consulteAgenceOptional(final Integer codeAgence);
    
    Agence ajouterAgence(final Agence agence);
    
    Agence modifierAgence(final Agence agence);
    
    boolean modifierAgence(final Integer codeAgence, final String cin, final String prenom, final String nom, final String tele, final String ville, final String email, final String date);
    
    Agence supprimerAgence(final Agence Agence);
    
    Page<Agence> listAgences(final int page, final int size);
    
    Page<Agence> listAgencesByCode(final Integer codeAgence, final int page, final int size);
}


package ma.gcb.metier;

import ma.gcb.entities.Agence;
import ma.gcb.metier.interfaces.InAgenceMetier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AgenceMetier implements InAgenceMetier
{
    @Override
    public Agence consulterAgence(final Integer codeAgence) {
        return null;
    }
    
    @Override
    public Optional<Agence> consulteAgenceOptional(final Integer codeAgence) {
        return Optional.empty();
    }
    
    @Override
    public Agence ajouterAgence(final Agence agence) {
        return null;
    }
    
    @Override
    public Agence modifierAgence(final Agence agence) {
        return null;
    }
    
    @Override
    public boolean modifierAgence(final Integer codeAgence, final String cin, final String prenom, final String nom, final String tele, final String ville, final String email, final String date) {
        return false;
    }
    
    @Override
    public Agence supprimerAgence(final Agence Agence) {
        return null;
    }
    
    @Override
    public Page<Agence> listAgences(final int page, final int size) {
        return null;
    }
    
    @Override
    public Page<Agence> listAgencesByCode(final Integer codeAgence, final int page, final int size) {
        return null;
    }
}

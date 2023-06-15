
package ma.gcb.metier.interfaces;

import ma.gcb.entities.Client;
import ma.gcb.entities.Compte;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface InClientMetier
{
    Client consulterClient(final Integer codeClient);
    
    Optional<Client> consulteClientOptional(final Integer codeClient);
    
    Client ajouterClient(final Client client);
    
    Client modifierClient(final Client client);
    
    boolean modifierClient(final Integer codeClient, final String cin, final String prenom, final String nom, final String tele, final String ville, final String email, final String date);
    
    Client supprimerClient(final Client client);
    
    Page<Client> listClients(final int page, final int size);
    
    Page<Client> listClientsByCode(final Integer codeClient, final int page, final int size);
    
    Page<Client> listClientsByNom(final String nom, final int page, final int size);
    
    Page<Client> listClientsByCin(final String cin, final int page, final int size);
    
    Page<Compte> listCompteByCodeClient(final Integer codeClient, final int page, final int size);
}

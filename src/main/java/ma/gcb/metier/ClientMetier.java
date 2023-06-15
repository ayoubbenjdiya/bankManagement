
package ma.gcb.metier;

import ma.gcb.dao.ClientRepo;
import ma.gcb.dao.CompteRepo;
import ma.gcb.entities.Client;
import ma.gcb.entities.Compte;
import ma.gcb.metier.interfaces.InClientMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Optional;

@Service
@Transactional
public class ClientMetier implements InClientMetier
{
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private CompteRepo compteRepo;
    
    public Client consulterClient(Integer codeClient) {
        Optional<Client> cp = (Optional<Client>)this.clientRepo.findById(codeClient);
        if (cp == null) {
            throw new RuntimeException("client non trouvé");
        }
        return cp.get();
    }
    
    public Optional<Client> consulteClientOptional(Integer codeClient) {
        Optional<Client> cl = (Optional<Client>)this.clientRepo.findById(codeClient);
        if (cl == null) {
            throw new RuntimeException("client not found");
        }
        return cl;
    }
    
    public Client ajouterClient(Client client) {
        this.clientRepo.save(client);
        return client;
    }
    
    public Client modifierClient(Client client) {
        this.clientRepo.save(client);
        return client;
    }
    
    public boolean modifierClient(Integer codeClient, String cin, String prenom, String nom, String tele, String ville, String email, String date) {
        try {
            Client client = this.consulterClient(codeClient);
            client.setCin(cin);
            client.setPrenom(prenom);
            client.setNom(nom);
            client.setTelephone(tele);
            client.setVille(ville);
            client.setEmail(email);
            client.setDate_naissance((java.util.Date)Date.valueOf(date));
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public Client supprimerClient(Client client) {
        this.clientRepo.delete(client);
        return client;
    }
    
    public Page<Client> listClients(int page, int size) {
        Page<Client> clients = (Page<Client>)this.clientRepo.listClients((Pageable)PageRequest.of(page, size));
        return clients;
    }
    
    public Page<Client> listClientsByCode(Integer codeClient, int page, int size) {
        return (Page<Client>)this.clientRepo.listClientsByCode(codeClient, (Pageable)PageRequest.of(page, size));
    }
    
    public Page<Client> listClientsByNom(String nom, int page, int size) {
        return (Page<Client>)this.clientRepo.listClientsByNom(nom, (Pageable)PageRequest.of(page, size));
    }
    
    public Page<Client> listClientsByCin(String cin, int page, int size) {
        return (Page<Client>)this.clientRepo.listClientsByCin(cin, (Pageable)PageRequest.of(page, size));
    }
    
    public Page<Compte> listCompteByCodeClient(Integer codeClient, int page, int size) {
        return (Page<Compte>)this.compteRepo.listCompteByCodeClient(codeClient, (Pageable)PageRequest.of(page, size));
    }
}

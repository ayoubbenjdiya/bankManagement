
package ma.gcb.metier;

import ma.gcb.dao.ClientRepo;
import ma.gcb.dao.CompteRepo;
import ma.gcb.dao.OperationRepo;
import ma.gcb.entities.*;
import ma.gcb.metier.interfaces.InCompteMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class CompteMetier implements InCompteMetier
{
    @Autowired
    private CompteRepo compteRepo;
    @Autowired
    private OperationRepo operationRepo;
    @Autowired
    private ClientRepo clientRepo;
    
    public Compte consulterCompte(String codeCompte) {
        Optional<Compte> cp = (Optional<Compte>)this.compteRepo.findById(codeCompte);
        if (cp == null) {
            throw new RuntimeException("compte non trouv\u00e9");
        }
        return cp.get();
    }
    
    public Optional<Compte> consulterCompteOptional(String codeCompte) {
        Optional<Compte> cp = (Optional<Compte>)this.compteRepo.findById(codeCompte);
        if (cp == null) {
            throw new RuntimeException("compte not fond");
        }
        return cp;
    }
    
    public Compte ajouterCompte(Compte compte) {
        this.compteRepo.save(compte);
        return compte;
    }
    
    public Compte ajouterCompte(Integer codeClient, String type) {
        String codeCompte = this.generateCodeCompte();
        Client client = this.clientRepo.findById(codeClient).get();
        Compte cp;
        if (type.equals("CC")) {
            cp = (Compte)new CompteCourant(codeCompte, client);
        }
        else {
            cp = (Compte)new CompteEpargne(codeCompte, client);
        }
        this.compteRepo.save(cp);
        return cp;
    }
    
    private String generateCodeCompte() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();
        while (true) {
            String generatedCodeCompte = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            try {
                this.consulterCompte(generatedCodeCompte);
            }
            catch (Exception e) {
                return generatedCodeCompte;
            }
        }
    }
    
    public Compte modifierCompte(Compte compte) {
        this.compteRepo.save(compte);
        return compte;
    }
    
    public Compte supprimerCompte(Compte compte) {
        this.compteRepo.delete(compte);
        return compte;
    }
    
    public Page<Compte> listCompte(int page, int size) {
        Page<Compte> comptes = (Page<Compte>)this.compteRepo.listCompte((Pageable)PageRequest.of(page, size));
        return comptes;
    }
    
    public Page<Compte> listCompteByCode(String codeCompte, int page, int size) {
        Page<Compte> comptesByCode = (Page<Compte>)this.compteRepo.listCompteByCode(codeCompte, (Pageable)PageRequest.of(page, size));
        return comptesByCode;
    }
    
    public Page<Compte> listCompteByNom(String nom, int page, int size) {
        Page<Compte> comptesByNom = (Page<Compte>)this.compteRepo.listCompteByNom(nom, (Pageable)PageRequest.of(page, size));
        return comptesByNom;
    }
    
    public Page<Compte> listCompteByCin(String cin, int page, int size) {
        Page<Compte> comptesByCin = (Page<Compte>)this.compteRepo.listCompteByCin(cin, (Pageable)PageRequest.of(page, size));
        return comptesByCin;
    }
    
    public Page<Operation> listOperation(String codeCompte, int page, int size) {
        Page<Operation> operations = (Page<Operation>)this.operationRepo.listOperation(codeCompte, (Pageable)PageRequest.of(page, size));
        return operations;
    }
}

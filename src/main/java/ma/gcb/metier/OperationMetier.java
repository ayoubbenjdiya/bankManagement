
package ma.gcb.metier;

import ma.gcb.dao.CompteRepo;
import ma.gcb.dao.OperationRepo;
import ma.gcb.entities.Compte;
import ma.gcb.entities.CompteCourant;
import ma.gcb.entities.Retrait;
import ma.gcb.entities.Versement;
import ma.gcb.metier.interfaces.InOperationMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class OperationMetier implements InOperationMetier {
    @Autowired
    private CompteRepo compteRepo;
    @Autowired
    private OperationRepo operationRepo;

    @Override
    public void verser(String codeCompte, double montant) {
        Compte cp = this.compteRepo.findById(codeCompte).get();
        Versement versement = new Versement(new Date(), montant, cp);
        this.operationRepo.save(versement);
        cp.setSolde(cp.getSolde() + montant);
        this.compteRepo.save(cp);
    }

    @Override
    public void retirer(String codeCompte, double montant) {
        Compte cp = this.compteRepo.findById(codeCompte).get();
        double facilitesCaisse = 0.0;
        if (cp instanceof CompteCourant) {
            facilitesCaisse = ((CompteCourant) cp).getDecouvert();
        }
        if (cp.getSolde() + facilitesCaisse < montant) {
            throw new RuntimeException("insufficient balance");
        }
        Retrait retrait = new Retrait(new Date(), montant, cp);
        this.operationRepo.save(retrait);
        cp.setSolde(cp.getSolde() - montant);
        this.compteRepo.save(cp);
    }

    @Override
    public void virment(String codeCompte1, String codeCompte2, double montant) {
        this.retirer(codeCompte1, montant);
        this.verser(codeCompte2, montant);
    }

    @Override
    public Collection<Compte> listOperationComplet() {
        return (Collection<Compte>) this.compteRepo.findAll();
    }
}

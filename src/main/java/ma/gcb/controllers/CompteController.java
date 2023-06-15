
package ma.gcb.controllers;

import ma.gcb.entities.Compte;
import ma.gcb.entities.CompteCourant;
import ma.gcb.entities.CompteEpargne;
import ma.gcb.metier.interfaces.InClientMetier;
import ma.gcb.metier.interfaces.InCompteMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class CompteController
{
    @Autowired
    private InCompteMetier compteMetier;
    @Autowired
    private InClientMetier clientMetier;
    
    @RequestMapping({ "/gestion_des_comptes" })
    public String index(final Model model, @RequestParam(name = "page", defaultValue = "0") final int page) {
        model.addAttribute("numPage", (Object)page);
        try {
            final Page<Compte> comptePage = (Page<Compte>)this.compteMetier.listCompte(page, 10);
            final int[] pages = new int[comptePage.getTotalPages()];
            model.addAttribute("comptePage", (Object)comptePage.getContent());
            model.addAttribute("nombrePage", (Object)pages);
        }
        catch (Exception e) {
            model.addAttribute("exception", (Object)e);
            return "gestion_des_comptes";
        }
        return "gestion_des_comptes";
    }
    
    @RequestMapping({ "/gestion_des_comptes/edit" })
    public String editer(final Model model, @RequestParam(name = "codeCompte") final String codeCompte, @RequestParam(name = "page", defaultValue = "0") final int page) {
        model.addAttribute("numPage", (Object)page);
        try {
            final Page<Compte> comptePage = (Page<Compte>)this.compteMetier.listCompte(page, 10);
            final int[] pages = new int[comptePage.getTotalPages()];
            final Compte compte = this.compteMetier.consulterCompte(codeCompte);
            model.addAttribute("comptePage", (Object)comptePage.getContent());
            model.addAttribute("nombrePage", (Object)pages);
            model.addAttribute("compteE", (Object)compte);
        }
        catch (Exception e) {
            model.addAttribute("exception", (Object)e);
            return "gestion_des_comptes";
        }
        return "gestion_des_comptes";
    }
    
    @RequestMapping(value = { "/gestion_des_comptes/rechercheCompte" }, method = { RequestMethod.POST })
    public String rechercheCompte(final Model model, final String par, final String input, @RequestParam(name = "page", defaultValue = "0") final int page) {
        model.addAttribute("numPage", (Object)page);
        try {
            if (par.equals("code")) {
                final Page<Compte> comptePage = (Page<Compte>)this.compteMetier.listCompteByCode(input, page, 10);
                final int[] pages = new int[comptePage.getTotalPages()];
                model.addAttribute("comptePage", (Object)comptePage.getContent());
                model.addAttribute("nombrePage", (Object)pages);
            }
            else if (par.equals("nom")) {
                final Page<Compte> comptePage = (Page<Compte>)this.compteMetier.listCompteByNom(input, page, 10);
                final int[] pages = new int[comptePage.getTotalPages()];
                model.addAttribute("comptePage", (Object)comptePage.getContent());
                model.addAttribute("nombrePage", (Object)pages);
            }
            else if (par.equals("cin")) {
                final Page<Compte> comptePage = (Page<Compte>)this.compteMetier.listCompteByCin(input, page, 10);
                final int[] pages = new int[comptePage.getTotalPages()];
                model.addAttribute("comptePage", (Object)comptePage.getContent());
                model.addAttribute("nombrePage", (Object)pages);
            }
            else {
                final Page<Compte> comptePage = (Page<Compte>)this.compteMetier.listCompte(page, 10);
                final int[] pages = new int[comptePage.getTotalPages()];
                model.addAttribute("comptePage", (Object)comptePage.getContent());
                model.addAttribute("nombrePage", (Object)pages);
            }
        }
        catch (Exception e) {
            model.addAttribute("exception", (Object)e);
            return "gestion_des_comptes";
        }
        return "gestion_des_comptes";
    }
    
    @RequestMapping({ "/gestion_des_comptes/rechercheCompte" })
    public String rechercheCompte() {
        return "redirect:/gestion_des_comptes";
    }
    
    @RequestMapping(value = { "/gestion_des_comptes/modifier" }, method = { RequestMethod.POST })
    public String modifierCompte(final Model model, final int page, final String type, final String codeCompte, final Integer codeClient, final String date, final double solde, final double dt) {
        try {
            if (type.equals("CompteCourant")) {
                final Compte compte = (Compte)new CompteCourant(codeCompte, (java.util.Date)Date.valueOf(date), solde, this.clientMetier.consulterClient(codeClient), dt);
                this.compteMetier.modifierCompte(compte);
            }
            else {
                final Compte compte = (Compte)new CompteEpargne(codeCompte, (java.util.Date)Date.valueOf(date), solde, this.clientMetier.consulterClient(codeClient), dt);
                this.compteMetier.modifierCompte(compte);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", (Object)e);
        }
        return "redirect:/gestion_des_comptes?page=" + page;
    }
    
    @RequestMapping({ "/gestion_des_comptes/supprimer" })
    public String supprimerCompte(final Model model, final int page, final String codeCompte) {
        try {
            this.compteMetier.supprimerCompte(this.compteMetier.consulterCompte(codeCompte));
        }
        catch (Exception e) {
            return "redirect:/gestion_des_comptes?page=" + page + "&exceptionOperation=" + e.getMessage();
        }
        return "redirect:/gestion_des_comptes?page=" + page + "&statut=true";
    }
}

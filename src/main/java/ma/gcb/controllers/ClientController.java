
package ma.gcb.controllers;

import ma.gcb.entities.Agence;
import ma.gcb.entities.Client;
import ma.gcb.entities.Compte;
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
public class ClientController
{
    @Autowired
    private InClientMetier clientMetier;
    @Autowired
    private InCompteMetier compteMetier;
    
    @RequestMapping({ "/gestion_des_clients" })
    public String index(final Model model, @RequestParam(name = "page", defaultValue = "0") final int page) {
        model.addAttribute("numPage", (Object)page);
        try {
            final Page<Client> clientPage = (Page<Client>)this.clientMetier.listClients(page, 10);
            final int[] pages = new int[clientPage.getTotalPages()];
            model.addAttribute("clientPage", (Object)clientPage.getContent());
            model.addAttribute("nombrePage", (Object)pages);
            model.addAttribute("page", (Object)page);
        }
        catch (Exception e) {
            model.addAttribute("exception", (Object)e);
            return "gestion_des_clients?error=true";
        }
        return "gestion_des_clients";
    }
    
    @RequestMapping(value = { "/gestion_des_clients/rechercheClient" }, method = { RequestMethod.POST })
    public String rechercheClients(final Model model, final String par, final String input, @RequestParam(name = "page", defaultValue = "0") final int page) {
        model.addAttribute("numPage", (Object)page);
        try {
            if (par.equals("code")) {
                final Page<Client> clientPage = (Page<Client>)this.clientMetier.listClientsByCode(Integer.valueOf(input), page, 10);
                final int[] pages = new int[clientPage.getTotalPages()];
                model.addAttribute("clientPage", (Object)clientPage.getContent());
                model.addAttribute("nombrePage", (Object)pages);
            }
            else if (par.equals("nom")) {
                final Page<Client> clientPage = (Page<Client>)this.clientMetier.listClientsByNom(input, page, 10);
                final int[] pages = new int[clientPage.getTotalPages()];
                model.addAttribute("clientPage", (Object)clientPage.getContent());
                model.addAttribute("nombrePage", (Object)pages);
            }
            else if (par.equals("cin")) {
                final Page<Client> clientPage = (Page<Client>)this.clientMetier.listClientsByCin(input, page, 10);
                final int[] pages = new int[clientPage.getTotalPages()];
                model.addAttribute("clientPage", (Object)clientPage.getContent());
                model.addAttribute("nombrePage", (Object)pages);
            }
            else {
                final Page<Client> clientPage = (Page<Client>)this.clientMetier.listClients(page, 10);
                final int[] pages = new int[clientPage.getTotalPages()];
                model.addAttribute("clientPage", (Object)clientPage.getContent());
                model.addAttribute("nombrePage", (Object)pages);
            }
        }
        catch (Exception e) {
            return "redirect:/gestion_des_clients?input=false";
        }
        return "gestion_des_clients";
    }
    
    @RequestMapping({ "/gestion_des_clients/rechercheClient" })
    public String rechercheClientsGet() {
        return "redirect:/gestion_des_clients";
    }
    
    @RequestMapping({ "/gestion_des_clients/edit" })
    public String editer(final Model model, @RequestParam(name = "codeClient") final Integer codeClient, @RequestParam(name = "page", defaultValue = "0") final int page) {
        model.addAttribute("numPage", (Object)page);
        try {
            final Page<Client> clientPage = (Page<Client>)this.clientMetier.listClients(page, 10);
            final int[] pages = new int[clientPage.getTotalPages()];
            model.addAttribute("clientPage", (Object)clientPage.getContent());
            model.addAttribute("nombrePage", (Object)pages);
            final Client client = this.clientMetier.consulterClient(codeClient);
            model.addAttribute("clientE", (Object)client);
        }
        catch (Exception e) {
            model.addAttribute("exception", (Object)e);
            return "gestion_des_clients";
        }
        return "gestion_des_clients";
    }
    
    @RequestMapping(value = { "/gestion_des_clients/modifier" }, method = { RequestMethod.POST })
    public String modifierClient(final Model model, final int page, final Integer codeClient, final String cin, final String prenom, final String nom, final String tele, final String ville, final String email, final String date) {
        try {
            this.clientMetier.modifierClient(codeClient, cin, prenom, nom, tele, ville, email, date);
        }
        catch (Exception e) {
            return "redirect:/gestion_des_clients?page=" + page + "&m=0";
        }
        return "redirect:/gestion_des_clients?page=" + page + "&m=1";
    }
    
    @RequestMapping({ "/gestion_des_clients/supprimer" })
    public String supprimerCompte(final Model model, final int page, final Integer codeClient) {
        try {
            this.clientMetier.supprimerClient(this.clientMetier.consulterClient(codeClient));
        }
        catch (Exception e) {
            return "redirect:/gestion_des_clients/?page=" + page + "&s=0";
        }
        return "redirect:/gestion_des_clients/?page=" + page + "&s=1";
    }
    
    @RequestMapping({ "/gestion_des_clients/ajouter" })
    public String ajouter(final Model model, @RequestParam(name = "page", defaultValue = "0") final int page) {
        model.addAttribute("numPage", (Object)page);
        try {
            final Page<Client> clientPage = (Page<Client>)this.clientMetier.listClients(page, 10);
            final int[] pages = new int[clientPage.getTotalPages()];
            model.addAttribute("clientPage", (Object)clientPage.getContent());
            model.addAttribute("nombrePage", (Object)pages);
            model.addAttribute("ajouter", (Object)true);
        }
        catch (Exception e) {
            model.addAttribute("exception", (Object)e);
            return "gestion_des_clients";
        }
        return "gestion_des_clients";
    }
    
    @RequestMapping(value = { "/gestion_des_clients/ajouterClient" }, method = { RequestMethod.POST })
    public String ajouterClient(final Model model, final String cin, final String prenom, final String nom, final String tele, final String ville, final String email, final String date) {
        final int page = 0;
        try {
            this.clientMetier.ajouterClient(new Client(cin, prenom, nom, tele, ville, email, (java.util.Date)Date.valueOf(date), (Agence)null));
            System.out.println("try");
        }
        catch (Exception e) {
            System.out.println("eror");
            return "redirect:/gestion_des_clients?page=" + page + "&a=0";
        }
        return "redirect:/gestion_des_clients?page=" + page + "&a=1";
    }
    
    @RequestMapping({ "/gestion_des_clients/consultationClient" })
    public String consultationClient(final Model model, final Integer codeClient, @RequestParam(name = "page", defaultValue = "0") final int page, @RequestParam(name = "page2", defaultValue = "0") final int page2) {
        model.addAttribute("numPage", (Object)page);
        model.addAttribute("numPage2", (Object)page2);
        System.out.println("inajtcpt");
        System.out.println(page);
        try {
            final Client clt = this.clientMetier.consulterClient(codeClient);
            final Page<Compte> comptePage = (Page<Compte>)this.clientMetier.listCompteByCodeClient(codeClient, page2, 10);
            model.addAttribute("clt", (Object)clt);
            model.addAttribute("comptePage", (Object)comptePage);
            final int[] pages2 = new int[comptePage.getTotalPages()];
            model.addAttribute("nombrePage2", (Object)pages2);
            final Page<Client> clientPage = (Page<Client>)this.clientMetier.listClients(page, 10);
            final int[] pages3 = new int[clientPage.getTotalPages()];
            model.addAttribute("clientPage", (Object)clientPage.getContent());
            model.addAttribute("nombrePage", (Object)pages3);
            model.addAttribute("page", (Object)page);
            model.addAttribute("page2", (Object)page2);
        }
        catch (Exception e) {
            model.addAttribute("exception", (Object)e);
            return "gestion_des_clients?error=true";
        }
        return "gestion_des_clients";
    }
    
    @RequestMapping(value = { "/gestion_des_clients/ajouterCompte" }, method = { RequestMethod.POST })
    public String modifierComote(final Model model, final Integer codeClient, final String typeCompte, final int page) {
        try {
            this.compteMetier.ajouterCompte(codeClient, typeCompte);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "redirect:/gestion_des_clients/consultationClient?ac=false&codeClient=" + codeClient + "&,page=" + page;
        }
        return "redirect:/gestion_des_clients/consultationClient?ac=true&codeClient=" + codeClient + "&,page=" + page;
    }
}


package ma.gcb.controllers;

import ma.gcb.entities.Agence;
import ma.gcb.entities.Agent;
import ma.gcb.metier.interfaces.InAgentMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class AgentController
{
    @Autowired
    private InAgentMetier agentMetier;
    
    @RequestMapping({ "/index" })
    public String index() {
        return "home";
    }
    
    @RequestMapping({ "/gestion_des_agents" })
    public String index2(final Model model, @RequestParam(name = "page", defaultValue = "0") final int page) {
        model.addAttribute("numPage", (Object)page);
      
            final Page<Agent> agentPage = this.agentMetier.listAgents(page, 10);
            final int[] pages = new int[agentPage.getTotalPages()];
            model.addAttribute("agentPage", (Object)agentPage.getContent());
            model.addAttribute("nombrePage", (Object)pages);
   
        return "gestion_des_agents";
    }
    
    @RequestMapping(value = { "/gestion_des_agents/rechercheAgent" }, method = { RequestMethod.POST })
    public String rechercheAgents(final Model model, final String par, final String input, @RequestParam(name = "page", defaultValue = "0") final int page) {
        model.addAttribute("numPage", (Object)page);
        try {
            if (par.equals("code")) {
                final Page<Agent> agentPage = this.agentMetier.listAgentsByCode(Integer.valueOf(input), page, 10);
                final int[] pages = new int[agentPage.getTotalPages()];
                model.addAttribute("agentPage", (Object)agentPage.getContent());
                model.addAttribute("nombrePage", (Object)pages);
            }
            else if (par.equals("nom")) {
                final Page<Agent> agentPage = this.agentMetier.listAgentsByNom(input, page, 10);
                final int[] pages = new int[agentPage.getTotalPages()];
                model.addAttribute("agentPage", (Object)agentPage.getContent());
                model.addAttribute("nombrePage", (Object)pages);
            }
            else if (par.equals("cin")) {
                final Page<Agent> agentPage = this.agentMetier.listAgentsByCin(input, page, 10);
                final int[] pages = new int[agentPage.getTotalPages()];
                model.addAttribute("agentPage", (Object)agentPage.getContent());
                model.addAttribute("nombrePage", (Object)pages);
            }
            else {
                final Page<Agent> agentPage = this.agentMetier.listAgents(page, 10);
                final int[] pages = new int[agentPage.getTotalPages()];
                model.addAttribute("agentPage", (Object)agentPage.getContent());
                model.addAttribute("nombrePage", (Object)pages);
            }
        }
        catch (Exception e) {
            return "redirect:/gestion_des_agents?input=false";
        }
        return "gestion_des_agents";
    }
    
    @RequestMapping({ "/gestion_des_agents/rechercheAgent" })
    public String rechercheAgentsGet() {
        return "redirect:/gestion_des_agents";
    }
    
    @RequestMapping({ "/gestion_des_agents/edit" })
    public String editer(final Model model, @RequestParam(name = "codeAgent") final Integer codeAgent, @RequestParam(name = "page", defaultValue = "0") final int page) {
        model.addAttribute("numPage", (Object)page);
        try {
            final Page<Agent> agentPage = this.agentMetier.listAgents(page, 10);
            final int[] pages = new int[agentPage.getTotalPages()];
            model.addAttribute("agentPage", (Object)agentPage.getContent());
            model.addAttribute("nombrePage", (Object)pages);
            final Agent agent = this.agentMetier.consulterAgent(codeAgent);
            model.addAttribute("agentE", (Object)agent);
        }
        catch (Exception e) {
            model.addAttribute("exception", (Object)e);
            return "gestion_des_agents";
        }
        return "gestion_des_agents";
    }
    
    @RequestMapping(value = { "/gestion_des_agents/modifier" }, method = { RequestMethod.POST })
    public String modifierAgent(final Model model, final int page, final Integer codeAgent, final String cin, final String prenom, final String nom, final String tele, final String ville, final String email, final String date) {
        try {
            this.agentMetier.modifierAgent(codeAgent, cin, prenom, nom, tele, ville, email, date);
        }
        catch (Exception e) {
            return "redirect:/gestion_des_agents?page=" + page + "&m=0";
        }
        return "redirect:/gestion_des_agents?page=" + page + "&m=1";
    }
    
    @RequestMapping({ "/gestion_des_agents/supprimer" })
    public String supprimerCompte(final Model model, final int page, final Integer codeAgent) {
        return "redirect:/gestion_des_agents/?page=" + page + "&s=1";
    }
    
    @RequestMapping({ "/gestion_des_agents/ajouter" })
    public String ajouter(final Model model, @RequestParam(name = "page", defaultValue = "0") final int page) {
        model.addAttribute("numPage", (Object)page);
        try {
            final Page<Agent> agentPage = this.agentMetier.listAgents(page, 10);
            final int[] pages = new int[agentPage.getTotalPages()];
            model.addAttribute("agentPage", (Object)agentPage.getContent());
            model.addAttribute("nombrePage", (Object)pages);
            model.addAttribute("ajouter", (Object)true);
        }
        catch (Exception e) {
            model.addAttribute("exception", (Object)e);
            return "gestion_des_agents";
        }
        return "gestion_des_agents";
    }
    
    @RequestMapping(value = { "/gestion_des_agents/ajouterAgent" }, method = { RequestMethod.POST })
    public String ajouterAgent(final Model model, final String cin, final String prenom, final String nom, final String tele, final String ville, final String email, final String date) {
        final int page = 0;
        try {
            this.agentMetier.ajouterAgent(new Agent(cin, prenom, nom, tele, ville, email, (java.util.Date)Date.valueOf(date), (Agence)null));
        }
        catch (Exception e) {
            return "redirect:/gestion_des_agents?page=" + page + "&a=0";
        }
        return "redirect:/gestion_des_agents?page=" + page + "&a=1";
    }
}

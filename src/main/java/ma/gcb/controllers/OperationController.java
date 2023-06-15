
package ma.gcb.controllers;

import ma.gcb.entities.Compte;
import ma.gcb.entities.Operation;
import ma.gcb.metier.interfaces.InCompteMetier;
import ma.gcb.metier.interfaces.InOperationMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OperationController
{
    @Autowired
    private InCompteMetier compteMetier;
    @Autowired
    private InOperationMetier operationMetier;
    
    @RequestMapping({ "/gestion_des_operations" })
    public String index() {
        return "gestion_des_operations";
    }
    
    @RequestMapping({ "/consultationCompte" })
    public String consultationCompte(final Model model, final String codeCompte, @RequestParam(name = "page", defaultValue = "0") final int page) {
        model.addAttribute("codeCompte", (Object)codeCompte);
        model.addAttribute("numPage", (Object)page);
        try {
            final Compte cp = this.compteMetier.consulterCompte(codeCompte);
            final Page<Operation> pageOperation = (Page<Operation>)this.compteMetier.listOperation(codeCompte, page, 10);
            model.addAttribute("listOperation", (Object)pageOperation.getContent());
            final int[] pages = new int[pageOperation.getTotalPages()];
            model.addAttribute("pages", (Object)pages);
            model.addAttribute("cpt", (Object)cp);
        }
        catch (Exception e) {
            model.addAttribute("exeption", (Object)e);
        }
        return "gestion_des_operations";
    }
    
    @RequestMapping(value = { "/saveOperation" }, method = { RequestMethod.POST })
    public String saveOperation(final Model model, final String typeOperation, final String codeCompte, final String codeCompte2, final double montant) {
        try {
            if (typeOperation.equals("versement")) {
                this.operationMetier.verser(codeCompte, montant);
            }
            else if (typeOperation.equals("retrait")) {
                this.operationMetier.retirer(codeCompte, montant);
            }
            else if (typeOperation.equals("virment")) {
                this.operationMetier.virment(codeCompte, codeCompte2, montant);
            }
        }
        catch (Exception e) {
            model.addAttribute("exceptionOperation", (Object)e);
            return "redirect:/consultationCompte?codeCompte=" + codeCompte + "&exceptionOperation=" + e.getMessage();
        }
        return "redirect:/consultationCompte?codeCompte=" + codeCompte;
    }
}

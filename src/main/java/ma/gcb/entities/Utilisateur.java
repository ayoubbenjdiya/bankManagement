
package ma.gcb.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import ma.gcb.entities.Administrateur;
import ma.gcb.entities.Agent;
import ma.gcb.entities.Client;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeUtilisateur", discriminatorType = DiscriminatorType.STRING, length = 2)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeUtilisateur")
@JsonSubTypes({ @JsonSubTypes.Type(name = "CL", value = Client.class), @JsonSubTypes.Type(name = "AG", value = Agent.class), @JsonSubTypes.Type(name = "AD", value = Administrateur.class) })
public abstract class Utilisateur implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code_utilisateur;
    private String cin;
    private String prenom;
    private String nom;
    private String telephone;
    private String ville;
    private String email;
    private Date date_naissance;
    
    public Utilisateur() {
    }
    
    public Utilisateur(final String cin, final String prenom, final String nom, final String telephone, final String ville, final String email, final Date date_naissance) {
        this.cin = cin;
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;
        this.ville = ville;
        this.email = email;
        this.date_naissance = date_naissance;
    }
    
    public Integer getCode_utilisateur() {
        return this.code_utilisateur;
    }
    
    public void setCode_utilisateur(final Integer code_utilisateur) {
        this.code_utilisateur = code_utilisateur;
    }
    
    public String getCin() {
        return this.cin;
    }
    
    public void setCin(final String cin) {
        this.cin = cin;
    }
    
    public String getPrenom() {
        return this.prenom;
    }
    
    public void setPrenom(final String prenom) {
        this.prenom = prenom;
    }
    
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(final String nom) {
        this.nom = nom;
    }
    
    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(final String telephone) {
        this.telephone = telephone;
    }
    
    public String getVille() {
        return this.ville;
    }
    
    public void setVille(final String ville) {
        this.ville = ville;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public Date getDate_naissance() {
        return this.date_naissance;
    }
    
    public void setDate_naissance(final Date date_naissance) {
        this.date_naissance = date_naissance;
    }
    
    @Override
    public String toString() {
        return "Utilisateur{code_utilisateur=" + this.code_utilisateur + ", cin='" + this.cin + '\'' + ", prenom='" + this.prenom + '\'' + ", nom='" + this.nom + '\'' + ", telephone='" + this.telephone + '\'' + ", ville='" + this.ville + '\'' + ", email='" + this.email + '\'' + ", date_naissance=" + this.date_naissance + '}';
    }
}

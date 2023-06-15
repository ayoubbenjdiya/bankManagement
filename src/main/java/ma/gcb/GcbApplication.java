// 
// Decompiled by Procyon v0.5.36
// 

package ma.gcb;

import ma.gcb.dao.CompteRepo;
import ma.gcb.metier.interfaces.InClientMetier;
import ma.gcb.metier.interfaces.InCompteMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class GcbApplication implements CommandLineRunner
{
    @Autowired
    private CompteRepo compteRepo;
    @Autowired
    private InClientMetier clientMetier;
    @Autowired
    private InCompteMetier compteMetier;
    
    public static void main(final String[] args) {
        SpringApplication.run((Class)GcbApplication.class, args);
    }
    
    public void run(final String... args) throws Exception {
    }
}

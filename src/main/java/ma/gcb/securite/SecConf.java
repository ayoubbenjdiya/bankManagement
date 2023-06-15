
package ma.gcb.securite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecConf extends WebSecurityConfigurerAdapter
{
    @Autowired
    private DataSource dataSource;
    
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(this.dataSource)
        .usersByUsernameQuery("select email as principal,CONCAT('{noop}',cin) as credentials ,'true' as enabled from utilisateur where email =?")
        .authoritiesByUsernameQuery("select email as principal ,type_utilisateur as role from utilisateur where email=?")
        .rolePrefix("ROLE_");
    }
    
    protected void configure(final HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login");
        http.logout().logoutRequestMatcher((RequestMatcher)new AntPathRequestMatcher("/logout"));
        http.csrf().disable();
        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)http.authorizeRequests().antMatchers(new String[] { "/login" })).permitAll().antMatchers(HttpMethod.GET, new String[] { "/index" })).authenticated().antMatchers(HttpMethod.GET, new String[] { "/home" })).hasAnyAuthority(new String[] { "ROLE_AG", "ROLE_AD", "ROLE_CL" }).antMatchers(HttpMethod.GET, new String[] { "/gestion_des_comptes" })).hasAnyAuthority(new String[] { "ROLE_AG" }).antMatchers(HttpMethod.GET, new String[] { "/gestion_des_operations" })).hasAnyAuthority(new String[] { "ROLE_AG" }).antMatchers(HttpMethod.GET, new String[] { "/gestion_des_agents" })).hasAnyAuthority(new String[] { "ROLE_AD" }).antMatchers(HttpMethod.GET, new String[] { "/gestion_des_clients" })).hasAnyAuthority(new String[] { "ROLE_AG", "ROLE_AD" });
    }
}

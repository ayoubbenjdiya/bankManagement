
package ma.gcb.dao;

import ma.gcb.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;


@Component
public interface OperationRepo extends JpaRepository<Operation, Long>
{
    @Query("select o from Operation o where o.compte.codeCompte=:x order by o.date desc ")
    Page<Operation> listOperation(@Param("x") final String codeComte, final Pageable pageable);
}

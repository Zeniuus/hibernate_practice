package kr.co.vcnc.swan.study.hibernate.practice

import org.hibernate.testing.transaction.TransactionUtil
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.function.Supplier
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory

@RunWith(SpringRunner::class)
@SpringBootTest
class AbstractHibernateTest {
    @Autowired
    lateinit var entityManagerFactory: EntityManagerFactory

    private val supplier: Supplier<EntityManagerFactory> by lazy {
        Supplier { entityManagerFactory }
    }

    fun doInJPA(block: (EntityManager) -> Unit) {
        TransactionUtil.doInJPA(supplier, TransactionUtil.JPATransactionVoidFunction {
            block(it)
        })
    }
}
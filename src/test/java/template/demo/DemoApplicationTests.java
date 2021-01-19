package template.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Commit
class DemoApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
		/*
		Hello hello = new Hello();
		em.persist(hello);

		JPAQueryFactory query = new JPAQueryFactory(em);
		//QHello qHello = new QHello("h");
		QHello qHello = QHello.hello;

		Hello result = query
				.selectFrom(qHello)
				.fetchOne();
		Assertions.assertThat(result).isEqualTo(hello);
		Assertions.assertThat(result.getId()).isEqualTo(hello.getId());
		*/

	}

}

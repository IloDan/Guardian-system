package com.guardian.api.operator;

import com.guardian.api.operator.models.Operator;
import com.guardian.api.operator.repositories.OperatorRepository;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OperatorRepositoryTest {

    @Autowired
    OperatorRepository operatorRepository;
    @Test
    public void testEmptyDB(){
        assertEquals(2,operatorRepository.findAll().size()  );
    }

    @Test
    public void testAddOperator() {
        val op = Operator.builder().name("Pluto").contact("Pluto@email.it").build();
        operatorRepository.save(op);
        assertEquals(3,operatorRepository.findAll().size()  );
    }

    @Test
    public void testDeleteAllOperators() {
        operatorRepository.deleteAll();
        assertEquals(0,operatorRepository.findAll().size()  );
    }
}

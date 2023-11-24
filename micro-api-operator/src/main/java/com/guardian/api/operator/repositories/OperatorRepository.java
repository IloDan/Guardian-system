package com.guardian.api.operator.repositories;

import com.guardian.api.operator.models.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  OperatorRepository  extends JpaRepository<Operator, Long> {

}

package pe.upc.ruedarentprojectmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.ruedarentprojectmobile.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    Plan findByPlanType(String PlanType);

    boolean existsByPlanType(String PlanType);
}

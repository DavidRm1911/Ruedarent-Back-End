package pe.upc.ruedarentprojectmobile.service.Plan;

import pe.upc.ruedarentprojectmobile.model.Plan;

import java.util.List;

public interface IPlanService {

    Plan getPlanById(Long id);
    List<Plan> getAllPlans();
    Plan addPlan(Plan plan);
    void deletePlanById(Long id);
    Plan updatePlan(Plan plan, Long planId);
}

package pe.upc.ruedarentprojectmobile.service.Plan;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.upc.ruedarentprojectmobile.model.Plan;
import pe.upc.ruedarentprojectmobile.repository.PlanRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanService implements IPlanService{
    private final PlanRepository planRepository;


    @Override
    public Plan getPlanById(Long id) {
        return planRepository.findById(id).orElseThrow(()-> new RuntimeException("Plan not found"));
    }

    @Override
    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }

    @Override
    public Plan addPlan(Plan plan) {
        return Optional.ofNullable(plan).filter(p -> !planRepository.existsByPlanType(p.getPlanType()))
                .map(planRepository::save)
                .orElseThrow(() -> new RuntimeException(plan.getPlanType()+ "already exists"));
    }

    @Override
    public void deletePlanById(Long id) {
        planRepository.findById(id).ifPresentOrElse(planRepository::delete,() -> {throw new RuntimeException("Plan not found");});
    }

    @Override
    public Plan updatePlan(Plan plan, Long planId) {
        return Optional.ofNullable(getPlanById(planId))
                .map(oldPlan -> {
                    oldPlan.setPlanType(plan.getPlanType());
                    oldPlan.setPlanDescription(plan.getPlanDescription());
                    oldPlan.setPlanPrice(plan.getPlanPrice());
                    return planRepository.save(oldPlan);
                }).orElseThrow(() -> new RuntimeException("Plan not found"));
    }
}

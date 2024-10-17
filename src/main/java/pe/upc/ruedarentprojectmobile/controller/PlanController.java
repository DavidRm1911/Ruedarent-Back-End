package pe.upc.ruedarentprojectmobile.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.ruedarentprojectmobile.model.Plan;
import pe.upc.ruedarentprojectmobile.response.ApiResponse;
import pe.upc.ruedarentprojectmobile.service.Plan.IPlanService;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/Plan")
public class PlanController {
    private final IPlanService planService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllPlans(){
        try {
            List<Plan> plans = planService.getAllPlans();
            return ResponseEntity.ok(new ApiResponse("Found", plans));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/{planId}/plan")
    public ResponseEntity<ApiResponse> getPlan(@PathVariable Long planId){
        try {
            Plan plan = planService.getPlanById(planId);
            return ResponseEntity.ok(new ApiResponse("Found", plan));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

    @DeleteMapping("/delete/{planId}/plan")
    public ResponseEntity<ApiResponse> deletePlan(@PathVariable Long planId){
        try {
            planService.deletePlanById(planId);
            return ResponseEntity.ok(new ApiResponse("Deleted", null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addPlan(@RequestBody Plan plan){
        try {
            Plan thePlan = planService.addPlan(plan);
            return ResponseEntity.ok(new ApiResponse("Plan added", thePlan));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/update/{planId}/plan")
    public ResponseEntity<ApiResponse> updatePlan(@RequestBody Plan plan, @PathVariable Long planId){
        try {
            Plan thePlan = planService.updatePlan(plan, planId);
            return ResponseEntity.ok(new ApiResponse("Plan updated", thePlan));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

}

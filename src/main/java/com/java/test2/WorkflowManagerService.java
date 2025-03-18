package com.java.test2;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service de gestion des workflows
 */
@Service
public class WorkflowManagerService {

    private final WorkflowService workflowService;

    public WorkflowManagerService() {
        this.workflowService = WorkflowService.getInstance();
        initializeWorkflows();
    }

    /**
     * Initialise les workflows disponibles
     */
    private void initializeWorkflows() {
        workflowService.defineWorkflow("ValidationCommande", List.of(
                () -> System.out.println("Vérification du stock..."),
                () -> System.out.println("Validation du paiement..."),
                () -> System.out.println("Préparation de la commande..."),
                () -> System.out.println("Expédition de la commande...")
        ));

        workflowService.defineWorkflow("OnboardingEmploye", List.of(
                () -> System.out.println("Création du compte utilisateur..."),
                () -> System.out.println("Affectation d'un mentor..."),
                () -> System.out.println("Enregistrement auprès des RH..."),
                () -> System.out.println("Accès aux systèmes internes...")
        ));
    }

    /**
     * Démarre un workflow
     * @param workflowName Nom du workflow à exécuter
     */
    public void startWorkflow(String workflowName) {
        workflowService.executeWorkflow(workflowName);
    }
}


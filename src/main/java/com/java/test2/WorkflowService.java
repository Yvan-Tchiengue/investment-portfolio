package com.java.test2;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@Service
public class WorkflowService {

    private static WorkflowService instance;
    private final ExecutorService executorService;
    private final Map<String, Workflow> workflows;

    private WorkflowService() {
        this.executorService = Executors.newFixedThreadPool(5); // 5 threads pour exécuter les tâches en parallèle
        this.workflows = new HashMap<>();
    }

    /**
     * Méthode Singleton pour garantir une seule instance du service
     */
    public static synchronized WorkflowService getInstance() {
        if (instance == null) {
            instance = new WorkflowService();
        }
        return instance;
    }

    /**
     * Définit un nouveau workflow
     * @param name Nom du workflow
     * @param tasks Liste des tâches du workflow
     */
    public void defineWorkflow(String name, List<Runnable> tasks) {
        workflows.put(name, new Workflow(name, tasks));
    }

    /**
     * Exécute un workflow en séquence
     * @param name Nom du workflow
     */
    public void executeWorkflow(String name) {
        Workflow workflow = workflows.get(name);
        if (workflow == null) {
            throw new IllegalArgumentException("Workflow non trouvé: " + name);
        }

        System.out.println("Démarrage du workflow: " + name);
        
        for (Runnable task : workflow.getTasks()) {
            Future<?> future = executorService.submit(task);
            try {
                future.get(); // Attend la fin de l'exécution de la tâche avant de passer à la suivante
            } catch (Exception e) {
                System.err.println("Erreur dans l'exécution de la tâche: " + e.getMessage());
            }
        }

        System.out.println("Workflow terminé: " + name);
    }

    /**
     * Arrête proprement le moteur de workflow
     */
    public void shutdown() {
        executorService.shutdown();
        System.out.println("Moteur de workflow arrêté.");
    }

    /**
     * Classe interne représentant un workflow
     */
    private static class Workflow {
        private final String name;
        private final List<Runnable> tasks;

        public Workflow(String name, List<Runnable> tasks) {
            this.name = name;
            this.tasks = tasks;
        }

        public List<Runnable> getTasks() {
            return tasks;
        }
    }
}


package io.arrogantprogrammer.spiritanimals.mocker;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class MockerService {

    private static boolean isMocking = false;

    @RestClient
    WorkflowApiClient workflowApiClient;

    public void start() {
        isMocking = true;
        mock();
    }

    public void stop() {
        isMocking = false;
    }

    public void mock() {

       while(isMocking) {
            mockWorkflow();
        }
    }

    private void mockWorkflow() {

        Log.debugf("Mocking...");

        // Get an animal
        WorkflowRecord workflowRecord = workflowApiClient.assignSpiritAnimal(NameService.getName());
        Log.debugf("Assigned animal: %s", workflowRecord.spiritAnimal());

        // 25% chance of liking the animal
        if(randomNumberBetweenOneAndTen() % 4 == 0) {
            WorkflowRecord updatedWorkflowRecord = workflowApiClient.like(workflowRecord.id());
            Log.debugf("Liked animal: %s", updatedWorkflowRecord.spiritAnimal());
            WorkflowRecord feedbackWorkflowRecord = workflowApiClient.feedback(new FeedbackJson(updatedWorkflowRecord.id(), FeedbackService.getRandomButMostlyPositiveFeedback()));
            Log.debugf("Feedback submitted: %s", feedbackWorkflowRecord.feedback());
        }
        // 75% chance of not liking the animal
        else {
            WorkflowRecord updatedWorkflowRecord = workflowApiClient.whatIs(workflowRecord.id());
            Log.debugf("What is animal: %s", updatedWorkflowRecord.spiritAnimal());
        }

        // 30% chance of liking the new animal
        if(randomNumberBetweenOneAndTen() % 3 == 0) {
            WorkflowRecord updatedWorkflowRecord = workflowApiClient.like(workflowRecord.id());
            Log.debugf("Liked animal: %s", updatedWorkflowRecord.spiritAnimal());
            if(randomNumberBetweenOneAndTen() % 2 > 0) {
                WorkflowRecord feedbackWorkflowRecord = workflowApiClient.feedback(new FeedbackJson(updatedWorkflowRecord.id(), FeedbackService.getRandomButMostlyPositiveFeedback()));
                Log.debugf("Feedback submitted: %s", feedbackWorkflowRecord.feedback());
            }
        }
        // 70% chance of not liking the new animal
        else{
            WorkflowRecord aPoemWorkflowRecord = workflowApiClient.aPoem(workflowRecord.id());
            Log.debugf("What is animal: %s", aPoemWorkflowRecord.spiritAnimal());
        }

        // 30% chance of liking the new animal
        if(randomNumberBetweenOneAndTen() % 3 == 0) {
            WorkflowRecord updatedWorkflowRecord = workflowApiClient.like(workflowRecord.id());
            Log.debugf("Liked animal: %s", updatedWorkflowRecord.spiritAnimal());
            if(randomNumberBetweenOneAndTen() % 2 > 0) {
                WorkflowRecord feedbackWorkflowRecord = workflowApiClient.feedback(new FeedbackJson(updatedWorkflowRecord.id(), FeedbackService.getRandomButMostlyPositiveFeedback()));
                Log.debugf("Feedback submitted: %s", feedbackWorkflowRecord.feedback());
            }
        }
        // 70% chance of not liking the new animal
        else {
            WorkflowRecord anotherPoemWorkflowRecord = workflowApiClient.anotherPoem(workflowRecord.id());
            Log.debugf("What is animal: %s", anotherPoemWorkflowRecord.spiritAnimal());
        }

        // 70% chance of liking the new animal
        if(randomNumberBetweenOneAndTen() % 3 > 0) {
            WorkflowRecord updatedWorkflowRecord = workflowApiClient.like(workflowRecord.id());
            Log.debugf("Liked animal: %s", updatedWorkflowRecord.spiritAnimal());
            if(randomNumberBetweenOneAndTen() % 2 > 0) {
                WorkflowRecord feedbackWorkflowRecord = workflowApiClient.feedback(new FeedbackJson(updatedWorkflowRecord.id(), FeedbackService.getRandomButMostlyPositiveFeedback()));
                Log.debugf("Feedback submitted: %s", feedbackWorkflowRecord.feedback());
            }
        }
    }

    private int randomNumberBetweenOneAndTen() {
        return (int) (Math.random() * 10) + 1;
    }
}

package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Activity;
import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAIService aiService;
    private final RecommendationRepository recommendationRepository;

//    @RabbitListener(queues = "activity.queue")
//    public void processActivity(Activity activity) {
////        log.info("Received activity for processing: {}", activity.getId());
//        log.info("Generated Recommendation: {}", aiService.generateRecommendation(activity));
//        Recommendation recommendation = aiService.generateRecommendation(activity);
//        recommendationRepository.save(recommendation);
//    }



//@RabbitListener(queues = "activity.queue")
//public void processActivity(Activity activity) {
//
//    log.info("Received activity {}", activity.getId());
//
//    Recommendation recommendation =
//            aiService.generateRecommendation(activity);
//
//    log.info("Saving recommendation...");
//
//    Recommendation saved =
//            recommendationRepository.save(recommendation);
//
//    log.info("Saved recommendation id = {}", saved.getId());
//}

@RabbitListener(queues = "activity.queue")
public void processActivity(Activity activity) {

    try {

        log.info("Received activity {}", activity.getId());

        Recommendation recommendation =
                aiService.generateRecommendation(activity);

        log.info("Recommendation generated = {}", recommendation);

        Recommendation saved =
                recommendationRepository.save(recommendation);

        log.info("Saved recommendation = {}", saved);

    } catch (Exception e) {
        log.error("SAVE FAILED", e);
    }
}
}
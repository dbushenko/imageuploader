package aws.imgupload.imgupload.application;

import aws.imgupload.imgupload.service.SubscriptionService;
import aws.imgupload.imgupload.ui.data.EmailSubscription;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionApp {
    private final SubscriptionService subscriptionService;

    public SubscriptionApp(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    public void subscribe(final EmailSubscription emailSubscription) {
        subscriptionService.subscribe(emailSubscription.getEmail());
    }
}

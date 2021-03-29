package aws.imgupload.imgupload.ui;

import aws.imgupload.imgupload.application.SubscriptionApp;
import aws.imgupload.imgupload.ui.data.EmailSubscription;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subscribe")
public class SubscriptionsController {
    private final SubscriptionApp subscriptionApp;

    public SubscriptionsController(SubscriptionApp subscriptionApp) {
        this.subscriptionApp = subscriptionApp;
    }

    @GetMapping
    public String subscribePage() {
        return "subscribePage";
    }

    @PostMapping
    public String doSubscribe(final EmailSubscription emailSubscription) {
        subscriptionApp.subscribe(emailSubscription);
        return "subscribedSuccessfully";
    }
}

package aws.imgupload.imgupload.service.console;

import aws.imgupload.imgupload.service.SubscriptionService;

public class ConsoleSubscriptionService implements SubscriptionService {
    @Override
    public void subscribe(String email) {
        System.out.println(email);
    }

    @Override
    public void sendMessage(String messageTopic, String messageBody) {
        System.out.println(messageTopic);
        System.out.println(messageBody);
    }
}

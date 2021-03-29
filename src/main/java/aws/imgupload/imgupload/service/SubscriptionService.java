package aws.imgupload.imgupload.service;

public interface SubscriptionService {
    void subscribe(String email);
    void sendMessage(String messageTopic, String messageBody);
}

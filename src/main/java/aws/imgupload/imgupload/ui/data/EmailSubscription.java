package aws.imgupload.imgupload.ui.data;

public class EmailSubscription {
    private final String email;

    public EmailSubscription(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "EmailSubscription{" +
                "email='" + email + '\'' +
                '}';
    }
}

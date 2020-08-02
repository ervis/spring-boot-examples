package webapp;

@SuppressWarnings("PMD")
public final class InvalidResponses {
    private InvalidResponses() {

    }

    public static String anyXML() {
        // or find the file from file system
        // String body = Files.readString(Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource("test.xml")).toURI()));
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<user>\n" +
                "    <name>John Doe</name>\n" +
                "    <email>john.doe@test.com</email>\n" +
                "</user>";
    }

    public static String anyHTML() {
        // or find the file from file system
        // String body = Files.readString(Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource("test.html")).toURI()));
        return "<html lang=\"en\">\n" +
                "<body>\n" +
                "<h1>Hello, World!</h1>\n" +
                "</body>\n" +
                "</html>";
    }
}

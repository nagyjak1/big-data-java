package datalakemanager;

public record DateFormatter(String year, String month, String day) {
    public String buildPath() {
        return year + "/" + month + "/" + day;
    }
}

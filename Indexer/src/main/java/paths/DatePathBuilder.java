package paths;

import paths.DateFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePathBuilder {
    private final String year = new SimpleDateFormat("yyyy").format(new Date());
    private final String month = new SimpleDateFormat("MM").format(new Date());
    private final String day = new SimpleDateFormat("dd").format(new Date());

    private final String datePath = new DateFormatter(year, month, day).buildPath();

    public String getPath(String root) {
        return root + "/" + datePath;
    }
}

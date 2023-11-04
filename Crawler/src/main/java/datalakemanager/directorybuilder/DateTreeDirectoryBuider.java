package datalakemanager.directorybuilder;

import datalakemanager.DateFormatter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTreeDirectoryBuider {
    private final String year = new SimpleDateFormat("yyyy").format(new Date());
    private final String month = new SimpleDateFormat("MM").format(new Date());
    private final String day = new SimpleDateFormat("dd").format(new Date());

    private final String datePath = new DateFormatter(year, month, day).buildPath();

    public void createDirectory(String root) {
        File file = new File(root + "/" + datePath);
        File dateFile = new File(file.getPath());
        if (!dateFile.exists()) {
            dateFile.mkdirs();
        }
    }

    public String getPath(String root) {
        return root + "/" + datePath;
    }
}

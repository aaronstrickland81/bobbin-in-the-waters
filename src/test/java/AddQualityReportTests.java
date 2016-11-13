import model.User;
import model.WaterQualityReport;
import model.enums.PurityCondition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import services.QualityReportInfo;
import services.UserInfoTable;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * JUnit tests for addQualityReport method
 * Created by Neil on 11/13/2016.
 */
public class AddQualityReportTests {
    @Rule
    public Timeout globalTimeout = new Timeout(5000L, TimeUnit.MILLISECONDS);

    @Before
    public void init() {

    }

    @Test
    public void testDBInit() {
        List<WaterQualityReport> reports = QualityReportInfo.getQualityReports();
        WaterQualityReport test = new WaterQualityReport(new Date(1997,1,14), 1, "nacharya", 1.0, 2.0, PurityCondition.SAFE, 1.00, 1.00);
        boolean reportExists = false;
        if (reports.contains(test)) {
            reportExists = true;
        }
        //Assert.assertFalse(reportExists);
        Assert.assertFalse("Test report with worker name \"nacharya\"" +
                " made today should not exist in database", reportExists);
    }

    @Test
    public void testAddReport() {
        WaterQualityReport test = new WaterQualityReport(new Date(2016,1,14), 1, "nacharya", 2.0, 2.0, PurityCondition.SAFE, 1.00, 1.00);
        QualityReportInfo.addQualityReport(test);
        WaterQualityReport comp = QualityReportInfo.getQualityReports().get(QualityReportInfo.getQualityCounter() - 1);

        Assert.assertEquals("Added report should be equal to" +
                " user stored in database", test, comp);
    }
}

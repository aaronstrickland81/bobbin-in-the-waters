import model.WaterSourceReport;
import model.enums.SourceCondition;
import model.enums.WaterType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import services.SourceReportInfo;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * JUnit tests for addSourceReport method
 *
 * Created by Jason on 11/13/2016.
 */
public class AddSourceReportTests {
    @Rule
    public Timeout globalTimeout = new Timeout(20000L, TimeUnit.MILLISECONDS);

    @Before
    public void init() {

    }

    @Test
    public void addToDataBaseTest() {
        List<WaterSourceReport> report = SourceReportInfo.getSourceReports();
        WaterSourceReport test = new WaterSourceReport(new Date(2020,2,15), 1,
                "jlin", 5.0, 5.0, WaterType.BOTTLED, SourceCondition.POTABLE);
        boolean reportCreated = false;
        if (report.contains(test)) {
            reportCreated = true;
        }
        Assert.assertFalse("Test report with worker name \"jlin\"" +
                "made today should not exist in the database", reportCreated);
    }

    @Test
    public void testAddSourceReport() {
        WaterSourceReport test = new WaterSourceReport(new Date(2020,6,9), 1,
                "jlin", 10.0, 10.0, WaterType.Well,
                SourceCondition.TREATABLE_CLEAR);
        SourceReportInfo.addSourceReport(test);
        WaterSourceReport comp = SourceReportInfo.getSourceReports().get(
                SourceReportInfo.getSourceCounter() - 1);
        Assert.assertEquals("Added report should be equal to user " +
                "stored in database", test, comp);
    }
}

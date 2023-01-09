package tests.data;

import org.testng.annotations.DataProvider;

public class Data {


    @DataProvider(name = "dataProvider1")
    public Object[][] createData1() {
        return new Object[][]{
                {"Samsung", "iOS" },
        };
    }

}

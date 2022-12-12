package tests.data;

import org.testng.annotations.DataProvider;

public class Data {


    @DataProvider(name = "dataProvider1")
    public Object[][] createData1() {
        return new Object[][]{
                {"Cedric", new Integer(36)},
                {"Anne", new Integer(37)},
                {"Anne", new Integer(37)},
                {"Anne", new Integer(37)},
                {"Anne", new Integer(37)},
                {"Anne", new Integer(37)},

        };
    }

}

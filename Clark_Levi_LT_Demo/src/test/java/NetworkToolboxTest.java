import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class NetworkToolboxTest {

    @Test
    public void makeNetworkCallForString() throws IOException {
        InputStream fakeConnectionStream1 = new ByteArrayInputStream("testValue".getBytes());
        InputStream fakeConnectionStream2 = new ByteArrayInputStream("".getBytes());
        URLConnection fakeConnection = mock(URLConnection.class);
        NetworkToolbox fakeNetworkToolbox = spy(NetworkToolbox.class);
        doReturn(fakeConnection, fakeConnection, fakeConnection, null).when(fakeNetworkToolbox).connectToURL(any());
        doReturn(fakeConnectionStream1, fakeConnectionStream2, null).when(fakeConnection).getInputStream();
        Assert.assertEquals("testValue", fakeNetworkToolbox.makeNetworkCallForString(null, null));
        Assert.assertNull(fakeNetworkToolbox.makeNetworkCallForString(null, null));
        Assert.assertNull(fakeNetworkToolbox.makeNetworkCallForString(null, null));
        Assert.assertNull(fakeNetworkToolbox.makeNetworkCallForString(null, null));
    }

    @Test
    public void networkCallHandlesStringReturn() throws IOException {
        InputStream fakeConnectionStream = new ByteArrayInputStream("testValue".getBytes());
        URLConnection fakeConnection = mock(URLConnection.class);
        NetworkToolbox fakeNetworkToolbox = spy(NetworkToolbox.class);
        doReturn(fakeConnection).when(fakeNetworkToolbox).connectToURL(any());
        doReturn(fakeConnectionStream).when(fakeConnection).getInputStream();
        Assert.assertEquals("testValue", fakeNetworkToolbox.makeNetworkCallForString(null, null));
    }

    @Test
    public void networkCallHandlesEmptyReturn() throws IOException {
        InputStream fakeConnectionStream = new ByteArrayInputStream("".getBytes());
        URLConnection fakeConnection = mock(URLConnection.class);
        NetworkToolbox fakeNetworkToolbox = spy(NetworkToolbox.class);
        doReturn(fakeConnection).when(fakeNetworkToolbox).connectToURL(any());
        doReturn(fakeConnectionStream).when(fakeConnection).getInputStream();
        Assert.assertNull(fakeNetworkToolbox.makeNetworkCallForString(null, null));
    }

    @Test
    public void networkCallHandlesNullReturn() throws IOException {
        URLConnection fakeConnection = mock(URLConnection.class);
        NetworkToolbox fakeNetworkToolbox = spy(NetworkToolbox.class);
        doReturn(fakeConnection).when(fakeNetworkToolbox).connectToURL(any());
        doReturn(null).when(fakeConnection).getInputStream();
        Assert.assertNull(fakeNetworkToolbox.makeNetworkCallForString(null, null));
    }

    @Test
    public void buildQueryString() {
        NetworkToolbox networkToolbox = new NetworkToolbox();
        HashMap<String, String> testParameters = new HashMap<>();
        Assert.assertEquals("", networkToolbox.buildQueryString(testParameters));
        testParameters.put("testParameter", "testValue");
        checkQueryStringHasCorrectContents(networkToolbox.buildQueryString(testParameters), testParameters);
    }

    @Test
    public void buildQueryStringWithNullParams(){
        HashMap<String, String> testParameters = new HashMap<>();
        NetworkToolbox networkToolbox = new NetworkToolbox();
        Assert.assertEquals("", networkToolbox.buildQueryString(testParameters));
        testParameters.put("null", "null");
        checkQueryStringHasCorrectContents(networkToolbox.buildQueryString(testParameters), testParameters);
    }

    @Test
    public void buildQueryStringWithNumericParams(){
        HashMap<String, String> testParameters = new HashMap<>();
        NetworkToolbox networkToolbox = new NetworkToolbox();
        Assert.assertEquals("", networkToolbox.buildQueryString(testParameters));
        testParameters.put("2ndParam", "2ndVal");
        checkQueryStringHasCorrectContents(networkToolbox.buildQueryString(testParameters), testParameters);
    }

    private void checkQueryStringHasCorrectContents(String queryString, HashMap<String, String> parameters){
        ArrayList<String> parameterValues = new ArrayList<>(Arrays.asList(queryString.split("&")));
        for(String key : parameters.keySet()){
            Assert.assertTrue(parameterValues.contains(key + "=" + parameters.get(key)));
        }
    }
}
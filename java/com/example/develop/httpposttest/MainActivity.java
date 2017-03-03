package com.example.develop.httpposttest;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity implements View.OnClickListener {
    Button btnPost ;
    EditText etName;
    TextView tvIsConnected;
    Obd_data obddata;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPost = (Button)findViewById(R.id.btnPost);
        tvIsConnected = (TextView)findViewById(R.id.tvIsConnected);
        etName = (EditText)findViewById(R.id.etName);

        if(isConnected()){
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("You are conncted");
        }
        else{
            tvIsConnected.setText("You are NOT conncted");
        }

        btnPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnPost:
                if(!validate())
                    Toast.makeText(getBaseContext(), "Enter some data!", Toast.LENGTH_SHORT).show();
                // call AsynTask to perform network operation on separate thread
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date curDate = new Date(System.currentTimeMillis()) ; // 獲取當前時間
                date = formatter.format(curDate);

                new HttpAsyncTask().execute("http://211.23.50.130:28082/HttpReciver/postobd");
                break;
        }
    }

    private boolean validate(){
        if(etName.getText().toString().trim().equals(""))
            return false;
        else
            return true;
    }

    public static String POST(String u_rl, Obd_data obddata){
        // -----------------------   http Post for API under MarshMellow ----------------------------------------//
        /*InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient client = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
            JSONObject json_obj_1 = new JSONObject();    //用來當內層被包覆的JSON物件
            JSONObject cardata = new JSONObject();    //用來當內層被包覆的JSON物件
            cardata.put("speedlog", "0000255500002580");
            cardata.put("tmplog", "0580254105702580");
            cardata.put("pedallog", "");
            cardata.put("gpshighlog", "");
            cardata.put("loadlog", "0000255600002580");
            cardata.put("rpmlog", "00000025410000002580");
            cardata.put("voltlog", "116025441140258011402581117026401170264611702700");
            cardata.put("trip_weather_state", "0");
            cardata.put("maflog", "0000255500002580");
            cardata.put("gpslog", "023019559861202038574202539023019556051202038116502548023019567491202038650502585023019567491202038650502594023019565581202038650502631023019565581202038650502640023019563671202038574202677023019563671202038574202686023019510271202038345302729023019510271202038345302730023019510271202038345302739");
            cardata.put("orientationlog", "");
            cardata.put("accelerometerlog", "");
            //將1號JSON物件存好內容
            json_obj_1.put("idsn",obddata.getIdsn());
            json_obj_1.put("start","2017-02-13 11:14:00");
            //再來將2號物件設好key並丟進1號物件
            json_obj_1.put("obddata",cardata);
            //最後json_obj_1內容印出(當JSON+"\n"就可成為String印出,在傳輸時也必須用JSON+"\n")
            Log.i("text", "json_obj_1="+json_obj_1+"\n");

            // 4. convert JSONObject to JSON to String
            json = json_obj_1.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = client.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        Log.d("!!!!!!" , result);
        return result;*/
        //-----------------------------------------------------------------------------------------------------------------//

        //------------------   Http Post for upper than API MarshMellow --------------------------------------------//
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        StringBuilder resultcode = new StringBuilder();

        try {
            URL url = new URL(u_rl);
            urlConnection = (HttpURLConnection) url.openConnection();


        /* optional request header */
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Content-type", "application/json");

            // read response
        /* for Get request */
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            urlConnection.connect();
            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());

            JSONObject json_obj_1 = new JSONObject();    //用來當內層被包覆的JSON物件
            JSONObject cardata = new JSONObject();    //用來當內層被包覆的JSON物件
            cardata.put("speedlog", "0000255500002580");
            cardata.put("tmplog", "0580254105702580");
            cardata.put("pedallog", "");
            cardata.put("gpshighlog", "");
            cardata.put("loadlog", "0000255600002580");
            cardata.put("rpmlog", "00000025410000002580");
            cardata.put("voltlog", "116025441140258011402581117026401170264611702700");
            cardata.put("trip_weather_state", "0");
            cardata.put("maflog", "0000255500002580");
            cardata.put("gpslog", "023019559861202038574202539023019556051202038116502548023019567491202038650502585023019567491202038650502594023019565581202038650502631023019565581202038650502640023019563671202038574202677023019563671202038574202686023019510271202038345302729023019510271202038345302730023019510271202038345302739");
            cardata.put("orientationlog", "");
            cardata.put("accelerometerlog", "");
            //將1號JSON物件存好內容
            json_obj_1.put("idsn",obddata.getIdsn());
            json_obj_1.put("start",obddata.getDate());
            //再來將2號物件設好key並丟進1號物件
            json_obj_1.put("obddata",cardata);
            //最後json_obj_1內容印出(當JSON+"\n"就可成為String印出,在傳輸時也必須用JSON+"\n")
            String result = json_obj_1.toString();
            Log.i("text", "json_obj_1="+result);

            wr.writeBytes(result);
            wr.flush();
            wr.close();

            // try to get response
            inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                resultcode.append(line);
                Log.d("resultCode" , resultcode.toString());
            }

            int responseCode = urlConnection.getResponseCode();
            Log.d("responseCode", String.valueOf(responseCode));
            return String.valueOf(responseCode);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            obddata = new Obd_data();
            obddata.setIdsn(etName.getText().toString());
            obddata.setDate(date);
        }
        @Override
        protected String doInBackground(String... urls) {
            return POST(urls[0],obddata);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }
    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

}



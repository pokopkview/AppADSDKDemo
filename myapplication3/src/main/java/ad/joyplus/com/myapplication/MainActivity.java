package ad.joyplus.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ad.joyplus.com.myapplication.AppUtil.gson.Gson;
import ad.joyplus.com.myapplication.entity.OpeningAdModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String json = "{\n" +
                "    \"Code\":\"1\",\n" +
                "    \"VersonCode\":\"1.0\",\n" +
                "    \"msg\":\"ok\",\n" +
                "    \"data\":{\n" +
                "        \"imageurl\":\"http://fwcdn.joyplus.tv/m/zhibodi.jpg\",\n" +
                "        \"impressionurl\":\"http://fwcdn.joyplus.tv/m/zhibodi.jpg\",\n" +
                "        \"clickurl\":\"\",\n" +
                "        \"showcount\":\"http://rapi.joyplus.tv/r.php?z=20&s=33\"\n" +
                "    },\n" +
                "    \"html5_url\":\"http://xxxx.xxxx.xxxx\"\n" +
                "}";
        Gson gson = new Gson();
        OpeningAdModel model = gson.fromJson(json, OpeningAdModel.class);
        System.out.println(model.getHtml5_url());
    }
}

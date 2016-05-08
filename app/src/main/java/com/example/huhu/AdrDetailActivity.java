package com.example.huhu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AdrDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private JSONObject mJsonData = null;

    private TextView mTxtArea;

    private OptionsPickerView mPickerView;

    ArrayList<ArrayList<ArrayList<String>>> a = new ArrayList<ArrayList<ArrayList<String>>>();
    ArrayList<ArrayList<String>> b = new ArrayList<ArrayList<String>>();
    ArrayList<String> c = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_adr_detail);

        initData();
        mPickerView = new OptionsPickerView(this);
        mPickerView.setPicker(c, b, a, true);
        mPickerView.setCyclic(false, false, false);
        mPickerView.setTitle("选择城市");
        //设置默认的三个级别的选中位置
        //监听确定选择按钮
        mPickerView.setSelectOptions(0, 0, 0);
        mPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String str = c.get(options1) + b.get(options1).get(option2) +
                        a.get(options1).get(option2).get(options3);
                mTxtArea.setText(str);
            }
        });
        mTxtArea.setOnClickListener(this);

    }

    private void initData() {
        getJsonData();
        parseJsonData();
        mTxtArea = (TextView) this.findViewById(R.id.adr_detail_area);
        mPickerView = new OptionsPickerView(this);
    }


    /**
     * 获取本地Json数据
     */
    private void getJsonData() {

        try {
            StringBuffer sbf = new StringBuffer();
            InputStream is = this.getAssets().open("city.json");
            int len = -1;
            byte[] buf = new byte[is.available()];
            while ((len = is.read(buf)) != -1) {
                sbf.append(new String(buf, 0, len, "gbk"));
            }
            is.close();
            mJsonData = new JSONObject(sbf.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析Json数据
     */
    private void parseJsonData() {

        try {

            ArrayList<String> subArea = null;
            ArrayList<String> subCity = null;

            JSONArray jsonArray = mJsonData.getJSONArray("citylist");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonP = jsonArray.getJSONObject(i);// 每个省的json对象
                String province = jsonP.getString("p");// 省名字

                c.add(province);
                subCity = new ArrayList<String>();
                ArrayList<ArrayList<String>> subArea02 = new ArrayList<ArrayList<String>>();

                JSONArray jsonCs = null;
                jsonCs = jsonP.getJSONArray("c");

                for (int j = 0; j < jsonCs.length(); j++) {
                    JSONObject jsonCity = jsonCs.getJSONObject(j);
                    String city = jsonCity.getString("n");// 市名字

                    subCity.add(city);
                    subArea = new ArrayList<String>();

                    JSONArray jsonAreas = null;
                    jsonAreas = jsonCity.getJSONArray("a");

                    for (int k = 0; k < jsonAreas.length(); k++) {

                        String area = jsonAreas.getJSONObject(k).getString("s");// 区域的名称
                        subArea.add(area);

                    }
                    subArea02.add(subArea);
                }
                a.add(subArea02);
                b.add(subCity);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mJsonData = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adr_detail_area:
                mPickerView.show();
                break;
        }
    }
}

package com.example.basicfunctions;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.ListView;

import com.example.basicfunctions.adapter.BasicFuncAdapter;
import com.example.basicfunctions.entity.MetaData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<MetaData> metaDataList=new ArrayList<>();
    private Context mContext;
    private BasicFuncAdapter basicFuncAdapter=null;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);

        //隐藏title
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        mContext=this;
        listView=(ListView) findViewById(R.id.listview);

        //解析Json数据
        try{
            AssetManager assetManager=getAssets();
            InputStreamReader inputStreamReader;
            inputStreamReader = new InputStreamReader(assetManager.open("metadata.json"));
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line;
            StringBuilder builder=new StringBuilder();
            while((line=bufferedReader.readLine())!=null){
                builder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();

            JSONArray jsonArray=new JSONArray(builder.toString());
            for (int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                String title=object.getString("title");
                String author=object.getString("author");
                String publishTime=object.getString("publishTime");
                int type=object.getInt("type");
                if(type==0){
                    metaDataList.add(new MetaData(title,author,publishTime,type));
                }
                else if(type==4){
                    String[] covers=new String[4];
                    JSONArray jsonArrayTmp=object.getJSONArray("covers");
                    for(int j=0;j<jsonArrayTmp.length();j++){
                        covers[j]=removeSuffix(jsonArrayTmp.getString(j));
                    }
                    metaDataList.add(new MetaData(title,author,publishTime,type,covers));
                }
                else {
                    metaDataList.add(new MetaData(title,author,publishTime,type,removeSuffix(object.getString("cover"))));
                }
            }
        }
        catch (IOException | JSONException e){
            e.printStackTrace();
        }

        basicFuncAdapter=new BasicFuncAdapter(metaDataList,mContext);
        listView.setAdapter(basicFuncAdapter);
    }

    /**
     * 去除图片名称字符串后缀
     * @param pre
     * @return
     */
    private String removeSuffix(String pre){
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<pre.length();i++){
            if(pre.charAt(i)=='.') break;
            stringBuilder.append(pre.charAt(i));
        }
        return stringBuilder.toString();
    }
}
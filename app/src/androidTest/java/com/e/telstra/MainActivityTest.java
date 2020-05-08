package com.e.telstra;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.e.telstra.activities.MainActivity;
import com.e.telstra.adapter.NewsListAdapter;
import com.e.telstra.model.NewsModel;

import androidx.test.rule.ActivityTestRule;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule=new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity=null;

    private String mQuery="believe";
    @Before
    public void setUp() throws Exception {
        mainActivity=mainActivityActivityTestRule.getActivity();
    }


    @Test
    public void findItemsInTheList(){
        final ListView listview = mainActivity.findViewById(R.id.listView);
        List<NewsModel> newsModels=new ArrayList<>();
        newsModels.add(new NewsModel("Title 1","Description 1","http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"));
        newsModels.add(new NewsModel("Title 2","Description 2","http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"));
        final NewsListAdapter adapter=new NewsListAdapter(mainActivity,newsModels);

        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listview.setAdapter(adapter);
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        assertThat(adapter, CoreMatchers.<ListAdapter>instanceOf(NewsListAdapter.class));
        assertThat(adapter.getCount(), greaterThan(0));
        assertThat(listview.getCount(), greaterThan(0));
    }

}

package com.sandy.bog.myapplication;

import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRv;
    private List<School> mData = new ArrayList<>();
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRv = (RecyclerView) findViewById(R.id.lv_school);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        findViewById(R.id.add_data).setOnClickListener((v) -> addNewData());

        SchoolCollection.getInstance().refreshData();
        mData = SchoolCollection.getInstance().getSchools();
        mMyAdapter = new MyAdapter(mData);
        mRv.setAdapter(mMyAdapter);
        SchoolCollection.getInstance().addSchoolListCallback(callback);
//        SchoolCollection.getInstance().onDataChange();
//        SchoolCollection.getInstance().addData();
    }

    private void addNewData() {
        if (mData.size() == 0) {
            return;
        }
        School school = mData.get(0);
        mData.remove(school);
//        mMyAdapter.notifyItemRemoved(0);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SchoolCollection.getInstance().removeSchoolListCallback(callback);
    }

    private ObservableList.OnListChangedCallback<ObservableList<School>> callback =
            new ObservableList.OnListChangedCallback<ObservableList<School>>() {
                @Override
                public void onChanged(ObservableList<School> sender) {
                    runOnUiThread(() -> notifyChange());
                }

                @Override
                public void onItemRangeChanged(ObservableList<School> sender, int positionStart, int itemCount) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mMyAdapter != null) {
                                mMyAdapter.notifyItemRangeChanged(positionStart, itemCount);
                            }
                        }
                    });
                }

                @Override
                public void onItemRangeInserted(ObservableList<School> sender, int positionStart, int itemCount) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mMyAdapter != null) {
                                mMyAdapter.notifyItemRangeInserted(positionStart, itemCount);
                            }
                        }
                    });
                }

                @Override
                public void onItemRangeMoved(ObservableList<School> sender, int fromPosition, int toPosition, int itemCount) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mMyAdapter != null) {
                                mMyAdapter.notifyItemMoved(fromPosition, toPosition);
                            }
                        }
                    });
                }

                @Override
                public void onItemRangeRemoved(ObservableList<School> sender, int positionStart, int itemCount) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mMyAdapter != null) {
                                mMyAdapter.notifyItemRangeRemoved(positionStart, itemCount);
                            }
                        }
                    });
                }
            };

    private void notifyChange() {
        if (mMyAdapter != null) {
            mMyAdapter.notifyDataSetChanged();
        }
    }


}

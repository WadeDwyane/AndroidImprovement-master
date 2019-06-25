package com.wadedwyane.www.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wadedwyane.www.R;
import com.wadedwyane.www.adapter.FeedAdapter;

public class RecyclerViewTitleHoverActivity extends AppCompatActivity {

    private RecyclerView mMrcy;
    private RelativeLayout suppensionBar;
    private TextView mSuspensionTv;
    private ImageView mSuspensionIv;

    private int mCurrentPosition;
    private int mSuspensionHeight;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_title_hover);
        mMrcy = findViewById(R.id.recyclerView);
        suppensionBar = findViewById(R.id.suspension_bar);
        mSuspensionIv = findViewById(R.id.iv_avatar);
        mSuspensionTv = findViewById(R.id.tv_nickname);
        initView();
    }

    private void initView() {
        mManager = new LinearLayoutManager(this);
        mMrcy.setLayoutManager(mManager);
        FeedAdapter feedAdapter = new FeedAdapter(this);
        mMrcy.setAdapter(feedAdapter);
        mMrcy.setHasFixedSize(true);

        mMrcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //获取悬浮栏高度
                mSuspensionHeight = suppensionBar.getHeight();
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //对悬浮条位置进行调整
                View view = mManager.findViewByPosition(mCurrentPosition + 1);
                if (null != view) {
                    if (view.getTop() < mSuspensionHeight) {
                        //需要对悬浮条目位置进行调整
                        suppensionBar.setY(-(mSuspensionHeight - view.getTop()));
                    }else {
                        suppensionBar.setY(0);
                    }
                }

                if (mCurrentPosition != mManager.findFirstVisibleItemPosition()){
                    mCurrentPosition = mManager.findFirstVisibleItemPosition();
                    updateSuspensionBar();
                }
            }
        });

        updateSuspensionBar();
    }

    private void updateSuspensionBar() {
        Glide.with(this)
                .load(getAvatarResId(mCurrentPosition))
                .centerInside()
                .fitCenter()
                .into(mSuspensionIv);
        mSuspensionTv.setText("WadeDwyane "  + mCurrentPosition);
    }

    private int getAvatarResId(int position){
        switch (position % 4){
            case 0:
                return R.drawable.avatar1;
            case 1:
                return R.drawable.avatar2;
            case 2:
                return R.drawable.avatar3;
            case 3:
                return R.drawable.avatar4;
        }
        return 0;
    }
}

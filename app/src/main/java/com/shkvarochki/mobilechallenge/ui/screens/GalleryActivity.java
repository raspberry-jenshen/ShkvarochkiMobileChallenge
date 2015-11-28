package com.shkvarochki.mobilechallenge.ui.screens;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.listeners.RecyclerClickListener;
import com.shkvarochki.mobilechallenge.ui.UiStateController;
import com.shkvarochki.mobilechallenge.ui.adapters.RecyclerViewAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.gallery_activity)
public class GalleryActivity extends AppCompatActivity {

    @ViewById
    protected RecyclerView recyclerView;
    @ViewById
    protected View loading_ui_View;
    @ViewById
    protected View error_ui_View;
    @ViewById
    protected View empty_ui_View;
    private RecyclerViewAdapter recyclerViewAdapter;
    private UiStateController uiStateController;

    @AfterViews
    protected void afterViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.addOnItemTouchListener(new RecyclerClickListener(this) {
            @Override
            public void onItemClick(RecyclerView recyclerView, View itemView, int position) {

            }
        });
        recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext());
        recyclerView.setAdapter(recyclerViewAdapter);
        uiStateController = new UiStateController.Builder()
                .withLoadingUi(loading_ui_View)
                .withErrorUi(error_ui_View)
                .withEmptyUi(empty_ui_View)
                .withContentUi(recyclerView)
                .build();
    }

    private void reloadData() {
        uiStateController.setUiStateLoading();
        /* Observable<List<TableInfo>> listObservable = dbModule.getTableInfoByQuery(
                Query.builder()
                        .table(GameTable.TABLE)
                        .orderBy(GameTable.COLUMN_DATE)
                        .build());
        final Subscription subscribe = listObservable
                .observeOn(AndroidSchedulers.mainThread()) // All Rx operations work on Schedulers.io()
                .subscribe(tables -> {
                    if (tables.isEmpty()) {
                        uiStateController.setUiStateEmpty();
                        adapter.setData(null);
                    } else {
                        uiStateController.setUiStateContent();
                        adapter.setData(tables);
                    }
                }, throwable -> {
                    uiStateController.setUiStateError();
                    adapter.setData(null);
                });
        unsubscribeOnStop(subscribe);*/
    }
}

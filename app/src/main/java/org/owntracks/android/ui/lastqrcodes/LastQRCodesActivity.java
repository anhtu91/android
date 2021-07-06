package org.owntracks.android.ui.lastqrcodes;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiLastQrCodesBinding;
import org.owntracks.android.model.LastQRCodesModel;
import org.owntracks.android.support.sqlite.SQLiteForLastJWTs;
import org.owntracks.android.ui.base.BaseActivity;

import java.util.Collections;

import timber.log.Timber;

public class LastQRCodesActivity extends BaseActivity<UiLastQrCodesBinding, LastQRCodesMvvm.ViewModel> implements LastQRCodesMvvm.View, LastQRCodesAdapter.ClickListener{

    private ObservableList<LastQRCodesModel> lastQRCodesList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lastQRCodesList = new ObservableArrayList<>();

        bindAndAttachContentView(R.layout.ui_last_qr_codes, savedInstanceState);
        setSupportToolbar(binding.toolbar);
        setDrawer(binding.toolbar);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new LastQRCodesAdapter(lastQRCodesList, this));

        //Swipe left or right to remove last JWT
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition(); //Get swipe position
                SQLiteForLastJWTs sqLiteForLastJWTs = new SQLiteForLastJWTs(LastQRCodesActivity.this); //Start SQLLite
                LastQRCodesModel selectedQRCode = lastQRCodesList.get(position);
                String swipedJWT = selectedQRCode.getLastJWT(); //Get string jwt of swiped object
                if(sqLiteForLastJWTs.removeLastJWT(swipedJWT)){ //Remove jwt from SQLLite
                    Toast.makeText(LastQRCodesActivity.this, getText(R.string.deleteQRCode), Toast.LENGTH_LONG).show();
                    lastQRCodesList.remove(position);
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        RecyclerView recyclerViewQRCode = (RecyclerView) findViewById(R.id.recycler_view);
        itemTouchHelper.attachToRecyclerView(recyclerViewQRCode);
    }

    @Override
    public void onClick(@NonNull LastQRCodesModel object, @NonNull View view, boolean longClick) {
        viewModel.onLastQRCodesShortClick(object);
    }

    @Override
    public void onResume() {
        super.onResume();
        lastQRCodesList.clear();

        if(lastQRCodesList.addAll(viewModel.getLastQRCodes())){
            Timber.i("Add successful last qr codes");
        }else{
            Timber.i("Add not successful last qr codes");
        }
    }

    @Override
    public void removeLastQRCodes(LastQRCodesModel p) {
        lastQRCodesList.remove(p);
    }

    @Override
    @MainThread
    public void addLastQRCodes(LastQRCodesModel p) {
        lastQRCodesList.add(p);
        //Collections.sort(lastQRCodesList);
    }

    @Override
    @MainThread
    public void updateLastQRCodes(LastQRCodesModel p) {
        //Collections.sort(lastQRCodesList);
    }
}

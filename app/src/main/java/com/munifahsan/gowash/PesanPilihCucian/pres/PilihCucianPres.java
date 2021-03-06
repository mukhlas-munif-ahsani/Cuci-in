package com.munifahsan.gowash.PesanPilihCucian.pres;

import com.munifahsan.gowash.EventBuss.EventBus;
import com.munifahsan.gowash.EventBuss.GreenRobotEventBus;
import com.munifahsan.gowash.PesanPilihCucian.view.PilihCucianActivityInt;
import com.munifahsan.gowash.PesanPilihCucian.PilihCucianEvent;
import com.munifahsan.gowash.PesanPilihCucian.repo.PilihCucianRepo;
import com.munifahsan.gowash.PesanPilihCucian.repo.PilihCucianRepoInt;

import org.greenrobot.eventbus.Subscribe;

import static com.munifahsan.gowash.PesanPilihCucian.PilihCucianEvent.onError;
import static com.munifahsan.gowash.PesanPilihCucian.PilihCucianEvent.onSuccess;

public class PilihCucianPres implements PilihCucianPresInt{
    private PilihCucianActivityInt mPilihCucianView;
    private EventBus mEventBus;
    private PilihCucianRepoInt mPilihCucianRepo;

    public PilihCucianPres(PilihCucianActivityInt mPilihCucianView) {
        this.mPilihCucianView = mPilihCucianView;
        mPilihCucianRepo = new PilihCucianRepo();
        mEventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
       mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mPilihCucianView = null;
        mEventBus.unregister(this);
    }

    @Subscribe
    public void onEventMainThread(PilihCucianEvent event){
        switch (event.getEventType()){
            case onSuccess:
                onSuccess();
                break;
            case onError:
                onError(event.getErrorMessage());
                break;
        }
    }

    private void onError(String msg) {
        mPilihCucianView.showMessage(msg);
    }

    private void onSuccess() {
        mPilihCucianView.showMessage("berhasil hore");
    }

    @Override
    public void createNewOrder(String idOrder, String idUser){
        mPilihCucianRepo.creatNewOrder(idOrder, idUser);
    }
}

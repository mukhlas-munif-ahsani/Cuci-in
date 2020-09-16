package com.munifahsan.gowash.PesanReview.pres;

import com.munifahsan.gowash.EventBuss.EventBus;
import com.munifahsan.gowash.EventBuss.GreenRobotEventBus;
import com.munifahsan.gowash.PesanReview.view.ReviewCucianActInt;
import com.munifahsan.gowash.PesanReview.ReviewCucianEvent;
import com.munifahsan.gowash.PesanReview.repo.ReviewCucianRepo;
import com.munifahsan.gowash.PesanReview.repo.ReviewCucianRepoInt;

import org.greenrobot.eventbus.Subscribe;

import static com.munifahsan.gowash.PesanReview.ReviewCucianEvent.onError;
import static com.munifahsan.gowash.PesanReview.ReviewCucianEvent.onSuccess;

public class ReviewCucianPres implements ReviewCucianPresInt {
    private ReviewCucianActInt mReviewCucianView;
    private EventBus mEventBus;
    private ReviewCucianRepoInt mReviewCucianRepo;

    public ReviewCucianPres(ReviewCucianActInt mReviewCucianView) {
        this.mReviewCucianView = mReviewCucianView;
        mEventBus = GreenRobotEventBus.getInstance();
        mReviewCucianRepo = new ReviewCucianRepo();
    }

    @Override
    public void onCreate() {
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mReviewCucianView = null;
        mEventBus.unregister(this);
    }

    @Subscribe
    public void onMainEventThread(ReviewCucianEvent event){
        switch (event.getEventType()){
            case onSuccess:
                onSuccess(event.getTotalBerat(), event.getTotalHarga());
                break;
            case onError:
                onError();
                break;
        }
    }

    @Override
    public void getData(String orderId){
        mReviewCucianRepo.getData(orderId);
    }

    private void onError() {

    }

    private void onSuccess(int totalBerat, int totalHarga) {
        mReviewCucianView.setTotalBerat(totalBerat);
        mReviewCucianView.setSubtotalHarga(totalHarga);
    }

}

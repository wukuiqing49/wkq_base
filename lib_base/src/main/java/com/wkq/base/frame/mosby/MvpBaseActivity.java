package com.wkq.base.frame.mosby;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.wkq.base.frame.mosby.delegate.ActivityMvpDelegate;
import com.wkq.base.frame.mosby.delegate.ActivityMvpDelegateImpl;
import com.wkq.base.frame.mosby.delegate.MvpDelegateCallback;
import com.wkq.base.frame.mosby.delegate.MvpPresenter;
import com.wkq.base.frame.mosby.delegate.MvpView;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/5/29
 * <p>
 * 简介:
 */
public abstract class MvpBaseActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends AppCompatActivity implements MvpView,
        MvpDelegateCallback<V,P> {

    protected ActivityMvpDelegate mvpDelegate;
    protected P presenter;
    protected boolean retainInstance;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

    @Override protected void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override protected void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override protected void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override protected void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override protected void onRestart() {
        super.onRestart();
        getMvpDelegate().onRestart();
    }

    @Override public void onContentChanged() {
        super.onContentChanged();
        getMvpDelegate().onContentChanged();
    }

    @Override protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getMvpDelegate().onPostCreate(savedInstanceState);
    }

    /**
     * Instantiate a presenter instance
     *
     * @return The {@link MvpPresenter} for this view
     */
    @NonNull
    public abstract P createPresenter();

    /**
     * Get the mvp delegate. This is internally used for creating presenter, attaching and detaching
     * view from presenter.
     *
     * <p><b>Please note that only one instance of mvp delegate should be used per Activity
     * instance</b>.
     * </p>
     *
     * <p>
     * Only override this method if you really know what you are doing.
     * </p>
     *
     * @return {@link ActivityMvpDelegateImpl}
     */
    @NonNull protected ActivityMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new ActivityMvpDelegateImpl(this, this, true);
        }

        return mvpDelegate;
    }

    @NonNull @Override public P getPresenter() {
        return presenter;
    }

    @Override public void setPresenter(@NonNull P presenter) {
        this.presenter = presenter;
    }

    @NonNull @Override public V getMvpView() {
        return (V) this;
    }
}

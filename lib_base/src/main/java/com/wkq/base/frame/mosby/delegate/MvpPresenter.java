
package com.wkq.base.frame.mosby.delegate;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

/**
 * Presenter的接口
 * @param <V>
 */
public interface MvpPresenter<V extends MvpView> {

  /**
   * Set or attach the view to this presenter
   */
  @UiThread
  void attachView(@NonNull V view);

  /**
   * Will be called if the view has been destroyed. Typically this method will be invoked from
   * <code>Activity.detachView()</code> or <code>Fragment.onDestroyView()</code>
   *
   * @deprecated This method has been split into 2 methods: {@link #detachView()} and {@link #destroy()}
    */
  @UiThread
  @Deprecated
  void detachView(boolean retainInstance);

  /**
   * Will be called if the view has been detached from the Presenter.
   * Usually this happens on screen orientation changes or view (like fragment) has been put on the backstack.
   */
  @UiThread
  void detachView();

  /**
   * Will be called if the presenter is no longer needed because the View has been destroyed permanently.
   * This is where you do clean up stuff.
   */
  @UiThread
  void destroy();
}

package com.wkq.base.frame.mosby;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/5/30
 * <p>
 * 简介:
 */

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.wkq.base.frame.mosby.delegate.MvpPresenter;
import com.wkq.base.frame.mosby.delegate.MvpView;

import java.lang.ref.WeakReference;

/**
 * A base implementation of a {@link MvpPresenter} that uses a <b>WeakReference</b> for referring
 * to the attached view.
 * <p>
 * You should always check {@link #isViewAttached()} to check if the view is attached to this
 * presenter before calling {@link #getView()} to access the view.
 * </p>
 *
 * <p>
 * <b>Why is this class using internally a WeakReference for referring to the attached view and why
 * do I have to use {@link #isViewAttached()} check?</b>
 * </p>
 * <p>
 * In a "perfect world" you wouldn't need this check nor a WeakReference. In Mosby <b>all
 * interaction
 * from Presenter to View must be executed on android's main UI thread</b>. Since screen
 * orientation changes are also executed on the main UI thread it is not possible to run into the
 * scenario where view is detached while presenter still wants to update the UI, right? However, we
 * are not living in a perfect world. Let's say you use an AsyncTask to make an http request and
 * the Presenter gets the result back and updates the View. If you forget to cancel this AsyncTask
 * after the View has been destroyed (i.e. android back button pressed, {@link
 * MvpPresenter#detachView(boolean)} will be called) then you can run into the scenario that the
 * View is detached from Presenter and then the Presenter gets the result back from AsyncTask and
 * wants to update the View which is null (because detached). So the `isViewAttached()` check is
 * basically some kind of safety net. In a perfect implementation you wouldn't need the {@link
 * #isViewAttached()} check.
 * </p>
 * <p>
 * Furthermore, in a perfect world you wouldn't need a WeakReference for referring to the View. In
 * Mosby you can create your own MvpDelegate to change Mosby's default implementation and
 * behaviour.
 * We have decided to use a WeakReference to avoid memory leaks if you use a custom MvpDelegate
 * that is not implementing the contract of attaching and detaching View from Presenter
 * properly (i.e. don't detach view in Activity.onDestroy() ).
 * </p>
 * <p>
 * So using a WeakReference and adding the {@link #isViewAttached()} check are basically just some
 * kind of safety net and not needed in a "perfect world". Please note that if you are sure that
 * you are coding in such a "perfect world" then you can also think about implementing your own
 * Presenter without WeakReference and isViewAttached(). Note also that
 * {@link MvpPresenter} is an interface. Hence implementing you own Presenter is easy.
 * </p>
 *
 * @param <V> type of the {@link MvpView}
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    /**
     * An Action that is executed to interact with the view.
     * Usually a Presenter should not get any data from View: so calls like view.getUserId() should not be done.
     * Rather write a method in your Presenter that takes the user id as parameter like this:
     * {@code
     *  void doSomething(int userId){
     *    // do something
     *    ...
     *
     *    ifViewAttached( view -> view.showSuccessful())
     *  }
     * @param <V> The Type of the View
     */
    public interface ViewAction<V> {

        /**
         * This method will be invoked to run the action. Implement this method to interact with the view.
         * @param view The reference to the view. Not null.
         */
        void run(@NonNull V view);
    }

    private WeakReference<V> viewRef;
    private boolean presenterDestroyed = false;

    @UiThread @Override public void attachView(V view) {
        viewRef = new WeakReference<V>(view);
        presenterDestroyed = false;
    }

    /**
     * Gets the attached view. You should always call {@link #isViewAttached()} to check if the view
     * is attached to avoid NullPointerExceptions.
     *
     * @return <code>null</code>, if view is not attached, otherwise the concrete view instance
     * @deprecated  Use {@link #ifViewAttached(ViewAction)}
     */
    @Deprecated @UiThread public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    /**
     * Checks if a view is attached to this presenter. You should always call this method before
     * calling {@link #getView()} to get the view instance.
     * @deprecated  Use {@link #ifViewAttached(ViewAction)}
     */
    @Deprecated @UiThread public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    /**
     * {@inheritDoc}
     */
    @Deprecated @UiThread @Override public void detachView(boolean retainInstance) {
    }

    /**
     * Executes the passed Action only if the View is attached.
     * If no View is attached, either an exception is thrown (if parameter exceptionIfViewNotAttached
     * is true) or the action is just not executed (no exception thrown).
     * Note that if no view is attached, this will not re-execute the given action if the View gets
     * re-attached.
     *
     * @param exceptionIfViewNotAttached true, if an exception should be thrown if no view is
     * attached while trying to execute the action. false, if no exception should be thrown (the action
     * will not be executed since no view is attached)
     * @param action The {@link ViewAction} that will be executed if a view is attached. This is
     * where you call view.isLoading etc. Use the view reference passed as parameter to {@link
     * ViewAction#run(Object)} and not deprecated method {@link #getView()}
     */
    protected final void ifViewAttached(boolean exceptionIfViewNotAttached, ViewAction<V> action) {
        final V view = viewRef == null ? null : viewRef.get();
        if (view != null) {
            action.run(view);
        } else if (exceptionIfViewNotAttached) {
            throw new IllegalStateException(
                    "No View attached to Presenter. Presenter destroyed = " + presenterDestroyed);
        }
    }

    /**
     * Calls {@link #ifViewAttached(boolean, ViewAction)} with false as first parameter (don't throw
     * exception if view not attached).
     *
     * @see #ifViewAttached(boolean, ViewAction)
     */
    protected final void ifViewAttached(ViewAction<V> action) {
        ifViewAttached(false, action);
    }

    /**
     * {@inheritDoc}
     */
    @Override public void detachView() {
        detachView(true);
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override public void destroy() {
        detachView(false);
        presenterDestroyed = true;
    }
}

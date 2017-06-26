package com.target.dealbrowserpoc.base;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Abstract presenter class defining process and lifecycle of any given presenter.
 *
 * @param <V>
 *     The view the presenter will interact with
 */
public abstract class BasePresenter<V> {

    private WeakReference<V> viewRef;
    private CompositeDisposable disposables;

    public void attachView(@NonNull V view) {
        viewRef = new WeakReference<>(view);

        onViewAttached();
    }

    public void detachView() {
        viewRef.clear();
        viewRef = null;

        onViewDetached();
    }

    /**
     * Method to be called when view is detached. Make sure to override this method in implementing
     * classes if you want to have an actual implementation.
     */
    protected void onViewAttached() {
    }

    /**
     * Method to be called when view is detached. Make sure to override this method in implementing
     * classes if you want more than just the default implementation.
     */
    protected void onViewDetached() {
        disposeComposites();
    }

    /**
     * Retrieve the view item to perform operations on.
     *
     * @return V view item
     */
    protected V getView() {
        if (isViewAttached()) {
            return viewRef.get();
        }

        throw new ViewNotAttachedException();
    }

    /**
     * Retrieve the {@link CompositeDisposable} to manage lifecycle of Rx calls within
     * presenter.
     *
     * @return {@link CompositeDisposable}
     */
    protected CompositeDisposable disposables() {
        if (disposables == null || disposables.isDisposed()) {
            disposables = new CompositeDisposable();
        }

        return disposables;
    }

    /**
     * Dispose of all current {@link io.reactivex.disposables.Disposable}s contained within the
     * {@link CompositeDisposable}.
     */
    protected void disposeComposites() {
        if (disposables != null
                && disposables.size() > 0
                && !disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    /**
     * Determines if a view has been attached to the presenter or not.
     *
     * @return {@link Boolean} view attached status
     */
    protected boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }
}
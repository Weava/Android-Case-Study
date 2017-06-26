package com.target.dealbrowserpoc.data;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import retrofit2.HttpException;
import retrofit2.adapter.rxjava2.Result;
import timber.log.Timber;

/**
 * {@link ObservableTransformer} for converting {@link Result} from HTTP calls to the potential
 * payload, or error, if that is the case.
 *
 * @param <T>
 *     The payload object type
 */
public class ResultTransformer<T> implements ObservableTransformer<Result<T>, T> {

    @Override
    public ObservableSource<T> apply(@NonNull Observable<Result<T>> upstream) {
        return upstream.flatMap(result -> {
                    if (!result.isError()) {
                        Timber.d("result occurred");
                        return Observable.just(result.response());
                    } else {
                        Timber.tag("Network Error").e(result.error(), result.error().getMessage());
                        return Observable.error(result.error());
                    }
                })
                .flatMap(response -> {
                    if (response.isSuccessful()) {
                        if (response.body() != null && !(response.body() instanceof Void)) {
                            Timber.d("Response occurred");
                            return Observable.just(response.body());
                        } else {
                            Timber.tag("Network Error").e(response.errorBody().string());
                            return Observable.error(new HttpException(response));
                        }
                    } else {
                        Timber.tag("Network Error").e(response.errorBody().string());
                        return Observable.error(new HttpException(response));
                    }
                });
    }
}

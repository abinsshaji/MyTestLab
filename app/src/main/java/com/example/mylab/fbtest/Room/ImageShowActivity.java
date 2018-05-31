package com.example.mylab.fbtest.Room;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mylab.fbtest.R;
import com.jakewharton.rxbinding2.widget.RxTextView;


import org.reactivestreams.Subscription;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class ImageShowActivity extends AppCompatActivity {

    public static final String TAG = "message";
    String path;
    File file;
    File[] files;
    String[] pathlist;
    List<GalleryEntity>galleryEntitieslist=new ArrayList<>();
    GalleryEntity galleryEntity;
    DbSingleton dbSingleton;
    GallleryDatabase databaseRef;
    Single<List<GalleryEntity>> listSingle;
    Flowable<String> textFlowable;
    @BindView(R.id.editText)EditText editText;
    @BindView(R.id.textView)TextView label;

    Observable<String> obs;


    private final CompositeDisposable disposable=new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        ButterKnife.bind(this);

        initDb();
        //Log.e(TAG, "onCreate:size"+galleryEntitieslist.size() );
        readTextData();
        if(galleryEntitieslist.size()<=0) {
            //getFile();
           // saveToDb();

        }
       // getGalleryData();

    }
    public void getFile()
    {
        path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/Whatsapp/Media/.Statuses";
        Log.e(TAG, "getFile: path"+path );
        file= new File(path);
        files=file.listFiles();
        pathlist=new String[files.length];
        for(int i=0;i<files.length;i++)
        {
            pathlist[i]=files[i].getAbsolutePath();

            galleryEntity=new GalleryEntity(pathlist[i]);
            galleryEntitieslist.add(galleryEntity);

        }
        Log.e(TAG, "getFile: size"+galleryEntitieslist.size());




    }
    private void initDb()
    {
       dbSingleton= DbSingleton.getDbSingleton();
       databaseRef=dbSingleton.initDb(this);


    }
    private void saveToDb()
    {
        Observable.fromCallable(new Callable<List<Long>>() {
            @Override
            public List<Long> call() throws Exception {
                return databaseRef.getGalleryDao().insertGallery(galleryEntitieslist);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Long>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe:save " );

                    }

                    @Override
                    public void onNext(List<Long> longs) {
                        Log.e(TAG, "onNext: save " +longs.get(0).toString());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError:save "+e.getMessage() );

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete:save " );

                    }
                });

    }

    private void getGalleryData()
    {


        databaseRef.getGalleryDao().getGallery().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<GalleryEntity>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: " );

            }

            @Override
            public void onSuccess(List<GalleryEntity> galleryEntities) {
                Log.e(TAG, "onSuccess: "+galleryEntities.get(0).getFpath() );

            }

            @Override
            public void onError(Throwable e) {

            }
        });


    }

    public void saveTestData(View view) {
        if(!editText.getText().toString().isEmpty())
        {
            //writeTexttoDb(editText.getText().toString());
            RxTextView.textChanges(editText).subscribe(new Observer<CharSequence>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(CharSequence charSequence) {
                    label.setText(charSequence);

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });


        }

    }

    public void writeTexttoDb(final String text)
    {
        final TextEntity textEntity=new TextEntity(text);
        disposable.add(Completable.fromRunnable(new Runnable() {
           @Override
           public void run() {
               databaseRef.getTextDao().insertData(textEntity);
           }
       }).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeWith(new DisposableCompletableObserver() {
                   @Override
                   public void onComplete() {
                       Log.e(TAG, "onComplete: write" );
                       editText.setText("");


                   }

                   @Override
                   public void onError(Throwable e) {
                       Log.e(TAG, "onError: write "+e.getMessage() );

                   }
               }));


    }

    public void readTextData()
    {

       disposable.add( databaseRef.getTextDao().getTextData().subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeWith(new DisposableSubscriber<String>() {
                   @Override
                   public void onNext(String s) {
                       //label.setText("");
                       label.setText(s);
                       Log.e(TAG, "onNext: read"+ s);

                   }

                   @Override
                   public void onError(Throwable t) {
                       Log.e(TAG, "onError:read "+t.getMessage() );

                   }

                   @Override
                   public void onComplete() {
                       Log.e(TAG, "onComplete: read" );


                   }
               }));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       if(! disposable.isDisposed())
           disposable.dispose();
    }
}

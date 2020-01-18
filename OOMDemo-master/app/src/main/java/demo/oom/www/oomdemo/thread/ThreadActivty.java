package demo.oom.www.oomdemo.thread;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;

import java.lang.ref.WeakReference;

public class ThreadActivty extends Activity {

    private MyRunnable mMyRunnable = new MyRunnable();
    private MyAsyncTask mMyAsyncTask = new MyAsyncTask(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(mMyRunnable).start();
        mMyAsyncTask.execute();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mMyAsyncTask && !mMyAsyncTask.isCancelled()) {
            mMyAsyncTask.cancel(true);
            mMyAsyncTask = null;
        }
    }

    /**
     * 使用静态内部类 + 弱引用的方式 解决Thread带来的内存泄漏
     */
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            SystemClock.sleep(1000);
        }
    }

    /**
     * 使用静态内部类 + 弱引用方式 解决AsyncTask带来的内存泄漏问题
     */
    static class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        private WeakReference<Context> mWeakReference;

        public MyAsyncTask(Context activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            SystemClock.sleep(1000);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ThreadActivty activty = (ThreadActivty) mWeakReference.get();
            if (null != activty) {
                // todo:

            }
        }
    }
}

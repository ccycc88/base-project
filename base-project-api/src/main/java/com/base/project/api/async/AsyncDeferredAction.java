package com.base.project.api.async;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@RestController
public class AsyncDeferredAction {

    private BlockingQueue<DeferredResult<String>> queue = new LinkedBlockingQueue<>();
    public AsyncDeferredAction(){

        this.init();
    }

    @GetMapping(value = "/deferred")
    public DeferredResult<String> deferred(){
        DeferredResult result = new DeferredResult<String>();
        queue.add(result);
        return result;
    }

    private void init(){

         for(int i=0; i<50; i++){

             new AsycnThread(queue).start();
         }
    }
    public class AsycnThread extends Thread{

        public BlockingQueue<DeferredResult<String>> tqueue = null;

        public AsycnThread(BlockingQueue<DeferredResult<String>> queue){

            this.tqueue = queue;
        }
        @Override
        public void run() {

            DeferredResult<String> dr = null;
            try {
                while((dr = tqueue.take()) != null){
                    dr.setResult("call");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

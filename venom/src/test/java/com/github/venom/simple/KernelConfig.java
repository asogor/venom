package com.github.venom.simple;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.venom.actors.CallBackMessage;
import com.github.venom.actors.CallBackTask;
import com.github.venom.actors.Key;
import com.github.venom.kernel.Kernel;
import com.google.inject.Inject;

public class KernelConfig {

        @Inject
        public void regisiter(Kernel kernel,Key<Result> mKey, Multiplier act)
        {
                kernel.registerProcess(mKey,act,Executors.newSingleThreadExecutor());
        }
        
        @Inject
        public void register(Kernel kernel,Key<CallBackMessage> timerKey,CallBackTask task)
        {
                kernel.registerProcess(timerKey,task, 2, TimeUnit.SECONDS,Executors.newScheduledThreadPool(1));
        }
        
        @Inject
        public void register(Kernel kernel,Key<CountDown> cKey,Finisher act)
        {
                kernel.registerProcess(cKey,act,Executors.newSingleThreadExecutor());
        }
}

package statusService.bl;

import statusService.entity.Entity;
import statusService.util.Texts;

public class StatusChangeThread implements Runnable {

    private Thread t;
    private Entity entity;
    private ServiceBD serviceBD;

    public StatusChangeThread(Entity entity,ServiceBD serviceBD) {
        t = new Thread(this, "changeStatus");
        this.entity=entity;
        this.serviceBD=serviceBD;
        t.start();
    }

    @Override
    public void run() {
        System.out.println(Texts.threadStart+this.entity.toString());
        String curDt = Util.getCurDate();
        entity.setDate(curDt);
        entity.setStatus("running");
        int resUpdate = serviceBD.updateEntity(entity);
        System.out.println(Texts.threadRunning+curDt);
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        System.out.println(Texts.threadAwake);
        curDt=Util.getCurDate();
        entity.setStatus("finished");
        entity.setDate(curDt);
        resUpdate = serviceBD.updateEntity(entity);
        System.out.println(Texts.threadFinished+curDt);
    }
}

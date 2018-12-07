package com.test.test1.utils;

import java.util.*;

/**
 *
 * @project: Electron-Ticket
 * @version: 2.0
 */
public class ClientRequestIPCache {

    private Map<String, List<Long>>  cameReqCount;

    public ClientRequestIPCache(){
        this.cameReqCount = new HashMap<>();
    }

    public boolean inMinutReqLessThan50(String ip,long time){
        if(!cameReqCount.containsKey(ip)) {
            createByIpClientList(ip);
        }

        cleanOneMinAgoReq(ip,time);

        if(cameReqCount.get(ip).size()<50){
            cameReqCount.get(ip).add(time);
            return true;
        }

        return false;
    }

    private void cleanOneMinAgoReq(String ip,long time){
        long oneMinAgoTime = time - (1000 * 60);
        Iterator<Long> reqComeTime = cameReqCount.get(ip).iterator();
        while (reqComeTime.hasNext()) {
            Long rtime = reqComeTime.next();
            if(rtime<oneMinAgoTime) {
                reqComeTime.remove();
            }
        }
    }

    private void createByIpClientList(String ip){
        cameReqCount.put(ip,new ArrayList<>());
    }
}

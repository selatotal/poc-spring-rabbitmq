package br.com.selat.pocspringrabbitmqconsumer.dynamiclistener;

public interface DynamicListenerService {
    void startListener(String identifier);

    void stopListener(String identifier);
}

package br.com.selat.pocspringrabbitmqproducer.service;

import br.com.selat.pocspringrabbitmqproducer.contract.v1.Message;
import br.com.selat.pocspringrabbitmqproducer.contract.v1.ProducerMessageInput;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProducerService {

    Message sendOutgoing(ProducerMessageInput message) throws JsonProcessingException;

    Message sendIncoming(ProducerMessageInput input) throws JsonProcessingException;
}

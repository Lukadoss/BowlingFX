package es.ulpgc.bowling.service;

import es.ulpgc.bowling.entity.Frame;

public interface FrameService extends BaseService<Frame> {

    Integer getFrameScore(Frame f);

    Boolean isLastFrame(Frame f);

    Boolean isStrike(Frame f);
}

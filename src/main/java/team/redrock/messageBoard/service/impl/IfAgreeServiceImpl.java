package team.redrock.messageBoard.service.impl;

import team.redrock.messageBoard.been.AGREE;
import team.redrock.messageBoard.been.Message;
import team.redrock.messageBoard.dao.IfAgreeDao;
import team.redrock.messageBoard.dao.MessageBoardDao;
import team.redrock.messageBoard.dao.impl.IfAgreeDaoImpl;
import team.redrock.messageBoard.dao.impl.MessageBoardDaoImpl;
import team.redrock.messageBoard.service.IfAgreeService;
import team.redrock.messageBoard.service.MessageBoardService;

public class IfAgreeServiceImpl implements IfAgreeService {
    //service的单例
    private static IfAgreeService instance = null;

    //dao的单例
    private IfAgreeDao ifAgreeDao = null;
    /**
     * 构造方法 实例化时为contentDao赋值
     */
    public IfAgreeServiceImpl() {
        ifAgreeDao = IfAgreeDaoImpl.getInstance();
    }

    public static IfAgreeService getInstance() {
        //双重校验锁
        if (instance == null) {
            synchronized (IfAgreeDaoImpl.class) {
                if (instance == null) {
                    instance = new IfAgreeServiceImpl();
                }
            }
        }
        return instance;
    }
    public boolean insertContent(AGREE agree) {

        if (agree.getUsername() != null && agree.getTextid() != null) {

           ifAgreeDao.InsertAgree(agree);
            return true;

        }

        return false;

    }

}


import com.kalix.admin.state.core.action.FSMAction;
import com.kalix.admin.state.core.fsm.FSM;
import com.kalix.admin.state.core.states.FSMState;
import com.kalix.admin.state.core.states.FSMStateList;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

public class Example1 {
    public static void testFSM() {
        try {
            FSM f = null;
            try {
                f = new FSM("C://config.xml", "INTERMEDIATE", new FSMAction() {
                    @Override
                    public boolean action(String curState, String message, String nextState, Object args) {
                        System.out.println(curState + ":" + message + " : " + nextState);
                        /*
                         * Here we can implement our login of how we wish to handle
                         * an action
                         */
                        return true;
                    }
                });
            } catch (org.xml.sax.SAXException e) {
                e.printStackTrace();
            }
            FSMState state = f.getCurrentFSMState();
            Map map = state.getTransitionMap();
            FSMStateList fsmStateList = f.get_fsm();

            System.out.println(f.getCurrentState());
            f.ProcessFSM("MOVELEFT");
            System.out.println(f.getCurrentState());
            f.ProcessFSM("MOVE");

            System.out.println(f.getCurrentState());
            f.ProcessFSM("MOVERIGHT");
            System.out.println(f.getCurrentState());
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
//            Logger.getLogger(TestOwnCode.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
//            Logger.getLogger(TestOwnCode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        try {
            testFSM();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

/**
 * GNU Public License
 * Copyright (C) 2014 Free Software Foundation, Inc. <http://fsf.org>
 * <p/>
 * This file is part of library EasyFSM.
 * <p/>
 * This library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This library can be redistributed
 * or used in case this license is copied as it is.
 * <p/>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * <p/>
 * Author : Ankit
 * Report bugs to : hiiankit (at) gmail (dot) com
 **/
package com.kalix.admin.state.core.states;

import com.kalix.admin.state.core.action.FSMAction;
import com.kalix.admin.state.core.common.CustomXMLReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Implements the list of fsm states
 *
 * <p>
 * This implementation caters to the need of maintaining the state of
 * of a fsm.
 * </p>
 *
 * @version 1.00
 * @author ANKIT
 */
public class FSMStateList implements java.io.Serializable {
    private static final long serialVersionUID = -7575735494729831944L;

    private Map<String, FSMState> _fsmStates;
    private ArrayList _states;
    private FSMState _curState;
    private String _configFileName = "config/config.xml";

    /**
     * <p>
     * This constructor allows to create a fsm from a Configuration File<br/>
     * This constructor allows a developer to have flexibility of specifying
     * an external XML Configuration file with a definite path.
     * </p>
     * <br/>
     *
     * @param configFName
     * @param extFile
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public FSMStateList(String configFName, String startState, boolean extFile)
            throws ParserConfigurationException, SAXException, IOException {

        if (!"".equals(configFName)) this._configFileName = configFName;
        this._fsmStates = new HashMap<>();
        CustomXMLReader _r = null;
        if (!extFile) {
            _r = new CustomXMLReader(
                    this.getClass().getClassLoader().getResourceAsStream(
                            this._configFileName)
            );
        } else {
            _r = new CustomXMLReader(this._configFileName);
        }

        _states = _r.getStates();

        for (Object _state : _states) {
            HashMap _t = _r.getStateInfo((String) _state);
            this._fsmStates.put((String) _state, new FSMState((String) _state, _t));
        }

        this._curState = (FSMState) this._fsmStates.get(startState);
    }

    /**
     * <p>
     * This constructor allows to create a fsm from a InputStream<br/>
     * This InputStream can be a :
     * <ol>
     *  <li> InputStream of a XML configuration file.
     * </ol>
     * <br/>
     * This constructor allows a developer to have flexibility of specifying
     * InputStream of a resource defined within a project.
     * </p>
     * <br/>
     *
     * @param configFStream InputStream of a XML Configuration file
     * @param startState
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public FSMStateList(InputStream configFStream, String startState)
            throws ParserConfigurationException, SAXException, IOException {
        CustomXMLReader _r = new CustomXMLReader(configFStream);
        this._fsmStates = new HashMap<>();
        _states = _r.getStates();

        for (Object _state : _states) {
            HashMap _t = _r.getStateInfo((String) _state);
            this._fsmStates.put((String) _state, new FSMState((String) _state, _t));
        }

        this._curState = (FSMState) this._fsmStates.get(startState);
    }

    /**
     * This constructor uses the default configuration file.
     * <br/>
     * This Constructor shall not be used and still exists to just test
     * or understand the behavior.
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @deprecated
     */
    @Deprecated
    public FSMStateList()
            throws ParserConfigurationException, SAXException, IOException {
        this("", "", false);
    }

    /**
     * This method allows to set the current state of the fsm
     * <br/>
     *
     * @param f
     */
    public void setCurrentState(FSMState f) {
        this._curState = f;
    }

    /**
     * This method allows to set specific action methods for a specific
     * message/action.<br/> 
     * If specified, this method shall be called when specified message is
     * received in specified list of states.
     * <br/>
     *
     * @param states List of states for which specified action method needs to
     *               be initiated
     * @param message Message/action which is received
     * @param act action method which needs to be initiated when message/action
     *            is received
     */
    public void setAction(ArrayList<String> states, String message,
                          FSMAction act) {
        int count = states.size();
        for (Iterator it = this._fsmStates.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, FSMState> entry = (Map.Entry<String, FSMState>) it.next();
            FSMState i = entry.getValue();
            if (states.contains(i.getCurrentState())) {
                i.addMessageAction(message, act);
                count--;
                if (count <= 0) break;
            }
        }
    }

    /**
     * This method allows to set specific action methods for a specific
     * message/action.<br/> 
     * If specified, this method shall be called when specified message is
     * received in specified state.
     * <br/>
     *
     * @param state State for which this override action method needs to be 
     *              initiated
     * @param message Message/action which is received
     * @param act action method which needs to be initiated when message/action
     *            is received
     */
    public void setAction(String state, String message,
                          FSMAction act) {
        setAction(new ArrayList(Arrays.asList(state)), message, act);
    }

    /**
     * This method allows to set specific action methods for a specific
     * message/action.<br/> 
     * If specified, this method shall be called when specified message is
     * received in any state.
     * <br/>
     *
     * @param message Message/action which is received
     * @param act action method which needs to be initiated when message/action
     *            is received
     */
    public void setAction(String message, FSMAction act) {
        ArrayList states = (ArrayList) getAllStates();
        int count = states.size();
        for (Iterator it = this._fsmStates.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, FSMState> entry = (Map.Entry<String, FSMState>) it.next();
            FSMState i = entry.getValue();
            if (states.contains(i)) {
                i.addMessageAction(message, act);
                count--;
                if (count <= 0) break;
            }
        }
    }

    /**
     * This method allows to set entry methods for a specific
     * State.<br/> 
     * If specified, this method shall be invoked before action method is 
     * invoked when any message is received in specified state.
     * <br/>
     *
     * @param state State for which entry function is being assigned
     * @param act Entry method which needs to be initiated when any message/action
     *            is received on specified state
     */
    public void setStateBeforeTransition(String state, FSMStateAction act) {
        for (Iterator it = this._fsmStates.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, FSMState> entry = (Map.Entry<String, FSMState>) it.next();
            FSMState i = entry.getValue();
            if (i.getCurrentState().equals(state)) {
                i.setBeforeTransition(act);
                break;
            }
        }
    }

    /**
     * This method allows to set entry methods for a specific
     * State.<br/> 
     * If specified, this method shall be invoked before action method is 
     * invoked when any message is received in specified state.
     * <br/>
     *
     * @param states List of State for which entry function is being assigned<br/>
     *               If passed null, entry method is applied to all states
     * @param act Entry method which needs to be initiated when any message/action
     *            is received on specified state
     */
    public void setStateBeforeTransition(ArrayList<String> states,
                                         FSMStateAction act) {
        if (states == null) {
            states = _states;
        }

        for (Iterator it = this._fsmStates.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, FSMState> entry = (Map.Entry<String, FSMState>) it.next();
            FSMState i = entry.getValue();
            if (states.contains(i.getCurrentState())) {
                i.setBeforeTransition(act);
            }
        }
    }

    /**
     * This method allows to set entry methods for a specific
     * State.<br/> 
     * If specified, this method shall be invoked after action method has been 
     * invoked when any message is received in specified state.
     * <br/>
     *
     * @param state State for which exit function is being assigned
     * @param act Exit method which needs to be initiated when any message/action
     *            is received on specified state
     */
    public void setStateAfterTransition(String state, FSMStateAction act) {
        for (Iterator it = this._fsmStates.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, FSMState> entry = (Map.Entry<String, FSMState>) it.next();
            FSMState i = entry.getValue();
            if (i.getCurrentState().equals(state)) {
                i.setAfterTransition(act);
                break;
            }
        }
    }

    /**
     * This method allows to set entry methods for a specific
     * State.<br/> 
     * If specified, this method shall be invoked after action method has been 
     * invoked when any message is received in specified state.
     * <br/>
     *
     * @param states List of State for which entry function is being assigned<br/>
     *               If passed null, entry method is applied to all states
     * @param act Exit method which needs to be initiated when any message/action
     *            is received on specified state
     */
    public void setStateAfterTransition(ArrayList<String> states,
                                        FSMStateAction act) {
        if (states == null) {
            states = _states;
        }

        for (Iterator it = this._fsmStates.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, FSMState> entry = (Map.Entry<String, FSMState>) it.next();
            FSMState i = entry.getValue();
            if (states.contains(i.getCurrentState())) {
                i.setAfterTransition(act);
            }
        }
    }

    /**
     * This method allows to get the current state of the fsm
     * <br/>
     *
     * @return Returns a FSMState object
     */
    public FSMState getCurrentState() {
        return this._curState;
    }

    /**
     * This method returns the list a fsm states configured for this fsm.
     * <br/>
     *
     * @return Returns the list of fsm states
     */
    public List<FSMState> getAllStates() {
        List<FSMState> list = new ArrayList<>();
        for (Iterator it = this._fsmStates.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, FSMState> entry = (Map.Entry<String, FSMState>) it.next();
            list.add(entry.getValue());
        }
        return list;
    }
}
